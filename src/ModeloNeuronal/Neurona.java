package ModeloNeuronal;

import EDD.Lista;
import EDD.NodoLista;

public class Neurona extends Nodo {

    private double eficienciak;
    private Lista<Sinapsis> listaAdyacencia;

    public Neurona(double eficienciak, String id) {
        super(id);
        this.eficienciak = eficienciak;
        this.listaAdyacencia = new Lista<>();
    }

    public double getEficienciak() {
        return eficienciak;
    }

    public void setEficienciak(double eficienciak) {
        this.eficienciak = eficienciak;
    }

    public Lista<Sinapsis> getListaAdyacencia() {
        return listaAdyacencia;
    }

    public void setListaAdyacencia(Lista<Sinapsis> listaAdyacencia) {
        this.listaAdyacencia = listaAdyacencia;
    }

    public void agregarSinapsis(Sinapsis sinapsis) {
        this.listaAdyacencia.agregar(sinapsis);
    }

    public void eliminarSinapsis(String idDestino) {
        NodoLista<Sinapsis> aux = listaAdyacencia.getHead();
        while (aux != null) {
            if (aux.dato.getDestino().getId().equals(idDestino)) {
                listaAdyacencia.eliminar(aux.dato);
                return;
            }
            aux = aux.pNext;
        }
    }

    @Override
    public String toString() {
        return id + " (k=" + String.format("%.3f", eficienciak) + ")";
    }
}
