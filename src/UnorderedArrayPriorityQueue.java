/**
 * Program #1
 * Unordered Array implementation of priority queue
 * CS310
 * 02/22/2018
 * @author jesuslopez cssc0736
 */
package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class UnorderedArrayPriorityQueue<E extends Comparable<E>> implements PriorityQueue<E>  {
	private E[] storage;
	private int currentSize;
	private int maxSize;
	
	public UnorderedArrayPriorityQueue() {
		this(DEFAULT_MAX_CAPACITY);
	}
	
	public UnorderedArrayPriorityQueue(int size) {
		maxSize = size;
		storage = (E[]) new Comparable[maxSize];
		currentSize = 0;
	}

	@Override
	/**
	 * Inserts a new object into the priority queue. Returns true if 
	 * the insertion is successful. If the PQ is full, the insertion 
	 * is aborted, and the method returns false.
	 */
	public boolean insert(E object) {
		if(isFull()) 
			return false;
		storage[currentSize++] = object;
		return true;
	}

	@Override
	/**
	 * Removes the object of highest priority that has been in the 
	 * PQ the longest, and returns it. Returns null if the PQ is empty.
	 */
	public E remove() {
		if(isEmpty())
			return null;
		else {
			E highestPriority = storage[0]; // Starting search for highest priority at index 0.
			int index = 0;
			for (int i = 1; i < currentSize; i++) { // Finds the highest priority in the array.Compares every item in the array.
				if ( ((Comparable <E>) storage[i]).compareTo(highestPriority) < 0) {
					highestPriority = storage[i];
					index = i;
				}
			}
			int shiftNum = (currentSize - 1) - index;
			for (int i = 0; i < shiftNum; i++) {
				storage[index] = storage[index + 1];
				index++;
			}
			currentSize--;
			return highestPriority;
		}
	}
	

	@Override
	/**
	 *  Deletes all instances of the parameter obj from the PQ if found, and
	 *  returns true. Returns false if no match to the parameter obj is found.
	 */
	public boolean delete(E obj) {
		int shift = 0;
		for (int i = 0; i < currentSize; i++) {
			//If the object in the array at index i is equal to obj.
			if(((Comparable<E>) storage[i]).compareTo(obj) == 0) {
				int shiftNum = (currentSize - 1) - i;
				int index = i;
				for (int j = 0; j <= shiftNum; j++) {
					storage[index] = storage[index++];
					index++;
				}
				shift++; //Shift for the deletion.
				currentSize--; //Decrement the size of the array.
			}
			return true;
		}
		return false;
	}

	@Override
	/**
	 * Returns the object of highest priority that has been in the 
	 * PQ the longest, but does NOT remove it.
	 * Returns null if the PQ is empty.
	 */
	public E peek() {
		if (isEmpty())
			return null;
		else {
			E highestPriority = storage[0]; // Starting search for highest priority at index 0.
			for (int i = 1; i < currentSize; i++) { // Finds the highest priority in the array. 
								//Compares every item in the array.
				if ( ((Comparable <E>) storage[i]).compareTo(highestPriority) < 0) { 
					highestPriority = storage[i];
				}
			}
			return highestPriority;
		}
	}

	@Override
	/**
	 * Returns true if the priority queue contains the specified element 
	 * false otherwise.
	 */
	public boolean contains(E obj) {
		for(int i=0; i<currentSize; i++)
			//If the object in the array at index i is equal to obj
			if(((Comparable<E>) storage[i]).compareTo(obj) == 0)  
				return true;
		return false;
	}

	@Override
	/**
	 *  Returns the number of objects currently in the PQ.
	 */
	public int size() {
		return currentSize;
	}

	@Override
    /**
     *  Returns the PQ to an empty state.
     */
	public void clear() {
		currentSize = 0;
	}

	@Override
	/**
	 *  Returns true if the PQ is empty, otherwise false
	 */
	public boolean isEmpty() {
		return currentSize == 0;
	}

	@Override
	/**
	 *  Returns true if the PQ is full, otherwise false. List based
	 *  implementations should always return false.
	 */
	public boolean isFull() {
		return currentSize == maxSize;
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

}
