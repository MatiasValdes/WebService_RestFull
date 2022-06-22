/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ryc.restful.ws_restful.Exceptions;

import com.ryc.restful.ws_restful.modelo.MessageError;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author RyC
 */
public class CustomNotAcceptableMapper implements ExceptionMapper<NotAcceptableException>{

    @Override
    @Produces( MediaType.APPLICATION_JSON)
    public Response toResponse(NotAcceptableException exception) {
        MessageError message = new MessageError("NotAcceptableException", 406, exception.getMessage());
        
        return Response.status(406)
                .entity(message)
                .build();
    }
    
}
