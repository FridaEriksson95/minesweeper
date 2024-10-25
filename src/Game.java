import jdk.jshell.EvalException;

import java.util.Scanner;

public class Game {
    private Board board;
    private final Scanner scanner;

    private final Menu menu;

    private Player playerOne;
    private Player playerTwo;
    private Player currentPlayer;



    public Game() {
        this.scanner = new Scanner(System.in);
        this.menu = new Menu();

    }

    public void startGame() {
        int size = 0;
        int bombs = 0;

        while (size < 1 || size > 20) {
            System.out.println("Choose boardsize (1-20): ");
            size = scanner.nextInt();
            if (size < 1 || size > 20) {
                System.out.println("Invalid input. Choose a size between 1-20.");
            }
        }
        while (bombs < 1 || bombs >= (size * size)) {
            System.out.println("Choose amount of mines (1-" + (size * size -1) + "): ");
            bombs = scanner.nextInt();
            if (bombs < 1 || bombs >= (size * size)) {
                System.out.println("Invalid input. Choose a  number between 1-" + (size * size - 1) + ".");
            }
        }

        board = new Board(size, bombs);
    }

    public void playGame() {
        menu.menu(this);
        while (!board.checkWin() && playerMove(0)) {
            board.printBoard();

        menu.menu(this);
        while (!board.checkWin() && playerMove(0)) {
            board.printBoard(false);
        }
        if (board.checkWin()) {
            System.out.println("Congratulations, you won!");
        } else {
            for (int i = 0; i < board.size; i++) {
                for (int j = 0; j < board.size; j++) {
                    board.getMinesweeper()[i][j].setOpen(true);
                }
            }
            board.printBoard(false);
            System.out.println("You hit a bomb, game over!");
        }
        System.out.println("Do you wish to play again? y/n");
        String input = scanner.next().toLowerCase();

        while (!input.equals("y") && !input.equals("n")) {
            System.out.println("Invalid input. Enter 'y' to play again or 'n' to exit");
            input = scanner.next().toLowerCase();
        }
        if (input.equals("y")) {
            playGame();
        } else {
            System.out.println("Thank you for playing!");
            System.exit(0);
        }
    }


    public void resetGame() {


    }

    /**
     * Asks the user to enter an x- and y-position.
     *
     * @return Returns true when move is made and false if a bomb is hit.
     */
    public boolean playerMove(int playerNumber) {
        Cell position;
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

                if (withinBoundsOfGrid(x, y)) {

                if (board.withinBoundsOfGrid(x, y)) {

                    position = board.getMinesweeper()[y][x];
                    break;

                } else {
                    System.out.println("The position you entered is not on the board.");
                }
            }

            /**
             * Asks the user if they want to place a flag on the selected cell.
             * If the cell is not open and not already flagged, a flag is placed on that cell.
             * If a flag is successfully placed, a message is printed showing the coordinates.
             * If a flag cannot be placed or if the cell is open or already contains a flag, an error message is shown.
             * If the input is invalid an error message will also be shown, asking the user to make a valid input.
             *
             * @param x The x-coordinate (column) of the selected cell.
             * @param y The y-coordinate (row) of the selected cell.
             */

            System.out.println("Do you want to place a flag on this cell? yes/no:");
            String input = scanner.next();
            if (input.equalsIgnoreCase("yes")) {
                if (!position.isOpen()) {
                    if (!position.isFlagged()) {
                        position.setFlagged(true);
                        System.out.println("Flag placed on Column: " + (x + 1) + " Row: " + (y + 1) + ".");
                    } else {
                        System.out.println("This cell already contains a flag. Try again.");
                        board.printBoard();
                        continue;
                    }
                    System.out.println("That cell is already open, try again.");
                    board.printBoard();
                    continue;
                }
            } else if (input.equalsIgnoreCase("no")) {
                System.out.println("No flag placed on Column: " + (x + 1) + " Row: " + (y + 1) + ".");
                board.printBoard();
            } else {
                System.out.println("Invalid input. Please enter yes or no.");
                continue;
            }


            if (position.isOpen()) {
                System.out.println("That cell is already open, try again.");

            } else {
                position.setLastOpenedBy(playerNumber);

            }
            else {

                board.getMinesweeper()[y][x].setOpen(true);
                if (position.isBomb()) {


                    return false;
                } else {

                    System.out.println("You opened Row: " + (y+1) + " Column: " + (x+1) + ".");

            
                    if (position.getNumber() == 0) {
                            board.openCellNearBy(y, x);
                    }


                    return true;
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
        return (x >= 0 && x < board.size) && (y >= 0 && y < board.size);
    }
    }



    /**
     * Method to Create player one and two.
     */
    public void twoPlayerInit() {
        System.out.println("Player: 1 enter name");
        String playerOneName = getNextString();
        playerOne = new Player(playerOneName, 1);
        System.out.println("Player: 2 enter name ");
        String playerTwoName = getNextString();
        playerTwo = new Player(playerTwoName, 2);

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
        int size = 0;
        Scanner scanner = new Scanner(System.in);
        while (size < 1 || size > 20) {
            System.out.println("Choose boardsize (1-20): ");
            size = scanner.nextInt();
            if (size < 1 || size > 20) {
                System.out.println("Invalid input. Choose a size between 1-20.");
            }
        }
        System.out.println("Choose amount of mines: ");
        int bombs = scanner.nextInt();
        board = new Board(size, bombs);
        currentPlayer = playerOne;
        playGameTp();
    }

    public void playGameTp() {
        while (!board.checkWin()) {
            board.printBoard(true);
            System.out.println(currentPlayer.getName() + "'s turn!");

            if (!playerMove(currentPlayer == playerOne ? 1 : 2)) {
                for (int i = 0; i < board.size; i++) {
                    for (int j = 0; j < board.size; j++) {
                        board.getMinesweeper()[i][j].setOpen(true);
                    }
                }
                board.printBoard(true);
                System.out.println(currentPlayer.getName() + " hit a bomb! ");
                currentPlayer.setLoseCount(currentPlayer.getLoseCount() + 1);
                printStats();
                break;
            }

            if (board.checkWin()) {
                System.out.println("Congratulations " + currentPlayer.getName() + " Won!");
                currentPlayer.setWinCount(currentPlayer.getWinCount() + 1);
                printStats();
                break;


            }
            currentPlayer.points++;


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
}


