public class Board {

    //spelbrädan
//    Nicholas


    int size;
    int amountBombs;
    /**
     * Ta bort 10/10
     */
 private   Cell[][] minesweeper;

    //    Constructor
    public Board(int size, int amountBombs) {
        this.size = size;
        this.amountBombs = amountBombs;
//  Create board
        this.minesweeper = new Cell[size][size];
        generateBombs();
        printBoard();
    }

    public void printBoard() {
        System.out.print("   ");
        for (int i = 1; i <= minesweeper.length; i++) {
            System.out.print(i + " ");
        }
        for (int i = 1; i <= minesweeper.length; i++) {
            System.out.println();
            if (i < 10) {
                System.out.print(i + "  ");
            } else {
                System.out.print(i + " ");
            }
            for (int j = 0; j < minesweeper.length; j++) {
                System.out.print("_" + " ");
            }
        }
    }

//    public void isOccupied() {
//    }

    public void generateBombs() {


    }

    public boolean checkWin() {
        for (Cell[] cellArray : minesweeper) {
            for (Cell cell : cellArray) {
                if (!cell.isOpen() && !cell.isBomb()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void generateBoard() {
        for (int i = 0; i < minesweeper.length; i++) {
            for (int j = 0; j < minesweeper.length; j++) {
                minesweeper[i][j] = new Cell();
            }
        }
    }

    public Cell[][] getMinesweeper() {
        return minesweeper;
    }
}
