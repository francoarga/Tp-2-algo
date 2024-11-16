package aed;

public class Traslado {
    
    int id;
    int origen;
    int destino;
    int gananciaNeta;
    int timestamp;
    Handle handleRed;
    Handle handleAnt;
    Handle handleSup;

    public Traslado(int id, int origen, int destino, int gananciaNeta, int timestamp){
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.gananciaNeta = gananciaNeta;
        this.timestamp = timestamp;
    }

    public void modificarHandle(String cual, int i){
        if(cual.equals("Red")){
            handleRed = new Handle(i);
        } if(cual.equals("Ant")) {
            handleAnt = new Handle(i);
        } if (cual.equals("Sup")){
            handleSup = new Handle(i);
        }
    }

    public int posRedituable(){
        return handleRed.valor();
    }

    public int posAntiguo(){
        return handleAnt.valor();
    }

    public int posSuperavit(){
        return handleSup.valor();
    }

}
