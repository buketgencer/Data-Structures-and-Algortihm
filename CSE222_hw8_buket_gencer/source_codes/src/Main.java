import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Main class for the social network analysis program.
 * This class provides a menu for interacting with the social network graph.
 */

public class Main {
    private static SocialNetworkGraph network = new SocialNetworkGraph();
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Main method for the social network analysis program.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        while (true) {
            System.out.println("===== Social Network Analysis Menu =====");
            System.out.println("1. Add person");
            System.out.println("2. Remove person");
            System.out.println("3. Add friendship");
            System.out.println("4. Remove friendship");
            System.out.println("5. Find shortest path");
            System.out.println("6. Suggest friends");
            System.out.println("7. Count clusters");
            System.out.println("8. Print network");
            System.out.println("9. Exit");
            System.out.print("Please select an option: ");

            try{
                int option = scanner.nextInt();
                scanner.nextLine();  // consume newline

                switch (option) {
                    case 1:
                        addPerson();
                        break;
                    case 2:
                        removePerson();
                        break;
                    case 3:
                        addFriendship();
                        break;
                    case 4:
                        removeFriendship();
                        break;
                    case 5:
                        findShortestPath();
                        break;
                    case 6:
                        suggestFriends();
                        break;
                    case 7:
                        countClusters();
                        break;
                    case 8:
                        network.printNetwork();
                        break;
                    case 9:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
                } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid input.");
                scanner.nextLine();  // Clear the invalid input
            }
            
        }
    }

    /**
     * Adds a person to the social network.
     * Prompts the user for the person's name, age, and hobbies.
     */
    private static void addPerson() {
        try {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            System.out.print("Enter age: ");
            int age = scanner.nextInt();
            scanner.nextLine();  // consume newline
            System.out.print("Enter hobbies (comma-separated): ");
            List<String> hobbies = Arrays.asList(scanner.nextLine().split(","));
            //add person to network to check if it is added or not
            network.addPerson(name, age, hobbies);
        } catch (InputMismatchException e) {
            System.out.println("Invalid format. Please enter valid data.");
            scanner.nextLine();  // Clear the invalid input
        }
    }

    /**
     * Removes a person from the social network.
     * Prompts the user for the person's name and timestamp.
     */
    private static void removePerson() {
        try {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            System.out.print("Enter timestamp: ");
            String timestampStr = scanner.nextLine();
            LocalDateTime timestamp = LocalDateTime.parse(timestampStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            network.removePerson(name, timestamp);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use 'yyyy-MM-dd HH:mm:ss'.");
        }
    }

    /**
     * Adds a friendship between two people in the social network.
     * Prompts the user for the names and timestamps of the two people.
     */
    private static void addFriendship() {
        try {
            System.out.print("Enter first person's name: ");
            String name1 = scanner.nextLine();
            System.out.print("Enter first person's timestamp: ");
            String timestampStr1 = scanner.nextLine();
            LocalDateTime timestamp1 = LocalDateTime.parse(timestampStr1, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            System.out.print("Enter second person's name: ");
            String name2 = scanner.nextLine();
            System.out.print("Enter second person's timestamp: ");
            String timestampStr2 = scanner.nextLine();
            LocalDateTime timestamp2 = LocalDateTime.parse(timestampStr2, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            network.addFriendship(name1, timestamp1, name2, timestamp2);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use 'yyyy-MM-dd HH:mm:ss'.");
        }
    }

    /**
     * Removes a friendship between two people in the social network.
     * Prompts the user for the names and timestamps of the two people.
     */
    private static void removeFriendship() {
        try {
            System.out.print("Enter first person's name: ");
            String name1 = scanner.nextLine();
            System.out.print("Enter first person's timestamp: ");
            String timestampStr1 = scanner.nextLine();
            LocalDateTime timestamp1 = LocalDateTime.parse(timestampStr1, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            System.out.print("Enter second person's name: ");
            String name2 = scanner.nextLine();
            System.out.print("Enter second person's timestamp): ");
            String timestampStr2 = scanner.nextLine();
            LocalDateTime timestamp2 = LocalDateTime.parse(timestampStr2, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            network.removeFriendship(name1, timestamp1, name2, timestamp2);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use 'yyyy-MM-dd HH:mm:ss'.");
        }
    }

    /**
     * Finds the shortest path between two people in the social network.
     * Prompts the user for the names and timestamps of the two people.
     * The shortest path is the fewest number of friendships between the two people.
     */
    private static void findShortestPath() {
        try {
            System.out.print("Enter start person's name: ");
            String startName = scanner.nextLine();
            System.out.print("Enter start person's timestamp): ");
            String startTimestampStr = scanner.nextLine();
            LocalDateTime startTimestamp = LocalDateTime.parse(startTimestampStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            System.out.print("Enter end person's name: ");
            String endName = scanner.nextLine();
            System.out.print("Enter end person's timestamp: ");
            String endTimestampStr = scanner.nextLine();
            LocalDateTime endTimestamp = LocalDateTime.parse(endTimestampStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            network.findShortestPath(startName, startTimestamp, endName, endTimestamp);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use 'yyyy-MM-dd HH:mm:ss'.");
        }
    }

    /**
     * Suggests friends for a person in the social network.
     * Prompts the user for the person's name, timestamp, and maximum number of suggestions.
     * The suggestions are people who share at least one hobby with the person.
     */
    private static void suggestFriends() {
        try {
            System.out.print("Enter person's name: ");
            String name = scanner.nextLine();
            System.out.print("Enter person's timestamp: ");
            String timestampStr = scanner.nextLine();
            LocalDateTime timestamp = LocalDateTime.parse(timestampStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            System.out.print("Enter maximum number of friends to suggest: ");
            int maxSuggestions = scanner.nextInt();
            scanner.nextLine();  // consume newline
            network.suggestFriends(name, timestamp, maxSuggestions);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use 'yyyy-MM-dd HH:mm:ss'.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number for maximum suggestions.");
            scanner.nextLine();  // Clear the invalid input
        }
    }

    private static void countClusters() {
        network.countClusters();
    }
}


/*
 * you can use the following code to test your implementation
 * you can add the following code to the addPerson method in the Main class
 *          network.addPerson("Jane Glenn", 24, Arrays.asList("a", "b", "c"));
            //wait 1 second to add another person
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            network.addPerson("Paul White", 24, Arrays.asList( "b", "c", "d"));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            network.addPerson("John Doe", 22, Arrays.asList("c", "d", "e"));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            network.addPerson("Bob Brown", 22, Arrays.asList("d", "e", "f", "g"));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            network.addPerson("David Kim", 24, Arrays.asList("g", "h", "i"));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            network.addPerson("Emily Davis", 24, Arrays.asList("i", "j", "k"));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            network.addPerson("Frank Wilson", 22, Arrays.asList("k", "l", "m"));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            network.addPerson("Alice Johnson", 22, Arrays.asList("n", "o", "p"));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            network.addPerson("Arya Stark", 22, Arrays.asList("p", "q", "r"));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            network.addPerson("Jamie Lannister", 22, Arrays.asList("r", "s", "t"));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


 */