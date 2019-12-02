package algo3;

import Modelo.AlgoChess;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class FaseJuego {

    private final Stage stage;
    private final AlgoChess juego;
    private String nombreJugador1;
    private String nombreJugador2;
    private TableroGridPane tablero;
    private String jugadorEnTurno;

    public FaseJuego (Stage stage, AlgoChess juego, String jugador1, String jugador2, TableroGridPane tablero) {
        this.stage = stage;
        this.juego = juego;
        this.nombreJugador1 = jugador1;
        this.nombreJugador2 = jugador2;
        this.tablero  = tablero;
        this.jugadorEnTurno = this.nombreJugador1;
    }

    public void mostrar() {
        crearEscena();
    }

    public void cambiarTurno() {
        if (jugadorEnTurno.equals(nombreJugador1)) {
            jugadorEnTurno = nombreJugador2;
        } else {
            jugadorEnTurno = nombreJugador1;
        }
    }

    public void crearEscena() {
        Button botonJugar1 = new Button();
        botonJugar1.setText("Jugar");
        botonJugar1.setStyle("-fx-base: #ff763d;");
        botonJugar1.setPrefSize(120, 30);

        Button botonJugar2 = new Button();
        botonJugar2.setText("Jugar");
        botonJugar2.setStyle("-fx-base: #ff763d;");
        botonJugar2.setPrefSize(120, 30);

        BorderPane caja = new BorderPane();
        caja.setLeft(botonJugar1);
        caja.setCenter(tablero.getVisual());
        caja.setRight(botonJugar2);
        stage.setScene(new Scene(caja));
    }
}
