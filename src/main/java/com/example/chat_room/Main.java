package com.example.chat_room;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
        stage.setTitle("Chat Room");
        stage.setScene(new Scene(root, 500, 400));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}