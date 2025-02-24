package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * An AdjacencyListGraph is an implementation of the {@link Graph} abstract data
 * type. AdjacencyListGraph maintains a list of vertices in the graph and a list
 * of edges in the graph. In addition, AdjacencyListGraph vertices each maintain
 * lists of incoming and outgoing edges to improve efficiency.
 * 
 * The AdjacencyListGraph class is based on the textbook:
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
public class AdjacencyListGraph<V, E> extends EdgeListGraph<V, E> {

	/** List of all edges in graph */
	private PositionalList<Edge<E>> edgeList;

	/**
	 * Represents an edge in an AdjacencyListGraph
	 * 
	 * @author Dr. King
	 *
	 */
	private class ALEdge extends GraphEdge {

		/** The position of the edge in a vertex's outgoing edge list */
		private Position<Edge<E>> outgoingListPosition;

		/** The position of the edge in a vertex's incoming edge list */
		private Position<Edge<E>> incomingListPosition;

		/**
		 * Creates a new adjacency list edge
		 * 
		 * @param element the data to store in the edge
		 * @param v1      an endpoint vertex
		 * @param v2      an endpoint vertex
		 */
		public ALEdge(E element, Vertex<V> v1, Vertex<V> v2) {
			super(element, v1, v2);
		}

		/**
		 * Returns the position of the edge in the associated vertex's incoming edge
		 * list
		 * 
		 * @return the position of the edge in the associated vertex's incoming edge
		 *         list
		 */
		public Position<Edge<E>> getIncomingListPosition() {
			return incomingListPosition;
		}

		/**
		 * Returns the position of the edge in the associated vertex's outgoing edge
		 * list
		 * 
		 * @return the position of the edge in the associated vertex's outgoing edge
		 *         list
		 */
		public Position<Edge<E>> getOutgoingListPosition() {
			return outgoingListPosition;
		}

		/**
		 * Sets the edge's position in the associated vertex's incoming edge list
		 * 
		 * @param incomingListPosition the position of the edge in the associated
		 *                             vertex's incoming edge list
		 */
		public void setIncomingListPosition(Position<Edge<E>> incomingListPosition) {
			this.incomingListPosition = incomingListPosition;
		}

		/**
		 * Sets the edge's position in the associated vertex's outgoing edge list
		 * 
		 * @param outgoingListPosition the position of the edge in the associated
		 *                             vertex's outgoing edge list
		 */
		public void setOutgoingListPosition(Position<Edge<E>> outgoingListPosition) {
			this.outgoingListPosition = outgoingListPosition;
		}
	}

	/**
	 * Represents a vertex in an AdjacencyListGraph
	 * 
	 * @author Dr. King
	 *
	 */
	private class ALVertex extends GraphVertex {

		/** A positional list of outgoing edges */
		private PositionalList<Edge<E>> outgoing;

		/** A positional list of incoming edges */
		private PositionalList<Edge<E>> incoming;

		/**
		 * Creates a new adjacency list vertex.
		 * 
		 * @param data       the data to store in the vertex
		 * @param isDirected if true, the vertex belongs to a directed graph; if false,
		 *                   the vertex belongs to an undirected graph
		 */
		public ALVertex(V data, boolean isDirected) {
			super(data);
			outgoing = new PositionalLinkedList<Edge<E>>();
			if (isDirected) {
				incoming = new PositionalLinkedList<Edge<E>>();
			} else {
				incoming = outgoing;
			}
		}

		/**
		 * Returns a positional list of incomingEdges to the vertex. For an undirected
		 * graph, returns the same as getOutgoing()
		 * 
		 * @return a positional list of incoming edges to the vertex
		 */
		public PositionalList<Edge<E>> getIncoming() {
			return incoming;
		}

		/**
		 * Returns a positional list of outgoingEdges from the vertex. For an undirected
		 * graph, returns the same as getIncoming()
		 * 
		 * @return a positional list of outgoing edges from the vertex
		 */
		public PositionalList<Edge<E>> getOutgoing() {
			return outgoing;
		}
	}

	/**
	 * Creates a new undirected adjacency list graph
	 */
	public AdjacencyListGraph() {
		this(false);
		edgeList = new PositionalLinkedList<Edge<E>>();
	}

	/**
	 * Creates a new adjacency list graph
	 * 
	 * @param directed if true, the graph is directed; if false, the graph is
	 *                 undirected
	 */
	public AdjacencyListGraph(boolean directed) {
		super(directed);
		edgeList = new PositionalLinkedList<Edge<E>>();
	}

	/**
	 * Creates a new ALEdge object with the given endpoints and data.
	 * 
	 * @param data    Data for edge
	 * @param vertex1 Endpoint 1 vertex
	 * @param vertex2 Endpoint 2 vertex
	 * @return new Edge
	 */
	protected Edge<E> createEdge(E data, Vertex<V> vertex1, Vertex<V> vertex2) {
		return new ALEdge(data, vertex1, vertex2);
	}

	/**
	 * Creates a new ALVertex object with the given data.
	 * 
	 * @param vertexData for Vertex
	 * @return new Vertex
	 */
	protected Vertex<V> createVertex(V vertexData) {
		return new ALVertex(vertexData, isDirected());
	}

	/**
	 * Gets an edge given it's two endpoints and returns it.
	 * 
	 * @param vertex1 Endpoint1
	 * @param vertex2 Endpoint2
	 * @return Edge
	 */
	@Override
	public Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2) {
		ALVertex v1 = validate(vertex1);
		ALVertex v2 = validate(vertex2);
		for (Edge<E> edge : v1.getOutgoing()) {
			ALEdge e = validate(edge);
			Vertex<V>[] ends = e.getEndpoints();
			if (!isDirected() && ends[1] == v1 && ends[0] == v2) {
				return e;
			}
			if (ends[0] == v1 && ends[1] == v2) {
				return e;
			}
		}
		return null;
	}

	/**
	 * Creates and returns a list of incoming edges for a certain vertex.
	 * 
	 * @param vertex Vertex to get incoming edges list for
	 * @return List of edge objects
	 */
	@Override
	public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex) {
		return validate(vertex).getIncoming();
	}

	/**
	 * Returns size of incoming edge list for a given vertex
	 * 
	 * @param vertex Vertex to get list for
	 * @return size as integer
	 */
	@Override
	public int inDegree(Vertex<V> vertex) {
		return validate(vertex).getIncoming().size();
	}

	/**
	 * Creates a new edge with the data and the two vertices
	 * 
	 * @param vertex1  Origin vertex
	 * @param vertex2  Destination vertex
	 * @param edgeData Data
	 * @return Edge that is created
	 */
	@Override
	public Edge<E> insertEdge(Vertex<V> vertex1, Vertex<V> vertex2, E edgeData) {
		if (getEdge(vertex1, vertex2) == null) {
			ALVertex vVertex1 = validate(vertex1);
			ALVertex vVertex2 = validate(vertex2);
			ALEdge newEdge = new ALEdge(edgeData, vVertex1, vVertex2);
			Position<Edge<E>> pos = edgeList.addLast(newEdge);
			newEdge.setPosition(pos);
			newEdge.setOutgoingListPosition(vVertex1.getOutgoing().addLast(newEdge));
	        newEdge.setIncomingListPosition(vVertex2.getIncoming().addLast(newEdge));
			return newEdge;
		} else {
			throw new IllegalArgumentException("Edge from v1 to v2 already exists.");
		}
	}

	/**
	 * Gives the number of out edges
	 * 
	 * @param vertex Vertex to get outDegree of
	 * @return number of outgoing edges
	 */
	@Override
	public int outDegree(Vertex<V> vertex) {
		return validate(vertex).getOutgoing().size();
	}

	/**
	 * Returns an iterable collection of edges
	 * 
	 * @return collection of edges
	 */
	@Override
	public Iterable<Edge<E>> edges() {
		return edgeList;
	}
	
	/**
	 * Returns the number of edges in the graph
	 * 
	 * @return size of edge list as integer
	 */
	@Override
	public int numEdges() {
		return edgeList.size();
	}

	/**
	 * Returns an iterable collection of outgoing edges.
	 * 
	 * @param vertex Vertex to get outgoing edge list of.
	 * @return Iterable collection
	 */
	@Override
	public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
		return validate(vertex).getOutgoing();
	}

	/**
	 * Removes the edge that is passed in from the AdjacencyMatrixGraph
	 * 
	 * @param edge Edge to remove
	 */
	@Override
	public void removeEdge(Edge<E> edge) {
		ALEdge vEdge = validate(edge);
		Vertex<V>[] endpoints = vEdge.getEndpoints();
		ALVertex origin = validate(endpoints[0]);
		ALVertex dest = validate(endpoints[1]);
		
		
		origin.getOutgoing().remove(vEdge .getOutgoingListPosition());
		dest.getIncoming().remove(vEdge .getIncomingListPosition());
		
		edgeList.remove(vEdge .getPosition());
	}

	/**
	 * Safely casts an Edge to an adjacency list edge
	 * 
	 * @param e Edge
	 * @return an adjacency list edge representation of the given Edge
	 * @throws IllegalArgumentException if the edge is not a valid adjacency list
	 *                                  edge
	 */
	protected ALEdge validate(Edge<E> e) {
		if (!(e instanceof AdjacencyListGraph.ALEdge)) {
			throw new IllegalArgumentException("Edge is not a valid adjacency list edge.");
		}
		return (ALEdge) e;
	}

	/**
	 * Safely casts a Vertex to an adjacency list vertex
	 * 
	 * @param v Vertex
	 * @return an adjacency list vertex representation of the given Vertex
	 * @throws IllegalArgumentException if the vertex is not a valid adjacency list
	 *                                  vertex
	 */
	protected ALVertex validate(Vertex<V> v) {
		if (!(v instanceof AdjacencyListGraph.ALVertex)) {
			throw new IllegalArgumentException("Vertex is not a valid adjacency list vertex.");
		}
		return (ALVertex) v;
	}
}