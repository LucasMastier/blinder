package blinderGUI;

import blinderBackEnd.model.*;
import javafx.event.ActionEvent;
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

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static blinderBackEnd.model.PlaylistService.getRandomAuthorFromPlaylist;
import static blinderGUI.Main.switchTo;

public class MultiplayerGamesListController {
    //Socket
    private Socket socket;

    private ObjectInputStream in;
    private ObjectOutputStream out;

    public void storeSocket(Socket socket, ObjectInputStream in, ObjectOutputStream out) throws IOException {
        this.socket = socket;
        this.in = in;
        this.out = out;
    }

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

                    GameConnectionController gameConnectionController = fxmlLoader.getController();
                    try {
                        gameConnectionController.storeSocketAndGame(socket, in, out, game);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            });
            buttonList.add(button);
        }
        buttonsContainer.getChildren().clear();
        buttonsContainer.getChildren().addAll(buttonList);
    }

    @FXML
    public void switchToPlaylistChoice(ActionEvent event) throws IOException {
        switchTo(event,"PlaylistChoice.fxml");
    }

}
