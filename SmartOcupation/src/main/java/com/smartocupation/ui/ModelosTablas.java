/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartocupation.ui;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author manu_
 */
public class ModelosTablas {

    public static DefaultTableModel modeloAlquileres() {
        return new DefaultTableModel(
            new String[]{
                "Expediente", "Fecha de inicio", "Duración (meses)", "Cliente",
                "Email", "Teléfono", "Dirección de facturación", "Código vivienda",
                "Dirección vivienda", "Metros", "Habitaciones", "Baños",
                "Precio (€)", "Estado"
            },
            0
        );
    }
}
