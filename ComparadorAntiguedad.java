package aed;

import java.util.Comparator;

public class ComparadorAntiguedad implements Comparator<Traslado>{
        @Override
        public int compare(Traslado t1, Traslado t2) {
            return Integer.compare(t1.timestamp, t2.timestamp);
        }
    }


