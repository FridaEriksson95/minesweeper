public class Cell {
    private boolean isOpen;
    private boolean isBomb;
    private int lastOpenedBy;
    private boolean isFlagged;
    private int number;

    public Cell() {
        this.isFlagged = false;
        this.isOpen = false;
        this.isBomb = false;
        this.number = 0;
    }

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

    /**
     * Represents the flagged status of the cell.
     * <p>
     * - `isFlagged`: Indicates whether the player has flagged the cell
     * as potentially containing a bomb.
     * <p>
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}