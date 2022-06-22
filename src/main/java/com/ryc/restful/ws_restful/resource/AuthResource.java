/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ryc.restful.ws_restful.resource;

import com.ryc.restful.ws_restful.dao.EmployeesDAO;
import com.ryc.restful.ws_restful.dao.UsersDAO;
import com.ryc.restful.ws_restful.modelo.Employees;
import com.ryc.restful.ws_restful.modelo.Token;
//import com.ryc.restful.ws_restful.modelo.IndicadorEconomico;
import com.ryc.restful.ws_restful.util.CustomGeneratorToken;
//import com.ryc.restful.ws_restful.util.ExternalAPI;
import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author RyC
 */
@Path("/auth")
public class AuthResource {
   CustomGeneratorToken Generatortoken = new CustomGeneratorToken();
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getToken(Employees user) throws SQLException, ClassNotFoundException {
        
        UsersDAO userDAO = new UsersDAO();
        EmployeesDAO empDAO = new EmployeesDAO();
        //ExternalAPI myapi = new ExternalAPI();
        Token token = new Token();
        
        /*
        
            CONSUMIENDO API EXTERNA DE PRUEBA
        
        IndicadorEconomico ie = new  IndicadorEconomico();
        
        ie = myapi.getJsonIndicadorEconomico("uf", new Date(2019, 06, 12));
        */
        Employees emp = null;
        
        if(userDAO.validateUser(user))
        {
            emp = empDAO.getEmployeeByEmail(user.getEmail());

            String tokenUser = Generatortoken.getToken(emp);

            if(empDAO.updateToken(emp.getId(), tokenUser))
            {
                token.setToken(tokenUser);
                
                return Response.status(Response.Status.CREATED)
                    .entity(token)
                    .build();
            }

        }
        
        return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(token)
                    .build();
    }

}
