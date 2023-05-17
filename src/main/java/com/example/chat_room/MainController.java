package com.example.chat_room;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends MasterController implements Initializable {
    @FXML
    private Label usernameLabel;

    @FXML
    private TextField userField;

    private String username;

    @FXML
    protected void joinGroup(ActionEvent event){
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("chat-view.fxml"));

        Singleton singleton = Singleton.getInstance();
        singleton.setUsername(username);
        singleton.setReceiverName("");
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

    @FXML
    protected void joinUser(ActionEvent event){
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("chat-view.fxml"));

        Singleton singleton = Singleton.getInstance();
        singleton.setUsername(username);
        singleton.setReceiverName(userField.getText());
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Singleton singleton = Singleton.getInstance();
        username = singleton.getUsername();
        usernameLabel.setText(username);
    }
}