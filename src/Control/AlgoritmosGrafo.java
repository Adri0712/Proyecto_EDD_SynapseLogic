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
}
