package com.company;

import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;
import java.util.regex.*;

public class Alienshipgame {

    private static int kill = 0;
    private static int guessCount = 0;
    private static Alienship obj = new Alienship();
    private static ArrayList<Integer> guessList = new ArrayList<Integer>();


    public static void main(String[] args) {

        gridBuilder();
        getUserGuess();


    }

    public static boolean guessExists(int guess) {
        boolean toReturn = false;
        if (guessList.contains(guess)) {
            toReturn = true;
        } else {
            guessList.add(guess);
        }
        return toReturn;
    }


    public int gameTester(String letter) {
        return convertLetterToInt(letter);

    }

    public static void getUserGuess() {

        Scanner scanner = new Scanner(System.in);

        while (kill < 3) {

            System.out.println("Enter a guess: ");
            String guess = scanner.nextLine();

            boolean match = Pattern.matches("[a-hA-H][1-8]", guess);
            if (match) {

                String letter = guess.substring(0, guess.length() / 2);
                String number = guess.substring(guess.length() / 2);
                int letterToNumber = convertLetterToInt(letter.toUpperCase());
                int numberInt = Integer.parseInt(number);
                int userGuessedCell = (letterToNumber * 8) + numberInt;
                boolean alreadyGuessed = guessExists(userGuessedCell);
                if (alreadyGuessed) {
                    System.out.println("You guessed that already");
                } else {
                    guessCount++;
                    boolean afterEval = obj.evaluateUserGuess(userGuessedCell);
                    if (afterEval) {
                        kill++;
                        System.out.println("You sank " + kill + " out of 3 Alienships! ");
                    }
                }
            } else {
                System.out.println("Invalid input");
            }
        }
        System.out.println("Game over!!");
        System.out.println("You took a total of " + guessCount + " guesses.");
    }

    public static int convertLetterToInt(String letter) {
        char character = letter.charAt(0);
        int converted = character - 'A';
        return converted;
    }

    public static void gridBuilder() {

        int shipCount = 3;
        while (shipCount > 0) {

            int shipLocation = random(1, 64);
            // System.out.println(shipLocation);
            boolean flag = obj.notInArraylist(shipLocation);
            boolean cornered = isCornered(shipLocation);

            if (flag == true && cornered == false) {


                int direction = random(0, 1);
                if (direction == 0) {


                    boolean isNext1 = obj.notInArraylist(shipLocation + 1);
                    boolean isNext2 = obj.notInArraylist(shipLocation - 1);
                    if (isNext1 && isNext2) {


                        obj.addToArraylist(shipLocation - 1);
                        obj.addToArraylist(shipLocation);
                        obj.addToArraylist(shipLocation + 1);
                        shipCount--;
                    }

                } else {
                    boolean isNext1 = obj.notInArraylist(shipLocation + 8);
                    boolean isNext2 = obj.notInArraylist(shipLocation - 8);
                    if (isNext1 && isNext2) {
                        obj.addToArraylist(shipLocation - 8);
                        obj.addToArraylist(shipLocation);
                        obj.addToArraylist(shipLocation + 8);
                        shipCount--;
                    }
                }
            }
        }

        obj.getArraylist();
    }


    public static boolean isCornered(int ship) {
        boolean cornered = false;
        int[] cornerNumbers = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 57, 58, 59, 60, 61, 62, 63, 64, 9, 17, 25, 33, 41, 49, 16, 24, 32, 40, 48, 56};
        for (int i = 0; i < cornerNumbers.length; i++) {
            if (cornerNumbers[i] == ship) {
                cornered = true;
                break;
            }
        }
        return cornered;
    }


    public static int random(int min, int max) {

        int range = max - min + 1;
        int rand = (int) (Math.random() * range) + min;
        return (rand);
    }
}
