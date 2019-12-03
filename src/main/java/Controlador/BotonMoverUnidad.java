package Controlador;

import Excepciones.CeldaOcupada;
import Excepciones.CoordenadaFueraDeRango;
import Excepciones.NoPuedeMoverseException;
import Excepciones.UnidadEnemiga;
import Modelo.AlgoChess;
import Modelo.Coordenada;
import Vista.AlertBox;
import algo3.FaseJuego;
import algo3.OpcionesDeJuego;
import algo3.TableroGridPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class BotonMoverUnidad implements EventHandler<ActionEvent> {
     private AlgoChess juego;
     private TableroGridPane tablero;
     private Coordenada coordenadaDesde;
     private Coordenada coordenadaHasta;
     private String jugador;
     private OpcionesDeJuego vista;
     private FaseJuego faseJuego;
     private boolean aux;

     public BotonMoverUnidad(AlgoChess juego, TableroGridPane tablero, String nombreJugador, OpcionesDeJuego vista, FaseJuego faseJuego) {
         this.juego = juego;
         this.tablero = tablero;
         this.jugador = nombreJugador;
         this.vista = vista;
         this.coordenadaDesde = null;
         this.coordenadaHasta = null;
         this.faseJuego = faseJuego;
     }

     @Override
    public void handle(ActionEvent actionEvent) {
         vista.cerrar();
         ((this.tablero).getVisual()).addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
             @Override
             public void handle(MouseEvent event){
                 agregarCoordenada(actionEvent, tablero.clickGrid(event));
                 event.consume();
             }});

     }

     public void agregarCoordenada(ActionEvent event, Coordenada coordenada) {
        if (coordenadaDesde == null) {
            coordenadaDesde = coordenada;
        } else if (coordenadaHasta == null) {
            coordenadaHasta = coordenada;
            try {
                juego.moverUnidadDesdeHasta(jugador, coordenadaDesde.getCoordenadaX(), coordenadaDesde.getCoordenadaY(), coordenadaHasta.getCoordenadaX(), coordenadaHasta.getCoordenadaY());
                tablero.actualizar();

            }
            catch(UnidadEnemiga seleccion) {
                AlertBox.display("Atencion", "ha seleccionado una unidad enemiga");
            }
            catch (NoPuedeMoverseException | CeldaOcupada | CoordenadaFueraDeRango exc) {
                AlertBox.display("Atencion", "no puede moverse");
            }
            faseJuego.cambiarTurno(jugador);
            event.consume();
        }
    }
}




