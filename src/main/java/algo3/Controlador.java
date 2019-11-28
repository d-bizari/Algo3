package algo3;

import Excepciones.CeldaDeTerritorioEnemigo;
import Excepciones.CeldaOcupada;
import Excepciones.CoordenadaFueraDeRango;
import Excepciones.PuntosInsuficientesException;
import Modelo.AlgoChess;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.scene.input.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;


public class Controlador {

    private Stage stage;
    private AlgoChess juego;
    private TableroGridPane tablero;
    private TextField jugador1;
    private TextField jugador2;
    private int sectorJugador1;
    private int sectorJugador2;
    private HashMap<String, Integer> hashUnidadesJugador1;
    private HashMap<String, Integer> hashUnidadesJugador2;
    private GridPane cantidadUnidadesJugador1;
    private GridPane cantidadUnidadesJugador2;
    private ArrayList<Integer> coordenadas;



    public Controlador(Stage escena, AlgoChess juego, TextField NombreJugador1, TextField NombreJugador2, int sector1, int sector2){
        this.stage = escena;
        this.juego = juego;
        this.tablero = new TableroGridPane(juego, 20, 20);
        this.jugador1 = NombreJugador1;
        this.jugador2 = NombreJugador2;
        this.sectorJugador1 = sector1;
        this.sectorJugador2 = sector2;

    }

    private GridPane crearGridCantidadUnidades(String jugador, HashMap<String, Integer> hash, Button continuar) {

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        //crea texto que se muestra al lado de cada choice box.
        String titulo = String.format("Jugador %s posee estas unidades, ubique sus fichas", jugador);
        Text text_jugador = new Text(titulo);
        Text text_soldadoCantidad = new Text("Soldado infanteria:");
        Text text_jineteCantidad = new Text("Jinete:");
        Text text_catapultaCantidad = new Text("Catapulta:");
        Text text_curanderoCantidad = new Text("Curandero:");

        String soldado = hash.get("Soldado Infanteria").toString();
        String jinete = hash.get("Jinete").toString();
        String catapulta = hash.get("Catapulta").toString();
        String curandero = hash.get("Curandero").toString();
        Text _soldado = new Text(soldado);
        Text _jinete = new Text(jinete);
        Text _catapulta = new Text(catapulta);
        Text _curandero = new Text(curandero);


        GridPane.setConstraints(text_jugador, 1, 0);

        GridPane.setConstraints(text_soldadoCantidad, 1, 1);
        GridPane.setConstraints(_soldado, 2, 1);

        GridPane.setConstraints(text_jineteCantidad, 1, 3);
        GridPane.setConstraints(_jinete, 2, 3);

        GridPane.setConstraints(text_catapultaCantidad, 1, 4);
        GridPane.setConstraints(_catapulta, 2, 4);

        GridPane.setConstraints(text_curanderoCantidad, 1, 5);
        GridPane.setConstraints(_curandero, 2, 5);

        GridPane.setConstraints(continuar, 2,6);

        grid.getChildren().addAll(text_jugador, text_soldadoCantidad, _soldado, text_jineteCantidad, _jinete, text_catapultaCantidad, _catapulta, text_curanderoCantidad, _curandero, continuar);

        return grid;
    }

    public void mostrar(Stage stage) throws PuntosInsuficientesException, CoordenadaFueraDeRango, CeldaDeTerritorioEnemigo, CeldaOcupada{
        Scene escenaJuego = new Scene(tablero.getVisual(), 640, 480);
        stage.setScene(escenaJuego);
        stage.setFullScreenExitHint("");
        stage.setFullScreen(true);

        hashUnidadesJugador1 = SelectorUnidades.display(jugador1);
        hashUnidadesJugador2 = SelectorUnidades.display(jugador2);

        irAUbicarUnidades(stage, escenaJuego);
    }

    private void irAUbicarUnidades(Stage stage, Scene scene) throws PuntosInsuficientesException, CoordenadaFueraDeRango, CeldaDeTerritorioEnemigo, CeldaOcupada  {

        //BotonUbicacionJugador1 botonUbicacion1 = new BotonUbicacionJugador1(hashUnidadesJugador1, juego, jugador1, tablero);
        //botonUbicacion1.setDisable(false);

        Button botonUbicacion1 = new Button();
        botonUbicacion1.setDisable(false);

        Button botonUbicacion2 = new Button();
        botonUbicacion2.setDisable(true);

        cantidadUnidadesJugador1 = crearGridCantidadUnidades(jugador1.getText() , hashUnidadesJugador1, botonUbicacion1);
        cantidadUnidadesJugador2 = crearGridCantidadUnidades(jugador2.getText() , hashUnidadesJugador2, botonUbicacion2);

        scene.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                tablero.clickGrid(event);
                Integer col = tablero.colIndex;
                AlertBoxEleccionSector.display("Atencion", "hola");
                event.consume();
            };
        });

        HBox caja = new HBox(cantidadUnidadesJugador1, tablero.getVisual(), cantidadUnidadesJugador2);
        Scene escenaUbicarUnidades = new Scene(caja, 640, 480);
        stage.setScene(escenaUbicarUnidades);
    }

}
