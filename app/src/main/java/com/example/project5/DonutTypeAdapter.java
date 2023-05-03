package com.example.project5;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DonutTypeAdapter extends BaseAdapter {
    Context context;
    String listType[];
    String typeCount[];
    int typeImages[];
    LayoutInflater inflater;

    public DonutTypeAdapter(Context ctx, String [] typeList, String[] typeNum, int [] images){
        this.context = ctx;
        this.listType = typeList;
        this.typeCount = typeNum;
        this.typeImages = images;
        inflater = LayoutInflater.from(ctx);
    }
    @Override
    public int getCount() {
        return listType.length;
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
        convertView = inflater.inflate(R.layout.activity_donut_list_view, null);
        TextView typeName =  convertView.findViewById(R.id.donutTyoe);
        TextView flavCount =  convertView.findViewById(R.id.dountTypeNum);
        ImageView donutImg = convertView.findViewById(R.id.donutIcon);
        typeName.setText(listType[position]);
        flavCount.setText(typeCount[position]);
        donutImg.setImageResource(typeImages[position]);
        return convertView;
    }
}
