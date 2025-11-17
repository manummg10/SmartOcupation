/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package com.smartocupation;

import com.smartocupation.ui.MainFrame;
import javax.swing.SwingUtilities;



/**
 *
 * @author manu_
 */
/**
 * Clase principal del proyecto SmartOcupation.
 * 
 * Este archivo inicia la aplicación lanzando la interfaz grafica (MainFrame)
 * en el hilo de eventos de Swing para evitar problemas de concurrencia.
 */
public class Main {

    /**
     * Método principal (punto de entrada del programa).
     * Se encarga de mostrar la ventana principal de la aplicación.
     * 
     * @param args Argumentos de línea de comandos (no se usan).
     */
    public static void main(String[] args) {

        // Ejecutar la interfaz grafica en el hilo de eventos (EDT)
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}

