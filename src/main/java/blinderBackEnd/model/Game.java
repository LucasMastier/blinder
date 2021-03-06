package blinderBackEnd.model;
import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {
    private ArrayList<Player> list_player;
    private Playlist pl;
    private String name;

    public Game(Playlist playlist, String name){
        list_player= new ArrayList<Player>();
        pl = playlist;
        this.name = name;
    }

    public ArrayList<Player> getPlayersList(){
        return list_player;
    }

    public void addPlayer(Player j){
        list_player.add(j);
    }

    public void removePlayer(Player j){
        list_player.remove(j);
    }
    public String toString(){
        String res="tableau des scores: \n";
        for (Player p: list_player) {
            res += p.toString()+"\n";
        }
        return res;
    }
    public void scoreUpdate(String username){
        for (Player p: list_player) {
            if(username.equals(p.getUsername())) {
                p.setScore(p.getScore()+1);
            }
        }
    }

    public void setPlayersList(ArrayList<Player> playersList){
        list_player = playersList;
    }

    public String getName(){
        return name;
    }

}
