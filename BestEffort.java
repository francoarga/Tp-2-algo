package aed;

import java.util.ArrayList;

public class BestEffort {
    private Heap masRedituables;   //heap a implementar sobre un array
    private Heap masAntiguos;
    private Heap superavit;
    private ArrayList<Integer> gananciaMax;
    private ArrayList<Integer> perdidaMax;
    private int[] ganancia;
    private int[] perdida;
    private int contadorDespachos;
    private int gananciaNetaMundial;



    public BestEffort(int cantCiudades, Traslado[] traslados){
        gananciaNetaMundial = 0;
        contadorDespachos = 0;
        gananciaMax = new ArrayList<Integer>();                            // O(C)
        perdidaMax = new ArrayList<Integer>();                            // O(C)
        perdida = new int[cantCiudades];                             // O(C)
        masRedituables = null;
        masAntiguos = null;
        masRedituables.heapify(traslados);  //condcion de prio????      O(T)
        masAntiguos.heapify(traslados);                              // O(T)
    }

    public void registrarTraslados(Traslado[] traslados){
        for(int i = 0; i < traslados.length; i++){    // |traslados| veces
            masRedituables.insertar(traslados[i]);                // O(log T)
            masAntiguos.insertar(traslados[i]);                   // O(log T)
        }
    }

    public int[] despacharMasRedituables(int n){
        int[] res = new int[n];                       // O(n)
        for(int i = 0; i < n; i++){                // n veces
            contadorDespachos += 1;
            Traslado traslado = masRedituables.BuscarMax();
            res[i] = traslado.id;                 // O(1)
            posicion = traslado.handle;           // ??       O(1)
            masRedituables.BorrarMax();                              //O(log T)
            masAntiguos.BorrarConHandle(posicion);                   //O(log T)


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
