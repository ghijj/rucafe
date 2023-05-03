package com.example.project5;

import android.annotation.SuppressLint;
import static com.example.project5.MainActivity.basket;
import static com.example.project5.MainActivity.orders;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BasketTypeAdapter extends BaseAdapter {
    private static final String TAG = "baskettypeadapter";

    Context context;



    String testItem[];

    String testPrice[];

    LayoutInflater inflater;

    //when you implement the orderbasket class!, use this constructor

    public BasketTypeAdapter(Context context, OrderBasket basket){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public BasketTypeAdapter(Context ctx, String[]ti, String[]tp){
        this.context = ctx;
        this.testItem =ti;
        this.testPrice = tp;
        inflater = LayoutInflater.from(ctx);
    }

    //make sure you put basket.size() in this method instead of testIten.length
    @Override
    public int getCount() {
        return basket.getSize();
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
        convertView = inflater.inflate(R.layout.activity_basket_view, null);
        //Log.v(TAG, "index====");
        TextView nameItem = convertView.findViewById(R.id.orderName);
        TextView itemP = convertView.findViewById(R.id.orderPrice);
        nameItem.setText(basket.getItemAtIndex(position).toString());
        itemP.setText("$".concat(String.valueOf(basket.getItemAtIndex(position).getCost())));
        return convertView;
    }
}
