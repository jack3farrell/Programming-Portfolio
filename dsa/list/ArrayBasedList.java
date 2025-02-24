package edu.ncsu.csc316.dsa.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An array-based list is a contiguous-memory representation of the List
 * abstract data type. This array-based list dynamically resizes to ensure O(1)
 * amortized cost for adding to the end of the list. Size is maintained as a
 * global field to allow for O(1) size() and isEmpty() behaviors.
 * 
 * @author Dr. King
 * @author Jack Farrell
 *
 * @param <E> the type of elements stored in the list
 */
public class ArrayBasedList<E> extends AbstractList<E> {

	/**
	 * The initial capacity of the list if the client does not provide a capacity
	 * when constructing an instance of the array-based list
	 **/
	private final static int DEFAULT_CAPACITY = 0;

	/** The array in which elements will be stored **/
	private E[] data;

	/** The number of elements stored in the array-based list data structure **/
	private int size;

	/**
	 * Constructs a new instance of an array-based list data structure with the
	 * default initial capacity of the internal array
	 */
	public ArrayBasedList() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Constructs a new instance of an array-based list data structure with the
	 * provided initial capacity
	 * 
	 * @param capacity the initial capacity of the internal array used to store the
	 *                 list elements
	 */
	@SuppressWarnings("unchecked")
	public ArrayBasedList(int capacity) {
		data = (E[]) new Object[capacity];
		size = 0;
	}

	/**
	 * This method adds an element to the index and increments the size
	 * 
	 * @param index the location of where to add the data
	 * @param value the value to add into the array
	 * @throws NullPointerException if the element entered is null
	 */
	public void add(int index, E value) {
		// Might not need this
		if (value == null) {
			throw new NullPointerException("Null element");
		}

		checkIndexForAdd(index);
		ensureCapacity(size() + 1);

		for (int i = size; i > index; i--) {
			data[i] = data[i - 1];
		}

		data[index] = value;
		size++;
	}

	/**
	 * To ensure amortized O(1) cost for adding to the end of the array-based list,
	 * use the doubling strategy on each resize. Here, we add +1 after doubling to
	 * handle the special case where the initial capacity is 0 (otherwise, 0*2 would
	 * still produce a capacity of 0).
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
			data = Arrays.copyOf(data, newCapacity);
		}
	}

	/**
	 * This method returns the element at a given index
	 * 
	 * @param index the location of the element
	 * 
	 * @return the element from the chosen index
	 */
	public E get(int index) {
		checkIndex(index);
		return data[index];
	}

	/**
	 * This method returns the element iterator
	 * 
	 * @return element iterator
	 */
	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}

	/**
	 * This method removes an element at a given index
	 * 
	 * @param index the location of the element
	 * 
	 * @return the element from the chosen index
	 */
	public E remove(int index) {
		checkIndex(index);

		E temp = data[index];
		for (int i = index; i < size - 1; i++) {
			data[i] = data[i + 1];
		}

		data[size - 1] = null;
		--size;
		return temp;
	}

	/**
	 * This method sets the value at a given index
	 * 
	 * @param index the location of the element
	 * @param value the value to switch into the array
	 * 
	 * @return the element that was taken out
	 */
	public E set(int index, E value) {
		checkIndex(index);

		E temp = data[index];
		data[index] = value;

		return temp;
	}

	/**
	 * This method returns the size field of the array
	 * 
	 * @return the size field
	 */
	public int size() {
		return size;
	}

	private class ElementIterator implements Iterator<E> {
		/**
		 * The position of the iterator
		 */
		private int position;

		/**
		 * The boolean for allowing the iterator or remove or not at a certain index
		 */
		private boolean removeOK;

		/**
		 * Construct a new element iterator where the cursor is initialized to the
		 * beginning of the list.
		 */
		public ElementIterator() {
			position = 0;
			removeOK = false;
		}

		/**
		 * Method returns true if position is less than size or false if not
		 * 
		 * @return true or false
		 */
		@Override
		public boolean hasNext() {
			return position < size;

		}

		/**
		 * Moves the position of the iterator
		 * 
		 * @return the element where the iterator is past
		 */
		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			removeOK = true;
			return data[position++];
		}

		/**
		 * This method removes the element in the array that the iterator has gone past
		 * 
		 */
		@Override
		public void remove() {
			if (!removeOK) {
				throw new IllegalStateException();
			}
			// Decrementing could be potentially wrong here
			ArrayBasedList.this.remove(position - 1);
			position--;
			removeOK = false;
		}
	}
}
