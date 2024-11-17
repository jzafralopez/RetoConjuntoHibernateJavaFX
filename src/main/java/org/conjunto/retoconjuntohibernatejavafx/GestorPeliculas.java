package org.conjunto.retoconjuntohibernatejavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/** Clase principal de la aplicación, desde donde se ejecuta el main. */

public class GestorPeliculas extends Application {

            public static Stage stage;

            @Override
            public void start(Stage stage) {
                GestorPeliculas.stage = stage;
                loadFXML("/views/inicioSesion-view.fxml", "Inicio de sesión", 640, 480);
                stage.setResizable(false);
            }

            private static void loadFXML(String view, String title, int width, int height) {
                FXMLLoader fxmlLoader = new FXMLLoader(GestorPeliculas.class.getResource(view));
                Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load(), width, height);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage.setTitle(title);
                stage.setScene(scene);
                stage.show();
            }

            public static void main(String[] args) {
                launch();
            }
}