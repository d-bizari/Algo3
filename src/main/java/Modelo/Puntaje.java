package Modelo;

import Excepciones.PuntosInsuficientesException;

public class Puntaje {
    private Integer puntos;

    public Puntaje() {
        puntos = 20;
    }

    public void descontarPuntos (Integer puntosAdescontar) throws PuntosInsuficientesException {
        puntos = puntos - puntosAdescontar;
        if (puntos < 0) {
            puntos = puntos + puntosAdescontar;
            throw new PuntosInsuficientesException("No tiene puntos suficientes para colocar la unidad");
        }
    }

    public Integer getPuntos() { return puntos;}
}
