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
        gananciaMax.add(0);
        perdidaMax = new ArrayList<Integer>();                              // O(1)
        perdidaMax.add(0);
        ganancia = new int[cantCiudades];                                   // O(|C|)  
        perdida = new int[cantCiudades];                                    // O(|C|)
        ComparadorRedituabilidad comR = new ComparadorRedituabilidad();
        masRedituables = new maxHeap(comR);
        ComparadorAntiguedad comA = new ComparadorAntiguedad();
        masAntiguos = new maxHeap(comA);

        ArrayList<Traslado> arrayR = new ArrayList<Traslado>();               //O(|T|)*O(1)
        for(int i = 0; i < traslados.length; i++){      
            arrayR.add(traslados[i]);
        }
        masRedituables.heapify(arrayR);                                //O(|T|)

        ArrayList<Traslado> arrayA = new ArrayList<Traslado>();               //O(|T|)*O(1)
        for(int i = 0; i < traslados.length; i++){      
            arrayA.add(traslados[i]);
        }
        masAntiguos.heapify(arrayA);                                  // O(|T|)
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


            if(gananciaMax.get(gananciaMax.size()-1) != traslado.origen && ganancia[gananciaMax.get(gananciaMax.size()-1)] == ganancia[traslado.origen]){
                gananciaMax.add(traslado.origen);
            } 
            if(ganancia[gananciaMax.get(gananciaMax.size()-1)] < ganancia[traslado.origen]){
                gananciaMax = new ArrayList<Integer>();
                gananciaMax.add(traslado.origen);
            }

            if(perdidaMax.get(perdidaMax.size()-1) != traslado.destino && perdida[perdidaMax.get(perdidaMax.size()-1)] == perdida[traslado.destino]){
                perdidaMax.add(traslado.destino);
            } 
            if(perdida[perdidaMax.get(perdidaMax.size()-1)] < perdida[traslado.destino]){
                perdidaMax = new ArrayList<Integer>();
                perdidaMax.add(traslado.destino);
            }

            gananciaNetaMundial += traslado.gananciaNeta; 

        }
        return res;
    }

    public int[] despacharMasAntiguos(int n){
        int[] res = new int[n];                       // O(n)
        for(int i = 0; i < n; i++){                // O(n)
            contadorDespachos += 1;
            Traslado traslado = masAntiguos.maximo();        //O(1)
            res[i] = traslado.id;                               //O(1)
            masAntiguos.eliminarElemento(0);                       //O(log|T|)
            masRedituables.eliminarElemento(traslado.posRedituable());        //O(log|T|)
                                                                //O(log|C|)


            //de acá para abajo todo O(1)
            ganancia[traslado.origen] += traslado.gananciaNeta; 
            perdida[traslado.destino] += traslado.gananciaNeta;  


            if(gananciaMax.get(gananciaMax.size()-1) != traslado.origen && ganancia[gananciaMax.get(gananciaMax.size()-1)] == ganancia[traslado.origen]){
                gananciaMax.add(traslado.origen);
            } 
            if(ganancia[gananciaMax.get(gananciaMax.size()-1)] < ganancia[traslado.origen]){
                gananciaMax = new ArrayList<Integer>();
                gananciaMax.add(traslado.origen);
            }

            if(perdidaMax.get(perdidaMax.size()-1) != traslado.destino && perdida[perdidaMax.get(perdidaMax.size()-1)] == perdida[traslado.destino]){
                perdidaMax.add(traslado.destino);
            } 
            if(perdida[perdidaMax.get(perdidaMax.size()-1)] < perdida[traslado.destino]){
                perdidaMax = new ArrayList<Integer>();
                perdidaMax.add(traslado.destino);
            }

            gananciaNetaMundial += traslado.gananciaNeta; 

        }
        return res;
    }

    public int ciudadConMayorSuperavit(){
        return 0;
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
