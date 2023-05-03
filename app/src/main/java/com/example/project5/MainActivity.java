package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button donutNav, coffeeNav, basketNav, orderNav;

    public static OrderBasket basket;
    public static Orders orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        OrderBasket basket = new OrderBasket();
        Orders orders = new Orders();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        donutNav = (Button) findViewById(R.id.donutNav);
        donutNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DonutActivity.class);
                startActivity(intent);
                finish();
            }
        });
        coffeeNav = findViewById(R.id.button2);
        coffeeNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CoffeeListView.class);
                startActivity(intent);
                finish();
            }
        });
        basketNav = findViewById(R.id.button3);
        basketNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BasketActivity.class);
                startActivity(intent);
                finish();
            }
        });
        orderNav = findViewById(R.id.button4);
        orderNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


}