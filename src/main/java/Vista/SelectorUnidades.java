package Vista;

import Controlador.BotonSeleccionarUnidades;
import Controlador.RadioButtonSelectorUnidades;
import Modelo.AlgoChess;
import Modelo.Coordenada;
import algo3.AlertBox;
import algo3.TableroGridPane;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SelectorUnidades{
    private static GridPane grid;
    private Stage popWidow;
    private int puntaje;
    private int cantidadSoldados;
    private int cantidadJinetes;
    private int cantidadCatapulta;
    private int cantidadCurandero;
    private RadioButton soldadoInfanteria;
    private RadioButton jinete;
    private RadioButton catapulta;
    private RadioButton curandero;
    private Button continuar;
    final ToggleGroup group;
    private Text textPuntos;
    private AlgoChess juego;
    private String nombreJugador;

    public SelectorUnidades(String nombreJugador, AlgoChess juego, Coordenada coordenada, TableroGridPane tablero){
        popWidow = new Stage();
        popWidow.setTitle("Selector de Unidad");
        popWidow.setMinWidth(300);
        popWidow.setMinHeight(250);
        this.juego = juego;
        this.nombreJugador = nombreJugador;

        grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        //Crea boton de continuar.
        continuar = new Button("Continuar");

        //Crea los radio button para elegir cantidad de unidades.
        group = new ToggleGroup();

        soldadoInfanteria = new RadioButton("Soldado Infanteria");
        jinete            = new RadioButton("Jinete");
        catapulta         = new RadioButton("Catapulta");
        curandero         = new RadioButton("Curandero");

        //Agrego al togglegroup
        soldadoInfanteria.setSelected(true);
        soldadoInfanteria.setToggleGroup(group);
        jinete.setToggleGroup(group);
        catapulta.setToggleGroup(group);
        curandero.setToggleGroup(group);

        Text titulo = new Text("Seleccione la unidad que desee colocar");
        textPuntos = new Text(String.format("Puntos disponibles: %d",juego.getPuntosDe(nombreJugador)));
        textPuntos.setFill(Color.GREEN);

        //Acomoda los textos y los choice box en el GridPane.
        GridPane.setConstraints(titulo, 1, 0);
        GridPane.setConstraints(textPuntos,4,0);
        GridPane.setConstraints(soldadoInfanteria, 1, 3);
        GridPane.setConstraints(jinete, 1, 4);
        GridPane.setConstraints(catapulta, 1, 5);
        GridPane.setConstraints(curandero, 1, 6);
        GridPane.setConstraints(continuar, 4,9 );

        group.selectedToggleProperty().addListener(new RadioButtonSelectorUnidades(this));

        grid.getChildren().addAll(titulo, textPuntos,soldadoInfanteria, jinete, catapulta, curandero, continuar);

        continuar.setOnAction(new BotonSeleccionarUnidades(this, continuar, juego, coordenada, nombreJugador, tablero));

        Scene vista = new Scene(grid);
        popWidow.setScene(vista);
        popWidow.showAndWait();
    }

    public void updatePuntaje(int puntos){
        textPuntos.setText(String.format("Puntos disponibles: %d",juego.getPuntosDe(nombreJugador) - puntos - 1));
    }

    /*public void habilitarBotonContinuar(boolean opt){
        continuar.setDisable(!opt); //Si llega true, queda en estado habilitado, en caso de false deshabilitado
    }*/

    public void cerrarVentana(){
        popWidow.close();
    }

    public boolean getValueSoldadoInfanteria(){
         return soldadoInfanteria.isSelected();
    }
    public boolean getValueJinete(){
        return jinete.isSelected();
    }
    public boolean getValueCatapulta(){
        return catapulta.isSelected();
    }
    public boolean getValueCurandero(){
        return curandero.isSelected();
    }

    public String obtenerSeleccionado() {
        RadioButton seleccionado = (RadioButton) group.getSelectedToggle();
        return seleccionado.getText();
    }

    public void puntajeNegativo(int puntos) {
        if(juego.getPuntosDe(nombreJugador) - puntos < 0)
            textPuntos.setFill(Color.RED);
        else
            textPuntos.setFill(Color.GREEN);
    }
}

