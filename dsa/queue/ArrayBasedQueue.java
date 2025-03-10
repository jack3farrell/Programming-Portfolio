package edu.ncsu.csc316.dsa.queue;

import java.util.NoSuchElementException;

/**
 * The Array-based Queue is implemented as a circular array-based data structure
 * to support efficient, O(1) worst-case Queue abstract data type behaviors. The
 * internal array dynamically resizes using the doubling strategy to ensure O(1)
 * amortized cost for adding to the queue.
 * 
 * @author Dr. King
 * @author Jack Farrell
 *
 * @param <E> the type of elements stored in the queue
 */
public class ArrayBasedQueue<E> extends AbstractQueue<E> {

	/**
	 * Internal array to store the data within the queue
	 */
	private E[] data;

	/**
	 * A reference to the index of the first element in the queue
	 */
	private int front;

	/**
	 * A reference to the index immediately after the last element in the queue
	 */
	private int rear;

	/**
	 * The number of elements stored in the queue
	 */
	private int size;

	/**
	 * The initial default capacity of the internal array that stores the data
	 */
	private static final int DEFAULT_CAPACITY = 0;

	/**
	 * Constructs a new array-based queue with the given initialCapcity for the
	 * array
	 * 
	 * @param initialCapacity the initial capacity to use when creating the initial
	 *                        array
	 */
	@SuppressWarnings("unchecked")
	public ArrayBasedQueue(int initialCapacity) {
		data = (E[]) new Object[initialCapacity];
		size = 0;
	}

	/**
	 * Constructs a new array-based queue with the default initial capacity for the
	 * array
	 */
	public ArrayBasedQueue() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * To ensure amortized O(1) cost for adding to the array-based queue, use the
	 * doubling strategy on each resize. Here, we add +1 after doubling to handle
	 * the special case where the initial capacity is 0 (otherwise, 0*2 would still
	 * produce a capacity of 0).
	 * 
	 * @param minCapacity the minimium capacity that must be supported by the
	 *                    internal array
	 */
	private void ensureCapacity(int minCapacity) {
		int oldCapacity = data.length;
		if (minCapacity > oldCapacity) {
			int newCapacity = (oldCapacity * 2) + 1;
			if (newCapacity < minCapacity) {
				newCapacity = minCapacity;
			}
			@SuppressWarnings("unchecked")
			E[] newData = (E[]) new Object[newCapacity];
			// Remember that we cannot copy an array the same way we do
			// when resizing an array-based list since we do not want to
			// "break" the elements in the queue since there may be wrap-around
			// at the end of the array
			// the element at index (front+1) becomes the element at index 1 in the new,
			// larger array.
			for (int i = 0; i < size; i++) {
				newData[i] = data[front + i];
			}

			front = 0;
			rear = size;
			data = newData;
		}
	}

	@Override
	public void enqueue(E element) {
		ensureCapacity(data.length + 1);
		size++;
		data[rear] = element;
		// Might not work correctly
		rear++;
	}

	@Override
	public E dequeue() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		ensureCapacity(data.length - 1);
		E returnElement = data[front];
		front = (front + 1) % data.length;
		size--;
//		for (int i = 0; i <= size; i++) {
//			data[i] = data[i + 1];
//		}
		return returnElement;
	}

	@Override
	public E front() {
		if(size == 0) {
			throw new NoSuchElementException();
		}
		return data[front];
	}

	@Override
	public int size() {
		return size;
	}
}