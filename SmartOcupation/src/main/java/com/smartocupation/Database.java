/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.smartocupation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase Database
 * 
 * Gestiona la conexión con la base de datos MySQL.
 * Proporciona un método estático para obtener un objeto Connection,
 * que luego puede ser usado en las clases DAO para ejecutar consultas SQL.
 */
public class Database {

    // -------- CONFIGURACIÓN DE LA BASE DE DATOS --------
    
    // Nombre de la base de datos en MySQL (debe existir previamente)
    private static final String DB_NAME = "smartocupation";

    // URL de conexión con MySQL
    // Incluye:
    //  - localhost:3306 → servidor y puerto por defecto
    //  - nombre de la base de datos
    //  - useSSL=false → desactiva SSL (no obligatorio en localhost)
    //  - serverTimezone=UTC → evita problemas de zona horaria
    private static final String URL = "jdbc:mysql://localhost:3306/" + DB_NAME + "?useSSL=false&serverTimezone=UTC";

    // Usuario de MySQL
    private static final String USER = "root";

    // Contraseña del usuario (cambiar según tu configuración)
    private static final String PASS = "root";

    /**
     * Método estático que devuelve una conexión activa con la base de datos.
     * 
     * Ventajas:
     *  - Se puede llamar directamente: Database.getConnection()
     *  - Centraliza la configuración de la conexión
     * 
     * @return Objeto Connection listo para ejecutar consultas SQL
     * @throws SQLException si ocurre un error al conectar
     */
    public static Connection getConnection() throws SQLException {
        // DriverManager se encarga de gestionar la conexión con MySQL
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
