package Modelo;

import Excepciones.*;

public class AlgoChess {

    private Tablero tablero;
    private Jugador jugador1;
    private Jugador jugador2;

    public AlgoChess(int cantFilas, int cantCol){

        tablero = new Tablero(cantFilas, cantCol);
    }

    public void agregarJugador(String nombre, int sector){
        if(sector == 1){
            jugador1 = new Jugador(nombre, sector);
            return;
        }
        jugador2 = new Jugador(nombre, sector);
    }

    public int getPuntosDe(String jugador){
        return getJugador(jugador).getPuntos();
    }

    public Jugador getJugador(String nombreJugador) {
        if(jugador1.getNombre().equals(nombreJugador)) {
            return jugador1;
        }
        return jugador2; //FIXME caso que ingresen un nombre erroneo
    }

    public void colocarCatapultaPara(String nombreJugador, int x, int y) throws PuntosInsuficientesException, CoordenadaFueraDeRango, CeldaDeTerritorioEnemigo, CeldaOcupada {
        Coordenada coordenadaUnidad = tablero.getCoordenada(x,y);
        Catapulta catapulta = new Catapulta(coordenadaUnidad);
        Jugador jugador = getJugador(nombreJugador);
        catapulta.colocarUnidad(jugador);
        this.colocarUnidad(catapulta);
    }

    public void colocarSoldadoInfanteriaPara(String nombreJugador, int x, int y) throws PuntosInsuficientesException, CoordenadaFueraDeRango, CeldaDeTerritorioEnemigo, CeldaOcupada {
        Coordenada coordenadaUnidad = tablero.getCoordenada(x,y);
        SoldadoInfanteria soldadoInfanteria = new SoldadoInfanteria(coordenadaUnidad);
        Jugador jugador = getJugador(nombreJugador);
        soldadoInfanteria.colocarUnidad(jugador);
        this.colocarUnidad(soldadoInfanteria);
    }
    public void colocarCuranderoPara(String nombreJugador, int x, int y) throws PuntosInsuficientesException, CoordenadaFueraDeRango, CeldaDeTerritorioEnemigo, CeldaOcupada {
        Coordenada coordenadaUnidad = tablero.getCoordenada(x,y);
        Curandero curandero = new Curandero(coordenadaUnidad);
        Jugador jugador = getJugador(nombreJugador);
        curandero.colocarUnidad(jugador);
        this.colocarUnidad(curandero);
    }
    public void colocarJinetePara(String nombreJugador, int x, int y) throws PuntosInsuficientesException, CoordenadaFueraDeRango, CeldaDeTerritorioEnemigo, CeldaOcupada {
        Coordenada coordenadaUnidad = tablero.getCoordenada(x,y);
        Jinete jinete = new Jinete(coordenadaUnidad);
        Jugador jugador = getJugador(nombreJugador);
        jinete.colocarUnidad(jugador);
        this.colocarUnidad(jinete);
    }

    public void colocarUnidad(Unidad unidad) throws CoordenadaFueraDeRango, CeldaDeTerritorioEnemigo, CeldaOcupada {
        tablero.colocarUnidad(unidad);
    }

    public void atacarDesdeHasta(String nombreJugador, int desdeFil, int desdeCol, int hastaFil, int hastaCol) throws ErrorAutoAtaque, ErrorNoHayUnidadAtacante, CoordenadaFueraDeRango, UnidadEnemiga {
        Jugador jugador = getJugador(nombreJugador);
        tablero.atacarDesdeHasta(jugador, desdeFil, desdeCol, hastaFil, hastaCol);
    }

    public void curarDesdeHasta(String nombreJugador, int desdeFil, int desdeCol, int hastaFil, int hastaCol) throws NoPuedeCurar, ErrorAutoAtaque, ErrorNoHayUnidadAtacante, CoordenadaFueraDeRango, UnidadEnemiga {
        Jugador jugador = getJugador(nombreJugador);
        tablero.curarDesdeHasta(jugador, desdeFil, desdeCol, hastaFil, hastaCol);
    }

    public void moverUnidadDesdeHasta(String nombreJugador, int desdeFil, int desdeCol, int hastaFil, int hastaCol) throws CeldaOcupada, NoPuedeMoverseException, CoordenadaFueraDeRango, UnidadEnemiga {
        Jugador jugador = getJugador(nombreJugador);
        tablero.moverUnidadDesdeHasta(jugador, desdeFil, desdeCol, hastaFil, hastaCol);
        return;
    }

    public int getCantFilasTablero() {
        return tablero.getCantFilas();
    }

    public int getCantColumnasTablero() {
        return tablero.getCantColumnas();
    }

    public String seTermino(){
        if(jugador1.perdio()) {
            return jugador2.getNombre();
        } else if (jugador2.perdio()) {
            return jugador1.getNombre();
        }
        return null;
    }

    public Coordenada getCoordenadasUnidadEn(int x, int y) throws CoordenadaFueraDeRango {
        //CLASE SOLO PARA PROBAR CORRECTO FUNCIONAMIENTO, DEBERIA SER PRIVADA
        return tablero.getCoordenadasUnidadEn(x, y);
    }

    public int verVida(int x, int y) throws CoordenadaFueraDeRango{
        return tablero.verVida(x, y);
    }

    public Celda getCelda(int x, int y) throws CoordenadaFueraDeRango {
        return tablero.getCelda(x,y);
    }

    public Integer getPuntosRestantes(String nombreJugador) {
        Jugador jugador = getJugador(nombreJugador);
        return jugador.getPuntos();
    }

    public String getTipoDeUnidadEnPosicion(int x, int y) throws CoordenadaFueraDeRango {
        return tablero.getTipoDeUnidadEnPosicion(x,y);
    }

    public int getSector(String nombreJugador) {
        return getJugador(nombreJugador).getSector();
    }
}
