package algo3;


import Modelo.AlgoChess;
import Modelo.Coordenada;
import Vista.AlertBox;
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
                    FaseJuego fase2  = new FaseJuego(stage, juego, nombreJugador1, nombreJugador2, tablero);
                    fase2.mostrar(stage);
                } else {
                    try{
                        if(juego.getCelda(coordenada.getCoordenadaX(),coordenada.getCoordenadaY()).estaOcupada()){
                            AlertBox.display("Aviso - No se puede colocar", "Celda ocupada");
                            return;
                        }
                        if(!juego.getCelda(coordenada.getCoordenadaX(),coordenada.getCoordenadaY()).esDeSectorAliado(juego.getJugador(jugadorEnTurno))){
                            AlertBox.display("Aviso - No se puede colocar", "Territorio enemigo");
                            return;
                        }
                    }catch (Exception e){
                        AlertBox.display("Error - Fuera de rango", "Fuera de rango");
                    }

                    new Vista.SelectorUnidades(jugadorEnTurno, juego, coordenada, tablero);
                    cambiarTurno();
                }

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