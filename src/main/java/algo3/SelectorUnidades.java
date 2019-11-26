package algo3;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class SelectorUnidades {

    private GridPane grid;
    private int puntaje;
    private int cantidadSoldados = 21;
    private Button continuar;

    public SelectorUnidades(int puntos){
        grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        puntaje = puntos;
        continuar = new Button("Continuar");
        continuar.setDisable(true);

        ChoiceBox<Integer> SoldadoInfanteria = createChoiceBox(cantidadSoldados);

        String puntos_ = String.format("puntos: %s", puntaje);

        Text text_soldadoCantidad = new Text("Soldado infanteria:");
        Text text_puntos = new Text(puntos_);
        GridPane.setConstraints(text_puntos, 1, 0);
        GridPane.setConstraints(text_soldadoCantidad, 1, 2);
        GridPane.setConstraints(SoldadoInfanteria, 2, 2);
        GridPane.setConstraints(continuar, 4,4 );

        continuar.setOnAction(e -> {
            //TODO llamar a metodo de algochess para iniciar a todos las unidades.
        });

        choiceBoxAgregarListener(SoldadoInfanteria);

        grid.getChildren().addAll(text_puntos, text_soldadoCantidad, SoldadoInfanteria, continuar);
    }

    private void choiceBoxAgregarListener(ChoiceBox<Integer> choiceBox) {
        choiceBox.getSelectionModel().selectedItemProperty().addListener((v, valorViejo, valorNuevo) -> {
            if(v.getValue() == 20){
                continuar.setDisable(false);
            }else{
                continuar.setDisable(true);
            }
        });
    }

    private ChoiceBox<Integer> createChoiceBox(int cantidad) {
       ChoiceBox choiceBox = new ChoiceBox<Integer>();

        for(int i = 0 ; i<cantidad; i++){
            choiceBox.getItems().add(i);
        }
        return choiceBox;

    }

    public GridPane getGrid(){
        return grid;
    }

}

