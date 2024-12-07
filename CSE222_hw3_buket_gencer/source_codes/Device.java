/**
 * Interface for the Device class
 * @author:  BuketGencer
 */

public interface Device {
    
    /**
     *time complexity: O(1) of getCategory() method
     * @return the category of the device
     */
    String getCategory(); 

    /**
     *time complexity: O(1) of getName() method
     * @return the name of the device
     */
    String getName(); 

    /**
     * time complexity: O(1) of setName() method
     * @param name the name of the device
     */
    void setName(String name);

    /**
     * time complexity: O(1) of getPrice() method
     * @return the price of the device
     */
    double getPrice();

    /**
     * time complexity: O(1) of setPrice() method
     * @param price the price of the device
     */ 
    void setPrice(double price);

    /**
     * time complexity: O(1) of getQuantity() method
     * @return the quantity of the device
     */
    int getQuantity();

    /**
     * time complexity: O(1) of setQuantity() method
     * @param quantity the quantity of the device
     */
    void setQuantity(int quantity);
}
