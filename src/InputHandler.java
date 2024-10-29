import java.util.Scanner;

public class InputHandler {

    static Scanner scanner = new Scanner(System.in);

    //TODO används denna?
    public static int getNewInt() {
        while(!scanner.hasNextInt()) {
            System.out.println("Invalid input, choose 1-4");
            scanner.nextLine();
        }

        int i = scanner.nextInt();
        scanner.nextLine();
        return i;
    }

    public static int getNewIntInRange(int min, int max) {
//TODO printar invalid två gånger om man skriver bokstäver vid menyval
        while(true){
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input, choose between 1-4. Try again: ");
                scanner.nextLine();
            }

            int i = scanner.nextInt();
            if (i >= min && i <= max) {
                scanner.nextLine();
                return i;
            }
//TODO varför min och max här men inte ovan? om man vill lägga till saker på menyn framöver
            System.out.println("Invalid input, choose between " + min + " and " + max + ": ");
        }
    }

    //TODO vart används denna?
    public static String getNewString() {
        return scanner.nextLine();
    }
}