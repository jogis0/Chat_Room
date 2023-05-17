package com.example.chat_room;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;

public class Main2 extends Application {
    /* Main class for the server */
    @Override
    public void start(Stage stage) throws IOException {
        try {
            Server server = new Server(new ServerSocket(1234));
            server.startServer();
        } catch (IOException e) {
            System.out.println("Error starting server");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
