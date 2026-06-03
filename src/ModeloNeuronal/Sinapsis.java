/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloNeuronal;

/**
 *
 * @author Adriana
 */
public class Sinapsis {//esta es la arista dirigida
    private Neurona destino;
    private double distancia;
    private String idNeuroT;
    private double pesoW;

    public Sinapsis(Neurona destino, double distancia, String idNeuroT) {
        this.destino = destino;
        this.distancia = distancia;
        this.idNeuroT = idNeuroT;
        this.pesoW = 0.0;
    }

    public Neurona getDestino() {
        return destino;
    }

    public void setDestino(Neurona destino) {
        this.destino = destino;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public String getIdNeuroT() {
        return idNeuroT;
    }

    public void setIdNeuroT(String idNeuroT) {
        this.idNeuroT = idNeuroT;
    }

    public double getPesoW() {
        return pesoW;
    }

    public void setPesoW(double pesoW) {
        this.pesoW = pesoW;
    }
    
    
    
    
    
    
    
}
