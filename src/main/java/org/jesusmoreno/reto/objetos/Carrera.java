package org.jesusmoreno.reto.objetos;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jesus R Moreno
 */
public class Carrera {

    private long id;
    private int numeroConductores;
    private int distancia;
    private Map<Integer, Progreso> carros;
    private List<Conductor> podio;

    public Carrera(int numeroConductores, int distancia) {
        this.id = System.currentTimeMillis();
        this.numeroConductores = numeroConductores;
        this.distancia = distancia;
        this.carros = new LinkedHashMap<>();
        this.podio = new LinkedList<>();

    }

    public int getNumeroConductores() {
        return numeroConductores;
    }

    public int getDistancia() {
        return distancia;
    }

    public Map<Integer, Progreso> getCarros() {
        return carros;
    }

    public List<Conductor> getPodio() {
        return podio;
    }

    public long getId() {
        return id;
    }
}
