package com.example.chat_room;

import javafx.scene.layout.VBox;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    //private String username;

    public Client(Socket socket){
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            System.out.println("Error creating client");
            e.printStackTrace();
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void sendMessageToServer(String messageToServer){
        try {
            bufferedWriter.write(messageToServer);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error sending message to the server");
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void receiveMessageFromServer(VBox vbox){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(socket.isConnected()){
                    try {
                        String messageFromServer = bufferedReader.readLine();
                        ChatController2.addLabel(messageFromServer, vbox);
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Error receiving message from the server");
                        closeEverything(socket, bufferedReader, bufferedWriter);
                        break;
                    }
                }
            }
        }).start();
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
        try {
            if(bufferedReader != null)
                bufferedReader.close();
            if(bufferedWriter != null)
                bufferedWriter.close();
            if(socket != null)
                socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public Client(Socket socket, String username) {
//        try {
//            this.socket = socket;
//            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            this.username = username;
//        } catch (IOException e) {
//            System.out.println("Failed to create user.");
//            closeEverything(socket, bufferedReader, bufferedWriter);
//        }
//
//    }
//
//    public void sendMessage() {
//        try {
//            bufferedWriter.write(username);
//            bufferedWriter.newLine();
//            bufferedWriter.flush();
//
//            Scanner scanner = new Scanner(System.in);
//            while (socket.isConnected()) {
//                String messageToSend = scanner.nextLine();
//                bufferedWriter.write(username + ": " + messageToSend);
//                bufferedWriter.newLine();
//                bufferedWriter.flush();
//            }
//        } catch (IOException e) {
//            closeEverything(socket, bufferedReader, bufferedWriter);
//
//        }
//
//    }
//
//    public void listenForMessage() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String messageFromGroupchat;
//
//                while (socket.isConnected()) {
//                    try {
//                        messageFromGroupchat = bufferedReader.readLine();
//                        System.out.println(messageFromGroupchat);
//                    } catch (IOException e) {
//                        closeEverything(socket, bufferedReader, bufferedWriter);
//
//                    }
//                }
//            }
//        }).start();
//    }
//
//    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
//        try {
//            if (bufferedReader != null) {
//                bufferedReader.close();
//            }
//            if (bufferedWriter != null) {
//                bufferedWriter.close();
//            }
//            if (socket != null) {
//                socket.close();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }
//
//    public static void main(String[] args) throws IOException {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter username:");
//        String username = scanner.nextLine();
//        Socket socket = new Socket("localHost", 1234);
//        Client client = new Client(socket, username);
//        client.listenForMessage();
//        client.sendMessage();
//    }
}
