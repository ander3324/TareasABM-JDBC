/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ander
 */
public class Tarea {
    
    private int nro;
    private String descripcion;
    private Date fecha;
    private boolean finalizada;

    public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Override
    public String toString() {
        return nro + " - " + descripcion + " - " + new SimpleDateFormat("dd/MM/yyyy").format(fecha);
    }

    public String getFecha() {
        return new SimpleDateFormat("dd/MM/yyyy").format(fecha);
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String isFinalizada() {
        return finalizada ? "Sí" : "No";
    }

    public void setFinalizada(boolean finalizada) {
        this.finalizada = finalizada;
    }
}
