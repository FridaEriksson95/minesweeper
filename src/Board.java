import java.util.Random;

import static java.awt.SystemColor.text;

public class Board {

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

    public class textColors {
        public static final String ANSI_RESET = "\u001B[0m";
        public static final String ANSI_RED = "\u001B[31m";
        public static final String ANSI_GREEN = "\u001B[32m";
        public static final String ANSI_BLUE = "\u001B[34m";
        public static final String ANSI_YELLOW = "\u001B[33m";

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
                // Show * for bombs, O for open cell and _ for hidden
                if (minesweeper[i][j].isOpen()) {
                    if (minesweeper[i][j].isBomb()) {
                        System.out.print(textColors.ANSI_RED + " * " + textColors.ANSI_RESET);
                    } else {
                        System.out.print(textColors.ANSI_GREEN + " O " + textColors.ANSI_RESET);
                    }
                } else {
                    System.out.print(" _ "); //
                }
            }
            System.out.println();
        }
    }


    public void generateBombs() {
        Random random = new Random();
        int bombsPlaced = 0;

        // Loop until the required number of bombs is placed
        while (bombsPlaced < amountBombs) {
            int row = random.nextInt(size);
            int col = random.nextInt(size);

            // Check if the selected cell is not already occupied by a bomb
            if (!minesweeper[row][col].isBomb()) {
                minesweeper[row][col].setBomb(true); // Sätt bomben med settern
                bombsPlaced++;
            }
        }
    }

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
