package main.java.grafo;

public class Arco <T>{
    private int verticeOrigen;
    private int verticeDestino;
    private T etiqueta;

    public Arco(int verticeOrigen, int verticeDestino, T etiqueta) {
        this.verticeOrigen = verticeOrigen;
        this.verticeDestino = verticeDestino;
        this.etiqueta = etiqueta;
    }

    public int getVerticeOrigen() {
        return verticeOrigen;
    }

    public int getVerticeDestino() {
        return verticeDestino;
    }

    public T getEtiqueta() {
        return etiqueta;
    }

    public boolean equals(Arco<T> a) {
        if(a.verticeOrigen==this.verticeOrigen && a.getVerticeDestino()==this.getVerticeDestino()) {
            return true;
        }else {
            return false;
        }
    }
}
