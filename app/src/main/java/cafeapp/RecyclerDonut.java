package cafeapp;

import android.graphics.drawable.Drawable;
import android.media.Image;

import com.example.rucafe.MainActivity;

import java.util.Objects;

public class RecyclerDonut {
    private String donutType;
    private String donutFlavor;
    private int img_id;

    public RecyclerDonut(String donutType, String donutFlavor, int img_id) {
        this.donutType = donutType;
        this.donutFlavor = donutFlavor;
        this.img_id = img_id;
    }

    @Override
    public String toString() {
        return donutType + " [" + donutFlavor + "]";
    }

    public int getImg_id() {
        return img_id;
    }

    public String getDonutFlavor() {
        return donutFlavor;
    }
    public String getDonutType() {
        return donutType;
    }
}
