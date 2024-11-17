package org.conjunto.retoconjuntohibernatejavafx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.conjunto.retoconjuntohibernatejavafx.dao.UserDAO;
import org.conjunto.retoconjuntohibernatejavafx.models.Sesion;
import org.conjunto.retoconjuntohibernatejavafx.models.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** Esta clase es el controlador de la vista de inicio de sesión. */

public class LoginController implements Initializable {

    @FXML
    private TextField nombreField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button btnIniciarSesion;
    @FXML
    private Button btnRegistrarse;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    public void login() throws IOException {
        String nombre = nombreField.getText();
        String password = passwordField.getText();

        Usuario usuario = UserDAO.validarUsuario(nombre, password);
        Sesion.setUsuarioActual(usuario);

        if (usuario != null) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Éxito en el inicio de sesión");
            alerta.setHeaderText("Éxito");
            alerta.setContentText("Bienvenido " + usuario.getNombre());
            alerta.showAndWait();

            FXMLLoader loaderInicio = new FXMLLoader(getClass().getResource("/views/main-view.fxml"));
            Parent root = loaderInicio.load();

            Stage stage = (Stage) btnRegistrarse.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Tus películas");
            stage.show();

        } else {
            Alert alertaError = new Alert(Alert.AlertType.ERROR);
            alertaError.setTitle("Error en el inicio de sesión");
            alertaError.setHeaderText("Error");
            alertaError.setContentText("Error al iniciar sesión");
            alertaError.showAndWait();
        }
    }


    public void registrarse() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/registrarse-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnRegistrarse.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

