package Modelo;

import Excepciones.ErrorAutoAtaque;
import Excepciones.NoPuedeMoverseException;

import java.util.List;

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

    public void atacar(Unidad unidad, Celda celda, Agrupacion agrupacion, Tablero tablero) throws ErrorAutoAtaque {
        if(coordenada.estanADistanciaLejana(this, unidad)) {
            //unidad.sufrirAtaque(this.danioADistancia, celda);
            try{
                List<Unidad> unidadesContiguas = tablero.getUnidadesContiguas(unidad.getCoordenadas());
                for(Unidad uni : unidadesContiguas){
                    uni.sufrirAtaque(this.danioADistancia, celda);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
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
