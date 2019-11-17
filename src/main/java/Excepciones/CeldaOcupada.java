package Excepciones;

public class CeldaOcupada  extends Exception {
    public CeldaOcupada() {
        super();

    }

    public CeldaOcupada (String message) {
        super(message);
    }
}