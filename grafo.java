/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoeduardocesar;

/**
 *
 * @author eduardo
 */
public class grafo {
    public String matriz [][];
    public String proteinas [];
    
    public grafo(String[] cadenas){
        this.proteinas = new String [cadenas.length];
        this.matriz = new String [this.proteinas.length] [this.proteinas.length]; 
        for (int i = 0; i<cadenas.length;i++) {
            String cadena = cadenas[i];
            String [] aux = cadena.split(","); 
            this.proteinas[i] = aux[0];
        }
        for (int i = 0; i<cadenas.length;i++){
            String cadena = cadenas[i];
            String [] aux = cadena.split(","); 
            for (int y = 0; y < this.proteinas.length; y++){
                if (aux[2].equals(this.proteinas[y])){
                    this.matriz[i][y]=aux[1];
                }
            
            }
        }
    
    }
}

