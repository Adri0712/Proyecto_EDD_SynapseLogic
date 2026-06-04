package ModeloNeuronal;

public class Sinapsis {

    private Neurona destino;
    private double distancia;
    private String idNeuroT;
    private double k;
    private double pesoW;

    public Sinapsis(Neurona destino, double distancia, String idNeuroT, double k) {
        this.destino = destino;
        this.distancia = distancia;
        this.idNeuroT = idNeuroT;
        this.k = k;
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

    public double getK() {
        return k;
    }

    public void setK(double k) {
        this.k = k;
    }

    public double getPesoW() {
        return pesoW;
    }

    public void setPesoW(double pesoW) {
        this.pesoW = pesoW;
    }

    @Override
    public String toString() {
        return "→" + destino.getId() + " [d=" + distancia + ", NT=" + idNeuroT
                + ", k=" + String.format("%.3f", k) + ", W=" + String.format("%.4f", pesoW) + "]";
    }
}
