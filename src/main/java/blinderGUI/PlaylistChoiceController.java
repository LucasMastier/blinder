package blinderGUI;

import blinderBackEnd.model.Game;
import blinderBackEnd.model.GameService;
import blinderBackEnd.model.Playlist;
import blinderBackEnd.model.PlaylistService;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistChoiceController {

    @FXML
    private TilePane buttonsContainer;

    private List<Button> buttonList = new ArrayList<>();

    @FXML
    public void initialize(){
        ArrayList<Playlist> playlists = PlaylistService.getPlaylists();
        for(Playlist playlist : playlists){

            Button button = new Button(playlist.getTitre());
            button.setOnAction(new EventHandler() {

                @Override
                public void handle(Event event) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("GameCreation.fxml"));
                    try {
                        Parent root = loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    GameCreationController gameCreationController = loader.getController();
                    gameCreationController.storePlaylistInstance(playlist);


                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GameCreation.fxml"));
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
