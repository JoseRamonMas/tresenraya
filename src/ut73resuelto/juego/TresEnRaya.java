package ut73resuelto.juego;

import ut73resuelto.juego.enums.EstadoCasilla;
import ut73resuelto.juego.Turno;

class TresEnRaya {

    private Tablero tablero;
    private Jugador[] jugadores;
    private Turno turno;
    
    public TresEnRaya(){
        tablero = new Tablero();
        jugadores = new Jugador[2];
        jugadores[0] = new Jugador(EstadoCasilla.XS);
        jugadores[1] = new Jugador(EstadoCasilla.OS);
        turno = new Turno();
    }
    
    public void jugar(){
        do {
            if (tablero.estaLleno()) 
                tablero.vaciar();
            
            tablero.mostrar();
            jugadores[turno.toca()].ponerFicha(tablero);
            turno.cambiar();
            
        } while (!tablero.hayTresEnRaya());
        
        jugadores[turno.noToca()].cantaVictoria();
    }
    
    public static void main(String[] args){
        new TresEnRaya().jugar();
    }
}
