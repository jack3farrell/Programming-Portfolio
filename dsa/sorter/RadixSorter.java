package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * RadixSorter uses the radix sort algorithm to sort data
 * 
 * @author Dr. King
 * @author Jack Farrell
 *
 * @param <E> the generic type of data to sort
 */
public class RadixSorter<E extends Identifiable> implements Sorter<E> {

	/**
	 * The non-comparison radix sorting algorithm
	 * 
	 * @param data the generic data being sorted
	 */
	public void sort(E[] data) {
		// Find the largest value in the input data
		int k = 0;
		for (int i = 0; i <= data.length - 1; i++) {
			k = Math.max(k, data[i].getId());
		}
		// Determine how many digits are in the largest value
		int x = (int) Math.ceil(Math.log(k + 1) / Math.log(10));

		int p = 1;
		for (int j = 1; j <= x; j++) {
			int[] b = new int[10];
			for (int i = 0; i <= data.length - 1; i++) {
				b[data[i].getId() / p % 10] = b[data[i].getId() / p % 10] + 1;
			}

			for (int i = 1; i <= 9; i++) {
				b[i] = b[i - 1] + b[i];
			}

			@SuppressWarnings("unchecked")
			E[] f = (E[]) new Identifiable[data.length];

			for (int i = data.length - 1; i >= 0; i--) {
				f[b[data[i].getId() / p % 10] - 1] = data[i];
				b[data[i].getId() / p % 10] = b[data[i].getId() / p % 10] - 1;
			}
			for (int i = 0; i <= data.length - 1; i++) {
				data[i] = f[i];
			}
			p = p * 10;
		}

	}
}
