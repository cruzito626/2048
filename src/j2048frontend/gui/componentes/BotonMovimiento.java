package j2048frontend.gui;

import j2048backend.Tablero;

import javax.swing.*;

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
            case IZQUIERDA -> "Izq";
            case ARRIBA -> "Arr";
            case DERECHA -> "Der";
            case ABAJO -> "Aba";
        };
        setText(text);
    }
}
