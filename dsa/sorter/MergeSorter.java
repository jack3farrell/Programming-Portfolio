package edu.ncsu.csc316.dsa.sorter;

import java.util.Arrays;
import java.util.Comparator;

/**
 * MergeSorter sorts arrays of comparable elements using the merge sort
 * algorithm. This implementation ensures O(nlogn) worst-case runtime to sort an
 * array of n elements that are comparable.
 * 
 * @author Dr. King
 * @author // Your Name Here
 *
 * @param <E> the type of elements to sort; elements must be {@link Comparable}
 */
public class MergeSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/**
	 * Constructs a new MergeSorter with a specified custom Comparator
	 * 
	 * @param comparator a custom Comparator to use when sorting
	 */
	public MergeSorter(Comparator<E> comparator) {
		super(comparator);
	}

	/**
	 * Constructs a new MergeSorter with comparisons based on the element's natural
	 * ordering
	 */
	public MergeSorter() {
		this(null);
	}

	@Override
	public void sort(E[] data) {
//		Algorithm mergeSort(T)
//		Input an array T of n elements
//		Output array T with sorted elements
		int n = data.length;
		if (n < 2) {
			return;
		}
		int mid = n / 2;

		// The index is off and had to remove the minus 1 from mid in
		// left and n in right
		E[] left = Arrays.copyOfRange(data, 0, mid);
		E[] right = Arrays.copyOfRange(data, mid, n);

		sort(left);
		sort(right);

		merge(left, right, data);
	}

	/**
	 * Merging the sorted left and right sides of list
	 * 
	 * @param left  technically s1, but left side of list
	 * @param right technically s2, but right side of list
	 * @param data  technically s, whole list to sort
	 */
	private void merge(E[] left, E[] right, E[] data) {
//		Algorithm merge(left, right, T)
//		Input the original array T of n elements
//		      the array containing the sorted left half
//		      the array containing the sorted right half
//		Output merges the left and right arrays into the output sorted T array
		int leftIndex = 0;
		int rightIndex = 0;
		while (leftIndex + rightIndex < data.length) {
			if (rightIndex == right.length
					|| (leftIndex < left.length && compare(left[leftIndex], right[rightIndex]) < 0)) {
				data[leftIndex + rightIndex] = left[leftIndex];
				leftIndex = leftIndex + 1;
			} else {
				data[leftIndex + rightIndex] = right[rightIndex];
				rightIndex = rightIndex + 1;
			}
		}

	}
}