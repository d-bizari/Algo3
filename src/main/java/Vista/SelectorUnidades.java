package Vista;

import Controlador.BotonSeleccionarUnidades;
import Controlador.CheckBoxSelectorUnidades;
import Modelo.AlgoChess;
import Modelo.Coordenada;
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

    public SelectorUnidades(String nombreJugador, AlgoChess juego, Coordenada coordenada, TableroGridPane tablero){
        popWidow = new Stage();
        popWidow.setTitle("Selector de Unidad");
        popWidow.setMinWidth(300);
        popWidow.setMinHeight(250);

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

        //textPuntos = new Text(String.format("Puntos: %s", puntaje));
        textPuntos = new Text("Seleccione la unidad que desee colocar");

        //Acomoda los textos y los choice box en el GridPane.
        GridPane.setConstraints(textPuntos, 1, 0);
        GridPane.setConstraints(soldadoInfanteria, 1, 2);
        GridPane.setConstraints(jinete, 1, 3);
        GridPane.setConstraints(catapulta, 1, 4);
        GridPane.setConstraints(curandero, 1, 5);
        GridPane.setConstraints(continuar, 2,10 );

        group.selectedToggleProperty().addListener(new CheckBoxSelectorUnidades(this));

        grid.getChildren().addAll(textPuntos, soldadoInfanteria, jinete, catapulta, curandero, continuar);

        continuar.setOnAction(new BotonSeleccionarUnidades(this, continuar, juego, coordenada, nombreJugador, obtenerSeleccionado(), tablero));

        Scene vista = new Scene(grid);
        popWidow.setScene(vista);
        popWidow.showAndWait();
    }

    public void updatePuntaje(int puntajeTotal){
        //textPuntos.setText(String.format("Puntos: %d", puntaje - puntajeTotal));
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

    public void puntajeNegativo(boolean b) {
        if(b)
            textPuntos.setFill(Color.RED);
        else
            textPuntos.setFill(Color.BLACK);
    }
}

