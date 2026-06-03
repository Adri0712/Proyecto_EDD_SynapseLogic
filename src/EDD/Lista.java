/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author Adriana
 */
public class Lista<T> {
    private NodoLista<T> head;
    private int size;

    public Lista() {
        this.head = null;
        this.size = 0;
    }
    
    public void agregar (T dato){
        NodoLista<T> nuevo = new NodoLista<T>(dato);
        if (head == null){
            head = nuevo;           
        }else{
            NodoLista<T> aux = head;
            while (aux.pNext != null){
                aux = aux.pNext;           
            }
            aux.pNext = nuevo;
        }
        size++;
        
    }
    
    
    
    
    
    
    
}
