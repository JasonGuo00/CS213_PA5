package cafeapp;

/**
 * Super class for the Coffee and Donut.  Is an abstract class.
 * @author Jason Guo, Russel Rivera
 */
public abstract class MenuItem {
    /**
     * Abstract method for obtaining the price of the menu item.
     * @return Price of the menu item.
     */
    public abstract double itemPrice();

    /**
     * Abstract method for obtaining the quantity requested of the menu item.
     * @return Quantity of the menu item.
     */
    public abstract int getQuantity();
}
