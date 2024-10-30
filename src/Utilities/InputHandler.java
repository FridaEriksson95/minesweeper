package Utilities;

import java.util.Scanner;

/**
 * InputHandler class
 * Handles games scanner inputs
 */
public class InputHandler {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Method that gives a range
     * Can be called everywhere
     * @param min sets a min value in range
     * @param max sets a max value in range
     * @param sub is used to be able to change minor text differences
     * @return a int
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
     * Methods that checks if user input is yes/no
     * @param question is used so other can write a question to yes/no answer
     * @return a boolean, whether its true/false
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
    public static String getNewString() {
        return scanner.nextLine();
    }
}

/*

 */