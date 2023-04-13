package cafeapp;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Class representing the order of a customer.
 * @author Jason Guo, Russel Rivera
 */
public class Order {
    /**
     * Static variable position to provide unique order numbers.
     */
    private static int position = 1;
    /**
     * Number identifier for the order.
     */
    private int orderNum;
    /**
     * List of menu items in the order.  Accessible through public methods to allow MenuItems
     * to be added from the Coffee and Donut views.
     */
    private static ArrayList<MenuItem> globalOrderList = new ArrayList<>();

    /**
     * Finalized list of MenuItems tied to the current order.
     */
    private ArrayList<MenuItem> finalOrderList;

    /**
     * Default constructor for the Order.  Assigns a unique order number and initializes orderList.
     */
    public Order() {
        orderNum = position;
        position++;
        finalOrderList = new ArrayList<>();
    }

    /**
     * Remove a menu item from the order.
     * @param item Item to be removed
     */
    public static void removeItem(MenuItem item) {
        globalOrderList.remove(item);
    }

    /**
     * Add a menu item to the order, and increment if that item is already in there.
     * Defined as a static method to allow access from the Coffee and Donut Controllers.
     * @param item Menu item to be added.
     */
    public static void addItem(MenuItem item) {
        if(!globalOrderList.contains(item)) {
            globalOrderList.add(item);
        }
        else {
            if(item instanceof  Donut) {
                ((Donut) globalOrderList.get(globalOrderList.indexOf(item))).addDonuts(item.getQuantity());
            }
            else {
                ((Coffee) globalOrderList.get(globalOrderList.indexOf(item))).setQuantity
                        (globalOrderList.get(globalOrderList.indexOf(item)).getQuantity() + item.getQuantity());
            }
        }
    }

    /**
     * Obtain the subtotal of all the items in the order.
     * @return Subtotal of the order.
     */
    public static double staticSubtotal() {
        double price = 0;
        for(MenuItem item : globalOrderList) {
            price += (item.itemPrice() * item.getQuantity());
        }
        return price;
    }

    /**
     * Obtain the tax due based on the subtotal.
     * @return Tax due on the order.
     */
    public static double staticTax() {
        return staticSubtotal() * Constants.SALES_TAX_MULTIPLIER;
    }

    /**
     * Obtain the total price of the order by adding the subtotal and tax.
     * @return Total price of the order.
     */
    public static double staticTotalPrice() {
        return staticSubtotal() + staticTax();
    }

    /**
     * Obtain the order number.
     * @return Order number.
     */
    public int getOrderNum() {return orderNum;}
    /**
     * Obtain the global order number
     * @return position
     */
    public static int getPosition() {
        return position;
    }

    /**
     * Writes all the data from the globalOrderList to the instance's finalOrderList.
     * The globalOrderList is cleared afterwards.
     */
    public void finalizeOrder() {
        finalOrderList.addAll(globalOrderList);
        globalOrderList.clear();
    }

    /**
     * Gets the global list of items in the current order
     * @return list of order items
     */
    public static ArrayList<MenuItem> getGlobal() {
        return new ArrayList<>(globalOrderList);
    }

    /**
     * Creates and returns string describing the entire order
     * @return order string
     */
    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        double total = 0;

        ret.append("Order #").append(orderNum).append("\n");

        for (MenuItem menuItem : finalOrderList) {
            ret.append(menuItem);
            ret.append("\n");
            total += menuItem.itemPrice() * menuItem.getQuantity() * (1 + Constants.SALES_TAX_MULTIPLIER);
        }

        ret.append(String.format(Locale.US, "Order Total: $%,.2f", total));

        return ret.toString();
    }
}
