package blinderBackEnd.model;

import java.util.ArrayList;

public class PlayerService {
    private static ArrayList<Player> players = new ArrayList<>();

    public static void addPlayerToPlayersList(Player player){
        players.add(player);
    }

    public static ArrayList<Player> getPlayers(){
        return players;
    }
}
