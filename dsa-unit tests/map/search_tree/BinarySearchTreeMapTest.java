package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for BinarySearchTreeMap
 * Checks the expected outputs of the Map and Tree abstract data type behaviors when using
 * an linked binary tree data structure 
 *
 * @author Dr. King
 * @author Jack Farrell
 *
 */
public class BinarySearchTreeMapTest {

	/**
	 * Binary Search Tree
	 */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a binary search tree map before each test case executes
     */
    @Before
    public void setUp() {
        tree = new BinarySearchTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        tree.put(1, "one");
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(1, (int)tree.root().getElement().getKey());
        
        tree.put(2, "two");
        assertEquals(2, tree.size());

        tree.put(3, "three");
        assertEquals(3, tree.size());

        tree.put(1, "one_updated");
        assertEquals(3, tree.size());  
        assertEquals("one_updated", tree.root().getElement().getValue()); 
        }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        tree.put(1,  "one");
        assertEquals(1, tree.size());
        assertEquals("one", tree.get(1));

        tree.put(2, "two");
        assertEquals(2, tree.size());
        assertEquals("two", tree.get(2));

        tree.put(3, "three");
        assertEquals(3, tree.size());
        assertEquals("three", tree.get(3));

        assertNull(tree.get(4));  
        assertNull(tree.get(-1)); 
    }

    /**
     * Test the output of the remove(k) behavior
     */ 
    @Test
    public void testRemove() {
    	// Removing the root
        tree.put(1,  "one");
        assertEquals(1, tree.size());
        
        assertNull(tree.remove(10));
        assertEquals(1, tree.size());
        
        assertEquals("one", tree.remove(1));
        assertEquals(0, tree.size());
        
        tree.put(10, "root");
        tree.put(8, "eight");
        tree.put(12, "twelve");
        tree.put(6, "six");
        tree.put(14, "fourteen");
        
        assertEquals(tree.size(), 5);
        
        // Removing from a node that has only a left child
        assertEquals("eight", tree.remove(8));      
        assertEquals(4, tree.size());
        
        // Removing from a node that has only a left child
        assertEquals("twelve", tree.remove(12));      
        assertEquals(3, tree.size());
        
        // Removing from a node that has both children
        tree.put(13, "thirteen");
        tree.put(15, "fifteen");
        
        assertEquals("fourteen", tree.remove(14));
        
    }
}