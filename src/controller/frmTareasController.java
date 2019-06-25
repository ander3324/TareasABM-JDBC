/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import model.Tarea;
import repository.TareaRepo;
import util.VentanasUtil;

/**
 *
 * @author ander
 */
public class frmTareasController implements Initializable {

    private Label label;

    @FXML
    private Button btnNueva;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnBorrar;
    @FXML
    private TableView<Tarea> tbvTareas;
    @FXML
    private TextField txfBuscar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarItems();
    }

    @FXML
    private void nueva_OnAction(ActionEvent event) {
        VentanasUtil.abrirDialogo(btnNueva.getScene().getWindow(), "/view/frmTareasABM.fxml", "Nueva Tarea");
        cargarItems();
    }

    void cargarItems() {
        tbvTareas.getItems().clear();
        tbvTareas.getItems().addAll(tareastbv.TareasTBV.repo.getTareas());
    }

    @FXML
    private void editar_OnAction(ActionEvent event) {
        Tarea t = new Tarea();
        t.setNro(tbvTareas.getSelectionModel().getSelectedItem().getNro());
        t.setDescripcion(tbvTareas.getSelectionModel().getSelectedItem().getDescripcion());

        //Desmenuzar la fecha: dd/mm/aaaa
        int dd, mm, aaaa;
        dd = Integer.parseInt(tbvTareas.getSelectionModel().getSelectedItem().getFecha().substring(0, 2));
        mm = Integer.parseInt(tbvTareas.getSelectionModel().getSelectedItem().getFecha().substring(3, 5));
        aaaa = Integer.parseInt(tbvTareas.getSelectionModel().getSelectedItem().getFecha().substring(6, 10));

        t.setFecha(Date.valueOf(LocalDate.of(aaaa, mm, dd)));

        tareastbv.TareasTBV.repo.setTarea(t);
        VentanasUtil.abrirDialogo(btnNueva.getScene().getWindow(), "/view/frmTareasABM.fxml", "Modificar Tarea");

        cargarItems();

    }

    @FXML
    private void borrar_OnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Estás seguro de borrar la tarea?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                //lstTareas.getItems().remove(lstTareas.getSelectionModel().getSelectedItem());
                tareastbv.TareasTBV.repo.quitarTarea(tbvTareas.getSelectionModel().getSelectedItem());
                cargarItems();
            }
        });
    }

    @FXML
    private void buscarOnKeyPressed(KeyEvent event) {
        tbvTareas.getItems().clear();
        tbvTareas.getItems().addAll(tareastbv.TareasTBV.repo.getTareas(txfBuscar.getText()));
    }

}
