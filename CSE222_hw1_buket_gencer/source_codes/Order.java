/**
 * Class that represents an order
 * @author: BuketGencer
 */
public class Order {
    //data fields 
    protected String product_name;
    protected int count;
    protected int total_price;
    protected int status;
    protected int customer_ID;

    //methods
    /**
     * Initializes an Order object with all properties specified
     * @param product_name The name of the product
     * @param count The count of the product
     * @param total_price The total price of the product
     * @param status The status of the product
     * @param customer_ID The ID of the customer
     */

    // Constructor
    public Order(String product_name, int count, int total_price, int status, int customer_ID) {
        this.product_name = product_name;
        this.count = count;
        this.total_price = total_price;
        this.status = status;
        this.customer_ID = customer_ID;
    }

    //prints the order information
    public void print_order() {

        /*
        Status codes:
        0 Initialized
        1 Processing
        2 Completed
        3 Cancelled
         */

        //checking the status and assigning the corresponding string
        System.out.print("Product name: " + product_name + " - " + "Count: "+ count + " - "+ "Total price: " + total_price + " - " + "Status: ");

        if (status == 0) {
            System.out.println("Initialized");
        }
        else if (status == 1) {
            System.out.println("Processing");
        }
        else if (status == 2) {
            System.out.println("Completed");
        }
        else if (status == 3) {
            System.out.println("Cancelled");
        }

    }

    // Getters
    //get customer ID
    public int getCustomer_ID() {
        return customer_ID;
    }

}
