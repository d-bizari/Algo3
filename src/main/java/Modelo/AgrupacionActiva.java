package Modelo;

import java.util.ArrayList;
import java.util.List;

public class AgrupacionActiva extends Agrupacion {

    public AgrupacionActiva(){
        super();
    }

    public void unirMiembro(Unidad unMiembro){
        if(miembros.size() >= 3 || miembros.contains(unMiembro)){
            return;
        }

        miembros.add(unMiembro);
    }

    @Override
    public List<Unidad> getMiembros(){
        if(miembros.size() < 3){
            List<Unidad> tmp = new ArrayList<Unidad>();
            tmp.add(miembros.get(0));
            return tmp;
        }
        return miembros;
    }
}
