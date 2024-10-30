package Game;

import Utilities.Colors;
import Utilities.InputHandler;

public class Menu {
    private final Game game;

    public Menu(Game game) {
        this.game = game;
    }

    public void mainMenu() {
        int choice = -1;
        while (choice == -1) {
            System.out.println("Let's clear some bombs in this Minesweeper game!");
            System.out.println("What would you like to do?");
            System.out.println(Colors.ANSI_GREEN + "1. Start game" + Colors.ANSI_RESET);
            System.out.println(Colors.ANSI_YELLOW + "2. Start game with two players" + Colors.ANSI_RESET);
            System.out.println(Colors.ANSI_BLUE + "3. Game instructions" + Colors.ANSI_RESET);
            System.out.println(Colors.ANSI_RED + "4. Exit" + Colors.ANSI_RESET);
            System.out.println("Your choice: ");
            choice = InputHandler.getNewIntInRange(1, 4, "list number");
            switch (choice) {
                case 1:
                    game.setSinglePlayer(true);
                    break;
                case 2:
                    game.setSinglePlayer(false);
                    break;
                case 3:
                    instructions();
                    break;
                case 4:
                    System.out.println("Thanks for playing, exiting...");
                    System.exit(0);
                    break;
            }
        }
    }

    /**
     * Method for instructions
     */
    public void instructions() {
        System.out.println();
        System.out.println(Colors.ANSI_BLUE + "Game instructions for 1 player:" + Colors.ANSI_RESET);
        System.out.println(Colors.ANSI_YELLOW + "The goal of the game is to open all of the cells without hitting a bomb.");
        System.out.println("You, the player, will pick a row and column to open a cell.");
        System.out.println("If you hit a bomb, the game ends, and you lose.");
        System.out.println("If you successfully open all cells without hitting a bomb, you win!");
        System.out.println("Each opened cell will show the number of bombs in the surrounding cells, helping you avoid bombs." + Colors.ANSI_RESET);

        System.out.println();
        System.out.println(Colors.ANSI_BLUE + "Game instructions for 2 players:" + Colors.ANSI_RESET);
        System.out.println(Colors.ANSI_YELLOW + "The goal of the game is to open all of the cells without hitting a bomb.");
        System.out.println("Two players take turns picking a row and column to open a cell.");
        System.out.println("If a player hits a bomb, they lose, and the other player wins.");
        System.out.println("If a player successfully opens a cell without hitting a bomb, their turn is complete, and the other player goes.");
        System.out.println("If all cells are opened without hitting a bomb, both players win!" + Colors.ANSI_RESET);
    }
}