

import java.util.LinkedList;
import java.util.List;
/**
 * Represents a directory in the file system.
 * A directory can contain other directories and files.
 * @author Buket Gencer
 */
public class Directory extends FileSystemElement {

    /**
     * The list of children of this directory.
     * The children can be directories or files.
     */
    private LinkedList<FileSystemElement> children;
    
    /**
     * Constructor for the Directory class.
     * @param name The name of the directory.
     * @param parent The parent directory of this directory.
     */
    public Directory(String name, FileSystemElement parent) {
        super(name, parent);
        this.children = new LinkedList<>();
    }

    /**
     * Adds a new element to the directory.
     * @param element The element to be added.
     */
    public void addElement(FileSystemElement element) {
        children.add(element);
    }

    /**
     * Removes an element from the directory.
     * @param element The element to be removed.
     */
    public void removeElement(FileSystemElement element) {
        children.remove(element);
    }

    /**
     * Prints the contents of the directory.
     * @param prefix The prefix to be printed before the name of the directory.
     */
    public void print(String prefix) {
        System.out.println(prefix + "Directory: " + getName());
        for (FileSystemElement element : children) {
            element.print(prefix + "  ");
        }
    }

    /**
     * Returns the list of children of this directory.
     * @return The list of children of this directory.
     */
    public List<FileSystemElement> getChildren() {
        return children;
    }
}