package com.example.chat_room;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController extends MasterController {
    @FXML
    private Label usernameLabel;

    private String username;

    @FXML
    protected void createChat(ActionEvent event){
        //switchToStage("server-view.fxml", new Stage());

    }

    @FXML
    protected void joinChat(ActionEvent event){
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("chat-view.fxml"));

        Singleton singleton = Singleton.getInstance();
        singleton.setUsername(username);

        Parent root;
        try {
            //When fxml is loaded, initialize method is run with a null username
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

    public void setUsername(String username){
        this.username = username;
        usernameLabel.setText(username);
    }
}