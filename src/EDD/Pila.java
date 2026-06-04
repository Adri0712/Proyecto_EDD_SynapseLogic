/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author Samuel Djekki
 */

public class Pila<T> {
    private Lista<T> lista;

    public Pila() {
        lista = new Lista<>();
    }

    public void apilar(T dato) {
        lista.agregarAlInicio(dato);
    }

    public T desapilar() {
        return lista.removeFirst();
    }

    public boolean estaVacia() {
        return lista.isEmpty();
    }
}
