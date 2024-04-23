package msTraductorIA;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    // Método para establecer la conexión a la base de datos
    public static Connection establecerConexion() throws SQLException {
        // Configura los detalles de conexión a tu base de datos
        String url = "jdbc:sqlserver://localhost:1433;databaseName=TRADUCTOR;encrypt=true;TrustServerCertificate=True;";
        String usuario = "sa";
        String contraseña = "francisDE";

        // Intenta establecer la conexión
        try {
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("Conexión establecida con éxito.");
            return conexion;
        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión: " + e.getMessage());
            throw e;
        }
    }

    // Método para cerrar la conexión a la base de datos
    public static void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    // Método main de ejemplo para probar la conexión
    public static void main(String[] args) {
        Connection conexion = null;
        try {
            conexion = establecerConexion();
            // Aquí puedes realizar operaciones con la base de datos
        } catch (SQLException e) {
            // Manejo de errores
        } finally {
            cerrarConexion(conexion);
        }
    }
}
