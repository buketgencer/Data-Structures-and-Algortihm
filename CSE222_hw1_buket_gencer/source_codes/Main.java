/**
 * Class that represents a main class
 * @author: BuketGencer
 */

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void Deneme ( Customer cust)
    {
        if (cust instanceof Retail_customer)
        {
            System.out.println("Retail_customer");
        }
        else if (cust instanceof Corporate_customer)
        {
            System.out.println("Corporate_customer");
            System.out.println("Corporate Name : " + ((Corporate_customer) cust).getCompany_name());
        }
    }
  
    public static void main(String[] args) {

        
        /*
        // arrays to store the objects
        Order[] orders = new Order[100];
        Retail_customer[] retailCustomers = new Retail_customer[100];
        Corporate_customer[] corporateCustomers = new Corporate_customer[100];
        Operator[] operators = new Operator[100];

        //id list array to check the id conflict
        int[] existingIDs = new int[200];

        // counters to keep track of the number of objects
        int orderCount = 0;
        int retailCustomerCount = 0;
        int corporateCustomerCount = 0;
        int operatorCount = 0;
        int idCount = 0;

        //CREATING OBJECTS PART ---READING THE FILE AND CREATING OBJECTS---

        try {
            // read the file
            File file = new File("content.txt"); // file name is content.txt
            //check if the file exists or not
            if (!file.exists()) {
                System.out.println("The file doesn't exist.");
                return;
            }
            Scanner scanner = new Scanner(file); // create a scanner object to read the file
            while (scanner.hasNextLine()) { // read the file line by line
                String line = scanner.nextLine(); // read the line
                String[] parts = line.split(";"); // split the line by the semicolon
                int currentID = -1;

               if(parts[0].equals("retail_customer") || parts[0].equals("corporate_customer")|| parts[0].equals("operator") ){
                // if parts[5] is not an integer, it should just skip the line
                try {
                    currentID = Integer.parseInt(parts[5]);
                } catch (Exception e) {
                    continue;
                }
               }
              

                //store the id in the existingIDs array to check the id conflict
                switch(parts[0]) { // check the first part of the line
                    case "order": 
                        if (isValidLine(parts) == 1){
                            orders[orderCount++] = new Order(parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[5]));
                        }  
                        break;
                    case "retail_customer":
                        if (isValidLine(parts) == 1 && IsIdUnique(currentID, existingIDs, idCount) == 1){
                            retailCustomers[retailCustomerCount++] = new Retail_customer(parts[1], parts[2], parts[3], parts[4], Integer.parseInt(parts[5]), null, Integer.parseInt(parts[6]));
                            existingIDs[idCount++] = Integer.parseInt(parts[5]);
                        }
                        break;
                    case "corporate_customer":
                          if (isValidLine(parts) == 1 && IsIdUnique(currentID, existingIDs, idCount) == 1){
                            corporateCustomers[corporateCustomerCount++] = new Corporate_customer(parts[1], parts[2], parts[3], parts[4], Integer.parseInt(parts[5]), null, Integer.parseInt(parts[6]), parts[7]);
                            existingIDs[idCount++] = Integer.parseInt(parts[5]);
                        }  
                        break;
                    case "operator":
                       if (isValidLine(parts) == 1 && IsIdUnique(currentID, existingIDs, idCount) == 1){
                            operators[operatorCount++] = new Operator(parts[1], parts[2], parts[3], parts[4], Integer.parseInt(parts[5]), Integer.parseInt(parts[6]), null);
                            existingIDs[idCount++] = Integer.parseInt(parts[5]);
                        }
                        break;
                }
               
            }
            scanner.close();
        } catch (Exception e) {
            
        }

        // DEFINING PART ---THE ORDERS OF THE CUSTOMERS AND THE CUSTOMERS OF THE OPERATORS---
        for (int i = 0; i < retailCustomerCount; i++) {
            retailCustomers[i].define_orders(orders); //pairing orders with customers
        }

        for (int i = 0; i < corporateCustomerCount; i++) {
            corporateCustomers[i].define_orders(orders); //pairing orders with customers
        }

        for (int i = 0; i < operatorCount; i++) {
            operators[i].define_customers(retailCustomers); //pairing customers with operators
            operators[i].define_customers(corporateCustomers); //pairing customers with operators
        }

        //PRINTING PART ---PRINTING THE CUSTOMERS AND THE OPERATORS INFORMATION ACCORDING TO THE USER INPUT---
         Scanner input_scanner = new Scanner(System.in);

         System.out.println("Please enter your ID...");

         int id = 0;

            try {
                id = input_scanner.nextInt();
                if(id < 0)
                {
                    System.out.println("Invalid ID. Please try again.");
                    input_scanner.nextLine(); // clear the newline character from the buffer
                    input_scanner.close(); // close the scanner object
                    return;
                }
            } catch (Exception e) {
                System.out.println("Invalid ID. Please try again.");
                input_scanner.nextLine(); // clear the newline character from the buffer
                input_scanner.close(); // close the scanner object

                return;
            }
                input_scanner.nextLine(); // clear the newline character from the buffer
                input_scanner.close(); // close the scanner object

                int iw = 0; // to break the while loop

         while(iw == 0)   
         {

            for (int i = 0; i < retailCustomerCount; i++) {
                if((retailCustomers[i].getID()) == id)
                {
                  
                    System.out.print("*** Customer Screen ***");
                    retailCustomers[i].print_customer();
                    iw++;
                    break;
                }
            }

            for (int i = 0; i < corporateCustomerCount; i++) {
                if((corporateCustomers[i].getID()) == id)
                {
                   
                    System.out.print("*** Customer Screen ***");
                    corporateCustomers[i].print_customer();
                    iw++;
                    break;
                }
            }

            for (int i = 0; i < operatorCount; i++) {
                if((operators[i].getID()) == id)
                {
                  
                    System.out.println("*** Operator Screen ***");
                    operators[i].print_operator();
                    operators[i].print_customers();
                    iw++;
                    break;
                }
            }

            if(iw == 0)
            {
                System.out.println("No operator/customer was found with ID " + id + ". Please try again.");
                iw++;
                break;
            }
            iw++;
            
         }


    }   
    public static int isValidLine(String[] parts ){

    
        if(parts[0].equals("order") && parts.length == 6)
        {
            //check this rules:
            //All strings should have at least 1 character
            //The count, total_price, customer ID and status should be an integer
            //order;product_name;count;total_price;status;customer_id
            //The status should be between 0 and 3
            //The count, total_price and customer ID should be greater than 0
            //A line in the text file cannot have more items than it should have
        
            for (int i = 1; i < parts.length; i++) {
                if(parts[i].length() < 1)
                {
                    return 0;
                }
            }
            try {
                Integer.parseInt(parts[2]);
                Integer.parseInt(parts[3]);
                Integer.parseInt(parts[4]);
                Integer.parseInt(parts[5]);
            } catch (Exception e) {
                return 0;
            }
            if(Integer.parseInt(parts[4]) < 0 || Integer.parseInt(parts[4]) > 3)
            {
                return 0;
            }
            if(Integer.parseInt(parts[2]) < 1 || Integer.parseInt(parts[3]) < 0 || Integer.parseInt(parts[5]) < 0)
            {
                return 0;
            }
            return 1;
            
        }
        else if(parts[0].equals("retail_customer") && parts.length == 7)
        {
            //check this rules:
            //All strings should have at least 1 character
            //retail_customer;name;surname;address;phone;ID;operator_ID
            //The ID and operator ID should be an integer
            //The ID and operator ID should be greater than 0
            //A line in the text file cannot have more items than it should have
            for (int i = 1; i < parts.length; i++) {
                if(parts[i].length() < 1)
                {
                    return 0;
                }
            }
            try {
                Integer.parseInt(parts[5]);
                Integer.parseInt(parts[6]);
            } catch (Exception e) {
                return 0;
            }
            if(Integer.parseInt(parts[5]) < 0 || Integer.parseInt(parts[6]) < 0)
            {
                return 0;
            }
            return 1;
        }
        else if(parts[0].equals("corporate_customer") && parts.length == 8)
        {
            //check this rules:
            //All strings should have at least 1 character
            //corporate_customer;name;surname;address;phone;ID;operator_ID;company_name
            //The ID and operator ID should be an integer
            //The ID and operator ID should be greater than 0
            //A line in the text file cannot have more items than it should have
            for (int i = 1; i < parts.length; i++) {
                if(parts[i].length() < 1)
                {
                    return 0;
                }
            }
            try {
                Integer.parseInt(parts[5]);
                Integer.parseInt(parts[6]);
            } catch (Exception e) {
                return 0;
            }
            if(Integer.parseInt(parts[5]) < 0 || Integer.parseInt(parts[6]) < 0)
            {
                return 0;
            }
            return 1;
        }
        else if(parts[0].equals("operator") && parts.length == 7)
        {
            //check this rules:
            //All strings should have at least 1 character
            //operator;name;surname;address;phone;ID;wage
            //The ID and wage should be an integer
            //The ID and wage should be greater than 0
            //A line in the text file cannot have more items than it should have
            for (int i = 1; i < parts.length; i++) {
                if(parts[i].length() < 1)
                {
                    return 0;
                }
            }
            try {
                Integer.parseInt(parts[5]);
                Integer.parseInt(parts[6]);
            } catch (Exception e) {
                return 0;
            }
            if(Integer.parseInt(parts[5]) < 0 || Integer.parseInt(parts[6]) < 0)
            {
                return 0;
            }
            return 1;
        }
        else
        {
            return 0;
        }
    }

    public static int IsIdUnique(int id, int[] existingIDs , int idCount)
    {
        //check this rules:
        //The ID of the retail customer and the corporate customer should be unique
        //The ID of the operator should be unique
        //any customers and operators cannot have the same ID
        //If an id is more than one, ignore the ones added later
        //orders dont have the id so we dont need to check the id for the orders
        //customers and operators's id exist in the perts[5].
        //if the id is unique return 1 otherwise return 0

        for (int i = 0; i < idCount; i++) {
            if(existingIDs[i] == id)
            {
                return 0;
            }
        }

        */

        //creating objects
        Retail_customer rc = new Retail_customer("Buket", "Gencer", "Istanbul", "123456", 1, null, 1);
        Corporate_customer cc = new Corporate_customer("Buket", "Gencer", "Istanbul", "123456", 2, null, 2, "Bilge Adam");

        //calling the method (deneme) to check the instance of the object
        Deneme(rc);
        Deneme(cc);


        

    }



}