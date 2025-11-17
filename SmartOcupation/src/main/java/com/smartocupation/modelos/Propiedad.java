/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.smartocupation.modelos;

/**
 * Modelo Propiedad
 * 
 * Representa una vivienda o propiedad con todos sus datos inmobiliarios.
 * Se utiliza dentro de la clase Alquiler para asociar una propiedad a un alquiler.
 * Contiene:
 *  - Código de referencia único
 *  - Dirección
 *  - Metros cuadrados
 *  - Número de habitaciones
 *  - Número de baños
 *  - Precio mensual
 * 
 * Autor: manu_
 */
public class Propiedad {

    // Identificador único de la propiedad en la base de datos
    private final int id;

    // Código de referencia de la propiedad (para identificarla fácilmente)
    private final String codigoReferencia;

    // Dirección física de la propiedad
    private final String direccion;

    // Superficie en metros cuadrados
    private final double metros;

    // Número de habitaciones
    private final int habitaciones;

    // Número de baños
    private final int banos;

    // Precio mensual del alquiler
    private final double precioMensual;

    // ---------------- Constructor ----------------

    /**
     * Constructor completo que inicializa todos los campos de la propiedad
     * 
     * @param id Identificador único
     * @param codigoReferencia Código de referencia de la propiedad
     * @param direccion Dirección completa
     * @param metros Superficie en m²
     * @param habitaciones Número de habitaciones
     * @param banos Número de baños
     * @param precioMensual Precio mensual del alquiler
     */
    public Propiedad(int id, String codigoReferencia, String direccion, double metros, int habitaciones, int banos, double precioMensual) {
        this.id = id;
        this.codigoReferencia = codigoReferencia;
        this.direccion = direccion;
        this.metros = metros;
        this.habitaciones = habitaciones;
        this.banos = banos;
        this.precioMensual = precioMensual;
    }

    // ---------------- Getters ----------------

    // Devuelve el ID de la propiedad
    public int getId() { return id; }

    // Devuelve el código de referencia
    public String getCodigoReferencia() { return codigoReferencia; }

    // Devuelve la dirección
    public String getDireccion() { return direccion; }

    // Devuelve los metros cuadrados
    public double getMetros() { return metros; }

    // Devuelve el número de habitaciones
    public int getHabitaciones() { return habitaciones; }

    // Devuelve el número de baños
    public int getBanos() { return banos; }

    // Devuelve el precio mensual
    public double getPrecioMensual() { return precioMensual; }
}
