package com.example.rucafe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

import cafeapp.Donut;
import cafeapp.Order;
import cafeapp.RecyclerClickListener;
import cafeapp.RecyclerDonut;

/**
 * Fragment that acts as the screen for ordering Donuts.
 * @author Jason Guo, Russel Rivera
 */
public class DonutFragment extends Fragment implements RecyclerClickListener {
    private RecyclerView recyclerView;
    private ArrayList<RecyclerDonut> recycler_list;
    private ArrayList<Donut> selected_donuts;
    private Spinner quantity;
    private Button add_donut, order_button;
    private ListView listView;
    private TextView total;
    private int selected_position;

    /**
     * Required public constructor
     */
    public DonutFragment() {
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
        selected_position = -1;
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_donut, container, false);
        // Recycler
        createRecycler(view);
        // Spinner
        createSpinner(view);
        // Selected Donut ListView
        ArrayAdapter<Donut> select_donuts_adapter = createListView(view);
        // Add Donut
        createAddButton(view, select_donuts_adapter);
        // Make Order
        createOrderButton(view, select_donuts_adapter);
        // Set initial price text box
        total = view.findViewById(R.id.donut_tprice);
        updatePrice();
        return view;
    }

    /**
     * Creates and displays a RecyclerView
     * @param view Main view
     */
    private void createRecycler(View view) {
        recycler_list = recycler_setup();
        recyclerView = view.findViewById(R.id.recycler);
        DonutItemAdapter adapter = new DonutItemAdapter(getContext(), recycler_list, this);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL ));
    }

    /**
     * Creates and displays a Spinner
     * @param view Main view
     */
    private void createSpinner(View view) {
        quantity = view.findViewById(R.id.quantity);
        ArrayAdapter<Integer> spinner_adapter = new ArrayAdapter<Integer>(getContext(), android.R.layout.simple_spinner_item, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12});
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantity.setAdapter(spinner_adapter);
        quantity.setSelection(0);
    }

    /**
     * Creates and displays a ListView
     * @param view Main view
     * @return The adapter that the ListView is set to
     */
    private ArrayAdapter<Donut> createListView(View view) {
        listView = view.findViewById(R.id.donut_listview);
        selected_donuts = new ArrayList<Donut>();
        ArrayAdapter<Donut> list_adapter = new ArrayAdapter<Donut>(getContext(), android.R.layout.simple_list_item_1, selected_donuts);
        listView.setAdapter(list_adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                remove_donut(list_adapter, i);
            }
        });
        return list_adapter;
    }

    /**
     * Creates the button that adds selected donuts to the ListView
     * @param view Main view
     * @param adapter ListView adapter
     */
    private void createAddButton(View view, ArrayAdapter<Donut> adapter) {
        add_donut = view.findViewById(R.id.add_donut);
        add_donut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choose_donut(adapter);
            }
        });
    }

    /**
     * Creates the button that adds the donuts in the ListView to the order
     * @param view Main view
     * @param adapter ListView adapter
     */
    private void createOrderButton(View view, ArrayAdapter<Donut> adapter){
        order_button = view.findViewById(R.id.donut_order_button);
        order_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToOrder(adapter);
            }
        });
    }

    /**
     * Updates the TextViews to show the total price of donuts in the ListView
     */
    private void updatePrice() {
        double price = 0;
        if(selected_donuts.isEmpty()) {
            total.setText("Total: $0.00");
            return;
        }
        for(Donut d : selected_donuts) {
            price += d.itemPrice()*d.getQuantity();
        }
        DecimalFormat f = new DecimalFormat("##.00");
        total.setText("Total: $" + f.format(price));
    }

    /**
     * Creates the list of items (RecyclerDonuts) that are to be displayed in the RecyclerView
     * @return An ArrayList holding the types of donuts users can buy
     */
    private ArrayList<RecyclerDonut> recycler_setup() {
        ArrayList<RecyclerDonut> donut_options = new ArrayList<>();
        donut_options.add(new RecyclerDonut("Yeast Donut", "Plain", R.drawable.yeast_plain));
        donut_options.add(new RecyclerDonut("Yeast Donut", "Glazed", R.drawable.yeast_glazed));
        donut_options.add(new RecyclerDonut("Yeast Donut", "Vanilla", R.drawable.yeast_vanilla));
        donut_options.add(new RecyclerDonut("Yeast Donut", "Chocolate", R.drawable.yeast_chocolate));
        donut_options.add(new RecyclerDonut("Yeast Donut", "Strawberry", R.drawable.yeast_strawberry));
        donut_options.add(new RecyclerDonut("Yeast Donut", "Jelly", R.drawable.yeast_jelly));
        donut_options.add(new RecyclerDonut("Cake Donut", "Plain", R.drawable.cake_plain));
        donut_options.add(new RecyclerDonut("Cake Donut", "Glazed", R.drawable.cake_glazed));
        donut_options.add(new RecyclerDonut("Cake Donut", "Vanilla", R.drawable.vanilla_cake));
        donut_options.add(new RecyclerDonut("Cake Donut", "Chocolate", R.drawable.cake_chocolate));
        donut_options.add(new RecyclerDonut("Donut Hole", "Plain", R.drawable.plain_hole));
        donut_options.add(new RecyclerDonut("Donut Hole", "Glazed", R.drawable.glazed_hole));
        donut_options.add(new RecyclerDonut("Donut Hole", "Vanilla", R.drawable.vanilla_hole));
        donut_options.add(new RecyclerDonut("Donut Hole", "Chocolate", R.drawable.chocolate_hole));
        return donut_options;
    }

    /**
     * Adds a donut from the RecyclerView to the ListView
     * @param adapter ListView adapter
     */
    void choose_donut(ArrayAdapter<Donut> adapter) {
        if(selected_position == -1) {
            Toast.makeText(getContext(), "Please first select a donut!", Toast.LENGTH_SHORT).show();
        }
        // Don't need to worry about no quantity: default set to 1
        else {
            Donut donut = new Donut(recycler_list.get(selected_position).getDonutType(), recycler_list.get(selected_position).getDonutFlavor(), (Integer) quantity.getSelectedItem());
            if(selected_donuts.contains(donut)) {
                selected_donuts.get(selected_donuts.indexOf(donut)).addDonuts(donut.getQuantity());
            }
            else {
                selected_donuts.add(donut);
            }
            adapter.notifyDataSetChanged();
            updatePrice();
            Toast.makeText(getContext(), donut.toString() + " added!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Remove a donut from the ListView when an entry is clicked on
     * @param adapter ListView adapter
     * @param position Position of the target donut in the ListView
     */
    void remove_donut(ArrayAdapter<Donut> adapter, int position) {
        Donut removing = selected_donuts.get(position);
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setTitle("Remove from list?");
        alert.setMessage(removing.toString());
        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                selected_donuts.remove(removing);
                adapter.notifyDataSetChanged();
                updatePrice();
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

    /**
     * Implement of the DonutClick function from the RecyclerClickListener interface.  Allows user to select donuts
     * from the RecyclerView by clicking an entry.
     * @param position Position of the selected donut from the RecyclerView
     */
    @Override
    public void DonutClick(int position) {
        selected_position = position;
        Toast.makeText(getContext(), recycler_list.get(position).toString() + " selected!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Adds donuts from the ListView to the order, incrementing if the donut already exists
     * @param adapter ListView adapter
     */
    private void addToOrder(ArrayAdapter<Donut> adapter) {
        if(selected_donuts.isEmpty()) {
            Toast.makeText(getContext(), "Please select some donuts before adding to order!", Toast.LENGTH_SHORT).show();
            return;
        }
        for(Donut donut : selected_donuts) {
            if(Order.getGlobal().contains(donut)) {
                Donut existing_donut = (Donut) Order.getGlobal().get(Order.getGlobal().indexOf(donut));
                existing_donut.addDonuts(donut.getQuantity());
            }
            else {
                Order.addItem(donut);
                System.out.println("This was ran");
            }
        }
        System.out.println(Order.getGlobal().toString());
        Toast.makeText(getContext(), "Donuts added to your order!", Toast.LENGTH_SHORT).show();
        selected_donuts.clear();
        adapter.notifyDataSetChanged();
        updatePrice();
    }
}