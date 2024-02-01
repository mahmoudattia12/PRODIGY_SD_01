package com.example.temperatureconversion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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

        // Check if the input field is empty
        if (input.isEmpty()) {
            showAlert();
            return;
        }

        resultField.getChildren().clear();
        resultField.getChildren().addAll(createHBox("unit1", "val1"), createHBox("unit2", "val2"));
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText("Please enter the temperature value first!");
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
            if(indexPos != -1)
                inputField.setText(input.substring(0, indexPos) + input.substring(indexPos + 1));
            inputField.positionCaret(indexPos);
        }
    }

    private boolean isValidChar(String input, char curr){
        return Character.isDigit(curr) || (curr == '.' && input.chars().filter(ch -> ch == '.').count() == 1);
    }
}