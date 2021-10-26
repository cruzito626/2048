package j2048frontend.gui;

import j2048backend.Estado;
import j2048backend.Tablero;
import j2048frontend.Observador;
import j2048frontend.TableroUI;
import j2048frontend.gui.componentes.Boton;
import j2048frontend.gui.componentes.TableroGraficoGUI;
import j2048frontend.gui.componentes.Ventana;
import j2048frontend.gui.eventos.BotonEvento;

import javax.swing.*;
import java.awt.*;

public class TableroGUI extends JFrame implements TableroUI, Observador {
    private final String NOMBRE_VENTANA = "Juego 2048";

    private Tablero tablero;
    private Ventana ventana;
    private Boton boton;
    private JButton botonIzquierda;
    private JButton botonDerecha;
    private JButton botonArriba;
    private JButton botonAbajo;
    private JButton botonTerminar;
    private JPanel panelVentana;
    private JPanel panelIzquierda;
    private JPanel panelDerecha;
    private JPanel panelArriba;
    private JPanel panelAbajo;
    private TableroGraficoGUI panelTablero;
    private JLabel etiquetaMensaje;

    public TableroGUI(Tablero tablero) {
        this.tablero = tablero;
        tablero.agregarObservador(this);
        ventana = new Ventana(NOMBRE_VENTANA);
        boton = new Boton();
    }

    private void init() {
        ventana.init();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contenedor = ventana.getContentPane();
        panelVentana = new JPanel();
        panelVentana.setLayout(new BorderLayout());

        definirTablero();
        definirIzquierda();
        definirDerecha();
        definirArriba();
        definirAbajo();

        panelVentana.add(panelTablero, BorderLayout.CENTER);
        panelVentana.add(panelIzquierda, BorderLayout.WEST);
        panelVentana.add(panelDerecha, BorderLayout.EAST);
        panelVentana.add(panelArriba, BorderLayout.NORTH);
        panelVentana.add(panelAbajo, BorderLayout.SOUTH);

        contenedor.add(panelVentana);

    }

    @Override
    public void correr() {
        init();
        ventana.setVisible(true);
    }

    @Override
    public void actualizar(Estado estado) {
        switch (estado) {
            case GANADO, PERDIDO -> {
                actulizarMensaje(estado.name());
                desactivarBotones();
            }
            case CONTINUAR -> panelTablero.actualizar();
        }

    }

    private void definirTablero() {
        panelTablero = new TableroGraficoGUI(tablero);
        panelTablero.init();
    }

    private void definirIzquierda() {
        panelIzquierda = new JPanel();
        panelIzquierda.setLayout(new BoxLayout(panelIzquierda, BoxLayout.X_AXIS));
        botonIzquierda = boton.createBoton(Tipo.IZQUIERDA, new BotonEvento(tablero, Tipo.IZQUIERDA));
        panelIzquierda.add(botonIzquierda);
    }

    private void definirDerecha() {
        panelDerecha = new JPanel();
        panelDerecha.setLayout(new BoxLayout(panelDerecha, BoxLayout.X_AXIS));
        botonDerecha = boton.createBoton(Tipo.DERECHA, new BotonEvento(tablero, Tipo.DERECHA));
        panelDerecha.add(botonDerecha);
    }

    private void definirArriba() {
        panelArriba = new JPanel();
        botonArriba = boton.createBoton(Tipo.ARRIBA, new BotonEvento(tablero, Tipo.ARRIBA));
        etiquetaMensaje = new JLabel();

        panelArriba.add(botonArriba);
        panelArriba.add(etiquetaMensaje);
    }

    private void definirAbajo() {
        panelAbajo = new JPanel();
        botonAbajo = boton.createBoton(Tipo.ABAJO, new BotonEvento(tablero, Tipo.ABAJO));
        botonTerminar = boton.createBoton(Tipo.TERMINAR, new BotonEvento(Tipo.TERMINAR));
        panelAbajo.add(botonTerminar);
        panelAbajo.add(botonAbajo);
    }

    private void actulizarMensaje(String mensaje) {
        etiquetaMensaje.setText(mensaje);
    }

    private void desactivarBotones() {
        botonIzquierda.setEnabled(false);
        botonDerecha.setEnabled(false);
        botonArriba.setEnabled(false);
        botonAbajo.setEnabled(false);
        botonTerminar.setEnabled(false);
    }

}
