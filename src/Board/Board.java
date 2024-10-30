package Board;

import Utilities.Colors;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class Board
 * Board logic/functionality
 */

public class Board {
    private int size, amountBombs;
    private Cell[][] minesweeper;

    public Board() {
        this.size = 10;
        this.amountBombs = 10;
        generateBoard();
    }

    /**
     * Helper class to store a pair of offset ints, to be used to get a coordinate relative to origin coordinate.
     *
     * @param xOffset
     * @param yOffset
     */
    private record OffsetCoordinate(int xOffset, int yOffset) {
        /**
         * @return Returns a list of OffsetCoordinate for all adjacent coordinates.
         */
        private static ArrayList<OffsetCoordinate> getSurroundingCellsOffsets() {
            ArrayList<OffsetCoordinate> offsets = new ArrayList<>();
            offsets.add(new OffsetCoordinate(1, 0)); //Right
            offsets.add(new OffsetCoordinate(1, -1)); //Bot-right
            offsets.add(new OffsetCoordinate(0, -1)); //Bot
            offsets.add(new OffsetCoordinate(-1, -1)); //Bot-left
            offsets.add(new OffsetCoordinate(-1, 0)); //Left
            offsets.add(new OffsetCoordinate(-1, 1)); //Top-left
            offsets.add(new OffsetCoordinate(0, 1)); //Top
            offsets.add(new OffsetCoordinate(1, 1)); //Top-right
            return offsets;
        }
    }

    /**
     * Method to print board
     */

    public void printBoard(boolean isTwoPlayer) {
        System.out.print("    ");
        for (int i = 1; i <= minesweeper.length; i++) {
            System.out.printf("%-2d ", i);
        }
        for (int i = 0; i < minesweeper.length; i++) {
            System.out.println();
            if (i < 10) {
                System.out.printf("%-2d ", i + 1);
            } else {
                System.out.print(i + 1 + "");
            }
            for (int j = 0; j < minesweeper.length; j++) {
                Cell cell = minesweeper[i][j];
                if (cell.isOpen()) {
                    if (cell.isBomb()) {
                        System.out.print(Colors.ANSI_YELLOW + " * " + Colors.ANSI_RESET);
                    } else if (cell.getNumber() == 0) {
                        if (isTwoPlayer) {
                            if (cell.getLastOpenedBy() == 1) {
                                System.out.print(Colors.ANSI_BLUE + " 0 " + Colors.ANSI_RESET);
                            } else {
                                System.out.print(Colors.ANSI_PURPLE + " 0 " + Colors.ANSI_RESET);
                            }
                        } else {
                            System.out.print(Colors.ANSI_GREEN + " O " + Colors.ANSI_RESET);
                        }
                    } else {
                        System.out.print(" " + cell.getNumber() + " ");

                    }
                } else {
                    if (cell.isFlagged()) {
                        System.out.print(Colors.ANSI_RED + " F " + Colors.ANSI_RESET);
                    } else {
                        System.out.print(" _ ");
                    }
                }
            }
            System.out.println();
        }
    }

    /**
     * Method to generate board
     * Calls 2 other methods.
     */

    private void generateBoard() {
        this.minesweeper = new Cell[size][size];
        for (int i = 0; i < minesweeper.length; i++) {
            for (int j = 0; j < minesweeper.length; j++) {
                minesweeper[i][j] = new Cell();
            }
        }
        generateBombs();
        generateNumbers();
    }

    /**
     * Randomly places bombs on the Minesweeper board.
     * - A random row and column are selected for each bomb placement.
     * - The method ensures that the same cell is not selected twice by checking if the cell already contains a bomb.
     * - The process continues until the specified number of bombs in amountBombs chosen by user is placed.
     * param "amountBombs" Number of bombs to be placed on the board.
     * param "size" Size of the board, both width and height.
     */
    private void generateBombs() {
        Random random = new Random();
        int bombsPlaced = 0;
        while (bombsPlaced < amountBombs) {
            int row = random.nextInt(size);
            int col = random.nextInt(size);
            if (!minesweeper[row][col].isBomb()) {
                minesweeper[row][col].setBomb(true);
                bombsPlaced++;
            }
        }
    }

    /**
     * Loops through all Cells on the board and counts the number of adjacent mines of each Cell. Stores the number in each Cell.
     */
    private void generateNumbers() {
        ArrayList<OffsetCoordinate> surroundingOffsets = OffsetCoordinate.getSurroundingCellsOffsets();
        for (int column = 0; column < size; column++) {
            for (int row = 0; row < size; row++) {
                Cell cell = minesweeper[row][column];
                for (OffsetCoordinate offset : surroundingOffsets) {
                    int surroundingRow = row + offset.xOffset;
                    int surroundingColumn = column + offset.yOffset;
                    if (withinBoundsOfGrid(surroundingRow, surroundingColumn) && (minesweeper[surroundingRow][surroundingColumn].isBomb())) {
                        cell.setNumber(cell.getNumber() + 1);
                    }
                }
            }
        }
    }

    /**
     * Method to check if there is a win or not
     * checks if all board cells are open
     * @return a boolean (true/false)
     */

    public boolean checkWin() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Cell cell = minesweeper[i][j];
                if (!cell.isBomb() && !cell.isOpen()) {
                    return false;
                }
            }
        }
        return true;
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

    /**
     * Opens up all adjacent Cells that are not bombs at a given coordinate on the board. Runs itself again on adjacent Cell
     * if the Cell's number is 0. To be used on each move.
     * @param x origin x-coordinate
     * @param y origin y-coordinate
     */
    public void openCellNearBy(int x, int y) {
        ArrayList<OffsetCoordinate> surroundingOffsets = OffsetCoordinate.getSurroundingCellsOffsets();
        if (withinBoundsOfGrid(x, y)) {
            Cell cell = minesweeper[x][y];
            if (!cell.isBomb() && !cell.isOpen()) {
                cell.setOpen(true);
            }
            if (cell.getNumber() == 0) {
                for (OffsetCoordinate offset : surroundingOffsets) {
                    int surroundingRow = x + offset.xOffset();
                    int surroundingColumn = y + offset.yOffset();
                    if (withinBoundsOfGrid(surroundingRow, surroundingColumn) && !minesweeper[surroundingRow][surroundingColumn].isOpen()) {
                        openCellNearBy(surroundingRow, surroundingColumn);
                    }
                }
            }
        }
    }

    public Cell[][] getMinesweeper() {
        return minesweeper;
    }

    public int getSize() {
        return size;
    }

    public void setBoard(int size, int amountBombs) {
        this.size = size;
        this.amountBombs = amountBombs;
        generateBoard();
    }
}