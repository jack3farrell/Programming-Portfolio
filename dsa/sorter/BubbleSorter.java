package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * BubbleSorter uses the bubble sort algorithm to sort data
 * 
 * @author Jack Farrell
 * 
 * @param <E> generic element
 */
public class BubbleSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

//	/**
//	 * Custom comparator that can be used instead of the java util one
//	 */
//	private Comparator<E> comparator;

	/**
	 * Creating a bubble sorter with a comparator
	 * 
	 * @param comparator a custom implemented comparator
	 */
	public BubbleSorter(Comparator<E> comparator) {
		super(comparator);
	}

	/**
	 * Creating the bubble sorter without a comparator
	 */
	public BubbleSorter() {
		super(null);
	}

	/**
	 * The comparison bubble sorting algorithm
	 * 
	 * @param data the generic data being sorted
	 */
	public void sort(E[] data) {
		boolean r = true;
		while (r) {
			r = false;
			for (int i = 1; i <= data.length - 1; i++) {
				if (compare(data[i], data[i - 1]) < 0) {
					E x = data[i - 1];
					data[i - 1] = data[i];
					data[i] = x;
					r = true;
				}
			}
		}
	}
}
