package Controlador;

import Excepciones.CoordenadaFueraDeRango;
import Excepciones.ErrorAutoAtaque;
import Excepciones.ErrorNoHayUnidadAtacante;
import Excepciones.UnidadEnemiga;
import Modelo.AlgoChess;
import Modelo.Coordenada;
import Vista.AlertBox;
import Vista.OpcionesDeJuego;
import Vista.TableroGridPane;
import algo3.FaseJuego;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class BotonAtacarUnidad implements EventHandler<ActionEvent> {
    private AlgoChess juego;
    private TableroGridPane tablero;
    private Coordenada coordenadaDesde;
    private Coordenada coordenadaHasta;
    private String jugador;
    private OpcionesDeJuego vista;
    private boolean aux;
    private FaseJuego faseJuego;

    public BotonAtacarUnidad(AlgoChess juego, TableroGridPane tablero, String nombreJugador, OpcionesDeJuego vista, FaseJuego faseJuego) {
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
                juego.atacarDesdeHasta(jugador, coordenadaDesde.getCoordenadaX(), coordenadaDesde.getCoordenadaY(), coordenadaHasta.getCoordenadaX(), coordenadaHasta.getCoordenadaY());
                tablero.actualizar();
                reproducirSonido(juego.getTipoDeUnidadEnPosicion(coordenadaDesde.getCoordenadaX(), coordenadaDesde.getCoordenadaY()));
            }
            catch(UnidadEnemiga seleccion) {
                AlertBox.display("Atencion", "ha seleccionado una unidad enemiga");
            }
            catch (ErrorAutoAtaque | ErrorNoHayUnidadAtacante | CoordenadaFueraDeRango exc) {
                AlertBox.display("Atencion", "no puede atacar");

            }
            faseJuego.cambiarTurno(jugador);
            event.consume();
        }
    }

    private void reproducirSonido(String tipoDeUnidadEnPosicion) {
        String musicFile = "soundFX/Catapulta.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();

        switch (tipoDeUnidadEnPosicion){
            case "Jinete":

                break;

            case "Soldado Infanteria":
                break;

            case "Catapulta":
                break;

        }
    }
}





