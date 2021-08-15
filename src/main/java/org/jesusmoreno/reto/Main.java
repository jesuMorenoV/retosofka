package org.jesusmoreno.reto;

import org.jesusmoreno.reto.controladores.ControladorCarrera;
import org.jesusmoreno.reto.controladores.ControladorHistoricoCarrera;
import org.jesusmoreno.reto.objetos.Progreso;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Jesus R Moreno
 */
public class Main {

    public static int obtenerNumeroCarros() {
        int numeroCarros = 0;
        boolean numCarrosFueIngresado = false;
        while (numCarrosFueIngresado == false) {
            try {
                Scanner scanner = new Scanner(System.in);
                numeroCarros = scanner.nextInt();
                numCarrosFueIngresado = true;
            } catch (InputMismatchException ex) {
                System.out.println("Ups!.. no entiendo ese valor, por favor digita el número de carros como un valor entero.");
            }
        }
        return numeroCarros;
    }

    public static int obtenerDistancia() {
        boolean distanciaValida = false;
        int distancia = 0;
        while (distanciaValida == false) {
            try {
                Scanner scanner = new Scanner(System.in);
                distancia = scanner.nextInt();
                distanciaValida = true;
            } catch (InputMismatchException ex) {
                System.out.println("Ups!.. no entiendo ese valor por favor digita la distancia como un valor entero mayor a 600.");
            }
        }
        return distancia;
    }

    public static void obtenerNmbresCoductores(int numeroCarros) {
        for (int i = 1; i <= numeroCarros; i++) {
            System.out.println("Ingrese en nombre del conductor de carro " + i);
            Scanner scanner = new Scanner(System.in);
            String nombre = scanner.next();
            ControladorCarrera.getInstancia().agregarConductor(nombre);
        }
    }

    public static void iniciarCarrera(int numeroCarros) {
        boolean juegoFinalizado = false;
        while (juegoFinalizado == false) {
            juegoFinalizado = ControladorCarrera.getInstancia().elJuegoFinalizo();
            for (int i = 1; i <= numeroCarros; i++) {
                if (ControladorCarrera.getInstancia().getJuego().getCarros().containsKey(i) && juegoFinalizado == false) {
                    Progreso progreso = ControladorCarrera.getInstancia().getJuego().getCarros().get(i);
                    System.out.println("Digita algo para lanzar los dados por el conductor " + progreso.getJugador().getNombre());
                    Scanner scanner = new Scanner(System.in);
                    String tecla = scanner.next();
                    ControladorCarrera.getInstancia().lanzarDados(i);
                    ControladorCarrera.getInstancia().mostrarResultados();
                }

            }
        }
    }

    public static void main(String args[]) {

        boolean continuarJugando = true;
        while (continuarJugando) {
            System.out.println("Hola, bienvenido a la carrera...");
            boolean opcionValida = false;
            while (!opcionValida) {
                System.out.println("¿Que quieres hacer? Ingresa el número correspondiente a la acción:\n\n1) Jugar.\n2) Ver historico.\n3) Salir.");
                Scanner scanner = new Scanner(System.in);
                String opcion = scanner.next();
                if ("1".equals(opcion)) {
                    opcionValida = true;
                } else if ("2".equals(opcion)) {
                    System.out.println("Este es el historico de juegos: \n");
                    String historico = new ControladorHistoricoCarrera().obtenerHistorialCarrera();
                    if (historico != null && !historico.trim().isEmpty()) {
                        System.out.println(historico);
                    } else {
                        System.out.println("Aun no hay datos en el historico.");
                    }

                } else if ("3".equals(opcion)) {
                    System.out.println("Muchas gracias por jugar, adios.");
                    continuarJugando = false;
                    System.exit(0);
                } else {
                    System.out.println("La opcion ingresada no es válida, intentalo nuevamente.");
                }
            }

            System.out.println("Para comenzar dime cuantos carros serán.");

            int numeroCarros = obtenerNumeroCarros();

            System.out.println("Tengo el número de carros, ahora necesito la distancia de la pista, recuerda que debe ser mayor a 600m.");

            int distancia = obtenerDistancia();

            ControladorCarrera.getInstancia().crearCarrera(numeroCarros, distancia);

            System.out.println("Excelente, ahora necesito los nombres de los conductores.");

            obtenerNmbresCoductores(numeroCarros);

            System.out.println("Tengo los nombres de los conductores... Lancemos los dados!");

            iniciarCarrera(numeroCarros);

        }

    }
}
