
import java.sql.Timestamp;

/**
 * FileSystemElement is an abstract class that represents a file or a directory in the file system.
 * It contains the common attributes and methods that are shared by both files and directories.
 * @author Buket Gencer
 */
public abstract class FileSystemElement {
    /**
     * name is the name of the file or directory.
     */
    protected String name; 
    /**
     * dateCreated is the date when the file or directory was created.
     */
    protected Timestamp dateCreated; 
    /**
     * parent is the parent directory of the file or directory.
     */
    protected FileSystemElement parent; 

    /**
     * Constructor for the FileSystemElement class.
     * @param name The name of the file or directory.
     * @param parent The parent directory of the file or directory.
     */
    public FileSystemElement(String name, FileSystemElement parent) {
        this.name = name;
        this.parent = parent;
        this.dateCreated = new Timestamp(System.currentTimeMillis());
    }

    /**
     * Returns the name of the file or directory.
     * @return The name of the file or directory.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the date when the file or directory was created.
     * @return The date when the file or directory was created.
     */
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    /**
     * Returns the parent directory of the file or directory.
     * @return The parent directory of the file or directory.
     */
    public FileSystemElement getParent() {
        return parent;
    }

    /**
     * Sets the parent directory of the file or directory.
     * @param parent The parent directory of the file or directory.
     */
    public void setParent(FileSystemElement parent) {
        this.parent = parent;
    }

    /**
     * Abstract method to print the file or directory.
     * @param prefix The prefix to be printed before the name of the file or directory.
     */
    public abstract void print(String prefix); //To help print the directory tree
}
