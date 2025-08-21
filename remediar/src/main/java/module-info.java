module com.example.demo2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens com.example.demo2 to javafx.fxml;
    opens com.example.demo2.controller to javafx.fxml;
    opens com.example.demo2.model to javafx.base;

    exports com.example.demo2;
    exports com.example.demo2.controller;
}
