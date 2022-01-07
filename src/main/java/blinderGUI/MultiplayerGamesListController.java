package blinderGUI;

import blinderBackEnd.model.Game;
import blinderBackEnd.model.GameService;
import blinderBackEnd.model.PlaylistService;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static blinderBackEnd.model.PlaylistService.getRandomAuthorFromPlaylist;

public class MultiplayerGamesListController {

    @FXML
    private TilePane buttonsContainer;

    @FXML
    private Label answerLabel;

    private List<Button> buttonList = new ArrayList<>();

    @FXML
    public void initialize(){
        for(Game game : GameService.getGamesList()){

            Button button = new Button(game.getName());
            button.setOnAction(new EventHandler() {

                @Override
                public void handle(Event event) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("GameConnection.fxml"));
                    try {
                        Parent root = loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    GameConnectionController gameConnectionController = loader.getController();
                    gameConnectionController.storeGameInstance(game);


                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GameConnection.fxml"));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = null;
                    try {
                        scene = new Scene(fxmlLoader.load(),800,500);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    stage.setScene(scene);
                    stage.show();
                }

            });
            buttonList.add(button);
        }
        buttonsContainer.getChildren().clear();
        buttonsContainer.getChildren().addAll(buttonList);
    }

}
