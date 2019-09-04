package com.company;

import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;
import java.util.regex.*;

public class Alienshipgame {

    private static int kill=0;
    public static void main(String[] args) {

        //gridBuilder();
        getuserGuess();
        Alienship obj= new Alienship();
    }

    public static void getuserGuess() {

        Scanner scanner= new Scanner(System.in);
        while(kill!=3) {

            System.out.println("Enter a guess: ");
            String guess = scanner.nextLine();

            boolean match= Pattern.matches("[a-hA-H]?[1-8]?", guess);
            if(match) {
                String letter = guess.substring(0, guess.length() / 2);
                String number = guess.substring(guess.length() / 2);
                int letterToNumber = convertLetterToInt(letter);
                int numberInt = Integer.parseInt(number);
                int locationCell=(letterToNumber*8)+numberInt;
                System.out.println(locationCell);
            }
            else {
                System.out.println("Invalid input");
            }



        }

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

        ArrayList<Integer> arraylist = new ArrayList<Integer>();

        while (shipCount > 0) {

            int ship1 = shipBuilder(1, 64);
            int flag = checkArraylist(arraylist, ship1);
            int cornered =isCornered(ship1);

            if(flag==1 && cornered==1) {

                int direction= shipBuilder(0,1);
                if(direction==0) {

                    int isNext1= checkArraylist(arraylist, ship1+1);
                    int isNext2= checkArraylist(arraylist, ship1-1);
                    if (isNext1==1 && isNext2==1) {
                        arraylist.add(ship1-1);
                        arraylist.add(ship1);
                        arraylist.add(ship1+1);

                        shipCount--;
                    }

                } else {
                    int isNext1= checkArraylist(arraylist, ship1+8);
                    int isNext2= checkArraylist(arraylist, ship1-8);
                    if (isNext1==1 && isNext2==1) {
                        arraylist.add(ship1-8);
                        arraylist.add(ship1);
                        arraylist.add(ship1+8);

                        shipCount--;
                    }
                }
            }
        }

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


    public static int checkArraylist(ArrayList<Integer> arraylist, int ship) {
        int flag=1;
        for (int i = 0; i < arraylist.size(); i++) {

            if (arraylist.get(i) == ship) {
                flag = 0;
            }
        }
        return flag;
    }




    public static int shipBuilder(int min, int max) {

        int range = max - min + 1;
        int rand = (int) (Math.random() * range) + min;
        return (rand);
    }
}
