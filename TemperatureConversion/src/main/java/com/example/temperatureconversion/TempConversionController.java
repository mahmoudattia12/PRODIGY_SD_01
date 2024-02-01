package com.example.temperatureconversion;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TempConversionController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}