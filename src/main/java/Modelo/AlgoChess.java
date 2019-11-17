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
        }
        jugador2 = new Jugador(nombre, sector);
    }

    private Jugador identificarJugador(String nombreJugador) {
        if(jugador1.getNombre() == nombreJugador) {
            return jugador1;
        }
        return jugador2;
    }

    public void colocarCatapultaPara(String nombreJugador, int x, int y) throws PuntosInsuficientesException, CoordenadaFueraDeRango, CeldaDeTerritorioEnemigo, CeldaOcupada {
        Coordenada coordenadaUnidad = tablero.getCoordenada(x,y);
        Catapulta catapulta = new Catapulta(coordenadaUnidad); //PREGUNTAR SI NO SE PUEDE COLOCAR COMO LA BORRO DESPUES????
        Jugador jugador = identificarJugador(nombreJugador);
        catapulta.colocarUnidad(jugador);
        this.colocarUnidad(catapulta);
    }

    public void colocarSoldadoInfanteriaPara(String nombreJugador, int x, int y) throws PuntosInsuficientesException, CoordenadaFueraDeRango, CeldaDeTerritorioEnemigo, CeldaOcupada {
        Coordenada coordenadaUnidad = tablero.getCoordenada(x,y);
        SoldadoInfanteria soldadoInfanteria = new SoldadoInfanteria(coordenadaUnidad);
        Jugador jugador = identificarJugador(nombreJugador);
        soldadoInfanteria.colocarUnidad(jugador);
        this.colocarUnidad(soldadoInfanteria);
    }
    public void colocarCuranderoPara(String nombreJugador, int x, int y) throws PuntosInsuficientesException, CoordenadaFueraDeRango, CeldaDeTerritorioEnemigo, CeldaOcupada {
        Coordenada coordenadaUnidad = tablero.getCoordenada(x,y);
        Curandero curandero = new Curandero(coordenadaUnidad); //PREGUNTAR SI NO SE PUEDE COLOCAR COMO LA BORRO DESPUES????
        Jugador jugador = identificarJugador(nombreJugador);
        curandero.colocarUnidad(jugador);
        this.colocarUnidad(curandero);
    }
    public void colocarJinetePara(String nombreJugador, int x, int y) throws PuntosInsuficientesException, CoordenadaFueraDeRango, CeldaDeTerritorioEnemigo, CeldaOcupada {
        Coordenada coordenadaUnidad = tablero.getCoordenada(x,y);
        Jinete jinete = new Jinete(coordenadaUnidad); //PREGUNTAR SI NO SE PUEDE COLOCAR COMO LA BORRO DESPUES????
        Jugador jugador = identificarJugador(nombreJugador);
        jinete.colocarUnidad(jugador);
        this.colocarUnidad(jinete);
    }

    public void colocarUnidad(Unidad unidad) throws CoordenadaFueraDeRango, CeldaDeTerritorioEnemigo, CeldaOcupada {
        tablero.colocarUnidad(unidad);
    }

    public void atacarDesdeHasta(int desdeFil, int desdeCol, int hastaFil, int hastaCol) throws ErrorAutoAtaque, ErrorNoHayUnidadAtacante, CoordenadaFueraDeRango {
       tablero.atacarDesdeHasta(desdeFil, desdeCol, hastaFil, hastaCol);
    }

    public void curarDesdeHasta(int desdeFil, int desdeCol, int hastaFil, int hastaCol) throws NoPuedeCurar, ErrorAutoAtaque, ErrorNoHayUnidadAtacante, CoordenadaFueraDeRango {
        tablero.curarDesdeHasta(desdeFil, desdeCol, hastaFil, hastaCol);
    }

    public void moverUnidadDesdeHasta(int desdeFil, int desdeCol, int hastaFil, int hastaCol) throws CeldaOcupada, NoPuedeMoverseException, CoordenadaFueraDeRango {
        //Se tiene que manejar desde aca la parte de batallon ya que se necesita el tablero para hacer efectivo el movimiento de
        // las unidades, ya que el mover de las unidades solo cambia sus respectivas coordenadas, pero no los estados de las
        // celdas.

        // --------------- implementacion vieja de batallon -----------------------------
        /*
        int tempX;
        int tempY;
        if(this.getCelda(desdeFil,desdeCol).getUnidad() instanceof SoldadoInfanteria){
            List<Celda> soldados = new LinkedList<Celda>();
            soldados.add(this.getCelda(desdeFil,desdeCol));
            tablero.buscarSoldadosContiguos(desdeFil,desdeCol,soldados);
            int deltaFil = hastaFil - desdeFil;
            int deltaCol = hastaCol - desdeCol;
            for (Celda celda : soldados) {
                tempX = celda.getUnidad().getCoordenadas().getCoordenadaX();
                tempY = celda.getUnidad().getCoordenadas().getCoordenadaY();
                tablero.moverUnidadDesdeHasta(tempX,tempY,tempX + deltaFil,tempY + deltaCol);
            }
            return;
        } */
        tablero.moverUnidadDesdeHasta(desdeFil, desdeCol, hastaFil, hastaCol);
        return;
    }

    public int getCantFilasTablero() {
        return tablero.getCantFilas();
    }

    public int getCantColumnasTablero() {
        return tablero.getCantColumnas();
    }

    public boolean seTermino(){
        if(jugador1.perdio()) {
            System.out.print("Perdió:" + jugador1.getNombre());
            return true;
        } else if (jugador2.perdio()) {
            System.out.print("Perdió:" + jugador2.getNombre());
            return true;
        }
        return false;
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
}
