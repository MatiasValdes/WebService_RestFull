/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ryc.restful.ws_restful.Exceptions;

import com.ryc.restful.ws_restful.modelo.MessageError;
import javax.ws.rs.NotSupportedException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author RyC
 */
public class CustomNotSupportedMapper implements ExceptionMapper<NotSupportedException >{

    @Override
    @Produces( MediaType.APPLICATION_JSON)
    public Response toResponse(NotSupportedException exception) {
        MessageError message = new MessageError("NotSupportedException", 415, exception.getMessage());
        
        return Response.status(415)
                .entity(message)
                .build();
    }
    
}
