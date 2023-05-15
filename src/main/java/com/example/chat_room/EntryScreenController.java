package com.example.chat_room;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class EntryScreenController {
    @FXML
    private Button enterButton;

    @FXML
    private TextField tfUsername;

    @FXML
    protected void enter(ActionEvent event){
        if(tfUsername.getText() != null) {
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-view.fxml"));
            Parent root;
            try {
                root = fxmlLoader.load();
            } catch (IOException e) {
                System.out.println("Failed to load main menu fxml.");
                throw new RuntimeException(e);
            }

            Scene scene = new Scene(root);
            stage.setTitle("Chat Room");
            stage.setScene(scene);
            stage.show();
        }
    }
}
