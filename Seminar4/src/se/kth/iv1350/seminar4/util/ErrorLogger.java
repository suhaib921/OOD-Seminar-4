package se.kth.iv1350.seminar4.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * The class that handles logging errors to a file.
 * The methods implements the logger interface.
 */
public class ErrorLogger implements Logger {
 private PrintWriter logStream;
    
    /**
     * Initializes a new instance of ErrorLogger.
     * Creates or opens a text file for logging errors.
     * If an I/O error occurs, an error message is printed to the console.
     */
    public ErrorLogger() {
        try {
            logStream = new PrintWriter(new FileWriter("ErrorLog.txt", true));
        } catch (IOException ioe) {
            System.err.println("ERROR: Cannot create or open log file.");
            ioe.printStackTrace();
        }
    }
    
    /**
     * Logs information about the occured error.
     * @param infoAboutError A string that explains the error
     * @param exc The exception which provides the stack trace of the error
     */
    @Override
    public void logError(String infoAboutError, Exception exc) {
        logStream.println(infoAboutError);
        logStream.println("The Stack Trace:");
        exc.printStackTrace(logStream);
        logStream.println();
    }
        
}
