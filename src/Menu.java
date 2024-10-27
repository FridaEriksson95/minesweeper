import java.util.Scanner;

public class Menu {

    public void menu(Game game) {
//        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while(choice != 0) {
            System.out.println("Let's clear some mines in this Minesweeper game!");
            System.out.println("1. Start game");
            System.out.println("2. Start game with two players ");
            System.out.println("3. Game instructions");
            System.out.println("4. Exit");
            System.out.println("Your choice: ");
//            int choice = scanner.nextInt();

//                choice = Integer.parseInt(scanner.nextLine().trim().replaceAll("^\"|\"$", ""));;
            choice = InputHandler.getNewIntInRange(1, 4);
//            System.out.println("you entered " + choice); //Debug mode,
            switch (choice) {
                case 1:
                    game.startGame();
                    //game.playerMove? -> Single player?
                    break;
                case 2:
                    //startGameTwoPlayers(); -> Two player
                    dummyMethod(); //Dummy method for console testing
                    break;
                case 3:
                    instructions(game);
                    break;
                case 4:
                    System.out.println("Thanks for playing, exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option, must choose 1-4 from list, choose again");
//                        System.out.println("switch default comment");
            }

        }
    }
    public void instructions(Game game) {
        System.out.println("Gameinstructions:");
        System.out.println("The goal of the game is to open all of the cells without hitting a mine.");
        System.out.println("Pick a row and a coloumn to place your move.");
        System.out.println("If your move hits a mine, you loose.");
        System.out.println("If you manage to open all the cells without hitting a mine, you win!\n");
//                menu(game);
    }

    public void dummyMethod() {
        System.out.println("nothing here");
    }
}