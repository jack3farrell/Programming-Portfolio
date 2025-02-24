package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Test class for UnorderedLinkedMap Checks the expected outputs of the Map
 * abstract data type behaviors when using an unordered link-based list data
 * structure that uses the move-to-front heuristic for self-organizing entries
 * based on access frequency
 *
 * @author Dr. King
 * @author Jack Farrell
 *
 */
public class UnorderedLinkedMapTest {

	/**
	 * Iterator for the map
	 */
	private Map<Integer, String> map;

	/**
	 * Create a new instance of an unordered link-based map before each test case
	 * executes
	 */
	@Before
	public void setUp() {
		map = new UnorderedLinkedMap<Integer, String>();
	}

	/**
	 * Test the output of the put(k,v) behavior
	 */
	@Test
	public void testPut() {
		assertEquals(0, map.size());
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertEquals("UnorderedLinkedMap[3]", map.toString());
		assertEquals(1, map.size());
		assertNull(map.put(2, "string2"));
		assertEquals("UnorderedLinkedMap[2, 3]", map.toString());
		assertEquals(2, map.size());
		assertNull(map.put(1, "string1"));
		assertEquals("UnorderedLinkedMap[1, 2, 3]", map.toString());
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
		assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());

		assertEquals("string1", map.get(1));
		assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());

		assertEquals("string2", map.get(2));
		assertEquals("UnorderedLinkedMap[2, 1, 4, 5, 3]", map.toString());

		assertEquals("string3", map.get(3));
		assertEquals("UnorderedLinkedMap[3, 2, 1, 4, 5]", map.toString());

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
		assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());

		assertEquals("string3", map.remove(3));
		assertEquals(map.size(), 4);

		assertNull(map.remove(9));

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
		assertEquals(Integer.valueOf(4), it.next());

		assertTrue(it.hasNext());
		assertEquals(Integer.valueOf(2), it.next());

		assertTrue(it.hasNext());
		assertEquals(Integer.valueOf(5), it.next());

		assertTrue(it.hasNext());
		assertEquals(Integer.valueOf(3), it.next());

		assertFalse(it.hasNext());
		try {
			it.next();
			fail("Expected NoSuchElementException");
		} catch (NoSuchElementException e) {
			// Empty catch block
		}
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

		Iterator<Entry<Integer, String>> it = map.entrySet().iterator();

		assertTrue(it.hasNext());
		Map.Entry<Integer, String> entry = it.next();
		assertEquals(Integer.valueOf(1), entry.getKey());
		assertEquals("string1", entry.getValue());

		assertTrue(it.hasNext());
		entry = it.next();
		assertEquals(Integer.valueOf(4), entry.getKey());
		assertEquals("string4", entry.getValue());

		assertTrue(it.hasNext());
		entry = it.next();
		assertEquals(Integer.valueOf(2), entry.getKey());
		assertEquals("string2", entry.getValue());

		assertTrue(it.hasNext());
		entry = it.next();
		assertEquals(Integer.valueOf(5), entry.getKey());
		assertEquals("string5", entry.getValue());

		assertTrue(it.hasNext());
		entry = it.next();
		assertEquals(Integer.valueOf(3), entry.getKey());
		assertEquals("string3", entry.getValue());

		assertFalse(it.hasNext());
		try {
			it.next();
			fail("Expected NoSuchElementException");
		} catch (NoSuchElementException e) {
			// Empty catch block
		}
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
		assertEquals("string4", it.next());

		assertTrue(it.hasNext());
		assertEquals("string2", it.next());

		assertTrue(it.hasNext());
		assertEquals("string5", it.next());

		assertTrue(it.hasNext());
		assertEquals("string3", it.next());
		
	    assertFalse(it.hasNext());
	    try {
	        it.next();
	        fail("Expected NoSuchElementException");
	    } catch (NoSuchElementException e) {
	        // Empty catch block
	    }
	}
}