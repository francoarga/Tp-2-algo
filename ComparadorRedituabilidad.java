package aed;

import java.util.Comparator;

public class ComparadorRedituabilidad implements Comparator<Traslado>{
    @Override
    public int compare(Traslado t1, Traslado t2){
        if(Integer.compare(t1.gananciaNeta, t2.gananciaNeta) == 0){
            return Integer.compare(t1.id, t2.id);
        } else {
            return Integer.compare(t1.gananciaNeta, t2.gananciaNeta);
        }
    }
}

