package Vista;

import Controlador.BotonSeleccionarUnidades;
import Controlador.CheckBoxSelectorUnidades;
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

    public SelectorUnidades(String nombreJugador){
        popWidow = new Stage();
        popWidow.setTitle(String.format("%s seleccione la unidad que desea", nombreJugador));
        popWidow.setMinWidth(250);

        grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        //Crea boton de continuar.
        continuar = new Button("Continuar");
        BotonSeleccionarUnidades continuarEventHandler =  new BotonSeleccionarUnidades(this,continuar);
        continuar.setDisable(true);

        //Crea los choice box para elegir cantidad de unidades.
        group = new ToggleGroup();

        soldadoInfanteria = new RadioButton();
        jinete            = new RadioButton();
        catapulta         = new RadioButton();
        curandero         = new RadioButton();

        //Agrego al togglegroup
        soldadoInfanteria.setSelected(true);
        soldadoInfanteria.setToggleGroup(group);
        jinete.setToggleGroup(group);
        catapulta.setToggleGroup(group);
        curandero.setToggleGroup(group);

        //crea texto que se muestra al lado de cada choice box.
        Text text_soldadoCantidad   = new Text("Soldado infanteria:");
        Text text_jineteCantidad    = new Text("Jinete:");
        Text text_catapultaCantidad = new Text("Catapulta:");
        Text text_curanderoCantidad = new Text("Curandero:");

        textPuntos = new Text(String.format("Puntos: %s", puntaje));

        //Acomoda los textos y los choice box en el GridPane.
        GridPane.setConstraints(textPuntos, 1, 0);
        GridPane.setConstraints(text_soldadoCantidad, 1, 2);
        GridPane.setConstraints(soldadoInfanteria, 2, 2);
        GridPane.setConstraints(text_jineteCantidad, 1, 3);
        GridPane.setConstraints(jinete, 2, 3);
        GridPane.setConstraints(text_catapultaCantidad, 1, 4);
        GridPane.setConstraints(catapulta, 2, 4);
        GridPane.setConstraints(text_curanderoCantidad, 1, 5);
        GridPane.setConstraints(curandero, 2, 5);
        GridPane.setConstraints(continuar, 6,6 );

        group.selectedToggleProperty().addListener(new CheckBoxSelectorUnidades(this));

        grid.getChildren().addAll(textPuntos, text_soldadoCantidad, soldadoInfanteria, text_jineteCantidad, jinete, text_catapultaCantidad, catapulta, text_curanderoCantidad, curandero, continuar);

        continuar.setOnAction(continuarEventHandler);

        Scene vista = new Scene(grid);
        popWidow.setScene(vista);
        popWidow.showAndWait();
    }

    public void updatePuntaje(int puntajeTotal){
        textPuntos.setText(String.format("Puntos: %d", puntaje - puntajeTotal));
    }

    public void habilitarBotonContinuar(boolean opt){
        continuar.setDisable(!opt); //Si llega true, queda en estado habilitado, en caso de false deshabilitado
    }

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

    public void puntajeNegativo(boolean b) {
        if(b)
            textPuntos.setFill(Color.RED);
        else
            textPuntos.setFill(Color.BLACK);
    }
}

