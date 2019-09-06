package com.company;

import java.lang.String;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Alienshipgametester {

    private static Alienship testObject = new Alienship();
    private static Alienshipgame testObjectGame = new Alienshipgame();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int locationCount = 0;
        int[] alienShip = new int[9];

        for (int i = 0; i < alienShip.length; i++) {
            System.out.println("Enter the exact ship locations as integers");
            alienShip[i] = scanner.nextInt();
        }
        scanner.nextLine();

        while (true) {
            System.out.println("Enter guess, 'c' to exit");
            String guess = scanner.nextLine();
            if(guess.equals("c")) {
                break;
            }

            boolean match = Pattern.matches("[a-hA-H][1-8]", guess);
            if (match) {
                String letter = guess.substring(0, guess.length() / 2);
                String number = guess.substring(guess.length() / 2);
                int letterToNumber = testObjectGame.gameTester(letter.toUpperCase());

                int numberInt = Integer.parseInt(number);
                int userGuessedCell = (letterToNumber * 8) + numberInt;

                System.out.println("Enter expected result");
                String expectedResult = scanner.nextLine();

                guessTester(userGuessedCell, expectedResult, alienShip);

            } else {
                System.out.println("Invalid input");
            }
        }
    }

    private static void guessTester(int userGuess, String expectedResult, int[] alienShip) {
        for (int i = 0; i < alienShip.length; i++) {
            testObject.addToArraylist(alienShip[i]);
        }
        System.out.println("What it gets:");
        testObject.evaluateUserGuess(userGuess);
        System.out.println("Expected Result:");
        System.out.println(expectedResult);


    }

}




