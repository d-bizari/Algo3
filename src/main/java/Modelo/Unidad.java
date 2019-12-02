package Modelo;

import Excepciones.ErrorAutoAtaque;
import Excepciones.NoPuedeCurar;
import Excepciones.NoPuedeMoverseException;
import Excepciones.PuntosInsuficientesException;

import java.util.List;

public abstract class Unidad {
    protected Vida         vida;
    protected int          costo;
    protected int          danioCuerpoACuerpo;
    protected int          danioADistancia;
    protected int          curacion;
    protected Coordenada   coordenada;
    protected List<Unidad> enemigosCercanos;
    protected List<Unidad> aliadosCercanos;
    private   Jugador      duenio;

    public abstract void mover(Coordenada coordenada) throws NoPuedeMoverseException;
    public abstract void curar(Unidad unidad) throws NoPuedeCurar;
    public abstract void recibirInvitacionAAgrupacion(Agrupacion unaAgrupacion); //TODO ver si habria que retornar algo!
    public abstract void atacar(Unidad unidad, Celda celda) throws ErrorAutoAtaque;/* {
        if(coordenada.estanADistanciaCercana(this, unidad)) {
            unidad.sufrirAtaque(this.danioCuerpoACuerpo);
        } else {
            unidad.sufrirAtaque(this.danioADistancia);
        }
    }*/

    private int cientoCincoPorcientoDe(int danio) {
        int danioTotal = danio + (danio*5)/100;
        return danioTotal;
    }

    public void atacarConPenalizacion(Unidad unidad, Celda celda) {
        if(coordenada.estanADistanciaCercana(this, unidad)) {
            unidad.sufrirAtaque(cientoCincoPorcientoDe(this.danioCuerpoACuerpo), celda);
        } else {
            unidad.sufrirAtaque(cientoCincoPorcientoDe(this.danioADistancia), celda);
        }
    }

    public int getCosto(){
        return costo;
    }

    public Jugador getDueño(){
        return duenio;
    }

    public void colocarUnidad(Jugador jugador) throws PuntosInsuficientesException {

        jugador.colocarUnidad(this);
        duenio = jugador;
    }

    public Coordenada getCoordenadas(){
        return coordenada;
    }

    public void sufrirAtaque(int danio, Celda celda) {
        vida.sufrirAtaque(danio);
        if (vida.estaMuerto()) {
            duenio.sufrirAtaque();
            celda.vaciar(); //TODO verificar que funcione bien
        }
    }

    public void ganarVida(int cantVida) {
        vida.ganarVida(cantVida);
    }

    public int verVidaRestante(){
        return vida.verVidaRestante();
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Unidad){
            Unidad u = (Unidad) o;
            if(this.coordenada.equals(u.coordenada)) //Una unidad es igual a otra si esta en la misma coordenada
                return true;
        }

        return false;
    }

    public void recibirEnemigosCercanos(List<Unidad> enemigos) {
        this.enemigosCercanos = enemigos;
    }

    public void recibirAliadosCercanos(List<Unidad> aliados) {
        this.aliadosCercanos = aliados;
    }

    public abstract Agrupacion getAgrupacion();

    public boolean esAliadaDe(Unidad otraUnidad) {
        if (this.duenio == otraUnidad.duenio) {
            return true;
        }
        return false;
    }

    public abstract boolean puedeAyudarJinete();

    public abstract String getTipo();
    //if(this.agrupacion == null)
}
