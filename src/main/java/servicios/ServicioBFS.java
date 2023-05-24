package main.java.servicios;

import main.java.grafo.Grafo;

import java.util.*;


public class ServicioBFS<T> {
    private Grafo<T> grafo;

    public ServicioBFS(Grafo<T> grafo) {
        this.grafo = grafo;
    }

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
