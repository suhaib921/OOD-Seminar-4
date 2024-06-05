package se.kth.iv1350.seminar4.util;

/**
 *
 * The interface for the log handler.
 */

public interface Logger {

    /**
     * Logs information about the occured error.
     * @param msg A string that explains the error
     * @param exc The exception which provides the stack trace of the error
     */
    public void logError(String msg, Exception exc);
}