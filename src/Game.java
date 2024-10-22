import java.util.Scanner;

public class Game {
    private Board board;
    private final Scanner scanner;

    public Game() {
        this.scanner = new Scanner(System.in);
    }

    public void menu() {
    }

    public void playGame() {
    }

    public void resetGame() {
    }

    public boolean playerMove() {
        Cell position;
        while (true) {
            int x;
            int y;
            while (true) {
                while (true) {
                    System.out.println("Enter x-position:");
                    if (scanner.hasNextInt()) {
                        x = scanner.nextInt();
                        break;
                    } else {
                        System.out.println("You need to enter a number, try again.");
                    }
                }
                while (true) {
                    System.out.println("Enter y-position:");
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
                    position = board.getMinesweeper()[x][y];
                    break;

                } else {
                    System.out.println("The position you entered is not on the board.");
                }
            }
            if (position.isOpen()) {
                System.out.println("That cell is already open, try again.");
            } else {
                if (position.isBomb()) {
                    System.out.println("You hit a bomb, game over!");
                    return false;
                } else {
                    board.getMinesweeper()[x][y].setOpen(true);
                    System.out.println("You opened x: " + x + " y: " + y + ".");
                    return true;
                }
            }
        }
    }

    public boolean withinBoundsOfGrid(int x, int y) {
        return (x > 0 && x < board.size) && (y > 0 && y < board.size);
    }

    public void gameOver() {
    }

}
