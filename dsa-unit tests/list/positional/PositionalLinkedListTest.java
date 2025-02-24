package edu.ncsu.csc316.dsa.list.positional;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for PositionalLinkedList. Checks the expected outputs of the
 * Positional List abstract data type behaviors when using an doubly-linked
 * positional list data structure
 *
 * @author Dr. King
 * @author Jack Farrell
 *
 */
public class PositionalLinkedListTest {

	/**
	 * Positional List
	 */
	private PositionalList<String> list;

	/**
	 * Create a new instance of an positional linked list before each test case
	 * executes
	 */
	@Before
	public void setUp() {
		list = new PositionalLinkedList<String>();
	}

	/**
	 * Test the output of the first() behavior, including any expected exceptions
	 */
	@Test
	public void testFirst() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		assertNull(list.first());

		Position<String> first = list.addFirst("one");
		assertEquals(1, list.size());
		assertEquals(first, list.first());

		Position<String> second = list.addFirst("two");
		assertEquals(2, list.size());
		assertEquals(second, list.first());
	}

	/**
	 * Test the output of the last() behavior, including any expected exceptions
	 */
	@Test
	public void testLast() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		assertNull(list.first());

		Position<String> first = list.addLast("one");
		assertEquals(1, list.size());
		assertEquals(first, list.last());

		Position<String> second = list.addLast("two");
		assertEquals(2, list.size());
		assertEquals(second, list.last());
	}

	/**
	 * Test the output of the addFirst(element) behavior
	 */
	@Test
	public void testAddFirst() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		Position<String> first = list.addFirst("one");
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());

		assertEquals(first, list.first());

	}

	/**
	 * Test the output of the addLast(element) behavior
	 */
	@Test
	public void testAddLast() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		Position<String> first = list.addLast("one");
		assertEquals(1, list.size());

		assertEquals(first, list.last());
	}

	/**
	 * Test the output of the before(position) behavior, including any expected
	 * exceptions
	 */
	@Test
	public void testBeforeAndAdd() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		assertNull(list.first());

		Position<String> first = list.addLast("one");

		Position<String> second = list.addBefore(first, "two");

		assertEquals(2, list.size());
		assertEquals(second, list.before(first));
	}

	/**
	 * Test the output of the after(position) behavior, including any expected
	 * exceptions
	 */
	@Test
	public void testAfterAndAddAfter() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		assertNull(list.first());

		Position<String> first = list.addLast("one");

		Position<String> second = list.addAfter(first, "two");

		assertEquals(2, list.size());
		assertEquals(second, list.after(first));
	}

	/**
	 * Test the output of the set(position, element) behavior, including any
	 * expected exceptions
	 */
	@Test
	public void testSet() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		assertNull(list.first());

		Position<String> first = list.addFirst("one");

		Position<String> second = list.addAfter(first, "two");

		Position<String> third = list.addAfter(second, "three");

		assertEquals(list.size(), 3);
		list.set(third, "negative three");
		list.set(second, "negative two");
		list.set(first, "negative one");
		assertEquals(third.getElement(), "negative three");
		assertEquals(second.getElement(), "negative two");
		assertEquals(first.getElement(), "negative one");
	}

	/**
	 * Test the output of the remove(position) behavior, including any expected
	 * exceptions
	 */
	@Test
	public void testRemove() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		assertNull(list.first());

		Position<String> first = list.addFirst("one");

		Position<String> second = list.addAfter(first, "two");

		Position<String> third = list.addAfter(second, "three");

		list.remove(third);
		assertEquals(second.getElement(), "two");
		assertEquals(list.size(), 2);

		list.remove(second);
		assertEquals(first.getElement(), "one");
		assertEquals(list.size(), 1);

		list.remove(first);
		assertEquals(list.size(), 0);
	}

	/**
	 * Test the output of the iterator behavior for elements in the list, including
	 * any expected exceptions
	 */
	@Test
	public void testIterator() {
		Iterator<String> it = list.iterator();
		assertFalse(it.hasNext());

		try {
			it.next();
			fail("Expected NoSuchElementException for next() on an empty list");
		} catch (NoSuchElementException e) {
			//Exception
		}

		 list.addFirst("one");
		 list.addLast("two");
		 list.addLast("three");

		it = list.iterator();
		assertTrue(it.hasNext());
		assertEquals("one", it.next());
		assertEquals("two", it.next());
		assertEquals("three", it.next());
		assertFalse(it.hasNext());
		it.remove();

		try {
			it.next();
			fail("Expected NoSuchElementException for next() after full traversal");
		} catch (NoSuchElementException e) {
			//Exception
		}
	}

	/**
	 * Test the output of the positions() behavior to iterate through positions in
	 * the list, including any expected exceptions
	 */
	@Test
	public void testPositions() {
		assertEquals(0, list.size());
		Position<String> first = list.addFirst("one");
		Position<String> second = list.addLast("two");
		Position<String> third = list.addLast("three");
		assertEquals(3, list.size());

		Iterator<Position<String>> it = list.positions().iterator();
		assertTrue(it.hasNext());
		assertEquals(first, it.next());
		assertEquals(second, it.next());
		assertEquals(third, it.next());

	}

}