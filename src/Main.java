import main.java.grafo.GrafoDirigido;
import main.java.servicios.ServicioBFS;
import main.java.servicios.ServicioCaminos;
import main.java.servicios.ServicioDFS;

public class Main {
    public static void main(String[] args) {
        GrafoDirigido<String> grafo = new GrafoDirigido<>();
        grafo.agregarVertice(0);
        grafo.agregarVertice(1);
        grafo.agregarVertice(2);
        grafo.agregarVertice(3);
        grafo.agregarArco(0, 1, "A");
        grafo.agregarArco(1, 2, "B");
        grafo.agregarArco(2, 3, "C");
        grafo.agregarArco(0, 3, "D");


        // System.out.println(grafo.contieneVertice(3));
        // grafo.borrarVertice(3);
        // System.out.println(grafo.contieneVertice(3));

        //System.out.println(grafo.existeArco(0, 1));
        //grafo.borrarArco(0,1);
        //System.out.println(grafo.existeArco(0,1));
        System.out.println(grafo.obtenerArco(1,2));
        System.out.println(grafo.cantidadArcos());
        System.out.println(grafo.obtenerVertices());
        System.out.println(grafo.obtenerAdyacentes(1));

        ServicioCaminos<String> servicioCaminos = new ServicioCaminos<>(grafo,0,3,3);
        System.out.println(servicioCaminos.caminos());

        ServicioBFS<String> servicioBFS = new ServicioBFS<>(grafo);
        System.out.println(servicioBFS.bfsForest());

        ServicioDFS<String> servicioDFS = new ServicioDFS<>(grafo);
        System.out.println(servicioDFS.dfsForest());
    }
}