package graph;
/**
 * The graph is used to represent the topology of a network.
 * It is implemented with 2 adjency list structures
 */
public class WeightedDirectedGraph
{
    // list of all the vertex in this graph
    private List<Vertex> vertices;

    // list of all edges in this graph
    private List<Edge> edges;
    
    public WeightedDirectedGraph()
    {
        vertices = new List<Vertex>();
        edges = new List<Edge>();
    }

    /**
     * Return an iterator of all the vertices in the graph
     */
    public List<Vertex> vertices()
    {
        return vertices;
    }

    /**
     * Return an iterator of all the edges in the graph
     */
    public List<Edge> edges()
    {
        return edges;
    }

    /**
     * Return an iterator of all the incoming edges of 
     * the vertex v in this graph
     */
    public List<Edge> getIncomingEdges(Vertex v)
    {
        return v.getIncomingEdgeList();
    }

    /**
     * Return an iterator of all the outgoing edges of
     * the vertex v in this graph
     */
    public List<Edge> getOutgoingEdges(Vertex v)
    {
        return v.getOutgoingEdgeList();
    }
    
    /**
     * Return the number of incoming edge to vertex v
     */
    public int getIncomingDegree(Vertex v)
    {
    	return v.getIncomingEdgeList().size();
    }
	
	/**
	 * Return the number of outgoing edge to vertex v
	 */
    public int getOutgoingDegree(Vertex v)
	{
		return v.getOutgoingEdgeList().size();
	}

    /**
     * Return the end vertex of edge e which is different from v
     */
    public Vertex opposite(Edge e, Vertex v)
    {
        if(e.getSource().equals(v))
            return e.getDestination();

        return null;
    }

    /**
     * Return the end vertices of the edge e
     *
     * Vertex[0] = source
     * Vertex[1] = destination
     */
    public Vertex[] endVertices(Edge e)
    {
        return ( new Vertex[] {e.getSource(), e.getDestination()} );
    }

    /**
     * Return the edge between the vertex source and the vertex
     * dest. Edge[0] is the edge going from source to dest and 
     * Edge[1] is the edge going from dest to source.
     *
     * Note: one or both edge in the returned array can be null
     *       if those edges dont exist in the directed graph
     */
    public Edge[] getEdges(Vertex source, Vertex dest)
    {
        Edge e[] = new Edge[2];
        Vertex v;

        e[0] = null;
        e[1] = null;

        // try to find the outgoing edge
        for(Edge f : getOutgoingEdges(source))
        {
            v = opposite(f, source);
            if(v != null && v.equals(dest))
            {
                e[0] = f;
                break;
            }
        }

        // try to find the incoming edge
        for(Edge f : getOutgoingEdges(dest))
        {
            v = opposite(f, dest);
            if(v != null && v.equals(source))
            {
                e[1] = f;
                break;
            }
        }

        return e;
    }

    /**
     * Insert a new vertex in this graph storing the object o
     */
    public Vertex insertVertex(Object o)
    {
        Vertex v = new Vertex(o);
        v.setVertexListEntry(vertices.add(v));
        
        return v;
    }

    /**
     * Insert a new directed edge in this graph going from source to dest,
     * storing the object o and with a cost equal to 0
     */
    public Edge insertDirectedEdge(Vertex source, Vertex dest, Object o)
    {
        return insertDirectedEdge(source, dest, o, 0);
    }

    /**
     * Insert a directed edge in this graph going from source to dest,
     * storing the obkect o and with a cost equal to cost
     */
    public Edge insertDirectedEdge(Vertex source, Vertex dest, Object o, int cost)
    {
        Edge e = new Edge(o, cost, source, dest, null, null, null);

        e.setEdgeListEntry(edges.add(e));
        e.setIncidenceListEntrySource(source.getOutgoingEdgeList().add(e));
        e.setIncidenceListEntryDestination(dest.getIncomingEdgeList().add(e));

        for(Edge f : dest.getOutgoingEdgeList())
        {
            if(f.getDestination().equals(source))
            {
                // update the old link
                f.setDestination(source);
                break;
            }
        }
        
        return e;
    }

    /**
     * Remove the vertex v in this graph and return the object it stored
     */
    public Object removeVertex(Vertex v)
    {
        Object old = v.getElement();
        
        for(Edge e : getOutgoingEdges(v))
        	removeEdge(e);
        vertices.remove(v.getVertexListEntry());
        
        return old;
    }

    /**
     * Remove the edge e in this graph and return the object it stored
     */
    public Object removeEdge(Edge e)
    {
        Object old = e.getElement();
        e.getSource().getOutgoingEdgeList().remove(e.getIncidenceListEntrySource());
        e.getDestination().getIncomingEdgeList().remove(e.getIncidenceListEntryDestination());
        edges.remove(e.getEdgeListEntry());
        
        return old;
    }

    /**
     * Return the number of vertexin the graph
     */
    public int getNumberOfVertices()
    {
        return vertices.size();
    }
}
