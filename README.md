🧱 1. README — Documentación técnica del proyecto
🏷️ Proyecto: SmartOcupation

Autor: Manuel Moraira García
Módulo: Desarrollo de Interfaces Web (DAM2)
Lenguaje: Java 17
Entorno: Apache NetBeans + Maven
Base de datos: MySQL
Fecha: Noviembre 2025

📖 Descripción general

SmartOcupation es una aplicación de escritorio desarrollada en Java Swing, cuyo propósito es permitir a una empresa inmobiliaria gestionar sus alquileres de forma eficiente.
La aplicación ofrece un sistema de búsqueda por rango de fechas, visualización de resultados, y generación automática de informes PDF a partir de los datos de la base de datos.

⚙️ Arquitectura

El sistema está estructurado bajo el patrón Modelo–Vista–Controlador (MVC):

com.smartocupation
 ├── Database.java                    → Conexión a MySQL
 ├── controller
 │    └── AlquilerControlador.java    → Lógica de negocio (búsqueda, informes)
 ├── dao
 │    └── AlquilerDAO.java            → Consultas SQL y acceso a datos
 ├── modelos
 │    ├── Alquiler.java
 │    ├── Cliente.java
 │    └── Propiedad.java              → Clases modelo del dominio
 ├── ui
 │    ├── MainFrame.java              → Interfaz principal (Swing)
 │    └── FuturisticCellRenderer.java
 │    └── TablaFuturista.java
 └── test
      └── AlquilerDAOTest.java        → Pruebas unitarias con JUnit
      |__ TestConnection.java

🧩 Dependencias principales (pom.xml)

Librería	     Versión	    Función
mysql-connector-j    8.3.0	    Conexión con base de datos MySQL
jcalendar	     1.4	    Componente JDateChooser para seleccionar fechas
pdfbox	             2.0.30	    Generación de informes PDF
junit-jupiter	     5.9.3	    Pruebas unitarias

💾 Base de datos

Nombre: smartocupation
Tablas:

-clientes (id, nombre, apellidos, email, telefono, direccion_factura)

-propiedades (id, codigo_referencia, direccion, metros, habitaciones, banos, precio_mensual)

-alquileres (id, expediente, fecha_entrada, duracion_meses, estado, cliente_id, propiedad_id)

🔗 Relaciones:

Un cliente puede tener varios alquileres.

Una propiedad puede estar asociada a un solo alquiler activo.


💻 Instalación y ejecución

1. Clonar o copiar el proyecto en NetBeans o IntelliJ.

2. Configurar MySQL con las tablas correspondientes.

3. Verificar el archivo Database.java (usuario y contraseña correctos).

4. Ejecutar la clase Main.java.

5. La aplicación se abrirá con el tema oscuro FlatDarkLaf.


🧠 Funcionamiento general

1. El usuario selecciona dos fechas usando los JDateChooser.

2. Al pulsar Buscar, se ejecuta una consulta SQL (findByDateRange).

3. Los resultados se muestran en la tabla.

4. Si se pulsa Generar informe PDF, se crea un documento con los datos visibles.

5. El informe se guarda automáticamente en el directorio del usuario (/home/usuario/informe_YYYY-MM-DD.pdf).


🧪 Pruebas unitarias

Las pruebas verifican:

-Que las consultas SQL devuelven listas no nulas.

-Que los objetos Alquiler devueltos contienen datos válidos.

-Que el sistema responde correctamente a rangos sin resultados.

Se ejecutan con:

mvn test


🧭 Diseño visual

-Botones 3D personalizados con degradado azul y efecto hover.

-Tabla con estilo neón mediante FuturisticCellRenderer.

-Mensajes dinámicos para notificar al usuario sobre el estado de la búsqueda o errores.


📈 Posibles ampliaciones

1. Sistema de login con roles (empleado / administrador).

2. Editor de registros (añadir, modificar, eliminar alquileres).

3. Panel de estadísticas con gráficos (por ejemplo, JFreeChart).

4. Exportación adicional a Excel o CSV.

5. Módulo de facturación con cálculos automáticos de pagos.


🧭 2. Diagrama UML de clases principales
                 ┌─────────────────────────┐
                 │      Alquiler           │
                 ├─────────────────────────┤
                 │ - expediente:String     │
                 │ - fechaInicio:LocalDate │
                 │ - duracionMeses:int     │
                 │ - estado:String         │
                 │ - cliente:Cliente       │
                 │ - propiedad:Propiedad   │
                 ├─────────────────────────┤ 
                 │ + getters/setters       │
                 └─────────────────────────┘
                        ▲
                        │
      ┌─────────────────┼────────────────────┐
      │                                      │
┌───────────────────────┐         ┌───────────────────────┐
│    Cliente            │         │   Propiedad           │
├───────────────────────┤         ├───────────────────────┤
│ - id:int              │         │ - id:int              │
│ - nombre:String       │         │ - codigoRef:String    │
│ - apellidos:String    │         │ - direccion:String    │
│ - correo:String       │         │ - metros:double       │
│ - telefono:String     │         │ - habitaciones:int    │
│ - direccionFact:String│         │ - banos:int           │
│                       │         │ - precioMensual:double│
└───────────────────────┘         └───────────────────────┘

┌──────────────────────┐
│     AlquilerDAO      │
├──────────────────────┤
│ + findByDateRange()  │
└──────────────────────┘
         ▲
         │ usa
┌──────────────────────────────┐
│     AlquilerControlador      │
├──────────────────────────────┤
│ + buscarPorRango()           │
│ + generarInformePDF()        │
└──────────────────────────────┘
         ▲
         │ controla
┌──────────────────────────────┐
│          MainFrame           │
├──────────────────────────────┤
│ + buscarAlquileres()         │
│ + configurarTabla()          │
│ + crearBoton3D()             │
└──────────────────────────────┘

💡 3. Buenas prácticas aplicadas

✅ Uso de MVC + DAO para separar responsabilidades.
✅ PreparedStatement para seguridad SQL.
✅ FlatLaf y diseño propio para una interfaz moderna.
✅ PDFBox para generación profesional de informes.
✅ JUnit 5 con pruebas automatizadas.
✅ Código comentado, modular y con buena legibilidad.
