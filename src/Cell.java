public class Cell {
    private boolean isOpen;
    private boolean isBomb;

//    Cell constructor.
    public Cell() {
        isOpen = false;
        isBomb = false;
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
}
