package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * Parent class of all comparison based sorting algorithms
 * 
 * @author Jack Farrell
 * 
 * @param <E> the generic data that will be sorted
 */
public abstract class AbstractComparisonSorter<E extends Comparable<E>> implements Sorter<E> {

	/**
	 * Custom comparator that can be used instead of the java util one
	 */
	private Comparator<E> comparator;

	/**
	 * Method that takes in a comparator and sets it to our custom implementation
	 * 
	 * @param comparator custom comparator
	 */
	public AbstractComparisonSorter(Comparator<E> comparator) {
		setComparator(comparator);
	}

	/**
	 * Method that sets the comparator to our custom settings
	 * 
	 * @param comparator custom comparator that is being set
	 */
	private void setComparator(Comparator<E> comparator) {
		if (comparator == null) {
			this.comparator = new NaturalOrder();
		} else {
			this.comparator = comparator;
		}
	}

	/**
	 * Inner class that handles the comparisons and returns the sorted order
	 */
	private class NaturalOrder implements Comparator<E> {
		public int compare(E first, E second) {
			return ((Comparable<E>) first).compareTo(second);
		}
	}

	/**
	 * The compare method to assess the following, returns a positive, zero, or
	 * negative number
	 * 
	 * @param first first element to be compared
	 * @param second second element to be compared
	 * @return the comparison of the elements
	 */
	public int compare(E first, E second) {
		return comparator.compare(first, second);
	}
}
