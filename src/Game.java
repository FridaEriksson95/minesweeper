    import jdk.jshell.EvalException;
    import java.util.Scanner;

    public class Game {
        private Board board;
        private final Scanner scanner;
        private final Menu menu;
        private Player playerOne;
        private Player playerTwo;
        private Player currentPlayer;

        //constructor for scanner and menu
        public Game() {
            this.scanner = new Scanner(System.in);
            this.menu = new Menu();
        }

        /**
         * Method for player to choose board size between 3-20 and number of bombs related to chosen board size
         */
        public void startGame() {
            int size = 0;
            int bombs = 0;
//TODO spel dör om spelare skriver in bokstäver, här och under twoplayer
            while (size < 3 || size > 20) {
                System.out.println("Choose board size (3-20): ");
                size = scanner.nextInt();
                if (size < 3 || size > 20) {
                    System.out.println("Invalid input. Choose a size between 3-20.");
                }
            }
            while (bombs < 1 || bombs >= (size * size)) {
                System.out.println("Choose amount of bombs (1-" + (size * size -1) + "): ");
                bombs = scanner.nextInt();
                if (bombs < 1 || bombs >= (size * size)) {
                    System.out.println("Invalid input. Choose a number between 1-" + (size * size - 1) + ".");
                }
            }

            board = new Board(size, bombs);
            playGame();
        }

        public void playGame() {
            while (!board.checkWin() && playerMove(0)) {
                board.printBoard(false);
            }
            if (board.checkWin()) {
                System.out.println("Congratulations, you won!");
            } else {
                for (int i = 0; i < board.size; i++) {
                    for (int j = 0; j < board.size; j++) {
                        board.getMinesweeper()[i][j].setOpen(true);
                    }
                }
                board.printBoard(false);
                System.out.println("You hit a bomb, game over!");
            }
            resetGame(false);
        }

        /**
         * Asks the user to enter an x- and y-position.
         * @return Returns true when move is made and false if a bomb is hit.
         */
        public boolean playerMove(int playerNumber) {
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
                    if (board.withinBoundsOfGrid(x, y)) {
                        position = board.getMinesweeper()[y][x];
                        position.setLastOpenedBy(playerNumber);
                        break;
                    } else {
                        System.out.println("The position you entered is not on the board.");
                    }
                }
                //TODO - fånga upp om spelaren svarar varken yes eller no så kommer invalid input men sedan går den vidare till enter row istället för att be spelaren svara yes or no
                //TODO - går ej att flagga två gånger efter varandra, spelares tur igen efter man flaggat? isf borde fråga om att flagga ej komma upp på nästa drag?
                System.out.println("Do you want to place a flag on this cell? yes/no:");
                String input = scanner.next();
                if (input.equalsIgnoreCase("yes")) {
                    if (!position.isOpen()) {
                        if (!position.isFlagged()) {
                            position.setFlagged(true);
                            System.out.println("Flag placed on Column: " + (x + 1) + " Row: " + (y + 1) + ".");
                            board.printBoard(false);
                        } else {
                            System.out.println("This cell already contains a flag. Try again.");
                            board.printBoard(false);
                        }
                    }
                } else if (input.equalsIgnoreCase("no")) {
                    System.out.println("No flag placed on Row: " + (y + 1) + " Column: " + (x + 1) + ".");
                    if (position.isOpen()) {
                        System.out.println("That cell is already open, try again.");
                    } else {
                        board.getMinesweeper()[y][x].setOpen(true);
                        if (position.isBomb()) {
                            return false;
                        } else {
                            System.out.println("You opened Row: " + (y + 1) + " Column: " + (x + 1) + ".");
                            if (position.getNumber() == 0) {
                                board.openCellNearBy(x, y);
                            }
                            return true;
                        }
                    }
                    board.printBoard(false);
                } else {
                    System.out.println("Invalid input. Please enter yes or no.");
                }
            }
        }

        /**
         * Method to Create player one and two.
         */
        public void twoPlayerInit() {
            System.out.println("Player 1, enter name: ");
            String playerOneName = getNextString();
            playerOne = new Player(playerOneName, 1, "\u001B[34m");
            System.out.println("Player 2, enter name: ");
            String playerTwoName = getNextString();
            playerTwo = new Player(playerTwoName, 2, "\u001B[35m");

            //TODO - lägga till så den printar ut typ "playerOneName and playerTwoName, let's begin! Lina?

            startGameTp();
        }

        /**
         * Method to handle input
         * @return a String
         */
        public String getNextString() {
            while (true) {
                boolean isValid = true;
                if (scanner.hasNext()) {
                    String string = scanner.next();

                    for (int i = 0; i < string.length(); i++) {
                        if (!Character.isLetter(string.charAt(i))) {
                            isValid = false;
                            System.out.println("Not a valid name, please use only letters.");
                            break;
                        }
                    }
                    if (isValid) {
                        return string;
                    }

                }
            }
        }

        /**
         * Method to start the twoPlayerMode
         */
        public void startGameTp() {
            int size = 0;
            int bombs = 0;
            Scanner scanner = new Scanner(System.in);
            while (size < 3 || size > 20) {
                System.out.println("Choose board size (3-20): ");
                size = scanner.nextInt();
                if (size < 3 || size > 20) {
                    System.out.println("Invalid input. Choose a size between 3-20.");
                }
            }
            while (bombs < 1 || bombs >= (size * size)) {
                System.out.println("Choose amount of bombs (1-" + (size * size - 1) + "): ");
                bombs = scanner.nextInt();
                if (bombs < 1 || bombs >= (size * size)) {
                    System.out.println("Invalid input. Choose a number between 1-" + (size * size - 1) + ".");
                }
            }
            currentPlayer = playerOne;
            board = new Board(size,bombs);
            playGameTp();
        }

        public void playGameTp() {
            while (!board.checkWin()) {
                board.printBoard(true);
                System.out.println(currentPlayer.getName() + "'s turn!");

                if (!playerMove(currentPlayer == playerOne ? 1 : 2)) {
                    for (int i = 0; i < board.size; i++) {
                        for (int j = 0; j < board.size; j++) {
                            board.getMinesweeper()[i][j].setOpen(true);
                        }
                    }
                    board.printBoard(true);
                    System.out.println(currentPlayer.getName() + " hit a bomb! ");
                    currentPlayer.setLoseCount(currentPlayer.getLoseCount() + 1);
                    printStats();
                    resetGame(true);
                    break;
                }

                if (board.checkWin()) {
                    System.out.println("Congratulations " + currentPlayer.getName() + " Won!");
                    currentPlayer.setWinCount(currentPlayer.getWinCount() + 1);
                    printStats();
                    resetGame(true);
                    break;
                }
                currentPlayer.points++;
                currentPlayer = (currentPlayer == playerOne) ? playerTwo : playerOne;
            }
        }
//TODO - behöver wins finnas kvar?
        public void printStats() {
            System.out.println("\n========= Player Statistics =========");
            System.out.printf("%-15s | %-6s | %-7s | %-6s %n", "Player", "Wins", "Losses", "Points");
            System.out.println("-------------------------------------");

            System.out.printf( "%-15s | %-6d | %-7d | %-6d %n",
                    playerOne.getName(),
                    playerOne.getWinCount(),
                    playerOne.getLoseCount(),
                    playerOne.getPoints());

            System.out.printf( "%-15s | %-6d | %-7d | %-6d %n",
                    playerTwo.getName(),
                    playerTwo.getWinCount(),
                    playerTwo.getLoseCount(),
                    playerTwo.getPoints());

            System.out.println("=====================================\n");
        }
        public void resetGame (boolean isTwoPlayer) {
            System.out.println("Do you wish to play again? y/n");
            String input = scanner.next().toLowerCase();

            while (!input.equals("y") && !input.equals("n")) {
                System.out.println("Invalid input. Enter 'y' to play again or 'n' to exit");
                input = scanner.next().toLowerCase();
            }
            if (input.equals("y") && !isTwoPlayer) {
                startGame();

            }else if (input.equalsIgnoreCase("y") && isTwoPlayer) {
                twoPlayerInit();
            }
            else {
                System.out.println("Thank you for playing!");
                System.exit(0);
            }
        }
    }