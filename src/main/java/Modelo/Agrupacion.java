package Modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Agrupacion {
    protected List<Unidad> miembros;

    public Agrupacion(){
        miembros = new ArrayList<Unidad>();
    }
    public abstract void unirMiembro(Unidad unMiembro);

    public int getCantidadDeMiembros(){ return miembros.size();} // Solo puesto para hacer testing

    public List<Unidad> getMiembros(){ return miembros;}

    public boolean tieneBatallon(){return miembros.size() == 3;}
}
