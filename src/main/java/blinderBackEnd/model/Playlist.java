package blinderBackEnd.model;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    List<Song> playlist = new ArrayList<>();


    public void addToPlaylist(Song song){
        playlist.add(song);
    }

    public List<Song> getPlaylist(Song song){
        return playlist;
    }

}
