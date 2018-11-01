/**
 * Program #1
 * Ordered Array implementation of priority queue
 * CS310
 * 02/22/2018
 * @author jesuslopez cssc0736
 */
package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class OrderedArrayPriorityQueue<E extends Comparable<E>> implements PriorityQueue<E> {
	private static final int DEFAULT_MAX_CAPACITY =	1000;
	private E [] storage;
	private int currentSize;
	private int maxSize;
	
	public OrderedArrayPriorityQueue(int size) {
		maxSize = size;
		storage = (E[]) new Comparable[maxSize];
		currentSize = 0;
	}
	
	public OrderedArrayPriorityQueue() {
		this(DEFAULT_MAX_CAPACITY);
	}

	@Override
	/**
	 * Inserts a new object into the priority queue. Returns true if 
	 *  the insertion is successful. If the PQ is full, the insertion 
	 * is aborted, and the method returns false.
	 */
	public boolean insert(E t) {
		if (isFull())
			return false;
		
		int where = findInsertionPoint(t, 0, currentSize-1);
		for (int i=currentSize-1; i >= where; i--)
			storage[i+1] = storage[i];
		storage[where] = t;
		currentSize++;
		return true;
	}

	@Override
	/**
	 * Removes the object of highest priority that has been in the
	 * PQ the longest, and returns it. Returns null if the PQ is empty.
	 */
	public E remove() {
		if (isEmpty())
			return null;
		else {
			E priority = storage[currentSize -1];
			currentSize--;
			return priority;
		}
	}

	@Override
	/**
	 * Deletes all instances of the parameter obj from the PQ if found, and
	 * returns true. Returns false if no match to the parameter obj is found.
	 * Used binary search to make the method as efficient as possible.
	 */
	public boolean delete(E obj) {
		if (contains(obj) != true)
			return false;
		
		int first = findInsertionPoint(obj, currentSize - 1, 0);
		int last = binarySearchLastIndex(0, currentSize - 1, obj);
		
		while(last < currentSize) {
			storage[first++] = storage[last++];
		}
		currentSize = first;
		return true;
	}

	@Override
	/**
	 * Returns the object of highest priority that has been in the 
	 * PQ the longest, but does NOT remove it.
	 * Returns null if the PQ is empty.
	 */
	public E peek() {
		if (currentSize == 0)
			return null;
		else
			return storage[currentSize-1];
	}

	@Override
	/**
	 * Returns true if the priority queue contains the specified element 
	 * false otherwise. Used binary search to make the method as efficient
	 * as possible.
	 */
	public boolean contains(E obj) {
		if (binarySearch(0, currentSize-1, obj) >= 0)
			return true;
		else
			return false;
	}

	@Override
	/**
	 * Returns the number of objects currently in the PQ.
	 */
	public int size() {
		return currentSize;
	}

	@Override
	/**
	 * Returns the PQ to an empty state.
	 */
	public void clear() {
		currentSize = 0;
	}

	@Override
	/**
	 * Returns true if the PQ is empty, otherwise false
	 */
	public boolean isEmpty() {
		if (currentSize == 0)
			return true;
		return false;
	}

	@Override
	/**
	 * Returns true if the PQ is full, otherwise false. List based 
	 * implementations should always return false.
	 */
	public boolean isFull() {
		if (currentSize == maxSize)
			return true;
		return false;
	}

	@Override
	/**
	 * Returns an iterator of the objects in the PQ, in no particular 
	 * order.
	 */
	public Iterator<E> iterator() {
		return new IteratorHelper();
	}
	class IteratorHelper<E> implements Iterator <E> {
		int index = 0;
		public boolean hasNext() {
				return (index < size());
		}
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return (E) storage[index++];
		}
	}
	
	/**
	 * Binary search to see if the item exists. The first search will be in 
	 * the middle. If found it will return the item at that index. Otherwise it 
	 * will search below or above the middle index based on the items value. 
	 * Returns -1 if the item does not exist.
	 * @param start
	 * @param end
	 * @param key
	 * @return
	 */
	private int binarySearch(int start, int end, E key) {
		if(end >= start) {
			int mid = (start + end) / 2;
		if(((Comparable<E>) storage[mid]).compareTo(key) == 0)
			return mid;
		else if(((Comparable<E>)storage[mid]).compareTo(key) < 0) {
			return binarySearch(start, mid-1, key);
		}
		else 
			return binarySearch(mid + 1, end, key);
	}
	return - 1;
	}
	
	/**
	 * Binary search to find the last occurrence of an item.
	 * @param start
	 * @param end
	 * @param key
	 * @return
	 */
	private int binarySearchLastIndex(int start, int end, E key) {
		if(end < start)
			return start;
		else if (end >= start) {
			int mid = (start + end) / 2;
			if(((Comparable<E>) storage[mid]).compareTo(key) >= 0) {
				return binarySearchLastIndex(start, mid - 1, key);
			}
			else
				return binarySearchLastIndex(mid+1, end, key);
		}
		else
			return -1;
	}
	
	/**
	 * Binary search to find the insertion point. It will decide where to 
	 * place an object based on the priority of the object and whether or
	 * not it exists.
	 * @param obj
	 * @param start
	 * @param end
	 * @return
	 */
	private int findInsertionPoint(E obj, int start, int end) {
		if(end < start)
			return start;
		int mid = (start + end) / 2;
		if( ((Comparable<E>)obj).compareTo(storage[mid]) >= 0)
			return findInsertionPoint(obj, start, mid-1);
		return findInsertionPoint(obj, mid+1, end);
	}
}
