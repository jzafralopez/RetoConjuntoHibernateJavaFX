package org.conjunto.retoconjuntohibernatejavafx.reports;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.conjunto.retoconjuntohibernatejavafx.models.Sesion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

/** Esta clase se encarga de generar los informes en formato PDF de las películas disponibles, las películas en mal estado,
 * las películas con más de una copia y de una copia concreta. */

public class ReportService {

    private static Connection con = null;

    static {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/primerabd", "root", "NIPUTAIDEA32");
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            throw new RuntimeException("Error al conectar a la base de datos", e);
        }
    }

    public static void mostrarInforme() {
        try {
            JasperPrint jp = JasperFillManager.fillReport("informePelis.jasper", null, con);
            JasperViewer.viewReport(jp, false);
        } catch (JRException e) {
            System.err.println("Error al generar el informe: " + e.getMessage());
            throw new RuntimeException("Error al generar el informe", e);
        }
    }

    public static void generarInforme() {
        try {
            JasperPrint jp = JasperFillManager.fillReport("informePelis.jasper", null, con);
            JasperExportManager.exportReportToPdfFile(jp, "InformeDeTodasLasPeliculas" + ".pdf");
        } catch (JRException e) {
            System.err.println("Error al generar el informe PDF: " + e.getMessage());
            throw new RuntimeException("Error al generar el informe PDF", e);
        }
    }

    public static void generarInformePelisMalEstado() {
        try {
            JasperPrint jp = JasperFillManager.fillReport("informeCopiasDañadas.jasper", null, con);
            JasperExportManager.exportReportToPdfFile(jp, "InformePelisDañadas" + ".pdf");
        } catch (JRException e) {
            System.err.println("Error al generar el informe PDF: " + e.getMessage());
            throw new RuntimeException("Error al generar el informe PDF", e);
        }
    }

    public static void generarInformePelisMasDeUnaCopia() {
        try {
            JasperPrint jp = JasperFillManager.fillReport("informeMasDeUnaCopia.jasper", null, con);
            JasperExportManager.exportReportToPdfFile(jp, "InformePelisMasDeUnaCopia" + ".pdf");
        } catch (JRException e) {
            System.err.println("Error al generar el informe PDF: " + e.getMessage());
            throw new RuntimeException("Error al generar el informe PDF", e);
        }
    }

    public static void generarInformeCopiaConcreta() {

        var params = new HashMap<>();
        params.put("copia_seleccionada", Sesion.getIdCopia());
        params.put("usuario_activo", Sesion.getIdUsuario());
        try {
            JasperPrint jp = JasperFillManager.fillReport("informeCopia.jasper", null, con);
            JasperExportManager.exportReportToPdfFile(jp, "InformeCopiaConcreta" + ".pdf");
        } catch (JRException e) {
            System.err.println("Error al generar el informe PDF: " + e.getMessage());
            throw new RuntimeException("Error al generar el informe PDF", e);
        }
    }

}
