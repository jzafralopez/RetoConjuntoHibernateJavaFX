package org.conjunto.retoconjuntohibernatejavafx.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.conjunto.retoconjuntohibernatejavafx.dao.CopiaDAO;
import org.conjunto.retoconjuntohibernatejavafx.models.Copia;
import org.conjunto.retoconjuntohibernatejavafx.models.Sesion;
import org.conjunto.retoconjuntohibernatejavafx.models.Usuario;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/** Esta clase es el controlador de la vista principal. */

public class MainController implements Initializable {

    @FXML
    private Button btnAddCopia;
    @FXML
    private Button btnEliminarCopia;
    @FXML
    private Button btnEditarCopia;
    @FXML
    private Button btnAddPelicula;
    @FXML
    private Button btnCerrarSesion;
    @FXML
    private Button btnInformes;
    @FXML
    private ImageView imgLogout;
    @FXML
    private TableView<Copia> peliculaTabla;
    @FXML
    private TableColumn<Copia, String> nombreColumn;
    @FXML
    private TableColumn<Copia, String> estadoColumn;
    @FXML
    private TableColumn<Copia, String> soporteColumn;
    @FXML
    private TableColumn<Copia, Integer> cantidadColumn;
    @FXML
    private ImageView backgroundImage;
    @FXML
    private ImageView userIcon;
    @FXML
    private Label lblNombreUsuario;

    public MainController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        configurarColumnas();

        Usuario usuarioActual = Sesion.getUsuarioActual();

        if (usuarioActual != null) {
            lblNombreUsuario.setText(usuarioActual.getNombre());
            List<Copia> copiasUsuario = new CopiaDAO().findCopiasByUsuario(usuarioActual.getId());
            ObservableList<Copia> copiasObservableList = FXCollections.observableArrayList(copiasUsuario);
            peliculaTabla.setItems(copiasObservableList);
        }
    }

    private void configurarColumnas() {
        nombreColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getPelicula().getTitulo()));
        estadoColumn.setCellValueFactory(new PropertyValueFactory<>("estado"));
        soporteColumn.setCellValueFactory(new PropertyValueFactory<>("soporte"));
        cantidadColumn.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
    }

    @FXML
    public void cerrarSesion() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/inicioSesion-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void agregarCopia() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/agregarCopia-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnAddCopia.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void editarCopia() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/editarCopia-view.fxml"));
            Parent root = loader.load();

            EditarCopiaController editarCopiaController = loader.getController();
            editarCopiaController.setCopia(peliculaTabla.getSelectionModel().getSelectedItem());

            Stage stage = (Stage) btnEditarCopia.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void borrarCopia() {
        Copia copia = peliculaTabla.getSelectionModel().getSelectedItem();
        if (copia != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar eliminación");
            alert.setHeaderText("¿Estás seguro de que deseas eliminar esta copia?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                new CopiaDAO().borrarCopia(copia);
                peliculaTabla.getItems().remove(copia);
            }
        }
    }

    @FXML
    public void addPelicula(){

        Usuario usuarioLogueado = Sesion.getUsuarioActual();

        if (usuarioLogueado.isEs_admin()){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/agregarPelicula-view.fxml"));
                Parent root = loader.load();

                Stage stage = (Stage) btnAddPelicula.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No tienes permisos para agregar películas");
            alert.setContentText("Solo los administradores pueden agregar películas.");
            alert.showAndWait();
        }
    }

    public void editarUsuario() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/editarUsuario-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) lblNombreUsuario.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void realizarInformes() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/informes-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnInformes.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
