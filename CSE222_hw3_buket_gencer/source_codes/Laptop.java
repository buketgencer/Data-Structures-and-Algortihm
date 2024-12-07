/**
 * This class represents a Laptop object that implements the Device interface.
 * @author BuketGencer
 */

public  class Laptop implements Device{

    private final String category = "Laptop";
    private String name;
    private double price;
    private int quantity;

    /**
     * Initializes a newly created Laptop object with the given parameters.
     * @param name the name of the laptop
     * @param price the price of the laptop
     * @param quantity the quantity of the laptop
     */

    public Laptop(String name, double price, int quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Returns the category of the laptop.
     * @return the category of the laptop
     * time complexity: O(1)
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Returns the name of the laptop.
     * @return the name of the laptop
     * time complexity: O(1)
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the laptop.
     * @param name the name of the laptop
     * time complexity: O(1)
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the price of the laptop.
     * @return the price of the laptop
     * time complexity: O(1)
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the laptop.
     * @param price the price of the laptop
     * time complexity: O(1)
     */
    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns the quantity of the laptop.
     * @return the quantity of the laptop
     * time complexity: O(1)
     */
    @Override
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the laptop.
     * @param quantity the quantity of the laptop
     * time complexity: O(1)
     */
    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
