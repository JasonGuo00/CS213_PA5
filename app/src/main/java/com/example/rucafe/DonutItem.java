package com.example.rucafe;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class DonutItem extends RecyclerView.ViewHolder {
    ImageView donut_img;
    TextView donut_desc;

    public DonutItem(View view) {
        super(view);
        donut_img = view.findViewById(R.id.imageView);
        donut_desc = view.findViewById(R.id.textView);
    }
}
