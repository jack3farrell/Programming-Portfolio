package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * A custom exception class
 * 
 * @author Jack Farrell
 */
public class ConflictException extends Exception {

	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;

	/**
	 * Exception with default message
	 */
	public ConflictException() {
		super("Schedule conflict");
	}
	
	/**
	 * Exception message with a parameter
	 * @param message the custom message 
	 */
	public ConflictException(String message) {
		super(message);
	}
}
