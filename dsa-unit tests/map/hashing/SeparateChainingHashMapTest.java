package edu.ncsu.csc316.dsa.map.hashing;

import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for SeparateChainingHashMap Checks the expected outputs of the Map
 * abstract data type behaviors when using a separate chaining hash map data
 * structure
 *
 * @author Dr. King
 * @author Jack Farrell
 *
 */
public class SeparateChainingHashMapTest {

	// 'Testing' Map used (no randomization) to check ordering of contents
	/**
	 * Test map
	 */
	private Map<Integer, String> testMap;

	// 'Production' Map (with randomization) to check correctness of ADT behaviors
	/**
	 * Production map
	 */
	private Map<Integer, String> prodMap;

	/**
	 * Create a new instance of a separate chaining hash map before each test case
	 * executes
	 */
	@Before
	public void setUp() {
		// Use the "true" flag to indicate we are TESTING.
		// Remember that (when testing) alpha = 1, beta = 1, and prime = 7
		// based on our AbstractHashMap constructor.
		// That means you can draw the hash table by hand
		// if you use integer keys, since Integer.hashCode() = the integer value, itself
		// Finally, apply compression. For example:
		// for key = 1: h(1) = ( (1 * 1 + 1) % 7) % 7 = 2
		// for key = 2: h(2) = ( (1 * 2 + 1) % 7) % 7 = 3
		// for key = 3: h(3) = ( (1 * 3 + 1) % 7) % 7 = 4
		// for key = 4: h(4) = ( (1 * 4 + 1) % 7) % 7 = 5
		// for key = 5: h(5) = ( (1 * 5 + 1) % 7) % 7 = 6
		// for key = 6: h(6) = ( (1 * 6 + 1) % 7) % 7 = 0
		// etc.
		// Remember that our secondary map (an AVL tree) is a search
		// tree, which means the entries should be sorted in order within
		// that tree
		testMap = new SeparateChainingHashMap<Integer, String>(7, true);
		prodMap = new SeparateChainingHashMap<Integer, String>();
	}

	/**
	 * Test the output of the put(k,v) behavior
	 */
	@Test
	public void testPut() {
		assertEquals(0, testMap.size());
		assertTrue(testMap.isEmpty());
		assertNull(testMap.put(3, "string3"));

		// Since our entrySet method returns the entries in the table
		// from left to right, we can use the entrySet to check
		// that our values are in the correct order in the hash table.
		// Alternatively, you could implement a toString() method if you
		// want to check that the exact index/map of each bucket is correct
		Iterator<Map.Entry<Integer, String>> it = testMap.entrySet().iterator();
		assertEquals(3, (int) it.next().getKey()); // should be in a map in index 4

		assertNull(testMap.put(4, "string4"));
		assertEquals(2, testMap.size());
		assertFalse(testMap.isEmpty());
		it = testMap.entrySet().iterator();
		assertEquals(3, (int) it.next().getKey()); // should be in a map in index 4
		assertEquals(4, (int) it.next().getKey()); // should be in a map in index 5

		// You should create some collisions to check that entries
		// are placed in the correct buckets.
		//
		// You should also use the prodMap to check that put works
		// as expected when randomization is used internally. You can't
		// check the placement of entries within the hash table,
		// but you can still check that put gives the correct outputs when
		// randomization is used internally.

		assertNull(testMap.put(13, "string13"));
		assertEquals(3, testMap.size());
		it = testMap.entrySet().iterator();
		assertEquals(13, (int) it.next().getKey());
		assertEquals(3, (int) it.next().getKey());
		assertEquals(4, (int) it.next().getKey());

		assertEquals("string4", testMap.put(4, "newString4"));
		assertEquals(3, testMap.size());
		assertEquals("newString4", testMap.get(4));

		assertNull(prodMap.put(1, "prodMapValue1"));
		assertNull(prodMap.put(2, "prodMapValue2"));
		assertEquals("prodMapValue1", prodMap.get(1));
		assertEquals("prodMapValue1", prodMap.put(1, "newProdMapValue1"));

	}

	/**
	 * Test the output of the get(k) behavior
	 */
	@Test
	public void testGet() {
		assertTrue(testMap.isEmpty());

		testMap.put(1, "value1");
		testMap.put(2, "value2");
		testMap.put(3, "value3");

		assertEquals("value1", testMap.get(1));
		assertEquals("value2", testMap.get(2));
		assertEquals("value3", testMap.get(3));

		assertNull(testMap.get(4));

		testMap.put(11, "value11");
		assertEquals("value11", testMap.get(11));

		prodMap.put(1, "prodMapValue1");
		prodMap.put(2, "prodMapValue2");
		assertEquals("prodMapValue1", prodMap.get(1));
		assertEquals("prodMapValue2", prodMap.get(2));
		assertNull(prodMap.get(3));

	}

	/**
	 * Test the output of the remove(k) behavior
	 */
	@Test
	public void testRemove() {
		assertTrue(testMap.isEmpty());
		assertTrue(prodMap.isEmpty());

		testMap.put(1, "testMapValue1");
		prodMap.put(1, "prodMapValue1");

		testMap.put(2, "testMapValue2");
		prodMap.put(2, "prodMapValue2");

		assertEquals("testMapValue1", testMap.remove(1));
		assertEquals("prodMapValue1", prodMap.remove(1));

		assertNull(testMap.get(1));
		assertNull(prodMap.get(1));

		assertEquals("testMapValue2", testMap.get(2));
		assertEquals("prodMapValue2", prodMap.get(2));

		assertNull(testMap.remove(3));
		assertNull(prodMap.remove(3));

		assertEquals(testMap.size(), prodMap.size());

	}

	/**
	 * Test the output of the iterator() behavior, including expected exceptions
	 */
	@Test
	public void testIterator() {
		assertNull(testMap.put(1, "number1"));
		assertNull(testMap.put(2, "number2"));
		assertNull(testMap.put(3, "number3"));
		assertNull(testMap.put(4, "number4"));
		assertNull(testMap.put(5, "number5"));
		Iterator<Integer> it = testMap.iterator();
		assertTrue(it.hasNext());
		assertEquals(Integer.valueOf(1), it.next());
		assertEquals(Integer.valueOf(2), it.next());
		assertEquals(Integer.valueOf(3), it.next());
		assertEquals(Integer.valueOf(4), it.next());
		assertEquals(Integer.valueOf(5), it.next());
		
		try {
			it.next();
		} catch (NoSuchElementException e) {
			assertNull(e.getMessage());
		}
	}

	/**
	 * Test the output of the entrySet() behavior
	 */
	@Test
	public void testEntrySet() {
	    testMap.put(1, "value1");
	    testMap.put(2, "value2");
	    testMap.put(3, "value3");
	    testMap.put(22, "value22"); 

	    Iterator<Map.Entry<Integer, String>> it = testMap.entrySet().iterator();

	    assertTrue(it.hasNext());
	    Map.Entry<Integer, String> entry = it.next();
	    assertEquals(1, (int) entry.getKey());
	    assertEquals("value1", entry.getValue());

	    assertTrue(it.hasNext());
	    entry = it.next();
	    assertEquals(22, (int) entry.getKey());
	    assertEquals("value22", entry.getValue());

	    assertTrue(it.hasNext());
	    entry = it.next();
	    assertEquals(2, (int) entry.getKey());
	    assertEquals("value2", entry.getValue());

	    assertTrue(it.hasNext());
	    entry = it.next();
	    assertEquals(3, (int) entry.getKey());
	    assertEquals("value3", entry.getValue());

	    assertFalse(it.hasNext());

	}

	/**
	 * Test the output of the values() behavior
	 */
	@Test
	public void testValues() {
	    testMap.put(1, "value1");
	    testMap.put(2, "value2");
	    testMap.put(3, "value3");
	    testMap.put(11, "value11"); 

	    Iterator<String> it = testMap.values().iterator();

	    assertTrue(it.hasNext());
	    assertEquals("value1", it.next()); 

	    assertTrue(it.hasNext());
	    assertEquals("value2", it.next());
	   
	    assertTrue(it.hasNext());
	    assertEquals("value3", it.next());
	    
	    assertTrue(it.hasNext());
	    assertEquals("value11", it.next()); 

	    assertFalse(it.hasNext());
	}
}
