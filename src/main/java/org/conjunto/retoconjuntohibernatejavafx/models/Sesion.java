package org.conjunto.retoconjuntohibernatejavafx.models;

/** Esta clase es el modelo de la sesión. Desde ella controlamos quién
 * se ha logeado en la app */

public class Sesion {
    private static Usuario usuarioActual;

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public static void setUsuarioActual(Usuario usuario) {
        usuarioActual = usuario;
    }

    public static String getNombreUsuario() {
        return usuarioActual.getNombre();
    }
}
