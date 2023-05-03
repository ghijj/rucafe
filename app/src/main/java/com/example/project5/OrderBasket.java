package com.example.project5;

import java.util.*;

/**
 * OrderBasket class to contain and manipulate OrderBasket objects, made up of an ArrayList of MenuItem objects.
 * @author Amol Vaidya
 * */

public class OrderBasket{

    private int coffees = 0;
    private int donuts = 0;
    private double cost = 0;


    private int counter = 0;

    ArrayList<MenuItem> basket = new ArrayList<MenuItem>();

    /** Constructor with no parameters */
    public OrderBasket(){
        this.basket = new ArrayList<MenuItem>();
        this.coffees = 0;
        this.donuts = 0;
        this.cost = 0;
    }
    /** Constructor to create duplicate objects */
    public OrderBasket(OrderBasket ob){
        this.basket = ob.basket;
        this.coffees = ob.coffees;
        this.donuts = ob.donuts;
        this.cost = ob.cost;
    }

    /** Get cost of basket */
    public double getCost(){
        return cost;
    }

    /** Get size of basket */
    public int getSize(){ return basket.size(); }


    /** Get ArrayList of MenuItems */
    public ArrayList<MenuItem> getArrayList(){
        return this.basket;
    }

    /** Get MenuItem at a given index */
    public MenuItem getItemAtIndex(int i){
        if(i > basket.size()){
            return basket.get(basket.size()-1);
        }
        return basket.get(i);
    }

    /** Remove MenuItem at a given index  */
    public void removeItemAtIndex(int i){
        if(i > basket.size()){
            return;
        }
        cost = cost - basket.get(i).getCost();
        basket.remove(i);
    }

    /** Add item to MenuItem ArrayList */
    public void addItem(MenuItem item){
        if(item instanceof Coffee){
            coffees++;
        } else{
            donuts++;
        }
        basket.add(item);
        cost = cost + item.getCost();
    }

    /** Remove specific MenuItem object */
    public void removeItem(MenuItem item){ //create dummy item of the type and characteristics that needs to be removed, call this
        if(!basket.remove(item)) {
            return;
        }
        double tempCost = cost - item.getCost();
        if(tempCost >= 0){
            cost = cost - item.getCost();
        } else{
            cost = 0;
        }
    }

    /** Override toString */
    @Override
    public String toString(){
        cost = Math.round(cost * 100);
        cost = cost/100;
        String ret = "$" + cost;
        for (MenuItem menuItem : basket) {
            ret = ret + "\n" + menuItem;
        }
        return ret;
    }
}