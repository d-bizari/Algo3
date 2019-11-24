package algo3;

import Modelo.AlgoChess;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private Stage window;
    private Scene scene1, scene2, scene3;
    AlgoChess juego;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;

        //Creacion scene: Bienvenida
        Label label1 = new Label("Bienvenido a ALGOCHESS!");
        Button button1 = new Button("Jugar");
        button1.setOnAction(e -> {juego= new AlgoChess(20,20); window.setScene(scene2);});
        //Crear layout bienvenida
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, button1);
        scene1 = new Scene(layout1, 2000, 2000);

        //Creacion scene: Configuracion juego
        scene2 = createSceneConfiguracion();

        //Creacion scene: JUEGO
        scene3 = createSceneJuego(); //TODO scene juego

        //Empieza por la bienvenida, cuando toca el boton va a la configuracion del juego
        window.setScene(scene1);
        window.setTitle("Title Here");
        window.show();
    }


    private Scene createSceneConfiguracion() {

        //Creo grilla y pongo tamaño
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        //Para recibir nombres jugadores y sectores1 seleccionados

        //Jugador 1
        Label nombreJugador1 = new Label("Nombre:");
        GridPane.setConstraints(nombreJugador1, 5, 0);
        //Nombre ingresado
        TextField nombreIngresado1 = new TextField();
        nombreIngresado1.setPromptText("Ingrese su nombre");
        nombreIngresado1.setEditable(true);
        GridPane.setConstraints(nombreIngresado1, 6, 0);
        //Jugador 2
        Label nombreJugador2 = new Label("Nombre:");
        GridPane.setConstraints(nombreJugador2, 5, 4);
        //Nombre Ingresado
        TextField nombreIngresado2 = new TextField();
        nombreIngresado2.setPromptText("Ingrese su nombre");
        nombreIngresado2.setEditable(true);
        GridPane.setConstraints(nombreIngresado2, 6, 4);

        //Eleccion de sector
        ChoiceBox<Integer> sectores1 = new ChoiceBox<Integer>();
        sectores1.getItems().add(1);
        sectores1.getItems().add(2);
        sectores1.setValue(1);
        //Jugador 1
        Label sectorJugador1 = new Label("Sector:");
        GridPane.setConstraints(sectorJugador1, 5, 1);
        //Sector ingresado
        GridPane.setConstraints(sectores1, 6, 1);

        //Eleccion de sector
        ChoiceBox<Integer> sectores2 = new ChoiceBox<Integer>();
        sectores2.getItems().add(1);
        sectores2.getItems().add(2);
        sectores2.setValue(1);
        //Jugador 2
        Label sectorJugador2 = new Label("Sector:");
        GridPane.setConstraints(sectorJugador2, 5, 5);
        //Sector ingresado
        GridPane.setConstraints(sectores2, 6, 5);

        //Play
        //Cuando toca el boton pasa a la scene del juego
        Button play = new Button("Comenzar a jugar!");
        play.setOnAction(e -> { juego.agregarJugador(nombreIngresado1.getText(), sectores1.getValue()); juego.agregarJugador(nombreIngresado2.getText(), sectores2.getValue()); window.setScene(this.scene3);});
        GridPane.setConstraints(play, 10, 10);

        //Add everything to grid
        grid.getChildren().addAll(nombreJugador1, nombreIngresado1, nombreJugador2, nombreIngresado2, sectores1, sectores2, play);

        return new Scene(grid, 2000, 2000);
    }

    private Scene createSceneJuego() {
        Label label = new Label("GUERRA");
        VBox layout = new VBox(20);
        layout.getChildren().addAll(label);
        return new Scene(layout, 2000, 2000);
    }



}