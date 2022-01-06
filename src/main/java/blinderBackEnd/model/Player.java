package blinderBackEnd.model;

public class Player {
    private String username;
    private int score;

    public Player(String username){
        this.username=username;
        this.score=0;
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
