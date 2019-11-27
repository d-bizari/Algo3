package algo3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.ArrayList;

public class SelectorUnidades{

    private GridPane grid;
    private int puntaje = 20;
    private int cantidadSoldados = 21;
    private int cantidadJinetes = 7;
    private int cantidadCatapulta = 5;
    private int cantidadCurandero = 11;
    private ChoiceBox soldadoInfanteria;
    private ChoiceBox jinete;
    private ChoiceBox catapulta;
    private ChoiceBox curandero;
    private Button continuar;

    public SelectorUnidades (Boolean pasarAtablero, Stage window, Scene scene4, Scene scene5){
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

        String puntos_ = String.format("puntos: %s", puntaje);
        Text text_puntos = new Text(puntos_);

        //Acomoda los textos y los choice box en el GridPane.
        GridPane.setConstraints(text_puntos, 1, 0);

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
        ChoiceBoxListener(soldadoInfanteria, pasarAtablero, window, scene4, scene5);
        ChoiceBoxListener(jinete, pasarAtablero, window, scene4, scene5);
        ChoiceBoxListener(catapulta, pasarAtablero, window, scene4, scene5);
        ChoiceBoxListener(curandero, pasarAtablero, window, scene4, scene5);

        grid.getChildren().addAll(text_puntos, text_soldadoCantidad, soldadoInfanteria, text_jineteCantidad, jinete, text_catapultaCantidad, catapulta, text_curanderoCantidad, curandero, continuar);

        continuar.setOnAction(e -> {
            if(pasarAtablero){ //TODO QUE PASE DE ESCENA.
                window.setScene(scene5);
            }
            window.setScene(scene4);
        });
    }
    private ArrayList<Integer> unidadesDeJugador(){
        ArrayList<Integer> unidades = new ArrayList<Integer>();
        unidades.add((Integer)soldadoInfanteria.getValue());
        unidades.add((Integer)jinete.getValue());
        unidades.add((Integer)catapulta.getValue());
        unidades.add((Integer)curandero.getValue());
        return unidades;
    }

    private void onChoiceBoxChange(Boolean pasarAtablero, Stage window, Scene scene4, Scene scene5){
        int condicion = (int)soldadoInfanteria.getValue() + (int)jinete.getValue() * 3 + (int)catapulta.getValue() * 5 + (int)curandero.getValue() * 2;
        if(condicion == 20){
            continuar.setDisable(false);
        }else{
            continuar.setDisable(true);
        }
    }

    public void ChoiceBoxListener(ChoiceBox<Integer> choiceBox, Boolean pasarAtablero, Stage window, Scene scene4, Scene scene5) {
        choiceBox.getSelectionModel().selectedItemProperty().addListener((v, valorViejo, valorNuevo) -> {
            onChoiceBoxChange(pasarAtablero, window, scene4, scene5);
        });
    }

    private ChoiceBox<Integer> createChoiceBox(int cantidad) {
       ChoiceBox choiceBox = new ChoiceBox<Integer>();

        for(int i = 0 ; i<cantidad; i++){
            choiceBox.getItems().add(i);
        }
        return choiceBox;
    }

    public GridPane getGrid(){ return grid; }

}

