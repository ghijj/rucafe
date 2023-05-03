package com.example.project5;


/**
 * Abstract MenuItem class to create and manipulate MenuItem objects. Extended by Coffee and Donut.
 * @author Amol Vaidya
 * */

public abstract class MenuItem{

    /** Get type of object */
    public String getType(){
        return "MENU";
    }

    /** Get cost */
    public double getCost(){
        return 1;
    }

}

