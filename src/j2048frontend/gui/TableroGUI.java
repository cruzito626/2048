package j2048frontend.gui;

import j2048backend.Tablero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableroGUI extends JFrame implements ActionListener {

    private Tablero tablero;
    private JButton izquierda;
    private JButton derecha;
    private JButton arriba;
    private JButton abajo;
    private JPanel panelPrincipal;
    private JPanel panelTablero;
    private JLabel[][] tableroGUI;

    public TableroGUI(Tablero tablero) {
        this.tablero = tablero;
        init();
    }

    private void init() {
        setTitle("J2048");
        setSize(400, 400);
        setBounds(500, 300, 450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contenedor = this.getContentPane();
        izquierda = new JButton("izq");
        derecha = new JButton("der");
        arriba = new JButton("arr");
        abajo = new JButton("aba");

        izquierda.addActionListener(this);
        derecha.addActionListener(this);
        arriba.addActionListener(this);
        abajo.addActionListener(this);

        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(izquierda, BorderLayout.WEST);
        panelPrincipal.add(derecha, BorderLayout.EAST);
        panelPrincipal.add(arriba, BorderLayout.NORTH);
        panelPrincipal.add(abajo, BorderLayout.SOUTH);

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
    }

    public void correr() {
        String[][] matriz = formatearTablero();
        tablero.insertarNumeroDos();
        setVisible(true);
        switch (tablero.estado()) {
            case GANADO -> JOptionPane.showMessageDialog(this, "Ganaste!!!");
            case PERDIDO -> JOptionPane.showMessageDialog(this, "Perdiste!!!");
            case CONTINUAR -> {
                for (int fila = 0; fila < matriz.length; fila++)
                    for (int columna = 0; columna < matriz[fila].length; columna++)
                        tableroGUI[fila][columna].setText(matriz[fila][columna]);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        Object botonPulsado = e.getSource();
        if (botonPulsado == izquierda) {
            tablero.moverIzquierda();
        } else if (botonPulsado == derecha) {
            tablero.moverDerecha();
        } else if (botonPulsado == arriba) {
            tablero.moverArriba();
        } else if (botonPulsado == abajo) {
            tablero.moverAbajo();
        }
    }
}
