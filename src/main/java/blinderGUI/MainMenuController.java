package blinderGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainMenuController {
    @FXML
    private Button trainingModeButton;

    @FXML
    public void switchToTrainingMode(ActionEvent event) throws IOException {
        Main.switchTo("TrainingGame.fxml");
    }

}
