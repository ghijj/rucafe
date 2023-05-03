package com.example.project5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import static com.example.project5.MainActivity.basket;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.number.NumberFormatter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Console;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DonutRecycleView extends AppCompatActivity {

    private static final String TAG = "donutrecycleview";
    TextView typeTitle;
    TextView subTotalPrice;
    Button back;
    Button add;

    double donutSubTotal = 0;


    int quantity;
    RecyclerView recyclerView;
    String[] yeastFlavors = {"Glazed", "Powdered", "Chocolate-Coated", "Chocolate-Glazed", "Pistachio", "Coconut"};
    int[] yeastPic = {R.drawable.y_glazed, R.drawable.y_powdered, R.drawable.y_chocolate_coat, R.drawable.y_choc_glazed, R.drawable.y_pist, R.drawable.y_coconut};

    String[] cakeFlavors = {"Baked Lemon", "Powdered", "Vanilla-Frosted"};
    int[] cakePic = {R.drawable.c_lemon, R.drawable.c_powder, R.drawable.c_vanilla};

    String[] holeFlavors = {"Jelly", "Chocolate-Frosted", "Glazed"};
    int[] holePic ={R.drawable.h_jelly, R.drawable.h_chocolate, R.drawable.h_glazed};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut_recycle_view);
        Intent receiverIntent = getIntent();
        String title = receiverIntent.getStringExtra("send");
        typeTitle = findViewById(R.id.typeSelect);
        typeTitle.setText(title);
        displayList(title);
        if(basket == null){
            basket = new OrderBasket();
        }

        subTotalPrice = findViewById(R.id.priceDonut);
        NumberFormat formatter = new DecimalFormat("#0.00");
        subTotalPrice.setText("$".concat(formatter.format(donutSubTotal)));
        back= findViewById(R.id.backButt);
        back.setOnClickListener(new View.OnClickListener() { //go back
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DonutRecycleView.this, DonutActivity.class);

                startActivity(intent);
                finish();
            }
        });
        add = findViewById(R.id.addDonutsButton);
        add.setOnClickListener(new View.OnClickListener() { //add donuts to basket
            @Override
            public void onClick(View v){
                AlertDialog.Builder builder = new AlertDialog.Builder(DonutRecycleView.this);
                builder.setMessage("Do you want to add these items to the basket?");
                builder.setTitle("Confirmation");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                    if(title.equals("Yeast Donut")){
                        for(int i = 0; i < 6; i++){
                            String input = "";
                            try{
                                input = (((EditText)Objects.requireNonNull(Objects.requireNonNull(recyclerView.getLayoutManager()).findViewByPosition(i)).findViewById(R.id.editTextNumber)).getText().toString());
                            } catch(Exception ignored){}
                            //.v(TAG, "index====" + input);
                            if (input.equals("")) {
                                continue;
                            }
                            quantity = Integer.parseInt(input);
                            for(int j = 0; j < quantity; j++){
                                Donut donut = new Donut("Yeast", yeastFlavors[i]);
                                donutSubTotal = donutSubTotal + donut.getCost();
                                basket.addItem(donut);
                            }
                        }
                    } else if(title.equals("Cake Donut")){
                        for(int i = 0; i < 3; i++){
                            String input = "";
                            try{
                                input = (((EditText)Objects.requireNonNull(Objects.requireNonNull(recyclerView.getLayoutManager()).findViewByPosition(i)).findViewById(R.id.editTextNumber)).getText().toString());
                            } catch(Exception ignored){}
                            if (input.equals("")) {
                                continue;
                            }
                            quantity = Integer.parseInt(input);
                            for(int j = 0; j < quantity; j++){
                                Donut donut = new Donut("Cake", cakeFlavors[i]);
                                donutSubTotal = donutSubTotal + donut.getCost();
                                basket.addItem(donut);
                            }
                        }
                    } else{
                        for(int i = 0; i < 3; i++){
                            String input = "";
                            try{
                                input = (((EditText)Objects.requireNonNull(Objects.requireNonNull(recyclerView.getLayoutManager()).findViewByPosition(i)).findViewById(R.id.editTextNumber)).getText().toString());
                            } catch(Exception ignored){}
                            if (input.equals("")) {
                                continue;
                            }
                            quantity = Integer.parseInt(input);
                            //Log.v(TAG, "index====" + quantity);
                            for(int j = 0; j < quantity; j++){
                                Donut donut = new Donut("Hole", holeFlavors[i]);
                                donutSubTotal = donutSubTotal + donut.getCost();
                                basket.addItem(donut);
                            }
                        }
                    }
                    subTotalPrice = findViewById(R.id.priceDonut);
                    NumberFormat formatter = new DecimalFormat("#0.00");
                    subTotalPrice.setText("$".concat(formatter.format(donutSubTotal)));
                    dialog.cancel();
                });
                builder.setNegativeButton("No!", (DialogInterface.OnClickListener) (dialog, which) ->{
                    //don't add the donuts to the basket!
                    dialog.cancel();
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                //int donutsEntered = Integer.parseInt(editText.getText().toString());

            }
        });


    }

    public void displayList(String t){
        if(t.equals("Yeast")){
            recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new RecycleAdapter(getApplicationContext(), yeastFlavors, yeastPic));

        } else if (t.equals("Cake")){
            recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new RecycleAdapter(getApplicationContext(), cakeFlavors, cakePic));
        } else{
            recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new RecycleAdapter(getApplicationContext(), holeFlavors, holePic));
        }
    }

    public int checkQuantity(EditText et){
        String enteredData = et.getText().toString();
        if(enteredData.isEmpty()){
            return 0;
        } else{
            return Integer.parseInt(enteredData);
        }
    }

}

