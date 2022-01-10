package blinderBackEnd.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PlaylistService {
    private static Playlist currentPlaylist;
    private static Song currentSong;
    private static ArrayList<Integer> randomValues = new ArrayList<>();
    public static MediaPlayer mediaPlayer;
    private static ArrayList<Playlist> playlists = new ArrayList<>();
    private static int numberOfRounds;

    public static void playRandomSongFromPlaylist(Playlist playlist){
        int i = 0;
        while(randomValues.get(i)==0){
            i++;
        }
        Song randomSong = playlist.getPlaylist().get(i);
        currentSong = randomSong;
        randomValues.set(i,0);
        playSongFromPlaylist(randomSong.getPathToFile());
    }

    public static void addToPlaylists(Playlist playlist){
        playlists.add(playlist);
    }

    public static void playSongFromPlaylist(String path){
        Media hit = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.setAutoPlay(true);
    }

    public static void initializeRandomValues(){
        randomValues.clear();

        for (int i = 0; i < numberOfRounds; i++) {
            randomValues.add(i);
            //System.out.println(i);
        }
        Collections.shuffle(randomValues);

    }

    public static ArrayList<String> getRandomAuthorFromPlaylist(Playlist playlist){
        ArrayList<String> randomAuthors = new ArrayList<>();
        ArrayList<Integer> randomIndexes = new ArrayList<>(Arrays.asList(0,1,2,3));
        Collections.shuffle(randomIndexes);

        for(int i = 0; i < 4; i++){
            randomAuthors.add(playlist.getPlaylist().get(randomIndexes.get(i)).getAuteur());

        }
        return randomAuthors;
    }

    public static void setCurrentPlaylist(Playlist playlist){
        currentPlaylist = playlist;
    }

    public static Playlist getCurrentPlaylist(){
        return currentPlaylist;
    }

    public static ArrayList<Playlist> getPlaylists(){
        return playlists;
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


    public static void setNumberOfRounds(int numberOfRounds) {
        PlaylistService.numberOfRounds = numberOfRounds;
    }
}
