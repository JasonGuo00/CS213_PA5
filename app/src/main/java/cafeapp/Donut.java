package cafeapp;

import java.util.ArrayList;

/**
 * Class representing Donut objects.
 * @author Jason Guo, Russel Rivera
 */
public class Donut extends MenuItem {
    /**
     * Type of the donut (yeast, cake, or hole).
     */
    private String donutType;

    /**
     * Flavor of the donut
     */
    private String donutFlavor;
    /**
     * Quantity of the donut
     */
    private int donutQuantity;

    /**
     * Constructor for the donut with a given type and flavor.
     * @param type Type of the donut.
     * @param donutFlavor Flavor of the donut.
     */
    public Donut(String type, String donutFlavor, int quantity) {
        donutType = type;
        this.donutFlavor = donutFlavor;
        donutQuantity = quantity;
    }

    /**
     * Set the donut type.
     * @param type Type of the donut to change to.
     */
    public void setDonutType(String type) {
        donutType = type;
    }

    /**
     * Return the type of the donut as an integer
     * @return
     */
    public String getDonutType() {
        return donutType;
    }

    /**
     * Set the donut flavor.
     * @param flavor Flavor of the donut to be set as.
     */
    public void setDonutFlavor(String flavor) {
        donutFlavor = flavor;
    }

    /**
     * Get the donut flavor.
     * @return Donut flavor as a String.
     */
    public String getDonutFlavor() {
        return donutFlavor;
    }

    public void addDonuts(int amt) {
        donutQuantity += amt;
    }
    @Override
    public int getQuantity() {
        return donutQuantity;
    }

    /**
     * Obtain the price of the donut.
     * @return The price of the donut.
     */
    @Override
    public double itemPrice() {
        double price;
        switch(donutType) {
            case Constants.DONUT_YEAST:
                price = Constants.DONUT_YEAST_PRICE;
                break;
            case Constants.DONUT_CAKE:
                price = Constants.DONUT_CAKE_PRICE;
                break;
            case Constants.DONUT_HOLE:
                price = Constants.DONUT_HOLE_PRICE;
                break;
            default:
                price = -1;
                break;
        }
        return price;
    }

    /**
     * Override the equals method for Donut.  Two donuts are equivalent if they are of the same type and flavor.
     * @param obj Object to compare to.
     * @return true or false.
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Donut x) {
            return (this.getDonutType().equals(x.getDonutType()) && this.getDonutFlavor().equals(x.getDonutFlavor()));
        }
        return false;
    }

    /**
     * Overriding toString method.
     * @return Donut along with its flavor
     */
    @Override
    public String toString() {
        return donutType + " [" + donutFlavor + "] x" +donutQuantity;
    }

}
