/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import Control.GrafoDirigido;
import EDD.Lista;
import EDD.NodoLista;
import ModeloNeuronal.Neurona;
import ModeloNeuronal.Sinapsis;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.JPanel;

/**
 *
 * @author SynapseLogic
 */
public class PanelGrafo extends JPanel implements MouseListener, MouseMotionListener {

    private GrafoDirigido grafo;
    private HashMap<String, int[]> posiciones;
    private Lista<String> aisladas;
    private String nodoMoviendo;
    private int offX, offY;

    public PanelGrafo() {
        posiciones = new HashMap<>();
        aisladas = new Lista<>();
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(600, 500));
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void setGrafo(GrafoDirigido g) {
        this.grafo = g;
        aisladas = new Lista<>();
        posicionarNodos();
        repaint();
    }

    public void setAisladas(Lista<String> lista) {
        this.aisladas = lista;
        repaint();
    }

    private void posicionarNodos() {
        posiciones.clear();
        if (grafo == null) return;
        int total = grafo.getNodos().getSize();
        if (total == 0) return;

        int cx = 300;
        int cy = 240;
        int radio = 180;
        int i = 0;

        NodoLista<Neurona> aux = grafo.getNodos().getHead();
        while (aux != null) {
            double angulo = 2 * Math.PI * i / total - Math.PI / 2;
            int x = (int) (cx + radio * Math.cos(angulo));
            int y = (int) (cy + radio * Math.sin(angulo));
            posiciones.put(aux.dato.getId(), new int[]{x, y});
            aux = aux.pNext;
            i++;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (grafo == null || grafo.getNodos().isEmpty()) {
            g.setColor(Color.GRAY);
            g.drawString("Carga una red CSV para ver el grafo aqui", 130, 250);
            return;
        }

        // dibujar las lineas (aristas)
        g.setColor(Color.GRAY);
        NodoLista<Neurona> aux = grafo.getNodos().getHead();
        while (aux != null) {
            int[] p1 = posiciones.get(aux.dato.getId());
            if (p1 != null) {
                NodoLista<Sinapsis> auxS = aux.dato.getListaAdyacencia().getHead();
                while (auxS != null) {
                    int[] p2 = posiciones.get(auxS.dato.getDestino().getId());
                    if (p2 != null) {
                        g.drawLine(p1[0], p1[1], p2[0], p2[1]);
                        // mostrar el peso W en el medio de la linea
                        int mx = (p1[0] + p2[0]) / 2;
                        int my = (p1[1] + p2[1]) / 2;
                        g.setColor(Color.BLACK);
                        g.setFont(new Font("Arial", Font.PLAIN, 10));
                        g.drawString(String.format("%.2f", auxS.dato.getPesoW()), mx + 3, my - 3);
                        g.setColor(Color.GRAY);
                    }
                    auxS = auxS.pNext;
                }
            }
            aux = aux.pNext;
        }

        // dibujar los nodos encima
        int r = 25;
        aux = grafo.getNodos().getHead();
        while (aux != null) {
            String id = aux.dato.getId();
            int[] p = posiciones.get(id);
            if (p != null) {
                // aislados en rojo, normales en azul
                if (esAislada(id)) {
                    g.setColor(Color.RED);
                } else {
                    g.setColor(new Color(70, 130, 180));
                }
                g.fillOval(p[0] - r, p[1] - r, r * 2, r * 2);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 11));
                FontMetrics fm = g.getFontMetrics();
                g.drawString(id, p[0] - fm.stringWidth(id) / 2, p[1] + 5);
            }
            aux = aux.pNext;
        }

        // leyenda abajo
        g.setColor(new Color(70, 130, 180));
        g.fillRect(10, getHeight() - 35, 15, 15);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 11));
        g.drawString("Neurona normal", 30, getHeight() - 23);
        g.setColor(Color.RED);
        g.fillRect(140, getHeight() - 35, 15, 15);
        g.setColor(Color.BLACK);
        g.drawString("Zona aislada", 160, getHeight() - 23);
    }

    private boolean esAislada(String id) {
        NodoLista<String> aux = aisladas.getHead();
        while (aux != null) {
            if (aux.dato.equals(id)) {
                return true;
            }
            aux = aux.pNext;
        }
        return false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (grafo == null) return;
        int r = 25;
        NodoLista<Neurona> aux = grafo.getNodos().getHead();
        while (aux != null) {
            String id = aux.dato.getId();
            int[] p = posiciones.get(id);
            if (p != null) {
                int dx = e.getX() - p[0];
                int dy = e.getY() - p[1];
                if (dx * dx + dy * dy <= r * r) {
                    nodoMoviendo = id;
                    offX = dx;
                    offY = dy;
                    return;
                }
            }
            aux = aux.pNext;
        }
        nodoMoviendo = null;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (nodoMoviendo != null) {
            posiciones.put(nodoMoviendo, new int[]{e.getX() - offX, e.getY() - offY});
            repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        nodoMoviendo = null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}
}
