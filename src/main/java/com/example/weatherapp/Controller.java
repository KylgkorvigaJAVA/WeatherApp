package com.example.weatherapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {


    @FXML
    private Label humidity;

    @FXML
    private Label pressure;

    @FXML
    private Button searchButton;

    @FXML
    private Label temperature;

    @FXML
    private TextField textField;

    @FXML
    private Label welcomeLabel;

    @FXML
    private Label windDirAndSpeed;

    @FXML
    void getWeather(ActionEvent event) {

    }

}