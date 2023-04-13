package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Main Activity Controller of the app
 * @author Jason Guo, Russel Rivera
 */
public class MainActivity extends AppCompatActivity {
    private ImageView donut, coffee, basket, list;
    // Fragment Manager
    private FragmentManager fragmentManager;

    /**
     * onCreate() method of the main activity.
     * @param savedInstanceState If the screen is being re-created from
     *      * a previous saved state, this is the state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Buttons
        donut = findViewById(R.id.donut);
        coffee = findViewById(R.id.coffee);
        basket = findViewById(R.id.basket);
        list = findViewById(R.id.list);

        fragmentManager = getSupportFragmentManager();
    }

    /**
     * Transition method to show the DonutFragment
     * @param view Main view
     */
    public void transition_donut(View view) {
        findViewById(R.id.home).setVisibility(View.INVISIBLE);
        DonutFragment donut_frag = (DonutFragment)fragmentManager.findFragmentByTag("DonutFragment");
        if(donut_frag == null) {
            donut_frag = new DonutFragment();
            FragmentTransaction fragmentTransaction  = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_view, donut_frag, "DonutFragment");
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    /**
     * Transition method to show the CoffeeFragment
     * @param view Main view
     */
    public void transition_coffee(View view) {
        findViewById(R.id.home).setVisibility(View.INVISIBLE);
        CoffeeFragment coffee_frag = (CoffeeFragment) fragmentManager.findFragmentByTag("CoffeeFragment");
        if(coffee_frag == null) {
            coffee_frag = new CoffeeFragment();
            FragmentTransaction fragmentTransaction  = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_view, coffee_frag, "CoffeeFragment");
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    /**
     * Transition method to show the OrderFragment
     * @param view Main view
     */
    public void transition_basket(View view) {
        findViewById(R.id.home).setVisibility(View.INVISIBLE);
        OrderFragment order_frag = (OrderFragment) fragmentManager.findFragmentByTag("OrderFragment");
        if(order_frag == null) {
            order_frag = new OrderFragment();
            FragmentTransaction fragmentTransaction  = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_view, order_frag, "OrderFragment");
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    /**
     * Transition method to show the ShoplistFragment
     * @param view Main view
     */
    public void transition_shoplist(View view) {
        findViewById(R.id.home).setVisibility(View.INVISIBLE);
        ShoplistFragment shoplist_frag = (ShoplistFragment) fragmentManager.findFragmentByTag("ShoplistFragment");
        if(shoplist_frag == null) {
            shoplist_frag = new ShoplistFragment();
            FragmentTransaction fragmentTransaction  = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_view, shoplist_frag, "ShoplistFrag");
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    /**
     * Handles transitioning back to the main activity page when clicking the back button
     */
    @Override
    public void onBackPressed() {
        findViewById(R.id.home).setVisibility(View.VISIBLE);
        super.onBackPressed();
    }

}