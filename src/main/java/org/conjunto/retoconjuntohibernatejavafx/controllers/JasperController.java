package org.conjunto.retoconjuntohibernatejavafx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.conjunto.retoconjuntohibernatejavafx.reports.ReportService;

import java.net.URL;
import java.util.ResourceBundle;

/** Esta clase es el controlador para el informe **/

public class JasperController implements Initializable {

    @FXML
    private Button informePeliculasDisponibles;
    @FXML
    private Button informePeliculasMalEstado;
    @FXML
    private Button informePeliculasMasDeUnaCopia;
    @FXML
    private Button informePeliculaConcreta;

    @FXML
    public void mostrarInformePeliculasDisponibles(ActionEvent actionEvent) {
        ReportService.generarInforme();
    }

    @FXML
    public void mostrarInformePeliculasMalEstado(ActionEvent actionEvent) {
        ReportService.generarInformePelisMalEstado();
    }

    @FXML
    public void mostrarInformePeliculasMasDeUnaCopia(ActionEvent actionEvent) {
        ReportService.generarInformePelisMasDeUnaCopia();
    }

    @FXML
    public void mostrarInformePeliculaConcreta(ActionEvent actionEvent) {
        ReportService.generarInformeCopiaConcreta();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
