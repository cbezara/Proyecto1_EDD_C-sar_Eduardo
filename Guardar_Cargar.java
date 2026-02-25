/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoeduardocesar;

/**
 *
 * @author cesardf
 */
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * permite cargar un archivo CSV y devolver su contenido como un array de Strings
 * utiliza JFileChooser para la selección de archivos
 */
public class Guardar_Cargar {
    /**
     * selecciona un archivo CSV, lee su contenido y devuelve un array de Strings donde cada elemento es una línea del archivo
     * @return array con las líneas del archivo, o null si hubo error o se canceló
     */
    public String[] cargarArchivo() {
        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Elige un archivo CSV");
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos CSV (*.csv)", "csv");
        selector.setFileFilter(filtro);

        int resultado = selector.showOpenDialog(null);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = selector.getSelectedFile();

            // Aqui recorremos por primera vez para contar las líneas del archivo y saber cuantos indices tendrá el array
            int numeroDeLineas = 0;
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                while (br.readLine() != null) {
                    numeroDeLineas++;
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al leer el archivo: " + e.getMessage());
                return null;
            }

            if (numeroDeLineas == 0) {
                return new String[0];
            }
            // Se recorre por segunda vez para llenar el arrat
            String[] lineas = new String[numeroDeLineas];
            int indice = 0;
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    lineas[indice] = linea;
                    indice++;
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al leer el archivo: " + e.getMessage());
                return null;
            }

            return lineas;
        } else {
            return null; 
        }
    }



    /**
     * Abre un cuadro de diálogo para guardar un archivo CSV y escribe
     * cada elemento del array como una línea
     * @param lineas array con las proteinas y sus relaciones
     */
    public void guardarArchivo(String[] lineas) {
        if (lineas == null || lineas.length == 0) {
            JOptionPane.showMessageDialog(null, "No hay datos para guardar");
            return;
        }

        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Guardar archivo CSV");
        selector.setFileFilter(new FileNameExtensionFilter("Archivos CSV (*.csv)", "csv"));
        selector.setSelectedFile(new File("datos.csv"));

        if (selector.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File archivo = selector.getSelectedFile();
        String ruta = archivo.getAbsolutePath();
        if (!ruta.toLowerCase().endsWith(".csv")) {
            archivo = new File(ruta + ".csv");
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (String linea : lineas) {
                bw.write(linea);
                bw.newLine();
            }
            JOptionPane.showMessageDialog(null, "Archivo guardado con exito");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el archivo " + e.getMessage());
        }
    }

}