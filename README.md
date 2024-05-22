# OOD-Seminar-4

## Commands
sudo update-alternatives --config javac
cd /home/suhkth/Documents/javaOOP/OOD-Seminar-4/Seminar3/src/se/kth/iv1350/seminar3/
javac /home/suhkth/Documents/javaOOP/OOD-Seminar-4/Seminar3/src/se/kth/iv1350/seminar3/**/*.java
java -cp /home/suhkth/Documents/javaOOP/OOD-Seminar-4/Seminar3/src se.kth.iv1350.seminar3.startup.Main


## Short explanation about Exception

In object-oriented programming, exceptions are categorized into various types to handle different kinds of errors and exceptional conditions. The book "A First Course in Object-Oriented Development" categorizes exceptions into three main types:

1. **Checked Exceptions**:
   - **Definition**: These are exceptions that must be either caught or declared in the method's `throws` clause. They represent conditions that a reasonable application might want to catch.
   - **Usage**: Used for business logic errors, such as violations of business rules. For example, attempting to withdraw more money than the account balance might throw an `OverdraftException`.
   - **Example**: `AlreadyBookedException` in the car rental example, indicating that a car is already booked and cannot be booked again.
   - **Benefits**: Forces the programmer to handle potential error conditions, leading to more robust and reliable code.

2. **Unchecked Exceptions**:
   - **Definition**: These are exceptions that do not need to be declared in a method's `throws` clause. They are typically subclasses of `RuntimeException`.
   - **Usage**: Used for programming errors, such as logic errors or improper use of an API. Examples include `NullPointerException` and `ArrayIndexOutOfBoundsException`.
   - **Example**: `CarRegistryException` in the car rental example, indicating a failure in the underlying datastore.
   - **Benefits**: Simplifies method signatures and can lead to cleaner code when the exceptions represent truly unexpected conditions that should not occur if the program is correct.

3. **Errors**:
   - **Definition**: These are serious problems that a reasonable application should not try to catch. They are subclasses of `Error`, and most errors are abnormal conditions that are not expected to be recovered from.
   - **Usage**: Used for severe problems like `OutOfMemoryError` or `StackOverflowError` that usually indicate a problem with the runtime environment itself.
   - **Example**: Not explicitly mentioned in the book, but in general usage, they would be conditions that signal serious issues requiring intervention beyond normal application logic.

### Best Practices for Exception Handling:

1. **Choose between checked and unchecked exceptions**:
   - Use checked exceptions for recoverable conditions and business logic errors.
   - Use unchecked exceptions for programming errors and conditions that are not expected to be recovered from during normal operation.

2. **Use the correct abstraction level for exceptions**:
   - High-level layers should not need to catch low-level, detailed exceptions. Instead, convert them to more general exceptions appropriate for the higher level.

3. **Name the exception after the error condition**:
   - Exception names should clearly describe the error condition, such as `AlreadyBookedException`.

4. **Include information about the error condition**:
   - Exceptions should carry enough information to understand the error context, including messages and relevant data.

5. **Use functionality provided in `java.lang.Exception`**:
   - Inherit from `Exception` or `RuntimeException` and utilize their constructors to handle error messages and causes.

6. **Write Javadoc comments for all exceptions**:
   - Document the conditions under which exceptions are thrown using `@throws` tags in Javadoc.

7. **An object shall not change state if an exception is thrown**:
   - Ensure that an object's state remains unchanged if a method fails and throws an exception.

8. **Notify users and developers**:
   - Provide informative messages to users and log detailed error reports for developers.

9. **Write unit tests for the exception handling**:
   - Test that exceptions are thrown under the correct conditions and that they are handled properly.

These principles help create a robust and maintainable system by ensuring that exceptions are handled consistently and appropriately throughout the application     .






## What is a Logger?

A **logger** is a component used in software applications to record log messages, including errors, warnings, informational messages, and debugging details.
- Loggers help in monitoring, debugging, and auditing applications.
- They support different logging levels like DEBUG, INFO, WARN, ERROR, and FATAL.
- Implementing a logger typically involves defining an interface and providing concrete implementations for writing logs to files or other destinations.
- Using a logger in your application helps maintain a record of significant events and errors, aiding in diagnostics and maintaining application health.



## What is `SQLException`?

`SQLException` is a class in the `java.sql` package of Java's Standard Edition (SE) that is used to handle exceptions related to database operations. It is a checked exception that provides information on a database access error or other errors related to SQL operations.

### How Does `SQLException` Work?

`SQLException` works by providing detailed information about database errors that occur during the execution of SQL statements. It is typically thrown by methods in the `java.sql` package when there is a problem with accessing or interacting with a database.

### Key Features of `SQLException`

1. **Error Information**:
   - `SQLException` includes methods to retrieve detailed information about the error, such as:
     - `getMessage()`: Returns a description of the error.
     - `getSQLState()`: Returns the SQLState string, which is a standardized error code.
     - `getErrorCode()`: Returns a vendor-specific error code.
     - `getNextException()`: Retrieves the next `SQLException` in the chain, if there are multiple exceptions.

2. **Chaining Exceptions**:
   - SQL exceptions can be chained together, providing a linked list of exceptions. This is useful when multiple errors occur in a sequence of database operations.

3. **Handling Database Errors**:
   - `SQLException` allows developers to handle database-related errors in a structured way, ensuring that applications can respond appropriately to issues like connectivity problems, SQL syntax errors, or data integrity violations.

### Summary

`SQLException` is a key class in Java's `java.sql` package for handling database-related errors. It provides detailed information about SQL errors and allows for structured error handling and resource management in applications that interact with databases.