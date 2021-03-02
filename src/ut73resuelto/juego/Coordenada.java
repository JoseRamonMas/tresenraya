package ut73resuelto.juego;

import ut73resuelto.util.GestorIO;
import ut73resuelto.util.Intervalo;

class Coordenada {

    private int fila;
    private int columna;
    

    private static final Intervalo LIMITES = new Intervalo(1, 3);

    public Coordenada() {
    }

    public Coordenada(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        assert this.valida();
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public void recoger() {
        boolean error = false;
        do {
            GestorIO gestorIO = new GestorIO();
            gestorIO.out("Introduce fila [1,3]: ");
            fila = gestorIO.inInt();
            gestorIO.out("Introduce columna [1,3]: ");
            columna = gestorIO.inInt();
            error = !this.valida();
            if (error) {
                new GestorIO().out("Error!!! Valores fuera de rango\n");
            }
        } while (error);
    }

    private boolean valida() {
        return LIMITES.incluye(fila) && LIMITES.incluye(columna);
    }
}
