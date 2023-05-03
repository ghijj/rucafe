package com.example.project5;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class FlavorView extends RecyclerView.ViewHolder {
    ImageView ImageView;
    TextView name;
    EditText editText;
    View view;

    public FlavorView (View itemView){
        super(itemView);
        ImageView = itemView.findViewById(R.id.flavorIcon);
        name = itemView.findViewById(R.id.donutFlavor);
        editText = itemView.findViewById(R.id.editTextNumber);
        view = itemView;
    }
}
