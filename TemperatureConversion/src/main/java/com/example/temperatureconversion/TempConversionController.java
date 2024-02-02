package com.example.temperatureconversion;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TempConversionController {
    @FXML
    private TextField inputField;

    @FXML
    private ChoiceBox<String> unitChoiceBox;

    @FXML
    public VBox resultField;

    @FXML
    private void initialize() {
        unitChoiceBox.setValue("Celsius");
    }

    @FXML
    private void onConvertButtonClick() {
        String input = inputField.getText().trim();

        if(!isNumeric(input)){
            showAlert();
            return;
        }

        resultField.getChildren().clear();
        double inputVal = Double.parseDouble(input);

        switch (unitChoiceBox.getValue()) {
            case "Celsius": {
                double fahrenheit = CelsiusToFahrenheit(inputVal);
                double kelvin = FahrenheitToKelvin(fahrenheit);
                resultField.getChildren().addAll(createHBox("Fahrenheit: ", String.format("%.5f", fahrenheit) + " °F"),
                                                createHBox("Kelvin: ", String.format("%.5f", kelvin) + " °K"));
                break;
            }
            case "Fahrenheit": {
                double kelvin = FahrenheitToKelvin(inputVal);
                double celsius = KelvinToCelsius(kelvin);
                resultField.getChildren().addAll(createHBox("Celsius: ", String.format("%.5f", celsius) + " °C"),
                                                createHBox("Kelvin: ", String.format("%.5f", kelvin) + " °K"));
                break;
            }
            default: {
                double celsius = KelvinToCelsius(inputVal);
                double fahrenheit = CelsiusToFahrenheit(celsius);
                resultField.getChildren().addAll(createHBox("Celsius: ", String.format("%.5f", celsius) + " °C"),
                                                createHBox("Fahrenheit: ", String.format("%.5f", fahrenheit) + " °F"));
            }
        }
    }

    public double CelsiusToFahrenheit(double celsius) {
        return (9.0 / 5) * celsius + 32;
    }

    public double FahrenheitToKelvin(double fahrenheit) {
        return (5.0 / 9) * (fahrenheit - 32) + 273.15;
    }

    public double KelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Input Error");

        Image icon = new Image(String.valueOf(getClass().getResource("warning.png")));
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(icon);

        alert.setHeaderText(null);
        alert.setContentText("Please enter a numeric value for the temperature!");
        alert.showAndWait();
    }

    private HBox createHBox(String unit, String val) {
        HBox resultHBox = new HBox();
        resultHBox.setAlignment(Pos.CENTER);
        resultHBox.setSpacing(10);

        Label titleLabel1 = new Label(unit);
        titleLabel1.getStyleClass().add("Title");
        Label valueLabel1 = new Label(val);
        valueLabel1.getStyleClass().add("Title");


        resultHBox.getChildren().addAll(titleLabel1, valueLabel1);
        return resultHBox;
    }

    @FXML
    private void onInputKeyTyped(KeyEvent event) {
        String input = inputField.getText();
        int indexPos = inputField.getCaretPosition() - 1;

        if (!isValidChar(input, event.getCharacter().charAt(0))) {
            event.consume();
            // Remove the wrong character from the text field
            if (indexPos != -1) inputField.setText(input.substring(0, indexPos) + input.substring(indexPos + 1));
            inputField.positionCaret(indexPos);
        }
    }

    private boolean isValidChar(String input, char curr) {
        return Character.isDigit(curr)
                || (curr == '.' && input.chars().filter(ch -> ch == '.').count() == 1)
                || (input.length() == 1 && curr == '-');
    }

    public boolean isNumeric(String str) {
        try {
            // Try to parse the string as a double
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            // If an exception is thrown, the string is not a number
            return false;
        }
    }
}