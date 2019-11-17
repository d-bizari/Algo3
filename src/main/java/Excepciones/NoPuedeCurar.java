package Excepciones;

public class NoPuedeCurar  extends Exception {
    public NoPuedeCurar() {
        super();

    }

    public NoPuedeCurar (String message) {
        super(message);
    }
}
