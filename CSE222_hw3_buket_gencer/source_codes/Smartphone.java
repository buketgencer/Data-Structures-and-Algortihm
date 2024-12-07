/**
 * This class represents a Smartphone object that implements the Device interface.
 * @author BuketGencer
 */

public class Smartphone implements Device{

    private final String category = "Smartphone";
    private String name;
    private double price;
    private int quantity;
    
    /**
     * Initializes a newly created Smartphone object with the given parameters.
     * @param name the name of the smartphone
     * @param price the price of the smartphone
     * @param quantity the quantity of the smartphone
     */

    public Smartphone(String name, double price, int quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Returns the category of the smartphone.
     * @return the category of the smartphone
     * time complexity: O(1)
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Returns the name of the smartphone.
     * @return the name of the smartphone
     * time complexity: O(1)
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the smartphone.
     * @param name the name of the smartphone
     * time complexity: O(1)
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the price of the smartphone.
     * @return the price of the smartphone
     * time complexity: O(1)
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the smartphone.
     * @param price the price of the smartphone
     * time complexity: O(1)
     */
    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns the quantity of the smartphone.
     * @return the quantity of the smartphone
     * time complexity: O(1)
     */
    @Override
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the smartphone.
     * @param quantity the quantity of the smartphone
     * time complexity: O(1)
     */
    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
}
