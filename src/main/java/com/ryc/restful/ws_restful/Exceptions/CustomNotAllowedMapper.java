/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ryc.restful.ws_restful.Exceptions;

import com.ryc.restful.ws_restful.modelo.MessageError;
import javax.ws.rs.NotAllowedException;
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
public class CustomNotAllowedMapper implements ExceptionMapper<NotAllowedException>{

    @Override
    @Produces( MediaType.APPLICATION_JSON)
    public Response toResponse(NotAllowedException exception) {
        MessageError message = new MessageError("NotAllowedException", 405, exception.getMessage());
        
        return Response.status(405)
                .entity(message)
                .build();
    }
    
}
