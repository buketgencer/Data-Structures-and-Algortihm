/**
 * This class represents a TV device. It implements the Device interface.
 * @author BuketGencer
 */

public class Tv implements Device{
    private final String category = "TV";
    private String name;
    private double price;
    private int quantity;

    /**
     * Initializes a newly created Tv object with the given parameters.
     * @param name the name of the tv
     * @param price the price of the tv
     * @param quantity the quantity of the tv
     */

    public Tv(String name, double price, int quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Returns the category of the tv.
     * @return the category of the tv
     * time complexity: O(1)
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Returns the name of the tv.
     * @return the name of the tv
     * time complexity: O(1)
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the tv.
     * @param name the name of the tv
     * time complexity: O(1)
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the price of the tv.
     * @return the price of the tv
     * time complexity: O(1)
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the tv.
     * @param price the price of the tv
     * time complexity: O(1)
     */
    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns the quantity of the tv.
     * @return the quantity of the tv
     * time complexity: O(1)
     */
    @Override
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the tv.
     * @param quantity the quantity of the tv
     * time complexity: O(1)
     */
    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
