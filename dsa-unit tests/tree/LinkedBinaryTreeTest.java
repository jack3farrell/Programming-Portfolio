package edu.ncsu.csc316.dsa.tree;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for LinkedBinaryTree Checks the expected outputs of the BinaryTree
 * abstract data type behaviors when using a linked data structure to store
 * elements
 *
 * @author Dr. King
 * @author Jack Farrell
 *
 */
public class LinkedBinaryTreeTest {

	/** The binary tree holding string data */
	private LinkedBinaryTree<String> tree;

	/** Position for the first node */
	private Position<String> one;

	/** Position for the second node */
	private Position<String> two;

	/** Position for the third node */
	private Position<String> three;

	/** Position for the fourth node */
	private Position<String> four;

	/** Position for the fifth node */
	private Position<String> five;

	/** Position for the sixth node */
	private Position<String> six;

	/** Position for the seventh node */
	private Position<String> seven;

	/** Position for the eighth node */
	private Position<String> eight;

	/** Position for the ninth node */
	private Position<String> nine;

	/** Position for the tenth node */
	private Position<String> ten;

	/**
	 * Create a new instance of a linked binary tree before each test case executes
	 */
	@Before
	public void setUp() {
		tree = new LinkedBinaryTree<String>();
	}

	/**
	 * Sample tree to help with testing
	 *
	 * One -> Two -> Six -> Ten -> Seven -> Five -> Three -> Four -> Eight -> Nine
	 * 
	 * Or, visually: one / \ two three / \ / six ten four / \ / \ seven five eight
	 * nine
	 */
	private void createTree() {
		one = tree.addRoot("one");
		two = tree.addLeft(one, "two");
		three = tree.addRight(one, "three");
		six = tree.addLeft(two, "six");
		ten = tree.addRight(two, "ten");
		four = tree.addLeft(three, "four");
		seven = tree.addLeft(ten, "seven");
		five = tree.addRight(ten, "five");
		eight = tree.addLeft(four, "eight");
		nine = tree.addRight(four, "nine");
	}

	/**
	 * Test the output of the set(p,e) behavior
	 */
	@Test
	public void testSet() {
		createTree();

		tree.set(five, "numero cinco");
		assertEquals("numero cinco", five.getElement());

		tree.set(three, "numero tres");
		assertEquals("numero tres", three.getElement());
	}

	/**
	 * Test the output of the size() behavior
	 */
	@Test
	public void testSize() {
		assertTrue(tree.isEmpty());
		createTree();
		assertEquals(tree.size(), 10);
	}

	/**
	 * Test the output of the numChildren(p) behavior
	 */
	@Test
	public void testNumChildren() {
		createTree();
		// Test root
		assertEquals(tree.numChildren(one), 2);

		// Test leaf
		assertEquals(tree.numChildren(six), 0);
	}

	/**
	 * Test the output of the parent(p) behavior
	 */
	@Test
	public void testParent() {
		createTree();

		assertEquals(tree.parent(one), null);
		assertEquals(tree.parent(eight), four);
		assertEquals(tree.parent(four), three);
		assertEquals(tree.parent(three), one);
	}

	/**
	 * Test the output of the sibling behavior
	 */
	@Test
	public void testSibling() {
		createTree();

		assertEquals(tree.sibling(six), ten);
		assertEquals(tree.sibling(five), seven);
		assertEquals(tree.sibling(eight), nine);
	}

	/**
	 * Test the output of the isInternal behavior
	 */
	@Test
	public void testIsInternal() {
		createTree();

		assertTrue(tree.isInternal(four));
		assertTrue(tree.isInternal(three));
		assertTrue(tree.isInternal(three));

		assertFalse(tree.isInternal(six));
		assertFalse(tree.isInternal(five));
	}

	/**
	 * Test the output of the isLeaf behavior
	 */
	@Test
	public void isLeaf() {
		createTree();

		assertTrue(tree.isLeaf(six));
		assertTrue(tree.isLeaf(five));

		assertFalse(tree.isLeaf(four));
		assertFalse(tree.isLeaf(three));
		assertFalse(tree.isLeaf(three));
	}

	/**
	 * Test the output of the isRoot(p)
	 */
	@Test
	public void isRoot() {
		createTree();

		assertTrue(tree.isRoot(one));

		assertFalse(tree.isRoot(eight));
	}

	/**
	 * Test the output of the preOrder traversal behavior
	 */
	@Test
	public void testPreOrder() {
		createTree();
		Iterator<Position<String>> it = tree.preOrder().iterator();

		assertEquals("one", it.next().getElement());
		assertEquals("two", it.next().getElement());
		assertEquals("six", it.next().getElement());
		assertEquals("ten", it.next().getElement());
		assertEquals("seven", it.next().getElement());
		assertEquals("five", it.next().getElement());
		assertEquals("three", it.next().getElement());
		assertEquals("four", it.next().getElement());
		assertEquals("eight", it.next().getElement());
		assertEquals("nine", it.next().getElement());
	}

	/**
	 * Test the output of the postOrder traversal behavior
	 */
	@Test
	public void testPostOrder() {
		createTree();
		Iterator<Position<String>> it = tree.postOrder().iterator();

		assertEquals("six", it.next().getElement());
		assertEquals("seven", it.next().getElement());
		assertEquals("five", it.next().getElement());
		assertEquals("ten", it.next().getElement());
		assertEquals("two", it.next().getElement());
		assertEquals("eight", it.next().getElement());
		assertEquals("nine", it.next().getElement());
		assertEquals("four", it.next().getElement());
		assertEquals("three", it.next().getElement());
		assertEquals("one", it.next().getElement());
	}

	/**
	 * Test the output of the inOrder traversal behavior
	 */
	@Test
	public void testInOrder() {
		createTree();
		Iterator<Position<String>> it = tree.inOrder().iterator();

		assertEquals("six", it.next().getElement());
		assertEquals("two", it.next().getElement());
		assertEquals("seven", it.next().getElement());
		assertEquals("ten", it.next().getElement());
		assertEquals("five", it.next().getElement());
		assertEquals("one", it.next().getElement());
		assertEquals("eight", it.next().getElement());
		assertEquals("four", it.next().getElement());
		assertEquals("nine", it.next().getElement());
		assertEquals("three", it.next().getElement());
	}

	/**
	 * Test the output of the Binary Tree ADT behaviors on an empty tree
	 */
	@Test
	public void testEmptyTree() {
		LinkedBinaryTree<String> empty = new LinkedBinaryTree<String>();
		assertTrue(empty.isEmpty());
	}

	/**
	 * Test level order traversal through the tree
	 */
	@Test
	public void testLevelOrder() {
		createTree();
		Iterator<Position<String>> it = tree.levelOrder().iterator();

		assertEquals("one", it.next().getElement());
		assertEquals("two", it.next().getElement());
		assertEquals("three", it.next().getElement());
		assertEquals("six", it.next().getElement());
		assertEquals("ten", it.next().getElement());
		assertEquals("four", it.next().getElement());
		assertEquals("seven", it.next().getElement());
		assertEquals("five", it.next().getElement());
		assertEquals("eight", it.next().getElement());
		assertEquals("nine", it.next().getElement());
	}

	/**
	 * Test the output of the addLeft(p,e) behavior, including expected exceptions
	 */
	@Test
	public void testAddLeft() {
		createTree();

		assertEquals("two", tree.left(one).getElement());
		assertEquals("six", tree.left(two).getElement());

		try {
			tree.addLeft(two, "newLeft");
			fail("Expected IllegalArgumentException for adding a second left child");
		} catch (IllegalArgumentException e) {
			// Exception
		}
	}

	/**
	 * Test the output of the addRight(p,e) behavior, including expected exceptions
	 */
	@Test
	public void testAddRight() {
		createTree();

		assertEquals("three", tree.right(one).getElement());
		assertEquals("ten", tree.right(two).getElement());

		try {
			tree.addRight(two, "newRight");
			fail("Expected IllegalArgumentException for adding a second right child");
		} catch (IllegalArgumentException e) {
			// Exception
		}
	}

	/**
	 * Test the output of the remove(p) behavior, including expected exceptions
	 */
	@Test
	public void testRemove() {
		createTree();
		tree.remove(seven);
		assertEquals(9, tree.size());
		tree.remove(nine);
		assertEquals(8, tree.size());
	}

	/**
	 * Tests the toString method in AbstractTree
	 */
	@Test
	public void testToString() {
		createTree();
		String expected = "LinkedBinaryTree[\n" + "one\n" + " two\n" + "  six\n" + "  ten\n" + "   seven\n"
				+ "   five\n" + " three\n" + "  four\n" + "   eight\n" + "   nine\n" + "]";

		String result = tree.toString();
		assertEquals(expected, result);
	}
}
