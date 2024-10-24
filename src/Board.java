import java.util.Random;

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
        generateBoard();
        generateBombs();
        printBoard();
    }

    public void printBoard() {
        System.out.print("    ");
        for (int i = 1; i <= minesweeper.length; i++) {
            System.out.print(String.format("%-2d ", i));
        }
        for (int i = 0; i < minesweeper.length; i++) {
            System.out.println();
            if (i < 10 ) {
                System.out.print(String.format("%-2d ", i + 1));
            } else {
                System.out.print(i + 1 + "");
            }
            for (int j = 0 ; j < minesweeper.length; j++) {
                /**
                 * Displays the symbols on the board based on the state of each cell:
                 * - "*" for bombs in open cells.
                 * - "O" for open cells that are not bombs.
                 * - "F" for flagged cells.
                 * - "_" for hidden/unopened cells.
                 *
                 * Each cell is checked in sequence.
                 * @param minesweeper 2D array representing the Minesweeper board with cells.
                 */
                if (minesweeper[i][j].isOpen()) {
                    if (minesweeper[i][j].isBomb()) {
                        System.out.print(" * ");
                    } else {
                        System.out.print(" O ");
                    }
                } else if (minesweeper[i][j].isFlagged()) {
                    System.out.print(" F ");
                } else {
                    System.out.print(" _ ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Randomly places bombs on the Minesweeper board.
     *
     * - A random row and column are selected for each bomb placement.
     * - The method ensures that the same cell is not selected twice by checking if the cell already contains a bomb.
     * - The process continues until the specified number of bombs in amountBombs chosen by user is placed.
     *
     * param "amountBombs" Number of bombs to be placed on the board.
     * param "size" Size of the board, both width and height.
     */
    public void generateBombs() {
        Random random = new Random();
        int bombsPlaced = 0;

        // Loop until the required number of bombs is placed
        while (bombsPlaced < amountBombs) {
            int row = random.nextInt(size);
            int col = random.nextInt(size);

            // Check if the selected cell is not already occupied by a bomb
            if (!minesweeper[row][col].isBomb()) {
                minesweeper[row][col].setBomb(true);
                bombsPlaced++;
            }
        }
    }



//    public void isOccupied() {
//    }

    



   

    public boolean checkWin() {

//        Goes through cells
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
//                Reference
                Cell cell = minesweeper[i][j];

//                Checks if cell is NOT bomb and not open/tapped
                if (!cell.isBomb() && !cell.isOpen()) {
                   return false;
                }
            }
        }

        return true;
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
