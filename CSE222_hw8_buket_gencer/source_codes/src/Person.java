import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Represents a person in the social network.
 * A person has a name, age, hobbies, and a timestamp.
 * @author Buket Gencer
 */
public class Person {
    String name; 
    int age;
    List<String> hobbies;
    LocalDateTime timestamp;


    /**
     * Constructor for a person.
     * @param name The name of the person.
     * @param age The age of the person.
     * @param hobbies The hobbies of the person.
     */
    public Person(String name, int age, List<String> hobbies) {
        this.name = name;
        this.age = age;
        this.hobbies = new ArrayList<>(hobbies);
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Gets the name of the person.
     * @return The name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the age of the person.
     * @return The age of the person.
     */
    public List<String> getHobbies() {
        return hobbies;
    }

    /**
     * Gets the timestamp of the person.
     * @return The timestamp of the person.
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Returns a string representation of the person.
     * @return A string representation of the person.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return name + " (Age: " + age + ", Hobbies: " + hobbies + ", Timestamp: " + timestamp.format(formatter) + ")";
    }

    /**
     * Returns the hash code of the person.
     * @return The hash code of the person.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, timestamp);
    }

    /**
     * Compares two person objects for equality.
     * @param obj The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return Objects.equals(name, person.name) && Objects.equals(timestamp, person.timestamp);
    }
}
