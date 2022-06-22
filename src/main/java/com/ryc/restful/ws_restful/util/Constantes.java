/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ryc.restful.ws_restful.util;
/**
 *
 * @author RyC
 */
public final class Constantes {

    // JWT token
    public static final String  TOKEN_HEADER = "Authorization";
    public static final String  TOKEN_PREFIX = "Bearer ";
    public static final String  TOKEN_TYPE = "JWT";
    public static final String  TOKEN_ISSUER = "secure-api";
    public static final String  TOKEN_AUDIENCE = "secure-app";
    
    public static final long    TIME_SESSION_TOKEN = 600000;//1200000;
    
    /**
     * Controla que solo se pueda utilizar un token unico.
     */
    public static final boolean ONLY_TOKEN = false;
    
    /**
     * Tipos de escritura de LOG
     */
    public static enum TYPE_LOG { FATAL, ERROR, WARNING, INFO, DEBUG, TRACE };
    
    private Constantes() {
        throw new IllegalStateException("No se puede crear la instancia de la clase de utilidad estática");
    }
}
