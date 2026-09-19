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


    }


}
