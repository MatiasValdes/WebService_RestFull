/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ryc.restful.ws_restful.util;

import org.apache.log4j.Logger;
/**
 *
 * @author RyC
 */
public class CustomLogger {
    
    private final static Logger logger = Logger.getLogger(CustomLogger.class);
    
    public void Customlogg(String _class, String _method, String error, Enum type_error){
        switch(type_error.toString()) {
            case "FATAL":
              logger.debug("*******************************************************************");
              logger.fatal("Class: " + _class );
              logger.fatal("Metodo: " + _method);
              logger.fatal("Error: " + error);
              logger.debug("*******************************************************************");
              break;
            case "ERROR":
              logger.debug("*******************************************************************");
              logger.error("Class: " + _class );
              logger.error("Metodo: " + _method);
              logger.error("Error: " + error);
              logger.debug("*******************************************************************");
              break;
            case "WARNING":
                logger.debug("*******************************************************************");
                logger.warn("Class: " + _class );
                logger.warn("Metodo: " + _method);
                logger.warn("Error: " + error);
                logger.debug("*******************************************************************");
                break;
            case "INFO":
                logger.debug("*******************************************************************");
                logger.info("Class: " + _class );
                logger.info("Metodo: " + _method);
                logger.info("Error: " + error);
                logger.debug("*******************************************************************");
                break;
            case "DEBUG":
                logger.debug("*******************************************************************");
                logger.debug("Class: " + _class );
                logger.debug("Metodo: " + _method);
                logger.debug("Error: " + error);
                logger.debug("*******************************************************************");
                break;
            case "TRACE":
                logger.debug("*******************************************************************");
                logger.trace("Class: " + _class );
                logger.trace("Metodo: " + _method);
                logger.trace("Error: " + error);
                logger.debug("*******************************************************************");
                break;
            default:
                break;
          }
    }
    
    public void simpleLog(String message, Enum type_error)
    {
        switch(type_error.toString()) {
            case "FATAL":
                logger.fatal(message);
                break;
            case "ERROR":
                logger.error(message);
                break;
            case "WARNING":
                logger.warn(message);
                break;
            case "INFO":
                logger.info(message);
                break;
            case "DEBUG":
                logger.debug(message);
                break;
            case "TRACE":
                logger.trace(message);
                break;
            default:
                break;
          }
    }
    
}
