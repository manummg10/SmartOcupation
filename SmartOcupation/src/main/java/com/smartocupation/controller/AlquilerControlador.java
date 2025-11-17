/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.smartocupation.controller;

import com.smartocupation.dao.AlquilerDAO;
import com.smartocupation.modelos.Alquiler;

import java.awt.Color;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Clase AlquilerControlador
 * 
 * Controlador encargado de:
 *  - Gestionar la búsqueda de alquileres por rango de fechas
 *  - Generar un informe en PDF de los datos mostrados en la tabla
 */
public class AlquilerControlador {

    // DAO para acceder a los datos de la tabla Alquiler
    private final AlquilerDAO alquilerDAO = new AlquilerDAO();

    /**
     * Busca los alquileres dentro de un rango de fechas
     * @param from Fecha inicial
     * @param to Fecha final
     * @return Lista de objetos Alquiler que cumplen el rango
     */
    public List<Alquiler> buscarPorRango(LocalDate from, LocalDate to) {
        return alquilerDAO.findByDateRange(from, to);
    }

    /**
     * Genera un informe en PDF a partir del modelo de tabla proporcionado
     * @param model Modelo de tabla (DefaultTableModel) con los datos
     */
    public void generarInformePDF(DefaultTableModel model) {
        // Validación: no generar PDF si no hay datos
        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "No hay información para generar el informe.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Nombre del archivo PDF con la fecha actual
        String fileName = "informe_" + LocalDate.now() + ".pdf";
        // Ruta del archivo: carpeta del usuario
        String filePath = System.getProperty("user.home") + File.separator + fileName;

        try (PDDocument doc = new PDDocument()) {

            // Crear página horizontal A4 (landscape)
            PDRectangle landscape = new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth());
            PDPage page = new PDPage(landscape);
            doc.addPage(page);

            // Stream para escribir contenido en la página
            PDPageContentStream content = new PDPageContentStream(doc, page);

            // -------- Configuración inicial de posiciones y tamaños --------
            float margin = 20;                         // Margen lateral
            float yStart = landscape.getHeight() - margin; // Posición Y inicial
            float y = yStart;                          // Posición Y actual
            float rowHeight = 12;                      // Altura de cada fila
            float colWidth = (landscape.getWidth() - 2 * margin) / 7; // Ancho por columna (7 columnas por bloque)
            float fontSize = 8;                        // Tamaño de fuente

            // -------- Cabecera principal del informe --------
            content.setFont(PDType1Font.HELVETICA_BOLD, 16);
            content.beginText();
            content.newLineAtOffset(margin, y);
            content.showText("Informe de Alquileres - SmartOcupation");
            content.endText();

            // Fecha de generación
            content.setFont(PDType1Font.HELVETICA, 10);
            content.beginText();
            content.newLineAtOffset(margin, y - 20);
            content.showText("Generado el: " + LocalDate.now());
            content.endText();

            y -= 40; // Espacio entre cabecera y la primera fila

            // -------- Recorrer filas del modelo de tabla --------
            for (int row = 0; row < model.getRowCount(); row++) {

                float blockHeight = rowHeight * 4 + 10; // altura del bloque de la fila con padding

                // Dibujar rectángulo que engloba todo el bloque de la fila
                content.setNonStrokingColor(Color.WHITE); // color de fondo del bloque
                content.addRect(margin - 2, y - blockHeight + rowHeight / 2,
                        landscape.getWidth() - 2 * margin + 4, blockHeight);
                content.fill();

                content.setNonStrokingColor(Color.BLACK); // reset color a negro para el borde
                content.addRect(margin - 2, y - blockHeight + rowHeight / 2,
                        landscape.getWidth() - 2 * margin + 4, blockHeight);
                content.stroke();

                // -------- Bloque 1: columnas 0 a 6 --------
                content.setFont(PDType1Font.HELVETICA_BOLD, fontSize);
                for (int col = 0; col < 7; col++) {
                    String text = model.getColumnName(col); // nombre de la columna
                    content.beginText();
                    content.newLineAtOffset(margin + col * colWidth, y);
                    content.showText(text);
                    content.endText();
                }

                y -= rowHeight; // bajar posición Y para datos

                content.setFont(PDType1Font.HELVETICA, fontSize);
                for (int col = 0; col < 7; col++) {
                    // Obtener valor de la celda, acortar si es muy largo
                    String text = model.getValueAt(row, col) != null ? model.getValueAt(row, col).toString() : "";
                    if (text.length() > 15) text = text.substring(0, 15) + "...";
                    content.beginText();
                    content.newLineAtOffset(margin + col * colWidth, y);
                    content.showText(text);
                    content.endText();
                }

                y -= rowHeight + 5; // espacio entre bloques

                // -------- Bloque 2: columnas 7 a 13 --------
                content.setFont(PDType1Font.HELVETICA_BOLD, fontSize);
                for (int col = 7; col < 14; col++) {
                    String text = model.getColumnName(col);
                    content.beginText();
                    content.newLineAtOffset(margin + (col - 7) * colWidth, y);
                    content.showText(text);
                    content.endText();
                }

                y -= rowHeight;

                content.setFont(PDType1Font.HELVETICA, fontSize);
                for (int col = 7; col < 14; col++) {
                    String text = model.getValueAt(row, col) != null ? model.getValueAt(row, col).toString() : "";
                    if (text.length() > 15) text = text.substring(0, 15) + "...";
                    content.beginText();
                    content.newLineAtOffset(margin + (col - 7) * colWidth, y);
                    content.showText(text);
                    content.endText();
                }

                y -= rowHeight + 15; // espacio antes de la siguiente fila

                // -------- Añadir nueva página si no cabe --------
                if (y < margin + rowHeight * 4) {
                    content.close();                    // cerrar el contenido actual
                    page = new PDPage(landscape);       // nueva página
                    doc.addPage(page);
                    content = new PDPageContentStream(doc, page);
                    y = yStart - 20;
                }
            }

            // Cerrar stream y guardar PDF
            content.close();
            doc.save(filePath);

            // Mensaje al usuario indicando éxito
            JOptionPane.showMessageDialog(null, "✅ Informe generado en:\n" + filePath);

        } catch (IOException ex) {
            // Mostrar error si falla la generación
            JOptionPane.showMessageDialog(null, "❌ Error al generar el PDF: " + ex.getMessage());
        }
    }
}
