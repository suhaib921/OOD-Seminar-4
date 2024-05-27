package se.kth.iv1350.seminar4.controller;

/**
 * Exception for database server not running failures.
 * This exception is thrown when the database server is not running or cannot be reached.
 */
public class DatabaseServerNotRunningException extends DatabaseException {
    /**
     * Creates an instance of DatabaseServerNotRunningException with a specific error message.
     * 
     * @param message The error message describing the cause of the error.
     */
    public DatabaseServerNotRunningException(String message) {
        super(message);
    }
}
