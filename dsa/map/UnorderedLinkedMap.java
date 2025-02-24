package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * An unordered link-based map is an unordered (meaning keys are not used to
 * order entries) linked-memory representation of the Map abstract data type.
 * This link-based map delegates to an existing doubly-linked positional list.
 * To help self-organizing entries to improve efficiency of lookUps, the
 * unordered link-based map implements the move-to-front heuristic: each time an
 * entry is accessed, it is shifted to the front of the internal list.
 * 
 * @author Dr. King
 * @author Jack Farrell
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class UnorderedLinkedMap<K, V> extends AbstractMap<K, V> {

	/**
	 * The concrete positional list data structure to hold the data in this map
	 */
	private PositionalList<Entry<K, V>> list;

	/**
	 * The constructor for the unordered linked map
	 */
	public UnorderedLinkedMap() {
		this.list = new PositionalLinkedList<Entry<K, V>>();
	}

	private Position<Entry<K, V>> lookUp(K key) {
		Position<Entry<K, V>> currentPosition = list.first();

		// Loop while positions are left
		while (currentPosition != null) {
			Entry<K, V> entry = currentPosition.getElement();

			if (entry.getKey().equals(key)) {
				moveToFront(currentPosition);
				return currentPosition;
			}
			currentPosition = list.after(currentPosition);

		}

		return null;

	}

	@Override
	public V get(K key) {
		Position<Entry<K, V>> p = lookUp(key);
		if (p != null) {
			return p.getElement().getValue();
		}
		return null;
	}

	private void moveToFront(Position<Entry<K, V>> position) {
		// This getElement() method returns the key and value
		// Not sure why we can't just use the position parameter variable name instead
		// of making a
		// new one, has to be something to do with entry?
		Entry<K, V> entry = position.getElement();

		// Remove original position
		list.remove(position);
		list.addFirst(entry);
	}

	@Override
	public V put(K key, V value) {
		Position<Entry<K, V>> p = lookUp(key);

		if (p == null) {
			// New entry
			Entry<K, V> newEntry = new MapEntry<>(key, value);
			list.addFirst(newEntry);
			return null;
		} else {
			// Update
			V oldValue = p.getElement().getValue();
			list.remove(p);
			MapEntry<K, V> update = new MapEntry<>(key, value);
			list.addFirst(update);
			return oldValue;
		}

	}

	@Override
	public V remove(K key) {
		Position<Entry<K, V>> p = lookUp(key);

			// Finds if the key is in the list, have to use .getElement() for maps and
			// .getValue to return the value as required by the method
		if(p != null) {
			return list.remove(p).getValue();
		} else {
			return null;
		}
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() {
		EntryCollection collection = new EntryCollection();
		for (Entry<K, V> entry : list) {
			collection.add(entry);
		}
		return collection;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("UnorderedLinkedMap[");
		Iterator<Entry<K, V>> it = list.iterator();
		while (it.hasNext()) {
			sb.append(it.next().getKey());
			if (it.hasNext()) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}

}