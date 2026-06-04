package Control;

import EDD.Lista;
import EDD.NodoLista;
import EDD.TablaHash;
import ModeloNeuronal.Neurona;
import ModeloNeuronal.Neurotransmisor;
import ModeloNeuronal.Sinapsis;


public class GrafoDirigido extends RedCompleja {

    private Lista<Neurona> nodos;
    private int numeroRegionesAisladas;
    private TablaHash diccionarioNeuroT;

    public GrafoDirigido(TablaHash diccionario) {
        this.nodos = new Lista<>();
        this.numeroRegionesAisladas = 0;
        this.diccionarioNeuroT = diccionario;
    }

    @Override
    public void agregarNeurona(String id, double k) {
        if (buscarNeurona(id) == null) {
            Neurona nueva = new Neurona(k, id);
            nodos.agregar(nueva);
        }
    }

@Override
public void eliminarNeurona(String id) {

    Neurona neuronaEliminar = buscarNeurona(id);
    if (neuronaEliminar == null) {
        return; 
    }
    
 
    NodoLista<Neurona> aux = nodos.getHead();
    while (aux != null) {
       
        NodoLista<Sinapsis> auxSinapsis = aux.dato.getListaAdyacencia().getHead();
        while (auxSinapsis != null) {
            Sinapsis sinapsis = auxSinapsis.dato;
           
            if (sinapsis.getDestino().getId().equals(id)) {
                aux.dato.getListaAdyacencia().eliminar(sinapsis);
                break; 
            }
            auxSinapsis = auxSinapsis.pNext;
        }
        aux = aux.pNext;
    }
    
    nodos.eliminar(neuronaEliminar);
}


    public void recalcularPesos() {
        NodoLista<Neurona> auxNodo = nodos.getHead();
        while (auxNodo != null) {
            Neurona origen = auxNodo.dato;
            double k = origen.getEficienciak();

            NodoLista<Sinapsis> auxSinapsis = origen.getListaAdyacencia().getHead();
            while (auxSinapsis != null) {
                Sinapsis sinapsis = auxSinapsis.dato;
                String idNeuroT = sinapsis.getIdNeuroT();
                
                Neurotransmisor neuroT = diccionarioNeuroT.buscarNeurotransmisor(idNeuroT);
                if (neuroT != null) {
                    double velocidad = neuroT.getVelocidadV();
                    double distancia = sinapsis.getDistancia();
                    // peso = d / (v * k)
                    double peso = distancia / (velocidad * k);
                    sinapsis.setPesoW(peso);
                }
                auxSinapsis = auxSinapsis.pNext;
            }
            auxNodo = auxNodo.pNext;
        }
    }


    public void recalcularPesos(double factorFatiga) {
        NodoLista<Neurona> auxNodo = nodos.getHead();
        while (auxNodo != null) {
            Neurona origen = auxNodo.dato;
            double k = origen.getEficienciak() * factorFatiga;

            NodoLista<Sinapsis> auxSinapsis = origen.getListaAdyacencia().getHead();
            while (auxSinapsis != null) {
                Sinapsis sinapsis = auxSinapsis.dato;
                String idNeuroT = sinapsis.getIdNeuroT();
                
                Neurotransmisor neuroT = diccionarioNeuroT.buscarNeurotransmisor(idNeuroT);
                if (neuroT != null) {
                    double velocidad = neuroT.getVelocidadV();
                    double distancia = sinapsis.getDistancia();
                    double peso = distancia / (velocidad * k);
                    sinapsis.setPesoW(peso);
                }
                auxSinapsis = auxSinapsis.pNext;
            }
            auxNodo = auxNodo.pNext;
        }
    }


    public void simularDeterioro() {
        NodoLista<Neurona> aux = nodos.getHead();
        while (aux != null) {
            aux.dato.setEficienciak(aux.dato.getEficienciak() * 1.2);
            aux = aux.pNext;
        }
        recalcularPesos();
    }


    public Neurona buscarNeurona(String id) {
        NodoLista<Neurona> aux = nodos.getHead();
        while (aux != null) {
            if (aux.dato.getId().equals(id)) {
                return aux.dato;
            }
            aux = aux.pNext;
        }
        return null;
    }

    public void agregarArista(String origen, String destino, double distancia, 
                           String idNeuroT, double k) {
        Neurona neuronaOrigen = buscarNeurona(origen);
        Neurona neuronaDestino = buscarNeurona(destino);
        
        if (neuronaOrigen != null && neuronaDestino != null) {
            Sinapsis sinapsis = new Sinapsis(neuronaDestino, distancia, idNeuroT);
            // Calcular peso inicial
            Neurotransmisor nt = diccionarioNeuroT.buscarNeurotransmisor(idNeuroT);
            if (nt != null) {
                double peso = distancia / (nt.getVelocidadV() * k);
                sinapsis.setPesoW(peso);
            }
            neuronaOrigen.getListaAdyacencia().agregar(sinapsis);
        } else {
            // Crear neuronas si no existen
            if (neuronaOrigen == null) {
                agregarNeurona(origen, k);
                neuronaOrigen = buscarNeurona(origen);
            }
            if (neuronaDestino == null) {
                agregarNeurona(destino, k);
                neuronaDestino = buscarNeurona(destino);
            }
            Sinapsis sinapsis = new Sinapsis(neuronaDestino, distancia, idNeuroT);
            Neurotransmisor nt = diccionarioNeuroT.buscarNeurotransmisor(idNeuroT);
            if (nt != null) {
                double peso = distancia / (nt.getVelocidadV() * k);
                sinapsis.setPesoW(peso);
            }
            neuronaOrigen.getListaAdyacencia().agregar(sinapsis);
        }
    }

    public Lista<Neurona> getNodos() { return nodos; }
    public void setNodos(Lista<Neurona> nodos) { this.nodos = nodos; }
    
    public int getNumeroRegionesAisladas() { return numeroRegionesAisladas; }
    public void setNumeroRegionesAisladas(int numeroRegionesAisladas) { 
        this.numeroRegionesAisladas = numeroRegionesAisladas; 
    }

    public TablaHash getDiccionarioNeuroT() { return diccionarioNeuroT; }
    public void setDiccionarioNeuroT(TablaHash diccionarioNeuroT) { 
        this.diccionarioNeuroT = diccionarioNeuroT; 
    }
}