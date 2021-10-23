package j2048backend;

public class Juego2048 {
    public Tablero iniciar() {
        Tablero tablero = new Tablero();
        tablero.insertarNumeroDos();
        return tablero;
    }
}
