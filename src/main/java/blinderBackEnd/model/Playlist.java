package blinderBackEnd.model;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    String titre;
    List<Song> playlist = new ArrayList<>();


    public Playlist(String titre, List<Song> playlist){
        this.titre = titre;
        this.playlist = playlist;
    }

    public Playlist(String titre){
        this.titre = titre;
    }
    public String getTitre() {
        return titre;
    }
    public void addToPlaylist(Song song){
        playlist.add(song);
    }
    public void removetoPlaylist(Song song){
        playlist.remove(song);
    }
    public List<Song> getPlaylist(){
        return playlist;
    }

    public String toString(){
        String res="";
        for (Song s: playlist) {
            res+=s.toString()+"\n";
        }
        return res;
    }

}
