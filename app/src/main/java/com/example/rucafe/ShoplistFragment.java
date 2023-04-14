package com.example.rucafe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import cafeapp.MenuItem;
import cafeapp.Order;
import cafeapp.ShopList;

/**
 * Fragment that acts as the screen for reviewing all orders
 * @author Jason Guo, Russel Rivera
 */
public class ShoplistFragment extends Fragment {
    private ArrayList<Order> olist;
    private ArrayAdapter<Order> list_adapter;


    /**
     * Required public constructor
     */
    public ShoplistFragment() {
        // Required empty public constructor
    }


    /**
     * onCreate() method for fragments
     * @param savedInstanceState If the fragment is being re-created from
     * a previous saved state, this is the state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * onCreateView() method for fragments - Creation of other Views are in here as well
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return the main view is returned
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shoplist, container, false);

        createListView(view);

        return view;
    }

    /**
     * Creates and displays a ListView for orders
     * @param view Main view
     */
    private ArrayAdapter<Order> createListView(View view) {
        olist = ShopList.getGlobal();
        ListView shop_list_listview = view.findViewById(R.id.shoplist_listview);
        list_adapter = new ArrayAdapter<Order>(getContext(), android.R.layout.simple_list_item_1, olist);
        shop_list_listview.setAdapter(list_adapter);
        shop_list_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                remove_item(list_adapter, i);
            }
        });
        return list_adapter;
    }

    /**
     * Creates and displays a ListView for orders
     * @param adapter ArrayAdapter<Order> for list view
     * @param position int position of item to remove
     */
    void remove_item(ArrayAdapter<Order> adapter, int position) {
        Order removing = olist.get(position);
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setTitle("Remove from list?");
        alert.setMessage(removing.toString());
        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                ShopList.removeOrder(removing);
                olist.remove(removing);
                adapter.notifyDataSetChanged();
                Toast.makeText(getContext(), removing.toString() + " removed!", Toast.LENGTH_SHORT).show();
            }
            //handle the "NO" click
        }).setNegativeButton("no", new DialogInterface.OnClickListener() {
            // Do nothing
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }
}