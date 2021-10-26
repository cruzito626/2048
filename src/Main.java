import j2048backend.Tablero;
import j2048frontend.consola.TableroConsola;
import j2048frontend.gui.TableroGUI;

public class Main {
    public static void main(String args[]) {
        Tablero tablero = Tablero.crear2048();
        TableroConsola consola = new TableroConsola(tablero);
       TableroGUI gui = new TableroGUI(tablero);

       gui.correr();
        consola.correr();
    }
}