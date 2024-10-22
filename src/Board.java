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
                // Show * for bombs, O for open cell and _ for hidden
                if (minesweeper[i][j].isOpen()) {
                    if (minesweeper[i][j].isBomb()) {
                        System.out.print(" * ");
                    } else {
                        System.out.print(" O ");
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
