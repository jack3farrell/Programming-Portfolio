package edu.ncsu.csc316.dsa.sorter;

/**
 * Interface that defines the sorting behavior
 * @author Dr. King
 * @author Jack Farrell
 * 
 * @param <E> generic element
 */
public interface Sorter<E> {
	
	/**
	 * Interface for sorting method in comparison based sorting algorithms
	 * @param data an array that contains generic elements
	 */
    void sort(E[] data);
	
}
