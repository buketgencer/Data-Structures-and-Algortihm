/**
 * This class represents a Smartwatch device. It implements the Device interface
 * @author BuketGencer
 */

public class Smartwatch implements Device{

    private final String category = "Smartwatch";
    private String name;
    private double price;
    private int quantity;

    /**
     * Initializes a newly created Smartwatch object with the given parameters.
     * @param name the name of the smartwatch
     * @param price the price of the smartwatch
     * @param quantity the quantity of the smartwatch
     */

    public Smartwatch(String name, double price, int quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Returns the category of the smartwatch.
     * @return the category of the smartwatch
     * time complexity: O(1)
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Returns the name of the smartwatch.
     * @return the name of the smartwatch
     * time complexity: O(1)
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the smartwatch.
     * @param name the name of the smartwatch
     * time complexity: O(1)
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the price of the smartwatch.
     * @return the price of the smartwatch
     * time complexity: O(1)
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the smartwatch.
     * @param price the price of the smartwatch
     * time complexity: O(1)
     */
    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns the quantity of the smartwatch.
     * @return the quantity of the smartwatch
     * time complexity: O(1)
     */
    @Override
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the smartwatch.
     * @param quantity the quantity of the smartwatch
     * time complexity: O(1)
     */
    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
