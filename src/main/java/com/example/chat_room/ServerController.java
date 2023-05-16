package com.example.chat_room;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerController extends MasterController {
    @FXML
    private Button startServerButton;

    @FXML
    protected void startServerOnClick(ActionEvent actionEvent) {
//        try {
//            Server server = new Server(new ServerSocket(1234));
//            server.startServer();
//        } catch (IOException e) {
//            System.out.println("Error starting server");
//            e.printStackTrace();
//        }
    }
}
