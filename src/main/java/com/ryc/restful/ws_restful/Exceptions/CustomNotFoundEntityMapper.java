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
public class CustomNotFoundEntityMapper 
        implements ExceptionMapper<NotFoundEntityException>{

    @Override
    @Produces( MediaType.APPLICATION_JSON)
    public Response toResponse(NotFoundEntityException exception) {
        MessageError message = new MessageError(exception.getMessage(), 404, "La informacion solicitada no ha sido encontrada");
        return Response.status(Response.Status.NOT_FOUND)
                .entity(message)
                .build();
    }
    
}