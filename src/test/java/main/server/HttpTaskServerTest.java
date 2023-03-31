package main.server;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.HttpServer;
import main.managers.Managers;
import main.managers.task.TaskManager;
import main.model.Task;
import main.model.enums.TaskType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class HttpTaskServerTest {
    private HttpServer server;
    private TaskManager taskManager;
    private final Gson gson = Managers.getGson();

    private Task task;


    @BeforeEach
    void init() {


    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getTasks() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create("http://localhost:8080/tasks");
        HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode());
        Type taskType = new TypeToken<ArrayList<Type>>() {
        }.getType();
//        List<Task> actual = gson.toJson(response.body(), TaskType);
//        assertNotNull(actual);
//        assertEquals(1, actual.size(), "message");
//        assertEquals(task );


    }

    @Test
    void stop() {

    }
}