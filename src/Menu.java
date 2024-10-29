public class Menu {

    /**
     *  menu with 4 choices for the player.
     * @param game
     */
    public void menu(Game game) {
        int choice = -1;

        while (choice == -1) {
            System.out.println("Let's clear some bombs in this Minesweeper game!");
            System.out.println("What would you like to do?");
            System.out.println(Board.textColors.ANSI_GREEN +  "1. Start game" + Board.textColors.ANSI_RESET);
            System.out.println(Board.textColors.ANSI_YELLOW + "2. Start game with two players" + Board.textColors.ANSI_RESET);
            System.out.println(Board.textColors.ANSI_BLUE + "3. Game instructions" + Board.textColors.ANSI_RESET);
            System.out.println(Board.textColors.ANSI_RED + "4. Exit" + Board.textColors.ANSI_RESET);
            System.out.println("Your choice: ");

            choice = InputHandler.getNewIntInRange(1, 4);

            switch (choice) {
                case 1:
                    game.startGame();
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
                    //TODO när används denna när felhantering redan finns i inputhandler?
                default:
                    System.out.println("Invalid option, must choose 1-4 from list, choose again");
            }
        }
    }
//TODO uppdatera instructions med två spelare - how to play
    public void instructions() {
        System.out.println();
        System.out.println(Board.textColors.ANSI_BLUE + "Game instructions:" + Board.textColors.ANSI_RESET);
        System.out.println(Board.textColors.ANSI_YELLOW + "The goal of the game is to open all of the cells without hitting a bomb.");
        System.out.println("Pick a row and a coloumn to place your move.");
        System.out.println("If your move hits a bomb, you loose.");
        System.out.println("If you manage to open all the cells without hitting a bomb, you win!\n" + Board.textColors.ANSI_RESET);
            }
        }