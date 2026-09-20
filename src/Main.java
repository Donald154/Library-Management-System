/*   Donald Pughsley
 *   CEN 3024C - Software Development 1
 *   9/21/2026
 *   Main.java
 *   This class is the main method and also controls the main interface for the LMS including accepting user input
 */

import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        /*
         * method: main
         * parameters: String[] args
         * return: void
         * purpose: Entry point for the Library Management System. Creates the LMS
         *          and Scanner objects, displays the interactive menu, accepts
         *          user selections, and calls the appropriate LMS methods until
         *          the user chooses to exit
         */

        Scanner scanner = new Scanner(System.in);

        // Create LMS object
        LibraryManagementSystem lms = new LibraryManagementSystem();

        System.out.println(
                "========================= Library Management System ========================="
        );

        boolean running = true;

        // Keeps the menu running until the user exits
        while (running) {

            System.out.println("\n================ LMS Menu ================");
            System.out.println("1. Import Patrons From File");
            System.out.println("2. Add Patron");
            System.out.println("3. Remove Patron");
            System.out.println("4. Display All Patrons");
            System.out.println("5. Exit");
            System.out.print("Enter selection: ");

            String choice = scanner.nextLine();

            /*
             * Each case used for choice selection
             * Each input is validated before continuing so the
             * user can correct invalid input without
             * restarting the entire add-patron process or continuing
             */
            switch (choice) {

                // Import patrons from a text file
                case "1":

                    System.out.println("\nThe text file should contain one patron per line " + "using the following format:");

                    System.out.println("ID-Name-Address-OverdueFine");

                    System.out.println("Examples:");

                    System.out.println("1245789-Sarah Jones-1136 Gorden Ave. Orlando, FL 32822-40.54");

                    System.out.println("3256897-Mason Arby-6060 Saginaw St. Casselberry, FL 34852-0");

                    System.out.println("4567891-Avery Jones-1919 Pine Lance Blvd. Oviedo, FL 32478-1.36");

                    System.out.println("----------------------------------------------------------------------------------");

                    System.out.print("Enter the file path for the patron text file: ");

                    String filePath = scanner.nextLine().trim();

                    // Removes quotes on file path to avoid errors
                    if (filePath.startsWith("\"") && filePath.endsWith("\"")){
                        filePath = filePath.substring(1, filePath.length() - 1);
                }

                    lms.importPatrons(filePath);

                    lms.displayPatron();

                    break;

                case "2":

                    String patronId;

                    // Keep asking until a valid, unique ID is entered
                    while (true) {

                        System.out.print("Enter Patron ID: ");
                        patronId = scanner.nextLine();

                        if (lms.validateId(patronId)) {
                            break;
                        }

                        System.out.println(
                                "Invalid or duplicate ID. Please enter a unique 7-digit ID."
                        );
                    }

                    String name;

                    // Keep asking until a valid name is entered
                    while (true) {

                        System.out.print("Enter Patron Name: ");
                        name = scanner.nextLine();

                        //Regex to ensure only letters are entered for the name
                        if (!name.trim().isEmpty()
                                && name.matches("[a-zA-Z ]+")) {

                            break;
                        }

                        System.out.println(
                                "Invalid name. Name cannot be blank or contains digits."
                        );
                    }

                    String address;

                    // Keep asking until a non-blank address is entered
                    while (true) {

                        System.out.print("Enter Patron Address: ");
                        address = scanner.nextLine();

                        if (!address.trim().isEmpty()) {
                            break;
                        }

                        System.out.println(
                                "Patron address cannot be blank. Please try again."
                        );
                    }

                    double overdueFine;

                    // Keep asking until a valid fine is entered
                    while (true) {

                        System.out.print("Enter Overdue Fine: ");

                        try {

                            overdueFine =
                                    Double.parseDouble(scanner.nextLine());

                            if (lms.validateFine(overdueFine)) {
                                break;
                            }

                            System.out.println(
                                    "Overdue fine must range between $0-$250."
                            );

                        } catch (NumberFormatException e) {

                            System.out.println(
                                    "Invalid fine amount. Please enter a number."
                            );
                        }
                    }

                    // Add patron after all fields are valid
                    if (lms.addPatron(
                            patronId,
                            name,
                            address,
                            overdueFine)) {

                        System.out.println(
                                "Patron added successfully."
                        );

                        lms.displayPatron();
                    }

                    break;

                // Remove a patron by ID
                case "3":

                    System.out.print(
                            "Enter Patron ID to remove: "
                    );

                    String removeId = scanner.nextLine();

                    if (lms.removePatron(removeId)) {

                        System.out.println(
                                "Patron removed successfully."
                        );

                    } else {

                        System.out.println(
                                "Patron ID not found."
                        );
                    }

                    // Display updated patron list
                    lms.displayPatron();

                    break;

                // Display all patrons
                case "4":

                    lms.displayPatron();

                    break;

                // Exit application
                case "5":

                    System.out.println(
                            "Exiting Library Management System."
                    );

                    running = false;

                    break;

                // Invalid menu selection
                default:

                    System.out.println(
                            "Invalid option. Please enter 1 through 5."
                    );
            }
        }

        scanner.close();
    }
}