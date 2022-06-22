/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ryc.restful.ws_restful.Exceptions;

/**
 *
 * @author RyC
 */
public class NotFoundEntityException extends RuntimeException{

    /**
     * Constructor
     * @param message
     */
    public NotFoundEntityException(String message) {
        super(message);
    }
    
}
