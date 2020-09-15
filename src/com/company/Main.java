package com.company;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    //TODO: Create and finish option 3 (search for and display single employee)

    // Setting up global variables to be used by all other methods in the class.
    static Scanner userInput = new Scanner(System.in);
    static String[][] employeeList = new String[10][3];
    static double[] paycheckList = new double[10];
    static double[][] bonusesList = new double[4][2];
    static int employeeCounter;

    public static void main(String[] args) {
        // Reading file to get bonus rates
        try {
            File ratesFile = new File("rates.dat");
            Scanner fileReader = new Scanner(ratesFile);
            fileReader.useDelimiter("[\n,]");
            double bonusRate;
            String b;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 2; j++) {
                    b = fileReader.next();
                    bonusRate = Double.parseDouble(b);
                    bonusesList[i][j] = bonusRate;
                }
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while trying to read a file.");
            e.printStackTrace();
        }

        String optionSelected;

        // Menu display
        System.out.println("\n--Morgan's Project--");
        System.out.println("Created by Aaron Lang");
        System.out.println("\nSelect an option to continue (1-4):");
        System.out.println("Option 1: Enter new employee information");
        System.out.println("Option 2: Display all employees");
        System.out.println("Option 3: Display a single employee");
        System.out.println("Option 4: Exit program");

        // Get input from user to select option they desire
        optionSelected = userInput.nextLine();

        // If statements to go to method of choice, or exit program if they selected 4
        switch (optionSelected) {
            case "4":
                System.exit(0);
            case "1":
                if (employeeCounter == 10) {
                    System.out.println("Error: too many employees, cannot add another to the database. Returning to menu...");
                    main(null);
                } else {
                    enterEmployee();
                }
                break;
            case "2":
                displayAllEmployees();
                break;
            // else if... for options 1-3
            default:
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
            System.out.println("Ensure that your input is not empty.");
            employeeFirstName = userInput.nextLine();
            employeeFirstName = employeeFirstName.strip();
        }while (employeeFirstName.equals(""));

        System.out.println("\nEnter employee last name: ");
        do {
            System.out.println("Ensure that your input is not empty.");
            employeeLastName = userInput.nextLine();
            employeeLastName = employeeLastName.strip();
        }while (employeeLastName.equals(""));

        System.out.println("\nEnter status of employee (FT for full time or PT for part time): ");
        do {
            System.out.println("Ensure that your input is not empty.");
            employeeStatus = userInput.nextLine();

            // changing status to full name for printing out later when displaying all employees
            if (employeeStatus.equals("FT")) {
                employeeStatus = "Full Time";
            }
            else if (employeeStatus.equals("PT")) {
                employeeStatus = "Part Time";
            }
            //TODO: Rewrite this statement to instead not ask for their status again, but capitalize the string so it accepts it regardless

            // else statement to catch if they misspell FT or PT (e.g. "Ft")
            else {
                System.out.println("Please enter either FT or PT.");
                // resetting employeeStatus to empty so loop runs again
                employeeStatus = "";
            }
        }while (employeeStatus.equals(""));

        System.out.println("\nEnter paycheck amount for employee: ");
        do {
            System.out.println("Ensure that your input is numerical and between 0.01 and 9999.99");
            paycheckAmount = userInput.nextDouble();
        }while (paycheckAmount < 0.01 && paycheckAmount > 9999.99);

        System.out.println("Entering employee into database...");
        employeeList[employeeCounter][0] = employeeFirstName;
        employeeList[employeeCounter][1] = employeeLastName;
        employeeList[employeeCounter][2] = employeeStatus;
        paycheckList[employeeCounter] = paycheckAmount;
        employeeCounter++;
        System.out.println("Employee entered successfully!");

        System.out.println("Would you like to enter another new employee? Y/N");
        repeat = userInput.nextLine();
        if (repeat.toUpperCase().equals("Y")) {
            enterEmployee();
        }
        else {
            System.out.println("Returning to menu...\n\n");
            main(null);
        }
        userInput.close();
    }

    // Method to display all employees currently in the database
    public static void displayAllEmployees() {
        // Setting up labels for printing out employees in columns
        String label1 = "Name";
        String label2 = "Status";
        String label3 = "Paycheck";
        String label4 = "Bonus";
        // Variables to be used in printing out employees using for loops
        String firstName = null;
        String lastName = null;
        String fullName;
        String status = null;
        String totalBonusLabel = "Total Bonuses Paid: ";
        double paycheck = 0;
        double bonusRate = 0;
        double bonus = 0;
        double totalBonusesPaid = 0;

        // if statement to handle if there are no employees in the string array
        if (employeeCounter == 0) {
            System.out.println("No employees in database! Add some in the menu.");
            System.out.println("Returning to menu...\n");
            main(null);
        }
        // else, continue the code like normal
        // display in column format
        // Name           Status        Paycheck     Bonus
        // Smith, Joe     Full Time       450.00     27.00
        System.out.printf("%-10s %15s %20s %15s", label1, label2, label3, label4);
        System.out.println("\n----------------------------------------------------------------");

        // do not run more times than there are number of employees, otherwise will return null
        for (int i = 0; i < employeeCounter; i++) {
            for (int j = 0; j < 3; j++) {
                switch (j) {
                    case 0 -> firstName = employeeList[i][j];
                    case 1 -> lastName = employeeList[i][j];
                    case 2 -> status = employeeList[i][j];
                    default -> throw new IllegalStateException("Unexpected value: " + j);
                }
            }

            fullName = lastName + ", " + firstName;
            paycheck = paycheckList[i];

            // Large nested decision structure to determine bonus rate for employee
            if (paycheck >= 0.01 && paycheck <= 100.00) {
                if (status.equals("Part Time")) {
                    bonusRate = bonusesList[0][0];
                }
                else {
                    bonusRate = bonusesList[0][1];
                }
            }
            else if (paycheck >= 100.01 && paycheck <= 300.00) {
                if (status.equals("Part Time")) {
                    bonusRate = bonusesList[1][0];
                }
                else {
                    bonusRate = bonusesList[1][1];
                }
            }
            else if (paycheck >= 300.01 && paycheck <= 500.00) {
                if (status.equals("Part Time")) {
                    bonusRate = bonusesList[2][0];
                }
                else {
                    bonusRate = bonusesList[2][1];
                }
            }
            else if (paycheck >= 500.01 && paycheck <= 9999.99) {
                if (status.equals("Part Time")) {
                    bonusRate = bonusesList[3][0];
                }
                else {
                    bonusRate = bonusesList[3][1];
                }
            }
            bonus = paycheck * bonusRate;
            totalBonusesPaid += bonus;

            System.out.printf("%s %" + String.valueOf(28 - fullName.length()) +
                    "s %" + String.valueOf(26 - status.length()) + ".2f %" + String.valueOf(15) + ".2f\n",
                    fullName, status, paycheck, bonus);
        }
        System.out.println("----------------------------------------------------------------");
        System.out.printf("%s %" + (62 - totalBonusLabel.length()) + ".2f\n", totalBonusLabel, totalBonusesPaid);
        main(null);
    }
}
