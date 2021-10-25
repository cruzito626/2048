package j2048backend;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TableroTest {
    private Tablero tablero;
    private String[][] tableroCadena;
    final String CERO = "0";

    @BeforeEach
    public void init() {
        tablero = Tablero.crear2048();
    }


    @Test
    public void pruebaCrearTableroNuevo() {
        transformarAMatriz2D();
        int ceros = contarCeros();

        Assertions.assertTrue(ceros == 15);
    }

    @Test
    public void pruebaMoverIzquierdaEnTableroNuevo() {
        tablero.moverDerecha();
        tablero.moverIzquierda();
        transformarAMatriz2D();
        int ceros = contarCeros();

        Assertions.assertTrue(ceros == 13 || ceros == 14);
    }

    @Test
    public void pruebaMoverDerechaEnTableroNuevo() {
        tablero.moverIzquierda();
        tablero.moverDerecha();
        transformarAMatriz2D();
        int ceros = contarCeros();

        Assertions.assertTrue(ceros == 13 || ceros == 14);
    }

    @Test
    public void pruebaMoverArribaEnTableroNuevo() {
        tablero.moverAbajo();
        tablero.moverArriba();
        transformarAMatriz2D();
        int ceros = contarCeros();

        Assertions.assertTrue(ceros == 13 || ceros == 14);
    }

    @Test
    public void pruebaMoverAbajoEnTableroNuevo() {
        tablero.moverArriba();
        tablero.moverAbajo();
        transformarAMatriz2D();
        int ceros = contarCeros();

        Assertions.assertTrue(ceros == 13 || ceros == 14);
    }

    @Test
    public void pruebaEstadoEnTableroNuevo() {
        tablero.moverDerecha();
        tablero.moverIzquierda();
        tablero.moverArriba();
        tablero.moverAbajo();
        Assertions.assertEquals(Estado.CONTINUAR, tablero.estado());
    }

    @Test
    public void pruebaEstadoEnTableroTerminado() {
        String tableroAnterior = tablero.toString();
        String tableroActual;
        while (true) {
            tablero.moverDerecha();
            tablero.moverIzquierda();
            tablero.moverArriba();
            tablero.moverAbajo();
            tableroActual = tablero.toString();
            if (tableroAnterior.equals(tableroActual)) {
                break;
            }
            tableroAnterior = tableroActual;
        }

        boolean terminado = Estado.GANADO == tablero.estado() || Estado.PERDIDO == tablero.estado();
        Assertions.assertTrue(terminado);
    }

    private int contarCeros() {
        int contador = 0;

        for (int fila = 0; fila < tableroCadena.length; fila++) {
            for (int columna = 0; columna < tableroCadena[fila].length; columna++) {
                if (CERO.equals(tableroCadena[fila][columna])) {
                    contador++;
                }
            }
        }
        return contador;
    }

    private void transformarAMatriz2D() {
        String[][] matriz;
        String[] lineas;
        int tamanio;

        lineas = tablero.toString().split("\\|");
        tamanio = lineas.length;
        matriz = new String[tamanio][tamanio];
        for (int i = 0; i < tamanio; i++) {
            matriz[i] = lineas[i].split("\\s");
        }
        tableroCadena = matriz;
    }
}
