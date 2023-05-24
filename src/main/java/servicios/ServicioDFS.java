package main.java.servicios;

import main.java.grafo.Grafo;

import java.util.*;

public class ServicioDFS<T> {
    private Grafo<T> grafo;
    private HashMap<Integer, String> colores;

    public ServicioDFS(Grafo<T> grafo) {
        this.grafo = grafo;
        this.colores = new HashMap<>();
    }

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
