package j2048frontend;

import j2048backend.Estado;

public interface Observador {
    void actualizar(Estado estado);
}
