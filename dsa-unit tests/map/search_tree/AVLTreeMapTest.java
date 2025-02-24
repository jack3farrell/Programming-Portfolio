package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for AVLTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * an AVL tree data structure 
 *
 * @author Dr. King
 * @author Jack Farrell 
 *
 */
public class AVLTreeMapTest {

	/**
	 * Binary Search Tree used for AVL restrictions 
	 */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of an AVL tree-based map before each test case executes
     */     
    @Before
    public void setUp() {
        tree = new AVLTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        // Right rotation
        tree.put(1, "one");
        tree.put(2, "two");
        tree.put(3, "three");

        assertEquals(2, (int)tree.root().getElement().getKey());
        assertEquals(1, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(3, (int)tree.right(tree.root()).getElement().getKey()); 
        
        tree = new AVLTreeMap<Integer, String>();      
        
        // Left rotation
        tree.put(3, "three");
        tree.put(2, "two");
        tree.put(1, "one");

        assertEquals(2, (int)tree.root().getElement().getKey());
        assertEquals(1, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(3, (int)tree.right(tree.root()).getElement().getKey());
        
        tree = new AVLTreeMap<Integer, String>();
        
        // Left right rotation
        tree.put(3, "three");
        tree.put(1, "one");
        tree.put(2, "two");

        assertEquals(2, (int)tree.root().getElement().getKey());
        assertEquals(1, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(3, (int)tree.right(tree.root()).getElement().getKey());
        
        tree = new AVLTreeMap<Integer, String>();
        
        // Right left rotation
        tree.put(1, "one");
        tree.put(3, "three");
        tree.put(2, "two");
        
        assertEquals(2, (int)tree.root().getElement().getKey());
        assertEquals(1, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(3, (int)tree.right(tree.root()).getElement().getKey());
        
        tree = new AVLTreeMap<Integer, String>();

    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(tree.isEmpty());
        
        tree.put(1, "one");
        tree.put(2, "two");
        tree.put(3, "three");
        
        assertEquals(3, tree.size());
        
        assertEquals("one", tree.get(1));
        assertEquals("two", tree.get(2));
        assertEquals("three", tree.get(3));
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(tree.isEmpty());
        
        tree.put(2, "two");
        tree.put(1, "one");
        tree.put(3, "three");

        assertEquals(2, (int)tree.root().getElement().getKey());
        assertEquals(1, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(3, (int)tree.right(tree.root()).getElement().getKey());

        // Make sure tree becomes empty
        tree.remove(1);
        tree.remove(2);
        tree.remove(3);
        assertTrue(tree.isEmpty());

        // Test tree 
        tree.put(4, "four");
        tree.put(2, "two");
        tree.put(6, "six");
        tree.put(1, "one");
        tree.put(3, "three");
        tree.put(5, "five");
        tree.put(7, "seven");
        
        // Check for correct spots
        assertEquals(4, (int)tree.root().getElement().getKey());
        assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(6, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(1, (int)tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(3, (int)tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(5, (int)tree.left(tree.right(tree.root())).getElement().getKey());
        assertEquals(7, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        
        // Stay balanced 
        tree.remove(1);
          
        assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(6, tree.size());

        // Stay balanced
        tree.remove(7);
        assertEquals(6, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(5, tree.size());

        // 5 as new parent
        tree.remove(6);
        assertEquals(5, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(4, (int)tree.root().getElement().getKey());
        assertEquals(4, tree.size());

        // 3 as new parent
        tree.remove(5);
        assertEquals(3, (int)tree.root().getElement().getKey());
        assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(4, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(3, tree.size());

        // Remove the remaining nodes (4, 2, 3)
        tree.remove(4);
        tree.remove(2);
        tree.remove(3);

        // Ensure the tree is empty again
        assertTrue(tree.isEmpty());
    }
}
