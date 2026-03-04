/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases_funciones;

/**
 *
 * @author eduardo
 */
/**
 * Cola para la busqueda BFS
 */
public class Cola {
    private int[] elementos;
    private int frente;
    private int fin;
    private int tamaño;
    private int capacidad;

    public Cola(int capacidad) {
        this.capacidad = capacidad;
        elementos = new int[capacidad];
        frente = 0;
        fin = -1;
        tamaño = 0;
    }

    public boolean vacia() {
        return tamaño == 0;
    }

    public boolean llena() {
        return tamaño == capacidad;
    }

    public void encolar(int valor) {
        fin = (fin + 1) % capacidad;
        elementos[fin] = valor;
        tamaño++;
    }

    public int desencolar() {
        
        int valor = elementos[frente];
        frente = (frente + 1) % capacidad;
        tamaño--;
        return valor;
    }
}
