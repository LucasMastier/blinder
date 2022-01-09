package blinderBackEnd.model;

import java.io.Serializable;

public class Player implements Serializable {
    private String username;
    private int score;
    private Game game;

    public Player(String username){
        this.username=username;
        this.score=0;
    }

    public Player(String username, Game game){
        this.username = username;
        this.game = game;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public String getUsername() {
        return username;
    }
    public int getScore() {
        return score;
    }
    public String toString(){
        return getUsername()+": "+getScore();
    }
}
