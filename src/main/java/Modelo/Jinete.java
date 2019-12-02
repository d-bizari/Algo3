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

    public void atacar(Unidad otraUnidad, Celda celda) throws ErrorAutoAtaque {
        if (this.enemigosCercanos.size()!=0 && this.aliadosCercanos.size()==0) {
            estado = new EstadoJineteEspada();
        } else if (puedenAyudar() || this.enemigosCercanos.size()==0) {
            estado = new EstadoJineteArcoYFlecha();
        } else {
            estado = new EstadoJineteNoAtaca();
        }
        estado.atacar(this, otraUnidad, celda);
    }

    @Override
    public Agrupacion getAgrupacion() {
        Agrupacion agrupacion = new AgrupacionInactiva();
        agrupacion.unirMiembro(this);
        return agrupacion;
    }

    private boolean puedenAyudar() {
        for (int i= 0; i<aliadosCercanos.size(); i++) {
            if (aliadosCercanos.get(i).puedeAyudarJinete()) {
                return true;
            }
        }
        return false;
    }

    public boolean puedeAyudarJinete() { return false;}

    public String getTipo() {
        return "Jinete";
    }
}
