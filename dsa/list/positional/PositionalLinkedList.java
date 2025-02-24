package edu.ncsu.csc316.dsa.list.positional;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ncsu.csc316.dsa.Position;

/**
 * The Positional Linked List is implemented as a doubly-linked list data
 * structure to support efficient, O(1) worst-case Positional List abstract data
 * type behaviors.
 * 
 * Size is maintained as a global field to ensure O(1) worst-case runtime of
 * size() and isEmpty().
 * 
 * The PositionalLinkedList class is based on the implementation developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley & Sons, 2014
 * 
 * @author Dr. King
 * @author Jack Farrell
 *
 * @param <E> the type of elements stored in the positional list
 */
public class PositionalLinkedList<E> implements PositionalList<E> {

	/** A dummy/sentinel node representing at the front of the list **/
	private PositionalNode<E> front;

	/** A dummy/sentinel node representing at the end/tail of the list **/
	private PositionalNode<E> tail;

	/** The number of elements in the list **/
	private int size;

	/**
	 * Constructs an empty positional linked list
	 */
	public PositionalLinkedList() {
		front = new PositionalNode<E>(null);
		tail = new PositionalNode<E>(null, null, front);
		front.setNext(tail);
		size = 0;
	}

	private Position<E> addBetween(E element, PositionalNode<E> next, PositionalNode<E> prev) {
		PositionalNode<E> node = new PositionalNode<E>(element, next, prev);
		prev.setNext(node);
		next.setPrevious(node);
		size++;
		return node;
	}

	@Override
	public Position<E> addAfter(Position<E> p, E element) {
		PositionalNode<E> node = validate(p);
		return addBetween(element, node.getNext(), node);
	}

	@Override
	public Position<E> addBefore(Position<E> p, E element) {
		PositionalNode<E> node = validate(p);
		return addBetween(element, node, node.getPrevious());
	}

	@Override
	public Position<E> addFirst(E element) {
		return addBetween(element, front.getNext(), front);
	}

	@Override
	public Position<E> addLast(E element) {
		return addBetween(element, tail, tail.getPrevious());
	}

	@Override
	public Position<E> after(Position<E> p) {
		PositionalNode<E> node = validate(p);
		PositionalNode<E> next = node.getNext();

		return (next != tail) ? next : null;
	}

	@Override
	public Position<E> before(Position<E> p) {
		PositionalNode<E> node = validate(p);
		PositionalNode<E> next = node.getPrevious();

		return (next != front) ? next : null;
	}

	@Override
	public Position<E> first() {
		if (isEmpty()) {
			return null;
		}
		return front.getNext();
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Position<E> last() {
		if (isEmpty()) {
			return null;
		}
		return tail.getPrevious();
	}

	@Override
	public E remove(Position<E> p) {
		PositionalNode<E> node = validate(p);
		PositionalNode<E> prevNode = node.getPrevious();
		PositionalNode<E> nextNode = node.getNext();
		size--;
		prevNode.setNext(nextNode);
		nextNode.setPrevious(prevNode);

		return node.getElement();
	}

	@Override
	public E set(Position<E> p, E element) {
		PositionalNode<E> node = validate(p);
		E e = node.getElement();
		node.setElement(element);
		return e;
	}

	@Override
	public int size() {
		return size;
	}

	private static class PositionalNode<E> implements Position<E> {

		/**
		 * Generic element
		 */
		private E element;
		/**
		 * The positional node that is next
		 */
		private PositionalNode<E> next;
		/**
		 * The positional node that is previous
		 */
		private PositionalNode<E> previous;

		public PositionalNode(E value) {
			this(value, null);
		}

		public PositionalNode(E value, PositionalNode<E> next) {
			this(value, next, null);
		}

		public PositionalNode(E value, PositionalNode<E> next, PositionalNode<E> prev) {
			setElement(value);
			setNext(next);
			setPrevious(prev);
		}

		public void setPrevious(PositionalNode<E> prev) {
			previous = prev;
		}

		public PositionalNode<E> getPrevious() {
			return previous;
		}

		public void setNext(PositionalNode<E> next) {
			this.next = next;
		}

		public PositionalNode<E> getNext() {
			return next;
		}

		@Override
		public E getElement() {
			return element;
		}

		public void setElement(E element) {
			this.element = element;
		}
	}

	/**
	 * Safely casts a Position, p, to be a PositionalNode.
	 * 
	 * @param p the position to cast to a PositionalNode
	 * @return a reference to the PositionalNode
	 * @throws IllegalArgumentException if p is null, or if p is not a valid
	 *                                  PositionalNode
	 */
	private PositionalNode<E> validate(Position<E> p) {
		if (p instanceof PositionalNode) {
			return (PositionalNode<E>) p;
		}
		throw new IllegalArgumentException("Position is not a valid positional list node.");
	}

	private class PositionIterator implements Iterator<Position<E>> {

		/**
		 * The current position
		 */
		private Position<E> current;
		/**
		 * The previous position
		 */
		private Position<E> previous;
		/**
		 * Boolean to say if it is able to remove or not
		 */
		private boolean removeOK;

		public PositionIterator() {
			current = first();
			previous = null;
			removeOK = false;
		}

		@Override
		public boolean hasNext() {
//			if (size == 0) {
//				return false;
//			}
			return current != null && size != 0;

		}

		@Override
		public Position<E> next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			Position<E> node = current;
			previous = current;
			current = after(current);
			removeOK = true;
			return node;
		}

		@Override
		public void remove() {
			if (!removeOK) {
				throw new IllegalStateException();
			}
			PositionalLinkedList.this.remove(previous);

			removeOK = false;
		}
	}

	private class ElementIterator implements Iterator<E> {

		/**
		 * Iterator for position
		 */
		private Iterator<Position<E>> it;

		public ElementIterator() {
			it = new PositionIterator();
		}

		@Override
		public boolean hasNext() {
			return it.hasNext();
		}

		@Override
		public E next() {
			return it.next().getElement();
		}

		@Override
		public void remove() {
			it.remove();
		}
	}

	private class PositionIterable implements Iterable<Position<E>> {

		@Override
		public Iterator<Position<E>> iterator() {
			return new PositionIterator();
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}

	@Override
	public Iterable<Position<E>> positions() {
		return new PositionIterable();
	}

}
