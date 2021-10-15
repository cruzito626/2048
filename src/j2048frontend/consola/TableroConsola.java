package j2048frontend.consola;

import j2048backend.Tablero;
import j2048frontend.Cliente;

import java.util.Arrays;
import java.util.Scanner;

public class TableroConsola implements Cliente {

    private Scanner teclado;
    private Tablero tablero;

    public TableroConsola(Tablero tablero) {
        this.tablero = tablero;
        this.tablero.agregarCliente(this);
        this.teclado = new Scanner(System.in);
    }

    public void correr() {
        tablero.insertarNumeroDos();
        System.out.println(formatearTablero());
        switch (tablero.estado()) {
            case GANADO -> System.out.println("Ganaste!!!");
            case PERDIDO -> System.out.println("Perdiste!!!");
            case CONTINUAR -> jugar(leer());
        }
    }

    private String leer() {
        String[] permitidos = {"W", "A", "S", "D"};
        System.out.print("Ingrese W, A, S, D: ");
        String entrada = teclado.next().toUpperCase();
        if (Arrays.stream(permitidos).anyMatch(entrada::equals)) {
            return entrada;
        }
        return leer();
    }

    private void jugar(String direccion) {
        switch (direccion) {
            case "W" -> tablero.moverArriba();
            case "A" -> tablero.moverIzquierda();
            case "S" -> tablero.moverAbajo();
            case "D" -> tablero.moverDerecha();
        }
    }

    private StringBuilder formatearTablero() {
        String[][] tableroMatriz;
        StringBuilder tableroFormatedo;
        int[] digitosMayores;

        tableroMatriz = transformarAMatriz();
        digitosMayores = digitosMayores(tableroMatriz);
        tableroFormatedo = new StringBuilder();

        for (int i = 0; i < tableroMatriz.length; i++) {
            for (int j = 0; j < tableroMatriz[i].length; j++) {
                int digitosExtras = digitosMayores[j] - tableroMatriz[i][j].length();

                tableroFormatedo.append(" ".repeat(digitosExtras) +
                        tableroMatriz[i][j] +
                        (j < tableroMatriz[i].length - 1 ? " " : "\n"));
            }
        }
        return tableroFormatedo;
    }

    private String[][] transformarAMatriz() {
        String[][] matriz;
        String[] lineas;

        lineas = tablero.toString().split("\\|");
        matriz = new String[lineas.length][];
        for (int i = 0; i < lineas.length; i++) {
            matriz[i] = lineas[i].split("\\s");
        }
        return matriz;
    }

    private int[] digitosMayores(String[][] numeros) {
        int mayores[], digitos;
        mayores = new int[numeros[0].length];
        for (int i = 0; i < numeros.length; i++) {
            for (int j = 0; j < numeros[i].length; j++) {
                digitos = numeros[i][j].length();
                if (digitos > mayores[j])
                    mayores[j] = digitos;
            }
        }
        return mayores;
    }
}