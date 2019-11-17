package Modelo;

import Excepciones.CeldaDeTerritorioEnemigo;
import Excepciones.CeldaOcupada;
import Excepciones.CoordenadaFueraDeRango;
import Excepciones.PuntosInsuficientesException;
import org.junit.Assert;
import org.junit.Test;

public class TableroTest {
    @Test
    public void testTableroEsCreadoCorrectamenteConAltoYAncho() {

        AlgoChess juego = new AlgoChess(20, 20);
        Assert.assertEquals(20, juego.getCantFilasTablero());
        Assert.assertEquals(20, juego.getCantColumnasTablero());
    }

    @Test (expected = CeldaDeTerritorioEnemigo.class)
    public void testNoSePuedeColocarUnaPiezaAliadaEnUnCasilleroDelSectorEnemigo() throws CoordenadaFueraDeRango, CeldaOcupada, CeldaDeTerritorioEnemigo, PuntosInsuficientesException {

        AlgoChess juego = new AlgoChess(20, 20);
        juego.agregarJugador("pedro", 1);
        juego.agregarJugador("juan", 2);

        juego.colocarCatapultaPara("pedro", 11, 5);

    }

    @Test
    public void testSeColocaUnidadEnSectorAliadoConExito() throws CoordenadaFueraDeRango, CeldaDeTerritorioEnemigo, CeldaOcupada, PuntosInsuficientesException {

        AlgoChess juego = new AlgoChess(20, 20);
        juego.agregarJugador("pedro", 1);
        juego.agregarJugador("juan", 2);

        juego.colocarCatapultaPara("pedro", 8, 5);

    }

    @Test (expected = CeldaOcupada.class)
    public void testNoSePuedeColocarUnidadEnCeldaOcupada() throws CoordenadaFueraDeRango, CeldaDeTerritorioEnemigo, CeldaOcupada, PuntosInsuficientesException {
        AlgoChess juego = new AlgoChess(20, 20);
        juego.agregarJugador("pedro", 1);
        juego.agregarJugador("juan", 2);

        juego.colocarCatapultaPara("pedro", 8, 5);
        juego.colocarCuranderoPara("pedro", 8, 5);
    }

}

