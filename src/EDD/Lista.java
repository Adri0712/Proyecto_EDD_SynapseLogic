package EDD;

/**

 * @author Adriana

 * @param <T> el tipo de datos de los elementos almacenados dentro de la lista
 * 
 * 
 *  * Clase genérica que implementa una Estructura de Datos de tipo Lista Simplemente Enlazada.
 * Permite almacenar una colección dinámica de elementos de cualquier tipo.
 * Proporciona operaciones optimizadas para el manejo de punteros, permitiendo
 * que sea utilizada de forma eficiente como base para pilas (DFS) y colas (BFS).
 *
 */
public class Lista<T> {

    /**
     * Puntero o referencia al primer nodo de la lista (cabeza). 
     * Es el punto de entrada para cualquier recorrido o manipulación.
     */
    private NodoLista<T> head;

    /**
     * Contador que registra la cantidad actual de elementos almacenados en la lista.
     */
    private int size;

    /**
     * Constructor por defecto. Crea una lista enlazada vacía, inicializando 
     * la cabeza en null y el tamaño en 0.
     */
    public Lista() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Agrega un nuevo elemento al final de la lista.
     * Si la lista está vacía, el elemento se convierte en la cabeza. De lo contrario, 
     * recorre la estructura empleando un nodo auxiliar hasta hallar el último eslabón.
     *
     * @param dato el elemento de tipo T que se añadirá al final
     */
    public void agregar(T dato) {
        NodoLista<T> nuevo = new NodoLista<>(dato);
        if (head == null) {
            head = nuevo;
        } else {
            NodoLista<T> aux = head;
            while (aux.pNext != null) {
                aux = aux.pNext;
            }
            aux.pNext = nuevo;
        }
        size++;
    }

    /**
     * Agrega un nuevo elemento al inicio de la lista (operación push).
     * Modifica el puntero del nuevo nodo para que apunte a la cabeza actual, 
     * convirtiendo al nuevo nodo en la nueva cabeza de la lista.
     * Es fundamental para el correcto funcionamiento de la estructura  en el recorrido DFS.
     *
     * @param dato el elemento de tipo T que se añadirá al inicio
     */
    public void agregarAlInicio(T dato) {
        NodoLista<T> nuevo = new NodoLista<>(dato);
        nuevo.pNext = head;
        head = nuevo;
        size++;
    }

    /**
     * Elimina y retorna el primer elemento ubicado a la cabeza de la lista (operación pop/dequeue).
     * Desplaza el puntero de la cabeza hacia el siguiente nodo de la secuencia.
     * Es fundamental para el correcto funcionamiento de la estructura COLA en el recorrido BFS.
     *
     * @return el elemento de tipo T que se encontraba al inicio de la lista, 
     * o NULLsi la lista está completamente vacía
     */
    public T removeFirst() {
        if (head == null) return null;
        T dato = head.dato;
        head = head.pNext;
        size--;
        return dato;
    }

    /**
     * Busca y elimina la primera ocurrencia del elemento especificado en la lista.
     * Realiza un recorrido lineal reconectando los enlaces de los nodos contiguos 
     * (saltándose el nodo a eliminar) para no perder la integridad de la estructura.
     * Utiliza el método EQUALS para la comparación de los objetos.
     *
     * @param dato el objeto de tipoT que se desea remover de la lista
     * @return TRUE si el elemento fue encontrado y eliminado con éxito; 
     * FALSE si el elemento no existía o si la lista estaba vacía
     */
    public boolean eliminar(T dato) {
        if (head == null) return false;
        if (head.dato.equals(dato)) {
            head = head.pNext;
            size--;
            return true;
        }
        NodoLista<T> aux = head;
        while (aux.pNext != null) {
            if (aux.pNext.dato.equals(dato)) {
                aux.pNext = aux.pNext.pNext;
                size--;
                return true;
            }
            aux = aux.pNext;
        }
        return false;
    }
    
    /**
     * Determina si un elemento específico se encuentra registrado dentro de la lista.
     * Efectúa una búsqueda secuencial desde la cabeza hasta el final de la estructura.
     *
     * @param dato el elemento de tipo T que se desea buscar
     * @returnT si la lista contiene al menos una ocurrencia del elemento; 
     * FALSE en caso contrario
     */
    public boolean contiene(T dato) {
        NodoLista<T> aux = head;
        while (aux != null) {
            if (aux.dato.equals(dato)) {
                return true;
            }
            aux = aux.pNext;
        }
        return false;
    }

    /**
     * Verifica si la lista carece de nodos en su estructura.
     *
     * @return TRUE si la cabeza es NULL; FALSE si contiene elementos
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Obtiene el primer nodo (cabeza) de la lista enlazada.
     * Útil para iniciar iteraciones o recorridos externos manuales.
     *
     * @return el objeto  NodoLista que lidera la lista
     */
    public NodoLista<T> getHead() {
        return head;
    }

    /**
     * Establece o modifica de forma directa el nodo inicial (cabeza) de la lista.
     *
     * @param head el nuevo {@link NodoLista} que se asignará como origen
     */
    public void setHead(NodoLista<T> head) {
        this.head = head;
    }

    /**
     * Obtiene el número de elementos registrados actualmente en la lista.
     *
     * @return un entero INTcon la cantidad total de nodos
     */
    public int getSize() {
        return size;
    }

    /**
     * Modifica manualmente el valor que registra el tamaño de la lista.
     * <i>Nota: Se aconseja precaución con su uso para mantener la consistencia con los nodos reales.</i>
     *
     * @param size el nuevo tamaño entero que se registrará
     */
    public void setSize(int size) {
        this.size = size;
    }
}