package com.example.project5;

import java.util.*;

/**
 * Donut class to create and manipulate CofDonutfee objects.
 * @author Amol Vaidya
 * */

public class Donut extends MenuItem{
    private final double yeastCost = 1.59;
    private final double cakeCost = 1.79;
    private final double holeCost = 0.39;

    private final int ERROR_CODE = -1;

    private String donutType;
    private String donutVariety;

    /** Constructor for Donut with donutType and donutVariety*/
    public Donut(String dt, String dv){ //should be able to make a new donut for menu with newDonut = Donut("Yeast", "Glazed");
        this.donutType = dt.toLowerCase();
        this.donutVariety = dv.toLowerCase();
    }

    /** Get Donut type*/
    public String getDonutType(){
        return donutType;
    }

    /** Get type of object*/
    public String getType(){
        return "Donut";
    }

    /** Get Donut variety*/
    public String getDonutVariety(){ //should be ok that theoretically any string can be a variety because only the strings allowed through the GUI should get to this point
        return donutVariety;
    }

    /** Get donut cost */
    public double getCost(){
        switch (donutType) {
            case "yeast": return yeastCost;
            case "cake": return cakeCost;
            case "hole": return holeCost;
        }
        return ERROR_CODE;
    }

    /** Override .equals*/
    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        
        if(!(o instanceof Donut)){
            return false;
        }

        Donut d = (Donut) o;

        return this.donutType.equals(d.getDonutType()) && this.donutVariety.equals(d.getDonutVariety());
    }

    /** Override .toString() */
    @Override
    public String toString() {
        if(donutType.equalsIgnoreCase("Hole")){
            return donutVariety + " donut " + donutType;
        } else{
            return donutVariety + " " + donutType + " donut";
        }
    }
}