package edu.ncsu.csc316.dsa.io;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Student reader test
 * 
 * @author Jack Farrell
 */
public class StudentReaderTest {

	/**
	 * Test read file
	 */
	@Test
	public void testReadFile() {
		Student[] contents = StudentReader.readInputAsArray("input/student_ascendingID.csv");
		assertEquals("Amber", contents[0].getFirst());
		assertEquals("Ara", contents[1].getFirst());
		assertEquals("Lacie", contents[2].getFirst());
		assertEquals("Idalia", contents[3].getFirst());
		assertEquals("Evelin", contents[4].getFirst());
		assertEquals("Lewis", contents[5].getFirst());
		assertEquals("Alicia", contents[6].getFirst());
		assertEquals("Tyree", contents[7].getFirst());
		assertEquals("Loise", contents[8].getFirst());
		assertEquals("Roxann", contents[9].getFirst());
		assertEquals("Nichole", contents[10].getFirst());
		assertEquals("Charlene", contents[11].getFirst());
		assertEquals("Shanti", contents[12].getFirst());
		assertEquals("Cristine", contents[13].getFirst());
		assertEquals("Tanner", contents[14].getFirst());
		assertEquals("Dante", contents[15].getFirst());
	}

//	/**
//	 * Invalid input
//	 */
//	@Test
//	public void testReadInputAsArrayFileNotFound() {
//		String invalidFilePath = "non_existent_file.csv";
//
//		try {
//			Student[] result = StudentReader.readInputAsArray(invalidFilePath);
//			fail("Expected IllegalArgumentException to be thrown for a non-existent file.");
//		} catch (IllegalArgumentException e) {
//			// Then
//			assertEquals("File not found: non_existent_file.csv (No such file or directory)", e.getMessage());
//		} catch (Exception e) {
//			fail("Expected IllegalArgumentException, but got: " + e);
//		}
//	}

}
