package algo3;

import Excepciones.CeldaDeTerritorioEnemigo;
import Excepciones.CeldaOcupada;
import Excepciones.CoordenadaFueraDeRango;
import Excepciones.PuntosInsuficientesException;
import Modelo.AlgoChess;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class BotonUbicacionJugador1 extends Button implements EventHandler<ActionEvent> {

    private HashMap<String, Integer> unidadesDisponibles;
    private AlgoChess juego;
    private TextField jugador1;
    private TableroGridPane tablero;
    private ArrayList<Integer> coordenadas;
    MouseEvent mouseEvent;

    public BotonUbicacionJugador1(HashMap<String, Integer> hash, AlgoChess juego, TextField nombreJugador1, TableroGridPane tablero) throws PuntosInsuficientesException, CoordenadaFueraDeRango, CeldaDeTerritorioEnemigo, CeldaOcupada {
        this.unidadesDisponibles = hash;
        this.juego = juego;
        this.jugador1 = nombreJugador1;
        this.tablero = tablero;
    }

    @Override
    public void handle(ActionEvent actionEvent) {



        String unidadAUbicar = VentanaSeleccion.display(jugador1);
        Integer cantidadRestante = (unidadesDisponibles.get(unidadAUbicar));

        if(unidadAUbicar != null &&  cantidadRestante != 0){

            unidadesDisponibles.put(unidadAUbicar, cantidadRestante-1);

            try {
                if (unidadAUbicar == "Soldado Infanteria") {
                    juego.colocarSoldadoInfanteriaPara(jugador1.getText(), coordenadas.get(0), coordenadas.get(1));
                } else if (unidadAUbicar == "Jinete") {
                    juego.colocarSoldadoInfanteriaPara(jugador1.getText(), coordenadas.get(0), coordenadas.get(1));
                } else if (unidadAUbicar == "Catapulta") {
                    juego.colocarSoldadoInfanteriaPara(jugador1.getText(), coordenadas.get(0), coordenadas.get(1));
                } else if (unidadAUbicar == "Curandero") {
                    juego.colocarSoldadoInfanteriaPara(jugador1.getText(), coordenadas.get(0), coordenadas.get(1));
                }
            }
            catch (PuntosInsuficientesException | CoordenadaFueraDeRango | CeldaDeTerritorioEnemigo | CeldaOcupada g){
                //TODO MANEJAR ERRORES
            }

            //TODO AGREGAR IMAGEN DE LA UNIDAD QUE SEA EN LA COORDENADA.

            if(!QuedanUnidades()){
                this.setDisable(true);
                //TODO CREAR EL OTRO BOTON
            }

        }else if(cantidadRestante == 0){
            AlertBoxEleccionSector.display("Error", "No le quedan mas de esa unidad");
        }
    }

    private boolean QuedanUnidades(){
        if(unidadesDisponibles.get("Soldado Infanteria") + unidadesDisponibles.get("Jinete") + unidadesDisponibles.get("Catapulta") + unidadesDisponibles.get("Curandero") == 0){
            return false;
        }
        return true;
    }

}
