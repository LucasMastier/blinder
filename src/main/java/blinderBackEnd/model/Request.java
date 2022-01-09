package blinderBackEnd.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Request implements Serializable {
    private String message;
    private Game game;
    private Player player;
    private Playlist playlist;

    private ArrayList<Game> games;
    private ArrayList<Player> players;
    private ArrayList<Playlist> playlists;

    public Request(String message, Player player, Game game){
        this.message = message;
        this.player = player;
        this.game = game;
    }

    public Request(String message){
        this.message = message;
    }

    public Request(String message, Game game){
        this.message = message;
        this.game = game;
    }

    public Request(String message, Player player){
        this.message = message;
        this.player = player;
    }

    public Request(String message, Playlist playlist){
        this.message = message;
        this.playlist = playlist;
    }


    public String getMessage() {
        return message;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }
}
