package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;

/**
 * An AdjacencyMapGraph is an implementation of the {@link Graph} abstract data
 * type. AdjacencyMapGraph maintains a list of vertices in the graph and a list
 * of edges in the graph. In addition, AdjacencyMapGraph vertices each maintain
 * maps of incoming and outgoing edges to improve efficiency.
 * 
 * The AdjacencyMapGraph class is based on the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 * @author Justin Cristinziano
 *
 * @param <V> the type of data in the vertices in the graph
 * @param <E> the type of data in the edges in the graph
 */
public class AdjacencyMapGraph<V, E> extends EdgeListGraph<V, E> {
	
	/** List of all vertex in graph */
	private PositionalList<Vertex<V>> vertices = new PositionalLinkedList<Vertex<V>>();
	
	/** List of all edges */
	private PositionalList<Edge<E>> edges = new PositionalLinkedList<Edge<E>>();
	
    /**
     * Represents a vertex in an AdjacencyMapGraph
     * 
     * @author Dr. King
     *
     */
    private class AMVertex extends GraphVertex {

        
    	/** This the map of outgoing edges (opposite vertex, edge to reach opposite vertex) */
        private Map<Vertex<V>, Edge<E>> outgoing;

    	/** This the map of incoming edges (opposite vertex, edge to reach opposite vertex) */
        private Map<Vertex<V>, Edge<E>> incoming;

        /**
         * Creates a new adjacency map vertex.
         * 
         * @param data       the data to store in the vertex
         * @param isDirected if true, the vertex belongs to a directed graph; if false,
         *                   the vertex belongs to an undirected graph
         */
        public AMVertex(V data, boolean isDirected) {
            super(data);
            outgoing = new LinearProbingHashMap<Vertex<V>, Edge<E>>();
            if (isDirected) {
                incoming = new LinearProbingHashMap<>();
            } else {
                incoming = outgoing;
            }
        }

        /**
         * Returns a map of incomingEdges to the vertex. For an undirected graph,
         * returns the same as getOutgoing()
         * 
         * @return a map of incoming edges to the vertex
         */
        public Map<Vertex<V>, Edge<E>> getIncoming() {
            return incoming;
        }

        /**
         * Returns a map of outgoingEdges from the vertex. For an undirected graph,
         * returns the same as getIncoming()
         * 
         * @return a map of outgoing edges from the vertex
         */
        public Map<Vertex<V>, Edge<E>> getOutgoing() {
            return outgoing;
        }
    }

    /**
     * Creates a new undirected adjacency map graph
     */
    public AdjacencyMapGraph() {
        super(false);
    }

    /**
     * Creates a new adjacency map graph
     * 
     * @param directed if true, the graph is directed; if false, the graph is
     *                 undirected
     */
    public AdjacencyMapGraph(boolean directed) {
        super(directed);
    }

    protected Vertex<V> createVertex(V vertexData) {
        return new AMVertex(vertexData, isDirected());
    }

    @Override
    public Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2) {
        AMVertex vVertex1 = validate(vertex1);
        AMVertex vVertex2 = validate(vertex2);
        
        return vVertex1.getOutgoing().get(vVertex2);
    }

    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex) {
        return validate(vertex).getIncoming().values();
    }

    @Override
    public int inDegree(Vertex<V> vertex) {
    	return validate(vertex).getIncoming().size();
    }

    @Override
    public Edge<E> insertEdge(Vertex<V> v1, Vertex<V> v2, E edgeData) {
    	if(getEdge(v1, v2) == null) {
    		Edge<E> e = createEdge(edgeData, v1, v2);
    		GraphEdge validatedEdge = super.validate(super.insertEdge(v1, v2, edgeData));
    		
    		AMVertex validatedOrigin = validate(v1);
    		AMVertex validatedDest = validate(v2);
    		validatedOrigin.getOutgoing().put(validatedDest, validatedEdge);
    		validatedDest.getIncoming().put(validatedOrigin, validatedEdge);
    		return validatedEdge;
    	} else {
    		throw new IllegalArgumentException("Edge from v1 to v2 exists");
    	}
    }

    @Override
    public int outDegree(Vertex<V> vertex) {
       return validate(vertex).getOutgoing().size();
    }

    @Override
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
        return validate(vertex).getOutgoing().values();
    }

    @Override
    public void removeEdge(Edge<E> edge) {
        GraphEdge vEdge = validate(edge);
        Vertex<V>[] endpoints = vEdge.getEndpoints();
        AMVertex origin = validate(endpoints[0]);
        AMVertex dest = validate(endpoints[1]);
        
        origin.getOutgoing().remove(dest);
        dest.getIncoming().remove(origin);
        super.removeEdge(vEdge);
    }
    
    /**
     * Safely casts a Vertex to an adjacency map vertex
     * 
     * @param v is the vertex to validate
     * @return an adjacency map vertex representation of the given Vertex
     * @throws IllegalArgumentException if the vertex is not a valid adjacency map
     *                                  vertex
     */
    private AMVertex validate(Vertex<V> v) {
        if (!(v instanceof AdjacencyMapGraph.AMVertex)) {
            throw new IllegalArgumentException("Vertex is not a valid adjacency map vertex.");
        }
        return (AMVertex) v;
    }
    
}