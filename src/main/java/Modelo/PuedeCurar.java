package Modelo;

import Excepciones.NoPuedeCurar;

public abstract class PuedeCurar extends Movible {

    public void curar(Unidad unidad) throws NoPuedeCurar {
        if(coordenada.estanADistanciaCercana(this, unidad)) {
            unidad.ganarVida(this.curacion);
        }
    }

    public void recibirInvitacionAAgrupacion(Unidad unaUnidad){
        return;
    }
}