public class Cell {
    private boolean isOpen;
    private boolean isBomb;
    private boolean isFlagged;


    //    Cell constructor.
    public Cell() {
        isOpen = false;
        isBomb = false;
        isFlagged = false;
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

    /**
     * Represents the flagged status of the cell.
     *
     * - `isFlagged`: Indicates whether the player has flagged the cell
     * as potentially containing a bomb.
     *
     * Getters and setters:
     * - `isFlagged()`: Returns the current flagged status.
     * - `setFlagged()`: Sets the flagged status of the cell.
     */
    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }
}
