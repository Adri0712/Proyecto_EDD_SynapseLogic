package EDD;

import ModeloNeuronal.Neurotransmisor;

public class TablaHash extends EstructuraDeDatos {

    private int tamanoTabla;
    private int cantidadElementos;
    private Lista<ElementoHash>[] buckets;

    // par clave-valor que va en cada cubeta
    private static class ElementoHash {
        String clave;
        Neurotransmisor valor;

        ElementoHash(String clave, Neurotransmisor valor) {
            this.clave = clave;
            this.valor = valor;
        }
    }

    // constructor, tamaño inicial 11 (primo)
    @SuppressWarnings("unchecked")
    public TablaHash() {
        this.tamanoTabla = 11;
        this.cantidadElementos = 0;
        this.buckets = new Lista[tamanoTabla];
        for (int i = 0; i < tamanoTabla; i++) {
            buckets[i] = new Lista<>();
        }
    }

    // funcion hash polinomica, multiplica por 31 para dispersar colisiones
    private int funcionHash(String clave, int modulo) {
        int hash = 0;
        for (int i = 0; i < clave.length(); i++) {
            hash = 31 * hash + clave.charAt(i);
        }
        return Math.abs(hash) % modulo;
    }

    // insertar o actualizar un neurotransmisor
    public void almacenarNeurotransmisor(String clave, Neurotransmisor nt) {
        int indice = funcionHash(clave, this.tamanoTabla);
        NodoLista<ElementoHash> aux = buckets[indice].getHead();

        // si ya existe la clave, se actualiza
        while (aux != null) {
            if (aux.dato.clave.equals(clave)) {
                aux.dato.valor = nt;
                return;
            }
            aux = aux.pNext;
        }

        // redimensionar si la carga supera 75%
        if ((double)(cantidadElementos + 1) / tamanoTabla >= 0.75) {
            redimensionarTabla();
            indice = funcionHash(clave, this.tamanoTabla);
        }

        buckets[indice].agregar(new ElementoHash(clave, nt));
        this.cantidadElementos++;
    }

    // buscar neurotransmisor por clave
    public Neurotransmisor buscarNeurotransmisor(String clave) {
        int indice = funcionHash(clave, this.tamanoTabla);
        NodoLista<ElementoHash> aux = buckets[indice].getHead();
        while (aux != null) {
            if (aux.dato.clave.equals(clave)) {
                return aux.dato.valor;
            }
            aux = aux.pNext;
        }
        return null;
    }

    // retorna todos los neurotransmisores almacenados
    public Lista<Neurotransmisor> obtenerTodos() {
        Lista<Neurotransmisor> resultado = new Lista<>();
        for (int i = 0; i < tamanoTabla; i++) {
            NodoLista<ElementoHash> aux = buckets[i].getHead();
            while (aux != null) {
                resultado.agregar(aux.dato.valor);
                aux = aux.pNext;
            }
        }
        return resultado;
    }

    // duplica el tamaño y reinserta todos los elementos
    @SuppressWarnings("unchecked")
    private void redimensionarTabla() {
        int nuevoTamano = obtenerSiguientePrimo(this.tamanoTabla * 2);
        Lista<ElementoHash>[] viejos = this.buckets;

        this.buckets = new Lista[nuevoTamano];
        for (int i = 0; i < nuevoTamano; i++) {
            this.buckets[i] = new Lista<>();
        }
        this.tamanoTabla = nuevoTamano;
        this.cantidadElementos = 0;

        for (Lista<ElementoHash> cubeta : viejos) {
            NodoLista<ElementoHash> aux = cubeta.getHead();
            while (aux != null) {
                almacenarNeurotransmisor(aux.dato.clave, aux.dato.valor);
                aux = aux.pNext;
            }
        }
    }

    // encuentra el siguiente numero primo mayor o igual a numero
    private int obtenerSiguientePrimo(int numero) {
        if (numero % 2 == 0) numero++;
        while (true) {
            boolean esPrimo = true;
            for (int i = 3; (long) i * i <= numero; i += 2) {
                if (numero % i == 0) {
                    esPrimo = false;
                    break;
                }
            }
            if (esPrimo) return numero;
            numero += 2;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void limpiar() {
        this.tamanoTabla = 11;
        this.cantidadElementos = 0;
        this.buckets = new Lista[tamanoTabla];
        for (int i = 0; i < tamanoTabla; i++) {
            buckets[i] = new Lista<>();
        }
    }

    @Override
    public int getCantidad() {
        return cantidadElementos;
    }

    public int getCantidadElementos() {
        return cantidadElementos;
    }
}
