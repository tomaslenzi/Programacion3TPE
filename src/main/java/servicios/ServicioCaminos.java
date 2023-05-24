package main.java.servicios;

import main.java.grafo.Grafo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Esta clase implementa un servicio para encontrar caminos validos en un grafo dado, desde un vertice de origen hasta
 * un vertice de destino. Utiliza una instancia de la clase Grafo para realizar la besqueda de caminos. El servicio
 * permite especificar un limite maximo de longitud para los caminos. El resultado de la busqueda de caminos es una
 * lista de listas de enteros, donde cada lista representa un camino valido en el grafo, en el orden en el que se
 * visitan los vertices.
 */

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

    /**
     * Complejidad: O(b^d), donde b representa el factor de ramificación promedio y d es la longitud maxima del camino.
     * El metodo caminos realiza la busqueda de todos los caminos validos desde el vertice de origen hasta el vertice de destino en el
     * grafo, con una longitud maxima especificada.
     * Devuelve una lista de listas de enteros, donde cada lista representa un camino
     * valido en el grafo.

     */

    public List<List<Integer>> caminos() {
        List<List<Integer>> caminosValidos = new ArrayList<>();
        List<Integer> caminoActual = new ArrayList<>();
        buscarCaminos(origen, destino, caminoActual, caminosValidos);
        return caminosValidos;

    }

/**
 * Complejidad: O(b^d), donde b es la cantidad de caminos posible y d es la longitud maxima del camino
 * Metodo auxiliar que realiza la busqueda de caminos validos en el grafo, utilizando
 * la tecnica de backtracking. Comienza en el vertice actual y realiza llamadas recursivas
 * para explorar los caminos posibles, evitando ciclos y respetando la longitud máxima
 * especificada.
 */

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