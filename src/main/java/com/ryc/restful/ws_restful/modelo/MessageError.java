package com.ryc.restful.ws_restful.modelo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author RyC
 */
@XmlRootElement
public class MessageError {
    
    private String mensaje;
    private int codigo;
    private String detalle;

    public MessageError() {
    }

    public MessageError(String mensaje, int codigo, String detalle) {
        this.mensaje = mensaje;
        this.codigo = codigo;
        this.detalle = detalle;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
    
    
    
}
