//Class to manage all records in the LMS

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManagementSystem {

    // Using generics to store patrons in the system
    private ArrayList<Patron> patrons;

    // Searches for a patron by ID
    public Patron findPatron(String patronId) {

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

    //validation for ID
    //Makes sure there is 7 digits and doesn't already exist
    public boolean validateId(String patronId){
        if (!patronId.matches("\\d{7}")){
            return false;
        }
        //If findPatron returns null, the ID is consider unique
        return findPatron(patronId) == null;
    }

    //Validate overdue fine range
    public boolean validateFine(double overdueFine){
        return overdueFine >= 0 && overdueFine <= 250;
    }

    // Adds new patron
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
        if (validateFine(overdueFine)){
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




}
