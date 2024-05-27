package se.kth.iv1350.seminar4.controller;
/**
 * 
 * Exception for an invalid input of an item identifier.
 * This exception is thrown when an item identifier provided does not match any items in the inventory.
 */

public class NoSuchItemFoundException extends Exception {
    /**
     * Creates an instance of NoSuchItemFoundException with a specific error message.
     * 
     * @param message The error message describing the cause of the wrong.
     */
    public NoSuchItemFoundException(String message) {
        super(message);
    }
}