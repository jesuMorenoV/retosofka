package org.jesusmoreno.reto.controladores;

import java.io.*;

/**
 *
 * @author Jesus R Moreno
 */
public class ControladorHistoricoCarrera {

    public String obtenerHistorialCarrera() {

        String contenido = "";
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            archivo = new File("historial.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            String linea;
            while ((linea = br.readLine()) != null) {
                contenido = contenido + linea + "\n";
            }

        } catch (Exception e) {
            System.out.println("Aun no existen registros en el historico.");
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return contenido;
    }

    public void agregarRegistroHistoriaCarreras(long idCarrera, String corredor1, String corredor2, String corredor3) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("historial.txt", true);
            pw = new PrintWriter(fichero);
            pw.println("ID de Carrera: " + idCarrera + "\t" + "--- Primer Puesto: " + corredor1 + "\t" + "--- Segundo Puesto: " + corredor2 + "\t" + "--- Tercer Puesto: " + corredor3);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

}
