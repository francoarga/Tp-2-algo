package aed;


public class Handle {
    private int valor;

    public Handle(int v){
        valor = v;
    }

    public int valor(){
        return valor;
    }
    public void modificar(int i){
        valor = i;
    }
}
