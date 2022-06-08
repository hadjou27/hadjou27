package Dijkstra;

import java.util.LinkedList;
import java.util.List;

import graph.Edge;
import graph.Entry;
import graph.Vertex;
import graph.WeightedDirectedGraph;

public class DijkstraAlgorithm
{
	/** Infinite distance */
    private static final int INFINITE = Integer.MAX_VALUE;
    
    private WeightedDirectedGraph wdg;
    
    private Vertex source;
    
    /** The key in the queue is the distance to source node */
    private PriorityQueue pq;
    
    public DijkstraAlgorithm(WeightedDirectedGraph wdg, Vertex source) {
    	this.wdg = wdg;
		this.source = source;
		
		pq = new PriorityQueue();
	}
	
    /**
     * Init the computation of dijkstra algorithm:
     *  - Set the distance to the source node to INFINITE
     *  - Remove the predecessor
     *  - Set the source node distance to 0 and
     *    the source node's predecessor to itself.
     */
    private void init()
    {
        for(Vertex v : wdg.vertices())
        {
            v.setPriorityQueueEntry(pq.insert(INFINITE, v));
            v.setPredecessor(null);
        }

        pq.replaceKey(source.getPriorityQueueEntry(), 0);
        source.setPredecessor(source);
    }

    /**
     * Relax the edge between u and v in the graph g and set u as the
     * predecessor of v if the new distance of v (distance(u)+cost(edge(u,v)))
     * is smaller than the current one.
     */
    private void relax(Vertex u, Vertex v, int w)
    {
        if(isLinkDown(u, v))
            // link is down ... we can't reach v.
            return ;

        if(u.getDist()+w < v.getPriorityQueueEntry().getKey())
        {
        	int currentDist = u.getDist()+w;
            pq.replaceKey(v.getPriorityQueueEntry(), currentDist);
            v.setPredecessor(u);
        }
    }

    /**
     * Check whether the link is down
     */
    private boolean isLinkDown(Vertex source, Vertex destination)
    {
        Edge e[] = wdg.getEdges(source, destination);

        if(e[0] == null && e[1] == null)
            return true;
        else if(e[0] != null && e[1] == null && e[0].getCost() == 0)
            return true;
        else if(e[0] == null && e[1] != null && e[1].getCost() == 0)
            return true;
        else if(e[0] != null & e[1] != null && (e[0].getCost() == 0 || e[1].getCost() == 0))
            return true;

        return false;
    }

    /**
     * Compute the dijkstra algorithm on g with source as the start node.
     *
     * @pre  - g is a graph with at least 1 node
     *       - source is a valid vertex in the graph g
     * @post - If a node n is reachable from source, its distance is the
     *         distance (<INFINITE) of the shortest path between source
     *         and the node.
     *         If not, the distance is INFINITE.
     *       - If node n is unreachable then its predecessor is null.
     *         If node n is reachable, the predecessor is the node used
     *         to reach n.
     *
     * Note: If n is reachable, its predecessor is not null and so is its
     *       predecessor's predecessor and ... until the source node is
     *       reached. In fact, there is one linked list for each reachable
     *       node in the graph. With the help of these lists, one can easily
     *       extract the path between one node and the source.
     */
    public void runAlgorithm ()
    {
        Vertex u, v;
        int u_dist, e_cost;
        Entry u_entry, v_entry;

        // init dijkstra algorithm
        init();

        while(!pq.isEmpty())
        {
            // Get the node with the shortest distance to the cloud
            u_entry = pq.removeMin();
            u = (Vertex) u_entry.getValue();
            u_dist = u_entry.getKey();

            u.setDist(u_dist);
            u.setPriorityQueueEntry(null);

            // if the node is not reachable yet, skip it.
            if(u_dist == INFINITE)
                continue;
            
            // for each vertex v in Adjacent(u)
            //   perform relax(u, v)
            for(Edge e : wdg.getOutgoingEdges(u))
            {
                v = wdg.opposite(e, u);
                v_entry = v.getPriorityQueueEntry();

                // if v_entry != null, the node v is not yet in the cloud
                if(v_entry != null)
                {
                    e_cost = e.getCost();
                    relax(u, v, e_cost);
                }
            }
        }
    }
    
    public List<Vertex> getPathToSource(Vertex v) {
    	LinkedList<Vertex> list = new LinkedList<Vertex>();
    	Vertex u = v;
    	
		while (u != null && !u.equals(source)) {
			list.addFirst(u);
			u = u.getPredecessor();
		}
    	
    	return list;
    }
}
