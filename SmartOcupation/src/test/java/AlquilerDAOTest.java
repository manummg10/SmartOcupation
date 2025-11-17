/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.smartocupation.dao.AlquilerDAO;
import com.smartocupation.modelos.Alquiler;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Pruebas unitarias para la clase AlquilerDAO.
 *
 * Comprueba que las consultas de alquileres funcionan correctamente
 * según el rango de fechas y los datos almacenados en la base de datos.
 *
 * @author manu_
 */
public class AlquilerDAOTest {

    /**
     * Verifica que el método findByDateRange devuelva al menos una lista no nula
     * aunque pueda estar vacía.
     */
    @Test
    public void testBuscarPorRangoFechas_devuelveLista() {
        AlquilerDAO dao = new AlquilerDAO();

        // Rango de fechas de prueba
        LocalDate desde = LocalDate.of(2025, 1, 1);
        LocalDate hasta = LocalDate.of(2025, 12, 31);

        // Ejecutamos el método a probar
        List<Alquiler> resultado = dao.findByDateRange(desde, hasta);

        // Verificaciones básicas
        assertNotNull(resultado, "La lista no debería ser nula");
        assertTrue(resultado.size() >= 0, "La lista debería devolver 0 o más alquileres");

        // Comentado: si  se quiere asegurar que siempre hay datos en el rango
        // assertFalse(resultado.isEmpty(), "Debería haber resultados para el rango indicado");
    }

    /**
     * Verifica que los datos obtenidos sean válidos y no nulos.
     */
    @Test
    public void testBuscarPorRangoFechas_datosValidos() {
        AlquilerDAO dao = new AlquilerDAO();

        // Rango de fechas de prueba
        LocalDate desde = LocalDate.of(2025, 1, 1);
        LocalDate hasta = LocalDate.of(2025, 12, 31);

        List<Alquiler> alquileres = dao.findByDateRange(desde, hasta);

        // Verificación de cada objeto Alquiler
        for (Alquiler a : alquileres) {
            assertNotNull(a.getExpediente(), "El expediente no debería ser nulo");
            assertNotNull(a.getFechaInicio(), "La fecha de inicio no debería ser nula");
            assertNotNull(a.getCliente(), "El cliente no debería ser nulo");
            assertNotNull(a.getCliente().getNombre(), "El nombre del cliente no debería ser nulo");
            assertNotNull(a.getPropiedad(), "La propiedad no debería ser nula");
            assertTrue(a.getPropiedad().getPrecioMensual() >= 0, "El precio mensual no puede ser negativo");
            assertNotNull(a.getEstado(), "El estado no debería ser nulo");
        }
    }

    /**
     * Verifica que el método devuelva una lista vacía cuando no hay resultados
     * para un rango de fechas donde no existen alquileres.
     */
    @Test
    public void testBuscarPorRangoFechas_sinResultados() {
        AlquilerDAO dao = new AlquilerDAO();

        // Rango de fechas donde no hay alquileres registrados
        LocalDate desde = LocalDate.of(1990, 1, 1);
        LocalDate hasta = LocalDate.of(1990, 12, 31);

        List<Alquiler> alquileres = dao.findByDateRange(desde, hasta);
        assertTrue(alquileres.isEmpty(), "Debería devolver una lista vacía si no hay datos");
    }
}
