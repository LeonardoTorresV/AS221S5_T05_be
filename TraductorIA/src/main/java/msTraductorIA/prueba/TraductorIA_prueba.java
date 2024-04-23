package msTraductorIA.prueba;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TraductorIA_prueba {
    private static String key = "b328c314264746f885a937ada7680e72";
    private static String location = "eastus";

    // Instancia OkHttpClient.
    OkHttpClient client = new OkHttpClient();

    public String Post(String texto) throws IOException {
        // Crea el cuerpo de la solicitud
    	String jsonData = "[{\"Text\": \"" + texto + "\"}]";
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, jsonData);

        // Crea la solicitud
        Request request = new Request.Builder()
                .url("https://api.cognitive.microsofttranslator.com/translate?api-version=3.0&from=en&to=es")
                .post(body)
                .addHeader("Ocp-Apim-Subscription-Key", key)
                .addHeader("Ocp-Apim-Subscription-Region", location)
                .addHeader("Content-type", "application/json")
                .build();

        // Realiza la llamada y obtiene la respuesta
        Response response = client.newCall(request).execute();
        String jsonResponse = response.body().string();

        // Extrae el texto de la traducción del JSON de la respuesta
        JsonParser parser = new JsonParser();
        JsonElement json = parser.parse(jsonResponse);
        JsonArray translations = json.getAsJsonArray().get(0).getAsJsonObject().get("translations").getAsJsonArray();

        StringBuilder translatedText = new StringBuilder();
        for (JsonElement translation : translations) {
          String text = translation.getAsJsonObject().get("text").getAsString();
          translatedText.append(text).append(" ");
        }

        return translatedText.toString().trim();
    }



    // Crea un respuesta JSON.
    public static String prettify(String json_text) {
        JsonParser parser = new JsonParser();
        JsonElement json = parser.parse(json_text);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(json);
    }

    // Inserta la traducción en la base de datos
    public void insertarTraduccion(String palabraIngresada, String palabraTraducida) throws SQLException {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=TRADUCTOR;encrypt=true;TrustServerCertificate=True;";
        String usuario = "sa";
        String contraseña = "francisDE";

        try (Connection con = DriverManager.getConnection(url, usuario, contraseña)) {
            String sql = "INSERT INTO traduccionesGuardadas (palabraIngresada, palabraTraducida) VALUES (?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, palabraIngresada);
            pstmt.setString(2, palabraTraducida);
            pstmt.executeUpdate();
            System.out.println("Traducción guardada correctamente en la base de datos.");
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TraductorIA_prueba translateRequest = new TraductorIA_prueba();

        boolean running = true;
        while (running) {
            System.out.println("1. Traducir de ingles a español");
            System.out.println("2. Salir");
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.println("Introduce la palabra a traducir:");
                    String palabra = scanner.nextLine();
                    try {
                        String response = translateRequest.Post(palabra);
                        String traduccion = prettify(response);
                        System.out.println(traduccion);
                        translateRequest.insertarTraduccion(palabra, traduccion); // Inserta la traducción en la base de datos
                    } catch (Exception e) {
                        System.out.println("Error al traducir y guardar la palabra: " + e.getMessage());
                    }
                    break;
                case 2:
                    running = false;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, elige una opción válida.");
                    break;
            }
        }

        scanner.close();
        System.out.println("¡Hasta luego!");
    }
}
