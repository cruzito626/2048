package j2048frontend.gui;

import j2048backend.Tablero;
import j2048frontend.gui.componentes.BotonMovimiento;
import j2048frontend.gui.componentes.Malla;
import j2048frontend.gui.componentes.Ventana;

import javax.swing.*;
import java.awt.*;

public class TableroGUI extends JFrame {
  /*  private Tablero tablero;
    private BotonMovimiento izquierda;
    private BotonMovimiento derecha;
    private BotonMovimiento arriba;
    private BotonMovimiento abajo;
    private JPanel panelPrincipal;
    private JPanel panelTablero;
    private JLabel[][] tableroGUI;
    private boolean continuar;

    public TableroGUI(Tablero tablero) {
        this.tablero = tablero;
        this.continuar = true;
    }

    private void init() {
        setTitle("J2048");
        setSize(400, 400);
        setBounds(500, 300, 450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contenedor = this.getContentPane();
        izquierda = new BotonMovimiento(tablero, Direccion.IZQUIERDA);
        derecha = new BotonMovimiento(tablero, Direccion.DERECHA);
        arriba = new BotonMovimiento(tablero, Direccion.ARRIBA);
        abajo = new BotonMovimiento(tablero, Direccion.ABAJO);

        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(izquierda, BorderLayout.WEST);
        panelPrincipal.add(derecha, BorderLayout.EAST);
        panelPrincipal.add(arriba, BorderLayout.NORTH);
        // panelPrincipal.add(abajo, BorderLayout.SOUTH);

        JPanel panelBotones = new JPanel();
        panelBotones.add(izquierda);
        panelBotones.add(derecha);
        panelBotones.add(arriba);
        panelBotones.add(abajo);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        String[][] matriz = formatearTablero();
        panelTablero = new JPanel();
        panelTablero.setLayout(new GridLayout(matriz.length, matriz.length));

        tableroGUI = new JLabel[matriz.length][matriz.length];

        for (int fila = 0; fila < matriz.length; fila++) {
            for (int columna = 0; columna < matriz[fila].length; columna++) {
                tableroGUI[fila][columna] = new JLabel(matriz[fila][columna], SwingConstants.CENTER);
                tableroGUI[fila][columna].setBorder(BorderFactory.createLineBorder(Color.BLUE));
                panelTablero.add(tableroGUI[fila][columna]);
            }
        }
        panelPrincipal.add(panelTablero, BorderLayout.CENTER);
        contenedor.add(panelPrincipal);
        setVisible(true);
    }



    public void correr() {
        String[][] matriz = formatearTablero();
        tablero.insertarNumeroDos();
        init();
        while(continuar) {
            switch (tablero.estado()) {
                case GANADO -> {
                    JOptionPane.showMessageDialog(this, "Ganaste!!!");
                    continuar = false;
                }
                case PERDIDO -> {
                    JOptionPane.showMessageDialog(this, "Perdiste!!!");
                    continuar = false;
                }
                case CONTINUAR -> actualizar();
            }
        }
    }

    private String[][] formatearTablero() {
        String[][] res;
        String[] filas, columnas;

        filas = tablero.toString().split("\\|");
        res = new String[filas.length][filas.length];
        for (int i = 0; i < filas.length; i++) {
            columnas = filas[i].split("\\s");
            for (int j = 0; j < columnas.length; j++) {
                res[i][j] = columnas[j].equals("0") ? "" : columnas[j];
            }
        }
        return res;
    }
*/
    private final String NOMBRE_VENTANA = "Juego 2048";

    private Tablero tablero;
    private Ventana ventana;
    private JButton izquierda;
    private JButton derecha;
    private JButton arriba;
    private JButton abajo;
    private JPanel panelPrincipal;
    private Malla panelTablero;
    private boolean continuar;

    public TableroGUI(Tablero tablero) {
        this.tablero = tablero;
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

        panelTablero.init();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(izquierda, BorderLayout.WEST);
        panelPrincipal.add(derecha, BorderLayout.EAST);
        panelPrincipal.add(arriba, BorderLayout.NORTH);
        panelPrincipal.add(abajo, BorderLayout.SOUTH);
        panelPrincipal.add(panelTablero, BorderLayout.CENTER);

        contenedor.add(panelPrincipal);
        ventana.setVisible(true);
    }
    public void correr() {
        tablero.insertarNumeroDos();
        init();
        while(continuar) {
            switch (tablero.estado()) {
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
            panelTablero.actualizar();
        }
    }
}
