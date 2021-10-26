package j2048frontend.gui.componentes;

import j2048backend.Tablero;
import j2048frontend.Helper;

import javax.swing.*;
import java.awt.*;

public class TableroGraficoGUI extends JPanel {
    private Tablero tablero;
    private JLabel[][] casillas;
    private String[][] matriz;

    public TableroGraficoGUI(Tablero tablero) {
        this.tablero = tablero;
    }

    public void init() {
        matriz = Helper.strAMatriz2D(tablero.toString(), "\\|", "\\s");
        casillas = new JLabel[matriz.length][matriz.length];
        setLayout(new GridLayout(matriz.length, matriz.length));
        formatearTablero();
        agregarEtiquetas();
    }

    public void actualizar() {
        matriz = Helper.strAMatriz2D(tablero.toString(), "\\|", "\\s");
        formatearTablero();
        for (int fila = 0; fila < matriz.length; fila++)
            for (int columna = 0; columna < matriz[fila].length; columna++)
                casillas[fila][columna].setText(matriz[fila][columna]);
    }

    private void agregarEtiquetas() {
        for (int fila = 0; fila < matriz.length; fila++) {
            for (int columna = 0; columna < matriz[fila].length; columna++) {
                casillas[fila][columna] = new JLabel(matriz[fila][columna], SwingConstants.CENTER);
                casillas[fila][columna].setBorder(BorderFactory.createLineBorder(Color.BLUE));
                add(casillas[fila][columna]);
            }
        }
    }

    private void formatearTablero() {
        for (int fila = 0; fila < matriz.length; fila++) {
            for (int columna = 0; columna < matriz[fila].length; columna++) {
                matriz[fila][columna] =  matriz[fila][columna].equals("0") ? "" : matriz[fila][columna];
            }
        }
    }

}
