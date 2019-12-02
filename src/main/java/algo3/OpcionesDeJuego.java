package algo3;

import Controlador.BotonMoverUnidad;
import Modelo.AlgoChess;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class OpcionesDeJuego<TableroGridPanetablero> {
    VBox caja;
    AlgoChess juego;
    String jugador;
    TableroGridPane tablero;
    public OpcionesDeJuego() {
        this.caja = new VBox();
        this.ubicarTitulo();
        caja.setAlignment(Pos.TOP_CENTER);
        caja.setSpacing(20);
        caja.setPadding(new Insets(25));

        this.fijarBotones();


    }

    public void  ubicarTitulo() {
        Label titulo = new Label("Elija que movimiento hacer");
        titulo.setFont(Font.font("Tahoma", FontWeight.BOLD, 18));
        titulo.setTextAlignment(TextAlignment.CENTER);
        titulo.setTextFill(Color.web("000000"));
        caja.getChildren().addAll(titulo);
    }

    public void fijarBotones() {
        BotonMoverUnidad botonMoverUnidad = new BotonMoverUnidad(juego, tablero, jugador);

        Button botonMover = new Button();
        botonMover.setText("Mover");
        botonMover.setStyle("-fx-base: #ff763d;");
        botonMover.setPrefSize(120, 30);
        botonMover.setOnAction(botonMoverUnidad);

        Button botonAtacar = new Button();
        botonAtacar.setText("Atacar");
        botonAtacar.setStyle("-fx-base: #ff763d;");
        botonAtacar.setPrefSize(120, 30);

        caja.getChildren().addAll(botonMover, botonAtacar);

    }
}
