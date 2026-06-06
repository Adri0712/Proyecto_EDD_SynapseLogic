/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import EDD.Lista;
import EDD.NodoLista;
import ModeloNeuronal.Neurona;
import ModeloNeuronal.Sinapsis;
import EDD.Cola;
import EDD.Pila;

    /**
 *
 * @author Samuel Djekki
 */
public class AlgoritmosGrafo {
    
    //BFS realizado
    
    /**
     * Recorre el grafo en anchura (BFS) desde la neurona fuente.
     * @param grafo el grafo a recorrer
     * @param fuente ID de la neurona de inicio
     * @return lista de IDs visitados en orden BFS
     */
    public static Lista<String> bfs(GrafoDirigido grafo, String fuente) {
        Lista<String> visitados = new Lista<>();
        Cola<String> cola = new Cola<>();

        if (grafo.buscarNeurona(fuente) == null) {
            return visitados;
        }

        cola.encolar(fuente);
        visitados.agregar(fuente);

        while (!cola.estaVacia()) {
            String actual = cola.desencolar();
            Neurona neurona = grafo.buscarNeurona(actual);

            if (neurona != null) {
                NodoLista<Sinapsis> aux = neurona.getListaAdyacencia().getHead();
                while (aux != null) {
                    String vecino = aux.dato.getDestino().getId();
                    if (!visitados.contiene(vecino)) {
                        visitados.agregar(vecino);
                        cola.encolar(vecino);
                    }
                    aux = aux.pNext;
                }
            }
        }

        return visitados;
    }
    
    /**
     * Recorre el grafo en profundidad (DFS) desde la neurona fuente.
     * @param grafo el grafo a recorrer
     * @param fuente ID de la neurona de inicio
     * @return lista de IDs visitados en orden DFS
     */
    public static Lista<String> dfs(GrafoDirigido grafo, String fuente) {
        Lista<String> visitados = new Lista<>();
        Pila<String> pila = new Pila<>();

        if (grafo.buscarNeurona(fuente) == null) {
            return visitados;
        }

        pila.apilar(fuente);

        while (!pila.estaVacia()) {
            String actual = pila.desapilar();
            if (!visitados.contiene(actual)) {
                visitados.agregar(actual);
                Neurona neurona = grafo.buscarNeurona(actual);
                if (neurona != null) {
                    NodoLista<Sinapsis> aux = neurona.getListaAdyacencia().getHead();
                    while (aux != null) {
                        pila.apilar(aux.dato.getDestino().getId());
                        aux = aux.pNext;
                    }
                }
            }
        }

        return visitados;
    }
}
