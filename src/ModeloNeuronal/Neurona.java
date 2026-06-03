/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloNeuronal;

import EDD.Lista;

/**
 *
 * @author Adriana
 */
public class Neurona extends Nodo{
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
    
    public void agregarSinapsis(Sinapsis sinapsis){
        this.listaAdyacencia.agregar(sinapsis);
    }

    
    


    
    
    
}
