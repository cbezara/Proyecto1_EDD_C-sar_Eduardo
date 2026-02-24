/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoeduardocesar;

/**
 * Clase que representa un grafo de proteínas usando una matriz de adyacencia
 * 
 * @author eduardo
 */
public class grafo {

    /** Matriz donde se guardan las relaciones entre las proteinas */
    public int matriz[][];

    /** Arra7 con los nombres de las proteinas */
    public String proteinas[];

    /**
     * El constructor construye a partir de un array de cadenas, donde cada elemento deberíoa ser cada linea del csv
     * Primero guarda los nombres de las proteínas y luego llena la matriz, los indices del array coinciden con la posicion de cada elemento en la matriz
     *
     * @param cadenas Array de líneas con la informacion
     */
    public grafo(String[] cadenas) {
        this.proteinas = new String[cadenas.length];
        this.matriz = new int[this.proteinas.length][this.proteinas.length];

        for (int i = 0; i < cadenas.length; i++) {
            String cadena = cadenas[i];
            String[] aux = cadena.split(",");
            this.proteinas[i] = aux[0];
        }

        for (int i = 0; i < cadenas.length; i++) {
            String cadena = cadenas[i];
            String[] aux = cadena.split(",");
            for (int y = 0; y < this.proteinas.length; y++) {
                if (aux[2].equals(this.proteinas[y])) {
                    this.matriz[i][y] = Integer.parseInt(aux[1]);
                }
            }
        }
    }

    /**
     * Agrega una nueva proteína al grafo junto con sus relaciones
     * Las relaciones se dan como pares "proteína, valor" en un array
     * Amplía el array de proteínas y la matriz, y coloca los valores en la nueva fila
     * Devuelve un array con las proteínas que no se encontraron en el array donde estan las proteinas, por lo tanto no existen en el sistema
     *
     * @param nueva Nombre de la nueva proteína
     * @param relacion array con pares: [proteína1, valor1, proteína2, valor2, ...]
     * @return array con los nombres de proteinas que no se encontraron en el grafo (vacío si todas existen)
     */
    public String[] agregar_proteina(String nueva, String[] relacion) {
        String[] reemplazo = new String[this.proteinas.length + 1];
        System.arraycopy(this.proteinas, 0, reemplazo, 0, this.proteinas.length);
        reemplazo[this.proteinas.length] = nueva;

        int[][] matriz_nueva = new int[this.matriz.length + 1][this.matriz[0].length + 1];
        for (int i = 0; i < this.matriz.length; i++) {
            System.arraycopy(this.matriz[i], 0, matriz_nueva[i], 0, this.matriz[0].length);
        }

        String[] no_encontrados = new String[relacion.length / 2];

        for (int i = 0; i < relacion.length; i += 2) {
            if (i != 0) {
                no_encontrados[i / 2] = relacion[i];
            } else {
                no_encontrados[0] = relacion[0];
            }

            for (int x = 0; x < reemplazo.length - 1; x++) {
                if (relacion[i].equals(reemplazo[x])) {
                    matriz_nueva[reemplazo.length - 1][x] = Integer.parseInt(relacion[i + 1]);
                    if (i != 0) {
                        no_encontrados[i / 2] = null;
                    } else {
                        no_encontrados[0] = null;
                    }
                }
            }
        }

        int contador = 0;
        for (String s : no_encontrados) {
            if (s != null) {
                contador++;
            }
        }

        String[] resultado = new String[contador];
        int indice = 0;
        for (String s : no_encontrados) {
            if (s != null) {
                resultado[indice++] = s;
            }
        }
        return resultado;
    }

    /**
     * Elimina una proteína del grafo. Busca el índice de la proteína por su nombre,
     * crea un nuevo array de proteínas sin ella y una nueva matriz de adyacencia
     * excluyendo la fila y columna de esa proteina. 
     *
     * @param nombre Nombre de la proteína a eliminar
     * @return Mensaje indicando si la proteína fue eliminada o no encontrada
     */
    public String eliminar_proteina(String nombre){
        int encontrado = -1;
        String [] nuevo = new String [this.proteinas.length];
        int aux = 0;
        for (int i = 0 ; i<this.proteinas.length; i++){
            if (nombre == this.proteinas[i]){
                encontrado += i+1;
            }
            else{
                nuevo[aux] = this.proteinas[i];
                aux++;
            }
        }
        if (encontrado == -1){
            String mensaje = "La proteina " + nombre + " no fue encontrada"; 
            return mensaje;
        } 
        int [][] nueva = new int [this.matriz.length-1][this.matriz.length-1];
        int primero = 0;
        int segundo = 0;
        for (int i = 0; i<this.matriz.length;i++){
            if (i == encontrado) continue;
            for (int x = 0; x<this.matriz.length;x++){
                if (x == encontrado)continue;
                nueva[primero][segundo++] = matriz[i][x];
            }
            primero++;
        
        }
        this.matriz = nueva;
        this.proteinas = nuevo;
        String mensaje = "La proteina " + nombre + " fue eliminada";
        return mensaje;
    }

    /**
     * Agrega o cambia la interacción (valor) entre dos proteínas en la matriz
     * Busca los índices de las proteínas de origen y destino. Si ambas existen,
     * no son la misma y el costo de la interacción no es menor a 0 debido a que si es 0 se toma como que ya no es posible esa interacción, entonces actualiza la matriz en la posición
     * [origen][destino] con el nuevo valor.
     *
     * @param proteina_origen Nombre de la proteína de origen
     * @param proteina_destino Nombre de la proteína de destino
     * @param valor Nuevo valor de interacción (debe ser >= 0)
     * @return Mensaje indicando éxito o error
     */
    public String agregar_cambiar_iteracciones(String proteina_origen, String proteina_destino, int valor){
        int origen = -1;
        int destino = -1;
        String mensaje = "La proteina "+ proteina_origen + " esta relacionada correctamente con la proteina " + proteina_destino;
        for (int i = 0; i<this.proteinas.length;i++){
            if (this.proteinas[i] == proteina_origen){origen += i+1;}
            if (this.proteinas[i] == proteina_destino){destino += i+1;}
        }
        if (origen == destino || origen == -1 || destino == 1 || valor < 0){mensaje = "Error";return mensaje;}
        this.matriz[origen][destino] = valor;
        return mensaje;
    }
}