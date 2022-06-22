/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ryc.restful.ws_restful.security;

import com.ryc.restful.ws_restful.annotation.Secured;
import com.ryc.restful.ws_restful.dao.EmployeesDAO;
import com.ryc.restful.ws_restful.modelo.MessageError;
import com.ryc.restful.ws_restful.util.Constantes;
import com.ryc.restful.ws_restful.util.CustomGeneratorToken;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Priorities;
import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author RyC
 */
@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class SecurityFilter implements ContainerRequestFilter {
    
    @Context
    private ResourceInfo resourceInfo;
    
    private EmployeesDAO empDAO = new EmployeesDAO();    
    
    private CustomGeneratorToken generatorToken;
    
    /*@Override
    public void filter(ContainerRequestContext crc) throws IOException {
        
        if(crc.getUriInfo().getPath().contains(SECURED_URL_PREFIX)){            
            List<String> authHeader = crc.getHeaders().get(AUTHORIZATION_HEADER_KEY);
            if(authHeader != null && authHeader.size() > 0) {
                String authToken = authHeader.get(0);
                authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PROFIX, "");
                String decodedString = Base64.decodeAsString(authToken);
                StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
                String username = tokenizer.nextToken();
                String password = tokenizer.nextToken();
                
                System.out.println("username: " + username);
                System.out.println("password: " + password);

                if("user".equals(username) && "password".equals(password)){
                    return;
                }            
            }

            Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED)
                                                        .entity("Usuario no puede acceder al recurso.")
                                                        .build();

            crc.abortWith(unauthorizedStatus);
        }
    }*/

    @Override
    public void filter(ContainerRequestContext crc) throws IOException {
        // Get the Authorization header from the request
        String authorizationHeader =
                crc.getHeaderString(HttpHeaders.AUTHORIZATION);
        
        System.out.println("******authorizationHeader******");
        
        // Validate the Authorization header
        if (!isTokenBasedAuthentication(authorizationHeader)) {
            abortWithUnauthorized(crc);
            return;
        }
        
        // Extract the token from the Authorization header
        String token = authorizationHeader
                            .substring(Constantes.TOKEN_PREFIX.length()).trim();
        
        generatorToken.decodeJWT(token);
        
        /**
         * VALIDA QUE SI SE GENERA UN NUEVO TOKEN, EL ANTERIOR NO ES VALIDO
         */
        if(Constantes.ONLY_TOKEN){
            String userId = generatorToken.decodeJWT(token).getId();
            try {
                if(!validateToken(token, Integer.parseInt(userId)))
                    abortWithUnauthorized(crc);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SecurityFilter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        /*****old*****/
        /*
        Method method = resourceInfo.getResourceMethod();
        
        if(!method.isAnnotationPresent(PermitAll.class)){
            if(method.isAnnotationPresent(DenyAll.class)){
               crc.abortWith(Response.status(Response.Status.FORBIDDEN)
                       .entity("Acceso denegado para todos los usuarios")
                       .build());
               return;
            }
            
            final MultivaluedMap<String, String> headers = crc.getHeaders();
            
            final List<String> authorization = headers.get(AUTHORIZATION_HEADER_KEY);
            
            if(authorization == null || authorization.isEmpty()){
                crc.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("No tienes acceso a este recurso").build());
                return;
            }
            
            final String encodedUserPassword = authorization.get(0).replace(AUTHORIZATION_HEADER_PROFIX, "");
            
            System.out.println("encodedUserPassword " + generatorToken.decodeJWT(encodedUserPassword));
            
            String usernameAndPassword = new String(Base64.decode(encodedUserPassword.getBytes()));
            
            final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
            
            final String username = tokenizer.nextToken();
            final String password = tokenizer.nextToken();
              
            //Verify user access
            if(method.isAnnotationPresent(RolesAllowed.class))
            {
                System.out.println("############isAnnotationPresent############");
                
                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
                Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));
                  
                //Is user valid?
                if( ! isUserAllowed(username, password, rolesSet))
                {
                    crc.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                        .entity("You cannot access this resource").build());
                    return;
                }
            }
        }*/
    }
    
    private boolean isTokenBasedAuthentication(String authorizationHeader) {

        // Check if the Authorization header is valid
        // It must not be null and must be prefixed with "Bearer" plus a whitespace
        // The authentication scheme comparison must be case-insensitive
        return authorizationHeader != null && authorizationHeader.toLowerCase()
                    .startsWith(Constantes.TOKEN_PREFIX.toLowerCase());
    }
    
    private void abortWithUnauthorized(ContainerRequestContext requestContext) {

        MessageError message = new MessageError("Usuario no puede acceder al recurso.", 401, "Debe validar su token");
        requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                                                        .entity(message)
                                                        .build());
    }
    
    private boolean isUserAllowed(final String username, final String password, final Set<String> rolesSet)
    {
        boolean isAllowed = false;
         
        if(username.equals("howtodoinjava") && password.equals("password"))
        {
            String userRole = "ADMIN";
             
            //Step 2. Verify user role
            if(rolesSet.contains(userRole))
            {
                isAllowed = true;
            }
        }
        return isAllowed;
    }
    
    private boolean validateToken(String token, int id) throws ClassNotFoundException
    {
        String empToken = empDAO.getTokenEmployeeById(id);
        
        System.out.println("Token: " + token);
        System.out.println("TokenUser: " + empToken);
        
        if(token.equals(empToken))
            return true;
        
        return false;
    }
   
}
