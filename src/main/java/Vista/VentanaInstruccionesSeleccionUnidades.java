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

public class VentanaInstruccionesSeleccionUnidades {
    private Stage popWindow;
    private Scene escena;
    private VBox caja;

    public VentanaInstruccionesSeleccionUnidades() {
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

    public void ubicarTexto() {
        Label titulo = new Label("Instrucciones Seleccion de Unidades");
        titulo.setFont(Font.font("Tahoma", FontWeight.BOLD, 18));
        titulo.setTextAlignment(TextAlignment.CENTER);
        titulo.setTextFill(Color.web("000000"));

        Text instrucciones = new Text();
        instrucciones.setWrappingWidth(400);
        instrucciones.setText("Seleccione el casillero donde desea ubicar la unidad y luego seleccione que unidad desea comprar");
        instrucciones.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
        instrucciones.setTextAlignment(TextAlignment.CENTER);

        caja.getChildren().addAll(titulo, instrucciones);

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
}

