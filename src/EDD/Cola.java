/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

public class Cola<T> {
    private Lista<T> lista;

    public Cola() {
        lista = new Lista<>();
    }

    public void encolar(T dato) {
        lista.agregar(dato);
    }

    public T desencolar() {
        return lista.removeFirst();
    }

    public boolean estaVacia() {
        return lista.isEmpty();
    }
}
