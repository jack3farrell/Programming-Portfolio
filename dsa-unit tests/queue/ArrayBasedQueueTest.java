package edu.ncsu.csc316.dsa.queue;

import static org.junit.Assert.*;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ArrayBasedQueue.
 * Checks the expected outputs of the Queue abstract data type behaviors when using
 * a circular array-based data structure
 *
 * @author Dr. King
 * @author Jack Farrell
 *
 */
public class ArrayBasedQueueTest {

	/**
	 * Queue for string objects
	 */
    private Queue<String> queue;
    
    /**
     * Create a new instance of a circular array-based queue before each test case executes
     */ 
    @Before
    public void setUp() {
        queue = new ArrayBasedQueue<String>();
    }

    /**
     * Test the output of the enqueue(e) behavior
     */     
    @Test
    public void testEnqueue() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        
        queue.enqueue("one");
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
        
        queue.enqueue("two");
        assertEquals(2, queue.size());
        assertFalse(queue.isEmpty());
        
		// Checking for null object
        queue.enqueue(null);
		assertEquals(3, queue.size());        
    }
    
    /**
     * Test the output of the dequeue(e) behavior, including expected exceptions
     */     
    @Test
    public void testDequeue() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        
        // Testing dequeue() on empty queue
        try {
            queue.dequeue();
            fail("NoSuchElementException should have been thrown.");        
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }        
        
        // Adding to queue
        queue.enqueue("one");
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
        
        // Adding to queue
        queue.enqueue("two");
        assertEquals(2, queue.size());
        assertFalse(queue.isEmpty());
        
        // Testing dequeue
        assertEquals(queue.dequeue(), "one");
        assertEquals(queue.dequeue(), "two");
        
        // Testing dequeue() on empty queue
        try {
            queue.dequeue();
            fail("NoSuchElementException should have been thrown.");        
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }        
        
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
    }
    
    /**
     * Test the output of the front() behavior, including expected exceptions
     */     
    @Test
    public void testFront() {
    	assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        
        // Testing front() on empty queue
        try {
            queue.front();
            fail("NoSuchElementException should have been thrown.");        
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }        
        
        // Adding to queue
        queue.enqueue("one");
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
        
        // Adding to queue 
        queue.enqueue("two");
        assertEquals(2, queue.size());
        assertFalse(queue.isEmpty());
        
        // Testing front()
        assertEquals(queue.front(), "one");
        assertEquals(queue.dequeue(), "one");
        assertEquals(queue.front(), "two");
        assertEquals(queue.dequeue(), "two");
        
        // Testing front() on empty queue
        try {
            queue.front();
            fail("NoSuchElementException should have been thrown.");        
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }        
        
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());    }

}