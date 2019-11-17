package Modelo;

public class AgrupacionInactiva extends Agrupacion {
    public AgrupacionInactiva(){
        super();
    }
    public void unirMiembro(Unidad unMiembro){
        if(miembros.size() >= 1 || miembros.contains(unMiembro)) return;
        miembros.add(unMiembro);
    }
}
