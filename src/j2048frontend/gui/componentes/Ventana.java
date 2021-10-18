package j2048frontend.gui.componentes;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {
    private final float TAMANIO = 0.5F;
    private float altoPantalla;
    private float anchoPantalla;
    private int alto;
    private int ancho;

    public Ventana(String titulo) {
        super(titulo);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.anchoPantalla = (float) dimension.getWidth();
        this.altoPantalla = (float) dimension.getHeight();
    }

    public void init() {
        cambiarTamanio();
        centrar();
    }

    private void centrar() {
        int x = (int) (anchoPantalla - ancho) / 2;
        int y = (int) (altoPantalla - alto) / 2;
        setLocation(x, y);
    }

    private void cambiarTamanio() {
        ancho = (int) (anchoPantalla * TAMANIO);
        alto = (int) (altoPantalla * TAMANIO);
        setSize(ancho, alto);
    }
}
