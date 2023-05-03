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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class BasketActivity extends AppCompatActivity {
    Button menu3, addButton, remButton;
    String [] testI = {"Glazed Donut", "Jelly Donut"};
    String [] testP = {"2.19", "3.12"};
    ListView  basketList;
    TextView totalcost;
    TextView subtotal;
    TextView tax;



    MenuItem item;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        basketList = findViewById(R.id.basketListView);

        if(basket == null){
            basket = new OrderBasket();
        }
        if(MainActivity.orders == null){
            orders = new Orders();
        }

        subtotal = findViewById(R.id.basketSubTotal);
        tax = findViewById(R.id.basketSalexTax);
        totalcost = findViewById(R.id.basketTotal);
        NumberFormat formatter = new DecimalFormat("#0.00");
        String sub = "Subtotal: $";
        String t = "Sales Tax: $";
        String tc = "Total Cost: $";
        subtotal.setText(sub.concat(formatter.format(basket.getCost())));
        tax.setText(t.concat(formatter.format(basket.getCost() * 0.06625)));
        totalcost.setText(tc.concat(formatter.format(basket.getCost() + basket.getCost()*0.06625)));

        BasketTypeAdapter adapter = new BasketTypeAdapter(getApplicationContext(), basket);
        basketList.setAdapter(adapter);

        menu3= findViewById(R.id.menu3);
        menu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BasketActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        basketList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long arg3) {
                index = position;
            }
        });
        addButton = findViewById(R.id.addOrder);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BasketActivity.this);
                builder.setMessage("Do you want to add the order?");
                builder.setTitle("Confirmation");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                    if(basket.getSize() == 0){
                        Toast.makeText(getApplicationContext(), "No items in basket!", Toast.LENGTH_SHORT).show();
                    } else{
                        orders.addOrder(basket);
                        basket = new OrderBasket();
                        adapter.notifyDataSetChanged();
                        BasketTypeAdapter adapter = new BasketTypeAdapter(getApplicationContext(), basket);
                        basketList.setAdapter(adapter);
                        subtotal.setText(sub.concat(formatter.format(basket.getCost())));
                        tax.setText(t.concat(formatter.format(basket.getCost() * 0.06625)));
                        totalcost.setText(tc.concat(formatter.format(basket.getCost() + basket.getCost()*0.06625)));
                    }
                    dialog.cancel();
                });
                builder.setNegativeButton("No!", (DialogInterface.OnClickListener) (dialog, which) ->{
                    //don't add the basket to the order!
                    dialog.cancel();
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        remButton = findViewById(R.id.removeItem);
        remButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BasketActivity.this);
                builder.setMessage("Do you want to remove the item?");
                builder.setTitle("Confirmation");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                    //determine which item is selected, remove from basket
                    //remove the item from the basket!
                    if(basket.getSize() == 0){
                        Toast.makeText(getApplicationContext(), "No items in basket!", Toast.LENGTH_SHORT).show();
                    } else{
                        basket.removeItemAtIndex(index);

                        subtotal = findViewById(R.id.basketSubTotal);
                        tax = findViewById(R.id.basketSalexTax);
                        totalcost = findViewById(R.id.basketTotal);
                        NumberFormat formatter = new DecimalFormat("#0.00");
                        String sub = "Subtotal: $";
                        String t = "Sales Tax: $";
                        String tc = "Total Cost: $";
                        subtotal.setText(sub.concat(formatter.format(basket.getCost())));
                        tax.setText(t.concat(formatter.format(basket.getCost() * 0.06625)));
                        totalcost.setText(tc.concat(formatter.format(basket.getCost() + basket.getCost()*0.06625)));

                        basketList.clearChoices();
                        basketList.requestLayout();
                        adapter.notifyDataSetChanged();
                        if(index > 0){
                            index--;
                        }
                        basketList.clearChoices();
                        adapter.notifyDataSetInvalidated();
                        basketList.requestLayout();
                    }
                    dialog.cancel();
                });
                builder.setNegativeButton("No!", (DialogInterface.OnClickListener) (dialog, which) ->{
                    //don't remove the item!
                    dialog.cancel();
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });


    }
}