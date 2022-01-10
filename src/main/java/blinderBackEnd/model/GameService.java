package blinderBackEnd.model;

import java.util.ArrayList;
import java.util.List;

public class GameService {
    private static int round_cpt=0;

    private static int score_cpt=0;

    private static Game currentGame;

    private static ArrayList<Game> games = new ArrayList<>();

    public static void addGameToList(Game game){
        games.add(game);
    }

    public static ArrayList<Game> getGamesList(){
        return games;
    }

    public static Game getCurrentGame(){
        return currentGame;
    }

    public static void setCurrentGame(Game game){
        currentGame = game;
    }

    public static int getRound_cpt(){return round_cpt;}

    public static int getScore_cpt(){return score_cpt;}

    public static void setRound_cpt(int r){round_cpt=r;}

    public static void setScore_cpt(int s){score_cpt=s;}
}
