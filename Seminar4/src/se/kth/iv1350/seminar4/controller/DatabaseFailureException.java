package se.kth.iv1350.seminar4.controller;

/**
 * Exception for database failures.
 */
public class DatabaseFailureException extends Exception {
    public DatabaseFailureException(String message) {
        super(message);
    }
}
