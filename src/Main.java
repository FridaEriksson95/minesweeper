import Game.Game;
import Game.Menu;


public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.menu(new Game());
    }
}