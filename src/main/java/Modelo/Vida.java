package Modelo;

public class Vida {

    protected int vidaMaxima;
    protected int vidaActual;
    protected boolean penalizacion;

    public Vida(int vida) {
        vidaMaxima = vida;
        this.vidaActual = vida;
        penalizacion = false;
    }

    public void ganarVida(int cantVida) {
        vidaActual = vidaActual + cantVida;
        if(vidaActual > vidaMaxima) {
            vidaActual = vidaMaxima;
        }
    }

    public void sufrirAtaque(int danio) {

        if (penalizacion) {
            danio = this.cientoCincoPorcientoDe(danio);
        }
        vidaActual = vidaActual - danio;
    }

    public boolean estaMuerto() {
        if (vidaActual <= 0) {
            return true;
        }
        return false;
    }

    public int verVidaRestante(){
        return vidaActual;
    }

    public void recibirPenalizacion() { penalizacion = true; }

    public void cancelarPenalizacion() { penalizacion = false; }

    private int cientoCincoPorcientoDe(int danio) {
        int danioTotal = danio + (danio*5)/100;
        return danioTotal;
    }
}
