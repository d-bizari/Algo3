package Controlador;

import Modelo.AlgoChess;
import Modelo.Coordenada;
import Vista.SelectorUnidades;
import algo3.AlertBox;
import algo3.TableroGridPane;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.HashMap;

public class BotonSeleccionarUnidades implements EventHandler<ActionEvent>{
    private SelectorUnidades vista;
    private Button b;
    private AlgoChess juego;
    private Coordenada coordenada;
    private String jugador;
    private String unidadElegida;
    private TableroGridPane tablero;

    public BotonSeleccionarUnidades(SelectorUnidades vista, Button b, AlgoChess juego, Coordenada coordena, String nombreJugador, String unidad, TableroGridPane tablero){
        this.vista = vista;
        this.b = b;
        this.juego = juego;
        this.jugador = nombreJugador;
        this.coordenada = coordena;
        this.unidadElegida = unidad;
        this.tablero = tablero;
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            switch (unidadElegida) {
                case "Jinete":
                    juego.colocarJinetePara(jugador, coordenada.getCoordenadaX(), coordenada.getCoordenadaY());
                    //tablero.setUnidadEnCelda(unidadElegida, coordenada.getCoordenadaX(),coordenada.getCoordenadaY());
                    tablero.agregarContenidoCeldaJinete(coordenada.getCoordenadaX(), coordenada.getCoordenadaY());
                    break;

                case "Soldado Infanteria":
                    juego.colocarSoldadoInfanteriaPara(jugador, coordenada.getCoordenadaX(), coordenada.getCoordenadaY());
                    tablero.setUnidadEnCelda(unidadElegida, coordenada.getCoordenadaX(),coordenada.getCoordenadaY());
                    break;

                case "Catapulta":
                    juego.colocarCatapultaPara(jugador, coordenada.getCoordenadaX(), coordenada.getCoordenadaY());
                    tablero.setUnidadEnCelda(unidadElegida, coordenada.getCoordenadaX(),coordenada.getCoordenadaY());
                    break;

                case "Curandero":
                    juego.colocarCuranderoPara(jugador, coordenada.getCoordenadaX(), coordenada.getCoordenadaY());
                    tablero.setUnidadEnCelda(unidadElegida, coordenada.getCoordenadaX(),coordenada.getCoordenadaY());
                    break;
            }
        } catch (Excepciones.CeldaDeTerritorioEnemigo enem) {
            enem.printStackTrace();
            AlertBox.display("Error - No se puede colocar", "Territorio enemigo"); //TODO MEJORAR TRATAMIENTO EXCEPCIONES PARICUALRES
        } catch (Excepciones.CeldaOcupada | Excepciones.CoordenadaFueraDeRango ocup) {
            ocup.printStackTrace();
            AlertBox.display("Error - No se puede colocar", "Celda ocupada");
        } catch (Excepciones.PuntosInsuficientesException exc) {
            exc.printStackTrace();
            AlertBox.display("Error", "Puntos Insuficientes");
        }
        event.consume();
        vista.cerrarVentana();
    }
}


