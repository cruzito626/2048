package j2048frontend.gui.componentes;

import j2048backend.Tablero;
import j2048frontend.gui.Direccion;
import j2048frontend.gui.eventos.MovimientoEvento;

import javax.swing.*;

public class BotonMovimiento extends JButton {
    private Direccion direccion;
    private Tablero tablero;

    public BotonMovimiento(Tablero tablero, Direccion direccion) {
        this.tablero = tablero;
        this.direccion = direccion;
        addActionListener(new MovimientoEvento(tablero, direccion));
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
