package cafeapp;

import java.util.ArrayList;

/**
 * Class representing Coffee objects
 * @author Jason Guo, Russel Rivera
 */
public class Coffee extends MenuItem {
    /**
     * Size of the coffee
     */
    private String size;
    /**
     * Quantity of coffee
     */
    private int coffeeQuantity;
    /**
     * List of add-ins for the coffee
     */
    private ArrayList<String> addInList;

    /**
     * Constructor for a coffee object, taking a size and initializing an empty add-in list
     * @param size Size of the coffee
     */
    public Coffee(String size) {
        addInList = new ArrayList<>();
        this.size = size;
        coffeeQuantity = 0;
    }

    /**
     * Constructor for a coffee object, taking a size and initializing an empty add-in list
     * @param coffee Coffee to copy from
     */
    public Coffee(Coffee coffee) {
        addInList = (ArrayList<String>) (coffee.addInList).clone();
        this.size = coffee.getSize();
        coffeeQuantity = coffee.getQuantity();
    }

    /**
     * Setter method to change the size of the Coffee
     * @param size Size of the coffee to change to
     */
    public void changeSize(String size) {
        this.size = size;
    }

    /**
     * Obtain the price of the coffee, based on coffee size and number of add-ins
     * @return Price of the coffee
     */
    @Override
    public double itemPrice() {
        double price = 0;
        switch(size) {
            case Constants.COFFEE_VENTI:
                price += Constants.COFFEE_SIZE_INCREMENT;
            case Constants.COFFEE_GRANDE:
                price += Constants.COFFEE_SIZE_INCREMENT;
            case Constants.COFFEE_TALL:
                price += Constants.COFFEE_SIZE_INCREMENT;
            case Constants.COFFEE_SHORT:
                price += Constants.COFFEE_BASE_PRICE;
                break;
            default:
                price = -1;
                break;
        }
        price += addInList.size()*Constants.COFFEE_ADDIN;
        return price;
    }

    /**
     * Obtain the size of the coffee
     * @return Size of the coffee
     */
    public String getSize() {return size;}

    /**
     * Add topping to the add-in list.
     * @param topping The topping to add to the coffee.
     */
    public void addTopping(String topping) {
        if(topping.equalsIgnoreCase("sweet cream") ||
                topping.equalsIgnoreCase("french vanilla") ||
                topping.equalsIgnoreCase("irish cream") ||
                topping.equalsIgnoreCase("caramel") ||
                topping.equalsIgnoreCase("mocha")) {

            addInList.add(topping);
        }
        addInList.sort(null);
    }

    /**
     * Remove topping to the add-in list
     * @param topping The topping to remove.
     */
    public void removeTopping(String topping) {
        addInList.remove(topping);
        addInList.sort(null);
    }

    /**
     * Set the quantity of Coffee.
     * @param amt Number amount of coffee to set quantity to.
     */
    public void setQuantity(int amt) {
        coffeeQuantity = amt;
    }

    /**
     * Obtain the list of toppings as a string.
     * @return String of all toppings.
     */
    public String getToppings() {
        String str = "";
        for(String topping : addInList) {
            str = str + topping;
            if(addInList.indexOf(topping) < addInList.size()-1) {
                str = str + ",";
            }
        }
        return str;
    }

    /**
     * Get the quantity of coffee.
     * @return Integer representing the quantity of coffee.
     */
    @Override
    public int getQuantity() {return coffeeQuantity;};

    /**
     * Overrides the equals method.  Checks that the coffees have the same size and toppings by comparing
     * their strings.
     * @param obj Object to be compared to.
     * @return true or false.
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Coffee x) {
            return (this.getSize().equals(x.getSize()) && this.getToppings().equals(x.getToppings()));
        }
        return false;
    }

    /**
     * Overriding toString method.
     * @return Coffee along with its size and toppings
     */
    @Override
    public String toString() {
        if(addInList.isEmpty()) {
            return "Coffee [" + getSize() + "] x" + coffeeQuantity;
        }
        else {
            return "Coffee [" + getSize() + "] [" + getToppings() + "] x" + coffeeQuantity;
        }
    }
}
