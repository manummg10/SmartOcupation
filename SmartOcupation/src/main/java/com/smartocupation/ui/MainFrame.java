/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.smartocupation.ui;

import com.smartocupation.controller.AlquilerControlador;
import com.smartocupation.modelos.Alquiler;
import com.toedter.calendar.JDateChooser; // Selector de fechas (de la librería JCalendar)

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

/**
 * Ventana principal de SmartOcupación.
 *
 * Permite buscar alquileres por rango de fechas y generar informes en PDF. Usa
 * botones con efecto 3D y una tabla personalizada (TablaFuturista).
 *
 * @author manu_
 */
public class MainFrame extends JFrame {

    // ----------- ATRIBUTOS DE INTERFAZ -----------
    // Campos para elegir las fechas de búsqueda
    private final JDateChooser fechaDesde;
    private final JDateChooser fechaHasta;

    // Botones principales
    private final JButton btnBuscar;
    private final JButton btnInforme;

    // Tabla personalizada con estilo futurista
    private final TablaFuturista tabla;

    // Modelo que contiene los datos mostrados en la tabla
    private final DefaultTableModel modeloTabla;

    // Controlador que maneja la lógica de negocio (búsqueda, PDF, etc.)
    private final AlquilerControlador controlador;

    // Etiqueta que se muestra si no hay resultados
    private final JLabel lblSinDatos;

    // ----------- CONSTRUCTOR -----------
    public MainFrame() {
        super("Manuel Moraira García - Búsqueda de Alquileres"); // Título de la ventana

        controlador = new AlquilerControlador(); // Crea el controlador de alquileres

        // Configuración básica de la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Cierra la app al salir
        setSize(1000, 600);                     // Tamaño inicial
        setLocationRelativeTo(null);            // Centrar en pantalla
        setLayout(new BorderLayout());          // Distribución general por regiones (NORTH, CENTER, etc.)

        // ----------- INICIALIZACIÓN DE COMPONENTES -----------
        fechaDesde = new JDateChooser(); // Calendario para seleccionar la fecha "desde"
        fechaHasta = new JDateChooser(); // Calendario para seleccionar la fecha "hasta"

        // Botones principales
        btnBuscar = crearBoton3D("Buscar");
        btnInforme = crearBoton3D("Generar informe PDF");

        // ----------- PANEL SUPERIOR (filtros + botones) -----------
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelSuperior.setBackground(new Color(44, 62, 80)); // Color de fondo oscuro (azul grisáceo)

        // Etiquetas de texto
        JLabel lblDesde = new JLabel("Desde:");
        JLabel lblHasta = new JLabel("Hasta:");
        lblDesde.setForeground(Color.WHITE);
        lblHasta.setForeground(Color.WHITE);

        // Se añaden los elementos al panel superior
        panelSuperior.add(lblDesde);
        panelSuperior.add(fechaDesde);
        panelSuperior.add(lblHasta);
        panelSuperior.add(fechaHasta);
        panelSuperior.add(btnBuscar);
        panelSuperior.add(btnInforme);
        panelSuperior.add(Box.createHorizontalGlue()); // Espaciador flexible

        // Título de la aplicación
        JLabel lblTitulo = new JLabel("SmartOcupación");
        lblTitulo.setFont(new Font("Raleway", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(236, 240, 241)); // Blanco suave
        panelSuperior.add(lblTitulo);

        // Añadir el panel superior a la parte superior de la ventana
        add(panelSuperior, BorderLayout.NORTH);

        // ----------- ETIQUETA "NO HAY REGISTROS" -----------
        lblSinDatos = new JLabel("NO HAY REGISTROS");
        lblSinDatos.setHorizontalAlignment(SwingConstants.CENTER);
        lblSinDatos.setForeground(new Color(231, 76, 60)); // Rojo alerta
        lblSinDatos.setFont(new Font("Raleway", Font.BOLD, 16));
        lblSinDatos.setVisible(false); // Oculta por defecto hasta que no haya datos

        // ----------- TABLA FUTURISTA PERSONALIZADA -----------
        // Usa la clase personalizada TablaFuturista (con hover, colores, etc.)
        modeloTabla = ModelosTablas.modeloAlquileres();
        tabla = new TablaFuturista(modeloTabla);

        // Panel central que contiene la tabla y el texto "sin datos"
        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.add(new JScrollPane(tabla), BorderLayout.CENTER);
        panelCentro.add(lblSinDatos, BorderLayout.NORTH);

        add(panelCentro, BorderLayout.CENTER);

        // ----------- EVENTOS -----------
        // Botón buscar → ejecuta la búsqueda de alquileres
        btnBuscar.addActionListener(e -> buscarAlquileres());

        // Botón informe → genera PDF con los datos actuales
        btnInforme.addActionListener(e -> {
            System.out.println("Botón PDF pulsado");
            controlador.generarInformePDF(modeloTabla);
        });

    }

    // ----------- MÉTODOS AUXILIARES -----------
    /**
     * Crea un botón con efecto 3D (degradado y sombra).
     */
    private JButton crearBoton3D(String texto) {
        JButton boton = new JButton(texto) {
            // Sobrescribe el método paintComponent para personalizar el dibujo del botón
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                int w = getWidth();
                int h = getHeight();

                // Degradado vertical (azul oscuro arriba, azul claro abajo)
                GradientPaint degradado = new GradientPaint(
                        0, 0, new Color(41, 128, 185),
                        0, h, new Color(52, 152, 219)
                );
                g2.setPaint(degradado);
                g2.fillRoundRect(0, 0, w, h, 10, 10); // Botón con esquinas redondeadas

                // Sombra inferior para efecto 3D
                g2.setColor(new Color(0, 0, 0, 50)); // Negro semitransparente
                g2.fillRoundRect(2, 4, w - 4, h - 2, 10, 10);

                // Llama al dibujado estándar del texto
                super.paintComponent(g);
                g2.dispose();
            }
        };

        // Configuración visual del texto y comportamiento
        boton.setFont(new Font("Raleway", Font.BOLD, 14));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setContentAreaFilled(false); // No usar relleno por defecto
        boton.setOpaque(false);
        boton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

        // ----------- EFECTOS DE RATÓN -----------
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setForeground(Color.DARK_GRAY); // Cambia el color del texto
                boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Cambia el cursor a mano
            }

            @Override
            public void mouseExited(MouseEvent e) {
                boton.setForeground(Color.WHITE); // Vuelve al color original
            }

            @Override
            public void mousePressed(MouseEvent e) {
                boton.setLocation(boton.getX(), boton.getY() + 2); // Simula el botón “hundido”
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                boton.setLocation(boton.getX(), boton.getY() - 2); // Vuelve a su posición
            }
        });

        return boton;
    }

    /**
     * Realiza la búsqueda de alquileres por rango de fechas y actualiza la
     * tabla con los resultados.
     */
    private void buscarAlquileres() {
        // Validar que se seleccionaron ambas fechas
        if (fechaDesde.getDate() == null || fechaHasta.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Selecciona ambas fechas antes de buscar.");
            return;
        }
// Convertir fechas de JDateChooser a LocalDate
        LocalDate desde = fechaDesde.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate hasta = fechaHasta.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Limpiar la tabla antes de llenarla
        modeloTabla.setRowCount(0);

        // Obtener los alquileres desde el DAO/controlador
        List<Alquiler> alquileres = controlador.buscarPorRango(desde, hasta);

        // Mostrar mensaje si no hay resultados
        if (alquileres.isEmpty()) {
            lblSinDatos.setVisible(true);
        } else {
            lblSinDatos.setVisible(false);

            // Inserción dinámica de datos en el DefaultTableModel
            for (Alquiler a : alquileres) {
                modeloTabla.addRow(new Object[]{
                    a.getExpediente(),
                    a.getFechaInicio(),
                    a.getDuracionMeses(),
                    a.getCliente().getNombre(),
                    a.getCliente().getCorreo(),
                    a.getCliente().getTelefono(),
                    a.getCliente().getDireccionFacturacion(),
                    a.getPropiedad().getCodigoReferencia(),
                    a.getPropiedad().getDireccion(),
                    a.getPropiedad().getMetros(),
                    a.getPropiedad().getHabitaciones(),
                    a.getPropiedad().getBanos(),
                    a.getPropiedad().getPrecioMensual(),
                    a.getEstado()
                });
            }
        }
    }
}
