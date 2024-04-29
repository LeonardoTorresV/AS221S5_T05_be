package msTraductorIA.PruebasCRUD;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

public class Update {
    private static final String URL = "https://wqqmeoqsimjodihxysqs.supabase.co/rest/v1/traduccionesGuardadas";
    private static final String API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6IndxcW1lb3FzaW1qb2RpaHh5c3FzIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTQyNjgzOTIsImV4cCI6MjAyOTg0NDM5Mn0.Hv51BuiUKsUQI0l0PZzbFTKc4K2D6SkN_ga5ts9w544";

    public static void actualizarPalabraTraducida(int idActualizar, String nuevaPalabraTraducida) {
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpPatch httpPatch = new HttpPatch(URL + "/" + idActualizar); // Cambiado de ?id=eq. a /id
            httpPatch.addHeader("Content-Type", "application/json");
            httpPatch.addHeader("apikey", API_KEY);

            String json = "{\"palabraTraducida\": \"" + nuevaPalabraTraducida + "\"}";
            StringEntity entity = new StringEntity(json);
            httpPatch.setEntity(entity);

            HttpResponse response = httpClient.execute(httpPatch);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200 || statusCode == 204) { // Cambiado para aceptar también códigos de respuesta 204 (No Content)
                System.out.println("Traducción actualizada exitosamente.");
            } else {
                System.out.println("Error al actualizar la traducción. Código de respuesta: " + statusCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int idActualizar = 15; // ID de la entrada que quieres actualizar
        String nuevaPalabraTraducida = "nueva traducción";
        actualizarPalabraTraducida(idActualizar, nuevaPalabraTraducida);
    }
}
