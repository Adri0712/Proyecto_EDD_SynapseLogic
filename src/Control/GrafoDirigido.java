package Control;

import EDD.Lista;
import EDD.NodoLista;
import EDD.TablaHash;
import ModeloNeuronal.Neurona;
import ModeloNeuronal.Neurotransmisor;
import ModeloNeuronal.Sinapsis;
import java.io.BufferedReader;
import java.io.FileReader;

public class GrafoDirigido extends RedCompleja {

    private Lista<Neurona> nodos;
    private int numeroRegionesAisladas;
    private TablaHash diccionarioNeuroT;

    public GrafoDirigido(TablaHash diccionario) {
        this.nodos = new Lista<>();
        this.numeroRegionesAisladas = 0;
        this.diccionarioNeuroT = diccionario;
    }

    // ── Nodos ──────────────────────────────────────────────────────────────

    @Override
    /**
     * Agrega una neurona al grafo si no existe ya.
     * @param id identificador unico de la neurona
     * @param k coeficiente de eficiencia inicial
     */
    public void agregarNeurona(String id, double k) {
        if (buscarNeurona(id) == null) {
            nodos.agregar(new Neurona(k, id));
        }
    }

    @Override
    public void eliminarNeurona(String id) {
        Neurona objetivo = buscarNeurona(id);
        if (objetivo == null) return;

        NodoLista<Neurona> aux = nodos.getHead();
        while (aux != null) {
            aux.dato.eliminarSinapsis(id);
            aux = aux.pNext;
        }
        nodos.eliminar(objetivo);
    }

    public Neurona buscarNeurona(String id) {
        NodoLista<Neurona> aux = nodos.getHead();
        while (aux != null) {
            if (aux.dato.getId().equals(id)) return aux.dato;
            aux = aux.pNext;
        }
        return null;
    }

    // ── Aristas ────────────────────────────────────────────────────────────

    public void agregarArista(String origen, String destino, double distancia,
                              String idNeuroT, double k) {
        if (buscarNeurona(origen) == null)  agregarNeurona(origen, 1.0);
        if (buscarNeurona(destino) == null) agregarNeurona(destino, 1.0);

        Neurona nOrigen  = buscarNeurona(origen);
        Neurona nDestino = buscarNeurona(destino);

        Sinapsis sinapsis = new Sinapsis(nDestino, distancia, idNeuroT, k);
        Neurotransmisor nt = diccionarioNeuroT.buscarNeurotransmisor(idNeuroT);
        if (nt != null && nt.getVelocidadV() > 0 && k > 0) {
            sinapsis.setPesoW(distancia / (nt.getVelocidadV() * k));
        }
        nOrigen.getListaAdyacencia().agregar(sinapsis);
    }

    public void eliminarArista(String idOrigen, String idDestino) {
        Neurona nOrigen = buscarNeurona(idOrigen);
        if (nOrigen != null) nOrigen.eliminarSinapsis(idDestino);
    }

    // ── Recalcular pesos (usa k de cada sinapsis) ──────────────────────────

    public void recalcularPesos() {
        NodoLista<Neurona> auxN = nodos.getHead();
        while (auxN != null) {
            NodoLista<Sinapsis> auxS = auxN.dato.getListaAdyacencia().getHead();
            while (auxS != null) {
                Sinapsis s = auxS.dato;
                Neurotransmisor nt = diccionarioNeuroT.buscarNeurotransmisor(s.getIdNeuroT());
                if (nt != null && nt.getVelocidadV() > 0 && s.getK() > 0) {
                    s.setPesoW(s.getDistancia() / (nt.getVelocidadV() * s.getK()));
                }
                auxS = auxS.pNext;
            }
            auxN = auxN.pNext;
        }
    }

    // ── Simular deterioro cognitivo: k × 1.2 en cada sinapsis ─────────────

    /**
     * Simula deterioro cognitivo multiplicando k por 1.2 en cada sinapsis
     * y recalculando todos los pesos W.
     */
    public void simularDeterioro() {
        NodoLista<Neurona> auxN = nodos.getHead();
        while (auxN != null) {
            NodoLista<Sinapsis> auxS = auxN.dato.getListaAdyacencia().getHead();
            while (auxS != null) {
                auxS.dato.setK(auxS.dato.getK() * 1.2);
                auxS = auxS.pNext;
            }
            auxN = auxN.pNext;
        }
        recalcularPesos();
    }

    // ── Dijkstra O(V²) ─────────────────────────────────────────────────────
    // Devuelve lista de IDs con la ruta más rápida; vacía si no existe camino

    /**
     * Calcula la ruta mas rapida entre dos neuronas usando Dijkstra O(V^2).
     * @param idOrigen neurona de inicio
     * @param idDestino neurona de llegada
     * @return lista con los IDs en orden, vacia si no hay camino
     */
    public Lista<String> dijkstra(String idOrigen, String idDestino) {
        int n = nodos.getSize();
        Lista<String> ruta = new Lista<>();
        if (n == 0) return ruta;

        int ori  = indiceNeurona(idOrigen);
        int dest = indiceNeurona(idDestino);
        if (ori == -1 || dest == -1) return ruta;

        double[]  dist     = new double[n];
        boolean[] visitado = new boolean[n];
        int[]     anterior = new int[n];

        for (int i = 0; i < n; i++) {
            dist[i]     = Double.MAX_VALUE;
            visitado[i] = false;
            anterior[i] = -1;
        }
        dist[ori] = 0;

        for (int iter = 0; iter < n; iter++) {
            int u = -1;
            for (int i = 0; i < n; i++) {
                if (!visitado[i] && (u == -1 || dist[i] < dist[u])) u = i;
            }
            if (u == -1 || dist[u] == Double.MAX_VALUE) break;
            visitado[u] = true;

            NodoLista<Sinapsis> auxS = neuronaEnIndice(u).getListaAdyacencia().getHead();
            while (auxS != null) {
                int v = indiceNeurona(auxS.dato.getDestino().getId());
                if (v != -1 && !visitado[v]) {
                    double nd = dist[u] + auxS.dato.getPesoW();
                    if (nd < dist[v]) {
                        dist[v]     = nd;
                        anterior[v] = u;
                    }
                }
                auxS = auxS.pNext;
            }
        }

        if (dist[dest] == Double.MAX_VALUE) return ruta;

        int actual = dest;
        while (actual != -1) {
            ruta.agregarAlInicio(neuronaEnIndice(actual).getId());
            actual = anterior[actual];
        }
        return ruta;
    }

    // ── Zonas aisladas y conectividad ──────────────────────────────────────

    /**
     * Detecta las neuronas que no son alcanzables desde la neurona fuente.
     * @param idFuente neurona desde donde se explora
     * @return lista de IDs de neuronas aisladas
     */
    public Lista<String> detectarZonasAisladas(String idFuente) {
        Lista<String> alcanzados = AlgoritmosGrafo.bfs(this, idFuente);
        Lista<String> aislados   = new Lista<>();
        NodoLista<Neurona> aux = nodos.getHead();
        while (aux != null) {
            if (!estaEnLista(alcanzados, aux.dato.getId())) {
                aislados.agregar(aux.dato.getId());
            }
            aux = aux.pNext;
        }
        this.numeroRegionesAisladas = aislados.getSize();
        return aislados;
    }

    public boolean verificarFuerteConexidad() {
        if (nodos.isEmpty()) return true;
        int total = nodos.getSize();
        NodoLista<Neurona> aux = nodos.getHead();
        while (aux != null) {
            if (AlgoritmosGrafo.bfs(this, aux.dato.getId()).getSize() < total) return false;
            aux = aux.pNext;
        }
        return true;
    }

    // ── Carga desde CSV ────────────────────────────────────────────────────

    // Formato: origen,destino,distancia,ID_Neurotransmisor,coheficiente_eficiencia_sináptica
    public void cargarRedCSV(String ruta) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(ruta));
        String linea;
        boolean primeraLinea = true;
        while ((linea = br.readLine()) != null) {
            linea = linea.trim();
            if (linea.isEmpty() || linea.startsWith("#")) continue;
            if (primeraLinea) { primeraLinea = false; continue; }
            String[] p = linea.split(",");
            if (p.length < 5) continue;
            String idOrigen  = p[0].trim();
            String idDestino = p[1].trim();
            double distancia = Double.parseDouble(p[2].trim());
            String idNeuroT  = p[3].trim();
            double k         = Double.parseDouble(p[4].trim());
            agregarArista(idOrigen, idDestino, distancia, idNeuroT, k);
        }
        br.close();
    }

    // Formato: id,nombre,efecto,velocidad,descripcion
    public void cargarDiccionarioCSV(String ruta) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(ruta));
        String linea;
        boolean primeraLinea = true;
        while ((linea = br.readLine()) != null) {
            linea = linea.trim();
            if (linea.isEmpty() || linea.startsWith("#")) continue;
            if (primeraLinea) { primeraLinea = false; continue; }
            String[] p = linea.replace("\"", "").split(",", 5);
            if (p.length < 5) continue;
            String id          = p[0].trim();
            String nombre      = p[1].trim();
            String efecto      = p[2].trim();
            double velocidad   = Double.parseDouble(p[3].trim());
            String descripcion = p[4].trim();
            diccionarioNeuroT.almacenarNeurotransmisor(
                id, new Neurotransmisor(id, nombre, velocidad, efecto, descripcion));
        }
        br.close();
        recalcularPesos();
    }

    // ── Getters / Setters ──────────────────────────────────────────────────

    public Lista<Neurona> getNodos() { return nodos; }
    public void setNodos(Lista<Neurona> nodos) { this.nodos = nodos; }

    public int getNumeroRegionesAisladas() { return numeroRegionesAisladas; }
    public void setNumeroRegionesAisladas(int n) { this.numeroRegionesAisladas = n; }

    public TablaHash getDiccionarioNeuroT() { return diccionarioNeuroT; }
    public void setDiccionarioNeuroT(TablaHash d) { this.diccionarioNeuroT = d; }

    // ── Helpers privados ───────────────────────────────────────────────────

    private int indiceNeurona(String id) {
        int idx = 0;
        NodoLista<Neurona> aux = nodos.getHead();
        while (aux != null) {
            if (aux.dato.getId().equals(id)) return idx;
            idx++;
            aux = aux.pNext;
        }
        return -1;
    }

    private Neurona neuronaEnIndice(int idx) {
        int i = 0;
        NodoLista<Neurona> aux = nodos.getHead();
        while (aux != null) {
            if (i == idx) return aux.dato;
            i++;
            aux = aux.pNext;
        }
        return null;
    }

    private boolean estaEnLista(Lista<String> lista, String id) {
        NodoLista<String> aux = lista.getHead();
        while (aux != null) {
            if (aux.dato.equals(id)) return true;
            aux = aux.pNext;
        }
        return false;
    }
}
