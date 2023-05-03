package com.example.project5;

import java.util.*;

/**
 * Coffee class to create and manipulate Coffee objects.
 * @author Amol Vaidya
 * */

public class Coffee extends MenuItem{
    private final double BASE_COST = 1.89;
    private final double SIZEUP_COST = 0.4;
    private final double ADDIN_COST = 0.3;

    private int size; // 1 == short 2 == tall 3 == grande 4 == venti
    private boolean[] addins = new boolean[5];

    /** Constructor with coffee size and addins as parameters*/
    public Coffee(int newSize, boolean[] adds){
        this.size = newSize;
        for(int i = 0; i < 5; i++){
            this.addins[i] = adds[i];
        }
    }

    /** Get cost of coffee */
    public double getCost(){
        double cost = BASE_COST + SIZEUP_COST * (size-1);
        for(int i = 0; i < 5; i++){
            if(this.addins[i]){
                cost = cost + ADDIN_COST;
            }
        }
        cost = Math.round(cost*100);
        return cost/100;
    }
    /** Get size of coffee */
    public int getSize(){
        return this.size;
    }

    /** Get addins for coffee */
    public boolean[] getAddins(){
        return this.addins;
    }

    /** Adjust size of coffee */
    public void adjustSize(int i){
        if(i > 4 || i < 1){
            return;
        }
        this.size = i;
    }

    /** Remove specific addin from coffee */
    public void removeAddin(int i){
        if(i > 4){
            return;
        }
        this.addins[i] = false;
    }

    /** Add specific addin to coffee */
    public void addAddin(int i){
        if(i > 4){
            return;
        }
        this.addins[i] = true;
    }
    /** Override .equals */
    @Override
    public boolean equals(Object o){

        if(o == this){
            return true;
        }

        if((o instanceof Donut)){
            return false;
        }

        Coffee c = (Coffee) o;
        if(c == null){
            return false;
        }
        return this.size == c.getSize() && Arrays.equals(this.addins, c.getAddins());

    }

    /** Override toString() */
    @Override
    public String toString() {
        String ret = "";
        if(size == 1){
            ret = ret + "short ";
        } else if(size == 2){
            ret = ret + "tall ";
        } else if(size == 3){
            ret = ret + "grande ";
        } else if(size == 4){
            ret = ret + "venti ";
        }
        if(addins[0]){
            ret = ret + "sweet cream ";
        }
        if(addins[1]){
            ret = ret + "french vanilla ";
        }
        if(addins[2]){
            ret = ret + "irish cream ";
        }
        if(addins[3]){
            ret = ret + "caramel ";
        }
        if(addins[4]){
            ret = ret + "mocha ";
        }
        return ret;
    }
}