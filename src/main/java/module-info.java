module com.example.chat_room {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.chat_room to javafx.fxml;
    exports com.example.chat_room;
}