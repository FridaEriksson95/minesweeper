package Game;
import Utilities.Colors;
import Utilities.InputHandler;

public class Menu {

    public void menu(Game game) {
        int choice = -1;
        while (choice == -1) {
            System.out.println("Let's clear some mines in this Minesweeper game!");
            System.out.println("1. Start game");
            System.out.println("2. Start game with two players ");
            System.out.println("3. Game instructions");
            System.out.println("4. Exit");
            System.out.println("Your choice: ");
            choice = InputHandler.getNewIntInRange(1, 4);
            switch (choice) {
                case 1:
                    game.singlePlayer();
                    break;
                case 2:
                    game.twoPlayerInit();
                    break;
                case 3:
                    instructions();
                    menu(game);
                    break;
                case 4:
                    System.out.println("Thanks for playing, exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option, must choose 1-4 from list, choose again");
            }
        }
    }

    public void instructions() {
        System.out.println();
        System.out.println(Colors.ANSI_BLUE + "Game instructions:");
        System.out.println("The goal of the game is to open all of the cells without hitting a mine.");
        System.out.println("Pick a row and a column to place your move.");
        System.out.println("If your move hits a mine, you loose.");
        System.out.println("If you manage to open all the cells without hitting a mine, you win!\n" + Colors.ANSI_RESET);
    }
}