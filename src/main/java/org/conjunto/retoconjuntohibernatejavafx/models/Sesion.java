package org.conjunto.retoconjuntohibernatejavafx.models;

/** Esta clase es el modelo de la sesión. Desde ella controlamos quién
 * se ha logeado en la app */

public class Sesion {
    private static Usuario usuarioActual;
    private static Copia copiaActual;

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public static void setUsuarioActual(Usuario usuario) {
        usuarioActual = usuario;
    }

    public static Copia getCopiaActual() {
        return copiaActual;
    }

    public static String getNombreUsuario() {
        return usuarioActual.getNombre();
    }

    public static int getIdUsuario() {
        return usuarioActual.getId();
    }

    public static int getIdCopia() {
        return copiaActual.getId();
    }
}
