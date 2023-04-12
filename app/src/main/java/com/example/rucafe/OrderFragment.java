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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cafeapp.Donut;
import cafeapp.MenuItem;
import cafeapp.Order;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<MenuItem> olist;
    private ListView order_listview;
    private TextView order_header;

    public OrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderFragment newInstance(String param1, String param2) {
        OrderFragment fragment = new OrderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        // Header
        order_header = view.findViewById(R.id.order_header);
        order_header.setText("Order #" + Order.getPosition());
        // Create a listview, populate with Order.getGlobal() list
        ArrayAdapter<MenuItem> adapter = createListView(view);

        return view;
    }

    private ArrayAdapter<MenuItem> createListView(View view) {
        olist = Order.getGlobal();
        order_listview = view.findViewById(R.id.order_listview);
        ArrayAdapter<MenuItem> list_adapter = new ArrayAdapter<MenuItem>(getContext(), android.R.layout.simple_list_item_1, olist);
        order_listview.setAdapter(list_adapter);
        order_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                remove_item(list_adapter, i);
            }
        });
        return list_adapter;
    }

    void remove_item(ArrayAdapter<MenuItem> adapter, int position) {
        MenuItem removing = olist.get(position);
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setTitle("Remove from list?");
        alert.setMessage(removing.toString());
        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Order.removeItem(removing);
                olist.remove(removing);
                adapter.notifyDataSetChanged();
//                updatePrice();
                Toast.makeText(getContext(), removing.toString() + " removed!", Toast.LENGTH_SHORT).show();
            }
            //handle the "NO" click
        }).setNegativeButton("no", new DialogInterface.OnClickListener() {
            // Do nothing
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }
}