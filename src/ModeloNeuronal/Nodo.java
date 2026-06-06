/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloNeuronal;

/**

 * @author Adriana

*  * Clase base que representa un Nodo genérico dentro de un modelo estructural.
 * Sirve como la superclase o clase "padre" en la jerarquía de herencia del sistema,
 * proveyendo un identificador alfanumérico único obligatorio. Es la base conceptual 
 * sobre la cual se especializan entidades más complejas como la clase neurona
 *
 */
public class Nodo {
    
    /**
     * Identificador alfanumérico único (etiqueta) que distingue a este nodo de 
     * cualquier otro dentro del sistema o grafo (por ejemplo, "N1", "A").
     * Al estar definido como protected, permite el acceso directo y eficiente 
     * desde las clases hijas que lo hereden.
     */
    protected String id;

    /**
     * Constructor de la clase. Inicializa un nuevo objeto Nodo asignándole 
     * su identificador único obligatorio.
     *
     * @param id la cadena de caracteres  String que servirá como identificador único
     */
    public Nodo(String id) {
        this.id = id;
    }

    /**
     * Obtiene el identificador único que tiene asignado este nodo.
     *
     * @return una cadena de texto String con el ID del nodo
     */
    public String getId() {
        return id;
    }

    /**
     * Permite modificar o reasignar el identificador único del nodo.
     *
     * @param id la nueva cadena de texto que se establecerá como ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Retorna una representación textual del nodo. En este modelo base, la 
     * representación equivale directamente a su identificador único.
     *
     * @return la cadena de caracteres String que identifica al nodo
     */
    @Override
    public String toString() {
        return id;
    }
}
