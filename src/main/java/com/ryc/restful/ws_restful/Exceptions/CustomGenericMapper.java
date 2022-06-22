/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ryc.restful.ws_restful.Exceptions;

import com.ryc.restful.ws_restful.modelo.MessageError;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author RyC
 */
@Provider
public class CustomGenericMapper 
        implements ExceptionMapper<Throwable>{

    @Override
    @Produces( MediaType.APPLICATION_JSON)
    public Response toResponse(Throwable exception) {
        //MessageError message = new MessageError(exception.getMessage(), 500, exception.getCause().toString());
        MessageError message = new MessageError("Se ha producido un error en la solicitud", 405, exception.getMessage());
        
        return Response.status(Response.Status.NOT_FOUND)
                .entity(message)
                .build();
    }
    
}
