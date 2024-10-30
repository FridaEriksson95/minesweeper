package Utilities;

import java.util.Scanner;

/**
 * Class that handles general inputs for game
 */

public class InputHandler {

    private static Scanner scanner = new Scanner(System.in);

    /**
     * Method to handle int range input
     *
     * @return a int, min max
     */

    public static int getNewIntInRange(int min, int max, String sub) {
        while(true){
            while (!scanner.hasNextInt()) {
//                If string is inserted
                System.out.println("String is not a number, choose a " + sub + " between " + min + " and " + max +":");
                scanner.nextLine();
            }

            int i = scanner.nextInt();
            scanner.nextLine();
            if (i >= min && i <= max) {
                return i;
            }
            System.out.println("Number input out of range, choose a " + sub + " between " + min + " and " + max +":+++");
        }
    }
    /**
     * Method to handle yes/no input
     *
     * @return a boolean
     */

    public static boolean getYesOrNo(String question) {
        while(true){
            System.out.println(question + " yes/no");
            String input = scanner.nextLine().trim().toLowerCase();

            if(input.equals("yes") || input.equals("y")) {
                return true;
            } else if(input.equals("no") || input.equals("n")) {
                return false;
            } else {
                System.out.println("Invalid input, must choose either yes/no");
            }
        }
    }
    /**
     * Method to handle input
     *
     * @return a input String
     */

    public static String getNewString() {
        return scanner.nextLine();
    }


    /**
     * Method to handle input
     *
     * @return a String
     */
    public static String getNextString() {
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
}

/*

 */