package aed;

import java.util.ArrayList;

public class BestEffort {
    private maxHeap masRedituables;   
    private maxHeap masAntiguos;
    private maxHeap masSuperavit;
    private ArrayList<Integer> gananciaMax;
    private ArrayList<Integer> perdidaMax;
    private int[] ganancia;
    private int[] perdida;
    private Handle[] handleSuperavit;
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

        handleSuperavit = new Handle[cantCiudades];                           
        for(int i = 0; i < cantCiudades; i++){   //O(|C|)
            handleSuperavit[i] = null;
        }



        ComparadorRedituabilidad comR = new ComparadorRedituabilidad();
        masRedituables = new maxHeap(comR);                                 //O(1)
        ComparadorAntiguedad comA = new ComparadorAntiguedad();
        masAntiguos = new maxHeap(comA);                                    //O(1)
        ComparadorSuperavit comS = new ComparadorSuperavit();
        masSuperavit = new maxHeap(comS);                                     //O(1)

        ArrayList<Traslado> arrayR = new ArrayList<Traslado>();          
        for(int i = 0; i < traslados.length; i++){                       //O(|T|)*O(1)
            arrayR.add(traslados[i]);
        }
        masRedituables.heapify(arrayR);                                //O(|T|)

        ArrayList<Traslado> arrayA = new ArrayList<Traslado>();        
        for(int i = 0; i < traslados.length; i++){                     //O(|T|)*O(1)
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
        int[] res = new int[n];                     // O(n)
        for(int i = 0; i < n; i++){                // O(n)
            contadorDespachos += 1;
            Traslado traslado = masRedituables.maximo();        //O(1)
            res[i] = traslado.id;                               //O(1)
            masRedituables.eliminarElemento(0);                       //O(log|T|)
            masAntiguos.eliminarElemento(traslado.posAntiguo());        //O(log|T|)


            if(handleSuperavit[traslado.origen] != null){
                Traslado origen = new Traslado(traslado.id, traslado.origen, -1, traslado.gananciaNeta + masSuperavit.obtener(handleSuperavit[traslado.origen].valor()).gananciaNeta, -1);
                masSuperavit.eliminarElemento(handleSuperavit[origen.origen].valor());
                masSuperavit.insertar(origen);                                         //O(log|C|) pues el heap nunca tendra más elementos que cantidad de ciudades, antes de insertar elimino la ciudad para que no se repitan.
                handleSuperavit[origen.origen] = origen.handleSup; 

            } else {
                Traslado origen = new Traslado(traslado.id, traslado.origen, -1, traslado.gananciaNeta, -1);
                masSuperavit.insertar(origen);
                handleSuperavit[origen.origen] = origen.handleSup;
            }

            if(handleSuperavit[traslado.destino] != null){
                Traslado destino = new Traslado(traslado.id, -1, traslado.destino, -traslado.gananciaNeta + masSuperavit.obtener(handleSuperavit[traslado.destino].valor()).gananciaNeta, -1);
                masSuperavit.eliminarElemento(handleSuperavit[destino.destino].valor());
                masSuperavit.insertar(destino);                                         //O(log|C|) pues el heap nunca tendra más elementos que cantidad de ciudades, antes de insertar elimino la ciudad para que no se repitan.
                handleSuperavit[destino.destino] = destino.handleSup;

            } else {
                Traslado destino = new Traslado(traslado.id, -1, traslado.destino, -traslado.gananciaNeta, -1);
                masSuperavit.insertar(destino);
                handleSuperavit[destino.destino] = destino.handleSup;
            }


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
            
            if(handleSuperavit[traslado.origen] != null){
                Traslado origen = new Traslado(traslado.id, traslado.origen, -1, traslado.gananciaNeta + masSuperavit.obtener(handleSuperavit[traslado.origen].valor()).gananciaNeta, -1);
                masSuperavit.eliminarElemento(handleSuperavit[origen.origen].valor());
                masSuperavit.insertar(origen);                                         //O(log|C|) pues el heap nunca tendra más elementos que cantidad de ciudades, antes de insertar elimino la ciudad para que no se repitan.
                handleSuperavit[origen.origen] = origen.handleSup; 

            } else {
                Traslado origen = new Traslado(traslado.id, traslado.origen, -1, traslado.gananciaNeta, -1);
                masSuperavit.insertar(origen);
                handleSuperavit[origen.origen] = origen.handleSup;
            }

            if(handleSuperavit[traslado.destino] != null){
                Traslado destino = new Traslado(traslado.id, -1, traslado.destino, -traslado.gananciaNeta + masSuperavit.obtener(handleSuperavit[traslado.destino].valor()).gananciaNeta, -1);
                masSuperavit.eliminarElemento(handleSuperavit[destino.destino].valor());
                masSuperavit.insertar(destino);                                         //O(log|C|) pues el heap nunca tendra más elementos que cantidad de ciudades, antes de insertar elimino la ciudad para que no se repitan.
                handleSuperavit[destino.destino] = destino.handleSup;

            } else {
                Traslado destino = new Traslado(traslado.id, -1, traslado.destino, -traslado.gananciaNeta, -1);
                masSuperavit.insertar(destino);
                handleSuperavit[destino.destino] = destino.handleSup;
            }

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
        int res;
        if(masSuperavit.maximo().destino != -1){
            res = masSuperavit.maximo().destino;
        } else {
            res = masSuperavit.maximo().origen;
        }
        return res;
    }

    public ArrayList<Integer> ciudadesConMayorGanancia(){//O(1)
        return gananciaMax;    
    }

    public ArrayList<Integer> ciudadesConMayorPerdida(){//O(1)
        return perdidaMax;
    }

    public int gananciaPromedioPorTraslado(){//O(1)
        return gananciaNetaMundial / contadorDespachos;
    }
    
}
