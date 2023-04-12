package cafeapp;

import java.util.ArrayList;

/**
 * Class containing the list of all order for the cafe.
 * @author Jason Guo, Russel Rivera
 */
public class ShopList {
    /**
     * The list containing all orders for the cafe.
     */
    private ArrayList<Order> orderList;

    /**
     * Constructor to initialize the orderList.
     */
    public ShopList() {
        orderList = new ArrayList<>();
    }

    /**
     * Add an order to the list.
     * @param order Order to be added.
     */
    public void addOrder(Order order) {
        orderList.add(order);
    }

    /**
     * Remove an order to the list.
     * @param order Order to be removed.
     */
    public void removeOrder(Order order) {
        orderList.remove(order);
    }

    /**
     * Returns the list of all orders made.
     * @return list of orders
     */
    public ArrayList<Order> getOrderList() {
        return orderList;
    }
}
