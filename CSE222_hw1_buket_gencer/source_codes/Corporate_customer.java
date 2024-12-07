/**
 * Class that represents a corporate_customer
 * @author: BuketGencer
 */
public class Corporate_customer extends Customer{

    //data fields
    protected String company_name;

    //methods

    /**
     * Initializes a Corporate_customer object with all properties specified
     * @param name The name of the customer
     * @param surname The surname of the customer
     * @param address The address of the customer
     * @param phone The phone number of the customer
     * @param ID The ID of the customer
     * @param orders The orders of the customer
     * @param operator_ID The ID of the operator
     * @param company_name The name of the company
     */

    // Constructor
    public Corporate_customer(String name, String surname, String address, String phone, int ID, Order[] orders, int operator_ID, String company_name) {
        super(name, surname, address, phone, ID, orders, operator_ID);
        this.company_name = company_name;
    }

    //prints the corporate customer information 
    @Override
    public void print_customer() {
        System.out.println("\nName & Surname: " + name + " " + surname + "\nAddress: " + address + "\nPhone: " + phone + "\nID: " + ID + "\nOperator ID: " + operator_ID + "\nCompany name: " + company_name);
        print_orders();
    }

    public String getCompany_name() {
        return company_name;
    }
    
}
