package msTraductorIA.PruebasCRUD;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Deleted {
    private static final String URL = "https://wqqmeoqsimjodihxysqs.supabase.co/rest/v1/traduccionesGuardadas";
    private static final String API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6IndxcW1lb3FzaW1qb2RpaHh5c3FzIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTQyNjgzOTIsImV4cCI6MjAyOTg0NDM5Mn0.Hv51BuiUKsUQI0l0PZzbFTKc4K2D6SkN_ga5ts9w544";

    public static void eliminarTraduccion(int idEliminar) {
        try {
            URL url = new URL(URL + "?id=eq." + idEliminar);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("apikey", API_KEY);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_NO_CONTENT) {
                System.out.println("Traducción eliminada exitosamente.");
            } else {
                System.out.println("Error al eliminar la traducción. Código de respuesta: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int idEliminar = 14; // ID de la entrada que quieres eliminar
        eliminarTraduccion(idEliminar);
    }
}