package algo3;

import Modelo.AlgoChess;
import Vista.ContenedorInicial;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
    AlgoChess juego;
    private TableroGridPane tablero;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) {
        stage.setTitle("AlgoChess");

        juego = new AlgoChess(20,20);

        ContenedorInicial contenedorInicial = new ContenedorInicial(stage, juego);
        Scene escenaInicial = new Scene(contenedorInicial, 640, 480);

        stage.setScene(escenaInicial);
        stage.setFullScreen(false);

        stage.show();
    }

    /*//Creacion scene: Bienvenida
        scene1 = createSceneBienvenida();

        //Creacion scene: Configuracion juego
        scene2 = createSceneConfiguracion();

        //Creacion scene: JUEGO
        scene3 = jugadorEligeUnidades("1", false);
        scene4 = jugadorEligeUnidades("2", true);

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



        private Scene jugadorEligeUnidades(String jugador, Boolean pasarAtablero){
        int puntaje = 20;

        String tituloCompleto = String.format("Jugador %s eliga unidades", jugador);
        Label titulo = new Label(tituloCompleto);
        titulo.setFont(Font.font("Tahoma", FontWeight.BOLD, 18));
        titulo.setTextAlignment(TextAlignment.CENTER);
        titulo.setTextFill(Color.web("000000"));

        VBox vista = new VBox(30);

        SelectorUnidades selector = new SelectorUnidades(pasarAtablero, window, scene3, scene4, scene5);

        PropiedadUnidad gridSoldado = new PropiedadUnidad();
        PropiedadUnidad gridJinete = new PropiedadUnidad();
        PropiedadUnidad gridCatapulta = new PropiedadUnidad();
        PropiedadUnidad gridCuradero = new PropiedadUnidad();

        vista.getChildren().addAll(titulo, gridSoldado.gridSoldadoInfanteria(), gridJinete.gridJinete(), gridCatapulta.gridCatapulta(), gridCuradero.gridCurandero(), selector.getGrid());

        return new Scene(vista, 2000, 2000);
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
*/
}