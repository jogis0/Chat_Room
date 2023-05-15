package com.example.chat_room;

import javafx.scene.layout.VBox;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Server {
    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public Server(ServerSocket serverSocket){
        try {
            this.serverSocket = serverSocket;
            this.socket = serverSocket.accept(); //Sitas neveik
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (SocketTimeoutException e){
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error creating server");
            e.printStackTrace();
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void sendMessageToClient(String messageToClient){
        try {
            bufferedWriter.write(messageToClient);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error sending message to the client");
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void receiveMessageFromClient(VBox vbox){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(socket.isConnected()){
                    try {
                        String messageFromClient = bufferedReader.readLine();
                        ChatController.addLabel(messageFromClient, vbox);
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Error recieving message from the client");
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

//    public Server(ServerSocket serverSocket) {
//        this.serverSocket = serverSocket;
//    }
//    public void startServer(){
//        try {
//            while (!serverSocket.isClosed()){
//                Socket socket = serverSocket.accept();
//                System.out.println("A new client has connected!");
//                ClientHandler clientHandler = new ClientHandler(socket);
//
//                Thread thread = new Thread(clientHandler );
//                thread.start();
//
//            }
//
//        }catch (IOException e ){
//            System.out.println("Failed to start server.");
//            e.printStackTrace();
//        }
//    }
//
//    public void closeServerSocket(){
//        try {
//            if (serverSocket != null){
//                serverSocket.close();
//            }
//
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) throws IOException {
//        ServerSocket serverSocket = new ServerSocket(1234);
//        Server server = new Server(serverSocket);
//        server.startServer();
//    }
}
