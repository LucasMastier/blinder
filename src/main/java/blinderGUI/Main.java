package blinderGUI;

import blinderBackEnd.model.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("Blinder");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public static void switchTo(ActionEvent event, String page) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(page));

        //Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(),800,500);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        Song vvs = new Song("Ninho","VVS", "src/main/resources/mp3files/VVS.mp3");
        Song tr = new Song("Leto","Tout recommencer", "src/main/resources/mp3files/Tout_recommencer.mp3");
        Song fr = new Song("Freeze Corleone","Freeze raël", "src/main/resources/mp3files/Freeze_Rael.mp3");
        Song hs = new Song("Hamza","HS", "src/main/resources/mp3files/HS.mp3");

        Playlist rap = new Playlist("Rap");
        rap.addToPlaylist(vvs);
        rap.addToPlaylist(tr);
        rap.addToPlaylist(fr);
        rap.addToPlaylist(hs);

        PlaylistService.setCurrentPlaylist(rap);

        Game game = new Game(rap, "Partie 1");
        GameService.addGameToList(game);

        launch();
    }
}