package msTraductorIA.PruebasCRUD;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Read {
    private static final String URL = "https://wqqmeoqsimjodihxysqs.supabase.co/rest/v1/traduccionesGuardadas";
    private static final String API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6IndxcW1lb3FzaW1qb2RpaHh5c3FzIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTQyNjgzOTIsImV4cCI6MjAyOTg0NDM5Mn0.Hv51BuiUKsUQI0l0PZzbFTKc4K2D6SkN_ga5ts9w544";

    public static void main(String[] args) {
        try {
            URL url = new URL(URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("apikey", API_KEY);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println(response.toString());
            } else {
                System.out.println("Error al realizar la solicitud. Código de respuesta: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
