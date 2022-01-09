package blinderGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

import static blinderGUI.Main.switchTo;

public class TrainingScoreController {
    @FXML
    private Button replayButton;

    @FXML
    private Label scoreDisplay;

    @FXML
    private Button mainMenuButton;

    public void initialize(){
        scoreDisplay.setText("phrase a inserer");
    }

    public void switchToMainMenu(ActionEvent event) throws IOException {
        switchTo(event, "MainMenu.fxml");
    }

    public void replayTrainingMode(ActionEvent event) throws IOException {
        //Reset le compteur de score
        switchTo(event, "TrainingGame.fxml");
    }

}
