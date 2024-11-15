package aed;

import java.util.ArrayList;

public class BestEffort {
    private maxHeap masRedituables;   //heap a implementar sobre un array
    private maxHeap masAntiguos;
    private maxHeap superavit;
    private ArrayList<Integer> gananciaMax;
    private ArrayList<Integer> perdidaMax;
    private int[] ganancia;
    private int[] perdida;
    private int contadorDespachos;
    private int gananciaNetaMundial;



    public BestEffort(int cantCiudades, Traslado[] traslados){//O(|T|+|C|)
        gananciaNetaMundial = 0;
        contadorDespachos = 0;
        gananciaMax = new ArrayList<Integer>();                             // O(1)
        perdidaMax = new ArrayList<Integer>();                              // O(1)
        ganancia = new int[cantCiudades];                                   // O(|C|)  
        perdida = new int[cantCiudades];                                    // O(|C|)
        masRedituables = null;
        masAntiguos = null;

        ArrayList<Traslado> arr = new ArrayList<Traslado>();               //O(|T|)*O(1)
        for(int i = 0; i < traslados.length; i++){      
            arr.set(i, traslados[i]);
        }

        masRedituables.heapify(arr);                                //O(|T|)
        masAntiguos.heapify(arr);                                  // O(|T|)
    }

    public void registrarTraslados(Traslado[] traslados){//O(|T|log|T|)
        for(int i = 0; i < traslados.length; i++){                 // O(|T|)*O(log|T|)
            masRedituables.insertar(traslados[i]);                                     // O(log|T|)
            masAntiguos.insertar(traslados[i]);                                        // O(log|T|)  
        }
    }

    public int[] despacharMasRedituables(int n){
        int[] res = new int[n];                       // O(n)
        for(int i = 0; i < n; i++){                // 
            contadorDespachos += 1;
            Traslado traslado = masRedituables.maximo();        //O(1)
            res[i] = traslado.id;                               //O(1)
            masRedituables.eliminarElemento(0);                       //O(log|T|)
            masAntiguos.eliminarElemento(traslado.posAntiguo());        //O(log|T|)
                                                                //O(log|C|)


            //de acá para abajo todo O(1)
            ganancia[traslado.origen] += traslado.gananciaNeta; 
            perdida[traslado.destino] += traslado.gananciaNeta;  


            if(ganancia[gananciaMax.get(0)] == traslado.gananciaNeta){
                gananciaMax.add(traslado.origen);
            } 
            if(ganancia[gananciaMax.get(0)] < traslado.gananciaNeta){
                gananciaMax = new ArrayList<Integer>();
                gananciaMax.add(traslado.origen);
            }

            if(perdida[perdidaMax.get(0)] == traslado.gananciaNeta){
                perdidaMax.add(traslado.destino);
            } 
            if(perdida[perdidaMax.get(0)] < traslado.gananciaNeta){
                perdidaMax = new ArrayList<Integer>();
                perdidaMax.add(traslado.destino);
            }

            gananciaNetaMundial += traslado.gananciaNeta; 

        }
        return res;
    }

    public int[] despacharMasAntiguos(int n){
        return null;
    }

    public int ciudadConMayorSuperavit(){
        return ;
    }

    public ArrayList<Integer> ciudadesConMayorGanancia(){
        return gananciaMax;
    }

    public ArrayList<Integer> ciudadesConMayorPerdida(){
        return perdidaMax;
    }

    public int gananciaPromedioPorTraslado(){
        return gananciaNetaMundial / contadorDespachos;
    }
    
}
