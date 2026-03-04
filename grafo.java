/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases_funciones;
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
        int contador = 0;
        String [] aux = new String [cadenas.length*2];
        for (String cadena : cadenas){
            String [] aux2 = cadena.split(",");
            aux[contador++] = aux2[0];
            aux[contador++] = aux2[1];
        }
        quickSort(aux,0,aux.length-1);
        contador = 1;
        String total = aux[0];
        for (int i=1; i<aux.length;i++){
            if (!aux[i].equals(total)){
                contador++;
                total = aux[i];
            }
        }
        this.proteinas = new String [contador];
        contador = 1;
        total = aux[0];
        this.proteinas[0] = total;
        for (int i=1; i<aux.length;i++){
            if (!aux[i].equals(total)){
                this.proteinas[contador] = aux[i];
                contador++;
                total = aux[i];
            }
        }
        this.matriz = new int [proteinas.length][proteinas.length];
        for (String cadena : cadenas){
            String [] aux2 = cadena.split(",");
            int origen = -1;
            int destino = -1;
            int valor = Integer.parseInt(aux2[2]);
            for (int i = 0; i<proteinas.length;i++){
                if (aux2[0].equals(proteinas[i])){origen += i + 1;}
                if (aux2[1].equals(proteinas[i])){destino += i + 1;}
                if (origen>-1 && destino>-1){this.matriz[origen][destino] = valor;break;}
            }
        
        
        }
        
        }
    

    /**
     * Agrega una nueva proteína al grafo junto con sus relaciones
     * Las relaciones se dan como pares "proteína, valor" en un array
     * Amplía el array de proteínas y la matriz, y coloca los valores en la nueva fila
     *
     * @param nueva Nombre de la nueva proteína
     * @param relacion array con pares: [proteína1, valor1, proteína2, valor2, ...]
     */
    public void agregar_proteina(String nueva, String[] relacion) {
        String [] nuevo = new String [this.proteinas.length+1];
        int [][] matri = new int [this.proteinas.length+1][this.proteinas.length+1];
        System.arraycopy(this.proteinas, 0, nuevo, 0, this.proteinas.length);
        for (int i = 0; i < this.matriz.length; i++) {
            System.arraycopy(this.matriz[i], 0, matri[i], 0, this.matriz[i].length);
        }
        nuevo[this.proteinas.length] = nueva;
        for (int i = 0; i<relacion.length;i+=2){
            for(int x = 0;x<this.proteinas.length;x++){
                if(this.proteinas[x].equals(relacion[i])){
                    int aux = Integer.parseInt(relacion[i+1]);
                    matri[this.proteinas.length][x] = aux;
                
                }
            
            
            }
        
        }
        this.matriz = matri;
        this.proteinas = nuevo;
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
        String [] nuevo = new String [this.proteinas.length-1];
        int aux = 0;
        for (int i = 0 ; i<this.proteinas.length; i++){
            if (nombre.equals(this.proteinas[i])){
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
        for (int i = 0; i<this.matriz.length;i++){
            if (i == encontrado) continue;
            int segundo = 0;
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
     * [origen][destino] con el nuevo valor
     * @param proteina_origen Nombre de la proteína de origen
     * @param proteina_destino Nombre de la proteína de destino
     * @param valor Nuevo valor de interacción (debe ser >= 0)
     * @return Mensaje indicando éxito o error
     */
    public String agregar_cambiar_iteracciones(String proteina_origen, String proteina_destino, int valor){
        int origen = -1;
        int destino = -1;
        String valora = Integer.toString(valor);
        String mensaje = "La proteina "+ proteina_origen + " esta relacionada correctamente con la proteina " + proteina_destino + " y su valor es " + valora;
        for (int i = 0; i<this.proteinas.length;i++){
            if (this.proteinas[i].equals(proteina_origen)){origen += i+1;}
            if (this.proteinas[i].equals(proteina_destino)){destino += i+1;}
        }
        if (origen == destino || origen == -1 || destino == -1 || valor < 0){mensaje = "Error";return mensaje;}
        this.matriz[origen][destino] = valor;
        return mensaje;
    }
    /**
     * Guarda las proteinas y sus relaciones en un array (cadena) de strings de la misma forma en que se cargan en el csv
     * @return cadena donde cada valor es un String de la forma Proteina_Origen,Proteina_Destino,Peso
     */
    public String [] guardado(){
        int contador = 0;
        for (int [] x : this.matriz){
            for (int y : x){
            if (y>0){contador++;}
            
            }
        }
        String[] cadena = new String [contador];
        int indice = 0;
        for (int x = 0; x<this.matriz[0].length;x++){
            for (int y = 0; y< this.matriz[0].length; y++){
                if (this.matriz[x][y] > 0){
                    String valor = Integer.toString(this.matriz[x][y]);
                    cadena[indice] = this.proteinas[x] + "," + this.proteinas[y] + "," + valor;
                    indice++;
                }
            }
        }
        return cadena;
    }
    public boolean existe(String nombre){
        for (String s : this.proteinas){
            if (nombre.equals(s)){
                return true;
            }
        }
        return false;
    
    }
    
    /**
     * QuickSort (Profe subanos puntos)
     * Toma los Strings como valores unicode, los compara y ordena
     *
     * @param proteinas  Array con las proteinas, incluida la repeticion
     * @param menor  Índice inferior de la particion
     * @param mayor Índice superior de la particion
     */
    private static void quickSort(String[] proteinas, int menor, int mayor) {
        if (menor < mayor) {
            int subanos_puntos = particion(proteinas, menor, mayor);
            quickSort(proteinas, menor, subanos_puntos - 1);
            quickSort(proteinas, subanos_puntos + 1, mayor);
        }
    }

    /**
     * Partición para Quicksort, coloca el pivote en su posición correcta
     * Reorganiza los elementos menores a la izquierda y mayores a la derecha
     *
     * @param proteinas array donde se ubican las proteinas para particionar
     * @param menor  Índice inferior
     * @param mayor Índice superior (pivote)
     * @return Posición final del pivote
     */
    private static int particion(String[] proteinas, int menor, int mayor) {
        String pivote = proteinas[mayor];
        int i = menor - 1;
        for (int j = menor; j < mayor; j++) {
            if (proteinas[j].compareTo(pivote) <= 0) {
                i++;
                cambio(proteinas, i, j);
            }
        }
        cambio(proteinas, i + 1, mayor);
        return i + 1;
    }

    /**
     * Intercambia dos elementos en un array de Strings
     *
     * @param proteinas Array donde se intercambiarán las proteinas para ordenarlas
     * @param i   Primer índice
     * @param j   Segundo índice
     */
    private static void cambio(String[] proteinas, int i, int j) {
        String aux = proteinas[i];
        proteinas[i] = proteinas[j];
        proteinas[j] = aux;
    }
}