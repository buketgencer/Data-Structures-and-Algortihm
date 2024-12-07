/**
 * This class represents a headphone device. It implements the Device interface.
 * @author BuketGencer
 */

public class Headphone implements Device{
    private final String category = "Headphone";
    private String name;
    private double price;
    private int quantity;

    /**
     * Initializes a newly created Headphone object with the given parameters.
     * @param name the name of the headphone
     * @param price the price of the headphone
     * @param quantity the quantity of the headphone
     */

    public Headphone(String name, double price, int quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /*
     * Returns the category of the headphone.
     * @return the category of the headphone
     * time complexity: O(1)
     */
    @Override
    public String getCategory() {
        return category;
    }

    /*
     * Returns the name of the headphone.
     * @return the name of the headphone
     * time complexity: O(1)
     */
    @Override
    public String getName() {
        return name;
    }

    /*
     * Sets the name of the headphone.
     * @param name the name of the headphone
     * time complexity: O(1)
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the price of the headphone.
     * @return the price of the headphone
     * time complexity: O(1)
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the headphone.
     * @param price the price of the headphone
     * time complexity: O(1)
     */
    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns the quantity of the headphone.
     * @return the quantity of the headphone
     * time complexity: O(1)
     */
    @Override
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the headphone.
     * @param quantity the quantity of the headphone
     * time complexity: O(1)
     */
    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
