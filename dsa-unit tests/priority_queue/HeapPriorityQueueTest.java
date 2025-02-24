package edu.ncsu.csc316.dsa.priority_queue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;

/**
 * Test class for HeapPriorityQueue Checks the expected outputs of the Priorty
 * Queue abstract data type behaviors when using a min-heap data structure
 *
 * @author Dr. King
 * @author Jack Farrell
 *
 */
public class HeapPriorityQueueTest {

	/**
	 * PQ for heap data structure
	 */
	private PriorityQueue<Integer, String> heap;

	/**
	 * Create a new instance of a heap before each test case executes
	 */
	@Before
	public void setUp() {
		heap = new HeapPriorityQueue<Integer, String>();
	}

	/**
	 * Test the output of the insert(k,v) behavior
	 */
	@Test
	public void testInsert() {
		assertTrue(heap.isEmpty());
		assertTrue(heap.size() == 0);

		heap.insert(8, "eight");
		assertEquals(1, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(8, (int) heap.min().getKey());
		
		heap.insert(6, "six");
		assertEquals(2, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(6, (int) heap.min().getKey());
		
		heap.insert(4, "four");
		assertEquals(3, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(4, (int) heap.min().getKey());

	}

	/**
	 * Test the output of the min behavior
	 */
	@Test
	public void testMin() {
		assertTrue(heap.isEmpty());
		assertTrue(heap.size() == 0);

		assertNull(heap.min());

		heap.insert(8, "eight");
		assertEquals(1, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(8, (int) heap.min().getKey());
		
		heap.insert(6, "six");
		assertEquals(2, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(6, (int) heap.min().getKey());
		
		heap.insert(4, "four");
		assertEquals(3, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(4, (int) heap.min().getKey());
		
		heap.insert(20, "tweny");
		assertEquals(4, heap.size());
		assertFalse(heap.isEmpty());
		
		heap.insert(64, "sixty four");
		assertEquals(5, heap.size());
		assertFalse(heap.isEmpty());
		
		heap.insert(2, "two");
		assertEquals(6, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(2, (int) heap.min().getKey());
	}

	/**
	 * Test the output of the deleteMin behavior
	 */
	@Test
	public void deleteMin() {
		assertTrue(heap.isEmpty());
		assertTrue(heap.size() == 0);

		assertNull(heap.min());

		heap.insert(8, "eight");
		assertEquals(1, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(8, (int) heap.min().getKey());
		
		heap.insert(6, "six");
		assertEquals(2, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(6, (int) heap.min().getKey());
		
		heap.insert(4, "four");
		assertEquals(3, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(4, (int) heap.min().getKey());
		
		heap.insert(20, "tweny");
		assertEquals(4, heap.size());
		assertFalse(heap.isEmpty());
		
		heap.insert(64, "sixty four");
		assertEquals(5, heap.size());
		assertFalse(heap.isEmpty());
		
		heap.insert(2, "two");
		assertEquals(6, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(2, (int) heap.min().getKey());

		
		
		heap.deleteMin();
		assertEquals(4, (int) heap.min().getKey());
		
		heap.deleteMin();
		assertEquals(6, (int) heap.min().getKey());

		heap.deleteMin();
		assertEquals(8, (int) heap.min().getKey());
		
		heap.deleteMin();
		assertEquals(20, (int) heap.min().getKey());
		
		heap.deleteMin();
		assertEquals(64, (int) heap.min().getKey());
	}

	/**
	 * Test the output of the heap behavior when using arbitrary key objects to
	 * represent priorities
	 */
	@Test
	public void testStudentHeap() {
		PriorityQueue<Student, String> sHeap = new HeapPriorityQueue<Student, String>(new StudentIDComparator());
		Student s1 = new Student("J", "K", 1, 1, 1, "jk1");
		Student s2 = new Student("J", "S", 2, 1, 2, "js2");
		Student s3 = new Student("S", "H", 3, 1, 3, "sh3");
		Student s4 = new Student("J", "J", 4, 1, 4, "jj4");
		Student s5 = new Student("L", "B", 5, 1, 5, "lb5");

		assertTrue(sHeap.isEmpty());
		assertEquals(0, sHeap.size());

		sHeap.insert(s1, "student1");
		sHeap.insert(s2, "student2");
		sHeap.insert(s3, "student3");
		sHeap.insert(s4, "student4");
		sHeap.insert(s5, "student5");
		assertEquals(5, sHeap.size());

		assertEquals(s1, sHeap.deleteMin().getKey());
		assertEquals(4, sHeap.size());

		assertEquals(s2, sHeap.deleteMin().getKey());
		assertEquals(3, sHeap.size());

		assertEquals(s3, sHeap.deleteMin().getKey());
		assertEquals(2, sHeap.size());
		
		assertEquals(s4, sHeap.deleteMin().getKey());
		assertEquals(1, sHeap.size());
		
		assertEquals(s5, sHeap.deleteMin().getKey());
		assertEquals(0, sHeap.size());
		
		assertTrue(sHeap.isEmpty());
	}
}