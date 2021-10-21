package j2048frontend.gui;

import j2048backend.Estado;
import j2048backend.Tablero;
import j2048frontend.Observador;
import j2048frontend.TableroUI;
import j2048frontend.gui.componentes.BotonMovimiento;
import j2048frontend.gui.componentes.Malla;
import j2048frontend.gui.componentes.Ventana;
import j2048frontend.gui.eventos.TerminarEvento;

import javax.swing.*;
import java.awt.*;

public class TableroGUI extends JFrame  implements TableroUI, Observador {
    private final String NOMBRE_VENTANA = "Juego 2048";

    private Tablero tablero;
    private Ventana ventana;
    private JButton izquierda;
    private JButton derecha;
    private JButton arriba;
    private JButton abajo;
    private JButton terminar;
    private JPanel panelPrincipal;
    private Malla panelTablero;
    private boolean continuar;

    public TableroGUI(Tablero tablero) {
        this.tablero = tablero;
        tablero.agregarObservador(this);
        this.continuar = true;
        ventana = new Ventana(NOMBRE_VENTANA);
    }

    private void init() {
        ventana.init();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contenedor = ventana.getContentPane();

        panelPrincipal = new JPanel();
        panelTablero = new Malla(tablero);
        izquierda = new BotonMovimiento(tablero, Direccion.IZQUIERDA);
        derecha = new BotonMovimiento(tablero, Direccion.DERECHA);
        arriba = new BotonMovimiento(tablero, Direccion.ARRIBA);
        abajo = new BotonMovimiento(tablero, Direccion.ABAJO);
        terminar = new JButton("Terminar");
        terminar.addActionListener(new TerminarEvento());
        panelTablero.init();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(izquierda, BorderLayout.WEST);
        panelPrincipal.add(derecha, BorderLayout.EAST);
        panelPrincipal.add(arriba, BorderLayout.NORTH);
        panelPrincipal.add(abajo, BorderLayout.SOUTH);
        panelPrincipal.add(terminar, BorderLayout.SOUTH);
        panelPrincipal.add(panelTablero, BorderLayout.CENTER);

        contenedor.add(panelPrincipal);

    }

    @Override
    public void correr() {
        init();
        ventana.setVisible(true);
    }

    @Override
    public void actualizar(Estado estado) {
        switch (estado) {
            case GANADO -> {
                JOptionPane.showMessageDialog(this, "Ganaste!!!");
                continuar = false;
            }
            case PERDIDO -> {
                JOptionPane.showMessageDialog(this, "Perdiste!!!");
                continuar = false;
            }
            case CONTINUAR -> panelTablero.actualizar();
        }
    }
}
