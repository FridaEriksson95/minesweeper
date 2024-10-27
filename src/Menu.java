import java.util.Scanner;

public class Menu {

    public void menu(Game game) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Let's clear some mines in this Minesweeper game!");
        System.out.println("1. Start game");
        System.out.println("2. Start game with two players ");
        System.out.println("3. Game instructions");
        System.out.println("4. Exit");
        System.out.println("Your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                game.startGame();
                break;
            case 2:
                game.twoPlayerInit();
            case 3:
                instructions(game);
                break;
            case 4:
                System.out.println("Thanks for playing, exiting...");
                break;
            default:
                System.out.println("Invalid choice, try again!");
                menu(game);
        }
    }

    public void instructions(Game game) {
        System.out.println();
        System.out.println(Board.textColors.ANSI_BLUE + "Gameinstructions:");
        System.out.println("The goal of the game is to open all of the cells without hitting a mine.");
        System.out.println("Pick a row and a coloumn to place your move.");
        System.out.println("If your move hits a mine, you loose.");
        System.out.println("If you manage to open all the cells without hitting a mine, you win!\n" + Board.textColors.ANSI_RESET);
        menu(game);
    }
}