package Excepciones;

public class CoordenadaFueraDeRango  extends Exception {
    public CoordenadaFueraDeRango() {
        super();

    }

    public CoordenadaFueraDeRango (String message) {
        super(message);
    }
}
