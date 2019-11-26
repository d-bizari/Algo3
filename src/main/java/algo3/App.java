package algo3;

import Modelo.AlgoChess;
import algo3.Controller.tableroGridPane;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private Stage window;
    private Scene scene1, scene2, scene3, scene4, scene5;
    AlgoChess juego = new AlgoChess(20,20);
    private tableroGridPane tablero;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;

        //Creacion scene: Bienvenida
        scene1 = createSceneBienvenida();

        //Creacion scene: Configuracion juego
        scene2 = createSceneConfiguracion();

        //Creacion scene: JUEGO
        scene3 = jugadorEligeUnidades("1");
        scene4 = jugadorEligeUnidades("2");

        //Crea el Tablero
        scene5 = createSceneJuego();

        //Empieza por la bienvenida, cuando toca el boton va a la configuracion del juego
        //window.setScene(scene5); //para el tablero, si presionas una posicion te lo indica
        window.setScene(scene1);
        window.setTitle("Algoritmos III - TP2");

        scene5.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                AlertBoxEleccionSector.display("Atencion", tablero.clickGrid(event));
                event.consume();
            }
        });
        window.show();


    }


    private Scene jugadorEligeUnidades(String jugador){
        int puntaje = 20;

        String tituloCompleto = String.format("Jugador %s eliga unidades", jugador);
        Label titulo = new Label(tituloCompleto);
        titulo.setFont(Font.font("Tahoma", FontWeight.BOLD, 18));
        titulo.setTextAlignment(TextAlignment.CENTER);
        titulo.setTextFill(Color.web("000000"));

        VBox vista = new VBox(30);

        SelectorUnidades selector = new SelectorUnidades(puntaje);

        vista.getChildren().addAll(titulo, createGridSoldadoInfanteria(), selector.getGrid());
        return new Scene(vista, 2000, 2000);
    }



    private GridPane createGridSoldadoInfanteria() {

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label soldadoInfanteria  = new Label("Soldado Infanteria");
        GridPane.setConstraints(soldadoInfanteria, 1, 1);
        Text text_soldado1 = new Text("Costo: 1");
        Text text_soldado2 = new Text("Atributos: Vida 100 + Daño cuerpo a cuerpo: 10 + Daño a distancia: 0");
        Text text_soldado3 = new Text("Comportamiento: Puede atacar a un enemigo a corta distancia + Si hay más de 3 Soldados contiguos (en cualquier dirección) se comportan como un Batallón y PUEDEN moverse los 3 al mismo tiempo en el mismo turno.");

        GridPane.setConstraints(text_soldado1, 1, 2);
        GridPane.setConstraints(text_soldado2, 1, 3);
        GridPane.setConstraints(text_soldado3, 1, 4);

        grid.getChildren().addAll(soldadoInfanteria, text_soldado1, text_soldado2, text_soldado3);

        return grid;
    }

    private Scene createSceneBienvenida() {
        Label label1 = new Label("Bienvenido a ALGOCHESS!");
        Button button1 = new Button("Jugar");
        button1.setOnAction(e -> {juego= new AlgoChess(20,20); window.setScene(scene2);});
        //Crear layout bienvenida
        VBox layout1 = new VBox(30);
        layout1.getChildren().addAll(label1, button1);
        return new Scene(layout1, 2000, 2000);
    }

    private Scene createSceneConfiguracion() {

        //Creo grilla y pongo tamaño
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        //Para recibir nombres jugadores y sectores1 seleccionados

        //Jugador 1
        Label nombreJugador1 = new Label("Jugador 1:");
        GridPane.setConstraints(nombreJugador1, 5, 0);
        //Nombre ingresado
        TextField nombreIngresado1 = new TextField();
        nombreIngresado1.setPromptText("Ingrese su nombre");
        nombreIngresado1.setEditable(true);
        GridPane.setConstraints(nombreIngresado1, 6, 0);
        //Jugador 2
        Label nombreJugador2 = new Label("Jugador 2:");
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
        play.setOnAction(e -> {
            if(sectores1.getValue().equals(sectores2.getValue())){
                AlertBoxEleccionSector.display("Error", "No pueden elegir el mismo sector del tablero");
            }
            else {
                juego.agregarJugador(nombreIngresado1.getText(), sectores1.getValue());
                juego.agregarJugador(nombreIngresado2.getText(), sectores2.getValue());
                window.setScene(this.scene3);
            }
        });
        GridPane.setConstraints(play, 10, 10);

        //Add everything to grid
        grid.getChildren().addAll(nombreJugador1, nombreIngresado1, nombreJugador2, nombreIngresado2, sectores1, sectores2, play);

        return new Scene(grid, 2000, 2000);
    }

    private Scene createSceneJuego() {
        tablero = new tableroGridPane(juego, 2000, 2000);
        Label label = new Label("GUERRA");
        BorderPane layout = new BorderPane();
        layout.setCenter(tablero.getVisual());
        return new Scene(layout, 2000, 2000);
        
    }


}