package Modelo;

import Excepciones.*;
import java.util.*;

public class Tablero {

    private HashMap<Coordenada, Celda> tablero;
    private int filasTotales;
    private int columnasTotales;
    private int finSectorUno;

    public Tablero(int cantFilas, int cantCol) {

        tablero = new HashMap<Coordenada, Celda>();
        filasTotales = cantFilas;
        columnasTotales = cantCol;
        finSectorUno = (cantFilas/2);
        int sector;

        for(int i=0; i< cantFilas; i++){
            for(int j = 0; j < cantCol; j++) {
                Coordenada coordenada = new Coordenada(i,j);
                //ESTA BIEN ESTE IF??
                if(i < finSectorUno) {
                    sector = 1;
                } else {
                    sector = 2;
                }
                tablero.put(coordenada, new Celda(sector));
            }
        }
    }

    public int getCantFilas() {
        return filasTotales;
    }

    public int getCantColumnas() {
        return columnasTotales;
    }

    private Celda getCeldaPorCoordenada(Coordenada coordenada) throws CoordenadaFueraDeRango{
        Celda celda = tablero.get(coordenada);
        if (celda == null){
            throw new CoordenadaFueraDeRango();
        }
        return celda;
    }

    public Coordenada getCoordenada(int x, int y) throws CoordenadaFueraDeRango {
        Set<Coordenada> setCoordenadas = tablero.keySet();
        for (Coordenada coordenada : setCoordenadas) {
            if (x == coordenada.getCoordenadaX() && y == coordenada.getCoordenadaY()) {
                return coordenada;
            }
        }
        throw new CoordenadaFueraDeRango();
    }

    public Celda getCelda(int x, int y) throws CoordenadaFueraDeRango {
        //VER SI SE PUEDE CAMBIAR POR TABLERO.GET(COORDENADA), PASANDO COMO PARAMETRO LA COORDENADA.
        Set<Coordenada> setCoordenadas = tablero.keySet();
        for (Coordenada coordenada : setCoordenadas) {
            if (x == coordenada.getCoordenadaX() && y == coordenada.getCoordenadaY()) {
                return tablero.get(coordenada);
            }
        }
        throw new CoordenadaFueraDeRango();

    }

    public Coordenada getCoordenadasUnidadEn(int x, int y) throws CoordenadaFueraDeRango {
        Celda celda = getCelda(x, y);
        return celda.getUnidad().getCoordenadas();
    }

    public void colocarUnidad(Unidad unidad) throws CeldaDeTerritorioEnemigo, CeldaOcupada, CoordenadaFueraDeRango {
        Coordenada coordenadas = unidad.getCoordenadas();
        Jugador jugador = unidad.getDueño();
        coordenadas.coordenadaDentroDeTablero(filasTotales, 0, columnasTotales, 0);
        //coordenadas.enSectorAliado(jugador);
        int x = coordenadas.getCoordenadaX();
        int y = coordenadas.getCoordenadaY();
        Celda celda = getCelda(x, y);
        if(!celda.esDeSectorAliado(jugador)) {
            throw new CeldaDeTerritorioEnemigo();
        }
        celda.colocarUnidad(unidad);
    }

    public void atacarDesdeHasta(int desdeFil, int desdeCol, int hastaFil, int hastaCol) throws ErrorAutoAtaque, ErrorNoHayUnidadAtacante, CoordenadaFueraDeRango {

        Celda celdaAliada = getCelda(desdeFil, desdeCol);
        Celda celdaEnemiga = getCelda(hastaFil, hastaCol);
        List<Unidad> enemigosCercanos = this.ObtenerEnemigosCercanos(celdaAliada);
        List<Unidad> aliadosCercanos = this.ObtenerAliadosCercanos(celdaAliada);
        celdaAliada.atacar(celdaEnemiga, enemigosCercanos, aliadosCercanos);
    }

    public void moverUnidadDesdeHasta(int desdeFil, int desdeCol, int hastaFil, int hastaCol) throws CeldaOcupada, NoPuedeMoverseException, CoordenadaFueraDeRango {
        int deltaX = hastaFil - desdeFil;
        int deltaY = hastaCol - desdeCol;
        Agrupacion unaAgrupacion = this.getCelda(desdeFil,desdeCol).getUnidad().getAgrupacion(); //Puede devolver Una agrupacion activa o inactiva
        this.enviarInvitacionAUnidadesContiguas(this.getCelda(desdeFil,desdeCol), unaAgrupacion);
        for(Unidad uni: unaAgrupacion.getMiembros()){
            Celda celdaNueva = this.getCelda(uni.getCoordenadas().getCoordenadaX() + deltaX,uni.getCoordenadas().getCoordenadaY() + deltaY);
            Celda celdaActual = this.getCelda(uni.getCoordenadas().getCoordenadaX(),uni.getCoordenadas().getCoordenadaY());
            try {
                celdaNueva.colocarUnidad(celdaActual.getUnidad());
            }catch (CeldaOcupada e){
                if(unaAgrupacion.tieneBatallon())
                    continue;
                throw new CeldaOcupada();
            }
            celdaActual.vaciar();
            Coordenada coordenadaAMover = getCoordenada(uni.getCoordenadas().getCoordenadaX() + deltaX,uni.getCoordenadas().getCoordenadaY() + deltaY);
            celdaNueva.getUnidad().mover(coordenadaAMover);
        }
    }

    public int verVida(int x, int y) throws CoordenadaFueraDeRango {
        Celda celda = getCelda(x, y);
        return celda.getUnidad().verVidaRestante();
    }

    public void curarDesdeHasta(int desdeFil, int desdeCol, int hastaFil, int hastaCol) throws NoPuedeCurar, ErrorAutoAtaque, ErrorNoHayUnidadAtacante, CoordenadaFueraDeRango {
        Celda celdaCuradora = getCelda(desdeFil, desdeCol);
        Celda celdaLastimada = getCelda(hastaFil, hastaCol);
        celdaCuradora.curar(celdaLastimada);
    }

    public List<Unidad> ObtenerEnemigosCercanos (Celda celdaPrincipal) {
        List<Unidad> EnemigosCercanos = new LinkedList<Unidad>();
        Unidad unidadPrincipal = celdaPrincipal.getUnidad();

        Coordenada coordenadaPrincipal = celdaPrincipal.getUnidad().getCoordenadas();
        Set<Coordenada> setCoordenadas = tablero.keySet();
        for (Coordenada coordenada : setCoordenadas) {
            Celda celdaAux = tablero.get(coordenada);
            Unidad unidadAux = celdaAux.getUnidad();
            if (unidadAux!=null && unidadAux!=unidadPrincipal) {
                if(!unidadAux.esAliadaDe(unidadPrincipal) && coordenadaPrincipal.estanADistanciaCercana(unidadPrincipal, unidadAux)) {
                    EnemigosCercanos.add(unidadAux);
                }
            }

        }
        return EnemigosCercanos;
    }

    public List<Unidad> ObtenerAliadosCercanos (Celda celdaPrincipal) {
        List<Unidad> AliadosCercanos = new LinkedList<Unidad>();
        Unidad unidadPrincipal = celdaPrincipal.getUnidad();
        Coordenada coordenadaPrincipal = celdaPrincipal.getUnidad().getCoordenadas();
        Set<Coordenada> setCoordenadas = tablero.keySet();
        for (Coordenada coordenada : setCoordenadas) {
            Celda celdaAux = tablero.get(coordenada);
            Unidad unidadAux = celdaAux.getUnidad();
            if (unidadAux!=null && unidadAux!=unidadPrincipal) {
                if(unidadAux.esAliadaDe(unidadPrincipal) && coordenadaPrincipal.estanADistanciaCercana(unidadPrincipal, unidadAux)) {
                    AliadosCercanos.add(unidadAux);
                }
            }

        }
        return AliadosCercanos;
    }


    public void enviarInvitacionAUnidadesContiguas(Celda celdaOrigen, Agrupacion unaAgrupacion) {
        Queue<Unidad> unidades = obtenerUnidadesADistancia1(celdaOrigen);
        List<Unidad> visitados = new ArrayList<Unidad>();
        Queue<Unidad> unidadesTemp;
        Unidad temp;
        Celda celdaTemp;

        while (!(unidades.isEmpty())){
            temp = unidades.remove();
            visitados.add(temp);
            temp.recibirInvitacionAAgrupacion(unaAgrupacion);
            try{
                celdaTemp = getCelda(temp.getCoordenadas().getCoordenadaX(),temp.getCoordenadas().getCoordenadaY());
            }catch (Exception e){
                System.out.println(e.getMessage()); //TODO implementar un mejor manejo de errores
                return;
            }
            unidadesTemp = obtenerUnidadesADistancia1(celdaTemp);
            for(Unidad uni : unidadesTemp){
                if(!visitados.contains(uni)){
                    unidades.add(uni);
                }
            }
        }
    }

    private Queue<Unidad> obtenerUnidadesADistancia1(Celda celdaOrigen){
        Queue<Unidad> contiguos = new LinkedList<>();
        List<Unidad> aliados = ObtenerAliadosCercanos(celdaOrigen);

        for(Unidad uni : aliados){
            if(celdaOrigen.estaADistancia1(uni))
                contiguos.add(uni);
        }
        return contiguos;
    }

    public String getTipoDeUnidadEnPosicion(int x, int y) throws CoordenadaFueraDeRango{
        Celda celda = getCelda(x, y);
        if (celda.estaOcupada()){
            return celda.getUnidad().getTipo();
        }
        return "NOHAYUNIDAD";

    }
}

