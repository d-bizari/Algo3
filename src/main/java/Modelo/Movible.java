package Modelo;

public abstract class Movible extends Unidad {

    public void mover(Coordenada coordenada) {
        this.coordenada = coordenada;
    }
}
