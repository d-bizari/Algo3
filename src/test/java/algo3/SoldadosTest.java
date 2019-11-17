package Modelo;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class SoldadosTest {
    @Test
    public void test3soldadosContiguosSeMuevenJuntosEnLaMismaDireccion(){
        AlgoChess juego = new AlgoChess(20,20);
        Celda celda1 = null;
        Celda celda2 = null;
        Celda celda3 = null;

        juego.agregarJugador("wola", 1);
        juego.agregarJugador("malo",2);
        try{
            //Coloco piezas
            juego.colocarSoldadoInfanteriaPara("wola",2,2);
            juego.colocarSoldadoInfanteriaPara("wola",2,1);
            juego.colocarSoldadoInfanteriaPara("wola",2,3);
            //Muevo una pieza
            juego.moverUnidadDesdeHasta(2,3,5,3);
            //Obtengo celdas siguientes
            celda1 = juego.getCelda(5,2);
            celda2 = juego.getCelda(5,1);
            celda3 = juego.getCelda(5,3);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        Assert.assertTrue(celda1.estaOcupada());
        Assert.assertTrue(celda2.estaOcupada());
        Assert.assertTrue(celda3.estaOcupada());
    }

    @Test
    public void Test4SoldadosContiguosElBatallonSoloMueve3(){
        AlgoChess juego = new AlgoChess(20,20);
        Celda celda1 = null;
        Celda celda1bis = null;
        Celda celda2 = null;
        Celda celda3 = null;
        Celda celda4 = null;

        juego.agregarJugador("wola", 1);
        juego.agregarJugador("malo",2);
        try{
            //Coloco piezas
            juego.colocarSoldadoInfanteriaPara("wola",2,1);
            juego.colocarSoldadoInfanteriaPara("wola",2,2);
            juego.colocarSoldadoInfanteriaPara("wola",2,3);
            juego.colocarSoldadoInfanteriaPara("wola",2,4);
            //Muevo una pieza
            juego.moverUnidadDesdeHasta(2,3,5,3);
            //Obtengo celdas siguientes
            celda1 = juego.getCelda(5,1);
            celda1bis = juego.getCelda(2,1);
            celda2 = juego.getCelda(5,2);
            celda3 = juego.getCelda(5,3);
            celda4 = juego.getCelda(5,4);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        Assert.assertTrue(celda1bis.estaOcupada());
        Assert.assertFalse(celda1.estaOcupada());
        Assert.assertTrue(celda2.estaOcupada());
        Assert.assertTrue(celda3.estaOcupada());
        Assert.assertTrue(celda4.estaOcupada());
    }

    @Test
    public void TestBatallonSeDeshaceCuandoUnaDeLasPiezasNoSePuedeMover(){
        AlgoChess juego = new AlgoChess(20,20);
        Celda celda1 = null;
        Celda celda2 = null;
        Celda celda3 = null;

        juego.agregarJugador("wola", 1);
        juego.agregarJugador("malo",2);
        try{
            //Coloco piezas
            juego.colocarSoldadoInfanteriaPara("wola",2,2);
            juego.colocarSoldadoInfanteriaPara("wola",2,1);
            juego.colocarSoldadoInfanteriaPara("wola",2,3);
            juego.colocarJinetePara("wola",4,1);
            //Muevo una pieza
            juego.moverUnidadDesdeHasta(2,3,3,3);
            //Obtengo celdas siguientes
            celda1 = juego.getCelda(3,1);
            celda2 = juego.getCelda(3,2);
            celda3 = juego.getCelda(3,3);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        Assert.assertTrue(celda1.estaOcupada());
        Assert.assertTrue(celda2.estaOcupada());
        Assert.assertTrue(celda3.estaOcupada());

        try{
            juego.moverUnidadDesdeHasta(3,2,4,2);
            //Obtengo celdas siguientes
            celda1 = juego.getCelda(3,1);
            celda2 = juego.getCelda(4,2);
            celda3 = juego.getCelda(4,3);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        Assert.assertTrue(celda1.estaOcupada());
        Assert.assertTrue(celda2.estaOcupada());
        Assert.assertTrue(celda3.estaOcupada());
    }
    @Test
    public void TestAgrupacionActivaSoloSeMueveCon3Miembros(){
        AlgoChess juego = new AlgoChess(20,20);
        Celda celda1 = null;
        Celda celda2 = null;

        juego.agregarJugador("wola", 1);
        juego.agregarJugador("malo",2);
        try{
            //Coloco piezas
            juego.colocarSoldadoInfanteriaPara("wola",2,2);
            juego.colocarSoldadoInfanteriaPara("wola",2,1);
            //Muevo una pieza
            juego.moverUnidadDesdeHasta(2,1,5,1);
            //Obtengo celdas siguientes
            celda1 = juego.getCelda(5,2);
            celda2 = juego.getCelda(5,1);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        Assert.assertFalse(celda1.estaOcupada());
        Assert.assertTrue(celda2.estaOcupada());
    }
    @Test
    public void TestNoSePuedenMoverHaciaLaCeldaDeAlgunMiembro(){

    }
    //TODO hacer mas tests!
}
