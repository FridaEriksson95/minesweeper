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
        generateBoard();
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
                if (minesweeper[i - 1][j].isOpen()) {
                    System.out.print("0 ");
                }
                else {
                    System.out.print("_ ");
                }
            }
        }
    }

//    public void isOccupied() {
//    }

    public void generateBombs() {}



   
    public void checkWin() {

        boolean hasWon = true;

//        Goes through cells
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
//                Reference
                Cell cell = minesweeper[i][j];

//                Checks if cell is NOT bomb and not open/tapped
                if (!cell.isBomb() && !cell.isOpen()) {
                    hasWon = false;
                    break;
                }
            }
        }

        if (hasWon) {
            System.out.println("Congratulations! You won!");
        } else {
            System.out.println("You lost!");
        }

    }

    public void generateBoard () {
        for (int i = 0 ; i < minesweeper.length; i ++) {
            for (int j = 0 ; j < minesweeper.length; j++){
                minesweeper[i][j] = new Cell();
            }
        }
    }

    public Cell[][] getMinesweeper() {
        return minesweeper;
    }
}
