package EDD;

/**

 * @author Adriana

 * @param <T> el tipo de dato del elemento que será almacenado dentro del nodo
 *  * Clase genérica que representa un Nodo o eslabón individual para una Lista Enlazada.
 * Actúa como un contenedor básico que encapsula un elemento de información y 
 * mantiene una referencia hacia el siguiente componente en la secuencia.
 *
 */
public class NodoLista<T> {

    /**
     * El valor de tipo {@code T} real que contiene este nodo (por ejemplo, 
     * una Neurona, una Sinapsis o una Cadena de texto).
     */
    public T dato;

    /**
     * Puntero o referencia dirigida hacia el siguiente nodo ({@code NodoLista}) en la lista.
     * Si es el último nodo de la estructura, su valor es {@code null}.
     */
    public NodoLista<T> pNext;

    /**
     * Constructor de la clase. Inicializa un nuevo eslabón con el dato provisto
     * y establece por defecto la referencia al siguiente nodo ({@code pNext}) en {@code null}.
     *
     * @param dato el objeto de tipo {@code T} que se resguardará de forma interna
     */
    public NodoLista(T dato) {
        this.dato = dato;
        this.pNext = null;
    }
}
