package Vista;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class VentanaCaracteristicasUnidades {

    private Stage popWindow;
    private Scene escena;
    private VBox caja;
    private ScrollPane scrollPane;

    public VentanaCaracteristicasUnidades() {
        this.popWindow = new Stage();
        this.caja = new VBox();
        caja.setAlignment(Pos.TOP_CENTER);
        caja.setSpacing(20);
        caja.setPadding(new Insets(25));
        this.ubicarTexto();
        this.fijarBotonCerrar();
        scrollPane = new ScrollPane();
        scrollPane.setContent(caja);
        popWindow.setHeight(650);
        popWindow.setWidth(450);
        popWindow.setScene(new Scene(scrollPane));
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
        Label titulo = new Label("Caracteristicas de Unidades");
        titulo.setFont(Font.font("Tahoma", FontWeight.BOLD, 18));
        titulo.setTextAlignment(TextAlignment.CENTER);
        titulo.setTextFill(Color.web("000000"));

        Label titulosoldado = new Label("Soldado Infanteria");
        titulosoldado.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
        titulosoldado.setTextFill(Color.web("000000"));

        Text soldado = new Text();
        soldado.setWrappingWidth(400);
        soldado.setText("-Vida: 100 \n-Daño cuerpo a cuerpo: 5 \n-Daño a distancia: 0 \n-Costo: 1 \n-Si hay más de 3 Soldados contiguos (en cualquier dirección) se comportan como un Batallón y PUEDEN moverse los 3 al mismo tiempo en el mismo turno." );
        soldado.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
        soldado.setTextAlignment(TextAlignment.LEFT);

        Label titulojinete = new Label("Jinete");
        titulojinete.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
        //titulosoldado.setTextAlignment(TextAlignment.LEFT);
        titulojinete.setTextFill(Color.web("000000"));

        Text jinete = new Text();
        jinete.setWrappingWidth(400);
        jinete.setText("-Vida: 100 \n-Daño cuerpo a cuerpo: 5 \n-Daño a distancia: 15 \n-Costo: 3 \n-Si hay al menos un Soldado de Infantería aliado cerca o no hay ningún enemigo cerca, " +
                "su arma de ataque es un Arco y Flecha y únicamente puede atacar a enemigos en distancia media \n" +
                "-Si no hay ningún aliado cercano y hay enemigos cercanos , su arma de ataque es una Espada y únicamente puede atacar a enemigos en distancia corta." );
        jinete.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
        jinete.setTextAlignment(TextAlignment.LEFT);

        Label titulocurandero = new Label("Curandero");
        titulocurandero.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
        titulocurandero.setTextFill(Color.web("000000"));

        Text curandero = new Text();
        curandero.setWrappingWidth(400);
        curandero.setText("-Vida: 75 \n-Curacion: 15 \n-Costo: 2 \n-Puede curar a una unidad Aliada (menos a la Catapulta) en una distancia cercana." );
        curandero.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
        curandero.setTextAlignment(TextAlignment.LEFT);

        Label titulocatapulta = new Label("Catapulta");
        titulocatapulta.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
        titulocatapulta.setTextFill(Color.web("000000"));

        Text catapulta = new Text();
        catapulta.setWrappingWidth(400);
        catapulta.setText("-Vida: 50 \n-Daño cuerpo a cuerpo: 0 \n-Daño a distancia: 20 \n-Costo: 5 \nNo puede moverse en toda la partida \n" +
                "- Ataca en una distancia lejana únicamente. [Puede dañar tanto a Enemigos como Aliados]\n" +
                "- Causa daño a la primera unidad enemiga alcanzada, y a todas las unidades directamente contiguas, " +
                "y si a su vez la segunda unidad tiene otra unidad contigua, también causa el mismo daño (y así sucesivamente)" );
        catapulta.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
        catapulta.setTextAlignment(TextAlignment.LEFT);

        caja.getChildren().addAll(titulo, titulosoldado, soldado, titulojinete, jinete, titulocurandero, curandero, titulocatapulta, catapulta);


    }
}

