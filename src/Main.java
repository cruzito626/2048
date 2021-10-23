import j2048backend.Juego2048;
import j2048backend.Tablero;
import j2048frontend.consola.TableroConsola;
import j2048frontend.gui.TableroGUI;

public class Main {
    public static void main(String args[]) {
        Juego2048 juego2048 = new Juego2048();
        Tablero tablero = juego2048.iniciar();

        TableroConsola consola = new TableroConsola(tablero);
        TableroGUI gui = new TableroGUI(tablero);

        gui.correr();
        consola.correr();
    }
}