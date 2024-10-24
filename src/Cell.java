public class Cell {
    private boolean isOpen;
    private boolean isBomb;
    private int number;

//    Cell constructor.
    public Cell() {
        this.isOpen = false;
        this.isBomb = false;
        this.number = 0;
    }

//    Getter and Setters
    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
