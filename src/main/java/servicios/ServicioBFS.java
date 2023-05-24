package main.java.servicios;

import main.java.grafo.Grafo;

import java.util.*;

/**
 * Esta clase implementa un servicio para realizar el recorrido BFS (Breadth-First Search)
 * en un grafo dado. Utiliza una instancia de la clase Grafo para realizar el recorrido.
 * El resultado del recorrido es una lista de enteros que representa el orden en el que
 * se visitan los vertices del grafo.
 */


public class ServicioBFS<T> {
    private Grafo<T> grafo;

    public ServicioBFS(Grafo<T> grafo) {
        this.grafo = grafo;
    }

    /**
     * Complejidad: O(V + A), donde V es el numero de vertices y A el numero de arcos
     * Itera sobre todos los vértices del grafo una vez, y en cada iteración ejecuta el método bfs() que tiene una
     * complejidad de O(V + A).
     * Cuando termina retorna una lista de enteros que representa el orden de visita de los vertices.
     */

    public List<Integer> bfsForest() {
        List<Integer> visitados = new ArrayList<>();
        Set<Integer> visitado = new HashSet<>();
        Iterator<Integer> verticesIterator = grafo.obtenerVertices();

        // Raliza el recorrido BFS para cada vertice no visitado

        while (verticesIterator.hasNext()) {
            int vertice = verticesIterator.next();
            if (!visitado.contains((vertice))) {
                bfs(vertice, visitado, visitados);
            }
        }

        return visitados;
    }

    /**
     * Complejidad: es O(V + A). Ya que recorre los vertices adyacentes al vertice actual y los encola
     * en la cola. En el peor de los caso, se recorreran todos los vertices y arcos del grafo.
     *
     * Realiza el recorrido BFS en el grafo comenzando desde el vértice dado.
     * Actualiza el conjunto de vertices visitados y la lista de visitados
     * con el orden en el que se visitan los vertices.
     */
    private void bfs(int vertice, Set<Integer> visitado, List<Integer> visitados) {
        Queue<Integer> cola = new LinkedList<>();
        cola.offer(vertice);
        visitado.add(vertice);

        while (!cola.isEmpty()) {
            int actual = cola.poll();
            visitados.add(actual);

            //obtener los vertices adyacentes no visitados y encolarlos
            Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(actual);
            while (adyacentes.hasNext()) {
                int adyacente = adyacentes.next();
                if (!visitado.contains(adyacente)) {
                    cola.offer(adyacente);
                    visitado.add(adyacente);
                }
            }
        }


    }
}
