package EDD;

/**

 * @author Adriana

 */
 /** Clase abstracta que sirve como plantilla base para todas las estructuras 
 * de datos del sistema
 * Define el comportamiento esencial e indispensable que cualquier estructura 
 * debe implementar para gestionar sus elementos y su memoria.
 */
public abstract class EstructuraDeDatos {

    /**
     * Vacía por completo la estructura de datos, eliminando todas sus referencias 
     * actuales y restableciendo su estado o capacidad al valor inicial por defecto.
     * Es indispensable para reiniciar los análisis sin dejar residuos en memoria.
     */
    public abstract void limpiar();

    /**
     * Obtiene el número total de elementos o componentes que se encuentran 
     * almacenados actualmente dentro de la estructura de datos.
     *
     * @return un número entero ({@code int}) que representa la cantidad de elementos registrados
     */
    public abstract int getCantidad();
}
