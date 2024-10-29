package Utilities;

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
        while(true){
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input, choose between " + min + " and " + max + ". Try again: ");
                scanner.nextLine();
            }

            int i = scanner.nextInt();
            scanner.nextLine();
            if (i >= min && i <= max) {
                return i;
            }

            System.out.println("Invalid input, choose between " + min + " and " + max + ": ");
        }
    }

    //TODO vart används denna?
    public static String getNewString() {
        return scanner.nextLine();
    }
}