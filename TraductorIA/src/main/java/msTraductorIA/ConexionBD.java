package msTraductorIA;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:postgresql://aws-0-us-west-1.pooler.supabase.com:5432/postgres";
    private static final String USUARIO = "postgres.wqqmeoqsimjodihxysqs";
    private static final String CONTRASEÑA = "holamundo123";

    public static Connection establecerConexion() throws SQLException {
        try {
            Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
            System.out.println("Conexión establecida con éxito.");
            return conexion;
        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión: " + e.getMessage());
            throw e;
        }
    }

    public static void cerrarRecursos(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Connection conexion = null;
        try {
            conexion = establecerConexion();
            // Operaciones con la base de datos
        } catch (SQLException e) {
            System.out.println("Error de base de datos: " + e.getMessage());
        } finally {
            cerrarRecursos(conexion);
        }
    }
}
