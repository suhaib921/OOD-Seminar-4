package se.kth.iv1350.seminar4.controller;
/**
 * 
 * Exception for an invalid input of an item identifier.
 */

public class NoSuchItemFoundException extends Exception {
    public NoSuchItemFoundException(String message) {
        super(message);
    }
}