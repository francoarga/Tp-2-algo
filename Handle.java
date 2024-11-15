package aed;

public class Handle {
    private int valor;

    public Handle() {
        this.valor = -1; // Estado inicial del Handle
    }

    public void cambiarHandle(int pos) {
        if (pos >= 0) {
            this.valor = pos;
        }
    }

    public int obtenerValor() {
        return this.valor;
    }

    public boolean estaInicializado() {
        return this.valor != -1;
    }

    public void resetear() {
        this.valor = -1;
    }
}
