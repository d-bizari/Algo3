package Controlador;

import Vista.SelectorUnidades;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.HashMap;

public class BotonSeleccionarUnidades implements EventHandler<ActionEvent>{
    private SelectorUnidades vista;
    private Button b;

    public BotonSeleccionarUnidades(SelectorUnidades vista,Button b){
        this.vista = vista;
        this.b = b;
    }

    @Override
    public void handle(ActionEvent event){
        HashMap<String, Integer> unidadesSeleccionadas = new HashMap<String, Integer>();

        /*unidadesSeleccionadas.put("Soldado Infanteria", vista.getValueSoldadoInfanteria());
        unidadesSeleccionadas.put("Jinete", vista.getValueJinete());
        unidadesSeleccionadas.put("Catapulta", vista.getValueCatapulta());
        unidadesSeleccionadas.put("Curandero", (Integer) vista.getValueCurandero());*/
        //TODO enviar el hashMap a donde sea necesario
        vista.cerrarVentana();
    }

}
