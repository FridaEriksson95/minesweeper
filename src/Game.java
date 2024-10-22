import java.util.Scanner;

public class Game {
    private Board board;

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

    public void playerMove() {
    }

    public void gameOver() {
    }

}
