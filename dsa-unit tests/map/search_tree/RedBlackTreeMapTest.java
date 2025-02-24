package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for RedBlackTreeMap Checks the expected outputs of the Map
 * abstract data type behaviors when using a red-black tree data structure
 *
 * @author Dr. King
 * @author Jack Farrell
 *
 */
public class RedBlackTreeMapTest {

	/**
	 * BST Map for RB Tree
	 */
	private BinarySearchTreeMap<Integer, String> tree;

	/**
	 * Create a new instance of a red-black tree-based map before each test case
	 * executes
	 */
	@Before
	public void setUp() {
		tree = new RedBlackTreeMap<Integer, String>();
	}

	/**
	 * Test the output of the put(k,v) behavior
	 */
	@Test
	public void testPut() {
		// Ensure the tree starts empty
		assertEquals(0, tree.size());
		assertTrue(tree.isEmpty());

		// Case 2
		tree.put(10, "ten");
		tree.put(5, "five");
		tree.put(20, "twenty");
		tree.put(15, "fifteen");
		tree.put(25, "twenty-five");

		assertEquals(10, (int) tree.root().getElement().getKey());
		assertEquals(5, (int) tree.left(tree.root()).getElement().getKey());
		assertEquals(20, (int) tree.right(tree.root()).getElement().getKey());
		assertEquals(25, (int) tree.right(tree.right(tree.root())).getElement().getKey());
		assertEquals(15, (int) tree.left(tree.right(tree.root())).getElement().getKey());

		// Case 1
		tree = new RedBlackTreeMap<Integer, String>();
		tree.put(1, "one");
		tree.put(6, "six");
		tree.put(10, "ten");

		assertEquals(6, (int) tree.root().getElement().getKey());
		assertEquals(1, (int) tree.left(tree.root()).getElement().getKey());
		assertEquals(10, (int) tree.right(tree.root()).getElement().getKey());
	}

	/**
	 * Test the output of the get(k) behavior
	 */
	@Test
	public void testGet() {
		assertTrue(tree.isEmpty());
		assertEquals(0, tree.size());

		tree.put(30, "A");
		tree.put(10, "B");
		tree.put(50, "C");
		tree.put(5, "D");
		tree.put(40, "E");
		assert tree.get(30).equals("A");
		assert tree.get(5).equals("D");
		assert tree.get(50).equals("C");
	}

	/**
	 * Test the output of the remove(k) behavior
	 */
	@Test
	public void testRemove() {
		assertTrue(tree.isEmpty());
		assertEquals(0, tree.size());

		// Case 1
		tree.put(12, "twelve");
		tree.put(5, "five");
		tree.put(15, "fifteen");
		tree.put(3, "three");
		tree.put(4, "four");
		tree.put(8, "eight");
		tree.put(7, "seven");
		tree.put(10, "ten");
		tree.put(11, "eleven");
		tree.put(13, "thirteen");
		tree.put(14, "fourteen");
		tree.put(17, "seventeen");

		assertEquals("thirteen", tree.remove(13));

		assertEquals(15, (int) tree.right(tree.right(tree.root())).getElement().getKey());
		assertEquals(14, (int) tree.left(tree.right(tree.right(tree.root()))).getElement().getKey());
		assertEquals(17, (int) tree.right(tree.right(tree.right(tree.root()))).getElement().getKey());

		// Case 2
		assertEquals("seventeen", tree.remove(17));

		assertEquals(15, (int) tree.right(tree.right(tree.root())).getElement().getKey());
		assertEquals(14, (int) tree.left(tree.right(tree.right(tree.root()))).getElement().getKey());

		// Check that 17s position is removed
		@SuppressWarnings("unused")
		NullPointerException exception = assertThrows(NullPointerException.class, () -> {
			tree.right(tree.right(tree.right(tree.root()))).getElement().getKey();
		});

	}
}
