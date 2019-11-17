package Modelo;

public class Vida {

    protected int vidaMaxima;
    protected int vidaActual;

    public Vida(int vida) {
        vidaMaxima = vida;
        this.vidaActual = vida;
    }

    public void ganarVida(int cantVida) {
        vidaActual = vidaActual + cantVida;
        if(vidaActual > vidaMaxima) {
            vidaActual = vidaMaxima;
        }
    }

    public void sufrirAtaque(int danio) {
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
}
