package com.example.project5;

import java.util.*;


/**
 * Orders class to contain and manipulate Order objects, made up of an ArrayList of OrderBasket objects.
 * @author Amol Vaidya
 * */

public class Orders{

    private double totalCost;

    ArrayList<OrderBasket> orders = new ArrayList<OrderBasket>();

    /** Constructor with no parameters */
    public Orders(){
        this.totalCost = 0;
        ArrayList<OrderBasket> orders = new ArrayList<OrderBasket>();
    }
    /** Add an OrderBasket to the orders ArrayList */
    public void addOrder(OrderBasket ob){
        orders.add(ob);
        totalCost = totalCost + ob.getCost();
    }
    /** Remove an OrderBasket from given index */
    public void removeOrderAtIndex(int i){
        if(i > orders.size()){
            return;
        }
        totalCost = totalCost - orders.get(i).getCost();
        orders.remove(i);
    }
    /** Remove a given OrderBasket object */
    public void removeOrder(OrderBasket ob){
        orders.remove(ob);
        totalCost = totalCost - ob.getCost();
    }
    /** Get size of the orders ArrayList */
    public int getSize(){
        return orders.size();
    }

    /** Get OrderBasket at given index */
    public OrderBasket getBasketAtIndex(int i){
        if(i > orders.size()){
            return orders.get(orders.size()-1);
        }
        return orders.get(i);
    }

    /** Get cost of the entire order */
    public double getCost(){
        return totalCost;
    }
}