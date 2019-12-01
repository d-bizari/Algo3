package algo3;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class OpcionesDeJuego extends VBox {

    public OpcionesDeJuego() {
        super();

        this.ubicarTitulo();
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(20);
        this.setPadding(new Insets(25));

        this.fijarBotones();


    }

    public void  ubicarTitulo() {
        Label titulo = new Label("Elija que movimiento hacer");
        titulo.setFont(Font.font("Tahoma", FontWeight.BOLD, 18));
        titulo.setTextAlignment(TextAlignment.CENTER);
        titulo.setTextFill(Color.web("000000"));
        this.getChildren().addAll(titulo);
    }

    public void fijarBotones() {
        Button botonMover = new Button();
        botonMover.setText("Mover");
        botonMover.setStyle("-fx-base: #ff763d;");
        botonMover.setPrefSize(120, 30);

        Button botonAtacar = new Button();
        botonAtacar.setText("Atacar");
        botonAtacar.setStyle("-fx-base: #ff763d;");
        botonAtacar.setPrefSize(120, 30);

        this.getChildren().addAll(botonMover, botonAtacar);

    }
}
