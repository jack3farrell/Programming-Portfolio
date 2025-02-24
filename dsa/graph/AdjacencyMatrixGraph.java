package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * An AdjacencyMatrixGraph is an implementation of the {@link Graph} abstract
 * data type. AdjacencyMatrixGraph maintains a list of vertices in the graph and
 * a list of edges in the graph. In addition, AdjacencyMatrixGraph maintains a
 * 2-dimensional array to store edges based on the endpoints of the edges
 * 
 * The AdjacencyMatrixGraph class is based on the textbook:
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
public class AdjacencyMatrixGraph<V, E> extends EdgeListGraph<V, E> {
	
	/** The vertex list for this graph*/
	private PositionalList<Vertex<V>> vertexList;

	/** The edge list for this graph */
	private PositionalList<Edge<E>> edgeList;

    /**
     * Represents a vertex in an AdjacencyMapGraph
     * 
     * @author Dr. King
     *
     */
    private class MatrixVertex extends GraphVertex {
    	

        /** The integer index of the vertex **/
        private int index;

        /**
         * Creates a new adjacency matrix vertex.
         * 
         * @param data       the data to store in the vertex
         */
        public MatrixVertex(V data) {
            super(data);
            index = getVertexIndex();
            System.out.println("Vertex " + data + " has index " + index); 
        }

        /**
         * Returns the row/column index of the vertex in the matrix
         * 
         * @return the index of the vertex in the matrix
         */
        public int getIndex() {
            return index;
        }
    }
    
    /** This is the matrix that contains all of the graph data */
    private GraphEdge[][] matrix;

    /** This is the last vertex's index */
    private int vertexIndexer;

    /**
     * Creates a new undirected adjacency matrix graph
     */
    public AdjacencyMatrixGraph() {
        this(false);
        vertexIndexer = 0;
		edgeList = new PositionalLinkedList<Edge<E>>();
    }

    /**
     * Creates a new adjacency matrix graph
     * 
     * @param directed if true, the graph is directed; if false, the graph is
     *                 undirected
     */
    @SuppressWarnings("unchecked")
    public AdjacencyMatrixGraph(boolean directed) {
        super(directed);
        matrix = (GraphEdge[][]) (new AbstractGraph.GraphEdge[0][0]);
		edgeList = new PositionalLinkedList<Edge<E>>();
    }

    protected Vertex<V> createVertex(V vertexData) {
        return new MatrixVertex(vertexData);
    }

    @Override
    public Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2) {
        MatrixVertex vVertex1 = validate(vertex1);
        MatrixVertex vVertex2 = validate(vertex2);
        return matrix[vVertex1.getIndex()][vVertex2.getIndex()];
    }

    /** This returns the vertex's index
     * 
     * @return the vertex's index
     */
    private int getVertexIndex() {
        vertexIndexer++;
        return vertexIndexer - 1;
    }

    @SuppressWarnings("unchecked")
    private void growArray() {
        GraphEdge[][] temp = new AbstractGraph.GraphEdge[matrix.length + 1][matrix.length + 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                temp[i][j] = matrix[i][j];
            }
        }
        matrix = temp;
    }

    private List<Edge<E>> incomingEdgeList(Vertex<V> vertex) {
        MatrixVertex v = validate(vertex);
        List<Edge<E>> list = new SinglyLinkedList<Edge<E>>();
        for (int i = 0; i < matrix.length; i++) {
            GraphEdge e = matrix[i][v.getIndex()];
            if (e != null) {
                list.addLast(e);
            }
        }
        return list;
    }

    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex) {
        return incomingEdgeList(vertex);
    }

    @Override
    public int inDegree(Vertex<V> vertex) {
        return incomingEdgeList(vertex).size();
    }

    @Override
    public Edge<E> insertEdge(Vertex<V> vertex1, Vertex<V> vertex2, E edgeData) throws IllegalArgumentException {
    	MatrixVertex vVertex1 = validate(vertex1);
        MatrixVertex vVertex2 = validate(vertex2);
        	GraphEdge newEdge = new GraphEdge(edgeData, vVertex1, vVertex2);
    		Position<Edge<E>> pos = edgeList.addLast(newEdge);
    		newEdge.setPosition(pos);
    		matrix[vVertex1.getIndex()][vVertex2.getIndex()] = newEdge;
    		
    		// If it's not directed, add an edge in flipped index spot
    		if (!isDirected()) {
    			matrix[vVertex2.getIndex()][vVertex1.getIndex()] = newEdge;
    		}
    		return newEdge;
    }

    @Override
    public Vertex<V> insertVertex(V vertexData) {
        growArray();
        return super.insertVertex(vertexData);
    }

    @Override
    public int outDegree(Vertex<V> vertex) {
        return outgoingEdgeList(vertex).size();
    }

    private List<Edge<E>> outgoingEdgeList(Vertex<V> vertex) {
        MatrixVertex vVertex = validate(vertex);
        
        List<Edge<E>> outEdgeList = new SinglyLinkedList<Edge<E>>();
        for(GraphEdge e : matrix[vVertex.getIndex()]) {
        	if(e != null) {
        		outEdgeList.addLast(e);
        	}
        }
        return outEdgeList;
    }
    
    @Override
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
        return outgoingEdgeList(vertex);
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
	 * Returns an iterable collection of edges
	 * 
	 * @return collection of edges
	 */
	@Override
	public int numEdges() {
		return edgeList.size();
	}

    @Override
    public void removeEdge(Edge<E> edge) {
        GraphEdge vEdge = validate(edge);
        Vertex<V>[] vertices = vEdge.getEndpoints();
        MatrixVertex vVertex1 = validate(vertices[0]);
        MatrixVertex vVertex2 = validate(vertices[1]);
        
        matrix[vVertex1.getIndex()][vVertex2.getIndex()] = null;
        
        // If it's not directed, delete the opposite
        if(!isDirected()) {
        	matrix[vVertex2.getIndex()][vVertex1.getIndex()] = null;
        }
        
        edgeList.remove(vEdge.getPosition());
    }
    
    /**
     * Safely casts a Vertex to a graph vertex
     * 
     * @param v is the vertex to validate
     * @return a graph vertex representation of the given Vertex
     * @throws IllegalArgumentException if the vertex is not a valid graph vertex
     */
    private MatrixVertex validate(Vertex<V> v) {
        if (!(v instanceof AdjacencyMatrixGraph.MatrixVertex)) {
            throw new IllegalArgumentException("Vertex is not a valid adjacency matrix vertex.");
        }
        return (MatrixVertex) v;
    }
}