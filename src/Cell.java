public class Cell {
    private boolean isOpen;
    private boolean isBomb;
    private int lastOpenedBy;

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

    public int getLastOpenedBy() {
        return lastOpenedBy;
    }

    public void setLastOpenedBy(int lastOpenedBy) {
        this.lastOpenedBy = lastOpenedBy;
    }
}
