/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.smartocupation.modelos;

import java.time.LocalDate;

/**
 * Modelo Alquiler
 * 
 * Representa un alquiler realizado por un cliente.
 * Contiene toda la información relevante de un alquiler:
 *  - Número de expediente
 *  - Fecha de inicio
 *  - Duración en meses
 *  - Cliente asociado
 *  - Propiedad alquilada
 *  - Estado del alquiler
 * 
 * Sirve como objeto de negocio para ser usado por DAO, controladores y vistas.
 * 
 * @author manu_
 */
public class Alquiler {

    // Número de expediente del alquiler (identificador)
    private String expediente;

    // Fecha de inicio del alquiler
    private LocalDate fechaInicio;

    // Duración del alquiler en meses
    private int duracionMeses;

    // Cliente que realiza el alquiler
    private Cliente cliente;

    // Propiedad que se alquila
    private Propiedad propiedad;

    // Estado del alquiler (ej: "Activo", "Finalizado", "Pendiente")
    private String estado;

    // ---------------- Getters y Setters ----------------

    // Devuelve el expediente del alquiler
    public String getExpediente() {
        return expediente;
    }

    // Asigna un expediente al alquiler
    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    // Devuelve la fecha de inicio del alquiler
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    // Asigna la fecha de inicio del alquiler
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    // Devuelve la duración del alquiler en meses
    public int getDuracionMeses() {
        return duracionMeses;
    }

    // Asigna la duración en meses
    public void setDuracionMeses(int duracionMeses) {
        this.duracionMeses = duracionMeses;
    }

    // Devuelve el cliente asociado al alquiler
    public Cliente getCliente() {
        return cliente;
    }

    // Asigna un cliente al alquiler
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    // Devuelve la propiedad alquilada
    public Propiedad getPropiedad() {
        return propiedad;
    }

    // Asigna la propiedad alquilada
    public void setPropiedad(Propiedad propiedad) {
        this.propiedad = propiedad;
    }

    // Devuelve el estado del alquiler
    public String getEstado() {
        return estado;
    }

    // Asigna un estado al alquiler
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
