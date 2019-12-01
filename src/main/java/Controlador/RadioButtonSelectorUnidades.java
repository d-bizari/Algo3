package Controlador;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Toggle;

public class RadioButtonSelectorUnidades implements ChangeListener<Toggle> {
    private Vista.SelectorUnidades vista;

    public RadioButtonSelectorUnidades(Vista.SelectorUnidades vista){
        this.vista = vista;
    }


   @Override
    public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
        int puntosTotales = 0;

        if(vista.getValueSoldadoInfanteria()){
            puntosTotales = 1 + puntosTotales;
        }

        if (vista.getValueJinete()) {
            puntosTotales = 3 + puntosTotales;
        }

        if (vista.getValueCatapulta()) {
            puntosTotales = 5 + puntosTotales;
        }

        if (vista.getValueCurandero()) {
            puntosTotales = 2 + puntosTotales;
        }

        //TODO hay que mantener registro de los puntos totales de otra meanera
        vista.puntajeNegativo(puntosTotales > 20); //Pinta texto de rojo si el puntaje es negativo
        vista.updatePuntaje(puntosTotales); //Refresca el puntaje total del usuario en la vista
        //vista.habilitarBotonContinuar(puntosTotales <= 20 && puntosTotales >= 10);
    }
}