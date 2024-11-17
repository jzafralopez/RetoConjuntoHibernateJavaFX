package org.conjunto.retoconjuntohibernatejavafx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.conjunto.retoconjuntohibernatejavafx.dao.PeliDAO;
import org.conjunto.retoconjuntohibernatejavafx.models.Pelicula;

/** Esta clase es el controlador de la vista de agregar película. */

public class AgregarPeliculaController {

    private final PeliDAO peliDAO;
    @FXML
    private TextField tituloTextField;

    @FXML
    private TextField generoTextField;

    @FXML
    private TextField anhoTextField;

    @FXML
    private TextField descripcionTextField;

    @FXML
    private TextField directorTextField;

    @FXML
    private Button btnAgregarPelicula;

    @FXML
    private Button btnVolver;

    public AgregarPeliculaController() {
        this.peliDAO = new PeliDAO();
    }

    @FXML
    private void agregarPelicula() {
        String titulo = tituloTextField.getText();
        String genero = generoTextField.getText();
        String anhoTexto = anhoTextField.getText();
        String descripcion = descripcionTextField.getText();
        String director = directorTextField.getText();

        if (titulo.isEmpty() || genero == null || anhoTexto.isEmpty() || descripcion.isEmpty() || director.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Campos incompletos");
            alert.setContentText("Por favor, completa todos los campos.");
            alert.showAndWait();
            return;
        }

        int anho;
        try {
            anho = Integer.parseInt(anhoTexto);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Año no válido");
            alert.setContentText("Por favor, ingresa un número válido para el año.");
            alert.showAndWait();
            return;
        }

        Pelicula nuevaPelicula = new Pelicula();
        nuevaPelicula.setTitulo(titulo);
        nuevaPelicula.setGenero(genero);
        nuevaPelicula.setAnho(anho);
        nuevaPelicula.setDescripcion(descripcion);
        nuevaPelicula.setDirector(director);

        boolean exito = peliDAO.save(nuevaPelicula);

        Alert alert;
        if (exito) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Película Agregada");
            alert.setContentText("La película ha sido agregada correctamente.");
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Ha ocurrido un error al agregar la película.");
        }
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
