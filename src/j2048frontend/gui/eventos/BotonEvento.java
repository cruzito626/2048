package j2048frontend.gui.eventos;

import j2048backend.Tablero;
import j2048frontend.gui.Tipo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotonEvento implements ActionListener {
    private Tablero tablero;
    private Tipo tipo;

    public BotonEvento(Tablero tablero, Tipo tipo) {
        this.tablero = tablero;
        this.tipo = tipo;
    }

    public BotonEvento(Tipo tipo) {
        this.tablero = null;
        this.tipo = tipo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (tipo){
            case IZQUIERDA -> tablero.moverIzquierda();
            case ARRIBA -> tablero.moverArriba();
            case DERECHA -> tablero.moverDerecha();
            case ABAJO -> tablero.moverAbajo();
        }
    }
}
