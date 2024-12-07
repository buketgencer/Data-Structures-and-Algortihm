import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents the file system.
 * The file system contains a root directory and a current directory.
 * It provides methods to manage the file system such as creating files and directories,
 * deleting files and directories, changing directories, listing directory contents,
 * searching for files and directories, moving files and directories, and printing the directory tree.
 * @author Buket Gencer
 */
public class FileSystem {
    /**
     * The root directory of the file system.
     * The root directory is the top-level directory in the file system.
     * It has no parent directory.
     */
    private Directory root;
    /**
     * The current directory of the file system.
     * The current directory is the directory where the user is currently located.
     */
    private Directory currentDirectory;

    /**
     * Constructor for the FileSystem class.
     * Initializes the file system with a root directory.
     */
    public FileSystem() {
        //Initialize the file system with a root directory
        this.root = new Directory("root", null); // root directory has no parent
        this.currentDirectory = root; // Start from the root directory
    }

    /**
     * Returns the current directory of the file system.
     * @return The current directory of the file system.
     */
    public Directory getCurrentDirectory() {
        return this.currentDirectory;
    }

    /**
     * Sets the current directory of the file system.
     * @param currentDirectory The current directory to be set.
     */
    public void setCurrentDirectory(Directory currentDirectory) {
        this.currentDirectory = currentDirectory;
    }

    /**
     * Creates a new file in the specified directory.
     * @param name The name of the file to be created.
     * @param parent The parent directory where the file will be created.
     */
    public void createFile(String name, Directory parent) {
        File newFile = new File(name, parent); // Create a new file
        parent.addElement(newFile); // Add the file to the parent directory
        //if creating is successful, print the message
        System.out.println("File created: " + newFile.getName());
    }

    /**
     * Creates a new directory in the specified directory.
     * @param name The name of the directory to be created.
     * @param parent The parent directory where the directory will be created.
     */
    public void createDirectory(String name, Directory parent) {
        Directory newDirectory = new Directory(name, parent); // Create a new directory
        parent.addElement(newDirectory); // Add the directory to the parent directory
        //if creating is successful, print the message
        System.out.println("Directory created: " + newDirectory.getName() + "/");
    }

    /**
     * Deletes a file from the specified directory.
     * @param name The name of the file to be deleted.
     * @param parent The parent directory where the file is located.
     */
    public void deleteFile(String name, Directory parent) {
        FileSystemElement toDelete = null;
        for (FileSystemElement element : parent.getChildren()) {
            if (element.getName().equals(name) && element instanceof File) {
                toDelete = element;
                break;
            }
        }
        if (toDelete != null) {
            parent.removeElement(toDelete);
            System.out.println("File deleted: " + name);
        } else {
            System.out.println("File not found: " + name);
        }
    }
    
    /**
     * Deletes a directory from the specified directory.
     * @param name The name of the directory to be deleted.
     * @param parent The parent directory where the directory is located.
     */
    public void deleteDirectory(String name, Directory parent) {
        FileSystemElement toDelete = null;
        for (FileSystemElement element : parent.getChildren()) {
            if (element.getName().equals(name) && element instanceof Directory) {
                toDelete = element;
                break;
            }
        }
        if (toDelete != null) {
            recursiveDelete((Directory) toDelete);
            parent.removeElement(toDelete);
            System.out.println("Directory deleted: " + name + "/");
        } else {
            System.out.println("Directory not found: " + name + "/");
        }
    }
    
    /**
     * Recursively delete a directory and all its contents.
     * @param dir The directory to be deleted.
     */
    private void recursiveDelete(Directory dir) {
        for (FileSystemElement element : new LinkedList<>(dir.getChildren())) {
            if (element instanceof Directory) {
                recursiveDelete((Directory) element);
            }
            dir.removeElement(element);
        }
    }
    
    /**
     * Validates the path and returns the directory if it is valid.
     * @param path The path to be validated.
     * @return The directory if the path is valid, null otherwise.
     */
    public Directory validatePath(String path) {
        // Yolun mutlak olup olmadığını kontrol et (kök ile başlamalı)
        if (!path.startsWith("/")) {
            System.out.println("Please provide an absolute path starting with '/'");
            return null;
        }
        
        String[] parts = path.split("/");
        Directory checkDir = root; // Kök dizinden başlayarak kontrol et
    
        for (String part : parts) {
            if (part.isEmpty()) continue; // Boş kısımları atla (kök dizininden sonra gelen ilk boşluk gibi)
    
            boolean found = false;
            for (FileSystemElement element : checkDir.getChildren()) {
                if (element instanceof Directory && element.getName().equals(part)) {
                    checkDir = (Directory) element; // Bulunan dizine geç
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Invalid path: " + path);
                return null; // Yol geçersiz ise null döndür
            }
        }
        return checkDir; // Geçerli ise kontrol edilen son dizini döndür
    }

    /**
     * Finds a file or directory by name in the specified directory.
     * @param name The name of the file or directory to be found.
     * @param dir The directory where the file or directory will be searched.
     * @return The file or directory if found, null otherwise.
     */
    public FileSystemElement findElement(String name, Directory dir) {
        for (FileSystemElement element : dir.getChildren()) {
            if (element.getName().equals(name)) {
                return element;
            }
            if (element instanceof Directory) {
                FileSystemElement found = findElement(name, (Directory) element);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }
    
    /**
     * Searches for a file or directory by name in the file system.
     * @param name The name of the file or directory to be searched.
     * @return The path of the file or directory if found, null otherwise.
     */
    public String searchPath(String name) {
        return searchPathRecursive(name, root, "");
    }
    
    /**
     * Recursively searches for a file or directory by name in the specified directory.
     * @param name The name of the file or directory to be searched.
     * @param dir The directory where the file or directory will be searched.
     * @param path The path of the directory.
     * @return The path of the file or directory if found, null otherwise.
     */
    private String searchPathRecursive(String name, Directory dir, String path) {
        for (FileSystemElement element : dir.getChildren()) {
            String currentPath = path + "/" + element.getName();
            if (element.getName().equals(name)) {
                return currentPath;
            }
            if (element instanceof Directory) {
                String foundPath = searchPathRecursive(name, (Directory) element, currentPath);
                if (foundPath != null) {
                    return foundPath;
                }
            }
        }
        return null;
    }
    
    /**
     * Prints the directory tree starting from the root directory until reaching the current directory. 
     */
    public void printDirectoryTree() {
        // get the currentDirectory path from root and print it hierarchically (with indentations)
        String currentPath = getCurrentPath(currentDirectory);
        int indent = 0;
        //Tokenize the path
        String[] parts = currentPath.split("/");

        // print the path with indentations
        for (String part : parts) {
            for (int i = 0; i < indent; i++) {
                System.out.print("  ");
            }
            // print the * before the directory name
            System.out.print("* ");
            //if the part is current directory, print it with (Current directory)
            if (part.equals(currentDirectory.getName())) {
                System.out.println(part + "/ (Current directory)");
            } 
            //if the part is root directory, print it with * root /
            else if (part.equals("")) {
                System.out.println("root/");
            }
            else {
                System.out.println(part + "/");
            }
            indent++;
        }

        // Print the contents of the current directory with indentations
        // directory name ends with / and start with *, file name does not
        for (FileSystemElement element : currentDirectory.getChildren()) {
            if(element instanceof Directory) {
                for (int i = 0; i < indent; i++) {
                    System.out.print("  ");
                }
                System.out.print("* ");
                System.out.println(element.getName() + "/");
            } else {
                System.out.print("  ");
                for (int i = 0; i < indent; i++) {
                    System.out.print("  ");
                }
                System.out.println(element.getName());
            }    
        }
    }
    
    
    /**
     * Searches for a file or directory by name in the file system.
     * @param name The name of the file or directory to be searched.
     */
    public void search(String name) {
        System.out.println("Search query: " + name);
        String path = searchPath(name);
        if (path != null) {
            System.out.println("Searching from root...");
            System.out.println("Found: " + path);
        } else {
            System.out.println("File/directory not found.") ;
        }
    }

    /**
     * Lists the contents of the given directory.
     * @param dir The directory to be listed.
     */
    public void listContents(Directory dir) {
        // Listing contents of (current path):
        System.out.println("Listing contents of " + getCurrentPath(dir) + ":");
        if (dir.getChildren().isEmpty()) {
            System.out.println("The directory is empty.");
        } else {
            for (FileSystemElement element : dir.getChildren()) {
                if (element instanceof Directory) {
                    System.out.println("* " + element.getName() + "/");
                } else if (element instanceof File) {
                    System.out.println(element.getName());
                }
            }
        }
    } 


    /**
     * Returns the current path of the specified directory.
     * @param dir The directory to get the current path.
     * @return The current path of the directory.
     */
    public String getCurrentPath(Directory dir) {
        if (dir == root) { 
            return "/";
        }
        return getCurrentPathRecursive(dir);
    }

    /**
     * Recursively returns the current path of the specified directory.
     * @param dir The directory to get the current path.
     * @return The current path of the directory.
     */
    public String getCurrentPathRecursive(Directory dir) {
        if (dir.getParent() == root) {
            return "/" + dir.getName();
        }
        return getCurrentPathRecursive((Directory) dir.getParent()) + "/" + dir.getName();
    }

    /**
     * Changes the current directory to the specified path.
     * @param path The path to change the current directory.
     * @return True if the directory is changed successfully, false otherwise.
     */
    public boolean changeDirectory(String path) {
        if (path.equals("/")) {
            currentDirectory = root;  // Set current directory to root if the path is "/"
            return true;
        }
        String[] parts = path.split("/");
        Directory current = root;  // Start from the root directory
        for (String part : parts) {
            if (part.isEmpty()) continue; // Skip empty parts (which you get from leading "/")
            
            Directory next = null;
            for (FileSystemElement element : current.getChildren()) {
                if (element instanceof Directory && element.getName().equals(part)) {
                    next = (Directory) element;  // Found the next part of the path
                    break;
                }
            }
            if (next == null) {
                return false;  // Path is invalid if any part does not exist
            }
            current = next;  // Move to the next directory in the path
        }
        currentDirectory = current;  // Successfully navigated to the directory, so update currentDirectory
        return true;
    }
    
    /**
     * Returns the root directory of the file system.
     * @return The root directory of the file system.
     */
    public Directory getRoot() {
        return root;
    }

    /**
     * Searches for a file or directory by name in the file system.
     * @param name The name of the file or directory to be searched.
     * @calls searchElementRecursive method to search for the file or directory.
     */
    public void searchElement(String name) {
        System.out.println("Search query: " + name);
        System.out.println("Searching from root...");
        searchElementRecursive(root, name, new StringBuilder());
    }
    
    /**
     * Recursively searches for a file or directory by name in the specified directory.
     * @param dir The directory to be searched.
     * @param name The name of the file or directory to be searched.
     * @param path The path of the directory.
     */
    private void searchElementRecursive(Directory dir, String name, StringBuilder path) {
        for (FileSystemElement element : dir.getChildren()) {
            if (element.getName().equals(name)) {
                System.out.println("Found: " + path + "/" + element.getName());
            }
            if (element instanceof Directory) {
                path.append("/").append(element.getName());  
                searchElementRecursive((Directory) element, name, path);
                int lastSlashIndex = path.lastIndexOf("/");  // Remove the last part of the path
                path.delete(lastSlashIndex, path.length());
            }
        }
    }

    /**
     * Sorts the contents of the current directory by name.
     * @calls sortContentsByNameRecursive method to sort the contents by name.
     */
    public void sortContentsByDate() {
        Directory dir = currentDirectory;
        System.out.println("Sorted contents of " + getCurrentPath(dir) + " by date created:");
        sortContentsByDateCreatedRecursive(dir, new StringBuilder());
    }

    /**
     * Recursively sorts the contents of the specified directory by date created.
     * @param dir The directory to be sorted.
     * @param prefix The prefix to be printed before the name of the directory.
     */
    private void sortContentsByDateCreatedRecursive(Directory dir, StringBuilder prefix) {
        List<FileSystemElement> sorted = new ArrayList<>(dir.getChildren());
        sorted.sort(Comparator.comparing(FileSystemElement::getDateCreated));
        for (FileSystemElement element : sorted) {
            // if element is directory add * to the beginning and add / to the end, if it is file just print the name
            if (element instanceof Directory) {
                System.out.println(prefix + "* " + element.getName() + "/" + " (" + element.getDateCreated() + ")");
            } else {
                System.out.println(prefix + element.getName() + " (" + element.getDateCreated() + ")");
            }
        }
    }

    /**
     * Moves a file or directory to a new directory.
     * @param elementName The name of the file or directory to move.
     * @param newParentPath The path of the new parent directory.
     */
    public void move(String elementName, String newParentPath) {
        Directory newParent = validatePath(newParentPath);
        if (newParent == null) {
            System.out.println("Invalid new parent directory path.");
            return;
        }
    
        FileSystemElement elementToMove = findElement(elementName, currentDirectory);
        if (elementToMove == null) {
            System.out.println("Element not found: " + elementName);
            return;
        }
    
        if (elementToMove.getParent() == newParent) {
            System.out.println("Element is already in the specified directory.");
            return;
        }
    
        Directory check = newParent;
        while (check != null) {
            if (check == elementToMove) {
                System.out.println("Cannot move a directory into one of its subdirectories.");
                return;
            }
            check = (check.getParent() instanceof Directory) ? (Directory) check.getParent() : null;
        }
    
        if (elementToMove.getParent() instanceof Directory) {
            Directory oldParent = (Directory) elementToMove.getParent();
            oldParent.removeElement(elementToMove);
        }
    
        newParent.addElement(elementToMove);
        elementToMove.setParent(newParent);
    
        System.out.println("Successfully moved " + elementName + " to " + newParentPath);
    }
    
    

}
    

