![Lexicon Logo](https://lexicongruppen.se/media/wi5hphtd/lexicon-logo.svg)

# Exception and Files

---

# 📑 Table of Contents

1. [Introduction to Exceptions](#1-introduction-to-exceptions)
2. [Types of Exceptions](#2-types-of-exceptions)
3. [Exception Hierarchy](#3-exception-hierarchy)
4. [Handling Exceptions](#4-handling-exceptions)
   - [try block](#try-block)
   - [catch block](#catch-block)
   - [finally block](#finally-block)
5. [Throw and Throws](#5-throw-and-throws)
6. [Custom (User-Defined) Exceptions](#6-custom-user-defined-exceptions)

---

## 1. Introduction to Exceptions

When writing a Java program, we normally expect our code to execute **step by step**, following the instructions we have written.

However, sometimes **unexpected problems occur while the program is running**.

For example:
- A user enters text when the program expects a number.
- The program tries to divide a number by zero.
- A file that the program needs does not exist.
- The program tries to access an invalid position in an array.
- A database connection fails.

These situations can interrupt the normal execution of a program.

### What is an Exception?

```mermaid
flowchart TD
    A["Program starts"] --> B["Execute instruction"]
    B --> C{"Exception occurs?"}

    C -->|No| D["Continue normally"]
    C -->|Yes| E["Exception thrown"]

    E --> F{"Matching handler found?"}

    F -->|Yes| G["Handle exception"]
    G --> H["Program may continue"]

    F -->|No| I["Exception propagates"]
    I --> J["May terminate if unhandled"]
```

An **exception** is an event that occurs during program execution and **interrupts the normal flow of instructions**.

**What happens?**

1. Java creates two integer variables, `a` and `b`.
2. The program tries to divide `10` by `0`.
3. Java throws an `ArithmeticException` because integer division by zero is not allowed.
4. The normal execution is interrupted.
5. The two `println` statements are never executed.

The program produces an error similar to:

```text
Exception in thread "main"
java.lang.ArithmeticException: / by zero
```

This happens because the exception was **not handled**.

### How can Exception Handling help?

Java allows us to **catch and handle exceptions** using `try-catch`.

Let's improve the previous example:

```java
void main() {
    int a = 10;
    int b = 0;

    try {
        int result = a / b;
        IO.println("Result: " + result);
    } catch (ArithmeticException e) {
        IO.println("Cannot divide by zero");
    }

    IO.println("Program finished");
}
```

**Output:**

```text
Cannot divide by zero
Program finished
```

**What changed?**

- The `try` block contains code that might cause an exception.
- When division by zero occurs, Java throws an `ArithmeticException`.
- The `catch` block handles that exception by displaying a message.
- The program continues executing after the `try-catch` statement.

Instead of terminating unexpectedly, the program handles the exception and continues executing.

### What information does an Exception contain?

In Java, exceptions are **objects** that provide useful information about a problem.

An exception can contain:

- **Exception type** — Identifies what kind of problem occurred, such as `ArithmeticException`.
- **Error message** — Describes the problem, such as `/ by zero`.
- **Stack trace** — Shows where the exception occurred and which methods were called before it happened.

```java
void main() {
    int a = 10;
    int b = 0;

    try {
        int result = a / b;
        IO.println("Result: " + result);
    } catch (ArithmeticException e) {

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
}
```

This information helps developers understand and debug problems.

### Why is Exception Handling important?

Exception handling helps us build applications that are **more reliable, maintainable, and user-friendly**.

| Benefit | Explanation |
|---|---|
| Prevents unexpected termination | Allows the application to respond to certain problems instead of stopping suddenly |
| Improves error messages | Provides clear information about what went wrong |
| Makes debugging easier | Helps developers identify where and why a problem occurred |
| Separates responsibilities | Keeps normal application logic separate from error-handling logic |
| Improves user experience | Allows applications to display helpful messages or offer alternatives |

**Important:** Exception handling does not automatically fix the underlying problem or guarantee that the application will continue. It gives the programmer an opportunity to decide how to respond.

Think of a program like a road trip:
- The normal flow is driving straight to your destination
- An exception is like a roadblock or accident
- Exception handling is taking a **detour** (alternative path/different route) instead of stopping the trip completely

Similarly, in Java, exception handling allows a program to **respond to unexpected situations instead of failing without a controlled response**.

## 2. Types of Exceptions

In Java, problems that interrupt program execution are commonly grouped into **three categories**:

1. **Checked Exceptions** — Exceptions that the compiler requires us to handle or declare.
2. **Unchecked Exceptions** — Exceptions that the compiler does not require us to handle or declare.
3. **Errors** — Serious problems that applications generally should not try to recover from.

Understanding these categories helps us decide **how to respond to different problems in a Java program**.

### Checked Exceptions

Checked exceptions are exceptions that **happen during program execution but are checked by the compiler at compile time**.  
Java requires the programmer to **handle these exceptions before running the program**.

If a checked exception is not handled, the program **will not compile**.

Checked exceptions force developers to **think about possible problems in advance**, making programs safer and more reliable.

#### Key points:
- Checked at **compile time**
- Must be handled using `try-catch` or declared using `throws`
- Usually related to **external resources** such as files, databases, or networks

#### Why they exist:
Checked exceptions ensure that programmers **do not ignore important problems**, such as missing files or failed database connections.

#### Examples (Compile-time error)

The following code will not compile because file and database operations throw checked exceptions that must be handled.

```java
void main(){

   // File not found (IOException)
   BufferedReader reader = Files.newBufferedReader(Paths.get("dir/lastnames.txt"));

   // Database connection error (SQLException)
   DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "user", "password");
}
```
> File and database operations depend on **external resources** that may not always be available.  
> Because these problems can happen outside the program’s control, Java marks them as **checked exceptions** to force the programmer to handle them.


---
### Unchecked Exceptions

Unchecked exceptions are exceptions that **occur during program execution and are not checked by the compiler at compile time**.  
They usually happen because of **programming mistakes** or invalid input.

Java does **not force** the programmer to handle unchecked exceptions, so the program can compile even if they are not handled.  
However, if they occur at runtime, the program may **stop running unexpectedly**.

Unchecked exceptions help developers **identify bugs in the code** that should be fixed rather than ignored.

#### Key points:
- Occur at **runtime**
- Not checked at compile time
- Subclasses of `RuntimeException`
- Usually caused by logic errors or invalid input

#### Why they exist:
Unchecked exceptions indicate **problems in the program logic**, such as using a variable incorrectly or assuming input is always valid.

---

#### Examples (Runtime error)

The following code **compiles successfully**, but may throw an unchecked exception **while the program is running**.

```java
void main() {

    // NullPointerException
    String name = null;
    IO.println(name.length());

    // ArithmeticException
    int result = 10 / 0;

    // ArrayIndexOutOfBoundsException
    int[] numbers = {1, 2, 3};
    IO.println(numbers[5]);
}
```

> Unchecked exceptions usually happen because of mistakes in the code,  
> and they should be fixed by correcting the program logic.

---

### Errors

Errors are **serious problems that occur during program execution** and are usually caused by the **Java Virtual Machine (JVM)** rather than the application code itself.

They represent conditions that are **beyond the control of the program**, and most applications **should not try to handle them**.

When an error occurs, the program usually **cannot continue running normally** and may need to be restarted or the system fixed.

#### Key points:
- Occur at **runtime**
- Caused by **system-level or resource problems**
- Not meant to be handled using `try-catch`
- Usually indicate critical failures

#### Why they exist:
Errors indicate **serious problems in the system environment**, such as memory shortages or infinite recursion, that cannot be safely recovered from by the application.

---

#### Examples

The following situations can cause errors during program execution:

```java
void main() {

    // StackOverflowError (infinite recursion)
    recursiveMethod();
}

void recursiveMethod() {
    recursiveMethod();
}
```

```java
// OutOfMemoryError
int[] largeArray = new int[Integer.MAX_VALUE];
```

> Errors usually require fixing the program design, increasing system resources,  
> or restarting the application, rather than handling them in code.

---

### Summary

- **Checked Exceptions** → Must be handled, checked at compile time
- **Unchecked Exceptions** → Runtime errors caused by code issues
- **Errors** → Serious system problems, not recoverable by the program

---

## 3. Exception Hierarchy

The **exception hierarchy** in Java shows how different exceptions and errors are related to each other.  
All exceptions and errors in Java are subclasses of the **`Throwable`** class.


```mermaid
classDiagram
   Throwable <|-- Error
   Throwable <|-- Exception

%% Errors
   Error <|-- OutOfMemoryError
   Error <|-- StackOverflowError

%% Exceptions
   Exception <|-- IOException
   Exception <|-- SQLException
   Exception <|-- RuntimeException

%% Runtime Exceptions (Unchecked)
   RuntimeException <|-- NullPointerException
   RuntimeException <|-- ArithmeticException
   RuntimeException <|-- ArrayIndexOutOfBoundsException
   RuntimeException <|-- NumberFormatException
   RuntimeException <|-- IllegalArgumentException
```

### `Throwable`
`Throwable` is the **root class** of the hierarchy.  
Only objects that extend `Throwable` can be **thrown** or **caught** in Java.

From `Throwable`, Java divides problems into two main branches:

---

### `Exception`
`Exception` represents **conditions that a program can usually handle**.  
Most application-level problems fall under this category.

- Includes both **checked** and **unchecked** exceptions
- Used to signal problems that occur during normal program execution

#### Checked Exceptions
- Subclasses of `Exception` (excluding `RuntimeException`)
- Must be handled at **compile time**
- Examples: `IOException`, `SQLException`

#### `RuntimeException` (Unchecked Exceptions)
- Subclass of `Exception`
- Occur at **runtime**
- Usually caused by programming mistakes
- Not required to be handled by the compiler

Common examples:
- `NullPointerException` → Using a null object
- `ArithmeticException` → Dividing by zero
- `ArrayIndexOutOfBoundsException` → Invalid array index
- `NumberFormatException` → Invalid string-to-number conversion
- `IllegalArgumentException` → Invalid method argument

---

### `Error`
`Error` represents **serious problems** that are **outside the control of the application**.  
These are usually caused by the Java Virtual Machine (JVM).

- Applications should **not attempt to handle** errors
- Often indicate system or memory failures

Common examples:
- `OutOfMemoryError` → JVM runs out of memory
- `StackOverflowError` → Too many nested method calls

---

## 4. Handling Exceptions

Java provides a structured way to handle runtime problems using the **try-catch-finally** mechanism.  
Instead of letting the program stop when an error occurs, Java allows the developer to **detect, handle, and recover** from exceptions.

Exception handling helps keep the application **stable**, **user-friendly**, and **safe**.

---

### try block

The `try` block contains **code that may cause an exception**.  
Java monitors this block, and if an exception occurs, the normal execution stops and control is passed to the `catch` block.

```java
try {
    int result = 10 / 0;
}
```

In this example, dividing by zero causes an `ArithmeticException`.

---

### catch block

The `catch` block is used to **handle the exception**.  
It runs only if an exception occurs in the corresponding `try` block.

```java
catch (ArithmeticException e) {
    IO.println("Cannot divide by zero");
}
```

- `ArithmeticException` specifies the type of exception to handle
- `e` is the exception object that contains error details

### finally block

The `finally` block contains code that **always executes**, whether an exception occurs or not.  
It is commonly used for **cleanup tasks** such as closing files or releasing resources.

```java
finally {
    IO.println("Execution completed");
}
```

### try-with-resources

The **try-with-resources** statement is a special form of the `try` block used to **automatically close resources** such as files, streams, or database connections.

Resources declared inside the `try` statement are **closed automatically**, even if an exception occurs.  
This reduces the need for a `finally` block.

```java
void main() {
    try (BufferedReader reader =
                 Files.newBufferedReader(Paths.get("dir/lastnames.txt"))) {

        String line = reader.readLine();
        IO.println(line);

    } catch (IOException e) {
        IO.println("Error reading file");
    }
}
```

#### Why use try-with-resources?
- Automatically closes resources
- Reduces boilerplate code
- Prevents resource leaks
- Makes code cleaner and safer

---

### Execution flow summary
- If no exception occurs → `try` → `finally`
- If an exception occurs → `try` → `catch` → `finally`
- If **try-with-resources** is used → resources are **closed automatically**, then `catch` (if needed) executes

> The `finally` block is guaranteed to run in most cases, making it ideal for cleanup operations.  
> In try-with-resources, the resource is closed automatically, so a `finally` block is usually not required.



---

## 5. Throw and Throws

Java provides two keywords, **`throw`** and **`throws`**, to work with exceptions.  
Although they sound similar, they are used for **different purposes**.

---

### throw

The `throw` keyword is used to **explicitly throw an exception** in the code.  
It is usually used when the programmer wants to **signal an error condition manually**.

- Used **inside a method or block** to enforce business rules
- Used to signal **logical or business-related errors**, not system failures
- Helps make the application **more logical, predictable, and easier to maintain**
- Commonly used for **input validation and rule checking**


```java
if(name == null || name.isBlank()){
        throw new IllegalArgumentException("Name cannot be null or empty");
}

if (amount <= 0) {
        throw new IllegalArgumentException("Amount must be greater than zero");
}
```

> `throw` is used when you decide that something is wrong and want to stop normal execution.

---

### throws

The `throws` keyword is used in a **method declaration** to indicate that the method **does not handle an exception itself**, but instead **passes the responsibility to the caller**.

It is commonly used when a method **cannot reasonably decide how to handle the problem**, such as when working with files, databases, or networks.

#### Key points:
- Used in the **method signature**
- Can declare **multiple exceptions**
- Mostly used with **checked exceptions**
- Shifts error-handling responsibility to the calling method

#### Real-world example (business-friendly)

```java
public void loadCustomerFile(String path) throws IOException {
    BufferedReader reader = Files.newBufferedReader(Paths.get(path));
    // read and process file
}
```

In this example:
- The method’s job is **only to read customer data**
- It does not decide what to do if the file is missing
- The **caller decides** whether to retry, show a message, or stop the process

```java
try {
    loadCustomerFile("customers.txt");
} catch (IOException e) {
    IO.println("Customer file could not be loaded");
}
```

> `throws` helps keep code **clean and well-structured** by separating **business logic** from **error-handling logic**.


- **`throw`** → actually throws an exception
- **`throws`** → declares that an exception may occur

---

## 6. Custom (User-Defined) Exceptions

Custom exceptions are exceptions **created by the programmer** to represent **specific business or application rules** that are not covered by Java’s built-in exceptions.

They make the code **more meaningful, readable, and easier to maintain**, because the exception name clearly describes what went wrong.

---

### Why use custom exceptions?

Built-in exceptions like `IllegalArgumentException` are generic.  
Custom exceptions allow us to express **business-specific problems** clearly.

Examples:
- A bank account does not have enough balance
- An order exceeds the allowed limit
- A user is not authorized to perform an action

---

### Example: Custom Exception Class

```java
public class InsufficientFundsException extends Exception {
    private Double balance;
    private Double amount;
    
    public InsufficientFundsException(String message, Double balance, Double amount) {
        super(message);
        this.balance = balance;
        this.amount = amount;
    }
    
    public InsufficientFundsException(String message) {
        super(message);
    }
    
    // Getter / Setter methods
    // Other methods
    
    @Override
    public String toString() {
        return "Insufficient funds: balance = " + balance + ", amount = " + amount;
    }
}
```

In this example:
- `InsufficientFundsException` represents a **business rule violation**
- It extends `Exception`, making it a **checked exception**
- The message explains the problem clearly

---

### Using the custom exception

```java
public void withdraw(double amount) throws InsufficientFundsException {
    if (amount > balance) {
        throw new InsufficientFundsException("Not enough balance in account", balance, amount);
    }
    balance -= amount;
}
```

Here:
- The exception is thrown when a **business rule is broken**
- The method remains clean and focused on its logic
- Error handling is left to the caller

---

### Why this improves application design

- Makes business rules **explicit**
- Improves code readability
- Separates **business logic** from **error handling**
- Helps developers understand the problem faster

> Custom exceptions allow us to describe business errors clearly instead of using generic exceptions.
