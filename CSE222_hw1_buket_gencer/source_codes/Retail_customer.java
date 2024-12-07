/**
 * Class that represents a retail_customer
 * @author: BuketGencer
 */
public class Retail_customer extends Customer{

    //methods

    /**
     * Initializes a Retail_customer object with all properties specified
     * @param name The name of the customer
     * @param surname The surname of the customer
     * @param address The address of the customer
     * @param phone The phone number of the customer
     * @param ID The ID of the customer
     * @param orders The orders of the customer
     * @param operator_ID The ID of the operator
     */

    // Constructor
    public Retail_customer(String name, String surname, String address, String phone, int ID, Order[] orders, int operator_ID) {
        super(name, surname, address, phone, ID, orders, operator_ID);
    }
    
}
