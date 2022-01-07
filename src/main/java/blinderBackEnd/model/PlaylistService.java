package blinderBackEnd.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class PlaylistService {
    private static Playlist currentPlaylist;
    private static Song currentSong;
    private static ArrayList<Integer> randomValues = new ArrayList<>();

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

    public static ArrayList<String> getRandomAuthorFromPlaylist(Playlist playlist){
        ArrayList<String> randomAuthors = new ArrayList<>();


        int playlistSize = PlaylistService.getCurrentPlaylist().getPlaylist().size();
        for (int i = 0; i < playlistSize; i++) {
            randomValues.add(i);
        }
        Collections.shuffle(randomValues);
        for(int i = 0; i < 4; i++){
            randomAuthors.add(playlist.getPlaylist().get(i).getAuteur());
            System.out.println(randomAuthors.get(i));
        }
        return randomAuthors;
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

    public static Song getCurrentSong(){
        return currentSong;
    }

    public static boolean checkAuthor(String author){
        if(author.equals(currentSong.getAuteur())){
            return true;
        } else {
            return false;
        }
    }


}
