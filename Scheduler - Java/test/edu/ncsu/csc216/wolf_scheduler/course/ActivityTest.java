/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Testing the functionality of the Activity class
 */
class ActivityTest {

	/**
	 * Test method for check conflict with no actual conflict
	 */
	@Test
	public void testCheckConflictWithNoConflict() {
		Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330,
				1445);
		Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "TH", 1330,
				1445);

		assertDoesNotThrow(() -> a1.checkConflict(a2));
		assertDoesNotThrow(() -> a2.checkConflict(a1));
	}

	/**
	 * Test method for check conflict with a conflict
	 */
	@Test
	public void testCheckConflictWithConflict() {
		Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
		Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "M", 1330, 1445);

		Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
		assertEquals("Schedule conflict.", e1.getMessage());

		Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
		assertEquals("Schedule conflict.", e2.getMessage());

		// Conflict where the endTime for this is the same as the startTime for possiblyConflictingActivity.
		Activity a3 = new Course("CSC 217", "Software Development Fundamentals Lab", "001", 3, "jjfarre2", "W", 1200, 1330);
		Activity a4 = new Course("CSC 217", "Software Development Fundamentals Lab", "001", 3, "jjfarre2", "W", 1330, 1445);

		Exception e3 = assertThrows(ConflictException.class, () -> a3.checkConflict(a4));
		assertEquals("Schedule conflict.", e3.getMessage());

		Exception e4 = assertThrows(ConflictException.class, () -> a4.checkConflict(a3));
		assertEquals("Schedule conflict.", e4.getMessage());

		// Conflict where endTime is in between, fairly basic test of functionality just
		// to make sure my algorithm for overlapping meeting times is correct
		Activity a5 = new Course("CSC 316", "Data Strucutres and Algorithms", "001", 3, "jjfarre2", "MW", 1200, 1330);
		Activity a6 = new Course("CSC 316", "Data Strucutres and Algorithms", "001", 3, "jjfarre2", "MW", 1100, 1230);
		
		Exception e5 = assertThrows(ConflictException.class, () -> a5.checkConflict(a6));
		assertEquals("Schedule conflict.", e5.getMessage());

		Exception e6 = assertThrows(ConflictException.class, () -> a6.checkConflict(a5));
		assertEquals("Schedule conflict.", e6.getMessage());

	}

}
