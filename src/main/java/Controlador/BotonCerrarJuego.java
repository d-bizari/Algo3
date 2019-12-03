package Controlador;

import algo3.VentanaJuegoTerminado;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class BotonCerrarJuego implements EventHandler<ActionEvent> {
    private Stage faseJuego;
    private VentanaJuegoTerminado ventanaJuegoTerminado;

    public BotonCerrarJuego(Stage faseJuego, VentanaJuegoTerminado ventanaJuegoTerminado) {
        this.faseJuego = faseJuego;
        this.ventanaJuegoTerminado = ventanaJuegoTerminado;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        ventanaJuegoTerminado.cerrar();
        faseJuego.close();
    }
}

