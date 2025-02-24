package edu.ncsu.csc316.dsa.map.hashing;

import edu.ncsu.csc316.dsa.map.Map;

/**
 * The LinearProbingHashMap is implemented as a hash table that uses linear
 * probing for collision resolution.
 * 
 * The hash map uses a multiply-and-divide compression strategy for calculating
 * hash functions. The hash map ensures expected O(1) performance of
 * {@link Map#put}, {@link Map#get}, and {@link Map#remove}.
 * 
 * The hash table resizes if the load factor exceeds 0.5.
 * 
 * The LinearProbingHashMap class is based on the implementation developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 * @author Jack Farrell
 *
 * @param <K> the type of keys stored in the hash map
 * @param <V> the type of values associated with keys in the hash map
 */
public class LinearProbingHashMap<K, V> extends AbstractHashMap<K, V> {

	/**
	 * Table entry is a table full of keys and values
	 */
	private TableEntry<K, V>[] table;
	/**
	 * Size of table
	 */
	private int size;

	/**
	 * Constructs a new linear probing hash map that uses natural ordering of keys
	 * when performing comparisons. The created hash table uses the
	 * {@link AbstractHashMap#DEFAULT_CAPACITY}
	 */
	public LinearProbingHashMap() {
		this(AbstractHashMap.DEFAULT_CAPACITY);
	}

	/**
	 * Constructs a new linear probing hash map that uses natural ordering of keys
	 * when performing comparisons. The created hash table is initialized to have
	 * the provided capacity.
	 * 
	 * @param capacity the initial capacity of the hash table
	 */
	public LinearProbingHashMap(int capacity) {
		this(capacity, false);
	}

	/**
	 * FOR TESTING PURPOSES ONLY! Constructs a new linear probing hash map that uses
	 * natural ordering of keys when performing comparisons. The created hash table
	 * is initialized to have the provided capacity.
	 * 
	 * @param capacity  the initial capacity of the hash table
	 * @param isTesting if true, the hash table uses a predictable series of random
	 *                  values for deterministic and repeatable testing
	 */
	public LinearProbingHashMap(int capacity, boolean isTesting) {
		super(capacity, isTesting);
		size = 0;
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() {
		EntryCollection collection = new EntryCollection();
		int counter = 0;
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null && !table[i].isDeleted()) {
				collection.add(table[i]);
				counter++;
			}
			if (counter == size) {
				return collection;
			}

		}
		return collection;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void createTable(int capacity) {
		table = (TableEntry<K, V>[]) new TableEntry[capacity];
		size = 0;
	}

	private boolean isAvailable(int index) {
		return table[index] == null || table[index].isDeleted();
	}

	@Override
	public V bucketGet(int hash, K key) {
		int num = findBucket(hash, key);
		if (num < 0) {
			return null;
		}
		return table[num].getValue();
	}

	@Override
	public V bucketPut(int hash, K key, V value) {
		// : should begin by calling findBucket to locate the bucket that contains key
		// to update; if the key doesn’t exist, add the new entry at the bucket
		// indicated by the value returned by findBucket
		int num = findBucket(hash, key);
		if (num >= 0) {
			V value1 = table[num].getValue();
			table[num].setValue(value);
			return value1;
		}
		table[-(num + 1)] = new TableEntry<K, V>(key, value);
		size++;
		return null;
	}

	private int findBucket(int index, K key) {
		int avail = -1;
		int j = index;
		do {
			if (isAvailable(j)) {
				if (avail == -1) {
					avail = j;
				}
				if (table[j] == null) {
					return -(avail + 1);
				}
			} else if (table[j].getKey().equals(key)) {
				return j;
			}
			j = (j + 1) % table.length;
		} while (j != index);
		return -(avail + 1);
	}

	@Override
	public V bucketRemove(int hash, K key) {
		int num = findBucket(hash, key);
		if (num < 0) {
			return null;
		}
		V val = table[num].getValue();
		table[num].setDeleted(true);
		size--;
		return val;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	protected int capacity() {
		return table.length;
	}

	private static class TableEntry<K, V> extends MapEntry<K, V> {

		/**
		 * Is Deleted returns true if the element is supposed to be deleted
		 */
		private boolean isDeleted;

		public TableEntry(K key, V value) {
			super(key, value);
			setDeleted(false);
		}

		public boolean isDeleted() {
			return isDeleted;
		}

		public void setDeleted(boolean deleted) {
			isDeleted = deleted;
		}
	}
}
