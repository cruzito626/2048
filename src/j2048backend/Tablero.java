package j2048backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import j2048frontend.Observador;

public class Tablero {
    private final int LIMITE = 16;
    private final int TAMANIO = 4;
    private final int DOS = 2;
    private final int CERO = 0;
    private int[][] tablero;
    private List<Observador> observadores;

    public Tablero() {
        tablero = new int[TAMANIO][TAMANIO];
        observadores = new ArrayList<>();
    }

    public boolean insertarNumeroDos() {
        if (!estaLleno()) {
            int fila, columna;
            do {
                fila = (int) (Math.random() * TAMANIO);
                columna = (int) (Math.random() * TAMANIO);
            } while (!esCasillaVacia(fila, columna));
            tablero[fila][columna] = DOS;
            return true;
        }
        return false;
    }

    public void moverIzquierda() {
        mover(Direccion.IZQUIERDA);
    }

    public void moverArriba() {
        mover(Direccion.ARRIBA);
    }

    public void moverDerecha() {
        mover(Direccion.DERECHA);
    }

    public void moverAbajo() {
        mover(Direccion.ABAJO);
    }

    @Override
    public String toString() {
        StringBuilder enCadena = new StringBuilder();
        for (int i = 0; i < TAMANIO; i++) {
            for (int j = 0; j < TAMANIO; j++) {
                enCadena.append(tablero[i][j]);
                enCadena.append(j < TAMANIO - 1 ? " " : (i < TAMANIO - 1 ? "|" : ""));
            }
        }
        return enCadena.toString();
    }

    public Estado estado() {
        return alcanceLimite() ?
                Estado.GANADO :
                (!sePuedeMover() ?
                    Estado.PERDIDO :
                    Estado.CONTINUAR);
    }

    public void agregarObservador(Observador observador) {
        observadores.add(observador);
    }

    public void notificar() {
        Estado estado = estado();
        observadores.forEach(observador -> observador.actualizar(estado));
    }

    private void mover(Direccion dir) {
        int sentido;
        int[] temp, compreso;
        boolean esHorizontal, movido, comprimido;

        movido = false;
        esHorizontal = dir.esHorizontal();
        sentido = dir.getSentido();

        for (int i = 0; i < TAMANIO; i++) {
            temp = esHorizontal ? obtenerFila(i) : obtenerColumna(i);
            compreso = comprimir(temp, sentido);
            comprimido = !Arrays.equals(temp, compreso);

            if (comprimido) {
                if (esHorizontal) {
                    cambiarFila(i, compreso);
                } else {
                    cambiarColumna(i, compreso);
                }
            }
            movido = movido || comprimido;
        }

        if (movido) {
            insertarNumeroDos();
            notificar();
        }
    }

    private boolean esCasillaVacia(int fila, int columna) {
        return tablero[fila][columna] == CERO;
    }

    private boolean estaLleno() {
        for (int fila = 0; fila < TAMANIO; fila++) {
            for (int columna = 0; columna < TAMANIO; columna++) {
                if (esCasillaVacia(fila, columna)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean sePuedeMover() {
        if (estaLleno()) {
            int ultimo = TAMANIO - 1;
            for (int fila = 0; fila < ultimo; fila++) {
                for (int columna = 0; columna < ultimo; columna++) {
                    if (tablero[fila][columna] == tablero[fila + 1][columna] ||
                            tablero[fila][columna] == tablero[fila][columna + 1]) {
                        return true;
                    }
                }
            }

            for (int columna = 0; columna < ultimo; columna++) {
                if (tablero[ultimo][columna] == tablero[ultimo][columna + 1]) {
                    return true;
                }
            }

            for (int fila = 0; fila < ultimo; fila++) {
                if (tablero[fila][ultimo] == tablero[fila + 1][ultimo]) {
                    return true;
                }
            }
        } else {
            return true;
        }
        return false;
    }

    private boolean alcanceLimite() {
        for (int fila = 0; fila < TAMANIO; fila++) {
            for (int columna = 0; columna < TAMANIO; columna++) {
                if (tablero[fila][columna] == LIMITE) {
                    return true;
                }
            }
        }
        return false;
    }

    private int[] comprimir(int[] arr, int sentido) {
        int inicio, pos;
        int[] temp;

        inicio = sentido > CERO ? 0 : TAMANIO - 1;
        temp = new int[TAMANIO];
        pos = inicio;

        for (int j = inicio; j >= CERO && j < TAMANIO; j += sentido) {
            if (arr[j] != CERO) {
                if (temp[pos] == CERO) {
                    temp[pos] = arr[j];
                } else {
                    if (arr[j] == temp[pos]) {
                        temp[pos] = temp[pos] * DOS;
                    } else {
                        temp[pos + sentido] = arr[j];
                    }
                    pos += sentido;
                }
            }
        }
        return temp;
    }

    private int[] obtenerFila(int posicion) {
        return tablero[posicion];
    }

    private int[] obtenerColumna(int posicion) {
        int[] columna = new int[TAMANIO];
        for (int fila = 0; fila < TAMANIO; fila++) {
            columna[fila] = tablero[fila][posicion];
        }
        return columna;
    }

    private void cambiarFila(int posicion, int[] nuevaFila) {
        tablero[posicion] = nuevaFila;
    }

    private void cambiarColumna(int posicion, int[] nuevaColumna) {
        for (int fila = 0; fila < TAMANIO; fila++) {
            tablero[fila][posicion] = nuevaColumna[fila];
        }
    }
}
