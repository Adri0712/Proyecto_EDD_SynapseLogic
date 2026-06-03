/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import EDD.Lista;
import EDD.NodoLista;
import EDD.TablaHash;
import ModeloNeuronal.Neurona;
import ModeloNeuronal.Neurotransmisor;
import ModeloNeuronal.Sinapsis;

/**
 *
 * @author Adriana
 */
public class GrafoDirigido extends RedCompleja {
    Lista<Neurona> nodos;
    int numeroRegionesAisladas;
    TablaHash diccionarioNeuroT;

    public GrafoDirigido(TablaHash diccionario) {
        this.nodos = new Lista<>();
        this.numeroRegionesAisladas = 0;
        this.diccionarioNeuroT = diccionario;
    }

    @Override
    public void agregarNeurona(String id, double k) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarNeurona(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void recalcularPesosAristas(Neurona neurona){
        NodoLista<Neurona> auxNodo = nodos.getHead();
        while(auxNodo != null){
            Neurona origen = auxNodo.dato;
            double k = origen.getEficienciak();
            
            NodoLista<Sinapsis> auxSinapsis = origen.getListaAdyacencia().getHead();
            while (auxSinapsis != null){
                Sinapsis sinapsis = auxSinapsis.dato;
                
                
            }            
            
        }
    
    
    }
    
    
}
