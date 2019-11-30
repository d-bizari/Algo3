package algo3;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.ArrayList;
import java.util.HashMap;

public class SelectorUnidades{

    static GridPane grid;
    static int puntaje = 20;
    static int cantidadSoldados = 21;
    static int cantidadJinetes = 7;
    static int cantidadCatapulta = 5;
    static int cantidadCurandero = 11;
    static ChoiceBox soldadoInfanteria;
    static ChoiceBox jinete;
    static ChoiceBox catapulta;
    static ChoiceBox curandero;
    static Button continuar;
    static Text textPuntos;

    public static HashMap<String, Integer> display(TextField nombreJugador){

        HashMap<String, Integer> unidadesSeleccionadas = new HashMap<String, Integer>();

        Stage popWidow = new Stage();
        String titulo = String.format("Jugador %s seleccione la cantidad de unidades que desea", nombreJugador.getText());
        popWidow.setTitle(titulo);
        popWidow.setMinWidth(250);

        grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        //Crea boton de continuar.
        continuar = new Button("Continuar");
        continuar.setDisable(true);

        //Crea los choice box para elegir cantidad de unidades.
        soldadoInfanteria = createChoiceBox(cantidadSoldados);
        jinete = createChoiceBox(cantidadJinetes);
        catapulta = createChoiceBox(cantidadCatapulta);
        curandero = createChoiceBox(cantidadCurandero);

        //crea texto que se muestra al lado de cada choice box.
        Text text_soldadoCantidad = new Text("Soldado infanteria:");
        Text text_jineteCantidad = new Text("Jinete:");
        Text text_catapultaCantidad = new Text("Catapulta:");
        Text text_curanderoCantidad = new Text("Curandero:");

        String puntos_ = String.format("Puntos: %s", puntaje);
        textPuntos = new Text(puntos_); //FIXME

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

        //Crea listeners para los choice box.
        ChoiceBoxListener(soldadoInfanteria);
        ChoiceBoxListener(jinete);
        ChoiceBoxListener(catapulta);
        ChoiceBoxListener(curandero);

        grid.getChildren().addAll(textPuntos, text_soldadoCantidad, soldadoInfanteria, text_jineteCantidad, jinete, text_catapultaCantidad, catapulta, text_curanderoCantidad, curandero, continuar);

        continuar.setOnAction(e -> {
            unidadesSeleccionadas.put("Soldado Infanteria", (Integer) soldadoInfanteria.getValue());
            unidadesSeleccionadas.put("Jinete", (Integer) jinete.getValue());
            unidadesSeleccionadas.put("Catapulta", (Integer) catapulta.getValue());
            unidadesSeleccionadas.put("Curandero", (Integer) curandero.getValue());
            popWidow.close();
        });

        Scene vista = new Scene(grid);
        popWidow.setScene(vista);
        popWidow.showAndWait();

        return unidadesSeleccionadas;
    }

    private static void updatePuntaje(int puntaje){
        textPuntos.setText(String.format("Puntos: %d", puntaje));
    }

    public static void ChoiceBoxListener(ChoiceBox<Integer> choiceBox) {
        choiceBox.getSelectionModel().selectedItemProperty().addListener((v, valorViejo, valorNuevo) -> {
            onChoiceBoxChange();
        });
    }

    private static void onChoiceBoxChange(){
        int condicion = 0;

        if (soldadoInfanteria != null){
            condicion = (int)soldadoInfanteria.getValue() + condicion;
        }

        if(jinete.getValue() != null){
            condicion = (int)jinete.getValue() * 3 + condicion;
        }

        if(catapulta.getValue() != null){
            condicion = (int)catapulta.getValue() * 5 + condicion;
        }

        if(curandero.getValue() != null){
            condicion = (int)curandero.getValue() * 2 + condicion;
        }
        updatePuntaje(puntaje - condicion);
        if(condicion == 20){
            continuar.setDisable(false);
        }else{
            continuar.setDisable(true);
        }
    }

    private static ChoiceBox<Integer> createChoiceBox(int cantidad) {
       ChoiceBox choiceBox = new ChoiceBox<Integer>();

        for(int i = 0 ; i<cantidad; i++){
            choiceBox.getItems().add(i);
        }
        return choiceBox;
    }

}

