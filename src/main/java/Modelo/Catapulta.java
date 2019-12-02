package Modelo;

import Excepciones.ErrorAutoAtaque;
import Excepciones.NoPuedeMoverseException;

public class Catapulta extends NoMovibleYNoCura {

    public Catapulta (Coordenada coordenadaUnidad) {
        this.vida = new Vida(50);
        this.costo = 5;
        this.danioCuerpoACuerpo = 0;
        this.danioADistancia = 20;
        this.coordenada = coordenadaUnidad;
    }

    @Override
    public void mover(Coordenada coordenada) throws NoPuedeMoverseException {
        throw new NoPuedeMoverseException();
    }

    @Override
    public void recibirInvitacionAAgrupacion(Agrupacion unaAgrupacion) {
        //No hace nada
    }

    public void atacar(Unidad unidad) throws ErrorAutoAtaque {
        if(coordenada.estanADistanciaLejana(this, unidad)) {
            unidad.sufrirAtaque(this.danioADistancia);
        }
    }

    @Override
    public Agrupacion getAgrupacion() {
        Agrupacion agrupacion = new AgrupacionInactiva();
        agrupacion.unirMiembro(this);
        return agrupacion;
    }


    public boolean puedeAyudarJinete() { return false;}

    public String getTipo() {
        return "Catapulta";
    }
}
