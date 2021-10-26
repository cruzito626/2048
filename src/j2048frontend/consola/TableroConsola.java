package j2048frontend.consola;

import j2048backend.Estado;
import j2048backend.Tablero;
import j2048frontend.Observador;
import j2048frontend.TableroUI;
import j2048frontend.Helper;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TableroConsola  implements TableroUI, Observador {

    private Scanner teclado;
    private Tablero tablero;
    private boolean continuar;

    public TableroConsola(Tablero tablero) {
        this.tablero = tablero;
        tablero.agregarObservador(this);
        this.teclado = new Scanner(System.in);
        this.continuar = true;
    }

    @Override
    public void actualizar(Estado estado) {
        limpliarConsola();
        mostrar(formatearTablero());
        switch (estado) {
            case GANADO, PERDIDO -> {
                mostrar(estado.name());
                continuar = false;
            }
            case CONTINUAR -> mostrar("Ingrese W, A, S, D: ");
        }
    }

    @Override
    public void correr() {
        String moverA;
        while (continuar) {
            Estado estado = tablero.estado();
            actualizar(estado);
            moverA = leerComando();
            jugar(moverA);
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

    private String leerComando() {
        String entrada;
        String[] permitidos;

        entrada = "";
        permitidos = new String[]{"W", "A", "S", "D"};
        do {
            try {
                entrada = teclado.next().toUpperCase();
            } catch (InputMismatchException ime) {
                break;
            }
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
        String[][] matriz;
        StringBuilder tableroFormatedo;
        int tamanioNumeroMasLargo;

        matriz = Helper.strAMatriz2D(tablero.toString(), "\\|", "\\s");
        tamanioNumeroMasLargo = tamanioNumeroMasLargo();
        tableroFormatedo = new StringBuilder();

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                int digitosExtras = tamanioNumeroMasLargo - matriz[i][j].length();

                tableroFormatedo.append(" ".repeat(digitosExtras) +
                        matriz[i][j] +
                        (j < matriz[i].length - 1 ? " " : "\n"));
            }
        }
        return tableroFormatedo.toString();
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