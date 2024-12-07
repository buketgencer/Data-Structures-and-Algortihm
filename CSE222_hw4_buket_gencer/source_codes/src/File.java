/**
 * File class that extends FileSystemElement
 * Represents a file in the file system.
 * @author Buket Gencer
 */
public class File extends FileSystemElement{
    
    public File(String name, FileSystemElement parent) {
        super(name, parent);
    }

    @Override
    public void print(String prefix) {
        System.out.println(prefix + "File: " + getName() + " created on " + getDateCreated());
    }
}
