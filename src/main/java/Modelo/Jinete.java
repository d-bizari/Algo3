package Modelo;

import Excepciones.ErrorAutoAtaque;


public class Jinete extends NoCura {

    private EstadoJinete estado;

    public Jinete (Coordenada coordenadaUnidad) {
        this.vida = new Vida(100);
        this.costo = 3;
        this.danioCuerpoACuerpo = 5;
        this.danioADistancia = 15;
        this.coordenada = coordenadaUnidad;

    }

    @Override
    public void recibirInvitacionAAgrupacion(Agrupacion unaAgrupacion) {
        //No hace nada
    }

    public void atacar(Unidad otraUnidad) throws ErrorAutoAtaque {
        if (this.enemigosCercanos.size()!=0 && this.aliadosCercanos.size()==0) {
            estado = new EstadoJineteEspada();
        } else {
            estado = new EstadoJineteArcoYFlecha();
        }
        estado.atacar(this, otraUnidad);
    }

    @Override
    public Agrupacion getAgrupacion() {
        Agrupacion agrupacion = new AgrupacionInactiva();
        agrupacion.unirMiembro(this);
        return agrupacion;
    }
}
