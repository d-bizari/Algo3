package Modelo;

import Excepciones.ErrorAutoAtaque;

public interface EstadoJinete {
    void atacar(Jinete jinete, Unidad otraUnidad, Celda celda) throws ErrorAutoAtaque;
}
