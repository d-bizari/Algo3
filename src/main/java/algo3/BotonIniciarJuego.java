package algo3;

import Excepciones.CeldaDeTerritorioEnemigo;
import Excepciones.CeldaOcupada;
import Excepciones.CoordenadaFueraDeRango;
import Excepciones.PuntosInsuficientesException;
import Modelo.AlgoChess;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BotonIniciarJuego extends Button implements EventHandler<ActionEvent> {
    private final Stage stage;
    private final AlgoChess juego;
    private final TextField nombreJugador1;
    private final TextField nombreJugador2;
    private final ChoiceBox sectores1;
    private final ChoiceBox sectores2;

    public BotonIniciarJuego(Stage stage, AlgoChess juego, TextField nombreJugador1, TextField nombreJugador2, ChoiceBox sector1, ChoiceBox sector2) {

        this.stage = stage;
        this.juego = juego;
        this.nombreJugador1 = nombreJugador1;
        this.nombreJugador2 = nombreJugador2;
        this.sectores1 = sector1;
        this.sectores2 = sector2;
    }

    @Override
    public void handle(ActionEvent actionEvent) {

        if(sectores1.getValue().equals(sectores2.getValue())){
            AlertBoxEleccionSector.display("Error", "No pueden elegir el mismo sector del tablero");

        }else{
            juego.agregarJugador(nombreJugador1.getText(), (int)sectores1.getValue());
            juego.agregarJugador(nombreJugador2.getText(), (int)sectores2.getValue());
            Controlador controlador = new Controlador(stage, juego, nombreJugador1, nombreJugador2, (int)sectores1.getValue(), (int)sectores2.getValue());
            try{
                controlador.mostrar(stage);
            }
            catch (PuntosInsuficientesException | CoordenadaFueraDeRango | CeldaDeTerritorioEnemigo | CeldaOcupada g){
                //TODO MANEJAR ERRORES
            }

        }
    }
}
