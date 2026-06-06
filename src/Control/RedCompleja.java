/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

/**

 * @author Adriana

  * Clase abstracta que define la interfaz estructural y el contrato base para 
 * cualquier tipo de Red Compleja o Grafo dentro del sistema.
 * Establece los métodos esenciales para la manipulación dinámica de los vértices 
 * (neuronas), abstrayendo la lógica interna de almacenamiento que implementen 
 * sus clases derivadas comografoDirigifo
 *
 * */
public abstract class RedCompleja {
    
    /**
     * Define la firma obligatoria para incorporar una nueva neurona (vértice) 
     * a la estructura de la red, asegurando su correcto registro en los contenedores.
     *
     * @param id el identificador único o etiqueta alfanumérica que representará a la neurona
     * @param k el coeficiente de eficiencia intrínseco inicial asignado a dicha neurona
     */
    public abstract void agregarNeurona(String id, double k);

    /**
     * Define la firma obligatoria para remover una neurona de la red utilizando 
     * su identificador único. Las clases que hereden de esta interfaz deben asegurar 
     * también la desvinculación completa de todas las sinapsis (aristas) asociadas 
     * a esta neurona para no dejar referencias rotas o huérfanas en el grafo.
     *
     * @param id el identificador único de la neurona que se desea dar de baja en el sistema
     */
    public abstract void eliminarNeurona(String id);
    
}