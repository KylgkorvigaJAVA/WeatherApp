module com.example.weatherapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;


    opens com.example.weatherapp to javafx.fxml;
    exports com.example.weatherapp;
}