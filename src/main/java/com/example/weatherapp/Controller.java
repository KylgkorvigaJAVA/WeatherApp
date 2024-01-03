package com.example.weatherapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Controller {

    String icon, description;

    Image img;

    Double temp, windSpd;

    Long hum, windDir, hPa;


    @FXML
    private ImageView bdImg;
    @FXML
    private Label descriptionLabel;

    @FXML
    private Label humidityLabel;

    @FXML
    private ImageView imgV;

    @FXML
    private Label pressureLabel;

    @FXML
    private Button searchButton;

    @FXML
    private Label tempLabel;

    @FXML
    private TextField txtField;

    @FXML
    private Label welcomeLabel;

    @FXML
    private Label windDirLabel;

    @FXML
    private Label windSpeedLabel;


    @FXML
    void getWeather(ActionEvent event) {
        HttpURLConnection conn = null;
        int responsecode = 0;
        BufferedReader br;
        StringBuilder strBuilder = new StringBuilder();

        try {
            String myUrl = "https://api.openweathermap.org/data/2.5/weather?q="
                    + txtField.getText() +
                    "&appid=0779ef4a8256a070c2c52e296c840d5d&units=metric";

            URL url = new URL(myUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(6000);
            conn.setReadTimeout(6000);

            responsecode = conn.getResponseCode();
            System.out.println("HttpResponseCode: " + responsecode);
            if (responsecode >= 300) {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            }

            String line;
            while ((line = br.readLine()) != null) {
                strBuilder.append(line);
            }
            br.close();
            System.out.println("Inside builder: " + strBuilder);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

        try {
            JSONParser parse = new JSONParser();
            JSONObject data_obj = (JSONObject) parse.parse(strBuilder.toString());
            JSONObject main_obj = (JSONObject) data_obj.get("main");
            JSONObject wind_obj = (JSONObject) data_obj.get("wind");

            temp = (double) main_obj.get("temp");
            System.out.println(temp);

            hPa = (long) main_obj.get("pressure");
            System.out.println(hPa);

//            JSONArray arr = (JSONArray) data_obj.get("wind");
//            JSONObject wind_obj = (JSONObject) arr.get(0);

            windDir = (long) wind_obj.get("deg");
            System.out.println(windDir);

            windSpd = (double) wind_obj.get("speed");
            System.out.println(windSpd);

            hum = (long) main_obj.get("humidity");
            System.out.println(hum);

            JSONArray arr = (JSONArray) data_obj.get("weather");
            JSONObject weather_obj = (JSONObject) arr.get(0);

            icon = (String) weather_obj.get("icon");
            System.out.println(icon);

            description = (String) weather_obj.get("description");
            System.out.println(description);

            String path = "https://openweathermap.org/img/wn/" + icon + "@2x.png";

            img = new Image(path);

            displayWeatherInfo();

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void displayWeatherInfo() {

        String tempStr = temp + "°C";
        tempLabel.setText(tempStr);

        descriptionLabel.setText(description);
        imgV.setImage(img);
        windDirLabel.setText(windDir + "°");

        String windSpdTemp = windSpd + "m/s";
        windSpeedLabel.setText(windSpdTemp);

        String tempPressure = hPa + "hPa";
        pressureLabel.setText(tempPressure);

        String tempHum = hum + "%";
        humidityLabel.setText(tempHum);







    }

}