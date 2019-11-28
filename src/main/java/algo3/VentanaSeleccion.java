package algo3;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;

public class VentanaSeleccion {

    public static String display(TextField nombreJugador){

        Stage popWidow = new Stage();
        String titulo = "Seleccione que unidad desea acomodar";
        popWidow.setTitle(titulo);
        popWidow.setMinWidth(250);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        //Crea boton de continuar.
        Button ok = new Button("OK");
        Button volver = new Button("Volver");

        //Crea los choice box para elegir cantidad de unidades.
        CheckBox soldadoInfanteria = new CheckBox("Soldado Infanteria");
        CheckBox jinete = new CheckBox("Jinete");
        CheckBox catapulta = new CheckBox("Catapulta");
        CheckBox curandero = new CheckBox("Curandero");

        GridPane.setConstraints(soldadoInfanteria, 2, 2);

        GridPane.setConstraints(jinete, 2, 3);

        GridPane.setConstraints(catapulta, 2, 4);

        GridPane.setConstraints(curandero, 2, 5);

        GridPane.setConstraints(ok, 6,6 );
        GridPane.setConstraints(volver, 7,6 );

        String soldado = "Soldado Infanteria";
        String jin = "Jinete";
        String cata = "Catapulta";
        String curan = "Curandero";

        ok.setOnAction(e -> { popWidow.close(); });

        volver.setOnAction(e -> {
            popWidow.close();
        });


        if(soldadoInfanteria.isSelected()){
            return soldado;
        }
        if(jinete.isSelected()){
            return jin;
        }
        if(catapulta.isSelected()){
            return cata;
        }
        if(curandero.isSelected()){
            return curan;
        }
        return null;
    }
}
