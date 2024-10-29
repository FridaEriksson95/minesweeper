package Game;

import Board.Board;
import Board.Cell;

import java.util.Scanner;

public class Game {
    private final Board board;
    private final Scanner scanner;
    private final Player playerOne, playerTwo;
    private Player currentPlayer;

    public Game() {
        this.board = new Board();
        this.scanner = new Scanner(System.in);
        this.playerOne = new Player("Player 1");
        this.playerTwo = new Player("Player 2");
    }

    public void singlePlayer() {
        setupBoard();
        playGame();
    }

    private void setupBoard() {
        int size = 0;
        int bombs = 0;
        while (size < 3 || size > 20) {
            System.out.println("Choose board size (3-20): ");
            size = scanner.nextInt();
            if (size < 3 || size > 20) {
                System.out.println("Invalid input. Choose a size between 3-20.");
            }
        }
        while (bombs < 1 || bombs >= (size * size)) {
            System.out.println("Choose amount of mines (1-" + (size * size - 1) + "): ");
            bombs = scanner.nextInt();
            if (bombs < 1 || bombs >= (size * size)) {
                System.out.println("Invalid input. Choose a  number between 1-" + (size * size - 1) + ".");
            }
        }
        board.setBoard(size, bombs);
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
            while (true) {
                while (true) {
                    System.out.println("Enter a row:");
                    if (scanner.hasNextInt()) {
                        y = scanner.nextInt();
                        break;
                    } else {
                        System.out.println("You need to enter a number, try again.");
                        scanner.next();
                    }
                }
                while (true) {
                    System.out.println("Enter a column:");
                    if (scanner.hasNextInt()) {
                        x = scanner.nextInt();
                        break;
                    } else {
                        System.out.println("You need to enter a number, try again.");
                        scanner.next();
                    }
                }
                x--;
                y--;
                if (board.withinBoundsOfGrid(x, y)) {
                    cell = board.getMinesweeper()[y][x];
                    cell.setLastOpenedBy(playerNumber);
                    break;
                } else {
                    System.out.println("The position you entered is not on the board.");
                }
            }
            //TODO Ska endas köras på singelplayer.

            System.out.println("Do you want to place a flag on this cell? yes/no:");
            String input = scanner.next();
            if (input.equalsIgnoreCase("yes")) {
                if (!cell.isOpen()) {
                    if (!cell.isFlagged()) {
                        cell.setFlagged(true);
                        System.out.println("Flag placed on Column: " + (x + 1) + " Row: " + (y + 1) + ".");
                        board.printBoard(false);
                    } else {
                        System.out.println("This cell already contains a flag. Try again.");
                        board.printBoard(false);
                    }
                }
            } else if (input.equalsIgnoreCase("no")) {
                System.out.println("No flag placed on Row: " + (y + 1) + " Column: " + (x + 1) + ".");
                if (cell.isOpen()) {
                    System.out.println("That cell is already open, try again.");
                } else {
                    if (cell.getNumber() == 0) {
                        board.openCellNearBy(x, y);
                    }
                    board.getMinesweeper()[y][x].setOpen(true);
                    if (cell.isBomb()) {
                        return false;
                    } else {
                        System.out.println("You opened Row: " + (y + 1) + " Column: " + (x + 1) + ".");
                        return true;
                    }
                }
                board.printBoard(false);
            } else {
                System.out.println("Invalid input. Please enter yes or no.");
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
        String playerOneName = getNextString();
        playerOne.setName(playerOneName);
        System.out.println("Player: 2 enter name ");
        String playerTwoName = getNextString();
        playerTwo.setName(playerTwoName);
        startGameTp();
    }

    /**
     * Method to handle input
     *
     * @return a String
     */
    public String getNextString() {
        while (true) {
            boolean isValid = true;
            if (scanner.hasNext()) {
                String string = scanner.next();
                for (int i = 0; i < string.length(); i++) {
                    if (!Character.isLetter(string.charAt(i))) {
                        isValid = false;
                        System.out.println("Not a valid name, please use only letters.");
                        break;
                    }
                }
                if (isValid) {
                    return string;
                }
            }
        }
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
        System.out.println("Do you wish to play again? y/n");
        String input = scanner.next().toLowerCase();

        while (!input.equals("y") && !input.equals("n")) {
            System.out.println("Invalid input. Enter 'y' to play again or 'n' to exit");
            input = scanner.next().toLowerCase();
        }
        if (input.equals("y") && !isTwoPlayer) {
            singlePlayer();

        } else if (input.equalsIgnoreCase("y")) {
            twoPlayerInit();
        } else {
            System.out.println("Thank you for playing!");
            System.exit(0);
        }

    }
}