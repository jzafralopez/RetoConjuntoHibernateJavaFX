package org.conjunto.retoconjuntohibernatejavafx.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import org.conjunto.retoconjuntohibernatejavafx.dao.PeliDAO;
import org.conjunto.retoconjuntohibernatejavafx.dao.CopiaDAO;
import org.conjunto.retoconjuntohibernatejavafx.models.Copia;
import org.conjunto.retoconjuntohibernatejavafx.models.Pelicula;
import org.conjunto.retoconjuntohibernatejavafx.models.Sesion;
import org.conjunto.retoconjuntohibernatejavafx.models.Usuario;

import java.util.List;

/** Esta clase es el controlador de la vista de agregar copia. */

public class AgregarCopiaController {

    @FXML
    private ComboBox<String> peliculaComboBox;
    @FXML
    private ComboBox<String> estadoComboBox;
    @FXML
    private ComboBox<String> soporteComboBox;
    @FXML
    private TextField cantidadTextField;
    @FXML
    private Button btnAgregarCopia;
    @FXML
    private Button btnVolver;

    private CopiaDAO copiaDAO = new CopiaDAO();
    private List<Pelicula> peliculas;

    @FXML
    public void initialize() {
        estadoComboBox.getItems().addAll("dañado", "bueno", "regular");
        soporteComboBox.getItems().addAll("DVD", "Blu-ray");

        peliculas = new PeliDAO().findAll();

        ObservableList<String> titulosObservableList = FXCollections.observableArrayList();
        for (Pelicula pelicula : peliculas) {
            titulosObservableList.add(pelicula.getTitulo());
        }
        peliculaComboBox.setItems(titulosObservableList);
    }

    @FXML
    public void agregarCopia() {
        String tituloSeleccionado = peliculaComboBox.getValue();

        Pelicula peliculaSeleccionada = null;
        for (Pelicula pelicula : peliculas) {
            if (pelicula.getTitulo().equals(tituloSeleccionado)) {
                peliculaSeleccionada = pelicula;
                break;
            }
        }

        String estado = estadoComboBox.getValue();
        String soporte = soporteComboBox.getValue();
        int cantidad;

        try {
            cantidad = Integer.parseInt(cantidadTextField.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Cantidad no válida");
            alert.setContentText("Por favor, ingresa un número válido para la cantidad.");
            alert.showAndWait();
            return;
        }

        if (peliculaSeleccionada == null || estado == null || soporte == null || cantidad <= 0) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Campos incompletos");
            alert.setContentText("Todos los campos deben estar completos.");
            alert.showAndWait();
            return;
        }

        Usuario logueado = Sesion.getUsuarioActual();

        Copia copia = new Copia();
        copia.setPelicula(peliculaSeleccionada);
        copia.setEstado(estado);
        copia.setSoporte(soporte);
        copia.setCantidad(cantidad);
        copia.setUsuario(logueado);

        copiaDAO.addCopia(copia);

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Copia Agregada");
        alert.setHeaderText(null);
        alert.setContentText("La copia se ha agregado con éxito.");
        alert.showAndWait();
    }

    @FXML
    public void volver() {
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
