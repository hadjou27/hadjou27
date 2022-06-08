package graph;
public class Edge
{
    /** Object stored in the edge */
    private Object element;

    /** Edge's cost */
    private int cost;

    /** Source vertex */
    private Vertex source;

    /** Destination vertex */
    private Vertex destination;

    /** Entry in the edge list of the graph */
    private ListNode<Edge> edgeListEntry;

    /** Entry in the incident edge list of the source */
    private ListNode<Edge> incidenceListEntrySource;

    /** Entry in the incident edge list of the destination */
    private ListNode<Edge> incidenceListEntryDestination;

    /**
     * Default Constructors
     */
    public Edge()
    {
        this(null, 0, null, null, null, null, null);
    }

    /**
     * Full constructor
     */
	public Edge(Object e, int c, Vertex s, Vertex d,
			 	ListNode<Edge> ele, ListNode<Edge> ileSource, ListNode<Edge> ileDest)
	{
        element = e;
        setCost(c);
        source = s;
        destination = d;
        edgeListEntry = ele;
        incidenceListEntrySource = ileSource;
        incidenceListEntryDestination = ileDest;
    }

    /**
     * Return the element stored in the edge
     */
    public Object getElement()
    {
        return element;
    }

    /**
     * Replace the element stored in the edge
     */
    public void setElement(Object e)
    {
        element = e;
    }

    /**
     * Return the cost of this edge
     */
    public int getCost()
    {
        return cost;
    }

    /**
     * @pre -
     * @post If c >= 0 then the cost of the link is updated
     *       else the change is discarded
     */
    public void setCost(int c)
    {
        if(c >= 0)
            cost = c;
    }

    /**
     * Return the source vertex for this edge
     * @return
     */
    public Vertex getSource()
    {
        return source;
    }

    /**
     * Replace the source vertex of this edge
     */
    public void setSource(Vertex v)
    {
        source = v;
    }

    /**
     * Return the destination vertex of this edge
     */
    public Vertex getDestination()
    {
        return destination;
    }

    /**
     * Replace the destination vertex of this edge
     */
    public void setDestination(Vertex v)
    {
        destination = v;
    }

    /**
     * Return the entry in the edge list of a vertex for this edge
     */
    public ListNode<Edge> getEdgeListEntry()
    {
        return edgeListEntry;
    }

    /**
     * Replace the entry in the edge list of a vertex for this edge
     */
    public void setEdgeListEntry(ListNode<Edge> ln)
    {
        edgeListEntry = ln;
    }

    /**
     * Return the entry of the incidence edge list of the source for this edge  
     */
    public ListNode<Edge> getIncidenceListEntrySource()
    {
        return incidenceListEntrySource;
    }

    /**
     * Replace the entry of the incidence edge list of the source for this edge  
     */
    public void setIncidenceListEntrySource(ListNode<Edge> ln)
    {
        incidenceListEntrySource = ln;
    }

    /**
     * Return the entry of the incidence edge list of the destination for this edge  
     */
    public ListNode<Edge> getIncidenceListEntryDestination()
    {
        return incidenceListEntryDestination;
    }

    /**
     * Replace the entry of the incidence edge list of the destination for this edge  
     */
    public void setIncidenceListEntryDestination(ListNode<Edge> ln)
    {
        incidenceListEntryDestination = ln;
    }

    /**
     * Return the string representing this edge
     */
    public String toString()
    {
        return "Edge(element:"+element+" source:"+source+
               " dest:"+destination+" cost:"+cost+")";
    }

    /**
     * Overriden default equals method
     */
    public boolean equals(Object o)
    {
        if(o == null || !(o instanceof Edge))
            return false;
        Edge e = (Edge)o;

        // router name are unique ... this should be enough to test equality
        return cost == e.getCost() &&
               source.equals(e.getSource()) &&
               destination.equals(e.getDestination());
    }
}
