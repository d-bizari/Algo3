package algo3;

import Modelo.AlgoChess;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class ContenedorInicial extends VBox {

    public ContenedorInicial(Stage stage, AlgoChess juego) {
        super();

        this.ubicarTituloSubtitulo();

        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(20);
        this.setPadding(new Insets(25));

        this.fijarFondo();

        this.fijarBotones(stage, juego);
    }

    private void ubicarTituloSubtitulo() {

        Label titulo = new Label("AlgoChess");
        titulo.setFont(Font.font("Tahoma", FontWeight.BOLD, 18));
        titulo.setTextAlignment(TextAlignment.CENTER);
        titulo.setTextFill(Color.web("000000"));
        Label subtitulo = new Label("Un juego de estrategia");
        subtitulo.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
        subtitulo.setTextAlignment(TextAlignment.CENTER);
        subtitulo.setTextFill(Color.web("000000"));
        this.getChildren().addAll(titulo, subtitulo);
    }

    private void fijarFondo() {

        Image imagen = new Image("file:img/imagenInicial.jpg");
        BackgroundPosition position = new BackgroundPosition(Side.LEFT, 0.5,
                true, Side.BOTTOM, 0, true);
        BackgroundSize size = new BackgroundSize(0.20, 0.40, true, true, true, false);
        BackgroundImage imagenDeFondo = new BackgroundImage(imagen, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                position, size);
        this.setBackground(new Background(imagenDeFondo));
        this.setBackground(new Background(imagenDeFondo));

    }

    private void fijarBotones(Stage stage, AlgoChess juego) {
        TextField jugador1 = new TextField();
        jugador1.setPromptText("Ingrese su nombre");
        jugador1.setMaxWidth(250);
        jugador1.setText("Jugador 1");

        TextField jugador2 = new TextField();
        jugador2.setPromptText("Ingrese su nombre");
        jugador2.setMaxWidth(250);
        jugador2.setText("Jugador 2");

        //Eleccion de sector
        ChoiceBox<Integer> sectores1 = new ChoiceBox<Integer>();
        sectores1.getItems().add(1);
        sectores1.getItems().add(2);
        sectores1.setValue(1);

        //Jugador 1
        Label sectorJugador1 = new Label("Sector:");

        //Eleccion de sector
        ChoiceBox<Integer> sectores2 = new ChoiceBox<Integer>();
        sectores2.getItems().add(1);
        sectores2.getItems().add(2);
        sectores2.setValue(1);
        //Jugador 2
        Label sectorJugador2 = new Label("Sector:");


        BotonIniciarJuego botonJugar = new BotonIniciarJuego(stage, juego, jugador1, jugador2, sectores1, sectores2);
        botonJugar.setText("Iniciar partida");
        botonJugar.setDefaultButton(true);
        botonJugar.setStyle("-fx-base: #ff763d;");
        botonJugar.setPrefSize(120, 30);

        botonJugar.setDefaultButton(true);
        botonJugar.setOnAction(botonJugar);

        this.getChildren().addAll(jugador1, sectorJugador1, sectores1, jugador2, sectorJugador2, sectores2, botonJugar);
    }


}
