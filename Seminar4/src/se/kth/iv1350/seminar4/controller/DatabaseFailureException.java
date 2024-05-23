package se.kth.iv1350.seminar4.controller;

/**
 * Exception for database failures.
 * This exception is thrown when there is a failure in database operations, such as connection issues or query failures.
 */
public class DatabaseFailureException extends Exception {
    public DatabaseFailureException(String message) {
        super(message);
    }
}
