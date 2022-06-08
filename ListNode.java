package graph;
/**
 * Node for the list
 */
public class ListNode<T>
{
	// object stored in the node
    private T element;
    
    // previous element in the list, null if none
    private ListNode<T> prev;
    
    // next element in the list, null if none
    private ListNode<T> next;

    /**
     * Constructor
     */
    public ListNode(T e, ListNode<T> p, ListNode<T> n)
    {
        element = e;
        prev = p;
        next = n;
    }

    /**
     * Return the element stored in the listnode
     */
    public T getElement()
    {
        return element;
    }

    /**
     * Return the previous node in the list
     */
    public ListNode<T> getPrevious()
    {
        return prev;
    }

    /**
     * Replace the previous node of this listnode
     * @param ln
     */
    public void setPrevious(ListNode<T> ln)
    {
        prev = ln;
    }

    /**
     * Return the next node of this node
     */
    public ListNode<T> getNext()
    {
        return next;
    }

    /**
     * Replace the next node of this node
     */
    public void setNext(ListNode<T> ln)
    {
        next = ln;
    }
}
