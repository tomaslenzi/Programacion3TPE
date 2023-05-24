package main.java.servicios;

import main.java.grafo.Grafo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ServicioCaminos<T> {

    private Grafo<T> grafo;
    private int origen;
    private int destino;
    private int lim;

    // Servicio caminos
    public ServicioCaminos(Grafo<T> grafo, int origen, int destino, int lim) {
        this.grafo = grafo;
        this.origen = origen;
        this.destino = destino;
        this.lim = lim;
    }

    public List<List<Integer>> caminos() {
        List<List<Integer>> caminosValidos = new ArrayList<>();
        List<Integer> caminoActual = new ArrayList<>();
        buscarCaminos(origen, destino, caminoActual, caminosValidos);
        return caminosValidos;

    }

    private void buscarCaminos(int actual, int destino, List<Integer> caminoActual, List<List<Integer>> caminosValidos) {
        if (actual == destino) {
            caminosValidos.add(new ArrayList<>(caminoActual));
            return;
        }
        if (caminoActual.size() == lim) {
            return;
        }
        caminoActual.add(actual);
        Iterator<Integer> adyacentesIterator = grafo.obtenerAdyacentes(actual);
        while (adyacentesIterator.hasNext()) {
            Integer adyacente = adyacentesIterator.next();
            if (!caminoActual.contains(adyacente)) {
                buscarCaminos(adyacente, destino, caminoActual, caminosValidos);
            }
        }
        caminoActual.remove(caminoActual.size() - 1); // elimina el ultimo vertice agregado al camino actual
    }
}