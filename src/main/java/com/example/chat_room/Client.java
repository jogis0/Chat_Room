package com.example.chat_room;

import javafx.scene.layout.VBox;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    public Socket socket;
    public BufferedReader bufferedReader;
    public BufferedWriter bufferedWriter;
    public String username;
    public String receiver;
    public ArrayList<String> messageData = new ArrayList<>();


    public Client(Socket socket, String username, String receiver) {
        try {
            this.socket = socket;
            this.username = username;
            this.receiver = receiver;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writeUsername(username, receiver);
        } catch (IOException e) {
            System.out.println("Failed to create user.");
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void writeUsername(String username, String receiverName){
        try {
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.write(receiverName);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            messageData.add(this.username + " " + receiver + "\n");
        } catch (IOException e) {
            System.out.println("Couldn't send username to ClientHandler");
            e.printStackTrace();
        }
    }

    public void sendMessage(String messageToSend) {
        try {
            if(socket.isConnected()) {
                bufferedWriter.write(username + ": " + messageToSend);
                messageData.add(username + ": " + messageToSend + "\n");
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
                        messageData.add(messageFromGroupchat + "\n");
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
