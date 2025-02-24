package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.data.Student;

/**
 * Test class for SearchTableMap Checks the expected outputs of the Map abstract
 * data type behaviors when using a sorted array-based data structure that uses
 * binary search to locate entries based on the key of the entry
 *
 * @author Dr. King
 * @author Jack Farrell
 *
 */
public class SearchTableMapTest {

	/**
	 * Map for integer and string
	 */
	private Map<Integer, String> map;

	/**
	 * Map for student and integer
	 */
	private Map<Student, Integer> studentMap;

	/**
	 * Create a new instance of a search table map before each test case executes
	 */
	@Before
	public void setUp() {
		map = new SearchTableMap<Integer, String>();
		studentMap = new SearchTableMap<Student, Integer>();
	}

	/**
	 * Test the output of the put(k,v) behavior
	 */
	@Test
	public void testPut() {
		assertEquals(0, map.size());
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertEquals("SearchTableMap[3]", map.toString());
		assertEquals(1, map.size());
		assertNull(map.put(2, "string2"));
		assertEquals("SearchTableMap[2, 3]", map.toString());
		assertEquals(2, map.size());
		assertNull(map.put(1, "string1"));
		assertEquals("SearchTableMap[1, 2, 3]", map.toString());
		assertEquals(3, map.size());
	}

	/**
	 * Test the output of the get(k) behavior
	 */
	@Test
	public void testGet() {
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		assertFalse(map.isEmpty());
		assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());

		assertEquals("string1", map.get(1));
		assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());

		assertEquals("string2", map.get(2));
		assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());

		assertEquals("string3", map.get(3));
		assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
	}

	/**
	 * Test the output of the remove(k) behavior
	 */
	@Test
	public void testRemove() {
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		assertFalse(map.isEmpty());
		assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());

		assertEquals("string3", map.remove(3));
		assertEquals(map.size(), 4);

		assertNull(map.remove(9));
	}

	/**
	 * Tests Map abstract data type behaviors to ensure the behaviors work as
	 * expected when using arbitrary objects as keys
	 */
	@Test
	public void testStudentMap() {
		Student s1 = new Student("J", "K", 1, 0, 0, "jk");
		Student s2 = new Student("J", "S", 2, 0, 0, "js");
		Student s3 = new Student("S", "H", 3, 0, 0, "sh");
		Student s4 = new Student("J", "J", 4, 0, 0, "jj");
		Student s5 = new Student("L", "B", 5, 0, 0, "lb");

		assertNull(studentMap.put(s1, 1));
		assertNull(studentMap.put(s2, 2));
		assertNull(studentMap.put(s3, 3));
		assertNull(studentMap.put(s4, 4));
		assertNull(studentMap.put(s5, 5));

		assertEquals(1, s1.compareTo(s5));
		assertEquals(1, s2.compareTo(s3));
		assertEquals(1, s2.compareTo(s1));
		assertEquals(1, s4.compareTo(s5));
	}

	/**
	 * Test the output of the iterator behavior, including expected exceptions
	 */
	@Test
	public void testIterator() {
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));

		Iterator<Integer> it = map.iterator();

		assertTrue(it.hasNext());
		assertEquals(Integer.valueOf(1), it.next());

		assertTrue(it.hasNext());
		assertEquals(Integer.valueOf(2), it.next());

		assertTrue(it.hasNext());
		assertEquals(Integer.valueOf(3), it.next());

		assertTrue(it.hasNext());
		assertEquals(Integer.valueOf(4), it.next());

		assertTrue(it.hasNext());
		assertEquals(Integer.valueOf(5), it.next());

		assertFalse(it.hasNext());
		assertThrows(NoSuchElementException.class, () -> it.next());
	}

	/**
	 * Test the output of the entrySet() behavior, including expected exceptions
	 */
	@Test
	public void testEntrySet() {
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));

		Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
		assertTrue(it.hasNext());
		Map.Entry<Integer, String> entry = it.next();
		assertEquals(1, (int) entry.getKey());
		assertEquals("string1", (String) entry.getValue());

		assertTrue(it.hasNext());
		entry = it.next();
		assertEquals(Integer.valueOf(2), entry.getKey());
		assertEquals("string2", entry.getValue());

		assertTrue(it.hasNext());
		entry = it.next();
		assertEquals(Integer.valueOf(3), entry.getKey());
		assertEquals("string3", entry.getValue());

		assertTrue(it.hasNext());
		entry = it.next();
		assertEquals(Integer.valueOf(4), entry.getKey());
		assertEquals("string4", entry.getValue());

		assertTrue(it.hasNext());
		entry = it.next();
		assertEquals(Integer.valueOf(5), entry.getKey());
		assertEquals("string5", entry.getValue());

		assertFalse(it.hasNext());
		assertThrows(NoSuchElementException.class, () -> it.next());
	}

	/**
	 * Test the output of the values() behavior, including expected exceptions
	 */
	@Test
	public void testValues() {
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));

		Iterator<String> it = map.values().iterator();
		assertTrue(it.hasNext());
		assertEquals("string1", it.next());

		assertTrue(it.hasNext());
		assertEquals("string2", it.next());

		assertTrue(it.hasNext());
		assertEquals("string3", it.next());
		
		assertTrue(it.hasNext());
		assertEquals("string4", it.next());

		assertTrue(it.hasNext());
		assertEquals("string5", it.next());
		
	    assertFalse(it.hasNext());
	    assertThrows(NoSuchElementException.class, () -> it.next());	}
}
