package Game;

import Board.Board;
import Board.Cell;
import Utilities.InputHandler;

import java.util.Scanner;

public class Game {
    private final Board board;
//    private final Scanner scanner;
    private final Player playerOne, playerTwo;
    private Player currentPlayer;

    public Game() {
        this.board = new Board();
//        this.scanner = new Scanner(System.in);
        this.playerOne = new Player("Player 1");
        this.playerTwo = new Player("Player 2");
    }

    public void singlePlayer() {
        setupBoard();
        playGame();
    }

    private void setupBoard() {

        System.out.println("Choose a board size (3-20): ");
        int size = InputHandler.getNewIntInRange(3,20,"board size");

        System.out.println("Choose amount of mines (1-" + (size * size - 1) + "): ");
        int mines = InputHandler.getNewIntInRange(1, size * size -1, "mine amount");
        board.setBoard(size, mines);
    }

    public void playGame() {
        do {
            board.printBoard(false);
        } while (!board.checkWin() && playerMove(0));
        if (board.checkWin()) {
            System.out.println("Congratulations, you won!");
        } else {
            for (int i = 0; i < board.getSize(); i++) {
                for (int j = 0; j < board.getSize(); j++) {
                    board.getMinesweeper()[i][j].setOpen(true);
                }
            }
            board.printBoard(false);
            System.out.println("You hit a bomb, game over!");
        }
        resetGame(false);
    }

    /**
     * Asks the user to enter an x- and y-position.
     *
     * @return Returns true when move is made and false if a bomb is hit.
     */

    public boolean playerMove(int playerNumber) {
        Cell cell;
        while (true) {
            int x;
            int y;

            System.out.println("Enter a row:");
            y = InputHandler.getNewIntInRange(1, board.getSize(), "row") - 1;

            System.out.println("Enter a column:");
            x = InputHandler.getNewIntInRange(1, board.getSize(), "column") - 1;

            cell = board.getMinesweeper()[y][x];
            cell.setLastOpenedBy(playerNumber);

            boolean placeFlag = InputHandler.getYesOrNo("Do you want to place a flag on this cell? ");
            if (placeFlag) {
                if (!cell.isOpen()) {
                    if (!cell.isFlagged()) {
                        cell.setFlagged(true);
                        System.out.println("Flag placed on Column: " + (x + 1) + " Row: " + (y + 1) + ".");
                        board.printBoard(false);
                    } else {
                        System.out.println("This cell already contains a flag. Try again.");
                        board.printBoard(false);
                    }
                } else {
                    System.out.println("Cannot place a flag on an open cell. Try again.");
                }
            } else {
                System.out.println("No flag placed on Row: " + (y + 1) + " Column: " + (x + 1) + ".");
                if (cell.isOpen()) {
                    System.out.println("That cell is already open, try again.");
                } else {
                    cell.setOpen(true);
                    if (cell.isBomb()) {
                        return false;
                    } else {
                        System.out.println("You opened Row: " + (y + 1) + " Column: " + (x + 1) + ".");
                        if (cell.getNumber() == 0) {
                            board.openCellNearBy(x, y);
                        }
                        return true;
                    }
                }
                board.printBoard(false);
            }
        }
    }

    /**
     * Method to Create player one and two.
     */
    public void twoPlayerInit() {
        playerOne.resetScore();
        playerTwo.resetScore();
        System.out.println("Player: 1 enter name");
        String playerOneName = InputHandler.getNewString();
        playerOne.setName(playerOneName);
        System.out.println("Player: 2 enter name ");
        String playerTwoName = InputHandler.getNewString();
        playerTwo.setName(playerTwoName);
        System.out.println(playerOneName + " and " + playerTwoName + ", let's go!");
        startGameTp();
    }


    /**
     * Method to start the twoPlayerMode
     */
    public void startGameTp() {
        setupBoard();
        currentPlayer = playerOne;
        playGameTp();
    }

    public void playGameTp() {
        while (!board.checkWin()) {
            board.printBoard(true);
            System.out.println(currentPlayer.getName() + "'s turn!");

            if (!playerMove(currentPlayer == playerOne ? 1 : 2)) {
                for (int i = 0; i < board.getSize(); i++) {
                    for (int j = 0; j < board.getSize(); j++) {
                        board.getMinesweeper()[i][j].setOpen(true);
                    }
                }
                board.printBoard(true);
                System.out.println(currentPlayer.getName() + " hit a bomb! ");
                currentPlayer.setLoseCount(currentPlayer.getLoseCount() + 1);
                printStats();
                resetGame(true);
                break;
            }

            if (board.checkWin()) {
                System.out.println("Congratulations " + currentPlayer.getName() + " Won!");
                currentPlayer.setWinCount(currentPlayer.getWinCount() + 1);
                printStats();
                resetGame(true);
                break;
            }
            currentPlayer.setPoints(currentPlayer.getPoints()+1);
            currentPlayer = (currentPlayer == playerOne) ? playerTwo : playerOne;
        }
    }

    public void printStats() {
        System.out.println("\n========= Player Statistics =========");
        System.out.printf("%-15s | %-6s | %-7s | %-6s %n", "Player", "Wins", "Losses", "Points");
        System.out.println("-------------------------------------");

        System.out.printf("%-15s | %-6d | %-7d | %-6d %n",
                playerOne.getName(),
                playerOne.getWinCount(),
                playerOne.getLoseCount(),
                playerOne.getPoints());

        System.out.printf("%-15s | %-6d | %-7d | %-6d %n",
                playerTwo.getName(),
                playerTwo.getWinCount(),
                playerTwo.getLoseCount(),
                playerTwo.getPoints());

        System.out.println("=====================================\n");
    }

    public void resetGame(boolean isTwoPlayer) {

    boolean playAgain = InputHandler.getYesOrNo("Do you want to play again?");

    if (playAgain && !isTwoPlayer) {
        singlePlayer();
    } else if (playAgain) {
        twoPlayerInit();
    } else {
        System.out.println("Thanks for playing!");
        System.exit(0);
    }

    }
}