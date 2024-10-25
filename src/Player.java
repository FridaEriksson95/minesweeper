public class Player {
    int winCount;
    int loseCount;
    int points;
    String name;



    public Player(String name, int marker) {
        this.name = name;

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
}