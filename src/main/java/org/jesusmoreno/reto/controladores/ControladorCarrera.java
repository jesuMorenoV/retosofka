package org.jesusmoreno.reto.controladores;

import org.jesusmoreno.reto.objetos.Carrera;
import org.jesusmoreno.reto.objetos.Conductor;
import org.jesusmoreno.reto.objetos.Progreso;

import java.util.Map;

/**
 *
 * @author Jesus R Moreno
 */
public class ControladorCarrera {

    private static ControladorCarrera instancia = null;
    Carrera carrera = null;

    private ControladorCarrera() {
    }

    public static ControladorCarrera getInstancia() {
        if (instancia == null) {
            instancia = new ControladorCarrera();
        }
        return instancia;
    }

    public boolean crearCarrera(int numeroJugadores, int distancia) {
        if (numeroJugadores < 2) {
            return false;
        }

        if (distancia < 600) {
            return false;
        }

        carrera = new Carrera(numeroJugadores, distancia);
        return true;
    }

    public boolean agregarConductor(String nombreConductor) {
        if (nombreConductor == null || nombreConductor.trim().isEmpty()) {
            return false;
        }
        int numeroConductoresActual = carrera.getCarros().size();
        if (numeroConductoresActual == carrera.getNumeroConductores()) {
            return false;
        }
        carrera.getCarros().put(++numeroConductoresActual, new Progreso(new Conductor(nombreConductor)));
        return true;
    }

    public boolean elJuegoFinalizo() {
        if (carrera.getPodio().size() >= 3) {

            new ControladorHistoricoCarrera().agregarRegistroHistoriaCarreras(
                    carrera.getId(),
                    carrera.getPodio().get(0).getNombre(),
                    carrera.getPodio().get(1).getNombre(),
                    carrera.getPodio().get(2).getNombre());

            return true;
        }
        return false;
    }

    public void lanzarDados(int carril) {
        int resultado = ((int) ((Math.random() * 6) + 1));
        int progreso = resultado * 100;
        System.out.println("El resultado fue un " + resultado + ", el carro avanza " + progreso + " metros");
        carrera.getCarros().get(carril).aumentarProgreso(progreso);
        if (carrera.getCarros().get(carril).getDistanciaRecorrida() > carrera.getDistancia()) {
            carrera.getPodio().add(carrera.getCarros().get(carril).getJugador());
            carrera.getCarros().remove(carril);
        }
    }

    public void mostrarResultados() {

        if (!carrera.getPodio().isEmpty()) {
            System.out.println("Podio\n--------------------------------------------------------------");
            int i = 1;
            for (Conductor conductor : carrera.getPodio()) {
                System.out.println("Puesto " + i + ": " + conductor.getNombre());
                i++;
            }
        }

        System.out.println("\n--------------------------------------------------------------\n");
        for (Map.Entry<Integer, Progreso> entry : carrera.getCarros().entrySet()) {
            System.out.println(calcularTrayecto(entry.getValue()));
        }
        System.out.println("\n--------------------------------------------------------------\n");
    }

    public String calcularTrayecto(Progreso progreso) {
        String trayecto = "-> " + progreso.getJugador().getNombre();
        int top = (int) (progreso.getDistanciaRecorrida() / 100);
        for (int i = 0; i < top; i++) {
            trayecto = "-" + trayecto;
        }
        return trayecto;
    }

    public Carrera getJuego() {
        return carrera;
    }
}
