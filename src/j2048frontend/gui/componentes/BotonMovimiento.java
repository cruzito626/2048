package j2048frontend.gui.componentes;

import j2048backend.Tablero;
import j2048frontend.gui.Direccion;

import javax.swing.*;
import java.awt.*;

public class BotonMovimiento extends JButton {
    private Direccion direccion;
    private Tablero tablero;

    public BotonMovimiento(Tablero tablero, Direccion direccion) {
        this.tablero = tablero;
        this.direccion = direccion;
        addActionListener(new Movimiento(tablero, direccion));
        definirIcono();
    }

    private void definirIcono() {
        String text;
        text = switch (direccion) {

            case IZQUIERDA -> "(←)";
            case ARRIBA -> "(↑)";
            case DERECHA -> "(→)";
            case ABAJO -> "(↓)";
        };
        setText(text);
    }
}
