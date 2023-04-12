package com.example.rucafe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cafeapp.RecyclerClickListener;
import cafeapp.RecyclerDonut;


public class DonutItemAdapter extends RecyclerView.Adapter<DonutItem>{
    private ArrayList<RecyclerDonut> donut_options;
    private Context context;

    private RecyclerClickListener listener;

    public DonutItemAdapter(Context context, ArrayList<RecyclerDonut> list, RecyclerClickListener listener) {
        this.context = context;
        donut_options = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DonutItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.donut_item_layout, parent, false);
        return new DonutItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonutItem holder, int position) {
        holder.donut_desc.setText(donut_options.get(position).toString());
        holder.donut_img.setImageResource(donut_options.get(position).getImg_id());
        holder.set_listener(position, listener);
    }

    @Override
    public int getItemCount() {
        return donut_options.size();
    }
}
