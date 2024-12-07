import java.util.ArrayList;
import java.util.LinkedList;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.io.IOException;
/**
 * Class that represents a inventory of a store.
 * @author: BuketGencer
 */

 /**
  * Inventory class with a LinkedList of ArrayLists to hold devices
  */
public class Inventory {

    private LinkedList<ArrayList<Device>> categories;

    /**
     * Initializes an Inventory object with all properties specified
     */
    public Inventory(){
        this.categories = new LinkedList<>();
        // create 5 categories of devices (TV, Smartphone, Laptop, Smartwatch, Headphone)
        for(int i = 0; i < 5; i++){
            categories.add(new ArrayList<>());
        }
    }

    /**
     * Add a new device to the inventory
     * @param name the name of the device
     * @param price  the price of the device
     * @param quantity the quantity of the device
     * @param category the category of the device
     * time complexity: O(1) of addDevice(String name, double price, int quantity, String category)
     */
    public void addDevice(String name, double price, int quantity, String category){
        /**
         * Create a new device object based on the category
         */
        //if name is alderady in the inventory, do not add it
        // int namecontrol = 0;
        //check there is no device with the same name in the inventory after do adding operation
        
        Device device = null;
        switch(category){
            case "TV":
                //check there is no device with the same name in the inventory after do adding operation
                for(Device d : categories.get(0)){
                    if(d.getName().equals(name)){
                        System.out.println("There is already a device with the same name in the inventory");
                        break;
                    }
                }
                device = new Tv(name, price, quantity);
                addDevice(device);
                System.out.println(category + ", " + name + ", " + price + "$, " + quantity + " amount added...");
                break;
            case "Smartphone":
                for(Device d : categories.get(1)){
                    if(d.getName().equals(name)){
                        System.out.println("There is already a device with the same name in the inventory");
                        break;
                    }
                }
                device = new Smartphone(name, price, quantity);
                addDevice(device);
                System.out.println(category + ", " + name + ", " + price + "$, " + quantity + " amount added...");
                break;
            case "Laptop":
                for(Device d : categories.get(2)){
                    if(d.getName().equals(name)){
                        System.out.println("There is already a device with the same name in the inventory");
                        break;
                    }
                }
                device = new Laptop(name, price, quantity);
                System.out.println(category + ", " + name + ", " + price + "$, " + quantity + " amount added...");
                addDevice(device);
                break;
            case "Smartwatch":
                for(Device d : categories.get(3)){
                    if(d.getName().equals(name)){
                        System.out.println("There is already a device with the same name in the inventory");
                        //out of the loop
                        break;
                    }
                }
                device = new Smartwatch(name, price, quantity);
                addDevice(device);
                System.out.println(category + ", " + name + ", " + price + "$, " + quantity + " amount added...");
                break;
            case "Headphone":
                for(Device d : categories.get(4)){
                    if(d.getName().equals(name)){
                        System.out.println("There is already a device with the same name in the inventory");
                        break;
                    }
                }
                device = new Headphone(name, price, quantity);
                addDevice(device);
                System.out.println(category + ", " + name + ", " + price + "$, " + quantity + " amount added...");
                break;
            default:
                System.out.println("Invalid category");
        }
           
    }

    /**
     * Add a new device to the inventory
     * @param device
     * time complexity: O(1) of addDevice(Device device)
     */
    private void addDevice(Device device){
        // get the category of the device
        String category = device.getCategory();
        // add the device to the corresponding category
        switch(category){
            case "TV":
                categories.get(0).add(device);
                break;
            case "Smartphone":
                categories.get(1).add(device);
                break;
            case "Laptop":
                categories.get(2).add(device);
                break;
            case "Smartwatch":
                categories.get(3).add(device);
                break;
            case "Headphone":
                categories.get(4).add(device);
                break;
            default:
                System.out.println("Invalid category");
        }
    }

    /**
     * Remove a device from the inventory
     * @param name
     * @param category
     * time complexity: O(1) of removeDevice(String name, String category)
     */
    public void removeDevice(String name, String category){
        // remove the device from the corresponding category

        
            boolean deviceFound = false; // the flag to check if the device is found in the inventory
            int indexToRemove = -1; // the index of the device to remove
        
            ArrayList<Device> devices = null;
            // find the category of the device
            switch(category){
                case "TV":
                    devices = categories.get(0);
                    break;
                case "Smartphone":
                    devices = categories.get(1);
                    break;
                case "Laptop":
                    devices = categories.get(2);
                    break;
                case "Smartwatch":
                    devices = categories.get(3);
                    break;
                case "Headphone":
                    devices = categories.get(4);
                    break;
                default:
                    System.out.println("Invalid category");
                    return; // exit the function if the category is invalid
            }
        
            // find the device in the category
            for(int i = 0; i < devices.size(); i++){
                if(devices.get(i).getName().equals(name)){
                    indexToRemove = i;
                    deviceFound = true;
                    break; // exit the loop if the device is found
                }
            }
        
            // remove the device if it is found
            if (deviceFound) {
                devices.remove(indexToRemove);
                System.out.println(category + ", " + name + ", removed...");
            } else {
                System.out.println("Device not found in " + category);
            }
    }

    /**
     * updateDevice method without category parameter 
     * @param name the name of the device
     * @param newPrice the new price of the device
     * @param newQuantity the new quantity of the device
     * time complexity: O(n) of updateDevice(String name, double newPrice, int newQuantity)
     * The updateDevice function iterates through all devices across all categories with nested loops,
     * but essentially performs a single pass over the total number of devices, n, in the inventory.
     * Therefore, the time complexity of the updateDevice function is O(n).
     */
    public void updateDevice(String name, double newPrice, int newQuantity) {
        // update the device from the corresponding category
        boolean deviceFound = false; // for checking if the device is found in the inventory

        for(ArrayList<Device> category : categories){
            for(Device device : category){
                if(device.getName().equals(name)){
                    deviceFound = true; // set deviceFound to true if the device is found
                    if(newPrice != 0){
                        device.setPrice(newPrice);
                    }
                    if(newQuantity != 0){
                        device.setQuantity(newQuantity);
                    }
                    if(newPrice == 0 && newQuantity != 0){
                        System.out.println(name + " details updated: Price - not updated, Quantity - " + newQuantity);
                    }
                    else if(newQuantity == 0 && newPrice != 0){
                        System.out.println(name + " details updated: Price - " + newPrice + "$, Quantity - not updated");
                    }
                    else if(newPrice == 0 && newQuantity == 0){
                        System.out.println(name + " details updated: Price - not updated, Quantity - not updated");
                    }
                    else{
                        System.out.println(name + " details updated: Price - " + newPrice + "$, Quantity - " + newQuantity);
                    }
                    break; //   break the inner loop if the device is found
                }
            }
            if(deviceFound) break; //   break the outer loop if the device is found
        }
    
        if(!deviceFound){
            System.out.println("Device with name '" + name + "' not found in the inventory.");
        }
    }
    /**
     * Display all devices in the inventory
     * time complexity: O(n) of displayDevices()
     * The displayDevices function iterates through all devices across all categories with nested loops,
     */
    public void displayDevices(){
        for(ArrayList<Device> category : categories){
            for(Device device : category){
                //give a number to each device when displaying
                int i = 1;
                System.out.println(i + ". Category: " + device.getCategory() + ", Name: " + device.getName() + ", Price: " + device.getPrice() + "$, Quantity: " + device.getQuantity());
                i++;
            }
        }
    }

    /**
     * Display all devices in a specific category
     * time complexity: O(n) of minPriceDevice()
     * The minPriceDevice function iterates through all devices across all categories with nested loops,
     */
    public void minPriceDevice(){
        double minPrice = Double.MAX_VALUE;
        Device minPriceDevice = null;
        //if there is no device in the inventory
        //try - catch block to handle the null pointer exception
        try{
            for(ArrayList<Device> category : categories){
                for(Device device : category){
                    if(device.getPrice() < minPrice){
                        minPrice = device.getPrice();
                        minPriceDevice = device;
                    }
                }
            }
            System.out.println("The cheapest device is:");
            System.out.println("Category: " + minPriceDevice.getCategory() + ", Name: " + minPriceDevice.getName() + ", Price: " + minPriceDevice.getPrice() + "$, Quantity: " + minPriceDevice.getQuantity());
        }catch(NullPointerException e){
            System.out.println("There is no device in the inventory");
        } 
        
    }
     
    /**
     * Sort devices by price in ascending order
     * time complexity: O(nlogn) of sortDevicesByPrice()
     * The sortDevicesByPrice function first creates a new list to store the sorted devices,
     */
    public void sortDevicesByPrice(){
        // create a new list to store the sorted devices
        ArrayList<Device> sortedDevices = new ArrayList<>();
        // add all devices to the new list
        for(ArrayList<Device> category : categories){
            sortedDevices.addAll(category);
        }
        // sort the devices by price in ascending order
        sortedDevices.sort((d1, d2) -> Double.compare(d1.getPrice(), d2.getPrice()));
        // display the sorted list to the console
        int i = 1;
        for(Device device : sortedDevices){
            System.out.println(i + ". Category: " + device.getCategory() + ", Name: " + device.getName() + ", Price: " + device.getPrice() + "$, Quantity: " + device.getQuantity());
            i++;
        }
    }

    /**
     * Calculate the total value of the inventory
     * time complexity: O(n) of totalValue()
     * @return totalValue the total value of the inventory
     */
    public double totalValue(){
        double totalValue = 0;
        for(ArrayList<Device> category : categories){
            for(Device device : category){
                totalValue += device.getPrice() * device.getQuantity();
            }
        }
        return totalValue;
    }

    /**
     * Restock a device in the inventory
     * @param name the name of the device
     * @param addOrRemove add or remove stock
     * @param quantity the quantity to add or remove
     * time complexity: O(n) of restockDevice(String name, String addOrRemove, int quantity)
     */
    public void restockDevice(String name, String addOrRemove, int quantity){
        
            boolean deviceFound = false; // the flag to check if the device is found in the inventory
        
            // find the device in the inventory
            for(ArrayList<Device> category : categories){
                for(Device device : category){
                    if(device.getName().equals(name)){
                        deviceFound = true; // deviceFound flag set to true if the device is found
                        if(addOrRemove.equals("Add")){
                            device.setQuantity(device.getQuantity() + quantity);
                            System.out.println(device.getName() + " restocked. New quantity: " + device.getQuantity());
                        } else if(addOrRemove.equals("Remove")){
                            // check if the quantity to remove is less than or equal to the current quantity
                            if(quantity <= device.getQuantity()){
                                device.setQuantity(device.getQuantity() - quantity);
                                System.out.println(device.getName() + " stock reduced. New quantity: " + device.getQuantity());
                            } else {
                                System.out.println("Quantity to remove is greater than the current quantity");
                            }
                        }
                        return; // out of the loop if the device is found
                    }
                }
            }
        
            // if the device is not found in the inventory after the loop is completed give a message
            if (!deviceFound) {
                System.out.println("Device named \"" + name + "\" not found.");
            }
        
    }

    /**
     * Export an inventory report to a text file
     * time complexity: O(n) of exportInventoryReport()
     * The exportInventoryReport function creates a new file object with the name "inventory_report.txt",
     */
    public void exportInventoryReport(){
        // create a new file
        File file = new File("inventory_report.txt"); //create a file object with the name "inventory_report.txt"
        try{
           
                        FileWriter writer = new FileWriter(file); //create a file writer object
                        // write the inventory report to the file
                        writer.write("Electronics Shop Inventory Report\n");
                        writer.write("Generated on: " + LocalDate.now() + "\n");
                        writer.write("---------------------------------------\n");
                        writer.write("| No. | Category | Name | Price | Quantity |\n");
                        writer.write("---------------------------------------\n");
                        int i = 0;
                        for(ArrayList<Device> category : categories){
                            for(Device device : category){
                                writer.write("| " + i + " | " + device.getCategory() + " | " + device.getName() + " | $" + device.getPrice() + " | " + device.getQuantity() + " |\n");
                                i++;
                            }
                        }
                        writer.write("---------------------------------------\n");
            writer.write("Summary:\n");
            writer.write("- Total Number of Devices: " + i + "\n");
            writer.write("- Total Inventory Value: $" + totalValue() + "\n");
            writer.write("End of Report\n");
            // close the writer
            System.out.println("Inventory report exported to inventory_report.txt");
            writer.close();
                }catch(IOException e){
                    e.printStackTrace();
                }

    }
}


