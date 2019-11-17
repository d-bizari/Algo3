package Modelo;

import Excepciones.PuntosInsuficientesException;

public class Jugador {

    private Puntaje puntos;
    private String nombre;
    private int cantUnidades;
    private int sector;

    public Jugador(String nombre, int sector) {
        cantUnidades = 0;
        puntos = new Puntaje();
        this.nombre = nombre;
        this.sector = sector;
    }

    public void colocarUnidad(Unidad unidad) throws PuntosInsuficientesException {
        try {
            puntos.descontarPuntos(unidad.getCosto());
        } catch (Excepciones.PuntosInsuficientesException e) {
            e.printStackTrace();
            throw new PuntosInsuficientesException();
        }
        cantUnidades ++;
    }

    public void sufrirAtaque() {
        cantUnidades--;
    }

    public String getNombre() {
        return nombre;
    }

    public int getSector() {
        return sector;
    }

    public boolean perdio(){
        return cantUnidades == 0;
    }


}
