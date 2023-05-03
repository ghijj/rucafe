package com.example.project5;

import static com.example.project5.MainActivity.basket;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class CoffeeListView extends AppCompatActivity {
    private static final String TAG = "coffeelistview";
    Button back2;
    Button add;
    Spinner sizeView, countView;
    TextView subTotalPrice;
    CheckBox c1, c2, c3, c4, c5;
    double coffeePrice = 0;
    private boolean[] addins = new boolean[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_list_view);
        back2 = findViewById(R.id.back2);
        if(basket == null){
            basket = new OrderBasket();
        }
        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CoffeeListView.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        add = findViewById(R.id.addCoffeeButton);
        add.setOnClickListener(new View.OnClickListener() { //add coffees to basket
            @Override
            public void onClick(View v){
                AlertDialog.Builder builder = new AlertDialog.Builder(CoffeeListView.this);
                builder.setMessage("Do you want to add these items to the basket?");
                builder.setTitle("Confirmation");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                    countView = (Spinner) findViewById(R.id.quanBox);
                    sizeView = (Spinner) findViewById(R.id.sizeSelect);
                    c1 = (CheckBox) findViewById(R.id.SweetCream);
                    c2 = (CheckBox) findViewById(R.id.frenchVanilla);
                    c3 = (CheckBox) findViewById(R.id.irishCream);
                    c4 = (CheckBox) findViewById(R.id.caramel);
                    c5 = (CheckBox) findViewById(R.id.Mocha);

                    int quantity = Integer.parseInt((String)countView.getSelectedItem());
                    String size = (String) sizeView.getSelectedItem();

                    addins[0] = c1.isChecked();
                    addins[1] = c2.isChecked();
                    addins[2] = c3.isChecked();
                    addins[3] = c4.isChecked();
                    addins[4] = c5.isChecked();
                    int s = 1;
                    if(size.equalsIgnoreCase("Short")){
                        s = 1;
                    } else if(size.equalsIgnoreCase("Tall")){
                        s = 2;
                    } else if(size.equalsIgnoreCase("Grande")){
                        s = 3;
                    } else if(size.equalsIgnoreCase("Venti")){
                        s = 4;
                    }
                    int identicals = 0;
                    Coffee coffee = new Coffee(s, addins);
                    for(int i = 0; i < basket.getSize(); i++){
                        if(basket.getItemAtIndex(i).equals(coffee)){
                            identicals++;
                        }
                    }
                    for(int i = 0; i < quantity; i++){
                        if(identicals > 4){
                            break;
                        }
                        coffee = new Coffee(s, addins);
                        basket.addItem(coffee);
                        coffeePrice = coffeePrice + coffee.getCost();
                        identicals++;

                    }

                    subTotalPrice = findViewById(R.id.coffeePrice);
                    NumberFormat formatter = new DecimalFormat("#0.00");
                    subTotalPrice.setText("$".concat(formatter.format(coffeePrice)));
                    dialog.cancel();
                    //Log.v(TAG, "ASDAKJSDHASDKJASD    ".concat(String.valueOf(quantity)));
                    dialog.cancel();
                });
                builder.setNegativeButton("No!", (DialogInterface.OnClickListener) (dialog, which) ->{
                    //don't add the donuts to the basket!
                    dialog.cancel();
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });


        sizeView = findViewById(R.id.sizeSelect);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.coffeeSize, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeView.setAdapter(adapter);
        countView = findViewById(R.id.quanBox);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.coffeeCount, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countView.setAdapter(adapter2);
    }

}