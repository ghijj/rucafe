package com.example.project5;

import static com.example.project5.MainActivity.orders;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class OrderTypeAdapter extends BaseAdapter {

    Context context;

    private static final String TAG = "ordertypeadapter";

    // Order orders;

    String testPrice[];

    String testItem[];

    LayoutInflater inflater;

    //when you implement the order class!, use this constructor

    /*public OrderTypeAdapter(Context context, Order orders){
        this.context = context;
        this.order = order;
    }*/

    public OrderTypeAdapter(Context ctx){
        this.context = ctx;
        inflater = LayoutInflater.from(ctx);
    }

    //make sure you put basket.size() in this method instead of testIten.length
    @Override
    public int getCount() {
        return orders.getSize();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_order_view, null);
        //Log.v(TAG, "index====");
        TextView oPrice = convertView.findViewById(R.id.orderPrice);
        TextView oItems = convertView.findViewById(R.id.orderItems);
        double price  = (orders.getBasketAtIndex(position).getCost());
        price = Math.round(price * 100);
        price = price/100;
        oPrice.setText("$".concat(String.valueOf(price)));
        oItems.setText(orders.getBasketAtIndex(position).toString());
        return convertView;
    }
}