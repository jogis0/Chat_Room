package com.example.chat_room;

import javafx.scene.layout.VBox;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;


    public Client(Socket socket, String username) {
        try {
            this.socket = socket;
            this.username = username;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writeUsername(username);
        } catch (IOException e) {
            System.out.println("Failed to create user.");
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void writeUsername(String username){
        try {
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            System.out.println("Couldn't send username to ClientHandler");
            e.printStackTrace();
        }
    }

    public void sendMessage(String messageToSend) {
        try {
            if(socket.isConnected()) {
                bufferedWriter.write(username + ": " + messageToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void listenForMessage(VBox vbox) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String messageFromGroupchat;
                while (socket.isConnected()) {
                    try {
                        messageFromGroupchat = bufferedReader.readLine();
                        System.out.println(messageFromGroupchat);
                        ChatController.addLabel(messageFromGroupchat, vbox);
                    } catch (IOException e) {
                        closeEverything(socket, bufferedReader, bufferedWriter);
                    }
                }
            }
        }).start();
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
