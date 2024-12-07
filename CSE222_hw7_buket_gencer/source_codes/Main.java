import javax.swing.SwingUtilities; 

public class Main {
    public static void main(String[] args) {
        CommandGenerator.generateCommands(); // Generate the commands and write them to a file
        SwingUtilities.invokeLater(() -> {
            try {
                GUIVisualization gui = new GUIVisualization();  // Create the GUI object and set it to be visible
                StockDataManager dataManager = new StockDataManager(gui); // Create the StockDataManager object
                gui.setVisible(true); // Set the GUI to be visible
                dataManager.executeCommands("commands.txt"); // Execute the commands in the file
        
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("An error occurred in the GUI thread: " + e.getMessage());
            }
        });        
    }
}
