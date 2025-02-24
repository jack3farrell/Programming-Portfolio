package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Interface for handling the check conflict method
 */
public interface Conflict {

	/**
	 * Checks for conflict
	 * 
	 * @param possibleConflictingActivity what is being compared
	 * @throws ConflictException the exception thrown
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;
}
