package edu.ncsu.csc316.dsa.set;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for HashSet Checks the expected outputs of the Set abstract data
 * type behaviors when using a hash table data structure
 *
 * @author Dr. King
 * @author Jack Farrell
 *
 */
public class HashSetTest {

	/**
	 * Set to use
	 */
	private Set<Integer> set;

	/**
	 * Testing set
	 */
	private Set<Integer> testSet;

	/**
	 * Create new instances of a hash-based set before each test case executes
	 */
	@Before
	public void setUp() {
		// Will use a "production" hash map with random alpha, beta values
		set = new HashSet<Integer>();

		// Will use a "testing" hash map which uses alpha=1, beta=1, prime=7
		testSet = new HashSet<Integer>(true);
	}

	/**
	 * Test the output of the add behavior
	 */
	@Test
	public void testAdd() {
		assertTrue(set.isEmpty());
		assertEquals(0, set.size());

		set.add(5);
		assertEquals(1, set.size());
		assertFalse(set.isEmpty());

		// Make sure duplicate values cannot be added
		set.add(5);
		assertEquals(1, set.size());
		assertFalse(set.isEmpty());

		set.add(6);
		assertEquals(2, set.size());
		assertFalse(set.isEmpty());

		set.add(10);
		assertEquals(3, set.size());
		assertFalse(set.isEmpty());

	}

	/**
	 * Test the output of the contains behavior
	 */
	@Test
	public void testContains() {
		assertTrue(set.isEmpty());
		assertEquals(0, set.size());
		set.add(5);
		set.add(10);
		set.add(15);
		set.add(20);
		set.add(25);
		assertEquals(5, set.size());

		assertTrue(set.contains(5));
		assertTrue(set.contains(10));
		assertTrue(set.contains(15));
		assertTrue(set.contains(20));
		assertTrue(set.contains(25));
	}

	/**
	 * Test the output of the remove behavior
	 */
	@Test
	public void testRemove() {
		assertTrue(set.isEmpty());
		assertEquals(0, set.size());
		set.add(5);
		set.add(10);
		set.add(15);
		set.add(20);
		set.add(25);
		assertEquals(5, set.size());

		set.remove(5);
		assertEquals(set.size(), 4);
		
		set.remove(10);
		assertEquals(set.size(), 3);
		
		set.remove(15);
		assertEquals(set.size(), 2);
		
		set.remove(20);
		assertEquals(set.size(), 1);
		
		set.remove(25);
		assertEquals(set.size(), 0);
		
		assertTrue(set.isEmpty());	}

	/**
	 * Test the output of the retainAll behavior
	 */
	@Test
	public void testRetainAll() {
		assertTrue(set.isEmpty());
		assertEquals(0, set.size());
		set.add(5);
		set.add(10);
		set.add(15);
		set.add(20);
		set.add(25);
		assertEquals(5, set.size());

		Set<Integer> other = new TreeSet<Integer>();
		other.add(10);
		other.add(20);
		other.add(30);

		set.retainAll(other);
		assertTrue(set.contains(10));
		assertTrue(set.contains(20));
		// Make sure the value is taken out
		assertFalse(set.contains(15));	}

	/**
	 * Test the output of the removeAll behavior
	 */
	@Test
	public void testRemoveAll() {
		assertTrue(set.isEmpty());
		assertEquals(0, set.size());
		set.add(5);
		set.add(10);
		set.add(15);
		set.add(20);
		set.add(25);
		assertEquals(5, set.size());

		Set<Integer> other = new TreeSet<Integer>();
		other.add(10);
		other.add(20);
		other.add(30);

		set.removeAll(other);
		assertTrue(set.contains(5));
		assertTrue(set.contains(15));
		assertTrue(set.contains(25));
		assertFalse(set.contains(20));	}

	/**
	 * Test the output of the addAll behavior
	 */
	@Test
	public void testAddAll() {
		assertTrue(set.isEmpty());
		assertEquals(0, set.size());
		set.add(5);
		set.add(10);
		set.add(15);
		set.add(20);
		set.add(25);
		assertEquals(5, set.size());

		Set<Integer> other = new TreeSet<Integer>();
		other.add(30);
		other.add(40);
		other.add(50);
		
		set.addAll(other);
		
		assertTrue(set.contains(30));
		assertTrue(set.contains(40));
		assertTrue(set.contains(50));	}

	/**
	 * Test the output of the iterator behavior
	 */
	@Test
	public void testIterator() {
		// Since our hash map uses random numbers, it can
		// be difficult to test that our hash set iterator returns
		// values in a certain order.
		// We could approach this problem with a few different options:
		// (1) instead of checking the specific order of entries
		// visited by the iterator, we could save each
		// iterator.next() into an array, then check that the
		// array *contains* the entry, regardless of its exact location
		//
		// (2) implement an isTesting flag for HashSet, similar to testSet in the setUp
		// method.
		// This is the more appropriate option since we can control the
		// entire execution and know exactly which values should appear
		// in the set and in the correct expected order from using the iterator
		//
		// Revisit your test cases for HashMap iterator -- those tests can be adapted
		// to work here, too, since you have already worked out the details
		// of where entries with certain keys will be stored and in what order
		// they will be stored
		assertTrue(testSet.isEmpty());
		testSet.add(3);
		testSet.add(5);
		testSet.add(2);
		testSet.add(4);
		testSet.add(1);
		testSet.add(6);
		assertEquals(6, testSet.size());
		assertFalse(testSet.isEmpty());

		Iterator<Integer> it = testSet.iterator();
		assertTrue(it.hasNext());
		assertEquals(6, (int) it.next()); // should be index 0
		assertEquals(1, (int) it.next()); // should be index 2
		assertEquals(2, (int) it.next()); // should be index 3
		assertEquals(3, (int) it.next()); // should be index 4
		assertEquals(4, (int) it.next()); // should be index 5
		assertEquals(5, (int) it.next()); // should be index 6
		assertFalse(it.hasNext());
	}
}