package se.kth.iv1350.seminar4.controller;

/**
 * Exception for database failures.
 * This exception is thrown when there is a failure in database operations, such as connection issues or query failures.
 */
public class DatabaseException extends Exception {
   /**
     * Creates an instance of DatabaseFailureException with a specific error message.
     * 
     * @param message The error message describing the cause of the wrong.
     */
    public DatabaseException(String message) {
        super(message);
    }
}
