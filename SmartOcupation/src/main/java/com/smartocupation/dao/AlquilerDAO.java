/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.smartocupation.dao;

import com.smartocupation.Database;       // Clase que gestiona la conexión con MySQL
import com.smartocupation.modelos.Alquiler; 
import com.smartocupation.modelos.Cliente;
import com.smartocupation.modelos.Propiedad;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) para gestionar los alquileres en la base de datos.
 * 
 * Esta clase ejecuta consultas SQL y devuelve objetos Alquiler completos
 * incluyendo los datos del cliente y la propiedad relacionada.
 * 
 * @author manu_
 */
public class AlquilerDAO {

    /**
     * Busca los alquileres que tienen fecha de entrada entre dos fechas dadas.
     *
     * @param desde Fecha inicial del rango (inclusive)
     * @param hasta Fecha final del rango (inclusive)
     * @return Lista de objetos Alquiler con cliente y propiedad asociados
     */
    public List<Alquiler> findByDateRange(LocalDate desde, LocalDate hasta) {
        // Lista donde se almacenarán los alquileres encontrados
        List<Alquiler> alquileres = new ArrayList<>();

        // Consulta SQL con JOIN para obtener datos de cliente y propiedad
        String sql = """
            SELECT a.expediente, a.fecha_entrada, a.duracion_meses, a.estado,
                   c.id AS id_cliente, c.nombre, c.apellidos, c.email, c.telefono, c.direccion_factura,
                   p.id AS id_propiedad, p.codigo_referencia, p.direccion, p.metros, p.habitaciones, p.banos, p.precio_mensual
            FROM alquileres a
            JOIN clientes c ON a.cliente_id = c.id
            JOIN propiedades p ON a.propiedad_id = p.id
            WHERE a.fecha_entrada BETWEEN ? AND ?
            ORDER BY a.fecha_entrada ASC;
        """;

        // Try-with-resources para asegurar cierre de recursos automáticamente
        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Asignar parámetros de la consulta (fechas)
            ps.setDate(1, Date.valueOf(desde));
            ps.setDate(2, Date.valueOf(hasta));

            // Ejecutar la consulta y obtener resultados
            ResultSet rs = ps.executeQuery();

            // Recorrer cada fila del resultado
            while (rs.next()) {
                // Crear objeto Cliente con los datos de la fila
                Cliente cliente = new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        rs.getString("direccion_factura")
                );

                // Crear objeto Propiedad con los datos de la fila
                Propiedad propiedad = new Propiedad(
                        rs.getInt("id_propiedad"),
                        rs.getString("codigo_referencia"),
                        rs.getString("direccion"),
                        rs.getDouble("metros"),
                        rs.getInt("habitaciones"),
                        rs.getInt("banos"),
                        rs.getDouble("precio_mensual")
                );

                // Crear objeto Alquiler y asignar cliente, propiedad y otros datos
                Alquiler alquiler = new Alquiler();
                alquiler.setExpediente(rs.getString("expediente"));
                alquiler.setFechaInicio(rs.getDate("fecha_entrada").toLocalDate());
                alquiler.setDuracionMeses(rs.getInt("duracion_meses"));
                alquiler.setCliente(cliente);
                alquiler.setPropiedad(propiedad);
                alquiler.setEstado(rs.getString("estado"));

                // Agregar alquiler a la lista de resultados
                alquileres.add(alquiler);
            }

        } catch (SQLException e) {
            // Mostrar error en consola si falla la consulta
            System.err.println("❌ Error al buscar alquileres: " + e.getMessage());
        }

        // Devolver la lista de alquileres encontrados (puede estar vacía)
        return alquileres;
    }
}
