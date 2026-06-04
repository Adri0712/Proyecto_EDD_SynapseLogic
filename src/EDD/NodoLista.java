package EDD;

public class NodoLista<T> {

    public T dato;
    public NodoLista<T> pNext;

    public NodoLista(T dato) {
        this.dato = dato;
        this.pNext = null;
    }
}
