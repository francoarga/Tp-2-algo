package aed;
import java.util.ArrayList;
import java.util.Comparator;



public class maxHeap {
    private ArrayList<Traslado> arr;
    private Integer longitud;
    private Comparator<Traslado> c;


    //constructor
    public maxHeap(Comparator<Traslado> com){
        arr = new ArrayList<Traslado>();
        longitud = 0;
        c = com;
    }

    public void heapify(ArrayList<Traslado> t){
        arr = t;
        longitud = t.size();
        for(int j = 0; j < arr.size();j++){
            cambiaHandle(arr.get(j), j, c);
        }
        int i =  longitud-1;
        while(i >= 0){
            siftDOWN(i);
            i = i-1;
        }
    }

    //devuelve el maximo del heap
    public Traslado maximo(){
        return arr.get(0);
    }


    //modifica el handle segun en qué heap se encuentra
    private void cambiaHandle(Traslado elem, int i, Comparator<Traslado> c){
    if (c instanceof ComparadorAntiguedad){
        elem.modificarHandle("Ant", i);
    } else {
        elem.modificarHandle("Red", i);
    }
}
    private void swap(Traslado t1, Traslado t2, Comparator<Traslado> c){
    int pos1;
    int pos2;
     if(c instanceof ComparadorAntiguedad){
         pos1 = t1.posAntiguo();
         pos2 = t2.posAntiguo();
     }else{
         pos1 = t1.posRedituable();
         pos2 = t2.posRedituable();
     }
     Traslado temp = arr.get(pos1);  
     arr.set(pos1, arr.get(pos2));  
     arr.set(pos2, temp);  
 
     cambiaHandle(arr.get(pos1), pos1, c);  
     cambiaHandle(arr.get(pos2), pos2, c);
 }

 private void siftUP(int i){
    while(i > 0 && c.compare(arr.get(i), arr.get((((i-1)/2)))) > 0){        //Si es mayor que el padre, sift up
        swap(arr.get(((i-1)/2)), arr.get(i), c);
        i = (i-1)/2;               
    }   
 }

 private void siftDOWN(int i){
    while(i>=0){
        if(2*i+2 < longitud){    //tiene dos hijos
            if((c.compare(arr.get(i), arr.get(2*i+1)) < 0  || c.compare(arr.get(i), arr.get(2*i+2)) < 0)){ //veo si alguno es mayor
                if(c.compare(arr.get(2*i+1), arr.get(2*i+2)) > 0){   //veo cual hijo es mayor
                    swap(arr.get(2*i+1), arr.get(i), c);
                    i = 2*i+1;
                } else {
                    swap(arr.get(2*i+2), arr.get(i), c);
                    i = 2*i+2;
                }
            } else {
                i = -1;
        }
    } else { //tiene un hijo o 0
        if(2*i+1 < longitud){
            if(c.compare(arr.get(i), arr.get(2*i+1)) < 0){
                swap(arr.get(2*i+1), arr.get(i), c);
                i = -1;
        } else {
            i = -1;
        }
    } else {
            i = -1;
        }
    }  
}
 }


    //insertar elemento al heap
    public void insertar(Traslado elem){
        arr.add(elem);
        longitud +=1;
        cambiaHandle(elem, longitud-1, c);
        int i = longitud-1;
        siftUP(i);
}
    //eliminar el maximo del heap
    public void eliminarElemento(int i){
        Traslado max = arr.get(i);
        swap(max, arr.get(longitud-1), c);
        arr.remove(longitud-1);
        longitud = longitud -1;
        siftDOWN(i);
    }
}