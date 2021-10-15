package j2048frontend.consola;

import j2048backend.Tablero;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TableroConsola {

    private Scanner teclado;
    private Tablero tablero;

    public TableroConsola(Tablero tablero) {
        this.tablero = tablero;
        this.teclado = new Scanner(System.in);
    }

    private void init() {
        tablero.insertarNumeroDos();
    }

    public void actualizar() {
        limpliarConsola();
        mostrar(formatearTablero());
    }


    public void correr() {
        boolean ciclo;
        ciclo = true;
        init();

        while (ciclo) {
            actualizar();
            switch (tablero.estado()) {
                case GANADO -> {
                    mostrar("Ganaste!!!");
                    ciclo = false;
                }
                case PERDIDO -> {
                    mostrar("Perdiste!!!");
                    ciclo = false;
                }
                case CONTINUAR -> jugar(leer());
            }
        }
    }

    private void mostrar(String str) {
        if (str == null) {
            mostrar();
        } else {
            System.out.println(str);
        }
    }

    private void mostrar() {
        System.out.println("");
    }

    private void limpliarConsola() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private String leer() {
        String entrada;
        String[] permitidos;

        permitidos = new String[]{"W", "A", "S", "D"};
        mostrar("Ingrese W, A, S, D: ");
        do {
            entrada = teclado.next().toUpperCase();
        } while (!Arrays.stream(permitidos).anyMatch(entrada::equals));

        return entrada;
    }

    private void jugar(String direccion) {
        switch (direccion) {
            case "W" -> tablero.moverArriba();
            case "A" -> tablero.moverIzquierda();
            case "S" -> tablero.moverAbajo();
            case "D" -> tablero.moverDerecha();
        }
    }

    private String formatearTablero() {
        String[][] tableroMatriz;
        StringBuilder tableroFormatedo;
        int tamanioNumeroMasLargo;

        tableroMatriz = transformarAMatriz2D();
        tamanioNumeroMasLargo = tamanioNumeroMasLargo();
        tableroFormatedo = new StringBuilder();

        for (int i = 0; i < tableroMatriz.length; i++) {
            for (int j = 0; j < tableroMatriz[i].length; j++) {
                int digitosExtras = tamanioNumeroMasLargo - tableroMatriz[i][j].length();

                tableroFormatedo.append(" ".repeat(digitosExtras) +
                        tableroMatriz[i][j] +
                        (j < tableroMatriz[i].length - 1 ? " " : "\n"));
            }
        }
        return tableroFormatedo.toString();
    }

    private String[][] transformarAMatriz2D() {
        String[][] matriz;
        String[] lineas;
        int tamanio;

        lineas = tablero.toString().split("\\|");
        tamanio = lineas.length;
        matriz = new String[tamanio][tamanio];
        for (int i = 0; i < tamanio; i++) {
            matriz[i] = lineas[i].split("\\s");
        }
        return matriz;
    }

    private int tamanioNumeroMasLargo() {
        StringTokenizer numeros;
        String numero;
        int tamanioMasLaro, tamanio;

        numeros = new StringTokenizer(tablero.toString(), "\\| | \\s");
        tamanioMasLaro = 0;
        while (numeros.hasMoreTokens()) {
            numero = numeros.nextToken();
            tamanio = numero.length();
            if (tamanio > tamanioMasLaro)
                tamanioMasLaro = tamanio;
        }
        return tamanioMasLaro;
    }
}