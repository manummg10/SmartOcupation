/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author manu_
 */
public class TestConnection {
    public static void main(String[] args) {
        try {
            System.out.println("✅ Conexion establecida con MySQL correctamente.");
        } catch (Exception e) {
            System.out.println("❌ Error de conexion: " + e.getMessage());
        }
    }
}
