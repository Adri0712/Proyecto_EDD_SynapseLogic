package EDD;

public class Lista<T> {

    private NodoLista<T> head;
    private int size;

    public Lista() {
        this.head = null;
        this.size = 0;
    }

    // agrega al final de la lista
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

    // agrega al inicio, usado como pila en DFS
    public void agregarAlInicio(T dato) {
        NodoLista<T> nuevo = new NodoLista<>(dato);
        nuevo.pNext = head;
        head = nuevo;
        size++;
    }

    // elimina y retorna el primero, usado como cola en BFS
    public T removeFirst() {
        if (head == null) return null;
        T dato = head.dato;
        head = head.pNext;
        size--;
        return dato;
    }

    // elimina la primera ocurrencia del dato
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

    public boolean isEmpty() {
        return head == null;
    }

    public NodoLista<T> getHead() {
        return head;
    }

    public void setHead(NodoLista<T> head) {
        this.head = head;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
