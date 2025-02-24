package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * SelectionSorter uses the selection sort algorithm to sort data
 * 
 * @author Dr. King
 * @author Jack Farrell
 *
 * @param <E> the generic type of data to sort
 */
public class SelectionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

//	/**
//	 * The custom comparator used for the sorting
//	 */
//	private Comparator<E> comparator;

	/**
	 * The selection sorter using a comparator
	 * 
	 * @param comparator custom comparator
	 */
	public SelectionSorter(Comparator<E> comparator) {
		super(comparator);
	}

	/**
	 * Creating the selection sorter without a comparator
	 */
	public SelectionSorter() {
		super(null);
	}


	/**
	 * The selection sort algorithm for generic elements
	 * 
	 * @param data the generic data to be sorted
	 */
	public void sort(E[] data) {
		for (int i = 0; i <= data.length - 1; i++) {
			int min = i;

			for (int j = i + 1; j <= data.length - 1; j++) {
				if (this.compare(data[j], data[min]) < 0) {
					min = j;
				}
			}

			if (i != min) {
				E x = data[i];
				data[i] = data[min];
				data[min] = x;
			}
		}

	}

//	private class NaturalOrder implements Comparator<E> {
//		public int compare(E first, E second) {
//			return ((Comparable<E>) first).compareTo(second);
//		}
//	}
}
