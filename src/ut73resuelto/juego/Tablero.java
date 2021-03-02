package ut73resuelto.juego;

import ut73resuelto.juego.enums.EstadoCasilla;
import ut73resuelto.util.GestorIO;

class Tablero {

    private EstadoCasilla[][] casillas;

    private static final int DIMENSION = 3;

    public Tablero() {
        casillas = new EstadoCasilla[DIMENSION][DIMENSION];
        vaciar();
    }

    public void mostrar() {
        GestorIO gestorIO = new GestorIO();
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                gestorIO.out(" " + casillas[i][j]);
            }
            gestorIO.out("\n");
        }
        gestorIO.out("\n");
    }

    public boolean hayTresEnRaya() {
        return this.hayTresEnRaya(EstadoCasilla.XS) || this.hayTresEnRaya(EstadoCasilla.OS);
    }

    private boolean hayTresEnRaya(EstadoCasilla color) {
        int[] filas = new int[DIMENSION];
        int[] columnas = new int[DIMENSION];
        int diagonal = 0;
        int secundaria = 0;
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                if (color == casillas[i][j]) {
                    filas[i]++;
                    columnas[j]++;
                    if (i == j) {
                        diagonal++;
                    }
                    if (i + j == 2) {
                        secundaria++;
                    }
                }
            }
        }
        if (diagonal == DIMENSION || secundaria == DIMENSION) {
            return true;
        } else {
            for (int i = 0; i < 3; i++) {
                if (filas[i] == DIMENSION || columnas[i] == DIMENSION) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean ocupada(Coordenada coordenada, EstadoCasilla color) {
        assert coordenada != null;
        return casillas[coordenada.getFila() - 1][coordenada.getColumna() - 1] == color;
    }

    public boolean ocupada(Coordenada coordenada) {
        return !this.ocupada(coordenada, EstadoCasilla.VACIA);
    }

    public void ponerFicha(Coordenada coordenada, EstadoCasilla color) {
        assert coordenada != null;
        casillas[coordenada.getFila() - 1][coordenada.getColumna() - 1] = color;
    }

    public void vaciar() {
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                casillas[i][j] = EstadoCasilla.VACIA;
            }
        }
    }

    public boolean estaLleno() {
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                if (casillas[i][j] == EstadoCasilla.VACIA) {
                    return false;
                }
            }
        }
        return true;
    }
}
