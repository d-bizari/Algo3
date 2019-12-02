package Modelo;

import Excepciones.ErrorAutoAtaque;

public class SoldadoInfanteria extends NoCura {

    public SoldadoInfanteria(Coordenada coordenadaUnidad){
        this.vida = new Vida(100);
        this.costo = 1;
        this.danioCuerpoACuerpo = 10;
        this.danioADistancia = 0;
        this.coordenada = coordenadaUnidad;
    }

    public void atacar(Unidad unidad) throws ErrorAutoAtaque {
        if(coordenada.estanADistanciaCercana(this, unidad)) {
            unidad.sufrirAtaque(this.danioCuerpoACuerpo);
        } else {
            unidad.sufrirAtaque(this.danioADistancia);
        }
    }

    @Override
    public void recibirInvitacionAAgrupacion(Agrupacion unaAgrupacion){
        unaAgrupacion.unirMiembro(this);
    }

    public Agrupacion getAgrupacion(){
        Agrupacion agrupacion = new AgrupacionActiva();
        agrupacion.unirMiembro(this);
        return agrupacion;
    }

    public boolean puedeAyudarJinete() { return true;}

    public String getTipo() {
        return "Soldado";
    }
}
