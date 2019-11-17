package Modelo;

import Excepciones.*;
import org.junit.Assert;
import org.junit.Test;

public class JugadorTest {
    @Test (expected = PuntosInsuficientesException.class)
    public void testSoloPuedeUsarUnidadesSiTienePuntos () throws CeldaDeTerritorioEnemigo, CeldaOcupada, PuntosInsuficientesException, CoordenadaFueraDeRango {
        AlgoChess juego = new AlgoChess(20,20);
        juego.agregarJugador("maria", 1);
        juego.colocarJinetePara("maria", 1,1);
        juego.colocarJinetePara("maria", 1,2);
        juego.colocarJinetePara("maria", 1,3);
        juego.colocarJinetePara("maria", 1,4);
        juego.colocarJinetePara("maria", 1,5);
        juego.colocarJinetePara("maria", 1,6);
        juego.colocarJinetePara("maria", 1,7);
    }

    /*@Test
    public void pierdeElJugadorQueNoTieneMasUnidades() throws PuntosInsuficientesException, CeldaOcupada, CoordenadaFueraDeRango, CeldaDeTerritorioEnemigo, ErrorNoHayUnidadAtacante, ErrorAutoAtaque {
        AlgoChess juego = new AlgoChess(20,20);

        juego.agregarJugador("maria", 1);
        juego.colocarJinetePara("maria", 9,1);
        juego.colocarJinetePara("maria", 9,2);
        juego.colocarJinetePara("maria", 9,3);
        juego.colocarJinetePara("maria", 9,4);

        juego.agregarJugador("jose", 2);
        juego.colocarJinetePara("jose", 11,1);
        juego.colocarJinetePara("jose", 11,2);
        juego.colocarJinetePara("jose", 11,3);
        juego.colocarJinetePara("jose", 11,4);

        for(int i= 0; i < 20; i++){
            juego.atacarDesdeHasta(9,1,11,1);
            juego.atacarDesdeHasta(9,2,11,2);
            juego.atacarDesdeHasta(9,3,11,3);
            juego.atacarDesdeHasta(9,4,11,4);

        }

        Assert.assertTrue(juego.seTermino());
    }

    @Test (expected = ErrorAutoAtaque.class)
    public void testNoSePuedeAutoAtacar () throws PuntosInsuficientesException, CeldaOcupada, CoordenadaFueraDeRango, CeldaDeTerritorioEnemigo, ErrorNoHayUnidadAtacante, ErrorAutoAtaque {
        AlgoChess juego = new AlgoChess(20,20);

        juego.agregarJugador("maria", 1);
        juego.colocarJinetePara("maria", 9,1);

        juego.agregarJugador("jose", 2);
        juego.colocarJinetePara("jose", 11,1);

        juego.atacarDesdeHasta(9, 1, 9, 1);
    }
*/
}
