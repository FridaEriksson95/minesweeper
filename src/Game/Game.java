package Game;

import Board.Board;
import Utilities.Colors;
import Utilities.InputHandler;
import Board.Cell;

public class Game {
    private final Board board;
    private boolean singlePlayer;
    private final Player p1, p2;
    private Player currentPlayer;
    private boolean newGame;
    private final Menu menu;

    public Game() {
        this.board = new Board();
        this.p1 = new Player("Player", Colors.ANSI_GREEN);
        this.p2 = new Player("Player 2", Colors.ANSI_BLUE);
        this.currentPlayer = p1;
        this.newGame = true;
        this.menu = new Menu(this);
        this.singlePlayer = true;
    }

    public void run() {
        do {
            if (newGame) {
                p1.setColor(Colors.ANSI_GREEN);
                menu.mainMenu();
                if (!singlePlayer) {
                    p1.setColor(Colors.ANSI_RED);
                    setupTwoPlayers();
                }
            }
            setupBoard();
            p1.setScore(0);
            p2.setScore(0);
            do {
                board.printBoard();
                switchCurrentPlayer();
            }
            while (placeMarker() && !board.checkWin());
            System.out.println(board.checkWin() ? currentPlayer.getName() + " won!" : currentPlayer.getName() + "hit a bomb! Game over!");
            currentPlayer.setWinCount(currentPlayer.getWinCount() + 1);
            if (!singlePlayer) {
                printStats();
            }
        }
        while (playAgain());
    }

    private void setupBoard() {
        System.out.println("Choose a board size (3-20): ");
        int size = InputHandler.getNewIntInRange(3, 20, "board size");
        System.out.println("Choose amount of mines (1-" + (size * size - 1) + "): ");
        int mines = InputHandler.getNewIntInRange(1, size * size - 1, "mine amount");
        board.setBoard(size, mines);
    }

    public void setupTwoPlayers() {
        p1.resetPlayerStatistics();
        p2.resetPlayerStatistics();
        System.out.println("Player: 1 enter name");
        String p1Name = InputHandler.getNewString();
        p1.setName(p1Name);
        System.out.println("Player: 2 enter name ");
        String p2Name = InputHandler.getNewString();
        p2.setName(p2Name);
        System.out.println(p1Name + " and " + p2Name + ", let's go!");
    }

    public boolean placeMarker() {
        Cell cell;
        while (true) {
            boolean flagging = true;
            int x;
            int y;
            do {
                System.out.println(currentPlayer.getName() + ", place a marker!");
                System.out.println("Enter a row:");
                y = InputHandler.getNewIntInRange(1, board.getSize(), "row") - 1;
                System.out.println("Enter a column:");
                x = InputHandler.getNewIntInRange(1, board.getSize(), "column") - 1;
                if (singlePlayer) {
                    flagging = placeFlag(x, y);
                }
            }
            while (flagging && singlePlayer);
            cell = board.getMinesweeper()[y][x];
            if (cell.isOpen()) {
                System.out.println("That cell is already open, try again.");
            } else {
                if (cell.getNumber() == 0) {
                    board.openCellNearBy(x, y);
                }
                if (!cell.isBomb()) {
                    cell.setOpen(true);
                    cell.setLastOpenedBy(currentPlayer);
                    currentPlayer.setScore(currentPlayer.getScore() + 1);
                    System.out.println("You opened Row: " + (y + 1) + " Column: " + (x + 1) + ".");
                    return true;
                } else {
                    board.openAllCells();
                    board.printBoard();
                    switchCurrentPlayer();
                    return false;
                }
            }
        }
    }

    public boolean placeFlag(int x, int y) {
        if (InputHandler.getYesOrNo("Place flag?")) {
            board.getMinesweeper()[y][x].setFlagged(true);
            board.printBoard();
            System.out.println("You flagged Row: " + x + " Column: " + y);
            return true;
        }
        return false;
    }


    private void switchCurrentPlayer() {
        currentPlayer = singlePlayer ? currentPlayer : (currentPlayer.equals(p1) ? p2 : p1);
    }

    public void printStats() {
        System.out.println("\n========= Player Statistics =========");
        System.out.printf("%-15s | %-6s | %-6s %n", "Player", "Wins", "Points");
        System.out.println("-------------------------------------");

        System.out.printf("%-15s | %-6d | %-6d %n",
                p1.getName(),
                p1.getWinCount(),
                p1.getScore());

        System.out.printf("%-15s | %-6d | %-6d %n",
                p2.getName(),
                p2.getWinCount(),
                p2.getScore());

        System.out.println("=====================================\n");
    }

    public boolean playAgain() {
        if (!InputHandler.getYesOrNo("Continue playing?")) {
            return false;
        } else {
            String setting = singlePlayer ? "single player" : "2 players)";
            newGame = !InputHandler.getYesOrNo("Continue with the same settings (" + setting + ")?");
            return true;
        }
    }

    public void setSinglePlayer(boolean singlePlayer) {
        this.singlePlayer = singlePlayer;
    }
}
