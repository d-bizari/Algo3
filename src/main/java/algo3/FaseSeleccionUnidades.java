package algo3;


import Modelo.AlgoChess;
import Modelo.Coordenada;
import Vista.AlertBox;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class FaseSeleccionUnidades {

    private final Stage stage;
    private final AlgoChess juego;
    private String nombreJugador1;
    private String nombreJugador2;
    private TableroGridPane tablero;
    private String jugadorEnTurno;
    public static double width;
    public static double heigth;

    public FaseSeleccionUnidades(Stage stage, AlgoChess juego, String jugador1, String jugador2) {
        this.stage = stage;
        this.juego = juego;
        this.nombreJugador1 = jugador1;
        this.nombreJugador2 = jugador2;
        this.width = Screen.getPrimary().getVisualBounds().getWidth()*0.5;
        this.heigth = Screen.getPrimary().getVisualBounds().getHeight()*0.5;
        this.tablero = new TableroGridPane(juego, width,heigth);
        this.jugadorEnTurno = this.nombreJugador1;
    }


    public void mostrar() {
        ((this.tablero).getVisual()).addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event){
                Label turno = new Label(jugadorEnTurno);
                Coordenada coordenada = tablero.clickGrid(event);
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
                if (jugadorEnTurno.equals("PASAR_ETAPA")) {
                    FaseJuego fase2  = new FaseJuego(stage, juego, nombreJugador1, nombreJugador2, tablero);
                    fase2.mostrar();
                }

                event.consume();
            }
        });
        Scene scene = new Scene(tablero.getVisual(),width,heigth);
        stage.setScene(scene);
    }

    public void cambiarTurno() {
        if ((this.juego).getPuntosRestantes(this.jugadorEnTurno) == 0 ) {
            if (this.jugadorEnTurno.equals(nombreJugador1)) {
                this.jugadorEnTurno = nombreJugador2;
            } else {
                this.jugadorEnTurno = "PASAR_ETAPA";
            }
        }
    }

}