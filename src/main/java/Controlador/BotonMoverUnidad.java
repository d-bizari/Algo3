package Controlador;

import Modelo.AlgoChess;
import Modelo.Coordenada;
import algo3.TableroGridPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class BotonMoverUnidad extends Button implements EventHandler<ActionEvent> {
     private AlgoChess juego;
     private TableroGridPane tablero;
     private Coordenada coordenadaDesde;
     private Coordenada coordenadaHasta;
     private String jugador;

     public BotonMoverUnidad(AlgoChess juego, TableroGridPane tablero, String nombreJugador) {
         this.juego = juego;
         this.tablero = tablero;
         this.jugador = nombreJugador;
     }

     @Override
    public void handle(ActionEvent actionEvent) {
         //Algun alertbox que indique que debe seleccionar la posicion desde donde quiere moverse
         //Como conseguir la coordenada clickeando
         //Algun alertbox que indique que debe seleccionar la posicion a la cual se quiere mover
         //Como conseguir la coordenada
         //Llame a mover unidad desde hasta
     }
}
