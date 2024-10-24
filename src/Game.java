import jdk.jshell.EvalException;

import java.util.Scanner;

public class Game {
    private Board board;
    private final Scanner scanner;
    private Player playerOne;
    private Player playerTwo;
    private Player currentPlayer;


    public Game() {
        this.scanner = new Scanner(System.in);
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Let's clear some mines in this Minesweeper game!");
        System.out.println("1. Start new game");
        //System.out.println("2. Gameinstructions.");
        System.out.println("2. Exit");
        System.out.println("3. Two-player mode");
        System.out.println("Your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                startGame();
                break;
           /* case 2:
                instructions();
                break;*/
            case 2:
                System.out.println("Thanks for playing, exiting...");
                break;
            case 3:
                twoPlayerInit();
                break;
            default:
                System.out.println("Invalid choice, try again!");
                menu();
        }
    }

    public void startGame() {
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

    }
    /*public void instructions() {
        System.out.println("Gameinstructions:");
        System.out.println("The goal of the game is to open all of the cells without hitting a mine.");
        System.out.println("Pick a row and a coloumn to place your move.");
        System.out.println("If your move hits a mine, you loose.");
        System.out.println("If you manage to open all the cells without hitting a mine, you win!\n");
        menu();
    }*/

    public void playGame() {
        menu();
        while (!board.checkWin() && playerMove()) {
            board.printBoard();
        }


        if (board.checkWin()) {
            System.out.println("Congratulations, you won!");
        } else {
            for (int i = 0; i < board.size; i++) {
                for (int j = 0; j < board.size; j++) {
                    board.getMinesweeper()[i][j].setOpen(true);

                }
            }
            board.printBoard();
            System.out.println("You hit a bomb, game over!");

        }
        System.out.println("Do you wish to play again? y/n");
        String input = scanner.next();

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
    public boolean playerMove() {
        Cell position;
        while (true) {
            int x;
            int y;
            while (true) {
                while (true) {
                    System.out.println("Enter a row:");
                    if (scanner.hasNextInt()) {
                        x = scanner.nextInt();
                        break;
                    } else {
                        System.out.println("You need to enter a number, try again.");
                    }
                }
                while (true) {
                    System.out.println("Enter a column:");
                    if (scanner.hasNextInt()) {
                        y = scanner.nextInt();
                        break;
                    } else {
                        System.out.println("You need to enter a number, try again.");
                    }
                }
                x--;
                y--;
                if (withinBoundsOfGrid(x, y)) {
                    position = board.getMinesweeper()[y][x];
                    break;

                } else {
                    System.out.println("The position you entered is not on the board.");
                }
            }
            if (position.isOpen()) {
                System.out.println("That cell is already open, try again.");
            } else {
                board.getMinesweeper()[y][x].setOpen(true);
                if (position.isBomb()) {


                    return false;
                } else {
                    System.out.println("You opened Column: " + x + " Row: " + y + ".");
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

    public void gameOver() {
    }


    /**
     * Method to Create player one and two.
     */
    public void twoPlayerInit() {
        System.out.println("Player: 1 enter name");
        String playerOneName = getNextString();
        playerOne = new Player(playerOneName, 1);
        System.out.println("Player: 2 enter name ");
        String playerTwoName  = getNextString();
        playerTwo = new Player(playerTwoName, 2);

        startGameTp();
    }

    /**
     * Method to handle input
     * @return a String
     */
    public String getNextString(){
        while(true) {
            boolean isValid = true;
            if (scanner.hasNext()) {
                String string = scanner.next();

                for (int i = 0 ; i < string.length(); i++) {
                    if (!Character.isLetter(string.charAt(i))){
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
        for (int i = 0 ; i < board.size; i++) {
            for (int j = 0 ; j < board.size; j++) {
              if (currentPlayer == playerOne) {
                 if (board.getMinesweeper()[i][j].isOpen()) {
                     System.out.print(" P1 ");
                 }
                  }else {
                  if(board.getMinesweeper()[i][j].isOpen()) {
                      System.out.print(" P2 ");
                  }
              }
            }
        }
        while (!board.checkWin()) {
            board.printBoard();
            System.out.println(currentPlayer.getName() + "'s turn!");

            if (!playerMove()) {
                for (int i = 0 ; i < board.size; i++) {
                    for (int j = 0 ; j < board.size; j++) {
                        board.getMinesweeper()[i][j].setOpen(true);
                    }
                }
                board.printBoard();
                System.out.println(currentPlayer.getName() + " hit a bomb! ");
                currentPlayer.setLoseCount(currentPlayer.getLoseCount()+ 1);
                printStats();
                break;
            }

            if (board.checkWin()) {
                for (int i = 0 ; i < board.size; i ++) {
                    for (int j = 0 ; i < board.size; j ++) {
                        board.getMinesweeper()[i][j].setOpen(true);
                    }
                }
                System.out.println(currentPlayer.getName() + " Has won!");
                currentPlayer.setWinCount(currentPlayer.getWinCount() + 1);
                printStats();
                break;
            }
            currentPlayer.points++;


            currentPlayer = (currentPlayer == playerOne) ? playerTwo : playerOne;
        }

    }
    public void printStats () {
        System.out.println(playerOne.getName() + "'s Stats");
        System.out.print(" Wins: " + " " +playerOne.getWinCount());
        System.out.print(" Losses: " + " " +playerOne.getLoseCount());
        System.out.print(" Points: " + " " + playerOne.getPoints());
        System.out.println();
        System.out.println("-------------------");
        System.out.println(playerTwo.getName() + "'s Stats");
        System.out.print("Wins: " + " " + playerTwo.getWinCount());
        System.out.print("Losses: " + " " + +playerTwo.getLoseCount());
        System.out.print("Points: " + " " + playerTwo.getPoints());
        System.out.println();
    }
}
