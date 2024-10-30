package Game;

/**
 * Player class
 * Logic/functionality
 */

public class Player {
    private int winCount;
    private int score;
    private String name;
    private String color;


    public Player(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public void resetPlayerStatistics(){
        this.score = 0;
        this.winCount = 0;
    }

    public int getWinCount() {
        return winCount;
    }

    public void setWinCount(int winCount) {
        this.winCount = winCount;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}