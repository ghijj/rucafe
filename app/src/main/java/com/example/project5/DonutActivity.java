package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class DonutActivity extends AppCompatActivity {
    String[] typeList = {"Yeast Donut", "Cake Donut", "Donut Hole"};
    String[] typeNum = {"6 flavors", "3 flavors", "3 flavors"};
    int[] typeImages = {R.drawable.yeast, R.drawable.cake, R.drawable.hole};

    public String selectedType = "";

    Button menu1;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut);
        listView = findViewById(R.id.donutTypeView);
        DonutTypeAdapter adapter = new DonutTypeAdapter(getApplicationContext(), typeList, typeNum, typeImages);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DonutActivity.this, DonutRecycleView.class);
                selectedType = typeList[position];
                intent.putExtra("send", selectedType);
                startActivity(intent);
            }
        });


        menu1= findViewById(R.id.menu1);
        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DonutActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}