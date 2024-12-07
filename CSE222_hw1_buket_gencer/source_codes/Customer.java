/**
 * Class that represents a customer
 * @author: BuketGencer
 */

public class Customer extends Person{

    //data fields
    protected Order[] orders;
    protected int operator_ID;
    protected int orderCount = 0; //this will hold the number of orders of the customer

    //methods

    /**
     * Initializes a Customer object with all properties specified
     * @param name The name of the customer
     * @param surname The surname of the customer
     * @param address The address of the customer
     * @param phone The phone number of the customer
     * @param ID The ID of the customer
     * @param orders The orders of the customer
     * @param operator_ID The ID of the operator
     */

    // Constructor
    public Customer(String name, String surname, String address, String phone, int ID, Order[] orders, int operator_ID) {
        super(name, surname, address, phone, ID);
        if(orders != null) {
            this.orders = orders;
        }
        else {
            this.orders = new Order[100];
        }
    
        this.operator_ID = operator_ID;
    }

    //prints the customer information
    public void print_customer() {
        System.out.println("\nName & Surname: " + name + " " + surname + "\nAddress: " + address + "\nPhone: " + phone + "\nID: " + ID + "\nOperator ID: " + operator_ID) ;
        print_orders();
    }

    //prints the orders of the customer
    public void print_orders() {
        //check if the order is null or not
        if(orders[0] == null) {
            System.out.println("This customer doesn't have any order.");
        } 
        //if there is an order, print the order information
        else {
            for (int i = 0; i < orderCount; i++) {
                System.out.print("Order #" + (i+1) + "=>  ");
                orders[i].print_order();
            }
        }   
    }

    // Define orders   
    public void define_orders(Order[] orders) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] != null && orders[i].getCustomer_ID() == this.ID) {
                // if the order is not null and the customer ID of the order is equal to the ID of the customer,
                // then add the order to the orders array of the customer
                this.orders[orderCount] = orders[i];
                orderCount++;
            }
        }
    }

    // Getters
    public Order[] getOrders() { return orders; }
    public int getOperator_ID() { return operator_ID; }

    
}
