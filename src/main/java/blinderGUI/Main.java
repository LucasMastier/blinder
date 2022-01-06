package blinderGUI;

import blinderBackEnd.model.Playlist;
import blinderBackEnd.model.PlaylistService;
import blinderBackEnd.model.Song;
import javafx.application.Application;
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


    public static void switchTo(String page) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(page));

        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(),1920,1080);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        Song vvs = new Song("Ninho","VVS", "C:\\Users\\U1\\Documents\\GitHub\\blinder\\src\\main\\resources\\mp3files\\VVS.mp3");
        Song tr = new Song("Leto","Tout recommencer", "C:\\Users\\U1\\Documents\\GitHub\\blinder\\src\\main\\resources\\mp3files\\Tout_recommencer.mp3");
        Song fr = new Song("Freeze Corleone","Freeze raël", "C:\\Users\\U1\\Documents\\GitHub\\blinder\\src\\main\\resources\\mp3files\\Freeze_Rael.mp3");
        Song hs = new Song("Hamza","HS", "C:\\Users\\U1\\Documents\\GitHub\\blinder\\src\\main\\resources\\mp3files\\HS.mp3");

        Playlist rap = new Playlist("Rap");
        rap.addToPlaylist(vvs);
        rap.addToPlaylist(tr);
        rap.addToPlaylist(fr);
        rap.addToPlaylist(hs);

        PlaylistService.setCurrentPlaylist(rap);


        launch();
    }
}