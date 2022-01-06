package blinderBackEnd.model;
import java.util.ArrayList;

public class Game {
    private ArrayList<Player> list_player;
    private Playlist pl;

    public Game(Playlist playlist){
        list_player= new ArrayList<Player>();
        pl= playlist;
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

}
