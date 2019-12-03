package Modelo;

import Excepciones.CeldaOcupada;
import Excepciones.ErrorAutoAtaque;
import Excepciones.ErrorNoHayUnidadAtacante;
import Excepciones.NoPuedeCurar;

import java.util.List;

public class Celda {

    private boolean ocupada;
    private Unidad unidad;
    protected int sector;

    public Celda(int sector){
        ocupada = false;
        this.sector = sector;
    }

    public Unidad getUnidad() {

        if(unidad != null) {
            return unidad;
        }
        return null;
    }

    public boolean estaOcupada(){
        return ocupada;
    }

    public void colocarUnidad(Unidad unidad) throws CeldaOcupada {
        if(ocupada){
            throw new CeldaOcupada();
        }
        if(unidad.getDueño().getSector() != this.sector) {
            unidad.recibirPenalizacion();
        } else {
            unidad.cancelarPenalizacion();
        }
        this.unidad = unidad;
        this.ocupada = true;
    }

    private void celdaEstaOcupadaPorEnemigo(Celda celda) throws ErrorAutoAtaque {
        if (this.unidad.getDueño() == celda.getUnidad().getDueño() ) {
            throw new ErrorAutoAtaque();
        }
    }

    public boolean esDeSectorAliado(Jugador jugador) {
        return this.sector == jugador.getSector();
    }

    public void atacar(Celda celdaEnemiga, List<Unidad> enemigos, List<Unidad> aliados, Agrupacion agrupacion, Tablero tablero) throws ErrorAutoAtaque, ErrorNoHayUnidadAtacante {
        celdaEstaOcupadaPorEnemigo(celdaEnemiga);
        if(!estaOcupada()) {
            throw new ErrorNoHayUnidadAtacante();
        }
        if(!celdaEnemiga.estaOcupada()) {
            return; //Ataca igual a la nada (se termina su turno)
        }
        this.unidad.recibirEnemigosCercanos(enemigos);
        this.unidad.recibirAliadosCercanos(aliados);
        /*if(!esDeSectorAliado(this.unidad.getDueño())){
            this.unidad.atacarConPenalizacion(celdaEnemiga.getUnidad(), celdaEnemiga);
        }*/
        this.unidad.atacar(celdaEnemiga.getUnidad(), celdaEnemiga, agrupacion, tablero);
    }

    public void curar(Celda celdaLastimada) throws NoPuedeCurar {
        this.unidad.curar(celdaLastimada.getUnidad());
    }

    public void vaciar() {
        ocupada = false;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Celda){
            Celda c = (Celda) o;
            if(this.unidad.equals(c.unidad))
                return true;
        }
        return false;
    }

    public boolean estaADistancia1(Unidad uni) {
        Coordenada coordOrigen = this.getUnidad().getCoordenadas();
        Coordenada coordUni = uni.getCoordenadas();

        return coordOrigen.estaADistancia1(coordUni);
    }
}

