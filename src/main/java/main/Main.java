package main;

import main.server.HttpTaskServer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        HttpTaskServer httpTaskServer = new HttpTaskServer("/tasks/");
        httpTaskServer.start();
    }
}