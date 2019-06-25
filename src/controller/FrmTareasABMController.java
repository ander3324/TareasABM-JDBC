/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Tarea;
import repository.TareaRepo;

/**
 * FXML Controller class
 *
 * @author ander
 */
public class FrmTareasABMController implements Initializable {

    @FXML
    private Button btnAceptar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private DatePicker dtpFecha;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (tareastbv.TareasTBV.repo.getTarea() != null) {
            int dd, mm, aaaa;
            txtDescripcion.setText(tareastbv.TareasTBV.repo.getTarea().getDescripcion());

            //Desmenuzar la fecha: dd/mm/aaaa
            dd = Integer.parseInt(tareastbv.TareasTBV.repo.getTarea().getFecha().substring(0, 2));
            mm = Integer.parseInt(tareastbv.TareasTBV.repo.getTarea().getFecha().substring(3, 5));
            aaaa = Integer.parseInt(tareastbv.TareasTBV.repo.getTarea().getFecha().substring(6, 10));

            dtpFecha.setValue(LocalDate.of(aaaa, mm, dd));
        }
    }

    @FXML
    private void aceptar_OnAction(ActionEvent event) {
        if (txtDescripcion.getText().isEmpty()) {
            //Mostrar dialogo...
            //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Dialog.html
            //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Alert.html

            Alert alert = new Alert(Alert.AlertType.ERROR, "La descripción no puede estar vacía...");
            alert.showAndWait();
        } else {
            Tarea t;
            TareaRepo tr = tareastbv.TareasTBV.repo;

            Stage st = (Stage) btnAceptar.getScene().getWindow();
            if (st.getTitle().startsWith("Nueva")) {
                t = new Tarea();
                t.setNro(tr.getTareas().size() + 1);
                t.setDescripcion(txtDescripcion.getText());
                t.setFecha(Date.valueOf(dtpFecha.getValue()));
                tr.addTarea(t);
            } else {
                t = tr.getTarea();
                for (int i = 0; i < tr.getTareas().size(); i++) {
                    if (tr.getTareas().get(i).getNro() == t.getNro()) {
                        tr.getTareas().get(i).setDescripcion(txtDescripcion.getText());
                        tr.getTareas().get(i).setFecha(Date.valueOf(dtpFecha.getValue()));
                    }
                }
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Tarea guardada correctamente.");
            alert.showAndWait();

            cancelar_OnAction(null);
        }
    }

    @FXML
    private void cancelar_OnAction(ActionEvent event) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

}
