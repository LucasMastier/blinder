package blinderGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

import static blinderGUI.Main.switchTo;

public class MainMenuController {
    @FXML
    private Button trainingModeButton;

    @FXML
    public void switchToTrainingMode(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainMenuController.class.getResource("TrainingGame.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(),800,500);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToMultiplayerMode(ActionEvent event) throws IOException {
        switchTo(event, "MultiplayerGamesList.fxml");
    }

}
