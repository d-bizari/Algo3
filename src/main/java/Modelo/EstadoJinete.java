package Modelo;

import Excepciones.ErrorAutoAtaque;

public interface EstadoJinete {
    void atacar(Jinete jinete, Unidad otraUnidad) throws ErrorAutoAtaque;
}
