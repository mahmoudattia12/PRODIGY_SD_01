package com.example.temperatureconversion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class TempConversionApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TempConversionApp.class.getResource("TempConversion-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 400);
        stage.setTitle("Temperature Conversion");

        Image icon = new Image(String.valueOf(getClass().getResource("temp2.png")));
        stage.getIcons().add(icon);

        stage.setScene(scene);
        stage.show();
    }
}