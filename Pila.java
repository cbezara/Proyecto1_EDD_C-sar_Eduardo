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
 * Pila implementada con arreglo estático.
 * Capacidad fija (máximo número de nodos).
 */
public class Pila {
    private int[] elementos;
    private int tope;
    private int capacidad;

    public Pila(int capacidad) {
        this.capacidad = capacidad;
        elementos = new int[capacidad];
        tope = -1;
    }

    public boolean vacia() {
        return tope == -1;
    }

    public boolean llena() {
        return tope == capacidad - 1;
    }

    public void apilar(int valor) {
        elementos[++tope] = valor;
    }

    public int desapilar() {
        return elementos[tope--];
    }

    public int cima() {
        return elementos[tope];
    }
}
