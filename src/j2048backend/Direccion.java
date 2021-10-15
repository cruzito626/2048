package j2048backend;

enum Direccion {
    IZQUIERDA(1),
    DERECHA(-1),
    ARRIBA(1),
    ABAJO(-1);

    private int sentido;
    Direccion(int sentido) {
        this.sentido = sentido;
    }

    int getSentido(){
        return sentido;
    }

    boolean esHorizontal(){
        return this == IZQUIERDA || this == DERECHA;
    }
}
