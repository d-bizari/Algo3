package Modelo;

import Excepciones.ErrorAutoAtaque;

public class Curandero extends PuedeCurar {

    public Curandero (Coordenada coordenadaUnidad) {
        this.vida = new Vida(100);
        this.costo = 2;
        this.danioCuerpoACuerpo = 0;
        this.danioADistancia = 0;
        this.curacion = 15;
        this.coordenada = coordenadaUnidad;
    }

    @Override
    public void recibirInvitacionAAgrupacion(Agrupacion unaAgrupacion) {
        //No hace nada
    }

    public void atacar(Unidad unidad, Celda celda) throws ErrorAutoAtaque {
        if(coordenada.estanADistanciaCercana(this, unidad)) {
            unidad.sufrirAtaque(this.danioCuerpoACuerpo, celda);
        } else {
            unidad.sufrirAtaque(this.danioADistancia, celda);
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
        return "Curandero";
    }
}
