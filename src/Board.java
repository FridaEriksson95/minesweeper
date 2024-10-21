public class Board {

    //spelbrädan


    int size;
    int amountBombs;
    /**
     * Ta bort 10/10
     */
    Cell[][] minesweeper = new Cell[10][10];

    public void printBoard() {
        System.out.print("   ");
        for (int i = 1; i <= minesweeper.length; i++) {
            System.out.print(i + " ");
        }
        for (int i = 1; i <= minesweeper.length; i++) {
            System.out.println();
            if (i < 10 ) {
                System.out.print(i + "  ");
            }else {
                System.out.print(i + " ");
            }
            for (int j = 0 ; j < minesweeper.length; j++) {
                System.out.print("_" + " ");
            }
        }
    }

    public void isOccupied() {
    }

    public void generateBombs() {
    }

    public void checkWin() {
    }
    public void generateBoard () {
        for (int i = 0 ; i < minesweeper.length; i ++) {
            for (int j = 0 ; j < minesweeper.length; j++){
                minesweeper[i][j] = new Cell();
            }
        }
    }
}
