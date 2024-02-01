module com.example.temperatureconversion {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.temperatureconversion to javafx.fxml;
    exports com.example.temperatureconversion;
}