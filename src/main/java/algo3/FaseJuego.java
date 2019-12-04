package algo3;

import Controlador.BotonJugar;
import Modelo.AlgoChess;
import Vista.TableroGridPane;
import Vista.VentanaInstruccionesJuego;
import Vista.VentanaInstruccionesSeleccionUnidades;
import Vista.VentanaJuegoTerminado;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
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
        String ganador = juego.seTermino();
        if (ganador != null) {
            botonJugar1.setDisable(true);
            botonJugar2.setDisable(true);
            new VentanaJuegoTerminado(ganador, stage);
        } else if (nombreJugador1.equals(jugador)) {
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

        //Jugador 1
        VBox jugador1 = new VBox();
        jugador1.setMinWidth(125);
        jugador1.setSpacing(20);
        jugador1.setPadding(new Insets(25));
        this.ubicarTextos(nombreJugador1, this.getColorSector(juego.getSector(nombreJugador1)), jugador1);
        this.fijarBoton(nombreJugador1, jugador1);
        jugador1.setAlignment(Pos.CENTER);

        //jugador 2
        VBox jugador2 = new VBox();
        jugador2.setMinWidth(125);
        jugador2.setSpacing(20);
        jugador2.setPadding(new Insets(25));
        this.ubicarTextos(nombreJugador2, this.getColorSector(juego.getSector(nombreJugador2)), jugador2);
        this.fijarBoton(nombreJugador2, jugador2);
        jugador2.setAlignment(Pos.CENTER);

        Button instrucciones = fijarBotonInstrucciones();
        //Layout y Scene
        BorderPane caja = new BorderPane();
        jugador1.getChildren().addAll(instrucciones);
        caja.setLeft(jugador1);
        BorderPane.setAlignment(jugador1, Pos.CENTER);
        caja.setCenter(tablero.getVisual());
        caja.getCenter().maxWidth(tablero.getAncho());
        BorderPane.setAlignment(tablero.getVisual(), Pos.CENTER);
        caja.setRight(jugador2);
        BorderPane.setAlignment(jugador2, Pos.CENTER);
        stage.setScene(new Scene(caja));
    }

    public void ubicarTextos(String nombre, String Sector, VBox caja) {
        Label nombreJugador = new Label(nombre);
        nombreJugador.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
        nombreJugador.setTextAlignment(TextAlignment.CENTER);
        nombreJugador.setTextFill(Color.web("000000"));

        Label sectorJugador = new Label(String.format("Sector %s", Sector));
        sectorJugador.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
        sectorJugador.setTextAlignment(TextAlignment.CENTER);
        sectorJugador.setTextFill(Color.web("000000"));

        caja.getChildren().addAll(nombreJugador, sectorJugador);
    }

    public void fijarBoton(String nombreJugador, VBox caja) {
        Button botonJugar = new Button();
        botonJugar.setText("Jugar");
        botonJugar.setStyle("-fx-base: #ff763d;");
        botonJugar.setPrefSize(120, 30);
        botonJugar.setAlignment(Pos.CENTER);
        BotonJugar jugar = new BotonJugar(juego, tablero, nombreJugador, this);
        botonJugar.setOnAction(jugar);
        if (nombreJugador.equals(nombreJugador1)) {
            botonJugar1 = botonJugar;
        } else {
            botonJugar2 = botonJugar;
            botonJugar2.setDisable(true);
        }
        caja.getChildren().addAll(botonJugar);
    }

    private Button fijarBotonInstrucciones() {
        Button botonInstrucciones = new Button();
        botonInstrucciones.setText("Instrucciones");
        botonInstrucciones.setStyle("-fx-base: #ff763d;");
        botonInstrucciones.setMinSize(120, 40);
        botonInstrucciones.setAlignment(Pos.CENTER);
        botonInstrucciones.setOnAction(actionEvent -> {
            new VentanaInstruccionesJuego();
        });
        return botonInstrucciones;
    }

    private String getColorSector(int Sector) {
        if (Sector == 1) {
            return "Rosa";
        }
        return "Gris";
    }
}
