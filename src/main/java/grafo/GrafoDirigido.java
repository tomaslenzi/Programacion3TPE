package main.java.grafo;

import java.util.*;

public class GrafoDirigido<T> implements Grafo<T> {
    private Map<Integer, List<Arco<T>>> listaAdyacencia;
    private int cantVertices;
    private int cantArcos;

    public GrafoDirigido() {
        listaAdyacencia = new HashMap<>();
        cantVertices = 0;
        cantArcos = 0;
    }

    /**
     * Complejidad: O(1)
     * ya que realiza una operacion constante de insercion en un mapa('HashMap').
     */
    @Override
    public void agregarVertice(int verticeId) {
        if (!listaAdyacencia.containsKey(verticeId)) {
            listaAdyacencia.put(verticeId, new ArrayList<Arco<T>>());
            cantVertices++;
        }

    }

    /**
     * Complejidad: O(V + A)
     * ya que recorre todos los vertices y arcos en el grafo para eliminar los arcos conectados al vertice dado.
     */
    @Override
    public void borrarVertice(int verticeId) {
        for (List<Arco<T>> listaArcos : listaAdyacencia.values()) {
            Iterator<Arco<T>> iter = listaArcos.iterator();
            while (iter.hasNext()) {
                Arco<T> arco = iter.next();
                if (arco.getVerticeDestino() == verticeId) {
                    iter.remove();
                }
            }
        }
        listaAdyacencia.remove(verticeId);
        cantVertices--;

    }

    /**
     * Complejidad: O(1)
     * ya que realiza una operacion constante de insercion en una lista.
     */
    @Override
    public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
        if (listaAdyacencia.containsKey(verticeId1)) {
            List<Arco<T>> listaArcos = listaAdyacencia.get(verticeId1);
            listaArcos.add(new Arco<>(verticeId1, verticeId2, etiqueta));
            cantArcos++;
        }

    }

    /**
     * Complejidad: O(n)
     * ya que recorre todos los arcos en la lista de adyacencia del vertice dado para buscar y eliminar el arco que coincide.
     */

    @Override
    public void borrarArco(int verticeId1, int verticeId2) {
        Arco<T> arco = new Arco<T>(verticeId1, verticeId2, null);
        if (listaAdyacencia.containsKey(verticeId1)) {
            for (Arco<T> ar : listaAdyacencia.get(verticeId1)) {
                if (arco.equals(ar)) {
                    listaAdyacencia.get(verticeId1).remove(ar);
                    cantArcos--;
                }
            }
        }

    }

    /**
     * Complejidad: O(1)
     * ya que realiza una operacion constante de busqueda en un mapa.
     */

    @Override
    public boolean contieneVertice(int verticeId) {

        return listaAdyacencia.containsKey(verticeId);
    }

    /**
     * Complejidad: O(n)
     * donde n es la cantidad de arcos en la lista de adyacencia del vertice dado.
     */

    @Override
    public boolean existeArco(int verticeId1, int verticeId2) {
        Arco<T> ar = new Arco<T>(verticeId1, verticeId2, null);
        List<Arco<T>> listaArcos = listaAdyacencia.get(verticeId1);
        if (listaArcos != null) {
            for (Arco<T> arco : listaArcos) {
                if (ar.equals(arco)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Complejidad: O(n)
     * donde n es la cantidad de arcos en la lista de adyacencia del vertice dado.
     */

    @Override
    public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
        Arco<T> a = new Arco<T>(verticeId1, verticeId2, null);
        Arco<T> arc = null;
        boolean encontrado = false;// Variable para indicar si se encontró el arco

        if (listaAdyacencia.containsKey(verticeId1)) {
            for (Arco<T> ar : listaAdyacencia.get(verticeId1)) {
                if (ar.equals(a)) {
                    arc = ar;// Se encontró el arco que coincide, se asigna a arc
                    encontrado = true;// Se marca como encontrado
                }
            }
        }
        if (!encontrado) {
            arc = null;// Si no se encontró el arco, se asigna null a arc
        }
        return arc;// Se devuelve el arco encontrado (o null si no se encontró)
    }

    /**
     * Complejidad: O(1)
     * ya que devuelve el valor almacenado en una variable.
     */
    @Override
    public int cantidadVertices() {

        return this.cantVertices;
    }

    /**
     * Complejidad: O(1)
     * ya que devuelve el valor almacenado en una variable.
     */

    @Override
    public int cantidadArcos() {

        return this.cantArcos;
    }


    /**
     * Complejidad: O(1)
     * ya que devuelve un iterador sobre las claves del mapa.
     */
    @Override
    public Iterator<Integer> obtenerVertices() {

        return listaAdyacencia.keySet().iterator();
    }

    /**
     * Complejidad: O(n)
     * donde n es la cantidad de arcos en la lista de adyacencia del vertice dado.
     */

    @Override
    public Iterator<Integer> obtenerAdyacentes(int verticeId) {
        if (!listaAdyacencia.containsKey(verticeId)) {
            // El vértice dado no existe en el grafo
            return null;
        }

        List<Integer> adyacentes = new ArrayList<>();
        List<Arco<T>> arcosAdyacentes = listaAdyacencia.get(verticeId);
        for (Arco<T> arco : arcosAdyacentes) {
            adyacentes.add(arco.getVerticeDestino());
        }

        return adyacentes.iterator();
    }


    /**
     * Complejidad: O(n)
     * donde n es la cantidad total de arcos en todas las listas de adyacencia.
     */
    @Override
    public Iterator<Arco<T>> obtenerArcos() {
        List<Arco<T>> arcos = new ArrayList<>();
        for (List<Arco<T>> listaArcos : listaAdyacencia.values()) {
            arcos.addAll(listaArcos);
        }
        return arcos.iterator();
    }

    /**
     * Complejidad: O(n)
     * donde n es la cantidad de arcos en la lista de adyacencia del vértice dado.
     */

    @Override
    public Iterator<Arco<T>> obtenerArcos(int verticeId) {
        if (listaAdyacencia.containsKey(verticeId)) {
            List<Arco<T>> listaArcos = listaAdyacencia.get(verticeId);
            return listaArcos.iterator();
        }
        return null;
    }
}
