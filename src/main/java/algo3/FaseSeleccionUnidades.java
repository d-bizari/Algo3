package algo3;


import Modelo.AlgoChess;
import Modelo.Coordenada;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class FaseSeleccionUnidades {

    private final Stage stage;
    private final AlgoChess juego;
    private String nombreJugador1;
    private String nombreJugador2;
    private TableroGridPane tablero;
    private String jugadorEnTurno;

    public FaseSeleccionUnidades(Stage stage, AlgoChess juego, String jugador1, String jugador2) {
        this.stage = stage;
        this.juego = juego;
        this.nombreJugador1 = jugador1;
        this.nombreJugador2 = jugador2;
        this.tablero = new TableroGridPane(juego, 2000,2000);
        this.jugadorEnTurno = this.nombreJugador1;
    }


    public void mostrar(Stage stage) {
        ((this.tablero).getVisual()).addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event){
                Label turno = new Label(jugadorEnTurno);
                Coordenada coordenada = tablero.clickGrid(event);
                if (jugadorEnTurno == "PASAR ETAPA") {

                } else {
                    new Vista.SelectorUnidades(jugadorEnTurno, juego, coordenada, tablero);
                }
                cambiarTurno();
                event.consume();
            }
        });
        Scene scene = new Scene(tablero.getVisual());
        stage.setScene(scene);
    }

    public void cambiarTurno() {
        if ((this.juego).getPuntosRestantes(this.jugadorEnTurno) == 0 ) {
            if (this.jugadorEnTurno == nombreJugador1) {
                this.jugadorEnTurno = nombreJugador2;
            } else {
                this.jugadorEnTurno = "PASAR_ETAPA";
            }
        }
    }

}