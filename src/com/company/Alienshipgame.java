package com.company;

import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;
import java.util.regex.*;

public class Alienshipgame {

    private static int kill=0;
    private static int guessCount=0;
    private static Alienship obj= new Alienship();
    private static ArrayList<Integer> guessexists = new ArrayList<Integer>();



    public static void main(String[] args) {

        guessexists.add(0);
        //System.out.println(guessexists);
        gridBuilder();
        getuserGuess();



    }

    public static boolean guessExists(int guess){
        boolean toReturn=false;
        if (guessexists.contains(guess)){
            toReturn= true;
        } else {
            guessexists.add(guess);
        }
        return toReturn;
    }

    public static void getuserGuess() {

        Scanner scanner= new Scanner(System.in);

        while(kill<3) {

            System.out.println("Enter a guess: ");
            String guess = scanner.nextLine();

            boolean match= Pattern.matches("[a-hA-H]?[1-8]?", guess);
            if(match) {

                String letter = guess.substring(0, guess.length() / 2);
                String number = guess.substring(guess.length() / 2);
                int letterToNumber = convertLetterToInt(letter);
                int numberInt = Integer.parseInt(number);
                int userGuessedCell = (letterToNumber * 8) + numberInt;
                boolean alreadyGuessed = guessExists(userGuessedCell);
                if(alreadyGuessed) {
                    System.out.println("You guessed that already");
                } else {
                    guessCount++;
                    boolean afterEval = obj.evaluateUserGuess(userGuessedCell);
                    if (afterEval) {
                        kill++;
                        System.out.println("You sank " + kill + " out of 3 Alienships! ");
                    }
                }


            }


            else {
                System.out.println("Invalid input");
            }



        }
        System.out.println("Game over!!");
        System.out.println("You took a total of "+guessCount+" guesses.");

    }

    public static int convertLetterToInt(String letter) {
        int toReturn=-1;
        switch (letter)
        {
            case "A":
            case "a":
                toReturn = 0;
                break;
            case "B":
            case "b":
                toReturn = 1;
                break;
            case "C":
            case "c":
                toReturn = 2;
                break;
            case "D":
            case "d":
                toReturn = 3;
                break;
            case "E":
            case "e":
                toReturn = 4;
                break;
            case "F":
            case "f":
                toReturn = 5;
                break;
            case "G":
            case "g":
                toReturn = 6;
                break;
            case "H":
            case "h":
                toReturn = 7;
                break;
            default:    toReturn = -1;
                break;
        }
        return toReturn;
    }

    public static void gridBuilder() {
        
        int shipCount = 3;


        while (shipCount > 0) {

            int ship1 = shipBuilder(1, 64);
            int flag = obj.checkArraylist(ship1);
            int cornered =isCornered(ship1);

            if(flag==1 && cornered==1) {

                int direction= shipBuilder(0,1);
                if(direction==0) {

                    int isNext1= obj.checkArraylist(ship1+1);
                    int isNext2= obj.checkArraylist(ship1-1);
                    if (isNext1==1 && isNext2==1) {

                        obj.addToArraylist(ship1-1);
                        obj.addToArraylist(ship1);
                        obj.addToArraylist(ship1+1);
                        shipCount--;
                    }

                } else {
                    int isNext1= obj.checkArraylist(ship1+8);
                    int isNext2= obj.checkArraylist(ship1-8);
                    if (isNext1==1 && isNext2==1) {
                        obj.addToArraylist(ship1-8);
                        obj.addToArraylist(ship1);
                        obj.addToArraylist(ship1+8);
                        shipCount--;
                    }
                }
            }
        }

        ArrayList<Integer> arraylist = new ArrayList<Integer>();
        arraylist=obj.getArraylist();
        for (int i=0; i<arraylist.size(); i++) {
            System.out.println(arraylist.get(i) + " ");
        }
    }


    public static int isCornered(int ship) {
        int cornered=1;
        int[] cornerNumbers= new int[]{ 1,2,3,4,5,6,7,8,57,58,59,60,61,62,63,64,9,17,25,33,41,49,16,24,32,40,48,56 };
        for(int i=0; i<cornerNumbers.length; i++) {
            if(cornerNumbers[i]==ship) {
                cornered=0;
                break;
            }
        }
        return cornered;
    }







    public static int shipBuilder(int min, int max) {

        int range = max - min + 1;
        int rand = (int) (Math.random() * range) + min;
        return (rand);
    }
}
