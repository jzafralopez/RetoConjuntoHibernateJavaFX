package org.conjunto.retoconjuntohibernatejavafx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.conjunto.retoconjuntohibernatejavafx.dao.UserDAO;
import org.conjunto.retoconjuntohibernatejavafx.models.Usuario;
import java.net.URL;
import java.util.ResourceBundle;

/** Esta clase es el controlador de la vista de registro de usuario. */

public class RegistrarseController implements Initializable {

    @FXML
    private TextField registrarUsuarioField;
    @FXML
    private PasswordField registrarPassField;
    @FXML
    private CheckBox adminCheckBox;
    @FXML
    private Button btnAceptarRegistro;
    @FXML
    private Button btnCancelarRegistro;
    @FXML
    private PasswordField confirmarRegistroTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    public void registrar() {

        String nombre = registrarUsuarioField.getText();
        String contrasenha = registrarPassField.getText();
        String confirmarContrasenha = confirmarRegistroTextField.getText();
        boolean es_admin = adminCheckBox.isSelected();

        if (!nombre.isEmpty() && !contrasenha.isEmpty() && contrasenha.equals(confirmarContrasenha)) {
            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setPass(contrasenha);
            usuario.setEs_admin(es_admin);

            UserDAO userDAO = new UserDAO();
            userDAO.save(usuario);

            registrarUsuarioField.clear();
            registrarPassField.clear();
            adminCheckBox.setSelected(false);
        }
    }

    @FXML
    public void cancelar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/inicioSesion-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnCancelarRegistro.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
