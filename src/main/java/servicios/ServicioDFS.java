package main.java.servicios;

import main.java.grafo.Grafo;

import java.util.*;

/**
 * Esta clase implementa un servicio para realizar el recorrido DFS (Depth-First Search) en un grafo dado.
 * Utiliza una instancia de la clase Grafo para realizar el recorrido y mantiene un registro de los colores de los vertices
 * durante el proceso de búsqueda.
 */

public class ServicioDFS<T> {
    private Grafo<T> grafo;
    private HashMap<Integer, String> colores;

    public ServicioDFS(Grafo<T> grafo) {
        this.grafo = grafo;
        this.colores = new HashMap<>();
    }


    /**
     * Complejidad: O(V + A), donde V es el numero de vertices y A el numero de arcos.
     * Realiza el recorrido DFS en el grafo y devuelve una lista de enteros que representa el orden en el que se visitan
     * los vertices.
     */
    public List<Integer> dfsForest() {
        List<Integer> tree = new ArrayList<>();

        Iterator<Integer> verticesIterator = this.grafo.obtenerVertices();

        //inicializa todos los vertices con el color blanco

        while (verticesIterator.hasNext()) {
            Integer vertice = verticesIterator.next();
            colores.put(vertice, "blanco");
        }

        //realiza el recorrido dfs para cada vertice no visitado

        verticesIterator = this.grafo.obtenerVertices();
        while (verticesIterator.hasNext()) {
            Integer vertice = verticesIterator.next();
            if (colores.get(vertice).equals("blanco")) {
                tree.addAll(dfs(vertice));
            }
        }
        return tree;
    }

    /**
     * Complejidad: O(V + A), donde V es el numero de vertices y A el numero de arcos.
     * Metodo auxiliar que realiza el recorrido DFS desde un vertice dado.
     * Marca los vertices como visitados y realiza llamadas recursivas para explorar los vertices adyacentes no visitados.
     * Devuelve una lista de enteros que representa el orden de visita de los vertices en el recorrido DFS.
     */

    private List<Integer> dfs(Integer vertice) {
        colores.put(vertice, "amarillo");
        Iterator<Integer> adyacentesIterator = this.grafo.obtenerAdyacentes(vertice);
        List<Integer> resultado = new ArrayList<>();
        resultado.add(vertice);

        //realiza el recorrido dfs para los vertices adyacentes no visitados
        while (adyacentesIterator.hasNext()) {
            Integer ady = adyacentesIterator.next();
            if (colores.get(ady).equals("blanco")) {
                resultado.addAll(dfs(ady));
                colores.put(ady, "amarillo");
            }
        }

        colores.put(vertice, "negro");
        return resultado;
    }
}
