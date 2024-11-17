package org.conjunto.retoconjuntohibernatejavafx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.conjunto.retoconjuntohibernatejavafx.dao.UserDAO;
import org.conjunto.retoconjuntohibernatejavafx.models.Sesion;
import org.conjunto.retoconjuntohibernatejavafx.models.Usuario;

/** Esta clase es el controlador de la vista de edición de usuario. */

public class EditarUsuarioController {

    @FXML
    private TextField txtNombreUsuario;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private CheckBox chkEsAdmin;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnCancelar;

    private Usuario usuarioActual;

    @FXML
    public void initialize() {
        usuarioActual = Sesion.getUsuarioActual();
        if (usuarioActual != null) {
            txtNombreUsuario.setText(usuarioActual.getNombre());
            txtPassword.setText(usuarioActual.getPass());
            chkEsAdmin.setSelected(usuarioActual.isEs_admin());
        } else {
            txtNombreUsuario.setText("");
            txtPassword.setText("");
            chkEsAdmin.setSelected(false);
        }
    }

    @FXML
    public void guardarUsuario() {
        if (usuarioActual != null) {
            String nuevoNombre = txtNombreUsuario.getText();
            String nuevaContrasenha = txtPassword.getText();
            boolean esAdmin = chkEsAdmin.isSelected();

            usuarioActual.setNombre(nuevoNombre);
            usuarioActual.setPass(nuevaContrasenha);
            usuarioActual.setEs_admin(esAdmin);

            UserDAO userDAO = new UserDAO();
            userDAO.update(usuarioActual);

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Usuario actualizado con éxito.");
            alert.showAndWait();
        }
    }

    @FXML
    public void cancelarEdicion() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/main-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnCancelar.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }    }
}
