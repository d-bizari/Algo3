package Excepciones;

public class NoPuedeAtacar  extends Exception {
    public NoPuedeAtacar() {
        super();

    }

    public NoPuedeAtacar (String message) {
        super(message);
    }
}