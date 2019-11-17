package Modelo;

import Excepciones.CeldaDeTerritorioEnemigo;
import Excepciones.CoordenadaFueraDeRango;

public class Coordenada {
    public int x;
    public int y;
    private int maxDistanciaCercana = 2;
    private int maxDistanciaMedia = 5;

    public Coordenada(int coordenadaX, int coordenadaY){
        x = coordenadaX;
        y = coordenadaY;
    }

    public int getCoordenadaX() {
        return x;
    }

    public int getCoordenadaY() {
        return y;
    }

    public void coordenadaDentroDeTablero(Integer rangoMaxFil, Integer rangoMinFil, Integer rangoMaxCol, Integer rangoMinCol) throws CoordenadaFueraDeRango {
        if(this.x > rangoMaxFil || this.x < rangoMinFil || this.y > rangoMaxCol || this.y < rangoMinCol){
            throw new CoordenadaFueraDeRango();
        }
    }

    /*public void enSectorAliado(Jugador jugador) throws CeldaDeTerritorioEnemigo {
        if (jugador.getSector() == 1 && this.x > 10 || jugador.getSector() == 2 && this.x < 10){
            throw new CeldaDeTerritorioEnemigo();
        }
    }*/

    public boolean estanADistanciaCercana(Unidad unidad1, Unidad unidad2) {
        Coordenada coordenadas1 = unidad1.getCoordenadas();
        Coordenada coordenadas2 = unidad2.getCoordenadas();

        int x1 = coordenadas1.getCoordenadaX();
        int y1 = coordenadas1.getCoordenadaY();

        int x2 = coordenadas2.getCoordenadaX();
        int y2 = coordenadas2.getCoordenadaY();

        return moduloDiferencia(x1, x2) <= maxDistanciaCercana && moduloDiferencia(y1, y2) <= maxDistanciaCercana;
    }

    public int moduloDiferencia(int n1, int n2) {
        if ((n1-n2) > 0) {
            return n1-n2;
        }
        return n2-n1;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Coordenada){
            Coordenada c = (Coordenada) o;
            if(this.x == c.getCoordenadaX() && this.y == c.getCoordenadaY())
                return true;
        }

        return false;
    }

    public boolean estanADistanciaMedia(Unidad unidad1, Unidad unidad2) {
        Coordenada coordenadas1 = unidad1.getCoordenadas();
        Coordenada coordenadas2 = unidad2.getCoordenadas();

        int x1 = coordenadas1.getCoordenadaX();
        int y1 = coordenadas1.getCoordenadaY();

        int x2 = coordenadas2.getCoordenadaX();
        int y2 = coordenadas2.getCoordenadaY();

        int modDiferenciax = moduloDiferencia(x1,x2);
        int modDiferenciay = moduloDiferencia(y1,y2);

        return (modDiferenciax > maxDistanciaCercana && modDiferenciax<=maxDistanciaMedia) &&
                (modDiferenciay> maxDistanciaCercana && modDiferenciay<=maxDistanciaMedia);
    }

    public boolean estanADistanciaLejana(Unidad unidad1, Unidad unidad2) {
        Coordenada coordenadas1 = unidad1.getCoordenadas();
        Coordenada coordenadas2 = unidad2.getCoordenadas();

        int x1 = coordenadas1.getCoordenadaX();
        int y1 = coordenadas1.getCoordenadaY();

        int x2 = coordenadas2.getCoordenadaX();
        int y2 = coordenadas2.getCoordenadaY();

        int modDiferenciax = moduloDiferencia(x1,x2);
        int modDiferenciay = moduloDiferencia(y1,y2);

        return modDiferenciax>maxDistanciaMedia && modDiferenciay>maxDistanciaMedia;
    }

    public boolean estaADistancia1(Coordenada coordUni) {
        int x1 = this.getCoordenadaX();
        int y1 = this.getCoordenadaY();
        int x2 = coordUni.getCoordenadaX();
        int y2 = coordUni.getCoordenadaY();

        int modDifX = moduloDiferencia(x1,x2);
        int modDifY = moduloDiferencia(y1,y2);

        double distancia = Math.sqrt((modDifX * modDifX) + (modDifY * modDifY));

        return distancia == 1;
    }
}
