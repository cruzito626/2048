package j2048backend;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TableroTest {
    private static Tablero tablero;

    @BeforeEach
    public void init() {
        tablero = new Tablero();
    }

    @Test
    public void pruebaInsertarNumeroDosEnTableroVacio() {
        Assertions.assertTrue(tablero.insertarNumeroDos());
    }

    @Test
    public void pruebaInsertarNumeroDosEnTableroMarcado() {
        tablero.insertarNumeroDos();
        Assertions.assertTrue(tablero.insertarNumeroDos());
    }

    @Test
    public void pruebaInsertarNumeroDosEnTableroLleno() {
        llenarTablero(tablero);
        Assertions.assertFalse(tablero.insertarNumeroDos());
    }

    @Test
    public void pruebaMoverIzquierdaEnTableroVacio() {
        tablero.moverIzquierda();
        Assertions.assertEquals("0 0 0 0|0 0 0 0|0 0 0 0|0 0 0 0", tablero.toString());
    }

    @Test
    public void pruebaMoverIzquierdaEnTableroRotandoMaximoVeces() {
        mover(tablero, Direccion.IZQUIERDA, 8);
        llenarTablero(tablero);
        Assertions.assertEquals(tablero.toString(), "16 8 4 2|16 8 4 2|16 8 4 2|16 8 4 2");
    }

    @Test
    public void pruebaMoverIzquierdaEnTableroRotandoMayorAMaximoVeces() {
        mover(tablero, Direccion.IZQUIERDA, 8);
        llenarTablero(tablero);
        tablero.moverIzquierda();
        Assertions.assertEquals(tablero.toString(), "16 8 4 2|16 8 4 2|16 8 4 2|16 8 4 2");
    }

    @Test
    public void pruebaMoverDerechaEnTableroVacio() {
        tablero.moverDerecha();
        Assertions.assertEquals("0 0 0 0|0 0 0 0|0 0 0 0|0 0 0 0", tablero.toString());
    }

    @Test
    public void pruebaMoverDerechaEnTableroRotandoMaximoVeces() {
        mover(tablero, Direccion.DERECHA, 8);
        llenarTablero(tablero);
        Assertions.assertEquals(tablero.toString(), "2 4 8 16|2 4 8 16|2 4 8 16|2 4 8 16");
    }

    @Test
    public void pruebaMoverDerechaEnTableroRotandoMayorAMaximoVeces() {
        mover(tablero, Direccion.DERECHA, 8);
        llenarTablero(tablero);
        tablero.moverDerecha();
        Assertions.assertEquals(tablero.toString(), "2 4 8 16|2 4 8 16|2 4 8 16|2 4 8 16");
    }

    @Test
    public void pruebaMoverArribaEnTableroVacio() {
        tablero.moverAbajo();
        Assertions.assertEquals("0 0 0 0|0 0 0 0|0 0 0 0|0 0 0 0", tablero.toString());
    }

    @Test
    public void pruebaMoverArribaEnTableroRotandoMaximoVeces() {
        mover(tablero, Direccion.ARRIBA, 8);
        llenarTablero(tablero);
        Assertions.assertEquals(tablero.toString(), "16 16 16 16|8 8 8 8|4 4 4 4|2 2 2 2");
    }

    @Test
    public void pruebaMoverArribaEnTableroRotandoMayorAMaximoVeces() {
        mover(tablero, Direccion.ARRIBA, 8);
        llenarTablero(tablero);
        tablero.moverArriba();
        Assertions.assertEquals(tablero.toString(), "16 16 16 16|8 8 8 8|4 4 4 4|2 2 2 2");
    }

    @Test
    public void pruebaMoverAbajoEnTableroVacio() {
        tablero.moverArriba();
        Assertions.assertEquals("0 0 0 0|0 0 0 0|0 0 0 0|0 0 0 0", tablero.toString());
    }

    @Test
    public void pruebaMoverAbajoEnTableroRotandoMaximoVeces() {
        mover(tablero, Direccion.ABAJO, 8);
        llenarTablero(tablero);
        Assertions.assertEquals(tablero.toString(), "2 2 2 2|4 4 4 4|8 8 8 8|16 16 16 16");
    }

    @Test
    public void pruebaMoverAbaJOEnTableroRotandoMayorAMaximoVeces() {
        mover(tablero, Direccion.ABAJO, 8);
        llenarTablero(tablero);
        tablero.moverAbajo();
        Assertions.assertEquals(tablero.toString(), "2 2 2 2|4 4 4 4|8 8 8 8|16 16 16 16");
    }

    @Test
    public void pruebaToStringEnTableroVacio() {
        Assertions.assertEquals("0 0 0 0|0 0 0 0|0 0 0 0|0 0 0 0", tablero.toString());
    }

    @Test
    public void pruebaToStringEnTableroLleno() {
        llenarTablero(tablero);
        Assertions.assertEquals("2 2 2 2|2 2 2 2|2 2 2 2|2 2 2 2", tablero.toString());
    }


    private void llenarTablero(Tablero tablero) {
        boolean insertado;
        do {
            insertado = tablero.insertarNumeroDos();
        } while (insertado);
    }

    private void mover(Tablero tablero, Direccion dir, int veces) {
        for (int i = 0; i < veces; i++) {
            llenarTablero(tablero);
            switch (dir) {
                case IZQUIERDA -> tablero.moverIzquierda();
                case DERECHA -> tablero.moverDerecha();
                case ARRIBA -> tablero.moverArriba();
                case ABAJO-> tablero.moverAbajo();
            }
        }
    }
}
