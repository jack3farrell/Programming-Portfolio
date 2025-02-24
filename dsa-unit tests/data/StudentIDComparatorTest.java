package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Student ID comparator test
 * 
 * @author Jack Farrell
 */
public class StudentIDComparatorTest {

	/**
	 * Student one
	 */
	private Student sOne;
	/**
	 * Student two
	 */
	private Student sTwo;
//	/**
//	 * Student three
//	 */
//	//private Student sThree;
//	/**
//	 * Student four
//	 */
//	//private Student sFour;
//	/**
//	 * Student five
//	 */
//	private Student sFive;

	/**
	 * Student id comparator
	 */
	private StudentIDComparator comparator;

	/**
	 * Set up for tests
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
//		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
//		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
//		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");

		comparator = new StudentIDComparator();
	}

	/**
	 * Comparing test
	 */
	@Test
	public void testCompare() {
		assertTrue(comparator.compare(sOne, sTwo) < 0);
		assertFalse(comparator.compare(sTwo, sOne) < 0);


	}


}
