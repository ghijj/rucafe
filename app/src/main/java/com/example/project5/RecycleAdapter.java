package com.example.project5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleAdapter extends RecyclerView.Adapter<FlavorView> {
    Context context;
    String[] flavors;
    int[] images;

    public RecycleAdapter (Context context, String[] flavorList, int[] img){
        this.context = context;
        this.flavors = flavorList;
        this.images = img;
    }
    @NonNull

    @Override
    public FlavorView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FlavorView(LayoutInflater.from(context).inflate(R.layout.recyclerow, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FlavorView holder, int position) {
        holder.ImageView.setImageResource(images[position]);
        holder.name.setText(flavors[position]);
    }

    @Override
    public int getItemCount() {
        return flavors.length;
    }
}
