package com.example.chat_room;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class EntryScreenController extends MasterController {

    @FXML
    private TextField tfUsername;

    @FXML
    protected void enter(ActionEvent event){
        String username = tfUsername.getText();
        System.out.println(username);

        Singleton singleton = Singleton.getInstance();
        singleton.setUsername(username);
        if(!Objects.equals(username, "")) {
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-view.fxml"));

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
}
