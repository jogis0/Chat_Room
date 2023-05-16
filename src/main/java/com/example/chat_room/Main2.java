package com.example.chat_room;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;

public class Main2 extends Application {
    /* Main class for the server */
    @Override
    public void start(Stage stage) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("server-view.fxml"));
//        stage.setTitle("Chat Room");
//        stage.setScene(new Scene(root));
//        stage.show();
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
