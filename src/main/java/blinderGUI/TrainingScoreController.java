package blinderGUI;

import blinderBackEnd.model.GameService;
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
        scoreDisplay.setText("Votre score : "+GameService.getScore_cpt());
    }

    public void switchToMainMenu(ActionEvent event) throws IOException {
        switchTo(event, "MainMenu.fxml");
        GameService.setScore_cpt(0);
    }

    public void replayTrainingMode(ActionEvent event) throws IOException {
        GameService.setScore_cpt(0);
        switchTo(event, "PlaylistChoice.fxml");
    }

}
