package blinderGUI;

import blinderBackEnd.model.Game;
import blinderBackEnd.model.Player;
import blinderBackEnd.model.PlayerService;
import blinderBackEnd.model.Playlist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;

public class GameConnectionController {

    @FXML
    private TextField usernameInput;

    @FXML
    private Button confirmButton;

    @FXML
    private static Game currentGame;

    @FXML
    private TilePane playersContainer;

    @FXML
    private boolean isConnected = false;



    private static Player currentPlayer;

    @FXML
    private Label warning;

    @FXML
    public void storeGameInstance(Game game){
        currentGame = game;
    }



    @FXML
    public void confirmUsername(ActionEvent event){
        if(!isConnected){
            /*
            Player player = new Player(usernameInput.getText());
            PlayerService.addPlayerToPlayersList(player);
            currentGame.addPlayer(player);
            currentPlayer = player;
            playersContainer.getChildren().add(new Label(player.getUsername()));*/

            isConnected = true;
        } else {
            warning.setText("Vous êtes déjà connecté en tant que "+currentPlayer.getUsername()+" !");
        }

    }



}
