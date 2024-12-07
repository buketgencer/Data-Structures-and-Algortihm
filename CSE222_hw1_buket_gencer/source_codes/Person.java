
/**
 * Class that represents a person 
 * @author: BuketGencer
 */

public class Person {

    //data fields
    protected String name;
    protected String surname;
    protected String address;
    protected String phone;
    protected int ID;

    //methods 
    /**
     * Initializes a Person object with all properties specified
     * @param name The name of the person
     * @param surname The surname of the person
     * @param address The address of the person
     * @param phone The phone number of the person
     * @param ID The ID of the person
     */

    // Constructor
    public Person(String name, String surname, String address, String phone, int ID) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.ID = ID;
    }

    // Getters

    public String getName() { return name; }
    public String getSurname() { return surname; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public int getID() { return ID; }

    // Setters

    public void setName(String name) { this.name = name; }
    public void setSurname(String surname) { this.surname = surname; }
    public void setAddress(String address) { this.address = address; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setID(int ID) { this.ID = ID; }

}