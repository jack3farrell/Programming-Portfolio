package edu.ncsu.csc316.dsa.stack;

import static org.junit.Assert.*;
import java.util.EmptyStackException;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for LinkedStack. Checks the expected outputs of the Stack abstract
 * data type behaviors when using a singly-linked list data structure
 *
 * @author Dr. King
 * @author Jack Farrell
 *
 */
public class LinkedStackTest {

	/**
	 * A Stack that holds strings
	 */
	private Stack<String> stack;

	/**
	 * Create a new instance of a linked list-based stack before each test case
	 * executes
	 */
	@Before
	public void setUp() {
		stack = new LinkedStack<String>();
	}

	/**
	 * Test the output of the push(e) behavior
	 */
	@Test
	public void testPush() {
		assertEquals(0, stack.size());
		assertTrue(stack.isEmpty());

		stack.push("one");
		assertEquals(1, stack.size());
		assertFalse(stack.isEmpty());

		stack.push("two");
		assertEquals(2, stack.size());
		assertFalse(stack.isEmpty());

		// Checking for null object
		stack.push(null);
		assertEquals(3, stack.size());
		assertNull(stack.top());
	}

	/**
	 * Test the output of the pop() behavior, including expected exceptions
	 */
	@Test
	public void testPop() {
		assertEquals(0, stack.size());

		// Testing pop() on empty stack
		try {
			stack.pop();
			fail("EmptyStackException should have been thrown.");
		} catch (Exception e) {
			assertTrue(e instanceof EmptyStackException);
		}

		// Adding to stack
		stack.push("one");
		assertEquals(1, stack.size());
		assertFalse(stack.isEmpty());
		stack.push("two");
		assertEquals(2, stack.size());
		assertFalse(stack.isEmpty());

		// Testing pop() method
		assertEquals(stack.pop(), "two");
		assertEquals(stack.pop(), "one");

		// Testing empty stack
		try {
			stack.pop();
			fail("EmptyStackException should have been thrown.");
		} catch (Exception e) {
			assertTrue(e instanceof EmptyStackException);
		}

		assertEquals(0, stack.size());
		assertTrue(stack.isEmpty());
	}

	/**
	 * Test the output of the top() behavior, including expected exceptions
	 */
	@Test
	public void testTop() {
		assertEquals(0, stack.size());
		
		// Adding to stack
		stack.push("one");
		assertEquals(1, stack.size());
		assertFalse(stack.isEmpty());
		stack.push("two");
		assertEquals(2, stack.size());
		assertFalse(stack.isEmpty());
		
		// Testing top() method
		assertEquals(stack.top(), "two");
		assertEquals(stack.pop(), "two");
		assertEquals(stack.top(), "one");
		assertEquals(stack.pop(), "one");
		
		// Testing empty stack
		try {
			stack.top();
			fail("EmptyStackException should have been thrown.");
		} catch (Exception e) {
			assertTrue(e instanceof EmptyStackException);
		}

		assertEquals(0, stack.size());
		assertTrue(stack.isEmpty());
	}

}