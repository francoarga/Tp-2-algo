package aed;


public class ComparadorRedituabilidad<Traslado> implements Comparable<Traslado>{
public int compare(Traslado t1, Traslado t2){
return Integer.compare(t1.gananciaNeta(), t2.gananciaNeta());
}
}

