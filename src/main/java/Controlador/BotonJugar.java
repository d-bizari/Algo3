package Controlador;

import Modelo.AlgoChess;
import Modelo.Coordenada;
import algo3.FaseJuego;
import algo3.OpcionesDeJuego;
import algo3.TableroGridPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class BotonJugar extends Button implements EventHandler<ActionEvent> {
    private AlgoChess juego;
    private TableroGridPane tablero;
    private String jugador;
    FaseJuego faseJuego;

    public BotonJugar(AlgoChess juego, TableroGridPane tablero, String jugador, FaseJuego faseJuego) {
        this.juego = juego;
        this.tablero = tablero;
        this.jugador = jugador;
        this.faseJuego = faseJuego;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        new OpcionesDeJuego(juego, tablero, jugador, faseJuego);
        actionEvent.consume();
    }
}
