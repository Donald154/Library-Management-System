/*
 * Donald Pughsley
 * CEN-3024C - Software Development I
 * 9/21/2026
 * LibraryManagementSystem.java
 *
 * This class manages all patron records for the Library Management System.
 * It stores Patron objects in an ArrayList and provides functionality for
 * locating, validating, adding, removing, displaying, and importing patrons.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManagementSystem {

    // Using generics to store patrons in the system
    private ArrayList<Patron> patrons;

    /*
     * method: findPatron
     * parameters: String patronId
     * return: Patron
     * purpose: Searches the patron list using the patron ID
     */
    public Patron findPatron(String patronId){

        for (Patron patron : patrons) {

            if (patron.getPatronId().equals(patronId)) {
                return patron;
            }
        }

        return null;
    }

    //Constructor
    public LibraryManagementSystem(){
        patrons = new ArrayList<>();
    }

    /*   validation for ID
     *   Makes sure there is 7 digits and doesn't already exist
     *   Regex used to make sure only 7 digits are accepted
     */

    public boolean validateId(String patronId){
        if (!patronId.matches("\\d{7}")){
            return false;
        }
        //If findPatron returns null, the ID is consider unique
        return findPatron(patronId) == null;
    }

    /*
     * method: validateFine
     * parameters: double overdueFine
     * return: boolean
     * purpose: Determines whether the patron's overdue fine is within the allowed range of $0 through $250.
     */
    public boolean validateFine(double overdueFine){
        return overdueFine >= 0 && overdueFine <= 250;
    }

    /*
     * method: addPatron
     * parameters: String patronId, String name, String address,double overdueFine
     * return: boolean
     * purpose: Validates the information provided for a new patron and if it's good it will add the patron to the LMS
     */
    public boolean addPatron(String patronId, String name, String address, double overdueFine){

        //Validate ID
        if (!validateId(patronId)){
            System.out.println("Invalid or duplicate ID.");
            return false;
        }
        //Validate name
        if (name == null || name.trim().isEmpty()){
            System.out.println("Patron name cannot be blank.");
            return false;
        }
        //Validate address
        if (address == null || address.trim().isEmpty()){
            System.out.println("Patron address cannot be empty");
            return false;
        }
        // Validate fine
        if (!validateFine(overdueFine)){
            System.out.println("Overdue fine must be between $0-$250.");
            return false;
        }

        //Creates a new patron object
        Patron patron = new Patron(patronId, name.trim(),address.trim(), overdueFine);

        //Adds patron to the list
        patrons.add(patron);
        return true;
    }

    //Removes a patron using their ID
    public boolean removePatron(String patronId) {

        Patron patron = findPatron(patronId);

        if (patron == null) {
            return false;
        }
        patrons.remove(patron);
        return true;
    }

    //Display functionality
    public void displayPatron(){
        System.out.println("\n========== Current Patron's  ==========");

        if (patrons.isEmpty()){
            System.out.println("No Patron is currently in the system");
            return;
        }

        for (Patron patron : patrons){
            System.out.println(patron);
            System.out.println("----------------------------");
        }
    }

    // Imports patrons from a txt file
    public void importPatrons(String filePath) {
        //New file
        File file = new File(filePath);

        //Try-Catch block for error exceptions
        try {
            Scanner fileScanner = new Scanner(file);

            int added = 0;
            int skipped = 0;

            while (fileScanner.hasNextLine()) {

                String line = fileScanner.nextLine().trim();

                // Will skip if lines are blank
                if (line.isEmpty()) {
                    continue;
                }

                //Expected format
                //ID-Name-Address-OverdueFine

                String[] data = line.split("-", 4);

                // Make sure all 4 fields exist
                if (data.length != 4){
                    System.out.println("Skipping invalid record: " + line);

                    //increment
                    skipped++;
                    continue;
                }

                String patronId = data[0].trim();
                String name = data[1].trim();
                String address = data[2].trim();
                double overdueFine;

                // Convert fine from String to double
                // Try-Catch block for error exceptions
                try {
                    overdueFine = Double.parseDouble(data[3].trim());

                } catch (NumberFormatException e){
                    System.out.println("Skipping record with invalid fine: " + line);
                    skipped++;
                    continue;
                }

                // Try adding the patron
                if (addPatron(patronId, name, address, overdueFine)) {
                    added++;
                } else {

                    System.out.println("Skipping invalid patron: " + patronId);
                    skipped++;
                }
            }

            fileScanner.close();

            System.out.println("\nImport complete. " + added + " patron(s) added.");

            if (skipped > 0) {

                System.out.println(skipped + " record(s) skipped.");
            }

        } catch (FileNotFoundException e) {

            System.out.println(
                    "Error: The specified file could not be found."
            );
        }
    }


}
