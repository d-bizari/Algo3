package Excepciones;

public class NoPuedeMoverseException  extends Exception {
    public NoPuedeMoverseException() {
        super();

    }

    public NoPuedeMoverseException (String message) {
        super(message);
    }
}
