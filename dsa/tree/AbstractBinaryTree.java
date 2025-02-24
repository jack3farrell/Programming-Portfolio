package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * A skeletal implementation of the Binary Tree abstract data type. This class
 * provides implementation for common methods that can be implemented the same
 * no matter what specific type of concrete data structure is used to implement
 * the binary tree abstract data type.
 * 
 * @author Dr. King
 * @author Jack Farrell
 *
 * @param <E> the type of elements stored in the binary tree
 */
public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {

	// Possibly wrong
	@Override
	public Iterable<Position<E>> inOrder() {
		PositionCollection traversal = new PositionCollection();
		if (!isEmpty()) {
			inOrderHelper(root(), traversal);
		}
		return traversal;
	}

	private void inOrderHelper(Position<E> p, PositionCollection traversal) {
		Position<E> temp = validate(p);

		if (left(temp) != null) {
			inOrderHelper(left(temp), traversal);
		}
		if (p.getElement() != null) { // do not add sentinel nodes to the traversal
			traversal.add(p);
		}		if (right(temp) != null) {
			inOrderHelper(right(temp), traversal);
		}
	}

	@Override
	public int numChildren(Position<E> p) {
		int count = 0;
		AbstractTreeNode<E> node = validate(p);
		if (left(node) != null) {
			count++;
		}
		if (right(node) != null) {
			count++;
		}
		return count;
	}

	@Override
	public Position<E> sibling(Position<E> p) {
		AbstractTreeNode<E> node = validate(p);
		Position<E> parent = parent(p);

		if (parent == null) {
			return null;
		}
		if (node == left(parent)) {
			return right(parent);
		} else {
			return left(parent);
		}
	}

	@Override
	public Iterable<Position<E>> children(Position<E> p) {
		AbstractTreeNode<E> node = validate(p);
		PositionCollection childrenCollection = new PositionCollection();
		if (left(node) != null) {
			childrenCollection.add(left(node));
		}
		if (right(node) != null) {
			childrenCollection.add(right(node));
		}
		return childrenCollection;
	}
}
