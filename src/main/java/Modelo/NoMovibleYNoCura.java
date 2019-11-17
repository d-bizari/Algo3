package Modelo;

import Excepciones.NoPuedeCurar;
import Excepciones.NoPuedeMoverseException;


public abstract class NoMovibleYNoCura extends Unidad {

    public void mover(int x, int y) throws NoPuedeMoverseException {
        throw new NoPuedeMoverseException();
    }

    public void curar(Unidad unidad) throws NoPuedeCurar {
        throw new NoPuedeCurar();
    }

    public void recibirInvitacionAAgrupacion(Unidad unaUnidad){
        return;
    }
}
