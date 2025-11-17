/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.smartocupation.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 * Clase personalizada: TablaFuturista
 * 
 * Extiende JTable para crear una tabla con estilo moderno/futurista,
 * adaptado al diseño del proyecto SmartOcupation.
 * 
 * Aplica:
 *  - Colores oscuros
 *  - Bordes suaves
 *  - Cabecera estilizada
 *  - Hover dinámico al pasar el ratón sobre las filas
 */
public class TablaFuturista extends JTable {

    // Almacena la fila actual sobre la que pasa el ratón
    private int filaHover = -1;

    /**
     * Constructor que recibe un modelo de tabla y aplica el estilo personalizado.
     * @param modelo Modelo de tabla que contiene los datos
     */
    public TablaFuturista(DefaultTableModel modelo) {
        super(modelo); // Llama al constructor de JTable con el modelo dado
        inicializarEstilo(); // Configura colores, hover y renderizado
    }

    /**
     * Método privado que configura la apariencia y comportamiento de la tabla
     */
    private void inicializarEstilo() {

        // -------- CONFIGURACIÓN GENERAL DE LA TABLA --------
        setFillsViewportHeight(true);                // Ocupa todo el alto del contenedor
        setRowHeight(28);                            // Altura de cada fila
        setShowGrid(false);                          // No mostrar líneas de cuadrícula
        setIntercellSpacing(new Dimension(0, 0));   // Sin espacio entre celdas
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Selección de una fila a la vez

        // -------- COLORES PRINCIPALES --------
        setBackground(new Color(30, 39, 46));        // Fondo base oscuro
        setForeground(Color.WHITE);                  // Texto blanco
        setSelectionBackground(new Color(41, 128, 185)); // Azul para fila seleccionada
        setSelectionForeground(Color.WHITE);        // Texto blanco al seleccionar

        // -------- CABECERA PERSONALIZADA --------
        JTableHeader cabecera = getTableHeader();    // Obtener encabezado de la tabla
        cabecera.setBackground(Color.GRAY);          // Fondo gris
        cabecera.setForeground(Color.WHITE);         // Texto blanco
        cabecera.setFont(new Font("Raleway", Font.BOLD, 13)); // Fuente moderna
        cabecera.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(52, 152, 219))); // Borde inferior azul brillante

        // -------- DETECTAR MOVIMIENTO DEL RATÓN (hover) --------
        // Este listener está en la tabla, no en el renderizador, para que funcione correctamente
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseMoved(java.awt.event.MouseEvent e) {
                int fila = rowAtPoint(e.getPoint());   // Determina la fila bajo el ratón
                if (fila != filaHover) {               // Si la fila ha cambiado
                    filaHover = fila;                  // Actualiza filaHover
                    repaint();                          // Redibuja la tabla para mostrar hover
                }
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Cambia cursor a mano
            }
        });

        // Cuando el ratón sale de la tabla, se elimina el efecto hover
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                filaHover = -1;                        // Ninguna fila bajo el ratón
                repaint();                              // Redibuja para quitar hover
                setCursor(Cursor.getDefaultCursor());  // Cursor vuelve a flecha normal
            }
        });

        // -------- RENDERIZADOR DE CELDAS --------
        // Controla cómo se pintan todas las celdas
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

            // Colores alternos para filas (mejor lectura)
            private final Color colorPar = new Color(36, 49, 60);
            private final Color colorImpar = new Color(44, 62, 80);

            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

                // Llama al renderizado por defecto
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // -------- Lógica de color de fondo --------
                if (isSelected) {
                    // Fila seleccionada
                    setBackground(new Color(41, 128, 185));
                } else if (row == filaHover) {
                    // Fila bajo el cursor (hover)
                    setBackground(new Color(52, 152, 219));
                } else {
                    // Filas alternas normales
                    setBackground((row % 2 == 0) ? colorPar : colorImpar);
                }

                setForeground(Color.WHITE);                    // Texto blanco
                setHorizontalAlignment(SwingConstants.CENTER); // Centrar texto
                setBorder(noFocusBorder);                      // Sin borde de enfoque feo

                return this; // Devuelve la celda ya configurada
            }
        });
    }
}
