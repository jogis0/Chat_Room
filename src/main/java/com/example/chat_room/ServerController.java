package com.example.chat_room;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerController {

    @FXML
    public void startServer(ActionEvent event){
        try {
            Server server = new Server(new ServerSocket(1234));
            server.startServer();
        } catch (IOException e) {
            System.out.println("Error starting server");
            e.printStackTrace();
        }
    }
}
