package com.company;

import java.util.Scanner;

public class Main {

    // TO-DO: set up file reading here from rates.dat
    // put rates into 2d double array

    // Setting up global variables to be used by all other methods in the class.
    // Set to static so there is one copy universally.
    static Scanner userInput = new Scanner(System.in);
    static String[][] employeeList = new String[10][3];
    static double[] paycheckList = new double[10];
    static int employeeCounter;

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
        }
        else if (optionSelected.equals("1")) {
            if (employeeCounter == 10) {
                System.out.println("Error: too many employees, cannot add another to the database. Returning to menu...");
                main(null);
            }
            else {
                enterEmployee();
            }
        }
        else if (optionSelected.equals("2")) {
            displayAllEmployees();
        }
        // else if... for options 1-3
        else {
            System.out.println("Not a valid option, exiting...");
            System.exit(0);
        }
        userInput.close();
    }

    // Method to enter a new employee into the database (String two-dimensional array)
    public static void enterEmployee() {
        String employeeFirstName, employeeLastName, employeeStatus, repeat = "";
        double paycheckAmount = 0.0;

        // Enter employee information
        // Use do while loops to have code run once, then check to make sure input from user was not null
        // If it was, ask for input again
        System.out.println("Entering new employee into database.");
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

        System.out.println("Entering employee into database...");
        employeeList[employeeCounter][1] = employeeFirstName;
        employeeList[employeeCounter][2] = employeeLastName;
        employeeList[employeeCounter][3] = employeeStatus;
        paycheckList[employeeCounter] = paycheckAmount;
        employeeCounter++;
        System.out.println("Employee entered successfully!");

        System.out.println("Would you like to enter another new employee? Y/N");
        repeat = userInput.nextLine();
        if (repeat.toUpperCase().equals("Y")) {
            enterEmployee();
        }
        else {
            System.out.println("Returning to menu...");
            main(null);
        }
    }

    // Method to display all employees currently in the database
    public static void displayAllEmployees() {
        // Setting up labels for printing out employees in columns
        String label1 = "Name";
        String label2 = "Status";
        String label3 = "Paycheck";
        String label4 = "Bonus";
        // Variables to be used in printing out employees using for loops
        String firstName = null, lastName = null, fullName, status = null;
        double paycheck = 0, bonus = 0;

        // if statement to handle if there are no employees in the string array
        if (employeeCounter == 0) {
            System.out.println("No employees in database! Add some in the menu.");
            System.out.println("Returning to menu...");
            main(null);
        }
        // else, continue the code like normal
        // display in column format
        // Name           Status        Paycheck     Bonus
        // Smith, Joe     Full Time       450.00     27.00
        System.out.printf("%-10s %15s %20s %15s", label1, label2, label3, label4);
        System.out.println("----------------------------------------");

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 3; j++) {
                switch (j) {
                    case 0 -> firstName = employeeList[i][j];
                    case 1 -> lastName = employeeList[i][j];
                    case 2 -> status = employeeList[i][j];
                    default -> throw new IllegalStateException("Unexpected value: " + j);
                }
                lastName = lastName.concat(", ");
                fullName = lastName.concat(firstName);
                System.out.printf("%-10s %-15s %20.2f %15.2f", fullName, status, paycheck, bonus);
                //System.out.println(employeeList[i][j]);

                // TO-DO:
                // - Test to make sure formatting is correct.
                // - Test for bugs
                // - MORE TESTING
                // - Make sure there are no logical errors
                // - remember to add an employee every time you want to test displaying one, so make sure you can
                // actually add one and return back to the menu without error
            }
        }

    }
}
