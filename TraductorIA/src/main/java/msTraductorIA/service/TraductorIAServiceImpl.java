package msTraductorIA.service;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TraductorIAServiceImpl {
    private static String key = "b328c314264746f885a937ada7680e72";
    private static String location = "eastus";

    // Instancia OkHttpClient.
    OkHttpClient client = new OkHttpClient();

    // Solicitud POST.
    public String Post() throws IOException {
        // Crea el cuerpo de la solicitud
        String jsonData = "[{\"Text\": \" It is an expression here in Peru, but it is not bad here, it refers to a person who makes donations\"}]";
        MediaType mediaType = MediaType.parse("application/json");
        @SuppressWarnings("deprecation")
		RequestBody body = RequestBody.create(mediaType, jsonData);

        // Crea la solicitud
        Request request = new Request.Builder()
                .url("https://api.cognitive.microsofttranslator.com/translate?api-version=3.0&from=en&to=es")
                .post(body)
                .addHeader("Ocp-Apim-Subscription-Key", key)
                // location required if you're using a multi-service or regional (not global) resource.
                .addHeader("Ocp-Apim-Subscription-Region", location)
                .addHeader("Content-type", "application/json")
                .build();

        // Realiza la llamada y obtiene la respuesta
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    // Crea un respuesta JSON.
    public static String prettify(String json_text) {
        @SuppressWarnings("deprecation")
		JsonParser parser = new JsonParser();
        @SuppressWarnings("deprecation")
		JsonElement json = parser.parse(json_text);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(json);
    }

    public static void main(String[] args) {
        try {
        	TraductorIAServiceImpl translateRequest = new TraductorIAServiceImpl();
            String response = translateRequest.Post();
            System.out.println(prettify(response));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}