package blinderGUI;

import blinderBackEnd.model.Game;
import blinderBackEnd.model.GameService;
import blinderBackEnd.model.Playlist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

import java.io.IOException;

import static blinderGUI.Main.switchTo;

public class GameCreationController {

    @FXML
    private TextField gameNameInput;

    private static Playlist chosenPlaylist;

    public void storePlaylistInstance(Playlist playlist) {
        chosenPlaylist = playlist;
    }

    @FXML
    public void gameCreation(ActionEvent event) throws IOException {
        Game game = new Game(chosenPlaylist, gameNameInput.getText());
        GameService.addGameToList(game);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameConnection.fxml"));
        try {
            Parent root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //GameConnectionController gameConnectionController = loader.getController();
        //gameConnectionController.storeGameInstance(game);

        switchTo(event,"GameConnectionController");
    }

}
