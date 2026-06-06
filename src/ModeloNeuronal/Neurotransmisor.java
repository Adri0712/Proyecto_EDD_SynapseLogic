package ModeloNeuronal;

/**

 * @author Adriana

 * 
 * Clase que representa un Neurotransmisor dentro del modelo biomatemático de la red.
 * Encapsula las propiedades cinéticas y bioquímicas particulares de las sustancias 
 * químicas (como Dopamina, Serotonina, etc.) que median la sinapsis. 
 * Estas instancias son almacenadas y consultadas dinámicamente en una estructura indexada 
 * (como una tabla hash) para determinar la velocidad de propagación de los impulsos.
 *
 */
public class Neurotransmisor {

    /**
     * Identificador alfanumérico único del neurotransmisor (por ejemplo, "NT01", "ACH").
     * Actúa como la clave primaria para las búsquedas dentro de la estructura hash.
     */
    private String id;

    /**
     * Nombre común o biológico de la sustancia química (por ejemplo, "Acetilcolina").
     */
    private String nombre;

    /**
     * Velocidad de transmisión o propagación (v) asociada a este neurotransmisor.
     * Influye de forma directa y proporcional en los cálculos de coste o peso de las sinapsis.
     */
    private double velocidadV;

    /**
     * Clasificación del impacto del neurotransmisor sobre el sistema (por ejemplo, "Excitatorio" o "Inhibitorio").
     */
    private String efecto;

    /**
     * Detalle o descripción textual extendida sobre las funciones o el rol clínico de la sustancia.
     */
    private String descripcion;

    /**
     * Constructor completo de la clase. Inicializa un neurotransmisor asignando
     * de forma inmediata cada uno de sus atributos funcionales y clínicos.
     *
     * @param id clave única o etiqueta alfanumérica de indexación
     * @param nombre designación biológica de la sustancia
     * @param velocidadV tasa o velocidad numérica con la que se transmite el impulso
     * @param efecto comportamiento operativo de la sustancia (excitatorio/inhibitorio)
     * @param descripcion explicación detallada de las propiedades o contexto biológico
     */
    public Neurotransmisor(String id, String nombre, double velocidadV, String efecto, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.velocidadV = velocidadV;
        this.efecto = efecto;
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el identificador único del neurotransmisor.
     *
     * @return una cadena de caracteres  String que representa el ID
     */
    public String getId() {
        return id;
    }

    /**
     * Modifica o asigna la clave alfanumérica única del neurotransmisor.
     *
     * @param id la nueva cadena de texto que actuará como ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre legible o biológico asignado a la sustancia.
     *
     * @return el nombre String del neurotransmisor
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre representativo del neurotransmisor.
     *
     * @param nombre el nuevo nombre biológico a asignar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la velocidad cinética de transmisión del neurotransmisor.
     *
     * @return el valor numérico double de la velocidad 'v'
     */
    public double getVelocidadV() {
        return velocidadV;
    }

    /**
     * Establece o reajusta la velocidad operativa de la sustancia.
     *
     * @param velocidadV la nueva tasa de velocidad de tipo double
     */
    public void setVelocidadV(double velocidadV) {
        this.velocidadV = velocidadV;
    }

    /**
     * Obtiene la naturaleza o tipo de efecto (excitatorio/inhibitorio) del neurotransmisor.
     *
     * @return una cadena de texto String que califica el efecto
     */
    public String getEfecto() {
        return efecto;
    }

    /**
     * Define o altera la categorización del efecto biológico de la sustancia.
     *
     * @param efecto el nuevo tipo de impacto regulador a registrar
     */
    public void setEfecto(String efecto) {
        this.efecto = efecto;
    }

    /**
     * Obtiene la descripción o reseña bibliográfica del componente químico.
     *
     * @return la cadena  String descriptiva
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Modifica el texto informativo o reseña descriptiva del neurotransmisor.
     *
     * @param descripcion el nuevo bloque explicativo a almacenar
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Genera una representación textual formateada con barras verticales que resume 
     * los datos esenciales de la sustancia (ID, nombre, velocidad y efecto). 
     * Es ideal para listados tabulares o visualizaciones rápidas en consola e interfaces.
     *
     * @return una cadena de caracteres String estructurada con las propiedades
     */
    @Override
    public String toString() {
        return id + " | " + nombre + " | v=" + velocidadV + " | " + efecto;
    }
}