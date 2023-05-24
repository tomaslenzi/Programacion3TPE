package main.java.grafo;

import java.util.*;

/*
Para implementar la estructura de almacenamiento de vertices y arcos, se utilizo un
'Map<Integer, List<Arco<T>>>' llamado 'listaAdyacencia'.

El 'Map' se utiliza para asociar cada vertice con una lista de arcos que representan sus
conexiones con otros vértices. La clave del Map es el identificador único del vertice
(valor entero) y el valor correspondiente es una lista de arcos.

La lista de arcos permite almacenar multiples arcos salientes desde un mismo vertice.
Cada arco contiene informacion sobre el vertice de destino y cualquier otra etiqueta o
información adicional asociada a la conexión.

Esta estructura de implementacion permite acceder rapidamente a los arcos de un vértice
específico y manejar multiples conexiones entre los vertices del grafo.

 */

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
     * ya que realiza una operacion constante que implica simplemente insertar una nueva entrada en el mapa.
     */
    @Override
    public void agregarVertice(int verticeId) {
        if (!listaAdyacencia.containsKey(verticeId)) {
            listaAdyacencia.put(verticeId, new ArrayList<Arco<T>>());
            cantVertices++;
        }

    }

    /**
     * Complejidad: O(V + A), donde V es el numero de vertices y A es el numero de arcos adyacentes al vertice que se esta
     * eliminando.
     * Se debe recorrer la lista de adyacencia y eliminar todas las referencias a dicho vertice.
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
     * Complejidad: O(V), donde V es el numero de vertices.
     * se recorre la lista de adyacencia del vértice de origen y se elimina el arco correspondiente
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
     * Complejidad: O(n), donde n es la cantidad de arcos en la lista de adyacencia del vertice dado.
     * El metodo existeArco verifica si existe un arco entre dos vertices dados.
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
     * El metodo obtenerArco obtiene un arco especifico entre dos vertices dados.
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
     * Complejidad: O(n), donde n es la cantidad de arcos en la lista de adyacencia del vertice dado.
     * Devuelve un iterador sobre los vertices adyacentes al vertice dado
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
     * Complejidad: O(n), donde n es la cantidad total de arcos en todas las listas de adyacencia.
     * Devuelve un iterador sobre todos los arcos del grafo.
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
     * Complejidad: O(n), donde n es la cantidad de arcos en la lista de adyacencia del vértice dado.
     * Devuelve un iterador sobre los arcos del vértice dado.
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
