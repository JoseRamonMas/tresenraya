package ut73resuelto.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GestorIO {

    /**
     * Lee un string introducido por teclado
     *
     * @return
     */
    public String inString() {
        String entrada = null;
        try {
            entrada = b.readLine();
        } catch (Exception e) {
            this.salir();
        }
        return entrada;
    }

    /**
     * Lee un entero introducido por teclado
     *
     * @return
     */
    public int inInt() {
        int entrada = 0;
        try {
            entrada = Integer.parseInt(this.inString());
        } catch (Exception e) {
            this.salir();
        }
        return entrada;
    }

    /**
     * Lee un real de baja precisión introducido por teclado
     *
     * @return
     */
    public float inFloat() {
        float entrada = 0;
        try {
            entrada = Float.parseFloat(this.inString());
        } catch (Exception e) {
            this.salir();
        }
        return entrada;
    }

    /**
     * Lee un real de alta precisión introducido por teclado
     *
     * @return
     */
    public double inDouble() {
        double entrada = 0.0;
        try {
            entrada = Double.parseDouble(this.inString());
        } catch (Exception e) {
            this.salir();
        }
        return entrada;
    }

    /**
     * Lee un entero largo introducido por teclado
     *
     * @return
     */
    public long inLong() {
        long entrada = 0;
        try {
            entrada = Long.parseLong(this.inString());
        } catch (Exception e) {
            this.salir();
        }
        return entrada;
    }

    /**
     * Lee un entero byte introducido por teclado
     *
     * @return
     */
    public byte inByte() {
        byte entrada = 0;
        try {
            entrada = Byte.parseByte(this.inString());
        } catch (Exception e) {
            this.salir();
        }
        return entrada;
    }

    /**
     * Lee un entero corto introducido por teclado
     *
     * @return
     */
    public short inShort() {
        short entrada = 0;
        try {
            entrada = Short.parseShort(this.inString());
        } catch (Exception e) {
            this.salir();
        }
        return entrada;
    }

    /**
     * Lee un carácter introducido por teclado
     *
     * @return
     */
    public char inChar() {
        char caracter = ' ';
        String entrada = this.inString();
        if (entrada.length() > 1) {
            this.salir();
        } else {
            caracter = entrada.charAt(0);
        }
        return caracter;
    }

    /**
     * Lee un booleano introducido por teclado
     *
     * @return
     */
    public boolean inBoolean() {
        boolean entradaLogica = true;
        String entrada = this.inString();
        if (entrada.equalsIgnoreCase("true") || entrada.equalsIgnoreCase("false")) {
            entradaLogica = Boolean.valueOf(entrada).booleanValue();
        } else {
            this.salir();
        }
        return entradaLogica;
    }

    /**
     * Imprime un string
     *
     * @param salida
     */
    public void out(String salida) {
        System.out.print(salida);
    }

    /**
     * Imprime un entero
     *
     * @param salida
     */
    public void out(int salida) {
        System.out.print(salida);
    }

    /**
     * Imprime un real de baja precisión
     *
     * @param salida
     */
    public void out(float salida) {
        System.out.print(salida);
    }

    /**
     * Imprime un real de alta precisión
     *
     * @param salida
     */
    public void out(double salida) {
        System.out.print(salida);
    }

    /**
     * Imprime un entero largo
     *
     * @param salida
     */
    public void out(long salida) {
        System.out.print(salida);
    }

    /**
     * Imprime un entero byte
     *
     * @param salida
     */
    public void out(byte salida) {
        System.out.print(salida);
    }

    /**
     * Imprime un entero corto
     *
     * @param salida
     */
    public void out(short salida) {
        System.out.print(salida);
    }

    /**
     * Imprime un caracter
     *
     * @param salida
     */
    public void out(char salida) {
        System.out.print(salida);
    }

    /**
     * Imprime un booleano
     *
     * @param salida
     */
    public void out(boolean salida) {
        System.out.print(salida);
    }
    
    /**
     * Imprime un objeto
     * @param objeto 
     */
    
    public void out(Object objeto) {
        System.out.print(objeto);
    }

    private void salir() {
        System.out.println("ERROR de entrada/salida");
        System.exit(0);
    }

    private static BufferedReader b = new BufferedReader(new InputStreamReader(System.in));

  
}
