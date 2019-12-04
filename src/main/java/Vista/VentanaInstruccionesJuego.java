package Vista;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class VentanaInstruccionesJuego {
    private Stage popWindow;
    private Scene escena;
    private VBox caja;

    public VentanaInstruccionesJuego() {
        this.popWindow = new Stage();
        this.caja = new VBox();
        caja.setAlignment(Pos.TOP_CENTER);
        caja.setSpacing(20);
        caja.setPadding(new Insets(25));
        this.ubicarTexto();
        this.fijarBotonCerrar();
        popWindow.setHeight(550);
        popWindow.setWidth(450);
        popWindow.setScene(new Scene(caja));
        popWindow.showAndWait();
    }

    public void fijarBotonCerrar() {
        Button cerrar = new Button();
        cerrar.setText("Cerrar");
        cerrar.setStyle("-fx-base: #ff763d;");
        cerrar.setPrefSize(120, 30);
        cerrar.setAlignment(Pos.CENTER);
        cerrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                popWindow.close();
            }
        });
        caja.getChildren().addAll(cerrar);

    }

    public void ubicarTexto() {
        Label titulo = new Label("Instrucciones de Juego");
        titulo.setFont(Font.font("Tahoma", FontWeight.BOLD, 18));
        titulo.setTextAlignment(TextAlignment.CENTER);
        titulo.setTextFill(Color.web("000000"));

        Label tituloMover = new Label("Mover");
        tituloMover.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
        tituloMover.setTextAlignment(TextAlignment.CENTER);
        tituloMover.setTextFill(Color.web("000000"));

        Text instruccionesMover = new Text();
        instruccionesMover.setWrappingWidth(400);
        instruccionesMover.setText("Para mover una unidad seleccione primero la unidad que desea mover, y luego " +
                "seleccione el casillero al que desea moverla");
        instruccionesMover.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
        instruccionesMover.setTextAlignment(TextAlignment.CENTER);

        Label tituloAtacar = new Label("Atacar");
        tituloAtacar.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
        tituloAtacar.setTextAlignment(TextAlignment.CENTER);
        tituloAtacar.setTextFill(Color.web("000000"));

        Text instruccionesAtacar = new Text();
        instruccionesAtacar.setWrappingWidth(400);
        instruccionesAtacar.setText("Para atacar a una unidad seleccione primero la unidad aliada que desea que ataque, y luego " +
                "seleccione la unidad enemiga que desea atacar");
        instruccionesAtacar.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
        instruccionesAtacar.setTextAlignment(TextAlignment.CENTER);

        Label tituloCurar = new Label("Curar");
        tituloCurar.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
        tituloCurar.setTextAlignment(TextAlignment.CENTER);
        tituloCurar.setTextFill(Color.web("000000"));

        Text instruccionesCurar = new Text();
        instruccionesCurar.setWrappingWidth(400);
        instruccionesCurar.setText("Para curar a una unidad seleccione primero el curandero mas cercano a la unidad que desea curar y luego " +
                "seleccione la unidad aliada que desea curar");
        instruccionesCurar.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
        instruccionesCurar.setTextAlignment(TextAlignment.CENTER);

        caja.getChildren().addAll(titulo, tituloMover, instruccionesMover, tituloAtacar, instruccionesAtacar, tituloCurar, instruccionesCurar);

    }
}
