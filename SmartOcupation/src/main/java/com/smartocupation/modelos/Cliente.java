/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.smartocupation.modelos;

/**
 * Modelo Cliente
 * 
 * Representa los datos personales y de facturación de un cliente.
 * Contiene información básica como nombre, correo, teléfono y dirección.
 * Se utiliza dentro de la clase Alquiler para relacionar un cliente con su alquiler.
 * 
 * @author manu_
 */
public class Cliente {

    // Identificador único del cliente en la base de datos
    private int id;

    // Nombre del cliente
    private String nombre;

    // Apellidos del cliente
    private String apellidos;

    // Correo electrónico del cliente
    private String correo;

    // Teléfono de contacto
    private String telefono;

    // Dirección de facturación (para recibos o facturas)
    private String direccionFacturacion;

    // ---------------- Constructor ----------------

    /**
     * Constructor completo que inicializa todos los campos del cliente.
     * 
     * @param id Identificador único
     * @param nombre Nombre del cliente
     * @param apellidos Apellidos del cliente
     * @param correo Correo electrónico
     * @param telefono Teléfono
     * @param direccionFacturacion Dirección de facturación
     */
    public Cliente(int id, String nombre, String apellidos, String correo, String telefono, String direccionFacturacion) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.telefono = telefono;
        this.direccionFacturacion = direccionFacturacion;
    }

    // ---------------- Getters y Setters ----------------

    // Devuelve el ID del cliente
    public int getId() { return id; }

    // Asigna el ID del cliente
    public void setId(int id) { this.id = id; }

    // Devuelve el nombre
    public String getNombre() { return nombre; }

    // Asigna el nombre
    public void setNombre(String nombre) { this.nombre = nombre; }

    // Devuelve los apellidos
    public String getApellidos() { return apellidos; }

    // Asigna los apellidos
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    // Devuelve el correo electrónico
    public String getCorreo() { return correo; }

    // Asigna el correo electrónico
    public void setCorreo(String correo) { this.correo = correo; }

    // Devuelve el teléfono
    public String getTelefono() { return telefono; }

    // Asigna el teléfono
    public void setTelefono(String telefono) { this.telefono = telefono; }

    // Devuelve la dirección de facturación
    public String getDireccionFacturacion() { return direccionFacturacion; }

    // Asigna la dirección de facturación
    public void setDireccionFacturacion(String direccionFacturacion) { this.direccionFacturacion = direccionFacturacion; }
}
