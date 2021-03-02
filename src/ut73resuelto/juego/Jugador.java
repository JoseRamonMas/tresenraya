package ut73resuelto.juego;

import ut73resuelto.juego.enums.EstadoCasilla;
import ut73resuelto.util.GestorIO;
import ut73resuelto.juego.Tablero;

class Jugador {

    private EstadoCasilla color;

    public Jugador(EstadoCasilla color) {
        assert color == EstadoCasilla.OS || color == EstadoCasilla.XS;
        this.color = color;
    }

    public void ponerFicha(Tablero tablero) {
        assert tablero != null;
        assert !tablero.estaLleno();
        new GestorIO().out("Pone el jugador con " + color + "\n");
        Coordenada coordenada = this.recogerCoordenadaPuestaValida(tablero);
        tablero.ponerFicha(coordenada, color);
    }

    private Coordenada recogerCoordenadaPuestaValida(Tablero tablero) {
        Coordenada resultado = new Coordenada();
        String error = "";
        do {
            resultado.recoger();
            error = this.errorPuesta(tablero, resultado);
            if (error != null) {
                new GestorIO().out("Error!!! " + error + "\n");
            }
        } while (error != null);
        return resultado;
    }

    private String errorPuesta(Tablero tablero, Coordenada coordenada) {
        if (tablero.ocupada(coordenada)) {
            return "Coordenada ocupada en el tablero";
        }
        return null;
    }
      
    public void cantaVictoria() {
        new GestorIO().out("CAMPEONESSS! Los " + color + " son los mejores\n");

    }
}
