/**
 * Class that represents an operator
 * @author: BuketGencer
 */


public class Operator extends Person{

    //data fields
    protected int wage;
    protected Customer[] customers; //this array will hold the customers of the operator
    protected int customerCount = 0; //this will hold the number of customers of the operator

    //methods
    /**
     * Initializes an Operator object with all properties specified
     * @param name The name of the operator
     * @param surname The surname of the operator
     * @param address The address of the operator
     * @param phone The phone number of the operator
     * @param ID The ID of the operator
     * @param wage The wage of the operator
     * @param customers The customers of the operator
     */

    // Constructor
    public Operator(String name, String surname, String address, String phone, int ID, int wage, Customer[] customers) {
        super(name, surname, address, phone, ID);
        this.wage = wage;
        if(customers != null) {
            this.customers = customers;
        } else {
            this.customers = new Customer[100];
        }
    }

    // Getters
    public int getWage() { return wage; }
    public Customer[] getCustomers() { return customers; }

    // Setters
    public void setWage(int wage) { this.wage = wage; }
 
    //prints the operator information 
    public void print_operator() {
        System.out.println("----------------------------");
        System.out.println("Name & Surname: " + name + " " + surname + "\nAddress: " + address + "\nPhone: " + phone + "\nID: " + ID + "\nWage: " + wage);
    }
    
    //prints the customers of the operator 
    public void print_customers() {

        //check if the customer is null or not
        if(customers[0] == null) { 
            System.out.println("----------------------------");
            System.out.println("This operator doesn't have any customer.");
            System.out.println("----------------------------");
        } //if there is a customer, print the customer information
        else {
            for (int i = 0; i < customerCount; i++) {
                if(customers[i] instanceof Retail_customer) {
                    System.out.println("----------------------------");
                    System.out.print("Customer #" + (i+1) + " (a retail customer):");
                    customers[i].print_customer();
                } else {
                    System.out.println("-----------------------------");
                    System.out.print("Customer #" + (i+1) + " (a corporate customer):");
                    customers[i].print_customer();
                }
            }
        }
    }

    //defines the customers of the operator
    public void define_customers(Customer[] customers) {
        for (int i = 0; i < customers.length; i++) {
            // check if the customer is null or not
            if (customers[i] != null && customers[i].getOperator_ID() == this.ID) {
                // if the customer is not null and 
                // their id's match, add the customer to the operator's customers array
                this.customers[customerCount] = customers[i];
                customerCount++;
            }
        }
    }
    
}
