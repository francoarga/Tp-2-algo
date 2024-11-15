package aed;
import java.util.ArrayList;
import java.util.Comparator;



public class maxHeap<Traslado> {
    private ArrayList<Traslado> arr;
    private Integer longitud;
    private Comparator<Traslado> c;


    //constructor
    public maxHeap(Comparator<Traslado> com){
        arr = new ArrayList<Traslado>();
        longitud = 0;
        c = com;
    }

    //devuelve el maximo del heap
    public Traslado maximo(){
        return arr.get(0);
    }

    //insertar elemento al heap
    public void insertar(Traslado elem){
        arr.add(elem);
        longitud +=1;
        int i = longitud-1;
        while(i >= 1 && i < longitud){
            if(c.compare(arr.get(i), arr.get((((i-1)/2)))) > 0){        //Si es mayor que el padre, sift up
                arr.set((i-1)/2, arr.get(i));
                i = (i-1)/2;
            }
        }
        if (c instanceof ComparadorAntiguedad){
            elem.modificarHandle("Ant", i);
        } else {
            elem.modificarHandle("Red", i);
        }
    }

    //eliminar el maximo del heap
    public void eliminarMax(){
        arr.set(0, arr.get(longitud-1));   //reemplazo el maximo por el ultimo elemento
        
    }









}
