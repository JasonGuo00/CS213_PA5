package com.example.rucafe;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import cafeapp.Coffee;
import cafeapp.Constants;
import cafeapp.Order;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CoffeeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CoffeeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Spinner quantity;
    private Button add_coffee;
    private TextView text_view;
    private Boolean sweet_cream = false;
    private Boolean french_vanilla = false;
    private Boolean irish_cream = false;
    private Boolean caramel = false;
    private Boolean mocha = false;
    private Coffee coffee = new Coffee("Short");

    public CoffeeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CoffeeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CoffeeFragment newInstance(String param1, String param2) {
        CoffeeFragment fragment = new CoffeeFragment();
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
        View view = inflater.inflate(R.layout.fragment_coffee, container, false);

        createSpinner(view);
        createAddButton(view);
        createCheckBoxSweetCream(view);
        createCheckBoxFrenchVanilla(view);
        createCheckBoxIrishCream(view);
        createCheckBoxCaramel(view);
        createCheckBoxMocha(view);
        createTotalLabel(view);
        createRadioButtonSmall(view);
        createRadioButtonTall(view);
        createRadioButtonGrande(view);
        createRadioButtonVenti(view);
        updatePrice();

        return view;
    }

    private void createTotalLabel(View view) {
        text_view = view.findViewById(R.id.coffee_total);
    }

    private void createAddButton(View view) {
        add_coffee = view.findViewById(R.id.add_coffee);

        add_coffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Order.addItem(coffee);
                Toast.makeText(getContext(), coffee.toString() + " added!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createSpinner(View view) {
        quantity = view.findViewById(R.id.coffee_quantity);
        ArrayAdapter<Integer> spinner_adapter = new ArrayAdapter<Integer>(getContext(), android.R.layout.simple_spinner_item, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12});
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantity.setAdapter(spinner_adapter);
        quantity.setSelection(0);

        quantity.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                coffee.setQuantity(Integer.parseInt(parentView.getItemAtPosition(position).toString()));
                updatePrice();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });
    }

    private void createRadioButtonSmall(View view) {
        RadioButton radioButtonSmall = view.findViewById(R.id.radio_button_small);
        radioButtonSmall.setChecked(true);

        radioButtonSmall.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    coffee.changeSize(Constants.COFFEE_SHORT);
                    updatePrice();
                }
            }
        });
    }

    private void createRadioButtonTall(View view) {
        RadioButton radioButtonTall = view.findViewById(R.id.radio_button_tall);

        radioButtonTall.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    coffee.changeSize(Constants.COFFEE_TALL);
                    updatePrice();
                }
            }
        });
    }

    private void createRadioButtonGrande(View view) {
        RadioButton radioButtonGrande = view.findViewById(R.id.radio_button_grande);

        radioButtonGrande.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    coffee.changeSize(Constants.COFFEE_GRANDE);
                    updatePrice();
                }
            }
        });
    }

    private void createRadioButtonVenti(View view) {
        RadioButton radioButtonVenti = view.findViewById(R.id.radio_button_venti);

        radioButtonVenti.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    coffee.changeSize(Constants.COFFEE_VENTI);
                    updatePrice();
                }
            }
        });
    }

    private void createCheckBoxSweetCream(View view) {
        CheckBox check_box_sweet_cream = view.findViewById(R.id.check_box_sweet_cream);

        check_box_sweet_cream.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (check_box_sweet_cream.isChecked()) {
                    coffee.addTopping("Sweet Cream");
                } else {
                    coffee.removeTopping("Sweet Cream");
                }
                updatePrice();
            }
        });
    }

    private void createCheckBoxFrenchVanilla(View view) {
        CheckBox check_box_french_vanilla = view.findViewById(R.id.check_box_french_vanilla);

        check_box_french_vanilla.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (check_box_french_vanilla.isChecked()) {
                    coffee.addTopping("French Vanilla");
                } else {
                    coffee.removeTopping("French Vanilla");
                }
                updatePrice();
            }
        });
    }

    private void createCheckBoxIrishCream(View view) {
        CheckBox check_box_irish_cream = view.findViewById(R.id.check_box_irish_cream);

        check_box_irish_cream.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (check_box_irish_cream.isChecked()) {
                    coffee.addTopping("Irish Cream");
                } else {
                    coffee.removeTopping("Irish Cream");
                }
                updatePrice();
            }
        });
    }

    private void createCheckBoxCaramel(View view) {
        CheckBox check_box_caramel = view.findViewById(R.id.check_box_caramel);

        check_box_caramel.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (check_box_caramel.isChecked()) {
                    coffee.addTopping("Caramel");
                } else {
                    coffee.removeTopping("Caramel");
                }
                updatePrice();
            }
        });
    }

    private void createCheckBoxMocha(View view) {
        CheckBox check_box_mocha = view.findViewById(R.id.check_box_mocha);

        check_box_mocha.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (check_box_mocha.isChecked()) {
                    coffee.addTopping("Mocha");
                } else {
                    coffee.removeTopping("Mocha");
                }
                updatePrice();
            }
        });
    }

    private void updatePrice() {
        double price = coffee.itemPrice()*coffee.getQuantity();
        DecimalFormat f = new DecimalFormat("##.00");
        String string_price = "$" + f.format(price);
        text_view.setText(string_price);
    }
}