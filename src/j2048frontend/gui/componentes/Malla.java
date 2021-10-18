package j2048frontend.gui.componentes;

import j2048backend.Tablero;

import javax.swing.*;
import java.awt.*;

public class Grilla extends JPanel {
    private Tablero tablero;
    private JLabel[][] panel;
    private String[][] matriz;

    public Grilla(Tablero tablero) {
        this.tablero = tablero;
    }

    public void init() {
        transformarAMatriz2D();
        panel = new JLabel[matriz.length][matriz.length];
        formatearTablero();
        actualizar();
    }
    public void actualizar() {
        transformarAMatriz2D();
        for (int fila = 0; fila < matriz.length; fila++)
            for (int columna = 0; columna < matriz[fila].length; columna++)
                panel[fila][columna].setText(matriz[fila][columna]);
    }

    private void formatearTablero() {
        String casilla = "";
        for (int fila = 0; fila < matriz.length; fila++) {
            for (int columna = 0; columna < matriz[fila].length; columna++) {
//                casilla = matriz[fila][columna].equals("0") ? "" : matriz[fila][columna];
                panel[fila][columna] = new JLabel(matriz[fila][columna], SwingConstants.CENTER);
                panel[fila][columna].setBorder(BorderFactory.createLineBorder(Color.BLUE));
            }
        }
    }

    private String[][] transformarAMatriz2D() {
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
}
