/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ryc.restful.ws_restful.Exceptions;

import com.ryc.restful.ws_restful.modelo.MessageError;
import javax.ws.rs.BadRequestException;
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
public class CustomBadRequestMapper implements ExceptionMapper<BadRequestException>{

    @Override
    @Produces( MediaType.APPLICATION_JSON)
    public Response toResponse(BadRequestException exception) {
        
        System.out.println("*** CustomBadRequestMapper ***");
        
        MessageError message = new MessageError("BadRequest", 400, exception.getMessage());
        
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(message)
                .build();
    }
    
}
