package ModeloNeuronal;

public class Neurotransmisor {

    private String id;
    private String nombre;
    private double velocidadV;
    private String efecto;
    private String descripcion;

    public Neurotransmisor(String id, String nombre, double velocidadV, String efecto, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.velocidadV = velocidadV;
        this.efecto = efecto;
        this.descripcion = descripcion;
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

    public double getVelocidadV() {
        return velocidadV;
    }

    public void setVelocidadV(double velocidadV) {
        this.velocidadV = velocidadV;
    }

    public String getEfecto() {
        return efecto;
    }

    public void setEfecto(String efecto) {
        this.efecto = efecto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return id + " | " + nombre + " | v=" + velocidadV + " | " + efecto;
    }
}
