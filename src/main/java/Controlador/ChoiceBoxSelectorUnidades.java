package Controlador;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ChoiceBoxSelectorUnidades {
    private Vista.SelectorUnidades vista;

    public ChoiceBoxSelectorUnidades(Vista.SelectorUnidades vista){
        this.vista = vista;
    }

    public void onChange(){
        int puntosTotales = 0;
        int temp;

        temp = vista.getValueSoldadoInfanteria();
        if (temp != -1) {
            puntosTotales = temp + puntosTotales;
        }

        temp = vista.getValueJinete();
        if (temp != -1) {
            puntosTotales = temp * 3 + puntosTotales;
        }

        temp = vista.getValueCatapulta();
        if (temp != -1) {
            puntosTotales = temp * 5 + puntosTotales;
        }

        temp = vista.getValueCurandero();
        if (temp != -1) {
            puntosTotales = temp * 2 + puntosTotales;
        }


        vista.puntajeNegativo(puntosTotales > 20); //Pinta texto de rojo si el puntaje es negativo
        vista.updatePuntaje(puntosTotales); //Refresca el puntaje total del usuario en la vista
        vista.habilitarBotonContinuar(puntosTotales <= 20 && puntosTotales >= 10);

    }
}