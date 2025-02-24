package edu.ncsu.csc316.dsa.list;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ArrayBasedList. Checks the expected outputs of the List
 * abstract data type behaviors when using an array-based list data structure
 *
 * @author Dr. King
 * @author Jack Farrell
 *
 */
public class ArrayBasedListTest {

	/**
	 * A String list
	 */
	private List<String> list;

	/**
	 * Create a new instance of an array-based list before each test case executes
	 */
	@Before
	public void setUp() {
		list = new ArrayBasedList<String>();
	}

	/**
	 * Test the output of the add(index, e) behavior, including expected exceptions
	 */
	@Test
	public void testAddIndex() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		list.add(0, "one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		assertFalse(list.isEmpty());

		// Use the statements above to help guide your test cases
		// for data structures: Start with an empty data structure, then
		// add an element and check the accessor method return values.
		// Then add another element and check again. Continue to keep checking
		// for special cases. For example, for an array-based list, you should
		// continue adding until you trigger a resize operation to make sure
		// the resize operation worked as expected.

		try {
			list.add(15, "fifteen");
			fail("An IndexOutOfBoundsException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}

	}

	/**
	 * Test the output of the addLast behavior
	 */
	@Test
	public void testAddLast() {
		// Make sure list is empty
		assertTrue(list.isEmpty());

		// Add elements
		list.add(0, "one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		assertFalse(list.isEmpty());

		list.add(1, "two");
		assertEquals(2, list.size());
		assertEquals("two", list.get(1));
		assertFalse(list.isEmpty());

		list.addLast("three");
		assertEquals(3, list.size());
		assertEquals("three", list.get(2));
		assertFalse(list.isEmpty());

		// Test
		assertEquals(list.get(list.size() - 1), "three");
		assertEquals(list.get(2), "three");

	}

	/**
	 * Test the output of the last() behavior, including expected exceptions
	 */
	@Test
	public void testLast() {
		// Make sure list is empty
		assertEquals(list.size(), 0);

		// Test exceptions
		Exception e = assertThrows(IndexOutOfBoundsException.class, () -> {
			list.last();
		});

		// Index is a negative one because this is a last method
		assertEquals("Index is invalid: -1 (size=0)", e.getMessage());

		// Add elements
		list.add(0, "one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		assertFalse(list.isEmpty());

		list.add(1, "two");
		assertEquals(2, list.size());
		assertEquals("two", list.get(1));
		assertFalse(list.isEmpty());

		list.addLast("three");
		assertEquals(3, list.size());
		assertEquals("three", list.get(2));
		assertFalse(list.isEmpty());

		// Test
		assertEquals(list.get(list.size() - 1), "three");
		assertEquals(list.last(), "three");
	}

	/**
	 * Test the output of the addFirst behavior
	 */
	@Test
	public void testAddFirst() {
		// Make sure list is empty
		assertTrue(list.isEmpty());

		// Add elements
		list.add(0, "one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		assertFalse(list.isEmpty());

		list.add(1, "two");
		assertEquals(2, list.size());
		assertEquals("two", list.get(1));
		assertFalse(list.isEmpty());

		list.addFirst("three");
		assertEquals(3, list.size());
		assertEquals("three", list.get(0));
		assertFalse(list.isEmpty());

		// Test
		assertEquals(list.get(0), "three");
		assertEquals(list.size(), 3);
	}

	/**
	 * Test the output of the first() behavior, including expected exceptions
	 */
	@Test
	public void testFirst() {
		// Make sure list is empty
		assertTrue(list.isEmpty());

		// Test exceptions
		Exception e = assertThrows(IndexOutOfBoundsException.class, () -> {
			list.first();
		});

		assertEquals("Index is invalid: 0 (size=0)", e.getMessage());

		// Add elements
		list.add(0, "one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		assertFalse(list.isEmpty());

		list.add(1, "two");
		assertEquals(2, list.size());
		assertEquals("two", list.get(1));
		assertFalse(list.isEmpty());

		list.addFirst("three");
		assertEquals(3, list.size());
		assertEquals("three", list.get(0));
		assertFalse(list.isEmpty());

		// Test
		assertEquals(list.first(), "three");
		assertEquals(list.size(), 3);
	}

	/**
	 * Test the iterator behaviors, including expected exceptions
	 */
	@Test
	public void testIterator() {
		// Start with an empty list
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		// Create an iterator for the empty list
		Iterator<String> it = list.iterator();

		// Try different operations to make sure they work
		// as expected for an empty list (at this point)
		try {
			it.remove();
			fail("An IllegalStateException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
		assertFalse(it.hasNext());

		// Now add an element
		list.addLast("one");

		// Use accessor methods to check that the list is correct
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());
		assertEquals("one", list.get(0));

		// Create an iterator for the list that has 1 element
		it = list.iterator();

		// Try different iterator operations to make sure they work
		// as expected for a list that contains 1 element (at this point)
		assertTrue(it.hasNext());
		assertEquals("one", it.next());
		assertFalse(it.hasNext());
		try {
			it.next();
			fail("A NoSuchElementException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}

		// Check to make sure there is no next
		assertFalse(it.hasNext());

		// Add new string
		list.addLast("zero");

		// Test
		assertEquals(it.next(), "zero");
		assertEquals(list.size(), 2);

		// Remove using iterator and add new
		it.remove();
		list.addLast("two");

		// Test
		assertTrue(it.hasNext());
		assertEquals(it.next(), "two");

	}

	/**
	 * Test the output of the remove(index) behavior, including expected exceptions
	 */
	@Test
	public void testRemoveIndex() {
		// Make sure list is empty
		assertTrue(list.isEmpty());
		assertEquals(list.size(), 0);

		// Add elements
		list.add(0, "one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		assertFalse(list.isEmpty());

		list.add(1, "two");
		assertEquals(2, list.size());
		assertEquals("two", list.get(1));
		assertFalse(list.isEmpty());

		list.addFirst("three");
		assertEquals(3, list.size());
		assertEquals("three", list.get(0));
		assertFalse(list.isEmpty());

		// Test exceptions
		Exception e = assertThrows(IndexOutOfBoundsException.class, () -> {
			list.remove(3);
		});

		assertEquals("Index is invalid: 3 (size=3)", e.getMessage());

		// Remove element
		list.remove(1);

		// Test
		assertEquals(list.get(1), "two");
		assertEquals(list.size(), 2);
	}

	/**
	 * Test the output of the removeFirst() behavior, including expected exceptions
	 */
	@Test
	public void testRemoveFirst() {
		// Make sure list is empty
		assertTrue(list.isEmpty());
		assertEquals(list.size(), 0);

		// Test exceptions
		Exception e = assertThrows(IndexOutOfBoundsException.class, () -> {
			list.removeFirst();
		});

		assertEquals("Index is invalid: 0 (size=0)", e.getMessage());

		// Add elements
		list.add(0, "one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		assertFalse(list.isEmpty());

		list.add(1, "two");
		assertEquals(2, list.size());
		assertEquals("two", list.get(1));
		assertFalse(list.isEmpty());

		list.addFirst("three");
		assertEquals(3, list.size());
		assertEquals("three", list.get(0));
		assertFalse(list.isEmpty());

		// Remove element
		list.removeFirst();

		// Test
		assertEquals(list.first(), "one");
		assertEquals(list.size(), 2);

	}

	/**
	 * Test the output of the removeLast() behavior, including expected exceptions
	 */
	@Test
	public void testRemoveLast() {
		// Make sure list is empty
		assertTrue(list.isEmpty());
		assertEquals(list.size(), 0);

		// Test exceptions
		Exception e = assertThrows(IndexOutOfBoundsException.class, () -> {
			list.removeLast();
		});

		// This is negative 1 because of the remove method
		assertEquals("Index is invalid: -1 (size=0)", e.getMessage());

		// Add elements
		list.add(0, "one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		assertFalse(list.isEmpty());

		list.add(1, "two");
		assertEquals(2, list.size());
		assertEquals("two", list.get(1));
		assertFalse(list.isEmpty());

		list.addLast("three");
		assertEquals(3, list.size());
		assertEquals("three", list.get(2));
		assertFalse(list.isEmpty());

		// Remove element
		list.removeLast();

		// Test
		assertEquals(list.size(), 2);
		assertEquals(list.last(), "two");
	}

	/**
	 * Test the output of the set(index, e) behavior, including expected exceptions
	 */
	@Test
	public void testSet() {
		// Make sure list is empty
		assertTrue(list.isEmpty());
		assertEquals(list.size(), 0);

		// Test exceptions
		Exception e = assertThrows(IndexOutOfBoundsException.class, () -> {
			list.set(0, "five");
		});

		assertEquals("Index is invalid: 0 (size=0)", e.getMessage());

		// Add elements
		list.add(0, "one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		assertFalse(list.isEmpty());

		list.add(1, "two");
		assertEquals(2, list.size());
		assertEquals("two", list.get(1));
		assertFalse(list.isEmpty());

		list.add(2, "three");
		assertEquals(3, list.size());
		assertEquals("three", list.get(2));
		assertFalse(list.isEmpty());

		// Set element
		list.set(1, "two");

		// Test
		assertEquals(list.get(1), "two");
		assertEquals(list.size(), 3);

	}
}
