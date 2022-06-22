/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ryc.restful.ws_restful.util;

import com.ryc.restful.ws_restful.modelo.IndicadorEconomico;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
/**
 *
 * @author RyC
 */
public class ExternalAPI {
    
    private static String REST_URI = "http://mindicador.cl/api/";
  
    private final Client client = ClientBuilder.newClient();
 
    public IndicadorEconomico getJsonIndicadorEconomico(String moneda, Date fecha) {
        SimpleDateFormat dmyFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dmy = dmyFormat.format(fecha);
        
        return client
          .target(REST_URI)
                .path(moneda)
                .path("12-06-2019")
          .request(MediaType.APPLICATION_JSON_TYPE)
          .get(IndicadorEconomico.class);
    }
    
}
