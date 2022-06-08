package graph;
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * Simple generic list except that it removes node in O(1)
 */
public class List<T> implements Iterable<T>
{
    private ListNode<T> head;
    private ListNode<T> tail;
    private int size;

    /**
     * Constructor
     */
    public List()
    {
        head = null;
        tail = null;
        size = 0;
    }
    
    /**
     * Useful to create a copy of the list
     */
    public List(List<T> l)
    {
    	this();
    	
    	for(T element : l)
    		add(element);    	
    }

    /**
     * Add the object e at then end of the list and
     * return the list node storing the object
     * Ajoute l'el�ment e � la liste en retourne l'entr�e de
     * la liste qui le contient
     */
    public synchronized ListNode<T> add(T e)
    {
        ListNode<T> ln;

        if(head == null) 
        {
            ln = new ListNode<T>(e, null, null);
            head = ln;
            tail = head;
        }
        else
        {
            ListNode<T> oldtail = tail;
            ln = new ListNode<T>(e, oldtail, null);
            tail = ln;
            oldtail.setNext(ln);
        }
        size++;
        return ln;
    }
    
    public ListNode<T> addFirst(T e)
    {
        ListNode<T> ln = new ListNode<T>(e, null, head);
        
        if(head != null)
            head.setPrevious(ln);
        
        head = ln;
        size++;
        
        return ln;
    }

    /**
     * Remove the list node ln from the list O(1)
     *
     * @pre ln is a valid list node in this list
     * @post ln is remove from this list
     *       size is decremented by 1
     */
    public synchronized void remove(ListNode<T> ln)
    {
        removeNode(ln);
        size--;
    }

    /**
     * Remove the object e from this list (O(n): sequential search of the
     * element).
     *
     * @pre -
     * @post If e is an element stored in a listnode ln of this list then ln is
     *       removed from the list and size is decrement by 1 (the first element
     *       in this list equal to e is removed if multiple instances of e are
     *       in the list),
     *       else throw NoSuchElementException
     */
    public synchronized void remove(T e)
    {
        ListNode<T> ln = head;

        while(ln != null)
        {
            if(ln.getElement().equals(e))
            {
                removeNode(ln);
                break;
            }
            ln = ln.getNext();
        }

        if(ln == null)
            throw new NoSuchElementException();
        size--;
    }

    /**
     * Return the size of this list
     */
    public synchronized int size()
    {
        return size;
    }
    
    /**
     * Check if the list is empty
     */
    public synchronized boolean isEmpty()
    {
    	return size() == 0;
    }

    /**
     * Return an iterator for this list
     */
    public synchronized Iterator<T> iterator()
    {
        return new ListIterator<T>(head);
    }

    /**
     * Remove the list node ln from the list O(1)
     */
    private synchronized void removeNode(ListNode<T> ln)
    {
        ListNode<T> prev = ln.getPrevious();
        ListNode<T> next = ln.getNext();

        if(prev != null)
            prev.setNext(next);
        else
            head = next;

        if(next != null)
            next.setPrevious(prev);
        else
            tail = prev;
    }
}
