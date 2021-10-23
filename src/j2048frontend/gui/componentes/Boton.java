package j2048frontend.gui.componentes;
import j2048frontend.gui.Tipo;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Boton {

    public JButton createBoton(Tipo tipo, ActionListener actionListener) {
        JButton boton;
        boton = new JButton();
        definirIcono(boton, tipo);
        boton.addActionListener(actionListener);
        return boton;
    }

    private void definirIcono(JButton boton, Tipo tipo) {
        String text;
        text = switch (tipo) {
            case IZQUIERDA -> "(←)";
            case ARRIBA -> "(↑)";
            case DERECHA -> "(→)";
            case ABAJO -> "(↓)";
            case TERMINAR -> "Terminar";
        };
        boton.setText(text);
    }
}
