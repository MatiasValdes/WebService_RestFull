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
public class utils {
    
    public static boolean validarRut(String rut) {

        boolean validacion = false;
        
        try {
            rut =  rut.toUpperCase();
            rut = rut.replace(".", ""); // limpiamos el rut si viene con separador de miles
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dv = rut.charAt(rut.length() - 1);

            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
            s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
            validacion = true;
        }

        } catch (java.lang.NumberFormatException e) {
            
        } catch (Exception e) {
            
        }
        return validacion;
    }
    
}
