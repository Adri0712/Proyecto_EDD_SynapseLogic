package ModeloNeuronal;

import EDD.Lista;
import EDD.NodoLista;

/**

 * @author Adriana

 * 
 *  * Clase que representa una Neurona dentro de la red simulación.
 * Modela un vértice o nodo especializado de un grafo dirigido, heredando el identificador
 * base de la clase nodo. Cada neurona posee un coeficiente de eficiencia propio
 * y gestiona sus conexiones salientes mediante una lista de adyacencia manual.
 */
public class Neurona extends Nodo {

    /**
     * Coeficiente de eficiencia intrínseco de la neurona (k).
     * Modula la transmisión de señales e influye directamente en el cálculo 
     * matemático del peso final W de sus sinapsis.
     */
    private double eficienciak;

    /**
     * Lista de adyacencia manual que almacena las conexiones salientes sinapsisi
     * hacia otras neuronas destino del grafo.
     */
    private Lista<Sinapsis> listaAdyacencia;

    /**
     * Constructor de la clase. Inicializa una nueva neurona con su coeficiente de 
     * eficiencia e identificador único, y crea una lista de adyacencia vacía.
     *
     * @param eficienciak el coeficiente de eficiencia inicial de la neurona
     * @param id el identificador único o etiqueta alfanumérica de la neurona
     */
    public Neurona(double eficienciak, String id) {
        super(id);
        this.eficienciak = eficienciak;
        this.listaAdyacencia = new Lista<>();
    }

    /**
     * Obtiene el coeficiente de eficiencia actual de la neurona.
     *
     * @return el valor numérico double de la eficiencia 'k'
     */
    public double getEficienciak() {
        return eficienciak;
    }

    /**
     * Modifica el coeficiente de eficiencia de la neurona.
     *
     * @param eficienciak el nuevo valor numérico de eficiencia a asignar
     */
    public void setEficienciak(double eficienciak) {
        this.eficienciak = eficienciak;
    }

    /**
     * Obtiene la lista de adyacencia manual que contiene todas las sinapsis salientes.
     *
     * @return un objeto Lista cargado con las sinapsis de la neurona
     */
    public Lista<Sinapsis> getListaAdyacencia() {
        return listaAdyacencia;
    }

    /**
     * Asigna o reemplaza por completo la lista de adyacencia de la neurona.
     *
     * @param listaAdyacencia la nueva Lista de sinapsis a vincular
     */
    public void setListaAdyacencia(Lista<Sinapsis> listaAdyacencia) {
        this.listaAdyacencia = listaAdyacencia;
    }

    /**
     * Registra una nueva conexión sináptica saliente (arista) en la lista de adyacencia.
     *
     * @param sinapsis el objeto  Sinapsis que conecta esta neurona con una de destino
     */
    public void agregarSinapsis(Sinapsis sinapsis) {
        this.listaAdyacencia.agregar(sinapsis);
    }

    /**
     * Busca y remueve una conexión sináptica dirigida basándose en el ID de la neurona destino.
     * Realiza un recorrido lineal por la lista de adyacencia y, al encontrar la coincidencia,
     * rompe el enlace liberando la arista del grafo.
     *
     * @param idDestino el identificador único de la neurona destino cuya sinapsis se desea eliminar
     */
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

    /**
     * Devuelve una representación textual legible de la neurona, incluyendo su ID
     * y su coeficiente de eficiencia formateado a tres decimales.
     *
     * @return una cadena de caracteres  String con los detalles de la neurona
     */
    @Override//para cambiarle la funcion al metodo
    public String toString() {
        return id + " (k=" + String.format("%.3f", eficienciak) + ")";
    }
}