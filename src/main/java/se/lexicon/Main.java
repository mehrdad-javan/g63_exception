package se.lexicon;

import se.lexicon.exception.ExceptionHandler;
import se.lexicon.model.BankAccount;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

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

        //double number = takeDecimalInput("Enter a decimal number: ");
        //IO.println("number = " + number);


        // readTextFile();

        // readImageAndCopy();

        // writeTextToFile();

        try {
            BankAccount account = new BankAccount(1001, 100);
            account.deposit(-50);
            account.withdraw(350);
            IO.println(account);
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }


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
            } catch (NullPointerException e) {
                IO.println("Input should not be null.");
            } catch (NumberFormatException e) {
                IO.println("Invalid input. Please enter a valid decimal number.");
            }
        }
    }

    public static void readTextFile() {
        try {

            // Files.readAllLines();
            // Read the entire file into memory at once and returns List of String
            // Easy to use and beginner-friendly
            // Best for SMALL files

            // Files.newBufferedReader();
            // It reads the file line by line
            // Suitable for LARGE files

            // Files.lines();
            // Returns a stream of data

            Path path = Path.of("dir/lastnames.txt");
            // Relative path: defined relative to the current working directory
            // dir/lastnames.txt

            // Absolute path: full path string from the root of the file system
            // D:\lexicon\g63\g63_exception\dir\lastnames.txt

            // Path represent the location of a file in the file system


            List<String> lines = Files.readAllLines(path);
            lines.forEach(IO::println);

            IO.println("----------------------");
            Files.lines(path).forEach(IO::println);
            IO.println("----------------------");
            Files.newBufferedReader(path).lines().forEach(IO::println);

        } catch (NoSuchFileException e) {
            IO.println("Error: File not found or invalid path - " + e.getMessage());
        } catch (IOException e) {
            IO.println("Error reading file.");
            IO.println("Reason: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public static void readImageAndCopy() {
        try {
            Path sourcePath = Paths.get("source/java_logo.png");
            Path destinationPath = Paths.get("destination/java_logo.png");
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (NoSuchFileException | FileAlreadyExistsException e) {
            IO.println("File Error:" + e.getMessage());
        } catch (IOException e) {
            IO.println("Error copying file.");
            IO.println("Reason: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public static void writeTextToFile() {
        BufferedWriter writer = null;
        try {
            writer = Files.newBufferedWriter(
                    Paths.get("dir/lastnames.txt"),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
            writer.append("TestName");
            writer.append(",");
            IO.println("Data written successfully.");
            // writer.close();
        } catch (IOException e) {
            System.err.println("❌ Error closing writer.");
            e.printStackTrace();

        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void writeTextToFileNew() {
        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get("dir/lastnames.txt"), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        ) {
            writer.append("TestName");
            writer.append(",");
            IO.println("Data written successfully.");
        } catch (IOException e) {
            System.err.println("❌ Error closing writer.");
            e.printStackTrace();
        }
    }

}
