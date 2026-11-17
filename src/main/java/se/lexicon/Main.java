package se.lexicon;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.DriverManager;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    void main() {
        /*
        int a = 10;
        int b = 0;
        try{
            int result = a / b;
            IO.println("Result: " + result);
        } catch (ArithmeticException e){
            IO.println("Cannot divide by zero");

            // Gets the exception type (e.g., ArithmeticException)
            IO.println("Exception type: " + e.getClass().getSimpleName());

            // Gets the error message describing what went wrong (e.g., / by zero)
            IO.println("Error message: " + e.getMessage());

            // Prints the stack trace, showing where the exception occurred
            // including the class name, method name, and line number
            IO.println("Stack trace:");
            e.printStackTrace();

        }

        IO.println("Program finished");

         */

        // Checked Exceptions
        /*
        // File not found (IOException)
        BufferedReader reader = Files.newBufferedReader(Paths.get("dir/lastnames.txt"));
        // Database connection error (SQLException)
        DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "user", "password");
         */

        // Unchecked Exceptions
        /*
        // NullPointerException
        String name  = null;
        IO.println(name.length());

        // ArithmeticException
        int result = 10 / 0;

        // ArrayIndexOutOfBoundsException
        int[] numbers = {1, 2, 3};
        IO.println(numbers[5]);
         */

        // Errors
        /*
        //recursiveMethod();

        // OutOfMemoryError
        int[] largeArray = new int[Integer.MAX_VALUE];
         */

        double number = takeDecimalInput("Enter a decimal number: ");
        IO.println("number = " + number);
    }


    void recursiveMethod() {
        recursiveMethod();
    }


    public static double takeDecimalInput(String prompt) {
        while (true) {
            try {
                String input = IO.readln(prompt);
                double result = Double.parseDouble(input);
                return result;
            }  catch (NullPointerException e) {
                IO.println("Input should not be null.");
            } catch (NumberFormatException e) {
                IO.println("Invalid input. Please enter a valid decimal number.");
            }
        }
    }


}
