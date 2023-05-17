package com.example.chat_room;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ChatController extends MasterController implements Initializable {
    @FXML
    private ScrollPane spMain;

    @FXML
    private VBox messageVBox;

    @FXML
    private TextField tfMessage;

    @FXML
    private Button sendButton, backButton, saveButton;

    @FXML
    private Label nameLabel;

    private Client client;

    private String username;

    private String receiverName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Singleton singleton = Singleton.getInstance();
            this.username = singleton.getUsername();
            this.receiverName = singleton.getReceiverName();
            nameLabel.setText(username);
            Socket socket = new Socket("localHost", 1234);
            client = new Client(socket, username, receiverName);
            System.out.println("CLIENT CREATED!");
        } catch (IOException e) {
            System.out.println("Error starting user");
            e.printStackTrace();
        }
        messageVBox.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                spMain.setVvalue((Double) newValue);
            }
        });

        client.listenForMessage(messageVBox);
    }

    public static void addLabel(String messageFromServer, VBox vbox){
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setPadding(new Insets(5, 5, 5, 10));

        Text text = new Text(messageFromServer);
        TextFlow textFlow = new TextFlow(text);
        textFlow.setStyle("-fx-background-color: rgb(233,233,235); " +
                "-fx-background-radius: 20px;");
        textFlow.setPadding(new Insets(5, 10, 5, 10));
        hbox.getChildren().add(textFlow);

        //You can only update the gui with main application thread
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vbox.getChildren().add(hbox);
            }
        });
    }

    @FXML
    public void handle(ActionEvent event) {
        String messageToSend = tfMessage.getText();
        if(!messageToSend.isEmpty()){
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER_RIGHT);
            hBox.setPadding(new Insets(5, 5, 5, 10));

            Text text = new Text(messageToSend);
            TextFlow textFlow = new TextFlow(text);

            textFlow.setStyle("-fx-color: rgb(239,242,255); " +
                    "-fx-background-color: rgb(15,125,242);" +
                    " -fx-background-radius: 20px");
            textFlow.setPadding(new Insets(5, 10, 5, 10));
            text.setFill(Color.color(0.934, 0.945, 0.996));

            hBox.getChildren().add(textFlow);
            messageVBox.getChildren().add(hBox);

            client.sendMessage(messageToSend);
            tfMessage.clear();
        }
    }

    //FOR EXITING THE PROGRAM
    @FXML
    public void backToMenu(ActionEvent event) {
        //client.closeEverything(client.socket, client.bufferedReader, client.bufferedWriter);
        switchToStage("main-view.fxml", (Stage)((Node)event.getSource()).getScene().getWindow());
    }

    @FXML
    public void saveToFile(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export file");
        FileChooser.ExtensionFilter csvFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(csvFilter);
        File file = fileChooser.showSaveDialog(new Stage());

        FileWriter writer = new FileWriter(file);
        for(int i = 0; i < client.messageData.size(); ++i){
            writer.write(client.messageData.get(i));
        }
        writer.close();
    }
}
