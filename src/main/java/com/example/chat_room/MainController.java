package com.example.chat_room;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private Button joinButton;

    @FXML
    protected void loadChat(ActionEvent event){
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("chat-view.fxml"));
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("Failed to load chat menu fxml.");
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(root, 500, 400);
        stage.setTitle("Chat Room");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void loadChat2(ActionEvent event){
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("chat-view2.fxml"));
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("Failed to load chat menu fxml.");
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(root, 500, 400);
        stage.setTitle("Chat Room");
        stage.setScene(scene);
        stage.show();
    }
}