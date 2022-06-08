package Dijkstra;

import java.util.Arrays;
import java.util.NoSuchElementException;

import graph.Entry;

/**
 * Implement a priority queue whose key can be changed at any time
 * The implementation of the queue is done with a heap using an array
 * 
 * java.util.PriorityQueue lacks replaceKey equivalent. It could be possible
 * to emulate it via a offer+poll but that would mean reordering the
 * PriorityQueue twice while this one does it only once.
 */
public class PriorityQueue
{
    /** default capacity */
    private final static int DEFAULT_CAPACITY = 32;
    
    /** Queue is considering big when over this capacity */
    private final static int BIG_CAPACITY = 250;

    /** container for the elements of the queue */
    private Entry[] queue;

    /**
     * Current size of the PriorityQueue
     * Invariant:
     *  size-1 = Last element index in the array iff the queue is not empty 
     */
    private int size;

    public PriorityQueue()
    {
        this(DEFAULT_CAPACITY);
    }

    public PriorityQueue(int n)
    {
    	int capacity = n;
    	if(capacity < DEFAULT_CAPACITY)
    		capacity = DEFAULT_CAPACITY;
        queue = new Entry[capacity];
        size = 0;
    }

    /**
     * @pre  -
     * @post return the current size of the queue
     */
    public int size()
    {
        return size;
    }

    /**
     * @pre  -
     * @post return true if the queue is empty,
     *              false otherwise
     */
    public boolean isEmpty()
    {
        return (size() == 0) ;
    }

    /**
     * @pre  -
     * @post If the queue is not empty, return the entry with
     *       the smallest key in the queue,
     *       If it is, throw NoSuchElementExepction
     */
    public Entry min() throws NoSuchElementException
	{
        if(isEmpty())
            throw new NoSuchElementException("Priority Queue is empty");

        return queue[0];
    }

    /**
     * @pre  -
     * @post If the queue is not empty, remove and return the entry with the
     *       smallest key in the queue,
     *       If it is, throw NoSuchElementException
     */
    public Entry removeMin() throws NoSuchElementException
	{
        Entry tmp = min();

        queue[0] = queue[size-1];
        queue[0].setPosition(0);
        size -= 1;
        bubbleDown(0);

        return tmp;
    }

    /**
     * @pre  -
     * @post A new entry e is inserted in the priority queue. The key of e is
     *       k and the value of e is v. e is returned.
     */
    public Entry insert(int k, Object v)
    {
        Entry e = new Entry(k, v);

        if(size == queue.length)
        {
            // Old array is too small ... create a new one:
        	//  - twice as big if capacity < BIG_CAPACITY
        	//  - 50% bigger if capacity >= BIG_CAPACITY 
        	int oldCapacity = queue.length;
        	int newCapacity = oldCapacity < BIG_CAPACITY ?
        						(oldCapacity + 1) * 2 : 
        						(oldCapacity / 2) / 3;
            queue = Arrays.copyOf(queue, newCapacity);
        }

        queue[size] = e;
        e.setPosition(size);
        size = size+1;

        bubbleUp(size-1);

        return e;
    }

    /**
     * @pre  e is a valid entry in this queue
     * @post The key of the entry e is changed to newkey
     */
    public void replaceKey(Entry e, int newKey)
    {
        int old = e.getKey();

        e.setKey(newKey);
        if(old < newKey)
            bubbleDown(e.getPosition());
        else
            bubbleUp(e.getPosition());
    }

    /**
     * Maintain the heap priority by moving the root of the heap
     * to its correction location in the heap
     */
    private void bubbleDown(int i)
    {
        int imin;
        Entry min=null;
        int left, right;

        while(true)
        {
            imin = i;
            min = queue[i];
            left = 2*i+1;
            right = 2*i+2;

            if(left < size && queue[left].getKey() < min.getKey())
            {
                imin = left;
                min = queue[left];
            }

            if(right < size && queue[right].getKey() < min.getKey())
            {
                imin = right;
                min = queue[right];
            }

            if(min == queue[i])
            	break;
            else {
                // swap element
                queue[imin] = queue[i];
                queue[i] = min;

                // update position
                queue[imin].setPosition(imin);
                queue[i].setPosition(i);
                i = imin;
            }
        }
    }

    /**
     * Maintain the heap priority by moving the last element of the
     * last level to its correct location in the heap
     */
    private void bubbleUp(int i)
    {
        int n = i;
        int up = (n-1)/2;

        while(up >= 0 && queue[up].getKey() > queue[n].getKey())
        {
            // swap element
            Entry tmp = queue[up];
            queue[up] = queue[n];
            queue[n] = tmp;

            // update position
            queue[n].setPosition(n);
            queue[up].setPosition(up);

            // update indexes
            n = up;
            up = (up-1)/2;
        }
    }
}
