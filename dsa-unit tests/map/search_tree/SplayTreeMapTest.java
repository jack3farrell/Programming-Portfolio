package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for SplayTreeMap Checks the expected outputs of the Map abstract
 * data type behaviors when using a splay tree data structure
 *
 * @author Dr. King
 * @author Jack Farrell
 *
 */
public class SplayTreeMapTest {

	/**
	 * BST for the 
	 */
	private BinarySearchTreeMap<Integer, String> tree;

	/**
	 * Create a new instance of a splay tree-based map before each test case
	 * executes
	 */
	@Before
	public void setUp() {
		tree = new SplayTreeMap<Integer, String>();
	}

	/**
	 * Test the output of the put(k,v) behavior
	 */
	@Test
	public void testPut() {
		assertEquals(0, tree.size());
		assertTrue(tree.isEmpty());

		tree.put(5, "five");
		assertEquals(1, tree.size());
		assertEquals(5, (int) tree.root().getElement().getKey());

		// Zig rotation
		tree.put(3, "three");
		assertEquals(2, tree.size());
		assertEquals(3, (int) tree.root().getElement().getKey());
		assertEquals(5, (int) tree.right(tree.root()).getElement().getKey());

		// Zig-Zig rotation
		tree.put(1, "one");
		assertEquals(3, tree.size());
		assertEquals(1, (int) tree.root().getElement().getKey());
		assertEquals(3, (int) tree.right(tree.root()).getElement().getKey());
		assertEquals(5, (int) tree.right(tree.right(tree.root())).getElement().getKey());

		// Zig-Zag rotation
		tree.put(4, "four");
		assertEquals(4, tree.size());
		assertEquals(4, (int) tree.root().getElement().getKey());
		assertEquals(1, (int) tree.left(tree.root()).getElement().getKey());
		assertEquals(5, (int) tree.right(tree.root()).getElement().getKey());
		assertEquals(3, (int) tree.right(tree.left(tree.root())).getElement().getKey());
	}

	/**
	 * Test the output of the get(k) behavior
	 */
	@Test
	public void testGet() {
		tree.put(4, "four");
		assertEquals(4, (int) tree.root().getElement().getKey());
		tree.put(2, "two");
		assertEquals(2, (int) tree.root().getElement().getKey());
		tree.put(6, "six");
		assertEquals(6, (int) tree.root().getElement().getKey());

	}

	/**
	 * Test the output of the remove(k) behavior
	 */
	@Test
	public void testRemove() {
		assertEquals(0, tree.size());
		assertTrue(tree.isEmpty());

		tree.put(5, "five");
		tree.put(3, "three");
		tree.put(1, "one");
		tree.put(4, "four");

		assertEquals(4, tree.size());
		assertEquals(4, (int) tree.root().getElement().getKey());
		assertEquals(1, (int) tree.left(tree.root()).getElement().getKey());
		assertEquals(5, (int) tree.right(tree.root()).getElement().getKey());
		assertEquals(3, (int) tree.right(tree.left(tree.root())).getElement().getKey());

		tree.remove(5);
		assertEquals(3, tree.size());
		assertNull(tree.get(5));
		assertEquals(4, (int) tree.root().getElement().getKey());
		assertEquals(1, (int) tree.left(tree.root()).getElement().getKey());
		assertEquals(3, (int) tree.right(tree.left(tree.root())).getElement().getKey());

		tree.remove(3); 
		assertEquals(2, tree.size());
		assertNull(tree.get(3)); 
		assertEquals(4, (int) tree.root().getElement().getKey()); 
		assertEquals(1, (int) tree.left(tree.root()).getElement().getKey());

		tree.remove(4);
		assertEquals(1, tree.size());
		assertNull(tree.get(4));
		assertEquals(1, (int) tree.root().getElement().getKey());

		tree.remove(1);
		assertEquals(0, tree.size());
		assertTrue(tree.isEmpty());
	}
}