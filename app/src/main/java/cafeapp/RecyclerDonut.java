package cafeapp;

import android.graphics.drawable.Drawable;
import android.media.Image;

import com.example.rucafe.MainActivity;

import java.util.Objects;

public class RecyclerDonut {
    private String donutType;
    private String donutFlavor;
    private String img_name;

    public RecyclerDonut(String donutType, String donutFlavor, String img_name) {
        this.donutType = donutType;
        this.donutFlavor = donutFlavor;
        this.img_name = img_name;
    }

    @Override
    public String toString() {
        return donutType + " [" + donutFlavor + "]";
    }
}
