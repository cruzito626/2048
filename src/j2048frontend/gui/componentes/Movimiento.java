package j2048frontend.gui.componentes;

import j2048backend.Tablero;
import j2048frontend.gui.Direccion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Movimiento implements ActionListener {
    private Tablero tablero;
    private Direccion direccion;

    public Movimiento(Tablero tablero, Direccion direccion) {
        this.tablero = tablero;
        this.direccion = direccion;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (direccion){
            case IZQUIERDA -> tablero.moverIzquierda();
            case ARRIBA -> tablero.moverArriba();
            case DERECHA -> tablero.moverDerecha();
            case ABAJO -> tablero.moverAbajo();
        }
        System.out.println(tablero.toString());
    }
}
