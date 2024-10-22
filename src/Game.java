import java.util.Scanner;

public class Game {
    private Board board;
    private final Scanner scanner;

    public Game() {
        this.scanner = new Scanner(System.in);
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Låt oss röja lite minor i detta Minesweeper spel!");
        System.out.println("1. Starta nytt spel");
        System.out.println("2. Spelinstruktioner.");
        System.out.println("3. Avluta");
        System.out.println("Ditt val: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                startGame();
                break;
           /* case 2:
                instructions();
                break;*/
            case 2:
                System.out.println("Tack för spelstunden, avslutar...");
                break;
            default:
                System.out.println("Ogiltigt val, försök igen!");
                menu();
        }
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Välj brädstorlek: ");
        int size = scanner.nextInt();
        System.out.println("Välj antal minor: ");
        int bombs = scanner.nextInt();
        board = new Board(size, bombs);
        playGame();
    }
    /*public void instructions() {
        System.out.println("Spelinstruktioner:");
        System.out.println("Målet med spelet är att öppna alla rutor utan att träffa en mina.");
        System.out.println("Välj en rad och kolumn för att placera ditt drag.");
        System.out.println("Om du hamnar på en mina förlorar du spelet.");
        System.out.println("Öppnar du alla rutor utom minorna så vinner du spelet!\n");
        menu();
    }*/

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
