import java.util.Scanner;

/**
 * Main class is the main class of the program. It contains the main method and the main menu of the program.
 * It is used to manage the file system.
 */
public class Main {
    
    /**
     * fileSystem is an instance of the FileSystem class that is used to manage the file system.
     */
    public static FileSystem fileSystem = new FileSystem();
    /**
     * Scanner is used to get user input from the console.
     */
    public static Scanner scanner = new Scanner(System.in);
    /**
     * Main method of the program. It displays the main menu and handles user input.
     */
    public static void main(String[] args)
    {

        while(true)
        {
            System.out.println("===== File System Management Menu =====");
            System.out.println("1. Change directory");
            System.out.println("2. List directory contents");
            System.out.println("3. Create file/directory");
            System.out.println("4. Delete file/directory");
            System.out.println("5. Move file/directory");
            System.out.println("6. Search file/directory");
            System.out.println("7. Print directory tree");
            System.out.println("8. Sort contents by date created");
            System.out.println("9. Exit");
            System.out.print("Please select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); //Consume the newline left by nextInt()

            switch(choice)
            {
                case 1:
                    changeDirectory();
                    break;
                case 2:
                    listDirectoryContents();
                    break;
                case 3:
                    createFileOrDirectory();
                    break;
                case 4:
                    deleteFileOrDirectory();
                    break;
                case 5:
                    moveFileOrDirectory();
                    break;
                case 6:
                    searchFileOrDirectory();
                    break;
                case 7:
                    printDirectoryTree();
                    break;
                case 8:
                    sortContentsByDate();
                    break;
                case 9:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * changeDirectory method is used to change the current directory of the file system.
     * It asks the user to enter the path of the directory to change to.
     * If the path is valid, it changes the current directory to the new directory.
     */
    private static void changeDirectory()
    {
        System.out.println("Current directory: " + fileSystem.getCurrentPath(fileSystem.getCurrentDirectory()));
        System.out.print("Enter the path of the directory you want to change to: ");
        String path = scanner.nextLine();
        boolean isChange = fileSystem.changeDirectory(path);
        if(isChange)
        {
            System.out.println("Current directory changed to: " + fileSystem.getCurrentPath(fileSystem.getCurrentDirectory()));
        }
        else
        {
            System.out.println("Invalid path. Please try again.");
        }
    }

    /**
     * listDirectoryContents method is used to list the contents of the current directory.
     * It calls the listContents method of the FileSystem class to list the contents of the current directory.
     */
    private static void listDirectoryContents()
    {
        fileSystem.listContents(fileSystem.getCurrentDirectory());
    }

    /**
     * createFileOrDirectory method is used to create a new file or directory in the current directory.
     * It asks the user to enter the name of the file or directory to create.
     * If the user chooses to create a file, it calls the createFile method of the FileSystem class.
     * If the user chooses to create a directory, it calls the createDirectory method of the FileSystem class.
     */
    private static void createFileOrDirectory()
    {
        System.out.print("Current directory: " + fileSystem.getCurrentPath(fileSystem.getCurrentDirectory()) + "\nCreate file or directory (f/d): ");
        String choice = scanner.nextLine();
        if(choice.equals("f"))
        {
            System.out.print("Enter name for new file: ");
            String name = scanner.nextLine();
            fileSystem.createFile(name, fileSystem.getCurrentDirectory());
        }
        else if(choice.equals("d"))
        {
            System.out.print("Enter name for new directory: ");
            String name = scanner.nextLine();
            fileSystem.createDirectory(name, fileSystem.getCurrentDirectory());
        }
        else
        {
            System.out.println("Invalid choice. Please try again.");
        }
    }
        
    /**
     * deleteFileOrDirectory method is used to delete a file or directory from the current directory.
     * It asks the user to enter the name of the file or directory to delete.
     */
    public static void deleteFileOrDirectory()
    {
        System.out.print("Current directory: " + fileSystem.getCurrentPath(fileSystem.getCurrentDirectory()) + "\nEnter name of file/directory to delete: ");
        String name = scanner.nextLine();
        FileSystemElement element = fileSystem.findElement(name, fileSystem.getCurrentDirectory());
        if (element != null) {
            if (element instanceof File) {
                fileSystem.deleteFile(name, fileSystem.getCurrentDirectory());
            } else if (element instanceof Directory) {
                fileSystem.deleteDirectory(name, fileSystem.getCurrentDirectory());
            }
        } else {
            System.out.println("File/directory not found. Please try again.");
            }
    }

    /**
     * moveFileOrDirectory method is used to move a file or directory to a new directory.
     * It asks the user to enter the name of the file or directory to move and the new directory path.
     * If the new directory path is valid, it calls the move method of the FileSystem class to move the file or directory.
     */
    public static void moveFileOrDirectory() {
        System.out.println("Current directory: " + fileSystem.getCurrentPath(fileSystem.getCurrentDirectory()));
        System.out.print("Enter the name of the file/directory to move: ");
        String name = scanner.nextLine();
        System.out.print("Enter the new directory path where to move: ");
        String newPath = scanner.nextLine();
    
        if (fileSystem.validatePath(newPath) != null) {  
            fileSystem.move(name, newPath);
            System.out.println("Moved '" + name + "' to '" + newPath + "'");
        } else {
            System.out.println("Invalid new directory path. Please try again.");
        }
    }
    
    /**
     * printDirectoryTree method is used to print the directory tree of the file system.
     * Path to current directory from root:
     */
    public static void printDirectoryTree()
    {
        fileSystem.printDirectoryTree();
    }

    /**
     * searchFileOrDirectory method is used to search for a file or directory in the file system.
     * It asks the user to enter the name of the file or directory to search.
     */
    public static void searchFileOrDirectory()
    {
        System.out.print("Enter the name of the file/directory to search: ");
        String name = scanner.nextLine();
        fileSystem.searchElement(name);
    }

    /**
     * sortContentsByDate method is used to sort the contents of the current directory by date created.
     * It calls the sortContentsByDate method of the FileSystem class to sort the contents of the current directory.
     */
    public static void sortContentsByDate()
    {
        fileSystem.sortContentsByDate();
    }

}

