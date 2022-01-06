package blinderBackEnd.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class PlaylistService {

    public static void playRandomSongFromPlaylist(){
        String bip = "C:\\Users\\U1\\Documents\\GitHub\\blinder\\src\\main\\resources\\mp3files\\VVS.mp3";
        Media hit = new Media(new File(bip).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.setAutoPlay(true);
    }
}
