package Game;

public class Player {
    private int winCount;
    private int loseCount;
    private int points;
    private String name;
    private int marker;
    private String color;

    public Player(String name, int marker, String color) {
        this.name = name;
        this.marker = marker;
        this.color = color;
    }

    public void resetScore() {
        this.points = 0;
        this.loseCount = 0;
        this.winCount = 0;
    }

    public int getWinCount() {
        return winCount;
    }

    public void setWinCount(int winCount) {
        this.winCount = winCount;
    }

    public int getLoseCount() {
        return loseCount;
    }

    public void setLoseCount(int loseCount) {
        this.loseCount = loseCount;
    }

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setName(String name) {
        this.name = name;
    }
}