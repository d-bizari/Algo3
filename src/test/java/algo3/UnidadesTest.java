package Modelo;

import Excepciones.*;
import org.junit.Assert;
import org.junit.Test;

public class UnidadesTest {

    @Test
    public void testUnidadMovibleSePuedeMoverEnTodasLasDirecciones() throws PuntosInsuficientesException, CeldaDeTerritorioEnemigo, CeldaOcupada, NoPuedeMoverseException, CoordenadaFueraDeRango {
        AlgoChess juego = new AlgoChess(20,20);
        juego.agregarJugador("maria", 1);
        juego.colocarJinetePara("maria", 1,1);
        juego.moverUnidadDesdeHasta(1,1,12,16);
        juego.moverUnidadDesdeHasta(12,16,3,4);
        juego.moverUnidadDesdeHasta(3,4,10,7);
        juego.moverUnidadDesdeHasta(10,7,4,5);
        juego.moverUnidadDesdeHasta(4,5,18,18);

        Coordenada coordenadas1 = new Coordenada(18,18);

        Coordenada coordenadas2 = juego.getCoordenadasUnidadEn(18, 18);

        boolean sonIguales = true;

        if(coordenadas1.getCoordenadaX() != coordenadas2.getCoordenadaX() || coordenadas1.getCoordenadaY() != coordenadas2.getCoordenadaY()) {
                Assert.assertFalse(sonIguales);
        }
        Assert.assertTrue(sonIguales);

    }

    @Test (expected = CeldaOcupada.class)
    public void testUnidadNoPuedeMoverseACasilleroOcupado() throws PuntosInsuficientesException, CeldaDeTerritorioEnemigo, CeldaOcupada, NoPuedeMoverseException, CoordenadaFueraDeRango {
        AlgoChess juego = new AlgoChess(20,20);
        juego.agregarJugador("maria", 1);
        juego.colocarJinetePara("maria", 1,1);
        juego.colocarCuranderoPara("maria", 2, 5);
        juego.moverUnidadDesdeHasta(1,1,2,5);
    }

    @Test
    public void testAtaqueConSIDescuentaVidaCorrecta() throws ErrorAutoAtaque, ErrorNoHayUnidadAtacante, PuntosInsuficientesException, CeldaDeTerritorioEnemigo, CeldaOcupada, NoPuedeMoverseException, CoordenadaFueraDeRango {
        AlgoChess juego = new AlgoChess(20,20);

        juego.agregarJugador("maria", 1);
        juego.agregarJugador("jose", 2);

        juego.colocarJinetePara("maria", 9,1);
        juego.colocarSoldadoInfanteriaPara("jose", 11,1);

        juego.atacarDesdeHasta(11, 1, 9,1);

        Assert.assertEquals(90, juego.verVida(9,1));
    }

    @Test
    public void testAtaqueConJineteDescuentaVidaCorrecta() throws ErrorAutoAtaque, ErrorNoHayUnidadAtacante, PuntosInsuficientesException, CeldaDeTerritorioEnemigo, CeldaOcupada, NoPuedeMoverseException, CoordenadaFueraDeRango {
        AlgoChess juego = new AlgoChess(20,20);

        juego.agregarJugador("maria", 1);
        juego.agregarJugador("jose", 2);

        juego.colocarJinetePara("maria", 9,1);
        juego.colocarSoldadoInfanteriaPara("jose", 11,1);

        juego.atacarDesdeHasta(9, 1, 11,1);

        Assert.assertEquals(95, juego.verVida(11,1));
    }

    @Test
    public void testAtaqueADistanciaLejanaConCatapultaDescuentaVidaCorrecta() throws ErrorAutoAtaque, ErrorNoHayUnidadAtacante, PuntosInsuficientesException, CeldaDeTerritorioEnemigo, CeldaOcupada, NoPuedeMoverseException, CoordenadaFueraDeRango {
        AlgoChess juego = new AlgoChess(20,20);

        juego.agregarJugador("maria", 1);
        juego.agregarJugador("jose", 2);

        juego.colocarJinetePara("maria", 3,1);
        juego.colocarCatapultaPara("jose", 11,9);

        juego.atacarDesdeHasta(11, 9, 3,1);

        Assert.assertEquals(80, juego.verVida(3,1));
    }

    @Test
    public void testAtaqueADistanciaMediaConCatapultaNoDescuentaVida() throws ErrorAutoAtaque, ErrorNoHayUnidadAtacante, PuntosInsuficientesException, CeldaDeTerritorioEnemigo, CeldaOcupada, NoPuedeMoverseException, CoordenadaFueraDeRango {
        AlgoChess juego = new AlgoChess(20,20);

        juego.agregarJugador("maria", 1);
        juego.agregarJugador("jose", 2);

        juego.colocarJinetePara("maria", 9,1);
        juego.colocarCatapultaPara("jose", 14,6);

        juego.atacarDesdeHasta(14, 6, 9,1);

        Assert.assertEquals(100, juego.verVida(9,1));
    }

    @Test
    public void testAtaqueADistanciaCercanaConCatapultaNoDescuentaVida() throws ErrorAutoAtaque, ErrorNoHayUnidadAtacante, PuntosInsuficientesException, CeldaDeTerritorioEnemigo, CeldaOcupada, NoPuedeMoverseException, CoordenadaFueraDeRango {
        AlgoChess juego = new AlgoChess(20,20);

        juego.agregarJugador("maria", 1);
        juego.agregarJugador("jose", 2);

        juego.colocarJinetePara("maria", 9,1);
        juego.colocarCatapultaPara("jose", 11,2);

        juego.atacarDesdeHasta(11, 2, 9,1);

        Assert.assertEquals(100, juego.verVida(9,1));
    }

    @Test
    public void testCuranderoAgregaVidaCorrecta() throws NoPuedeCurar, ErrorAutoAtaque, ErrorNoHayUnidadAtacante, PuntosInsuficientesException, CeldaDeTerritorioEnemigo, CeldaOcupada, NoPuedeMoverseException, CoordenadaFueraDeRango {
        AlgoChess juego = new AlgoChess(20,20);

        juego.agregarJugador("maria", 1);
        juego.agregarJugador("jose", 2);

        juego.colocarJinetePara("maria", 9,1);
        juego.colocarCuranderoPara("jose", 12, 1);
        juego.colocarSoldadoInfanteriaPara("jose", 11,1);

        juego.atacarDesdeHasta(9, 1, 11,1);
        juego.atacarDesdeHasta(9, 1, 11,1);
        juego.atacarDesdeHasta(9, 1, 11,1);
        juego.atacarDesdeHasta(9, 1, 11,1);
        juego.curarDesdeHasta(12,1,11,1);

        Assert.assertEquals(95, juego.verVida(11,1));
    }


    @Test
    public void testRecibePenalizacionAlSerAtacadoEnTerritorioEnemigo() throws NoPuedeCurar, ErrorAutoAtaque, ErrorNoHayUnidadAtacante, PuntosInsuficientesException, CeldaDeTerritorioEnemigo, CeldaOcupada, NoPuedeMoverseException, CoordenadaFueraDeRango{
        AlgoChess juego = new AlgoChess(20,20);

        juego.agregarJugador("maria", 1);
        juego.agregarJugador("jose", 2);

        juego.colocarJinetePara("maria", 9,1);
        juego.colocarSoldadoInfanteriaPara("jose", 11,1);

        juego.moverUnidadDesdeHasta(9,1,12, 1);
        juego.atacarDesdeHasta(11, 1, 12,1);
        
        Assert.assertEquals(89.5, juego.verVida(12, 1), 0.5);

    }

    @Test
    public void testJineteSinAliadosCercaYConEnemigosAtacaAEnemigoCercaSeVerificaQueSeRestaLaVidaCorrespondinte() throws PuntosInsuficientesException, ErrorAutoAtaque, CoordenadaFueraDeRango, NoPuedeAtacar, CeldaDeTerritorioEnemigo, ErrorNoHayUnidadAtacante, CeldaOcupada {
        AlgoChess juego = new AlgoChess(20,20);
        juego.agregarJugador("maria",1);
        juego.agregarJugador("jose",2);

        juego.colocarJinetePara("maria",9,3);
        juego.colocarSoldadoInfanteriaPara("jose",10,3);

        juego.atacarDesdeHasta(9,3,10,3);

        Assert.assertEquals(95, juego.verVida(10,3));
    }

    @Test
    public void testJineteSinAliadosCercaYConEnemigosCercaAtacaAEnemigoLejosSeVerificaQueNoSeLeRestaVida() throws PuntosInsuficientesException, ErrorAutoAtaque, CoordenadaFueraDeRango, NoPuedeAtacar, CeldaDeTerritorioEnemigo, ErrorNoHayUnidadAtacante, CeldaOcupada {
        AlgoChess juego = new AlgoChess(20,20);
        juego.agregarJugador("maria",1);
        juego.agregarJugador("jose",2);

        juego.colocarJinetePara("maria",9,3);
        juego.colocarSoldadoInfanteriaPara("jose",10,3);
        juego.colocarSoldadoInfanteriaPara("jose", 12,3);

        juego.atacarDesdeHasta(9,3,12,3);

        Assert.assertEquals(100, juego.verVida(10,3));
    }

    @Test
    public void testJineteSinEnemigosCercaAtacaADistanciaMediaSeVerificaQueSeRestaLaVidaCorrespondiente() throws PuntosInsuficientesException, ErrorAutoAtaque, CoordenadaFueraDeRango, NoPuedeAtacar, CeldaDeTerritorioEnemigo, ErrorNoHayUnidadAtacante, CeldaOcupada {
        AlgoChess juego = new AlgoChess(20,20);
        juego.agregarJugador("maria",1);
        juego.agregarJugador("jose",2);

        juego.colocarJinetePara("maria",9,3);
        juego.colocarSoldadoInfanteriaPara("jose",13,6);

        juego.atacarDesdeHasta(9,3,13,6);

        Assert.assertEquals(85, juego.verVida(13,6));
    }

    @Test
    public void testJineteSinEnemigosCercaAtacaADistanciaLejanaSeVerificaQueNoSeLeRestaVida() throws PuntosInsuficientesException, ErrorAutoAtaque, CoordenadaFueraDeRango, NoPuedeAtacar, CeldaDeTerritorioEnemigo, ErrorNoHayUnidadAtacante, CeldaOcupada {
        AlgoChess juego = new AlgoChess(20,20);
        juego.agregarJugador("maria",1);
        juego.agregarJugador("jose",2);

        juego.colocarJinetePara("maria",9,3);
        juego.colocarSoldadoInfanteriaPara("jose",19,12);

        juego.atacarDesdeHasta(9,3,19,12);

        Assert.assertEquals(100, juego.verVida(19,12));
    }
}
