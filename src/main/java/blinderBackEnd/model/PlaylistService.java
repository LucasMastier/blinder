package blinderBackEnd.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class PlaylistService {
    private static Playlist currentPlaylist;
    private static Song currentSong;

    public static void playRandomSongFromPlaylist(Playlist playlist){
        int randomNumber = (int)(Math.random() * ((playlist.getPlaylist().size())));
        Song randomSong = playlist.getPlaylist().get(randomNumber);
        currentSong = randomSong;
        playSongFromPlaylist(randomSong.getPathToFile());
    }

    public static void playSongFromPlaylist(String path){
        Media hit = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.setAutoPlay(true);
    }

    public static String getRandomAuthorFromPlaylist(Playlist playlist){
        int randomNumber = (int)(Math.random() * ((playlist.getPlaylist().size())));
        return playlist.getPlaylist().get(randomNumber).getAuteur();
    }

    public static void setCurrentPlaylist(Playlist playlist){
        currentPlaylist = playlist;
    }

    public static Playlist getCurrentPlaylist(){
        return currentPlaylist;
    }

    public static void setCurrentSong(Song song){
        currentSong = song;
    }

    public static boolean checkAuthor(String author){
        if(author.equals(currentSong.getAuteur())){
            return true;
        } else {
            return false;
        }
    }


}
