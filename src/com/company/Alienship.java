package com.company;

import java.util.ArrayList;
import java.util.List;

class Alienship {

    private ArrayList<Integer> arraylist = new ArrayList<>();


    boolean notInArrayList(int ship) {
        boolean flag = true;
        if (arraylist.contains(ship)) {
            flag = false;
        }
        return flag;
    }

    void printArrayList() {
        for (Integer integer : arraylist) {
            System.out.println(integer + " ");
        }
    }

    void addToArrayList(int shipLocation) {

        arraylist.add(shipLocation);
    }


    boolean evaluateUserGuess(int guess) {
        boolean toReturn = false;
        List<Integer> ship1 = arraylist.subList(0, 3);
        List<Integer> ship2 = arraylist.subList(3, 6);
        List<Integer> ship3 = arraylist.subList(6, 9);
        if (ship1.contains(guess) || ship2.contains(guess) || ship3.contains(guess)) {
            System.out.println("Its a Hit!");
            if (ship1.contains(guess)) {
                int position = ship1.indexOf(guess);
                ship1.set(position, 0);
                int flag = 1;
                for (Integer integer : ship1) {
                    if (integer != 0) {
                        flag = 1;
                    } else {
                        flag = 0;
                    }
                }
                if (flag == 0) {
                    toReturn = true;
                }
            } else if (ship2.contains(guess)) {
                int position = ship2.indexOf(guess);
                ship2.set(position, 0);
                int flag = 1;
                for (Integer integer : ship2) {
                    if (integer != 0) {
                        flag = 1;
                    } else {
                        flag = 0;
                    }
                }
                if (flag == 0) {
                    toReturn = true;
                }

            } else {
                int position = ship3.indexOf(guess);
                ship3.set(position, 0);
                int flag = 1;
                for (Integer integer : ship3) {
                    if (integer != 0) {
                        flag = 1;
                    } else {
                        flag = 0;
                    }
                }
                if (flag == 0) {
                    toReturn = true;
                }
            }

        } else {
            System.out.println("Its a Miss!");

        }
        return toReturn;

    }

}
