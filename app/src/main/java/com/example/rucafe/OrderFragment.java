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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import cafeapp.MenuItem;
import cafeapp.Order;
import cafeapp.ShopList;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment {
    private ArrayList<MenuItem> olist;
    private TextView subtotal;
    private TextView tax;
    private TextView total;
    private ArrayAdapter<MenuItem> list_adapter;

    public OrderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        // Header and other text fields
        TextView order_header = view.findViewById(R.id.order_header);
        String order_title = "Order #" + Order.getPosition();
        order_header.setText(order_title);
        subtotal = view.findViewById(R.id.subtotal);
        tax = view.findViewById(R.id.tax);
        total = view.findViewById(R.id.total);
        // Create a listview, populate with Order.getGlobal() list
        ArrayAdapter<MenuItem> adapter = createListView(view);
        // Set the price text fields
        updatePrice();

        createAddButton(view);

        return view;
    }

    private ArrayAdapter<MenuItem> createListView(View view) {
        olist = Order.getGlobal();
        ListView order_listview = view.findViewById(R.id.order_listview);
        list_adapter = new ArrayAdapter<MenuItem>(getContext(), android.R.layout.simple_list_item_1, olist);
        order_listview.setAdapter(list_adapter);
        order_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                remove_item(list_adapter, i);
            }
        });
        return list_adapter;
    }

    private void updatePrice() {
        String new_text1 = String.format(Locale.US, "$%,.2f",Order.staticSubtotal());
        String new_text2 = String.format(Locale.US, "$%,.2f",Order.staticTax());
        String new_text3 = String.format(Locale.US, "$%,.2f",Order.staticTotalPrice());
        subtotal.setText(new_text1);
        tax.setText(new_text2);
        total.setText(new_text3);
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
                updatePrice();
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

    private void createAddButton(View view) {
        Button add_order = view.findViewById(R.id.add_order);

        add_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Order.getGlobal().isEmpty()) {
                    Toast.makeText(getContext(), "Order #" + Order.getPosition() + " added!", Toast.LENGTH_SHORT).show();
                    Order order = new Order();
                    order.finalizeOrder();
                    ShopList.addOrder(order);
                    Order.getGlobal().clear();
                    olist.clear();
                    list_adapter.notifyDataSetChanged();
                    updatePrice();
                } else {
                    Toast.makeText(getContext(), "Order Empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}