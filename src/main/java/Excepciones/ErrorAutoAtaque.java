package Excepciones;

public class ErrorAutoAtaque  extends Exception {
    public ErrorAutoAtaque() {
        super();

    }

    public ErrorAutoAtaque (String message) {
        super(message);
    }
}