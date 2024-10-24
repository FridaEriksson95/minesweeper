import java.util.Scanner;

public class Menu {

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Let's clear some mines in this Minesweeper game!");
        System.out.println("1. Start game");
        System.out.println("2. Start game with two players ");
        System.out.println("3. Gameinstructions.");
        System.out.println("4. Exit");
        System.out.println("Your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                startGame();
                break;
            case 2:
                //startGameTwoPlayers();
            case 3:
                instructions();
                break;
            case 4:
                System.out.println("Thanks for playing, exiting...");
                break;
            default:
                System.out.println("Invalid choice, try again!");
                menu();
        }
    }
    public void startGame() {
        int size = 0;
        int bombs = 0;
        Scanner scanner = new Scanner(System.in);
        while (size < 1 || size > 20) {
            System.out.println("Choose boardsize (1-20): ");
            size = scanner.nextInt();
            if (size < 1 || size > 20) {
                System.out.println("Invalid input. Choose a size between 1-20.");
            }
            }
            while (bombs < 1 || bombs >= size * size) {
                System.out.println("Choose amount of mines: ");
                bombs = scanner.nextInt();
                if (bombs < 1 || bombs >=size * size) {
                    System.out.println("Invalid input. Choose a  number between 1-" + (size * size - 1) + ".");
                }
                }

                Board board = new Board(size, bombs);
            }

            public void instructions() {
                System.out.println("Gameinstructions:");
                System.out.println("The goal of the game is to open all of the cells without hitting a mine.");
                System.out.println("Pick a row and a coloumn to place your move.");
                System.out.println("If your move hits a mine, you loose.");
                System.out.println("If you manage to open all the cells without hitting a mine, you win!\n");
                menu();
            }
        }