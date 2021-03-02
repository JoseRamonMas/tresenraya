package ut73resuelto.juego.enums;

public enum EstadoCasilla {
    
    OS('o'), XS('x'), VACIA('_'); 
    
    private char id;
    
    private EstadoCasilla(char id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
       return String.valueOf(id); 
    }
    
}
