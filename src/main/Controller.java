package main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import system.QueuingSystem;

public class Controller
{

    @FXML
    public TextField serviceChannelsCountField;

    @FXML
    public TextField averageServiceTimeField;

    @FXML
    public TextField averageRequestIncomingTimeField;

    @FXML
    public TextField averageTimeInSystemField;

    @FXML
    public Button runButton;

    public void runButtonClicked() {
        int serviceChannelsCount = Integer.parseInt(this.serviceChannelsCountField.getText());
        long averageServiceTime = Long.parseLong(this.averageServiceTimeField.getText());
        long averageRequestIncomingTime = Long.parseLong(this.averageRequestIncomingTimeField.getText());
        QueuingSystem queuingSystem = new QueuingSystem(
                averageRequestIncomingTime,
                serviceChannelsCount,
                averageServiceTime
        );
        queuingSystem.run();
        this.averageTimeInSystemField.setText(
                Long.toString(
                        queuingSystem.calculateAverageTimeInSystem()
                )
        );
    }
}
