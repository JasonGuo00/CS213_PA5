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

/**
 * Adapter for the RecyclerView in the DonutFragment
 * @author Jason Guo, Russel Rivera
 */
public class DonutItemAdapter extends RecyclerView.Adapter<DonutItem>{
    private ArrayList<RecyclerDonut> donut_options;
    private Context context;

    private RecyclerClickListener listener;

    /**
     * Constructor for the adapter.
     * @param context Main context
     * @param list List of items to be shown
     * @param listener The listener for clicks
     */
    public DonutItemAdapter(Context context, ArrayList<RecyclerDonut> list, RecyclerClickListener listener) {
        this.context = context;
        donut_options = list;
        this.listener = listener;
    }

    /**
     * onCreateViewHolder() method of RecyclerViewAdapters
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return DonutItem ViewHolder
     */
    @NonNull
    @Override
    public DonutItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.donut_item_layout, parent, false);
        return new DonutItem(view);
    }

    /**
     * onBindViewHolder() method of RecyclerViewAdapters
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull DonutItem holder, int position) {
        holder.donut_desc.setText(donut_options.get(position).toString());
        holder.donut_img.setImageResource(donut_options.get(position).getImg_id());
        holder.set_listener(position, listener);
    }

    /**
     * getItemCount() method of RecyclerViewAdapters
     * @return number of entries in the RecyclerView
     */
    @Override
    public int getItemCount() {
        return donut_options.size();
    }
}
