package Vista;

import Controlador.BotonSeleccionarUnidades;
import Controlador.ChoiceBoxSelectorUnidades;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
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
    private ChoiceBox soldadoInfanteria;
    private ChoiceBox jinete;
    private ChoiceBox catapulta;
    private ChoiceBox curandero;
    private Button continuar;
    private Text textPuntos;

    public SelectorUnidades(TextField nombreJugador){
        puntaje = 20;
        cantidadSoldados = 21;
        cantidadJinetes = 7;
        cantidadCatapulta = 5;
        cantidadCurandero = 11;

        popWidow = new Stage();
        popWidow.setTitle(String.format("%s seleccione la cantidad de unidades que desea", nombreJugador.getText()));
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
        soldadoInfanteria = createChoiceBox(cantidadSoldados);
        jinete            = createChoiceBox(cantidadJinetes);
        catapulta         = createChoiceBox(cantidadCatapulta);
        curandero         = createChoiceBox(cantidadCurandero);

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

        //Crea listeners para los choice box.
        ChoiceBoxListener(soldadoInfanteria);
        ChoiceBoxListener(jinete);
        ChoiceBoxListener(catapulta);
        ChoiceBoxListener(curandero);

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

    public void ChoiceBoxListener(ChoiceBox<Integer> choiceBox) {
        ChoiceBoxSelectorUnidades selectorListener = new ChoiceBoxSelectorUnidades(this);
        choiceBox.getSelectionModel().selectedItemProperty().addListener((v, valorViejo, valorNuevo) -> {
            selectorListener.onChange();
        });
    }

    public void cerrarVentana(){
        popWidow.close();
    }

    public int getValueSoldadoInfanteria(){
        if (soldadoInfanteria.getValue() == null)
            return -1;
        return (int)soldadoInfanteria.getValue();
    }
    public int getValueJinete(){
        if (jinete.getValue() == null)
            return -1;
        return (int)jinete.getValue();
    }
    public int getValueCatapulta(){
        if (catapulta.getValue() == null)
            return -1;
        return (int)catapulta.getValue();
    }
    public int getValueCurandero(){
        if (curandero.getValue() == null)
            return -1;
        return (int)curandero.getValue();
    }

    private static ChoiceBox<Integer> createChoiceBox(int cantidad) {
        ChoiceBox choiceBox = new ChoiceBox<Integer>();

        for(int i = 0 ; i<cantidad; i++){
            choiceBox.getItems().add(i);
        }
        return choiceBox;
    }

    public void puntajeNegativo(boolean b) {
        if(b)
            textPuntos.setFill(Color.RED);
        else
            textPuntos.setFill(Color.BLACK);
    }
}

