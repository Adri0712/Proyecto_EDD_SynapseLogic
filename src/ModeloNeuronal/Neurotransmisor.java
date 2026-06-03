/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloNeuronal;

/**
 *
 * @author Adriana
 */
public class Neurotransmisor {
    private String id;
    private String nombre;
    private double velocidadV;

    public Neurotransmisor(String id, String nombre, double velocidadV) {
        this.id = id;
        this.nombre = nombre;
        this.velocidadV = velocidadV;
    }

    public double getVelocidadV() {
        return velocidadV;
    }

    public void setVelocidadV(double velocidadV) {
        this.velocidadV = velocidadV;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void buscarNeurotransmisor(){
        
    }
    
    
    
    
    
    
}
