/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jdbc.ConnectionFactory;
import model.Tarea;

/**
 *
 * @author ander
 */
public class TareaRepo {

    private List<Tarea> tareas;
    private Tarea tarea;

    public TareaRepo() {
        tareas = new ArrayList<>();
        //cargarTareas();
    }

    public void addTarea(Tarea tarea) {
        tareas.add(tarea);
    }

    public void quitarTarea(Tarea tarea) {
        tareas.remove(tarea);
    }

    public void cargarTareas() {

        try {
            //Objetos de conexión:
            Connection cn = new ConnectionFactory().getConnection();

            //VAriable que almacena la sentencia...
            String sql = "select * from tareas";

            //Crear un obj PS
            PreparedStatement ps = cn.prepareStatement(sql);

            //Crear un obj RS
            ResultSet rs = ps.executeQuery();

            //Recorrer el RS y crear los objetos...
            while (rs.next()) {

                //Crear una nueva instancia de tarea...
                //Y setear sus valores...
                tarea = new Tarea();
                tarea.setNro(rs.getInt(1));
                tarea.setDescripcion(rs.getString(2));
                tarea.setFecha(rs.getDate(3));
                tarea.setFinalizada(rs.getBoolean(4));

                tareas.add(tarea);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public void cargarTareas(String descripcion) {

        //Limpiar lista:
        tareas.clear();
        try {
            //Objetos de conexión:
            Connection cn = new ConnectionFactory().getConnection();

            //VAriable que almacena la sentencia...
            String sql = "select * from tareas where descri like '%" + descripcion + "%';";

            //Crear un obj PS
            PreparedStatement ps = cn.prepareStatement(sql);

            //Crear un obj RS
            ResultSet rs = ps.executeQuery();

            //Recorrer el RS y crear los objetos...
            while (rs.next()) {

                //Crear una nueva instancia de tarea...
                //Y setear sus valores...
                tarea = new Tarea();
                tarea.setNro(rs.getInt(1));
                tarea.setDescripcion(rs.getString(2));
                tarea.setFecha(rs.getDate(3));
                tarea.setFinalizada(rs.getBoolean(4));

                tareas.add(tarea);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    /*
     * Métodos de ABM en la base de datos
     */
    public void insertar() {

    }

    public void editar() {

    }

    public void borrar(int id) {
        try {
            //Objetos de conexión:
            Connection cn = new ConnectionFactory().getConnection();

            //VAriable que almacena la sentencia delete...
            String sql = "delete from tareas where id =" + id;

            //Crear un obj PS
            PreparedStatement ps = cn.prepareStatement(sql);

            //Ejecutar el delete...
            ps.execute();
            
            tareas.clear();
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List<Tarea> getTareas() {
        cargarTareas();
        return tareas;
    }

    public List<Tarea> getTareas(String descripcion) {
        cargarTareas(descripcion);
        return tareas;
    }

    public void setTareas(List<Tarea> tareas) {
        this.tareas = tareas;
    }

    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

}
