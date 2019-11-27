package algo3;

import Modelo.AlgoChess;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controlador {

    private Stage stage;
    private AlgoChess juego;
    private TableroGridPane tablero;
    private TextField jugador1;
    private TextField jugador2;
    private int sectorJugador1;
    private int sectorJugador2;


    public Controlador(Stage escena, AlgoChess juego, TextField NombreJugador1, TextField NombreJugador2, int sector1, int sector2){
        this.stage = escena;
        this.juego = juego;
        this.tablero = new TableroGridPane(juego, 20, 20);
        this.jugador1 = NombreJugador1;
        this.jugador2 = NombreJugador2;
        this.sectorJugador1 = sector1;
        this.sectorJugador2 = sector2;
    }

    public void mostrar(Stage stage) {
        Scene escenaJuego = new Scene(tablero.getVisual(), 640, 480);
        stage.setScene(escenaJuego);
        stage.setFullScreenExitHint("");
        stage.setFullScreen(true);
    }
}
