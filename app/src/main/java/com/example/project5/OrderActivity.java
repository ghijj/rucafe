package com.example.project5;

import static com.example.project5.MainActivity.basket;
import static com.example.project5.MainActivity.orders;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
    Button menu4;
    ListView list;

    /*String[] dawg = {"Glazed Donut \nShort Coffee", "Bruh \nMoment \nLMAO"};
    String[] pound = {"4.00", "69.00"};



    String [] testI = {"Glazed Donut", "Jelly Donut"};
    String [] testP = {"2.19", "3.12"};
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        if(basket == null){
            basket = new OrderBasket();
        }
        if(MainActivity.orders == null){
            orders = new Orders();
        }
        list = findViewById(R.id.orderListView);
        OrderTypeAdapter adapter = new OrderTypeAdapter(getApplicationContext());
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(OrderActivity.this);
                builder.setMessage("Do you want to remove the order?");
                builder.setTitle("Confirmation");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                    //Remove the order!
                    if(orders.getSize() == 0){
                        Toast.makeText(getApplicationContext(), "No orders!", Toast.LENGTH_SHORT).show();
                    }else{
                        orders.removeOrder(orders.getBasketAtIndex(position));
                        adapter.notifyDataSetChanged();
                        OrderTypeAdapter adapter = new OrderTypeAdapter(getApplicationContext());
                        list.setAdapter(adapter);
                        Toast.makeText(getApplicationContext(), "Order Removed!", Toast.LENGTH_SHORT).show();
                    }
                    dialog.cancel();
                });
                builder.setNegativeButton("No!", (DialogInterface.OnClickListener) (dialog, which) ->{
                    //don't remove the order!
                    dialog.cancel();
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        menu4= findViewById(R.id.menu4);
        menu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}