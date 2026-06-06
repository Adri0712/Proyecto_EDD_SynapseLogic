package EDD;

/**
 *
 * @author Samuel Djekki

 * @param <T> el tipo de elementos que almacena la pila
 * 
 *  * Clase genérica que representa una estructura de datos de tipo Pila (Stack).
 * Sigue el principio LIFO (Last In, First Out), donde el último elemento 
 * en ingresar es el primero en ser retirado.
 * Se implementa internamente haciendo uso de una lista simplemente enlazada .
 * Es un componente esencial para la ejecución del algoritmo de recorrido en profundidad (DFS).
 */
public class Pila<T> {
    
    /**
     * Lista interna utilizada para almacenar y gestionar las referencias de los elementos en la pila.
     */
    private Lista<T> lista;

    /**
     * Constructor de la clase. Crea una pila vacía e inicializa la lista interna.
     */
    public Pila() {
        lista = new Lista<>();
    }

    /**
     * Introduce un nuevo elemento en el tope de la pila (operación push).
     * Invoca internamente al método AGREGARALINICIO de la lista para asegurar 
     * que el nuevo dato sea el primero disponible al desapilar.
     *
     * @param dato el elemento de tipo T que se desea posicionar en el tope
     */
    public void apilar(T dato) {
        lista.agregarAlInicio(dato);
    }

    /**
     * Extrae, remueve y retorna el elemento que se encuentra actualmente en el tope de la pila (operación pop).
     *
     * @return el elemento de tipoT retirado del tope, 
     * o NULL si la pila se encuentra vacía
     */
    public T desapilar() {
        return lista.removeFirst();
    }

    /**
     * Evalúa si la pila carece de elementos en su interior.
     *
     * @return TRUE si la pila está vacía; FALSE en caso contrario
     */
    public boolean estaVacia() {
        return lista.isEmpty();
    }
}