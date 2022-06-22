/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ryc.restful.ws_restful.modelo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author RyC
 */
public class IndicadorEconomico implements Serializable{
    private String autor;
    private String codigo;
    private String nombre;
    private String unidad_medida;
    private Serie serie;

    public IndicadorEconomico() {
    }    

    public IndicadorEconomico(String autor, String codigo, String nombre, String unidad_medida, Date fecha, Double valor) {
        this.autor = autor;
        this.codigo = codigo;
        this.nombre = nombre;
        this.unidad_medida = unidad_medida;
        this.serie.setFecha(fecha);
        this.serie.setValor(valor);
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUnidad_medida() {
        return unidad_medida;
    }

    public void setUnidad_medida(String unidad_medida) {
        this.unidad_medida = unidad_medida;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }
    
}
