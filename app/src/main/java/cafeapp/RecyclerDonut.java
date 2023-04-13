package cafeapp;

import android.graphics.drawable.Drawable;
import android.media.Image;

import com.example.rucafe.MainActivity;

import java.util.Objects;

/**
 * RecyclerDonut used to create an object that will be held in the RecyclerView of the app.
 * @author Jason Guo, Russel Rivera
 */
public class RecyclerDonut {
    private String donutType;
    private String donutFlavor;
    private int img_id;

    /**
     * Constructor for the RecyclerDonut
     * @param donutType String representing donut type
     * @param donutFlavor String representing donut flavor
     * @param img_id int representing the corresponding RecyclerDonut's image ID: for the app
     */
    public RecyclerDonut(String donutType, String donutFlavor, int img_id) {
        this.donutType = donutType;
        this.donutFlavor = donutFlavor;
        this.img_id = img_id;
    }

    /**
     * toString() override.
     * @return Donut type and flavor as a string
     */
    @Override
    public String toString() {
        return donutType + " [" + donutFlavor + "]";
    }

    /**
     * Getter method to get the RecyclerDonut's image ID
     * @return int representing image ID
     */
    public int getImg_id() {
        return img_id;
    }

    /**
     * Getter method to get the RecyclerDonut's flavor
     * @return String representing donut flavor
     */
    public String getDonutFlavor() {
        return donutFlavor;
    }

    /**
     * Getter method to get the RecyclerDonut's type
     * @return String representing donut type
     */
    public String getDonutType() {
        return donutType;
    }
}
