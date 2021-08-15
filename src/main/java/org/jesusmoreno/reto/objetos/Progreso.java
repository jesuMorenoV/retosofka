package org.jesusmoreno.reto.objetos;

/**
 *
 * @author Jesus R Moreno
 */
public class Progreso {

    private Conductor conductor;
    private int distanciaRecorrida;

    public Progreso(Conductor conductor) {
        this.conductor = conductor;
        this.distanciaRecorrida = 0;
    }

    public boolean aumentarProgreso(int metros) {
        if (metros < 1) {
            return false;
        }
        distanciaRecorrida = distanciaRecorrida + metros;
        return true;
    }

    public int getDistanciaRecorrida() {
        return distanciaRecorrida;
    }

    public Conductor getJugador() {
        return conductor;
    }
}
