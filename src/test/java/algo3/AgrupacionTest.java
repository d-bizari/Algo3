package Modelo;

import org.junit.Assert;
import org.junit.Test;

public class AgrupacionTest {
    @Test
    public void TestAgrupacionActivaPuedeAgregarMiembros(){
        SoldadoInfanteria s1 = new SoldadoInfanteria(new Coordenada(2,2));
        SoldadoInfanteria s2 = new SoldadoInfanteria(new Coordenada(2,3));
        SoldadoInfanteria s3 = new SoldadoInfanteria(new Coordenada(2,4));
        Agrupacion agrupacion = s1.getAgrupacion();

        s2.recibirInvitacionAAgrupacion(agrupacion);
        s3.recibirInvitacionAAgrupacion(agrupacion);

        Assert.assertEquals(3,agrupacion.getCantidadDeMiembros());
    }
    @Test
    public void TestAgrupacionNoPermiteMasDe3Miembros(){
        SoldadoInfanteria s1 = new SoldadoInfanteria(new Coordenada(2,2));
        SoldadoInfanteria s2 = new SoldadoInfanteria(new Coordenada(2,3));
        SoldadoInfanteria s3 = new SoldadoInfanteria(new Coordenada(2,4));
        SoldadoInfanteria s4 = new SoldadoInfanteria(new Coordenada(2,5));
        Agrupacion agrupacion = s1.getAgrupacion();

        s2.recibirInvitacionAAgrupacion(agrupacion);
        s3.recibirInvitacionAAgrupacion(agrupacion);
        s4.recibirInvitacionAAgrupacion(agrupacion);

        Assert.assertEquals(3,agrupacion.getCantidadDeMiembros());
    }
    @Test
    public void TestAgrupacionInactivaNoPuedeAgregarMiembrosExtra(){
        Jinete j1 = new Jinete(new Coordenada(2,2));
        Jinete j2 = new Jinete(new Coordenada(2,3));
        Agrupacion agrupacion = j1.getAgrupacion();

        j2.recibirInvitacionAAgrupacion(agrupacion);

        Assert.assertEquals(1,agrupacion.getCantidadDeMiembros());
    }
    @Test
    public void TestSoloSoldadosAceptanInvitaiconAAgrupacionActiva(){
        SoldadoInfanteria s1 = new SoldadoInfanteria(new Coordenada(2,2));
        SoldadoInfanteria s2 = new SoldadoInfanteria(new Coordenada(2,3));
        Jinete j = new Jinete(new Coordenada(2,4));
        Agrupacion agrupacion = s1.getAgrupacion();

        s2.recibirInvitacionAAgrupacion(agrupacion);
        j.recibirInvitacionAAgrupacion(agrupacion);

        Assert.assertEquals(2,agrupacion.getCantidadDeMiembros());
    }
    @Test
    public void TestSoldadoNoPuedeUnirseAAgrupacionInactiva(){
        SoldadoInfanteria s = new SoldadoInfanteria(new Coordenada(2,2));
        Jinete j = new Jinete(new Coordenada(2,4));
        Agrupacion agrupacion = j.getAgrupacion();

        s.recibirInvitacionAAgrupacion(agrupacion);

        Assert.assertEquals(1,agrupacion.getCantidadDeMiembros());
    }
}
