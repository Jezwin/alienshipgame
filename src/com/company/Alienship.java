package com.company;

import java.util.ArrayList;

public class Alienship {

    private ArrayList<Integer> arraylist = new ArrayList<Integer>();

    public int checkArraylist(int ship) {
        int flag=1;
        for (int i = 0; i < arraylist.size(); i++) {

            if (arraylist.get(i) == ship) {
                flag = 0;
            }
        }
        return flag;
    }

    public ArrayList<Integer> getArraylist(){
        return arraylist;
    }

    public void addToArraylist(int shipLocation) {
        arraylist.add(shipLocation);
    }

    public void evaluateUserGuess(int guess) {
    if(arraylist.contains(guess)) {
        System.out.println("Its a Hit!");
    }
    else{
        System.out.println("Its a Miss!");
    }
    }
}
