import java.util.ArrayList;
import java.util.List;

public class StockDataManager { // Class to manage the stock data and execute the commands
    private AVLTree avlTree = new AVLTree(); // Create an AVL tree to store the stock data
    private GUIVisualization gui; // Create a GUI object to visualize the stock data
    private List<Long> searchTimes = new ArrayList<>(); // Create lists to store the search, add, remove, and update times
    private List<Long> addTimes = new ArrayList<>(); // Create lists to store the search, add, remove, and update times
    private List<Long> removeTimes = new ArrayList<>(); // Create lists to store the search, add, remove, and update times
    private List<Long> updateTimes = new ArrayList<>(); // Create lists to store the search, add, remove, and update times
    private List<Integer> searchSizes = new ArrayList<>();  // Create lists to store the search, add, remove, and update sizes
    private List<Integer> addSizes = new ArrayList<>();   // Create lists to store the search, add, remove, and update sizes
    private List<Integer> removeSizes = new ArrayList<>();  // Create lists to store the search, add, remove, and update sizes
    private List<Integer> updateSizes = new ArrayList<>();  // Create lists to store the search, add, remove, and update sizes

    public StockDataManager(GUIVisualization gui) { // Constructor for StockDataManager
        this.gui = gui; // Set the GUI object
    }

    public void add(String symbol, double price, long volume, long marketCap) { // Method to add a stock to the AVL tree
        long startTime = System.nanoTime(); // Get the start time
        avlTree.insert(new Stock(symbol, price, volume, marketCap));    // Insert the stock into the AVL tree
        long endTime = System.nanoTime();   // Get the end time
        addTimes.add(endTime - startTime);  // Add the time taken to the addTimes list
        addSizes.add(avlTree.size());   // Add the size of the AVL tree to the addSizes list
        gui.setAddTimes(addTimes, addSizes);    // Set the add times and sizes in the GUI
        System.out.println("Added: " + symbol + ", price: " + price + ", volume: " + volume + ", marketCap: " + marketCap);
    }

    public void remove(String symbol) { // Method to remove a stock from the AVL tree
        long startTime = System.nanoTime(); // Get the start time
        avlTree.delete(symbol); // Delete the stock from the AVL tree
        long endTime = System.nanoTime();   // Get the end time
        removeTimes.add(endTime - startTime);   // Add the time taken to the removeTimes list
        removeSizes.add(avlTree.size());    // Add the size of the AVL tree to the removeSizes list
        gui.setRemoveTimes(removeTimes, removeSizes);   // Set the remove times and sizes in the GUI
        System.out.println("Removed: " + symbol);
    }

    public void update(String oldSymbol, String newSymbol, double price, long volume, long marketCap) { // Method to update a stock in the AVL tree
        long startTime = System.nanoTime(); // Get the start time
        avlTree.update(oldSymbol, new Stock(newSymbol, price, volume, marketCap));  // Update the stock in the AVL tree
        long endTime = System.nanoTime();   // Get the end time
        updateTimes.add(endTime - startTime);   // Add the time taken to the updateTimes list
        updateSizes.add(avlTree.size());    // Add the size of the AVL tree to the updateSizes list
        gui.setUpdateTimes(updateTimes, updateSizes);   // Set the update times and sizes in the GUI
        System.out.println("Updated: " + oldSymbol + " to " + newSymbol + ", price: " + price + ", volume: " + volume + ", marketCap: " + marketCap);
    }

    public void search(String symbol) { // Method to search for a stock in the AVL tree
        long startTime = System.nanoTime(); // Get the start time
        Stock result = avlTree.search(symbol);  // Search for the stock in the AVL tree
        long endTime = System.nanoTime();   // Get the end time
        searchTimes.add(endTime - startTime);   // Add the time taken to the searchTimes list 
        searchSizes.add(avlTree.size());    // Add the size of the AVL tree to the searchSizes list
        gui.setSearchTimes(searchTimes, searchSizes);   // Set the search times and sizes in the GUI
        if (result != null) {
            System.out.println("Found: " + result);   // Print out the stock information
        } else {
            System.out.println("Stock not found: " + symbol);  // Print out that the stock was not found
        }
    }

    public void executeCommands(String filename) {  // Method to execute the commands in the file
        FileOperator fileOperator = new FileOperator(filename); // Create a FileOperator object
        List<String> commands = fileOperator.readCommands();    // Read the commands from the file

        for (String command : commands) {   // Loop through the commands
            String[] parts = command.split(" ");    // Split the command into parts
            String operation = parts[0];    // Get the operation

            switch (operation) {    // Switch on the operation
                case "ADD":
                    add(parts[1], Double.parseDouble(parts[2]), Long.parseLong(parts[3]), Long.parseLong(parts[4]));    // Add the stock
                    break;
                case "REMOVE":
                    remove(parts[1]);   // Remove the stock
                    break;
                case "UPDATE":
                    update(parts[1], parts[2], Double.parseDouble(parts[3]), Long.parseLong(parts[4]), Long.parseLong(parts[5]));   // Update the stock
                    break;
                case "SEARCH":
                    search(parts[1]);   // Search for the stock
                    break;
                default:
                    System.out.println("Unknown command: " + command);  // Print out that the command is unknown
                    break;
            }
        }
    }

}
