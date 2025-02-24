package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test student class
 */
public class StudentTest {

	/**
	 * Student object 1
	 */
	private Student sOne;
	/**
	 * Student object 2
	 */
	private Student sTwo;
//	/**
//	 * Student object 3
//	 */
//	//private Student sThree;
//	/**
//	 * Student object 4
//	 */
//	//private Student sFour;
//	/**
//	 * Student object 5
//	 */
//	private Student sFive;

	/**
	 * Set up tests
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		// sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		// sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		// sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
	}

	/**
	 * Test set first
	 */
	@Test
	public void testSetFirst() {
		sOne.setFirst("newOne");
		assertEquals("newOne", sOne.getFirst());
	}

	/**
	 * Test set last
	 */
	@Test
	public void testSetLast() {
		sOne.setLast("newOne");
		assertEquals("newOne", sOne.getLast());
	}

	/**
	 * Test set ID
	 */
	@Test
	public void testSetId() {
		sOne.setId(100);
		assertEquals(100, sOne.getId());
	}

	/**
	 * Test set gpa
	 */
	@Test
	public void testSetGpa() {
		sOne.setGpa(3.51);
		assertEquals(3.51, sOne.getGpa(), 0.001);
	}

	/**
	 * Test set unity ID
	 */
	@Test
	public void testSetUnityID() {
		sOne.setUnityID("oneUnity");
		assertEquals("oneUnity", sOne.getUnityID());
	}

	/**
	 * Test for compareTo
	 */
	@Test
	public void testCompareTo() {
		assertTrue(sOne.compareTo(sTwo) < 0);
		assertTrue(sTwo.compareTo(sOne) > 0);
		assertTrue(sOne.compareTo(sOne) == 0);
		assertTrue(sTwo.compareTo(sTwo) == 0);
	}

	/**
	 * Hash code test
	 */
	@Test
	public void testHashCodeConsistency() {
		Student student = new Student("James", "Farrell", 12345, 15, 3.0, "jfarre");

		int hashCode1 = student.hashCode();
		int hashCode2 = student.hashCode();

		assertEquals(hashCode1, hashCode2);
	}

	/**
	 * To string test
	 */
	@Test
	public void testToStringWithAllFields() {
		Student student = new Student("James", "Farrell", 12345, 15, 3.0, "jfarre");

		String result = student.toString();

		assertEquals("Student [first=James, last=Farrell, id=12345, creditHours=15, gpa=3.0, unityID=jfarre]", result);
	}

	/**
	 * Seeing if it equals itself
	 */
	@Test
	public void testEqualsSameObject() {
		Student student = new Student("James", "Farrell", 12345, 15, 3.0, "jfarre");

		assertEquals(student, student);

		assertEquals(student.getCreditHours(), 15);
	}

}
