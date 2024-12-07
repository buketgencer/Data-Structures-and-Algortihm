import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        // Creating an instance of the Inventory class
        Inventory inventory = new Inventory();
        Scanner scanner = new Scanner(System.in);
            


         while(true){
                System.out.println("Menu System");
                System.out.println("Welcome to the Electronics Inventory Management System!");
                System.out.println("Please select an option:");
                System.out.println("1. Add a new device");
                System.out.println("2. Remove a device");
                System.out.println("3. Update device details");
                System.out.println("4. List all devices");
                System.out.println("5. Find the cheapest device");
                System.out.println("6. Sort devices by price");
                System.out.println("7. Calculate total inventory value");
                System.out.println("8. Restock a device");
                System.out.println("9. Export inventory report");
                System.out.println("0. Exit");
            
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                switch(choice){
                    case 1:
                            // Add a new device
                            System.out.println("Enter category name: ");
                            String category = scanner.nextLine();
                            System.out.println("Enter device name: ");
                            String name = scanner.nextLine();
                            System.out.println("Enter price: ");
                            double price = scanner.nextDouble();
                            System.out.println("Enter quantity: ");
                            int quantity = scanner.nextInt();
                            scanner.nextLine(); // Consume newline character

                            inventory.addDevice(name, price, quantity, category);

                        break;
                    case 2:
                            // Remove a device
                            System.out.println("Enter category name: ");
                            category = scanner.nextLine();
                            System.out.println("Enter device name: ");
                            name = scanner.nextLine();
                            inventory.removeDevice(name, category);
                            
                        break;
                    case 3:
                        // Update device details
                      
                            System.out.println("Enter the name of the device to update: ");
                            name = scanner.nextLine();
                            System.out.println("Enter new price (leave blank to keep current price): ");
                            String priceStr = scanner.nextLine();
                            System.out.println("Enter new quantity (leave blank to keep current quantity): ");
                            String quantityStr = scanner.nextLine();
                            double newPrice = priceStr.isEmpty() ? 0 : Double.parseDouble(priceStr); //if priceStr is empty, set newPrice to 0, else parse the priceStr to double
                            int newQuantity = quantityStr.isEmpty() ? 0 : Integer.parseInt(quantityStr); //if quantityStr is empty, set newQuantity to 0, else parse the quantityStr to int
                            //if newPrice or newQuantity not an integer or double, it will throw an exception
                            //and the program go back to the main menu
                                
                            inventory.updateDevice(name, newPrice, newQuantity);

                        break;
                    case 4:
                            // List all devices
                            inventory.displayDevices();
                        break;
                    case 5:
                         // Find the cheapest device
                        inventory.minPriceDevice();
                        break;
                    case 6:
                            // Sort devices by price
                            inventory.sortDevicesByPrice();
                        break;
                    case 7:
                        // Calculate total inventory value
                        //Total inventory value: $56,000.00
                            System.out.println("Total inventory value: $" + inventory.totalValue());
                        break;
                    case 8:
                        // Restock a device
                            System.out.println("Enter the name of the device to restock: ");
                            name = scanner.nextLine();
                            System.out.println("Do you want to add or remove stock? (Add/Remove): ");
                            String addOrRemove = scanner.nextLine();
                            System.out.println("Enter the quantity to " + addOrRemove + ": ");
                            int quantityToRestock = scanner.nextInt();
                            scanner.nextLine(); // Consume newline character
                            inventory.restockDevice(name, addOrRemove, quantityToRestock);
                        break;
                    case 9:
                        // Export inventory report
                        inventory.exportInventoryReport();
                    break;
                case 0:
                    // Exit
                    //terminate the program
                        System.out.println("Exiting...");

                        // close the scanner
                        if(scanner != null){
                            scanner.close();
                        }

                        System.exit(0);
                        break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    }
     
}
