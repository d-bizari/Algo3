package algo3;


import Modelo.AlgoChess;
import Modelo.Coordenada;
import Vista.AlertBox;
import Vista.TableroGridPane;
import Vista.VentanaCaracteristicasUnidades;
import Vista.VentanaInstruccionesSeleccionUnidades;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class FaseSeleccionUnidades {

    private final Stage stage;
    private final AlgoChess juego;
    private String nombreJugador1;
    private String nombreJugador2;
    private TableroGridPane tablero;
    private String jugadorEnTurno;
    public static double width;
    public static double heigth;
    private Scene scene;
    private BorderPane borderPane;

    public FaseSeleccionUnidades(Stage stage, AlgoChess juego, String jugador1, String jugador2) {
        this.stage = stage;
        this.juego = juego;
        this.nombreJugador1 = jugador1;
        this.nombreJugador2 = jugador2;
        this.width = Screen.getPrimary().getVisualBounds().getWidth() * 0.9;
        this.heigth = Screen.getPrimary().getVisualBounds().getHeight() * 0.9;
        this.tablero = new TableroGridPane(juego, width,heigth);
        this.jugadorEnTurno = this.nombreJugador1;
        this.crearScene();

    }


    public void mostrar() {
        borderPane.getCenter().addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event){
                Label turno = new Label(jugadorEnTurno);
                Coordenada coordenada = tablero.clickGrid(event);
                    try{
                        if(juego.getCelda(coordenada.getCoordenadaX(),coordenada.getCoordenadaY()).estaOcupada()){
                            AlertBox.display("Aviso - No se puede colocar", "Celda ocupada");
                            return;
                        }
                        if(!juego.getCelda(coordenada.getCoordenadaX(),coordenada.getCoordenadaY()).esDeSectorAliado(juego.getJugador(jugadorEnTurno))){
                            AlertBox.display("Aviso - No se puede colocar", "Territorio enemigo");
                            return;
                        }
                    }catch (Exception e){
                        AlertBox.display("Error - Fuera de rango", "Fuera de rango");
                    }

                    new Vista.SelectorUnidades(jugadorEnTurno, juego, coordenada, tablero);
                    cambiarTurno();
                if (jugadorEnTurno.equals("PASAR_ETAPA")) {
                    borderPane.getCenter().removeEventFilter(MouseEvent.MOUSE_PRESSED, this);
                    FaseJuego fase2  = new FaseJuego(stage, juego, nombreJugador1, nombreJugador2, tablero);
                    fase2.mostrar();
                }

                event.consume();
            }
        });

        stage.setScene(scene);
        stage.setWidth(width);
        stage.setHeight(heigth);
    }

    public void cambiarTurno() {
        if ((this.juego).getPuntosRestantes(this.jugadorEnTurno) == 0 ) {
            if (this.jugadorEnTurno.equals(nombreJugador1)) {
                this.jugadorEnTurno = nombreJugador2;
            } else {
                this.jugadorEnTurno = "PASAR_ETAPA";
            }
        }
    }

    public void crearScene() {borderPane = new BorderPane();
        //Construccion boton caracteristicas
        Button botonCaracteristicas = new Button();
        botonCaracteristicas.setText("Unidades");
        botonCaracteristicas.setStyle("-fx-base: #ff763d;");
        botonCaracteristicas.setMinSize(150, 40);
        botonCaracteristicas.setWrapText(true);
        botonCaracteristicas.setAlignment(Pos.CENTER);
        botonCaracteristicas.setOnAction(actionEvent -> new VentanaCaracteristicasUnidades());

        //Construccion boton instrucciones
        Button botonInstrucciones = new Button();
        botonInstrucciones.setText("Instrucciones");
        botonInstrucciones.setStyle("-fx-base: #ff763d;");
        botonInstrucciones.setMinSize(150, 40);
        botonInstrucciones.setAlignment(Pos.CENTER);
        botonInstrucciones.setOnAction(actionEvent -> new VentanaInstruccionesSeleccionUnidades());

        VBox caja = new VBox();
        caja.setAlignment(Pos.BASELINE_CENTER);
        caja.setSpacing(20);
        caja.setPadding(new Insets(25));
        caja.setPrefWidth(155);
        caja.getChildren().addAll(botonInstrucciones, botonCaracteristicas);

        //Escena
        borderPane.setLeft(caja);
        borderPane.setCenter(tablero.getVisual());

        this.scene = new Scene(borderPane);


    }

}