//Main application

import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        //Create LMS object
        LibraryManagementSystem lms = new LibraryManagementSystem();

        System.out.println("========================= Library Management System =========================");

        //Ask user for patron txt file
        System.out.println("Enter the file path for the patron text file");
        String filePath = scanner.nextLine();

        //Import patrons
        lms.importPatrons(filePath);

        //Display imported patrons
        lms.displayPatron();
        boolean running = true;

        //Keeps the menu running until the user exist
        while (running) {

            System.out.println("\n================ LMS Menu ================");
            System.out.println("1. Add Patron");
            System.out.println("2. Remove Patron");
            System.out.println("3. Display All Patrons");
            System.out.println("4. Exit");
            System.out.print("Enter selection: ");

            String choice = scanner.nextLine();

            switch (choice) {

                // Add a new patron
                case "1":

                    System.out.print("Enter Patron ID: ");
                    String patronId = scanner.nextLine();

                    System.out.print("Enter Patron Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Patron Address: ");
                    String address = scanner.nextLine();

                    System.out.print("Enter Overdue Fine: ");

                    try {

                        double overdueFine =
                                Double.parseDouble(scanner.nextLine());

                        if (lms.addPatron(
                                patronId,
                                name,
                                address,
                                overdueFine)) {

                            System.out.println(
                                    "Patron added successfully."
                            );

                            // Display updated patron list
                            lms.displayPatron();
                        }

                    } catch (NumberFormatException e) {

                        System.out.println(
                                "Invalid fine amount. Please enter a number."
                        );
                    }

                    break;

                // Remove a patron by ID
                case "2":

                    System.out.print(
                            "Enter Patron ID to remove: "
                    );

                    String removeId = scanner.nextLine();

                    if (lms.removePatron(removeId)) {

                        System.out.println("Patron removed successfully."
                        );

                    } else {

                        System.out.println("Patron ID not found."
                        );
                    }

                    // Display updated patron list
                    lms.displayPatron();

                    break;

                // Display all patrons
                case "3":

                    lms.displayPatron();

                    break;

                // Exit application
                case "4":

                    System.out.println(
                            "Exiting Library Management System."
                    );

                    running = false;

                    break;

                // Invalid menu selection
                default:

                    System.out.println(
                            "Invalid option. Please enter 1 through 4."
                    );
            }
        }

        scanner.close();
    }
}



