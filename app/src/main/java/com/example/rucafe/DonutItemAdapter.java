package com.example.rucafe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cafeapp.RecyclerDonut;


public class DonutItemAdapter extends RecyclerView.Adapter<DonutItem>{
    private ArrayList<RecyclerDonut> donut_options;

    public DonutItemAdapter(ArrayList<RecyclerDonut> list) {
        donut_options = list;
    }

    @NonNull
    @Override
    public DonutItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.donut_item_layout, parent, false);
        return new DonutItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonutItem holder, int position) {
        holder.donut_desc.setText(donut_options.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
