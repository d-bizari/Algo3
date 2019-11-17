package Modelo;

import Excepciones.ErrorAutoAtaque;

public class EstadoJineteEspada implements EstadoJinete {
    @Override
    public void atacar (Jinete jinete, Unidad otraUnidad) throws ErrorAutoAtaque {
        if (jinete.coordenada.estanADistanciaCercana(jinete, otraUnidad)) {
            otraUnidad.sufrirAtaque(jinete.danioCuerpoACuerpo);
        }
    }
}
