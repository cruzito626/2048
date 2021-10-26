package j2048frontend;

public class Helper {

    private Helper(){}

    public static String[][] strAMatriz2D(String str, String regexFila, String regexColumna) {
        String[][] matriz;
        String[] lineas;
        int tamanio;

        lineas = str.split(regexFila);
        tamanio = lineas.length;
        matriz = new String[tamanio][tamanio];
        for (int i = 0; i < tamanio; i++) {
            matriz[i] = lineas[i].split(regexColumna);
        }
        return matriz;
    }
}
