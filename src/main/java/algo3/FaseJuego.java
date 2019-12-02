package algo3;

import Controlador.BotonJugar;
import Controlador.BotonMoverUnidad;
import Modelo.AlgoChess;
import Vista.AlertBox;
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
    Button botonJugar1;
    Button botonJugar2;

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

    public void cambiarTurno(String jugador) {
        if (juego.seTermino()) {
            botonJugar1.setDisable(true);
            botonJugar2.setDisable(true);
            AlertBox.display("JUEGO TERMINADO", "Se termino el juego");
        }
        if (nombreJugador1.equals(jugador)) {
            jugadorEnTurno = nombreJugador2;
            botonJugar1.setDisable(true);
            botonJugar2.setDisable(false);
        } else {
            jugadorEnTurno = nombreJugador1;
            botonJugar2.setDisable(true);
            botonJugar1.setDisable(false);
        }
    }

    public void crearEscena() {
        botonJugar1 = new Button();
        botonJugar1.setText("Jugar");
        botonJugar1.setStyle("-fx-base: #ff763d;");
        botonJugar1.setPrefSize(120, 30);
        BotonJugar jugar1 = new BotonJugar(juego, tablero, nombreJugador1, this);
        botonJugar1.setOnAction(jugar1);

        botonJugar2 = new Button();
        botonJugar2.setText("Jugar");
        botonJugar2.setStyle("-fx-base: #ff763d;");
        botonJugar2.setPrefSize(120, 30);
        BotonJugar jugar2 = new BotonJugar(juego, tablero, nombreJugador2, this);
        botonJugar2.setOnAction(jugar2);
        botonJugar2.setDisable(true);

        BorderPane caja = new BorderPane();
        caja.setLeft(botonJugar1);
        caja.setCenter(tablero.getVisual());
        caja.setRight(botonJugar2);
        stage.setScene(new Scene(caja));
    }
}
