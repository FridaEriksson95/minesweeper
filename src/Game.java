import java.util.Scanner;

public class Game {
    private Board board;
    private final Scanner scanner;
    private final Menu menu;


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
                    System.out.println("You opened Row: " + (y+1) + " Column: " + (x+1) + ".");
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