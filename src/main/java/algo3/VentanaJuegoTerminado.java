package algo3;

import Controlador.BotonCerrarJuego;
import Controlador.BotonMoverUnidad;
import Modelo.AlgoChess;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class VentanaJuegoTerminado {
    VBox caja;
    String ganador;
    Stage popWindow;
    Stage faseJuego;

    public VentanaJuegoTerminado(String ganador, Stage faseJuego) {
        this.ganador = ganador;
        this.faseJuego = faseJuego;

        //ArmoVBOX
        this.caja = new VBox();
        this.ubicarTextos();
        caja.setAlignment(Pos.TOP_CENTER);
        caja.setSpacing(20);
        caja.setPadding(new Insets(25));
        this.fijarBotonFin();

        //Armo scene y nuevo stage
        this.popWindow = new Stage();
        popWindow.setScene(new Scene(caja));
        popWindow.showAndWait();
    }



    private void ubicarTextos() {
        Label titulo = new Label("Juego Terminado");
        titulo.setFont(Font.font("Tahoma", FontWeight.BOLD, 18));
        titulo.setTextAlignment(TextAlignment.CENTER);
        titulo.setTextFill(Color.web("000000"));
        Label subtitulo = new Label(String.format("Ganador: %s", ganador));
        subtitulo.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
        subtitulo.setTextAlignment(TextAlignment.CENTER);
        subtitulo.setTextFill(Color.web("000000"));
        caja.getChildren().addAll(titulo, subtitulo);
    }

    private void fijarBotonFin() {
        BotonCerrarJuego botonCerrarJuego = new BotonCerrarJuego(faseJuego, this);

        Button botonCerrar = new Button();
        botonCerrar.setText("Cerrar juego");
        botonCerrar.setStyle("-fx-base: #ff763d;");
        botonCerrar.setPrefSize(120, 30);
        botonCerrar.setOnAction(botonCerrarJuego);

        caja.getChildren().add(botonCerrar);
    }

    public void cerrar() {popWindow.close();}
}
