package aed;
import java.util.ArrayList;


public class maxHeap<T extends Comparable<T>> {
    private ArrayList<T> arr;
    private Integer longitud;

    //constructor
    public maxHeap(){
        arr = new ArrayList<T>();
        longitud = 0;
    }

    //devuelve el maximo del heap
    public T maximo(){
        return arr.get(0);
    }

    //insertar elemento al heap
    public void insertar(T elem){
        arr.add(elem);
        longitud +=1;
        int i = longitud-1;
        while(i >= 1 && i < longitud){
            if(arr.get(i).compareTo(arr.get((((i-1)/2)))) > 0){        //Si es mayor que el padre, sift up
                arr.set((i-1)/2, arr.get(i));
                i = (i-1)/2;
            }
        }
    }

    //eliminar el maximo del heap
    public void eliminarMax(){
        arr.set(0, arr.get(longitud-1));   //reemplazo el maximo por el ultimo elemento
        
    }









}
