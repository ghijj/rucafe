package com.example.project5;
/**
 * Global class to contain and manipulate Global objects, made up of an OrderBasket object and an Orders object. Meant to allow .getUserData and .setUserData to manipulate both an OrderBasket instance and an Orders instance.
 * @author Amol Vaidya
 * */
public class Global {
    private OrderBasket basket;
    private Orders orders;

    /** Constructor with no parameters */
    public Global(){
        OrderBasket basket = new OrderBasket();
        Orders orders = new Orders();
    }
    /** Get OrderBasket */
    public OrderBasket getBasket(){
        return this.basket;
    }
    /** Get Orders */
    public Orders getOrders(){
        return this.orders;
    }
    /** Set OrderBasket */
    public void setOrderBasket(OrderBasket orderbasket){
        this.basket = orderbasket;
    }
    /** Set Orders */
    public void setOrders(Orders o){
        this.orders = o;
    }
}
