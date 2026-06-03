/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

import ModeloNeuronal.Neurotransmisor;

/**
 *
 * @author Adriana
 */
public class TablaHash extends EstructuraDeDatos {


    
    private int tamanoTabla;
    private int cantidadElementos;
    private Lista<ElementoHash>[] buckets;

    @Override
    public void CantidadElementos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getCantidadElementos() {
        return cantidadElementos;
    }
    
    
    private static class ElementoHash{// esta clas interna representaria el par clave-valor
        String clave;//id del neurotransmisor
        Neurotransmisor valor; // objeto con su numbre y velciodad v

        public ElementoHash(String clave, Neurotransmisor valor) {
            this.clave = clave;
            this.valor = valor;
        }    
    }
    
    @SuppressWarnings("unchecked")

    public TablaHash() {
        //atributos 
        this.tamanoTabla = 11;//se inicia con un numero primo peque;o pero al ir aumentando la info, va duplicandose el guardado de lista en el arreglo
        this.cantidadElementos = 0;
        
        this.buckets = new Lista[tamanoTabla];//iniciamos el arreglo
        for(int i = 0; i<tamanoTabla; i++){//arreglo de las listas
            this.buckets[i] = new Lista<>();
        }
    }
    
    private int funcionHash (String clave, int modulo){
        int hash = 0;
        for (int i = 0; i<clave.length(); i++){
            
            hash = 31 * hash + clave.charAt(i);
            //se multiplica por 31 para dispersar los bits y sumamos el valor ASCII.
        }
        return Math.abs(hash)% modulo;
        //se usa Math.abs para asegurar que el indice sea positivo y se aplica el modulo
    }
    
    public void almacenarNeurotransmisor(String clave, Neurotransmisor nt){// para insertar o almancenar
        int indice = funcionHash(clave, this.tamanoTabla);
        NodoLista<ElementoHash> aux = buckets[indice].getHead();
        //primero verificar si ya existe el neurotransmisor para actualizarlo
        while(aux != null){
            if(aux.dato.clave.equals(clave)){
                aux.dato.valor = nt;// se tiene que  actualizar el valor existente
                return;
            }
            aux = aux.pNext;
        }
        // hay que ver si es nuevo para luego verificar si la tabla necesita crecer
        if((double)(cantidadElementos + 1)/ tamanoTabla >= 0.75){//si supera el 75 % de ocupacion se agranda la tabla
            redimensionarTabla();
            //el tama;o cambia por lo que recalcula el indice para la tabal nueva
            indice = funcionHash(clave, this.tamanoTabla);
        }
        
        buckets[indice].agregar(new ElementoHash(clave, nt));//insertar el nuevo elemento en la lista 
        this.cantidadElementos++;
        
    }
    @SuppressWarnings("unchecked")//para suprimir las advertencias que arroja el sistema
    private void redimensionarTabla(){// buscamos el numero primp que sea mayor al dpoble del tama;o actual
        int nuevoTamano = obtenerSiguientePrimo(this.tamanoTabla * 2);
        
        Lista<ElementoHash>[] viejosBuckets = this.buckets;//aqui se guarda una referencia temporal a las listas viejas
        this.buckets = new Lista[nuevoTamano];
        for (int i = 0; i < nuevoTamano; i++){
            this.buckets[i] = new Lista<>();
        }
        //y se crea el nuevo arreglo con el tama;o agrandado
        this.tamanoTabla = nuevoTamano;//se actualiza el atributo de tama;o global
        
        this.cantidadElementos = 0;// se reinicia el contador temporalmente pq el metodo alamcenarneurptransmisor lo vlvera a sumar al reinsertar
        
        //recorremos cada casilla vieja y pasamos los datos a la nueva tabla
        for(int i = 0; i < viejosBuckets.length; i ++){
            NodoLista<ElementoHash> aux = viejosBuckets[i].getHead();
            while (aux!= null){//si llamamos este metodo se calcularael hash con el nuevo tamanoTbala
                almacenarNeurotransmisor(aux.dato.clave, aux.dato.valor);
                aux = aux.pNext;
            }
            
        }
    }
    
    public Neurotransmisor buscarNeurotransmisor(String clave){// se quiere buscar pero aca implementamos en TIEMPO O(1) para mas efectividad
        int indice = funcionHash(clave, this.tamanoTabla);
        NodoLista<ElementoHash> aux = buckets[indice].getHead();
        
        
        while (aux!=null){
            if (aux.dato.clave.equals(clave)){
                return aux.dato.valor;//si esta encontrado
            }
            aux = aux.pNext;
        }
        return null;// por si no existe en la tabla
    }
    private int obtenerSiguientePrimo(int numero){
        if(numero%2==0) numero ++;
        while(true){
            boolean esPrimo = true;
            for(int i = 3; i * i <= numero; i+= 2){
                if (numero % 1==0){
                    esPrimo = false;
                    break;
                }
            }
            if (esPrimo) return numero;
            numero +=2;
        }
    }
    
    
    @Override //sirve para modificar l la accion del metodo
    public void limpiar(){
        this.tamanoTabla = 11;
        this.cantidadElementos = 0;
        this.buckets = new Lista[tamanoTabla];
        for (int i = 0; i < tamanoTabla; i ++){
            buckets[i] = new Lista<>();
        }
        
        
    }

      
 
}
