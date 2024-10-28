public class Player {
    int winCount;
    int loseCount;
    int points;
    String name;
    int marker;
    String color;

    public Player(String name, int marker, String color) {
        this.name = name;
        this.marker = marker;
        this.color = color;
    }

    public String getColorMarker() {
        return color + marker + "\u001B[0m";
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