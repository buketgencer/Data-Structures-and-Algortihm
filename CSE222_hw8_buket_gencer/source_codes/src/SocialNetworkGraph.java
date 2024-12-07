import java.util.*;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Represents a social network graph.
 * The graph contains people and their friendships.
 * Provides methods to add and remove people, add and remove friendships,
 * find the shortest path between two people, count clusters, and suggest friends.
 * The graph is implemented using adjacency lists.
 * The shortest path and clusters are found using breadth-first search (BFS).
 * Friend suggestions are based on mutual friends and common hobbies.
 * @author Buket Gencer
 */
public class SocialNetworkGraph {
    Map<String, Person> people = new HashMap<>();
    Map<Person, List<Person>> friendships = new HashMap<>();

    /**
     * Adds a person to the network.
     * @param name The name of the person.
     * @param age The age of the person.
     * @param hobbies The hobbies of the person.
     */
    public void addPerson(String name, int age, List<String> hobbies) {
        Person person = new Person(name, age, hobbies); // Create a new person
        String key = name + person.getTimestamp().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")); // Create a unique key for the person
        people.put(key, person); // Add the person to the network
        friendships.put(person, new ArrayList<>()); // Add an empty list of friends for the person
        System.out.println("Person added: " + person); // Print a message
    }

    /**
     * Removes a person from the network.
     * @param name The name of the person.
     * @param timestamp The timestamp of the person.
     */
    public void removePerson(String name, LocalDateTime timestamp) { // Remove a person from the network
        String key = name + timestamp.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        Person person = people.remove(key); // Remove the person from the network using the key
        if (person != null) { // If the person was found
            friendships.remove(person); // Remove the person's friendships
            for (List<Person> friends : friendships.values()) { // Remove the person from all friendship lists in the network
                friends.remove(person); // Remove the person from the friendship list of each friend
            }
            System.out.println("Person removed: " + person.getName() + " (Timestamp: " + person.getTimestamp() + ")"); 
        } else {
            System.out.println("Person not found: " + name + " at " + timestamp);
        }
    }

    /**
     * Adds a friendship between two people.
     * @param name1 The name of the first person.
     * @param timestamp1 The timestamp of the first person.
     * @param name2 The name of the second person.
     * @param timestamp2 The timestamp of the second person.
     */
    public void addFriendship(String name1, LocalDateTime timestamp1, String name2, LocalDateTime timestamp2) {
        String key1 = name1 + timestamp1.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")); // Create a unique key for the first person
        String key2 = name2 + timestamp2.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")); // Create a unique key for the second person
        Person person1 = people.get(key1); // Get the first person from the network
        Person person2 = people.get(key2); // Get the second person from the network 
        if (person1 != null && person2 != null) { // If both persons were found
            friendships.get(person1).add(person2);
            friendships.get(person2).add(person1);
            System.out.println("Friendship added between " + person1.getName() + " and " + person2.getName()); // Print a message
        } else {
            System.out.println("One or both persons not found in the network."); // Print an error message
        }
    }

    /**
     * Removes a friendship between two people.
     * @param name1 The name of the first person.
     * @param timestamp1 The timestamp of the first person.
     * @param name2 The name of the second person.
     * @param timestamp2 The timestamp of the second person.
     */
    public void removeFriendship(String name1, LocalDateTime timestamp1, String name2, LocalDateTime timestamp2) {
        String key1 = name1 + timestamp1.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")); // Create a unique key for the first person
        String key2 = name2 + timestamp2.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")); // Create a unique key for the second person 
        Person person1 = people.get(key1); // Get the first person from the network
        Person person2 = people.get(key2); // Get the second person from the network
        if (person1 != null && person2 != null) { // If both persons were found
            friendships.get(person1).remove(person2); // Remove the second person from the friendship list of the first person
            friendships.get(person2).remove(person1); // Remove the first person from the friendship list of the second person
            System.out.println("Friendship removed between " + person1.getName() + " and " + person2.getName()); // Print a message
        } else {
            System.out.println("One or both persons not found in the network.");
        }
    }

    /**
     * Finds the shortest path between two people.
     * @param startName The name of the start person.
     * @param startTimestamp The timestamp of the start person.
     * @param endName The name of the end person.
     * @param endTimestamp The timestamp of the end person.
     */
    public void findShortestPath(String startName, LocalDateTime startTimestamp, String endName, LocalDateTime endTimestamp) {
        String startKey = startName + startTimestamp.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")); // Create a unique key for the start person
        String endKey = endName + endTimestamp.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")); // Create a unique key for the end person
        Person start = people.get(startKey); // Get the start person from the network
        Person end = people.get(endKey); // Get the end person from the network

        if (start == null || end == null) {
            System.out.println("One or both persons not found in the network."); // Print an error message
            return;
        }

        Map<Person, Person> prev = bfs(start, end); // Find the shortest path between the start and end persons using BFS
        if (prev != null) { // If a path was found between the start and end persons 
            printPath(start, end, prev); // Print the shortest path
        } else {
            System.out.println("No path found between " + startName + " and " + endName); // Print an error message
        }
    }

    /**
     * Breadth-first search to find the shortest path between two people.
     * @param start The start person.
     * @param end The end person.
     * @return A map of previous nodes for each node in the path.
     */
    private Map<Person, Person> bfs(Person start, Person end) { // Find the shortest path between two people using BFS 
        Map<Person, Person> prev = new HashMap<>(); // Create a map of previous nodes for each node in the path 
        Queue<Person> queue = new LinkedList<>(); // Create a queue for BFS 
        Set<Person> visited = new HashSet<>(); // Create a set of visited nodes 

        queue.add(start); // Add the start person to the queue 
        visited.add(start); // Add the start person to the set of visited nodes 

        while (!queue.isEmpty()) { // While the queue is not empty
            Person person = queue.poll(); // Remove the first person from the queue 

            if (person.equals(end)) { // If the end person is found in the path
                return prev; // Return the map of previous nodes
            }

            for (Person neighbor : friendships.get(person)) { // For each neighbor of the current person in the path
                if (!visited.contains(neighbor)) { // If the neighbor has not been visited
                    queue.add(neighbor); // Add the neighbor to the queue
                    visited.add(neighbor); // Add the neighbor to the set of visited nodes
                    prev.put(neighbor, person);     // Add the neighbor to the map of previous nodes
                }
            }
        }
        return null;
    }

    /**
     * Prints the shortest path between two people.
     * @param start The start person.
     * @param end The end person.
     * @param prev A map of previous nodes for each node in the path.
     */
    private void printPath(Person start, Person end, Map<Person, Person> prev) { // Print the shortest path between two people
        List<Person> path = new ArrayList<>(); // Create a list of people in the path
        for (Person at = end; at != null; at = prev.get(at)) { // For each person in the path
            path.add(at); // Add the person to the list
        }
        Collections.reverse(path); // Reverse the order of people in the path
        System.out.println("Shortest path: " + path.stream().map(Person::getName).collect(Collectors.joining(" -> "))); // Print the shortest path
    }

    /**
     * Counts the number of clusters in the network.
     * A cluster is a group of people who are connected to each other.
     */
    public void countClusters() {
        Set<Person> visited = new HashSet<>(); // Create a set of visited people
        int clusterCount = 0; // Initialize the number of clusters to 0

        for (Person person : people.values()) { // For each person in the network
            if (!visited.contains(person)) {
                List<Person> cluster = new ArrayList<>(); // Create a list of people in the cluster
                bfs(person, visited, cluster); // Find the cluster of people connected to the current person
                clusterCount++; // Increment the number of clusters
                System.out.println("Cluster " + clusterCount + ": " + cluster.stream().map(Person::getName).collect(Collectors.joining(", ")));
            }
        }

        System.out.println("Number of clusters found: " + clusterCount); // Print the number of clusters
    }

    /**
     * Breadth-first search to find a cluster of people connected to each other.
     * @param start The start person.
     * @param visited A set of visited people.
     * @param cluster A list of people in the cluster.
     */
    private void bfs(Person start, Set<Person> visited, List<Person> cluster) { // Find a cluster of people connected to each other using BFS
        Queue<Person> queue = new LinkedList<>(); // Create a queue for BFS
        queue.add(start); // Add the start person to the queue 
        visited.add(start); // Add the start person to the set of visited nodes 
 
        while (!queue.isEmpty()) {
            Person current = queue.poll(); // Remove the first person from the queue 
            cluster.add(current); // Add the current person to the cluster

            for (Person neighbor : friendships.get(current)) { // For each neighbor of the current person in the cluster
                if (!visited.contains(neighbor)) { // If the neighbor has not been visited
                    queue.add(neighbor); // Add the neighbor to the queue
                    visited.add(neighbor);  // Add the neighbor to the set of visited nodes
                } 
            }
        }
    }

    /**
     * Suggests friends for a person based on mutual friends and common hobbies.
     * @param name The name of the person.
     * @param timestamp The timestamp of the person.
     * @param maxSuggestions The maximum number of friend suggestions.
     */
    public void suggestFriends(String name, LocalDateTime timestamp, int maxSuggestions) { // Suggest friends for a person based on mutual friends and common hobbies
        String key = name + timestamp.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")); 
        Person person = people.get(key);    // Get the person from the network

        if (person == null) {
            System.out.println("Person not found: " + name);
            return;
        }

        Map<Person, Double> scores = new HashMap<>(); // Create a map of scores for each person

        // Collect scores for all people in the network except the person themselves and their current friends
        for (Person candidate : people.values()) {
            if (!candidate.equals(person) && !friendships.get(person).contains(candidate)) { // If the candidate is not the person or a current friend
                double score = calculateScore(person, candidate); // Calculate the score for the person
                scores.put(candidate, score);
            }
        }

        List<Map.Entry<Person, Double>> sortedSuggestions = scores.entrySet().stream() // Sort the suggestions by score in descending order
                .sorted(Map.Entry.<Person, Double>comparingByValue().reversed())    
                .limit(maxSuggestions) // Limit the number of suggestions to the maximum number
                .collect(Collectors.toList()); // Collect the suggestions into a list 

        System.out.println("Suggested friends for " + name + ":"); // Print the suggested friends
        if (sortedSuggestions.isEmpty()) {
            System.out.println("No suggestions available.");
        } else {
            for (Map.Entry<Person, Double> entry : sortedSuggestions) { // For each suggestion
                Person suggestedFriend = entry.getKey();
                double score = entry.getValue();
                long mutualFriends = friendships.get(person).stream().filter(friendships.get(suggestedFriend)::contains).count();
                long commonHobbies = person.getHobbies().stream().filter(suggestedFriend.getHobbies()::contains).count();
                System.out.println(suggestedFriend.getName() + " (Score: " + score + ", " + mutualFriends + " mutual friends, " + commonHobbies + " common hobbies)");
            }
        }
    }

    /**
     * Calculates a score for a person based on mutual friends and common hobbies.
     * The score is calculated as the number of mutual friends plus half the number of common hobbies.
     * @param person The person to calculate the score for.
     * @param candidate The candidate person to compare with.
     * @return The score for the person.
     */
    private double calculateScore(Person person, Person candidate) { // Calculate a score for a person based on mutual friends and common hobbies
        long mutualFriends = friendships.get(person).stream()   // Get the list of friends of the person
                .filter(friendships.get(candidate)::contains) // Check  if the candidate is a friend of the person
                .count(); // Count the number of mutual friends
        long commonHobbies = person.getHobbies().stream() 
                .filter(candidate.getHobbies()::contains) // Check if the candidate has common hobbies with the person
                .count(); // Count the number of common hobbies
        return mutualFriends * 1.0 + commonHobbies * 0.5;
    }

    // I used this method to test the class methods if you wan to see relationships between people and see all the people in the network
    
    //You can add this method in the Main.java file to test the class methods. 
    public void printNetwork() {
        System.out.println("People in the network:");
        for (Person person : people.values()) {
            System.out.println(person);
        }

        System.out.println("\nFriendships in the network:");
        for (Map.Entry<Person, List<Person>> entry : friendships.entrySet()) {
            System.out.print(entry.getKey().getName() + " -> ");
            System.out.println(entry.getValue().stream().map(Person::getName).collect(Collectors.joining(", ")));
        }
    }
    
}
