package edu.ncsu.csc316.dsa.stack;

import java.util.EmptyStackException;

import edu.ncsu.csc316.dsa.list.SinglyLinkedList;

/**
 * The Linked Stack is implemented as a singly-linked list data structure to
 * support efficient, O(1) worst-case Stack abstract data type behaviors.
 * 
 * @author Dr. King
 * @author Jack Farrell
 *
 * @param <E> the type of elements stored in the stack
 */
public class LinkedStack<E> extends AbstractStack<E> {

	/** Delegate to our existing singly linked list class **/
	private SinglyLinkedList<E> list;

	/**
	 * Construct a new singly-linked list to use when modeling the last-in-first-out
	 * paradigm for the stack abstract data type.
	 */
	public LinkedStack() {
		list = new SinglyLinkedList<E>();
	}

	@Override
	public void push(E element) {
		list.addFirst(element);
	}

	@Override
	public E pop() {
		// could use isEmpty()
		if(list.size() <= 0) {
			throw new EmptyStackException();
		}
		return list.removeFirst();
	}

	@Override
	public E top() {
		// could use isEmpty()
		if(list.size() <= 0) {
			throw new EmptyStackException();
		}
		return list.first();
	}

	@Override
	public int size() {
		return list.size();
	}
}