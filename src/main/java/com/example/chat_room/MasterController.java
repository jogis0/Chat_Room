package com.example.chat_room;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

abstract class MasterController {
    public void switchToStage(String fxmlName, Stage stage){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlName));
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("Failed to load fxml.");
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(root);
        stage.setTitle("Chat Room");
        stage.setScene(scene);
        stage.show();
    }
}
