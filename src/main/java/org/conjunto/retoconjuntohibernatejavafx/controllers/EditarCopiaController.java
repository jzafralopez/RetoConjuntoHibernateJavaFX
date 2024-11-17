package org.conjunto.retoconjuntohibernatejavafx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import org.conjunto.retoconjuntohibernatejavafx.dao.CopiaDAO;
import org.conjunto.retoconjuntohibernatejavafx.models.Copia;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** Esta clase es el controlador de la vista de editar copia. */

public class EditarCopiaController implements Initializable {

    @FXML
    private ComboBox<String> estadoComboBox;
    @FXML
    private ComboBox<String> soporteComboBox;
    @FXML
    private TextField cantidadTextField;

    private Copia copia;
    @FXML
    private Button btnVolver;
    @FXML
    private Button btnGuardarCambios;

    public EditarCopiaController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        estadoComboBox.getItems().addAll("dañado", "bueno", "regular");
        soporteComboBox.getItems().addAll("DVD", "Blu-ray");

        if (copia != null) {
            estadoComboBox.setValue(copia.getEstado());
            soporteComboBox.setValue(copia.getSoporte());
            cantidadTextField.setText(String.valueOf(copia.getCantidad()));
        }
    }

    public void setCopia(Copia copia) {
        this.copia = copia;
    }

    @FXML
    public void guardarCambios() {
        try {
            int cantidad = Integer.parseInt(cantidadTextField.getText());

            copia.setEstado(estadoComboBox.getValue());
            copia.setSoporte(soporteComboBox.getValue());
            copia.setCantidad(cantidad);

            CopiaDAO copiaDAO = new CopiaDAO();
            copiaDAO.actualizarCopia(copia);

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Éxito");
            alert.setHeaderText("Los cambios se han guardado correctamente.");
            alert.showAndWait();

            Stage stage = (Stage) btnGuardarCambios.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/main-view.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Fallo al editar la copia.");
            alert.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void cancelarEdicion() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/main-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnVolver.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
