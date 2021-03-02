package ut73resuelto.juego;

import java.util.Random;

class Turno {

    private int valor;

    public Turno() {
        valor = new Random().nextInt(2);
    }

    public int toca() {
        return valor;
    }

    public int noToca() {
        return (valor + 1) % 2;
    }

    public void cambiar() {
        valor = this.noToca();
    }
}
