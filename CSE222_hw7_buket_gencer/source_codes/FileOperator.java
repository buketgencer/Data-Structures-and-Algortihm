import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileOperator { // Class to read commands from a file

    private String filePath;    // Path to the file

    public FileOperator(String filePath) {  // Constructor for FileOperator
        this.filePath = filePath;   // Set the file path
    }

    public List<String> readCommands() {    // Method to read the commands from the file
        List<String> commands = new ArrayList<>();  // Create a list to store the commands
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {    // Try to read the file
            String line;    // Create a string to store each line
            while ((line = br.readLine()) != null) {    // Read each line
                commands.add(line); // Add the line to the list of commands
            }
        } catch (IOException e) {   // Catch any exceptions
            e.printStackTrace();    // Print the stack trace
        }
        return commands;    // Return the list of commands
    }
}
