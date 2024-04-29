package msTraductorIA.PruebasCRUD;

import java.io.IOException;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Create {
    private static final String SUPABASE_URL = "https://wqqmeoqsimjodihxysqs.supabase.co";
    private static final String SUPABASE_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6IndxcW1lb3FzaW1qb2RpaHh5c3FzIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTQyNjgzOTIsImV4cCI6MjAyOTg0NDM5Mn0.Hv51BuiUKsUQI0l0PZzbFTKc4K2D6SkN_ga5ts9w544";

    private static String key = "b328c314264746f885a937ada7680e72";
    private static String location = "eastus";

    OkHttpClient client = new OkHttpClient();

    public String post(String texto) throws IOException {
        String jsonData = "[{\"Text\": \"" + texto + "\"}]";
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, jsonData);

        Request request = new Request.Builder()
                .url("https://api.cognitive.microsofttranslator.com/translate?api-version=3.0&from=en&to=es")
                .post(body)
                .addHeader("Ocp-Apim-Subscription-Key", key)
                .addHeader("Ocp-Apim-Subscription-Region", location)
                .addHeader("Content-type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        String jsonResponse = response.body().string();

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

    public void insertarTraduccion(String palabraIngresada, String palabraTraducida) {
        try {
            JsonObject data = new JsonObject();
            data.addProperty("palabraIngresada", palabraIngresada);
            data.addProperty("palabraTraducida", palabraTraducida);

            // Imprimir los datos antes de enviar la solicitud
            System.out.println("Datos a enviar: " + data.toString());

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, data.toString());

            Request request = new Request.Builder()
                    .url(SUPABASE_URL + "/rest/v1/traduccionesGuardadas")
                    .addHeader("apikey", SUPABASE_KEY)  // Usar la clave de autorización como un encabezado de apikey
                    .addHeader("Content-Type", "application/json")
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                System.out.println("Traducción guardada correctamente en la base de datos.");
            } else {
                System.out.println("Error al guardar la traducción en la base de datos. Código de respuesta: " + response.code());
            }
        } catch (IOException e) {
            System.out.println("Error al realizar la solicitud HTTP: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Create translateRequest = new Create();

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
                        String response = translateRequest.post(palabra);
                        System.out.println(response);
                        translateRequest.insertarTraduccion(palabra, response);
                    } catch (IOException e) {
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
