package ut73resuelto.util;

public class Intervalo {

    private double inferior;
    private double superior;

    /**
     * Proporciona un Intervalo con los límites inferior y superior dados
     *
     * @param inferior
     * @param superior
     */
    public Intervalo(double inferior, double superior) {
        assert superior >= inferior;
        
        this.inferior = inferior;
        this.superior = superior;
    }

    /**
     * Proporciona un Intervalo con límite superior dado
     *
     * @param superior
     */
    public Intervalo(double superior) {
        this(0, superior);
       
    }

    /**
     * Proporciona un Intervalo a partir de otro (copia)
     *
     * @param Intervalo
     */
    public Intervalo(Intervalo intervalo) {
        this(intervalo.inferior, intervalo.superior);
    }

    /**
     * Proporciona un Intervalo con límites por defecto
     */
    public Intervalo() {
        this(0, 0);
    }

    /**
     * Clona el Intervalo
     *
     * @return
     */
    public Intervalo clone() {
        return new Intervalo(this);
    }

    /**
     * Longitud del Intervalo
     *
     * @return
     */
    public double longitud() {
        return superior - inferior;
    }

    /**
     * Desplaza los límites del Intervalo según lo indicado
     *
     * @param desplazamiento
     */
    public void desplazar(double desplazamiento) {
        inferior += desplazamiento;
        superior += desplazamiento;
    }

    /**
     * Obtiene el Intervalo desplazado según el desplazamiento proporcionado
     *
     * @param desplazamiento
     * @return
     */
    public Intervalo desplazado(double desplazamiento) {
        Intervalo Intervalo = this.clone();
        Intervalo.desplazar(desplazamiento);
        return Intervalo;
    }

    /**
     * Determina el valor está dentro del Intervalo
     *
     * @param valor
     * @return
     */
    public boolean incluye(double valor) {
        return inferior <= valor && valor <= superior;
    }

    /**
     * Determina si el Intervalo proporcionado está incluido en el Intervalo
     *
     * @param Intervalo
     * @return
     */
    public boolean incluye(Intervalo intervalo) {
        assert intervalo != null;
        return this.incluye(intervalo.inferior)
                && this.incluye(intervalo.superior);
    }

    /**
     * Determina si dos intervalos son iguales
     *
     * @param Intervalo
     * @return
     */
    public boolean equals(Intervalo intervalo) {
        assert intervalo != null;
        return inferior == intervalo.inferior
                && superior == intervalo.superior;
    }

    /**
     * Obtiene el Intervalo que intersecta entre el Intervalo que se recibe y
     * uno mismo
     *
     * @param Intervalo
     * @return
     */
    public Intervalo interseccion(Intervalo intervalo) {
        assert this.intersecta(intervalo);
        
        if (this.incluye(intervalo)) {
            return intervalo.clone();
        } else if (intervalo.incluye(this)) {
            return this.clone();
        } else if (this.incluye(intervalo.inferior)) {
            return new Intervalo(intervalo.inferior, superior);
        } else {
            return new Intervalo(inferior, intervalo.superior);
        }
    }

    /**
     * Deterimna si el Intervalo intersecta con el proporcionado
     *
     * @param Intervalo
     * @return
     */
    public boolean intersecta(Intervalo intervalo) {
        assert intervalo != null;
        return this.incluye(intervalo.inferior)
                || this.incluye(intervalo.superior)
                || intervalo.incluye(inferior)
                || intervalo.incluye(superior);
    }

    /**
     * Transforma el Intervalo a su opuesto
     */
    public void oponer() {
        double inferiorInicial = inferior;
        double superiorInicial = superior;
        inferior = -superiorInicial;
        superior = -inferiorInicial;
    }

    /**
     * Aumenta el tamaño del Intervalo al doble de su longitud por ambos
     * extremos
     */
    public void doblar() {
        double longitudInicial = this.longitud();
        inferior -= longitudInicial / 2;
        superior += longitudInicial / 2;
    }

    /**
     * Pide al usuario que introduzca los límites del Intervalo
     */
    public void recoger() {
        GestorIO gestorIO = new GestorIO();
        gestorIO.out("Inferior?");
        inferior = gestorIO.inDouble();
        gestorIO.out("Superior?");
        superior = gestorIO.inDouble();
    }

    /**
     * Muestra el Intervalo siguiedo el formato [limiteInferior, limiteSuperior]
     *
     */
    public void mostrar() {
        GestorIO gestorIO = new GestorIO();
        gestorIO.out("[" + inferior + "," + superior + "]");
    }

    /**
     * Obtiene los subintervalos en tamaños iguales según los trozos
     * proporcionados
     *
     * @param trozos
     * @return
     */
    public Intervalo[] trocear(int trozos) {
        assert trozos > 0;
        
        double longitudTrozo = longitud() / trozos;
        Intervalo[] subIntervalos = new Intervalo[trozos];
        
        double inferiorTrozo = this.inferior;
        for (int i = 0; i < trozos; i++) {
            double superiorTrozo = inferiorTrozo + longitudTrozo;
            subIntervalos[i] = new Intervalo(inferiorTrozo, superiorTrozo);
            inferiorTrozo += longitudTrozo;
        }
        
        return subIntervalos;
    }
    
}
