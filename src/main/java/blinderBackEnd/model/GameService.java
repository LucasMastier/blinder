package blinderBackEnd.model;

import java.util.ArrayList;
import java.util.List;

public class GameService {
    private static ArrayList<Game> games = new ArrayList<>();

    public static void addGameToList(Game game){
        games.add(game);
    }

    public static ArrayList<Game> getGamesList(){
        return games;
    }



}
