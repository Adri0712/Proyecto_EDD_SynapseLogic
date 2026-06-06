package ModeloNeuronal;

/**
 * @author Adriana

 * Clase que modela una Sinapsis o conexión interneuronal dirigida dentro del sistema.
 * En términos de teoría de grafos, actúa como una arista dirigida y ponderada. 
 * Conecta la neurona propietaria (origen) con una neurona objetivo (destino), 
 * encapsulando las variables biológicas y físicas (distancia, neurotransmisor asociado) 
 * necesarias para computar de forma dinámica el peso matemático pesoW del camino.
 *
 */
public class Sinapsis {

    /**
     * Referencia directa a la neurona destino (vértice terminal de la arista).
     */
    private Neurona destino;

    /**
     * Distancia física o longitud del axón entre las neuronas involucradas. 
     * Afecta inversamente la velocidad de propagación de los impulsos.
     */
    private double distancia;

    /**
     * Identificador alfanumérico único del neurotransmisor asociado a esta sinapsis. 
     * Se utiliza para buscar sus propiedades cinéticas en la tabla hash.
     */
    private String idNeuroT;

    /**
     * Coeficiente de modulación o constante de atenuación específica para la arista.
     */
    private double k;

    /**
     * Peso matemático calculado (W) que determina la fuerza, resistencia o prioridad 
     * de transmisión de esta conexión en los algoritmos de la red.
     */
    private double pesoW;

    /**
     * Constructor de la clase. Inicializa un enlace sináptico con sus atributos base 
     * y establece el peso matemático inicial en 0.0 antes de su evaluación.
     *
     * @param destino objeto  Neuronaque recibirá el impulso eléctrico o señal
     * @param distancia longitud espacial  doublemedida entre origen y destino
     * @param idNeuroT clave o ID del neurotransmisor químico que actúa como puente de señal
     * @param k coeficiente de modulación particular para la arista
     */
    public Sinapsis(Neurona destino, double distancia, String idNeuroT, double k) {
        this.destino = destino;
        this.distancia = distancia;
        this.idNeuroT = idNeuroT;
        this.k = k;
        this.pesoW = 0.0;
    }

    /**
     * Obtiene la neurona destino ubicada en el extremo final de la conexión.
     *
     * @return el objeto Neurona terminal
     */
    public Neurona getDestino() {
        return destino;
    }

    /**
     * Modifica o redirige el destino de la sinapsis hacia otra neurona.
     *
     * @param destino la nueva Neurona receptora de la arista
     */
    public void setDestino(Neurona destino) {
        this.destino = destino;
    }

    /**
     * Obtiene la distancia espacial que separa a los dos nodos de la conexión.
     *
     * @return el valor numérico  double de la distancia
     */
    public double getDistancia() {
        return distancia;
    }

    /**
     * Establece o altera la distancia física del camino axónico.
     *
     * @param distancia el nuevo valor double de longitud
     */
    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    /**
     * Obtiene el identificador del neurotransmisor que regula la brecha sináptica.
     *
     * @return una cadena de texto  String con el ID del neurotransmisor
     */
    public String getIdNeuroT() {
        return idNeuroT;
    }

    /**
     * Asigna o cambia la clave del neurotransmisor químico asociado.
     *
     * @param idNeuroT el nuevo identificador de tipo String
     */
    public void setIdNeuroT(String idNeuroT) {
        this.idNeuroT = idNeuroT;
    }

    /**
     * Obtiene la constante o coeficiente 'k' de modulación de la arista.
     *
     * @return el valor numérico double de 'k'
     */
    public double getK() {
        return k;
    }

    /**
     * Modifica el coeficiente 'k' de la conexión.
     *
     * @param k el nuevo valor numérico del coeficiente a guardar
     */
    public void setK(double k) {
        this.k = k;
    }

    /**
     * Obtiene el peso matemático actual (W) de la arista.
     *
     * @return un double que representa la intensidad o coste del enlace
     */
    public double getPesoW() {
        return pesoW;
    }

    /**
     * Asigna el valor numérico definitivo del peso W calculado tras procesar las 
     * fórmulas de resiliencia del modelo neural.
     *
     * @param pesoW el peso matemático calculado a asociar
     */
    public void setPesoW(double pesoW) {
        this.pesoW = pesoW;
    }

    /**
     * Genera una cadena legible que describe los parámetros técnicos de la sinapsis, 
     * formateando el coeficiente 'k' a tres decimales y el peso 'W' a cuatro decimales 
     * para facilitar los volcados de texto y consolas.
     *
     * @return un formato textual estructurado String con los datos del enlace
     */
    @Override
    public String toString() {
        return "→" + destino.getId() + " [d=" + distancia + ", NT=" + idNeuroT
                + ", k=" + String.format("%.3f", k) + ", W=" + String.format("%.4f", pesoW) + "]";
    }
}