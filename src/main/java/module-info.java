module com.example.carrolobj {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.carrolobj to javafx.fxml;
    exports com.example.carrolobj;
}