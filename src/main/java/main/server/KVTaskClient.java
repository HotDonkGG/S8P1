package main.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class KVTaskClient {
    private final String apiUrl;
    private final String apiToken;

    public KVTaskClient(String apiUrl) throws IOException {
        this.apiUrl = apiUrl;
      this.apiToken = register();
    }

    private String register() throws IOException {
        URL url = new URL(apiUrl + "/register");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
     //   connection.setDoOutput(true);

     //   String postData = "";

     //   byte[] postDataBytes = postData.getBytes(StandardCharsets.UTF_8);
      //  connection.getOutputStream().write(postDataBytes);

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
        String token = reader.readLine();
        reader.close();

        return token;
    }

    public void put(String key, String json) throws IOException {
        URL url = new URL(apiUrl + "/save/" + key + "?API_TOKEN=" + apiToken);
       // URL url = new URL(apiUrl + "/save/" + key + "?API_TOKEN=");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        byte[] postDataBytes = json.getBytes(StandardCharsets.UTF_8);
        connection.getOutputStream().write(postDataBytes);

        connection.getResponseCode();
    }
//localhost:8078/save/1?API_TOKEN=1680362422576
// http://localhost:8078/load/1?API_TOKEN=1680362422576
    public String load(String key) throws IOException {
        URL url = new URL(apiUrl + "/load/" + key + "?API_TOKEN=" + apiToken);
      //  URL url = new URL(apiUrl + "/load/" + key + "?API_TOKEN=" );
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        return response.toString();
    }
}
