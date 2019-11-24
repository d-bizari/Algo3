package Modelo;

import Excepciones.ErrorAutoAtaque;

public class EstadoJineteNoAtaca implements EstadoJinete {
    @Override
    public void atacar (Jinete jinete, Unidad otraUnidad) throws ErrorAutoAtaque {
        return;
    }
}
