import java.util.ArrayList;
import java.util.Random;

public class Board {

    //spelbrädan
//    Nicholas


    int size;
    int amountBombs;
    /**
     * Ta bort 10/10
     */
    private Cell[][] minesweeper;

    //    Constructor
    public Board(int size, int amountBombs) {

        this.size = size;
        this.amountBombs = amountBombs;
//  Create board
        this.minesweeper = new Cell[size][size];
        generateBoard();
        printBoard();
    }

    public void printBoard() {
        System.out.print("    ");
        for (int i = 1; i <= minesweeper.length; i++) {
            System.out.print(String.format("%-2d ", i));
        }
        for (int i = 0; i < minesweeper.length; i++) {
            System.out.println();
            if (i < 10) {
                System.out.print(String.format("%-2d ", i + 1));
            } else {
                System.out.print(i + 1 + "");
            }
            for (int j = 0; j < minesweeper.length; j++) {
                // Show * for bombs, O for open cell and _ for hidden
                Cell cell = minesweeper[i][j];
                if (cell.isOpen()) {
                    if (cell.isBomb()) {
                        System.out.print(" * ");
                    } else if (cell.getNumber() == 0) {
                        System.out.print(" O ");
                    } else {
                        System.out.print(" " + cell.getNumber() + " ");
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

    public void generateBoard() {
        for (int i = 0; i < minesweeper.length; i++) {
            for (int j = 0; j < minesweeper.length; j++) {
                minesweeper[i][j] = new Cell();
            }
        }
        generateBombs();
        generateNumbers();
    }

    private class IntPair {
        private final int xOffset;
        private final int yOffset;

        public IntPair(int xOffset, int yOffset) {
            this.xOffset = xOffset;
            this.yOffset = yOffset;
        }

        public int getX() {
            return xOffset;
        }

        public int getY() {
            return yOffset;
        }
    }

    private ArrayList<IntPair> offSetsForSurroundingCells() {
        ArrayList<IntPair> offsets = new ArrayList<>();
        offsets.add(new IntPair(1, 0)); //Right
        offsets.add(new IntPair(1, -1)); //Bot-right
        offsets.add(new IntPair(0, -1)); //Bot
        offsets.add(new IntPair(-1, -1)); //Bot-left
        offsets.add(new IntPair(-1, 0)); //Left
        offsets.add(new IntPair(-1, 1)); //Top-left
        offsets.add(new IntPair(0, 1)); //Top
        offsets.add(new IntPair(1, 1)); //Top-right
        return offsets;
    }

    private void generateNumbers() {
        ArrayList<IntPair> surroundingOffsets = offSetsForSurroundingCells();
        for (int column = 0; column < size; column++) {
            for (int row = 0; row < size; row++) {
                Cell cell = minesweeper[row][column];
                for (IntPair offset : surroundingOffsets) {
                    int surroundingRow = row + offset.getX();
                    int surroundingColumn = column + offset.getY();
                    if (withinBoundsOfGrid(surroundingRow, surroundingColumn) && (minesweeper[surroundingRow][surroundingColumn].isBomb())) {
                        cell.setNumber(cell.getNumber() + 1);
                    }
                }
            }
        }

    }

    /**
     * Checks if a position exists on the board.
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @return Returns true if position is within bounds.
     */
    public boolean withinBoundsOfGrid(int x, int y) {
        return (x >= 0 && x < size) && (y >= 0 && y < size);
    }

    //    Methods that checks cells to open nearby
    public void openCellNearBy(int x, int y) {
        Random random = new Random();
        int limitCellsToOpen = random.nextInt(4)+1;

//        Check if coordinates are within bounds & if cell is open
        if (!withinBoundsOfGrid(x, y) || minesweeper[x][y].isOpen()) {
//           Don't do anything if cell is open/out of bounds
            return;
        }

//        Open cell
        minesweeper[x][y].setOpen(true);

        if (minesweeper[x][y].getNumber() == 0) {

        }


    }


    public Cell[][] getMinesweeper() {
        return minesweeper;
    }
}

