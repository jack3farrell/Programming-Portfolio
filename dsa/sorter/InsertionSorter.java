package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * InsertionSorter uses the insertion sort algorithm to sort data.
 * 
 * @author Dr. King
 * @author Jack Farrell
 * 
 * @param <E> the generic elements 
 */
public class InsertionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

//	/**
//	 * Custom comparator that can be used instead of the java util one
//	 */
//	private Comparator<E> comparator;

	/**
	 * Creates the insertion sorter
	 */
	public InsertionSorter() {
		super(null);
	}

	/**
	 * Creates the insertion sorter using a comparator
	 * @param comparator the custom implemented comparator
	 */
	public InsertionSorter(Comparator<E> comparator) {
		super(comparator);
	}

//	private void setComparator(Comparator<E> comparator) {
//		if (comparator == null) {
//			this.comparator = new NaturalOrder();
//		} else {
//			this.comparator = comparator;
//		}
//	}

	/**
	 * The insertion sort algorithm for generic elements 
	 * 
	 * @param data the generic data to be sorted
	 */
	public void sort(E[] data) {
		for (int i = 1; i <= data.length - 1; i++) {
			E x = data[i];
			int j = i - 1;
			while (j >= 0 && (compare(data[j], x) > 0)) {
				data[j + 1] = data[j];
				j = j - 1;
			}
			data[j + 1] = x;
		}

	}
	
//	private class NaturalOrder implements Comparator<E> {
//		public int compare(E first, E second) {
//			return ((Comparable<E>) first).compareTo(second);
//		}
//	}
	
}




