package main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import optimization.Point;
import optimization.QueuingSystemFunction;
import optimization.ResponseFunctionInterface;
import optimization.approximation.ApproximationHandler;

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

    private ApproximationHandler approximationHandler;

    private void initApproximationHandler() {
        int serviceChannelsCount = Integer.parseInt(this.serviceChannelsCountField.getText());
        long averageServiceTime = Long.parseLong(this.averageServiceTimeField.getText());
        long averageRequestIncomingTime = Long.parseLong(this.averageRequestIncomingTimeField.getText());

        ResponseFunctionInterface responseFunction = new QueuingSystemFunction(averageRequestIncomingTime);
        Point startPoint = new Point(
                serviceChannelsCount,
                averageServiceTime,
                responseFunction.calculate(serviceChannelsCount, averageServiceTime)
        );

        this.approximationHandler = new ApproximationHandler(startPoint, responseFunction);
    }

    private void writeData(Point point) {
        this.serviceChannelsCountField.setText(Long.toString((long) point.x1));
        this.averageServiceTimeField.setText(Long.toString((long) point.x2));
        this.averageTimeInSystemField.setText(Long.toString((long) point.y));
    }

    public void runButtonClicked() {
        this.initApproximationHandler();
        this.writeData(this.approximationHandler.run());
    }
}
