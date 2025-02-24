package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;

/**
 * Testing the insertion algorithm
 * 
 * @author Jack Farrell
 */
public class InsertionSorterTest {

//	/**
//	 * Integers sorted
//	 */
//	private Integer[] dataAscending = { 1, 2, 3, 4, 5 };
//	/**
//	 * Integers descending
//	 */
//	private Integer[] dataDescending = { 5, 4, 3, 2, 1 };
//	/**
//	 * Integers random
//	 */
//	private Integer[] dataRandom = { 4, 1, 5, 3, 2 };
//
//	/**
//	 * Strings sorted
//	 */
//	private String[] dataStringAscending = { "abc", "bac", "cab", "dad", "eve" };
//	/**
//	 * Strings in descending order
//	 */
//	private String[] dataStringDescending = { "eve", "dad", "cab", "bac", "abc" };
//	/**
//	 * Strings in unsorted order
//	 */
//	private String[] dataStringRandom = { "bac", "dad", "abc", "eve", "cab" };

	/**
	 * Student object 1
	 */
	private Student student1 = new Student("Jack", "Farrell", 200398868, 15, 2.9, "jjfarre2");
	/**
	 * Student object 2
	 */
	private Student student2 = new Student("Jarret", "Taylor", 200528392, 13, 2.4, "jtfarrel");
	/**
	 * Student object 3
	 */
	private Student student3 = new Student("Beatriz", "Santos", 200429402, 15, 4.0, "bpsantos");
	/**
	 * Student object 4
	 */
	private Student student4 = new Student("Alex", "Llanes", 200284929, 12, 1.4, "allanes2");
	/**
	 * Student object 5
	 */
	private Student student5 = new Student("Arthur", "Morgan", 200183920, 15, 3.2, "amorgan13");

	/**
	 * Student objects in sorted order by id
	 */
	private Student[] studentsAscendingID = { student5, student4, student1, student3, student2 };
	/**
	 * Student objects in descending order by id
	 */
	private Student[] studentsDescendingID = { student2, student3, student1, student4, student5 };
	/**
	 * Student objects in random order by id
	 */
	private Student[] studentsRandomID = { student1, student2, student3, student4, student5 };

	/**
	 * Student objects in sorted order by gpa
	 */
	private Student[] studentsAscendingGPA = { student3, student5, student1, student2, student4 };
	/**
	 * Student objects in descending order by gpa
	 */
	private Student[] studentsDescendingGPA = { student4, student2, student1, student5, student3 };
	/**
	 * Stuent objects in random order by gpa
	 */
	private Student[] studentsRandomGPA = { student1, student2, student3, student4, student5 };

	/**
	 * Student objects in sorted order by name
	 */
	private Student[] studentsAscending = { student4, student5, student3, student1, student2 };
	/**
	 * Student objects sorted in descending order by name
	 */
	private Student[] studentsDescending = { student2, student1, student3, student5, student4 };
	/**
	 * Student objects sorted in random order
	 */
	private Student[] studentsRandom = { student1, student2, student3, student4, student5 };


	/**
	 * Testing student objects by name
	 */
	@Test
	public void testSortStudentName() {
		Sorter<Student> studentNameSorter = new InsertionSorter<Student>(null);
		// Ascending
		studentNameSorter.sort(studentsAscending);
		assertEquals(student1, studentsAscending[0]);
		assertEquals(student4, studentsAscending[1]);
		assertEquals(student5, studentsAscending[2]);
		assertEquals(student3, studentsAscending[3]);
		assertEquals(student2, studentsAscending[4]);

		// Descending
		studentNameSorter.sort(studentsDescending);
		assertEquals(student1, studentsDescending[0]);
		assertEquals(student4, studentsDescending[1]);
		assertEquals(student5, studentsDescending[2]);
		assertEquals(student3, studentsDescending[3]);
		assertEquals(student2, studentsDescending[4]);

		// Random
		studentNameSorter.sort(studentsRandom);
		assertEquals(student1, studentsRandom[0]);
		assertEquals(student4, studentsRandom[1]);
		assertEquals(student5, studentsRandom[2]);
		assertEquals(student3, studentsRandom[3]);
		assertEquals(student2, studentsRandom[4]);
	}

	/**
	 * Testing student objects by GPA
	 */
	@Test
	public void testSortStudentGPA() {
		Sorter<Student> studentGPASorter = new InsertionSorter<Student>(new StudentGPAComparator());

		// Ascending
		studentGPASorter.sort(studentsAscendingGPA);
		assertEquals(student3, studentsAscendingGPA[0]);
		assertEquals(student5, studentsAscendingGPA[1]);
		assertEquals(student1, studentsAscendingGPA[2]);
		assertEquals(student2, studentsAscendingGPA[3]);
		assertEquals(student4, studentsAscendingGPA[4]);

		// Descending
		studentGPASorter.sort(studentsDescendingGPA);
		assertEquals(student3, studentsDescendingGPA[0]);
		assertEquals(student5, studentsDescendingGPA[1]);
		assertEquals(student1, studentsDescendingGPA[2]);
		assertEquals(student2, studentsDescendingGPA[3]);
		assertEquals(student4, studentsDescendingGPA[4]);

		// Random
		studentGPASorter.sort(studentsRandomGPA);
		assertEquals(student3, studentsRandomGPA[0]);
		assertEquals(student5, studentsRandomGPA[1]);
		assertEquals(student1, studentsRandomGPA[2]);
		assertEquals(student2, studentsRandomGPA[3]);
		assertEquals(student4, studentsRandomGPA[4]);

	}

	/**
	 * Testing student objects by ID
	 */
	@Test
	public void testSortStudentID() {
		Sorter<Student> studentIDSorter = new InsertionSorter<Student>(new StudentIDComparator());

		// Ascending
		studentIDSorter.sort(studentsAscendingID);
		assertEquals(student5, studentsAscendingID[0]);
		assertEquals(student4, studentsAscendingID[1]);
		assertEquals(student1, studentsAscendingID[2]);
		assertEquals(student3, studentsAscendingID[3]);
		assertEquals(student2, studentsAscendingID[4]);

		// Descending
		studentIDSorter.sort(studentsDescendingID);
		assertEquals(student5, studentsDescendingID[0]);
		assertEquals(student4, studentsDescendingID[1]);
		assertEquals(student1, studentsDescendingID[2]);
		assertEquals(student3, studentsDescendingID[3]);
		assertEquals(student2, studentsDescendingID[4]);

		// Random
		studentIDSorter.sort(studentsRandomID);
		assertEquals(student5, studentsRandomID[0]);
		assertEquals(student4, studentsRandomID[1]);
		assertEquals(student1, studentsRandomID[2]);
		assertEquals(student3, studentsRandomID[3]);
		assertEquals(student2, studentsRandomID[4]);
	}
}
