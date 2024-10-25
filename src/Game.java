import java.util.Scanner;

public class Game {
    private Board board;
    private final Scanner scanner;


    public Game() {
        this.scanner = new Scanner(System.in);
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Let's clear some mines in this Minesweeper game!");
        System.out.println("1. Start new game");
        //System.out.println("2. Gameinstructions.");
        System.out.println("2. Exit");
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
                if (board.withinBoundsOfGrid(x, y)) {
                    position = board.getMinesweeper()[y][x];
                    break;
                } else {
                    System.out.println("The position you entered is not on the board.");
                }
            }
            System.out.println("Do you want to place a flag on this cell? yes/no:");
            String input = scanner.next();
            if (input.equalsIgnoreCase("yes")) {
                if (!position.isOpen()) {
                    if (!position.isFlagged()) {
                        position.setFlagged(true);
                        System.out.println("Flag placed on Column: " + (x + 1) + " Row: " + (y + 1) + ".");
                        board.printBoard();
                    } else {
                        System.out.println("This cell already contains a flag. Try again.");
                        board.printBoard();
                     //   continue;
                    }
                   // continue;
                }
            } else if (input.equalsIgnoreCase("no")) {
                System.out.println("No flag placed on Column: " + (x + 1) + " Row: " + (y + 1) + ".");
                if (position.isOpen()) {
                    System.out.println("That cell is already open, try again.");
                } else {
                    board.getMinesweeper()[y][x].setOpen(true);
                    if (position.isBomb()) {
                        return false;
                    } else {
                        System.out.println("You opened Column: " + x + " Row: " + y + ".");

                        if (position.getNumber() == 0) {
                            board.openCellNearBy(y, x);
                        }

                        return true;
                    }

                }
                board.printBoard();
            } else {
                System.out.println("Invalid input. Please enter yes or no.");
              //  continue;
            }

        }
    }
}


