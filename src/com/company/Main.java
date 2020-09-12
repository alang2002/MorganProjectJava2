package com.company;

import java.util.Scanner;

public class Main {

    // Setting up global variables to be used by all other methods in the class.
    // Set to static so there is one copy universally.
    static Scanner userInput;
    static String[][] employeeList = new String[10][3];
    static double[] paycheckList = new double[10];

    public static void main(String[] args) {
        String optionSelected;

        // Menu display
        System.out.println("--Morgan's Project--");
        System.out.println("Created by Aaron Lang");
        System.out.println("\nSelect an option to continue (1-4):");
        System.out.println("Option 1: Enter new employee information");
        System.out.println("Option 2: Display all employees");
        System.out.println("Option 3: Display a single employee");
        System.out.println("Option 4: Exit program");

        // Get input from user to select option they desire
        optionSelected = userInput.nextLine();

        // If statements to go to method of choice, or exit program if they selected 4
        if (optionSelected.equals("4")) {
            System.exit(0);
        } else if (optionSelected.equals("1")) {
            enterEmployee();
        }
        // else if... for options 1-3
        else {
            System.out.println("Not a valid option, exiting...");
            System.exit(0);
        }
    }

    // Method to enter a new employee into the database (String two-dimensional array)
    public static void enterEmployee() {
        String employeeFirstName, employeeLastName, employeeStatus = "";
        double paycheckAmount = 0.0;

        // Enter employee information
        // Use do while loops to have code run once, then check to make sure input from user was not null
        // If it was, ask for input again
        System.out.println("Option 1 selected: Enter new employee into database");
        System.out.println("\nEnter employee first name: ");
        do {
            System.out.println("Ensure that your input is not empty.\n");
            employeeFirstName = userInput.nextLine();
        }while (employeeFirstName.equals(""));

        System.out.println("\nEnter employee last name: ");
        do {
            System.out.println("Ensure that your input is not empty.\n");
            employeeLastName = userInput.nextLine();
        }while (employeeLastName.equals(""));

        System.out.println("\nEnter status of employee (FT for full time or PT for part time): ");
        do {
            System.out.println("Ensure that your input is not empty.\n");
            employeeStatus = userInput.nextLine();
        }while (employeeStatus.equals(""));

        System.out.println("\nEnter paycheck amount for employee: ");
        do {
            System.out.println("Ensure that your input is numerical and between 0.01 and 9999.99\n");
            paycheckAmount = userInput.nextDouble();
        }while (paycheckAmount > 0.01 && paycheckAmount < 9999.99);

        
    }
}
