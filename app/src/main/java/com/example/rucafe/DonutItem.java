package com.example.rucafe;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import cafeapp.RecyclerClickListener;

/**
 * ViewHolder for the RecyclerView
 * @author Jason Guo, Russel Rivera
 */
public class DonutItem extends RecyclerView.ViewHolder {
    ImageView donut_img;
    TextView donut_desc;

    /**
     * Constructor of the ViewHolder
     * @param view Main view
     */
    public DonutItem(View view) {
        super(view);
        donut_img = view.findViewById(R.id.imageView);
        donut_desc = view.findViewById(R.id.textView);
    }

    /**
     * Creates a listener to listen for clicks on the entries of the RecyclerView
     * @param position Position of the item in the list
     * @param listener The listener
     */
    public void set_listener(int position, RecyclerClickListener listener) {
        donut_desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.DonutClick(position);
            }
        });
    }
}
