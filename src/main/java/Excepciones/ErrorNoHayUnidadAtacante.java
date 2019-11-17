package Excepciones;

public class ErrorNoHayUnidadAtacante  extends Exception {
    public ErrorNoHayUnidadAtacante() {
        super();

    }

    public ErrorNoHayUnidadAtacante (String message) {
        super(message);
    }
}