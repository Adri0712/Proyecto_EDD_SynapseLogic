/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author Adriana

 * @param <T> el tipo de elementos que almacena la cola
 Clase genérica que representa una estructura de datos de tipo Cola (Queue).
 * Sigue el principio FIFO (First In, First Out), donde el primer elemento 
 * en entrar es el primero en salir.
 * Se implementa internamente utilizando una lista enlazada .
 * */
public class Cola<T> {
    
    /**
     * Lista interna utilizada para almacenar y gestionar los elementos de la cola.
     */
    private Lista<T> lista;

    /**
     * Crea una cola vacía e inicializa la lista interna.
     */
    public Cola() {
        lista = new Lista<>();
    }

    /**
     * Inserta un nuevo elemento al final de la cola (operación enqueue).
     *
     * @param dato el elemento de tipo {@code T} que se desea agregar a la cola
     */
    public void encolar(T dato) {
        lista.agregar(dato);
    }

    /**
     * Extrae y elimina el elemento que está al frente de la cola (operación dequeue).
     *
     * @return el elemento retirado del frente de la cola, 
     * o {@code null} si la cola está vacía
     */
    public T desencolar() {
        return lista.removeFirst();
    }

    /**
     * Verifica si la cola no contiene elementos.
     *
     * @return {@code true} si la cola está vacía; {@code false} en caso contrario
     */
    public boolean estaVacia() {
        return lista.isEmpty();
    }
}
