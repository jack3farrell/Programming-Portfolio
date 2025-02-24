package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;

/**
 * Test class for EdgeListGraph Checks the expected outputs of the Graph
 * abstract data type behaviors when using an edge list graph data structure
 *
 * @author Dr. King
 * @author Jack Farrell
 *
 */
public class EdgeListGraphTest {

	/**
	 * Field for undirected graph
	 */
	private Graph<String, Integer> undirectedGraph;
	/**
	 * Field for directed graph
	 */
	private Graph<String, Integer> directedGraph;

	/**
	 * Create a new instance of an edge list graph before each test case executes
	 */
	@Before
	public void setUp() {
		undirectedGraph = new EdgeListGraph<String, Integer>();
		directedGraph = new EdgeListGraph<String, Integer>(true);
	}

	private void buildUndirectedSample() {
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
		Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");

		undirectedGraph.insertEdge(v1, v2, 5);
		undirectedGraph.insertEdge(v1, v3, 10);
		undirectedGraph.insertEdge(v1, v4, 15);
		undirectedGraph.insertEdge(v1, v5, 20);
		undirectedGraph.insertEdge(v2, v3, 25);
		undirectedGraph.insertEdge(v2, v4, 30);
		undirectedGraph.insertEdge(v2, v5, 35);
		undirectedGraph.insertEdge(v3, v4, 40);
		undirectedGraph.insertEdge(v3, v5, 45);
		undirectedGraph.insertEdge(v4, v5, 50);
	}

	private void buildDirectedSample() {
		Vertex<String> v1 = directedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = directedGraph.insertVertex("Asheville");
		Vertex<String> v3 = directedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = directedGraph.insertVertex("Durham");
		Vertex<String> v5 = directedGraph.insertVertex("Greenville");
		Vertex<String> v6 = directedGraph.insertVertex("Boone");

		directedGraph.insertEdge(v1, v2, 5);
		directedGraph.insertEdge(v1, v3, 10);
		directedGraph.insertEdge(v1, v4, 15);
		directedGraph.insertEdge(v1, v5, 20);
		directedGraph.insertEdge(v2, v3, 25);
		directedGraph.insertEdge(v2, v4, 30);
		directedGraph.insertEdge(v2, v5, 35);
		directedGraph.insertEdge(v3, v4, 40);
		directedGraph.insertEdge(v3, v5, 45);
		directedGraph.insertEdge(v4, v5, 50);
		directedGraph.insertEdge(v5, v6, 55);
	}

	/**
	 * Test the output of the numVertices() behavior
	 */
	@Test
	public void testNumVertices() {
		buildUndirectedSample();
		assertEquals(5, undirectedGraph.numVertices());

		buildDirectedSample();
		assertEquals(6, directedGraph.numVertices());
	}

	/**
	 * Test the output of the vertices() behavior
	 */
	@Test
	public void testVertices() {
		// We cannot call buildUndirectedSample() because
		// then we would not be able to reference specific edges
		// or vertices when testing
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
		Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
		undirectedGraph.insertEdge(v1, v2, 5);
		undirectedGraph.insertEdge(v1, v3, 10);
		undirectedGraph.insertEdge(v1, v4, 15);
		undirectedGraph.insertEdge(v1, v5, 20);
		undirectedGraph.insertEdge(v2, v3, 25);
		undirectedGraph.insertEdge(v2, v4, 30);
		undirectedGraph.insertEdge(v2, v5, 35);
		undirectedGraph.insertEdge(v3, v4, 40);
		undirectedGraph.insertEdge(v3, v5, 45);
		undirectedGraph.insertEdge(v4, v5, 50);

		Iterable<Vertex<String>> nodes = undirectedGraph.vertices();
		int totalVertices = 0;
		for (Vertex<String> node : nodes) {
			totalVertices++;
		}

		assertEquals(5, totalVertices);

		// DIRECTED
		// We cannot call buildDirectedSample() because
		// then we would not be able to reference specific edges
		// or vertices when testing
		v1 = directedGraph.insertVertex("Raleigh");
		v2 = directedGraph.insertVertex("Asheville");
		v3 = directedGraph.insertVertex("Wilmington");
		v4 = directedGraph.insertVertex("Durham");
		v5 = directedGraph.insertVertex("Greenville");
		Vertex<String> v6 = directedGraph.insertVertex("Boone");
		directedGraph.insertEdge(v1, v2, 5);
		directedGraph.insertEdge(v1, v3, 10);
		directedGraph.insertEdge(v1, v4, 15);
		directedGraph.insertEdge(v1, v5, 20);
		directedGraph.insertEdge(v2, v3, 25);
		directedGraph.insertEdge(v2, v4, 30);
		directedGraph.insertEdge(v2, v5, 35);
		directedGraph.insertEdge(v3, v4, 40);
		directedGraph.insertEdge(v3, v5, 45);
		directedGraph.insertEdge(v4, v5, 50);
		directedGraph.insertEdge(v5, v6, 55);

		Iterable<Vertex<String>> graphVertices = undirectedGraph.vertices();
		int vertexCount = 0;
		for (Vertex<String> vertex : graphVertices) {
			vertexCount++;
		}

		assertEquals(5, vertexCount);
	}

	/**
	 * Test the output of the numEdges() behavior
	 */
	@Test
	public void testNumEdges() {
		buildUndirectedSample();
		assertEquals(10, undirectedGraph.numEdges());
		buildDirectedSample();
		assertEquals(11, directedGraph.numEdges());
	}

	/**
	 * Test the output of the edges() behavior
	 */
	@Test
	public void testEdges() {
		// UNDIRECTED
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
		Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
		Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
		Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
		Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
		Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
		Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
		Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
		Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
		Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
		Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
		Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);

		Iterable<Edge<Integer>> undirectedEdges = undirectedGraph.edges();
		int undirectedEdgeCount = 0;
		for (Edge<Integer> edge : undirectedEdges) {
			undirectedEdgeCount++;
		}
		assertEquals(10, undirectedEdgeCount);
		assertTrue(undirectedEdges.iterator().hasNext());

		// DIRECTED
		v1 = directedGraph.insertVertex("Raleigh");
		v2 = directedGraph.insertVertex("Asheville");
		v3 = directedGraph.insertVertex("Wilmington");
		v4 = directedGraph.insertVertex("Durham");
		v5 = directedGraph.insertVertex("Greenville");
		Vertex<String> v6 = directedGraph.insertVertex("Boone");
		e1 = directedGraph.insertEdge(v1, v2, 5);
		e2 = directedGraph.insertEdge(v1, v3, 10);
		e3 = directedGraph.insertEdge(v1, v4, 15);
		e4 = directedGraph.insertEdge(v1, v5, 20);
		e5 = directedGraph.insertEdge(v2, v3, 25);
		e6 = directedGraph.insertEdge(v2, v4, 30);
		e7 = directedGraph.insertEdge(v2, v5, 35);
		e8 = directedGraph.insertEdge(v3, v4, 40);
		e9 = directedGraph.insertEdge(v3, v5, 45);
		e10 = directedGraph.insertEdge(v4, v5, 50);
		Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);

		Iterable<Edge<Integer>> directedEdges = directedGraph.edges();
		int directedEdgeCount = 0;
		for (Edge<Integer> edge : directedEdges) {
			directedEdgeCount++;
		}
		assertEquals(11, directedEdgeCount);
		assertTrue(directedEdges.iterator().hasNext());
	}

	/**
	 * Test the output of the getEdge(v1, v2) behavior
	 */
	@Test
	public void testGetEdge() {
		// UNDIRECTED GRAPH
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
		Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
		Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
		Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
		Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
		Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
		Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
		Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);

		assertEquals(e1, undirectedGraph.getEdge(v1, v2));
		assertEquals(e1, undirectedGraph.getEdge(v2, v1));
		assertEquals(e2, undirectedGraph.getEdge(v1, v3));
		assertEquals(e3, undirectedGraph.getEdge(v1, v4));
		assertEquals(null, undirectedGraph.getEdge(v5, v6));

		// DIRECTED GRAPH
		v1 = directedGraph.insertVertex("Raleigh");
		v2 = directedGraph.insertVertex("Asheville");
		v3 = directedGraph.insertVertex("Wilmington");
		v4 = directedGraph.insertVertex("Durham");
		v5 = directedGraph.insertVertex("Greenville");
		v6 = directedGraph.insertVertex("Boone");
		e1 = directedGraph.insertEdge(v1, v2, 5);
		e2 = directedGraph.insertEdge(v1, v3, 10);
		e3 = directedGraph.insertEdge(v1, v4, 15);
		e4 = directedGraph.insertEdge(v1, v5, 20);
		e5 = directedGraph.insertEdge(v5, v6, 55);

		assertEquals(e1, directedGraph.getEdge(v1, v2));
		assertEquals(null, directedGraph.getEdge(v2, v1));
		assertEquals(e2, directedGraph.getEdge(v1, v3));
		assertEquals(e5, directedGraph.getEdge(v5, v6));
		assertEquals(null, directedGraph.getEdge(v6, v5));
	}

	/**
	 * Test the output of the endVertices(e) behavior
	 */
	@Test
	public void testEndVertices() {
		// UNDIRECTED GRAPH
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
		Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
		Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
		Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
		Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
		Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);

		Vertex<String>[] endpoints = undirectedGraph.endVertices(e1);
		assertTrue((endpoints[0] == v1 && endpoints[1] == v2) || (endpoints[0] == v2 && endpoints[1] == v1));

		endpoints = undirectedGraph.endVertices(e2);
		assertTrue((endpoints[0] == v1 && endpoints[1] == v3) || (endpoints[0] == v3 && endpoints[1] == v1));

		endpoints = undirectedGraph.endVertices(e3);
		assertTrue((endpoints[0] == v1 && endpoints[1] == v4) || (endpoints[0] == v4 && endpoints[1] == v1));

		// DIRECTED GRAPH
		v1 = directedGraph.insertVertex("Raleigh");
		v2 = directedGraph.insertVertex("Asheville");
		v3 = directedGraph.insertVertex("Wilmington");
		v4 = directedGraph.insertVertex("Durham");
		v5 = directedGraph.insertVertex("Greenville");
		v6 = directedGraph.insertVertex("Boone");
		e1 = directedGraph.insertEdge(v1, v2, 5);
		e2 = directedGraph.insertEdge(v1, v3, 10);
		e3 = directedGraph.insertEdge(v1, v4, 15);

		endpoints = directedGraph.endVertices(e1);
		assertEquals(v1, endpoints[0]);
		assertEquals(v2, endpoints[1]);

		endpoints = directedGraph.endVertices(e2);
		assertEquals(v1, endpoints[0]);
		assertEquals(v3, endpoints[1]);

		endpoints = directedGraph.endVertices(e3);
		assertEquals(v1, endpoints[0]);
		assertEquals(v4, endpoints[1]);
	}

	/**
	 * Test the output of the opposite(v, e) behavior
	 */
	@Test
	public void testOpposite() {
		// UNDIRECTED GRAPH
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
		Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
		Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
		Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
		Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);

		assertEquals(v2, undirectedGraph.opposite(v1, e1));
		assertEquals(v1, undirectedGraph.opposite(v2, e1));
		assertEquals(v3, undirectedGraph.opposite(v1, e2));
		assertEquals(v1, undirectedGraph.opposite(v3, e2));

		// DIRECTED GRAPH
		v1 = directedGraph.insertVertex("Raleigh");
		v2 = directedGraph.insertVertex("Asheville");
		v3 = directedGraph.insertVertex("Wilmington");
		v4 = directedGraph.insertVertex("Durham");
		v5 = directedGraph.insertVertex("Greenville");
		Vertex<String> v6 = directedGraph.insertVertex("Boone");
		e1 = directedGraph.insertEdge(v1, v2, 5);
		e2 = directedGraph.insertEdge(v1, v3, 10);
		e3 = directedGraph.insertEdge(v1, v4, 15);
		Edge<Integer> e4 = directedGraph.insertEdge(v5, v6, 55);

		Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
		assertEquals(v6, directedGraph.opposite(v5, e11));
		assertEquals(v5, directedGraph.opposite(v6, e11));
	}

	/**
	 * Test the output of the outDegree(v) behavior
	 */
	@Test
	public void testOutDegree() {
		// UNDIRECTED GRAPH
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
		Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
		Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
		undirectedGraph.insertEdge(v1, v2, 5);
		undirectedGraph.insertEdge(v1, v3, 10);
		undirectedGraph.insertEdge(v1, v4, 15);
		undirectedGraph.insertEdge(v1, v5, 20);
		undirectedGraph.insertEdge(v2, v3, 25);
		undirectedGraph.insertEdge(v2, v4, 30);
		undirectedGraph.insertEdge(v2, v5, 35);
		undirectedGraph.insertEdge(v3, v4, 40);
		undirectedGraph.insertEdge(v3, v5, 45);
		undirectedGraph.insertEdge(v4, v5, 50);

		assertEquals(4, undirectedGraph.outDegree(v1));
		assertEquals(4, undirectedGraph.outDegree(v2));
		assertEquals(4, undirectedGraph.outDegree(v3));
		assertEquals(4, undirectedGraph.outDegree(v4));
		assertEquals(4, undirectedGraph.outDegree(v5));
		assertEquals(0, undirectedGraph.outDegree(v6));

		// DIRECTED GRAPH
		v1 = directedGraph.insertVertex("Raleigh");
		v2 = directedGraph.insertVertex("Asheville");
		v3 = directedGraph.insertVertex("Wilmington");
		v4 = directedGraph.insertVertex("Durham");
		v5 = directedGraph.insertVertex("Greenville");
		v6 = directedGraph.insertVertex("Boone");
		directedGraph.insertEdge(v1, v2, 5);
		directedGraph.insertEdge(v1, v3, 10);
		directedGraph.insertEdge(v1, v4, 15);
		directedGraph.insertEdge(v1, v5, 20);
		directedGraph.insertEdge(v2, v3, 25);
		directedGraph.insertEdge(v2, v4, 30);
		directedGraph.insertEdge(v2, v5, 35);
		directedGraph.insertEdge(v3, v4, 40);
		directedGraph.insertEdge(v3, v5, 45);
		directedGraph.insertEdge(v4, v5, 50);
		directedGraph.insertEdge(v5, v6, 55);

		assertEquals(4, directedGraph.outDegree(v1));
		assertEquals(3, directedGraph.outDegree(v2));
		assertEquals(2, directedGraph.outDegree(v3));
		assertEquals(1, directedGraph.outDegree(v4));
		assertEquals(1, directedGraph.outDegree(v5));
		assertEquals(0, directedGraph.outDegree(v6));
	}

	/**
	 * Test the output of the inDegree(v) behavior
	 */
	@Test
	public void testInDegree() {
		// UNDIRECTED GRAPH
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
		Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
		Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
		undirectedGraph.insertEdge(v1, v2, 5);
		undirectedGraph.insertEdge(v1, v3, 10);
		undirectedGraph.insertEdge(v1, v4, 15);
		undirectedGraph.insertEdge(v1, v5, 20);
		undirectedGraph.insertEdge(v2, v3, 25);
		undirectedGraph.insertEdge(v2, v4, 30);
		undirectedGraph.insertEdge(v2, v5, 35);
		undirectedGraph.insertEdge(v3, v4, 40);
		undirectedGraph.insertEdge(v3, v5, 45);
		undirectedGraph.insertEdge(v4, v5, 50);

		assertEquals(4, undirectedGraph.inDegree(v1));
		assertEquals(4, undirectedGraph.inDegree(v2));
		assertEquals(4, undirectedGraph.inDegree(v3));
		assertEquals(4, undirectedGraph.inDegree(v4));
		assertEquals(4, undirectedGraph.inDegree(v5));
		// No edges connected to Boone
		assertEquals(0, undirectedGraph.inDegree(v6));

		// DIRECTED GRAPH
		v1 = directedGraph.insertVertex("Raleigh");
		v2 = directedGraph.insertVertex("Asheville");
		v3 = directedGraph.insertVertex("Wilmington");
		v4 = directedGraph.insertVertex("Durham");
		v5 = directedGraph.insertVertex("Greenville");
		v6 = directedGraph.insertVertex("Boone");
		directedGraph.insertEdge(v1, v2, 5);
		directedGraph.insertEdge(v1, v3, 10);
		directedGraph.insertEdge(v1, v4, 15);
		directedGraph.insertEdge(v1, v5, 20);
		directedGraph.insertEdge(v2, v3, 25);
		directedGraph.insertEdge(v2, v4, 30);
		directedGraph.insertEdge(v2, v5, 35);
		directedGraph.insertEdge(v3, v4, 40);
		directedGraph.insertEdge(v3, v5, 45);
		directedGraph.insertEdge(v4, v5, 50);
		directedGraph.insertEdge(v5, v6, 55);

		assertEquals(0, directedGraph.inDegree(v1));
		assertEquals(1, directedGraph.inDegree(v2));
		assertEquals(2, directedGraph.inDegree(v3));
		assertEquals(3, directedGraph.inDegree(v4));
		assertEquals(4, directedGraph.inDegree(v5));
		assertEquals(1, directedGraph.inDegree(v6));
	}

	/**
	 * Test the output of the outgoingEdges(v) behavior
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testOutgoingEdges() {
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
		Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
		Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
		Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
		Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
		Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
		Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
		Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
		Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
		Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
		Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
		Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
		Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);

		// We can use a custom arrayContains() helper method to check that
		// an array *contains* a certain target edge.
		// This is helpful for testing graph ADT behaviors where an order
		// of edges cannot be guaranteed (such as .outgoingEdges or .incomingEdges
		// in adjacencyMaps, etc.)
		Edge<Integer>[] edgesArray = (Edge<Integer>[]) (new Edge[4]);
		int edgeIndex = 0;
		Iterator<Edge<Integer>> edgeIterator = undirectedGraph.outgoingEdges(v1).iterator();

		assertTrue(edgeIterator.hasNext());
		edgesArray[edgeIndex++] = edgeIterator.next();
		edgesArray[edgeIndex++] = edgeIterator.next();
		edgesArray[edgeIndex++] = edgeIterator.next();
		edgesArray[edgeIndex++] = edgeIterator.next();

		assertFalse(edgeIterator.hasNext());

		assertTrue(arrayContains(edgesArray, e1));
		assertTrue(arrayContains(edgesArray, e2));
		assertTrue(arrayContains(edgesArray, e3));
		assertTrue(arrayContains(edgesArray, e4));

		// DIRECTED
		v1 = directedGraph.insertVertex("Raleigh");
		v2 = directedGraph.insertVertex("Asheville");
		v3 = directedGraph.insertVertex("Wilmington");
		v4 = directedGraph.insertVertex("Durham");
		v5 = directedGraph.insertVertex("Greenville");
		v6 = directedGraph.insertVertex("Boone");
		e1 = directedGraph.insertEdge(v1, v2, 5);
		e2 = directedGraph.insertEdge(v1, v3, 10);
		e3 = directedGraph.insertEdge(v1, v4, 15);
		e4 = directedGraph.insertEdge(v1, v5, 20);
		e5 = directedGraph.insertEdge(v2, v3, 25);
		e6 = directedGraph.insertEdge(v2, v4, 30);
		e7 = directedGraph.insertEdge(v2, v5, 35);
		e8 = directedGraph.insertEdge(v3, v4, 40);
		e9 = directedGraph.insertEdge(v3, v5, 45);
		e10 = directedGraph.insertEdge(v4, v5, 50);
		Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);

		assertNotNull(e11);

		int undirectedEdgeCount = 0;
		for (Edge<Integer> outgoingEdge : undirectedGraph.outgoingEdges(v1)) {
			assertNotNull(outgoingEdge);
			undirectedEdgeCount++;
		}
		assertEquals(0, undirectedEdgeCount);

		int directedEdgeCount = 0;
		for (Edge<Integer> outgoingEdge : directedGraph.outgoingEdges(v1)) {
			assertNotNull(outgoingEdge);
			directedEdgeCount++;
		}
		assertEquals(4, directedEdgeCount);
	}

	// Helper method to check that an array has a certain target
	private boolean arrayContains(Edge<Integer>[] temp, Edge<Integer> target) {
		for (Edge<Integer> e : temp) {
			if (e == target) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Test the output of the incomingEdges(v) behavior
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testIncomingEdges() {
		// UNDIRECTED GRAPH
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
		Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
		Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
		undirectedGraph.insertEdge(v1, v2, 5);
		undirectedGraph.insertEdge(v1, v3, 10);
		undirectedGraph.insertEdge(v1, v4, 15);
		undirectedGraph.insertEdge(v1, v5, 20);
		undirectedGraph.insertEdge(v2, v3, 25);
		undirectedGraph.insertEdge(v2, v4, 30);
		undirectedGraph.insertEdge(v2, v5, 35);
		undirectedGraph.insertEdge(v3, v4, 40);
		undirectedGraph.insertEdge(v3, v5, 45);
		undirectedGraph.insertEdge(v4, v5, 50);

		int undirectedCount = 0;
		for (Edge<Integer> edge : undirectedGraph.incomingEdges(v1)) {
			assertNotNull(edge);
			undirectedCount++;
		}
		assertEquals(4, undirectedCount);

		undirectedCount = 0;
		for (Edge<Integer> edge : undirectedGraph.incomingEdges(v6)) {
			undirectedCount++;
		}
		assertEquals(0, undirectedCount);

		// DIRECTED GRAPH
		v1 = directedGraph.insertVertex("Raleigh");
		v2 = directedGraph.insertVertex("Asheville");
		v3 = directedGraph.insertVertex("Wilmington");
		v4 = directedGraph.insertVertex("Durham");
		v5 = directedGraph.insertVertex("Greenville");
		v6 = directedGraph.insertVertex("Boone");
		directedGraph.insertEdge(v1, v2, 5);
		directedGraph.insertEdge(v1, v3, 10);
		directedGraph.insertEdge(v1, v4, 15);
		directedGraph.insertEdge(v1, v5, 20);
		directedGraph.insertEdge(v2, v3, 25);
		directedGraph.insertEdge(v2, v4, 30);
		directedGraph.insertEdge(v2, v5, 35);
		directedGraph.insertEdge(v3, v4, 40);
		directedGraph.insertEdge(v3, v5, 45);
		directedGraph.insertEdge(v4, v5, 50);
		Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);

		int directedCount = 0;
		for (Edge<Integer> edge : directedGraph.incomingEdges(v5)) {
			assertNotNull(edge);
			directedCount++;
		}
		assertEquals(4, directedCount);

		directedCount = 0;
		for (Edge<Integer> edge : directedGraph.incomingEdges(v6)) {
			assertNotNull(edge);
			directedCount++;
		}
		assertEquals(1, directedCount);
	}

	/**
	 * Test the output of the insertVertex(x) behavior
	 */
	@Test
	public void validateAddNode() {
		assertEquals(0, undirectedGraph.numVertices());
		Vertex<String> cityA = undirectedGraph.insertVertex("Fayetteville");
		assertEquals(1, undirectedGraph.numVertices());
		Iterator<Vertex<String>> vertexChecker = undirectedGraph.vertices().iterator();
		assertTrue(vertexChecker.hasNext());
		assertEquals(cityA, vertexChecker.next());
		assertFalse(vertexChecker.hasNext());

		assertEquals(0, directedGraph.numVertices());
		Vertex<String> cityB = directedGraph.insertVertex("Brasilia");
		assertEquals(1, directedGraph.numVertices());
		Iterator<Vertex<String>> directedVertexChecker = directedGraph.vertices().iterator();
		assertTrue(directedVertexChecker.hasNext());
		assertEquals(cityB, directedVertexChecker.next());
		assertFalse(directedVertexChecker.hasNext());
	}

	/**
	 * Test the output of the insertEdge(v1, v2, x) behavior
	 */
	@Test
	public void testInsertEdge() {
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");

		assertEquals(0, undirectedGraph.numEdges());
		Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 99);
		assertEquals(1, undirectedGraph.numEdges());
		Iterator<Edge<Integer>> it = undirectedGraph.edges().iterator();
		assertTrue(it.hasNext());
		assertEquals(e1, it.next());
		assertFalse(it.hasNext());

		Vertex<String> v3 = directedGraph.insertVertex("Cary");
		Vertex<String> v4 = directedGraph.insertVertex("Wake");

		assertEquals(0, directedGraph.numEdges());
		Edge<Integer> edgeInserted = directedGraph.insertEdge(v3, v4, 99);
		assertEquals(1, directedGraph.numEdges());
		Iterator<Edge<Integer>> edgeIterator = directedGraph.edges().iterator();
		assertTrue(edgeIterator.hasNext());
		assertEquals(edgeInserted, edgeIterator.next());
		assertFalse(edgeIterator.hasNext());
	}

	/**
	 * Test the output of the removeVertex(v) behavior
	 */
	@Test
	public void testRemoveVertex() {
		// Undirected Graph
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Vertex<String> v3 = undirectedGraph.insertVertex("Durham");
		Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
		Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);

		assertEquals(3, undirectedGraph.numVertices());
		assertEquals(2, undirectedGraph.numEdges());
		undirectedGraph.removeVertex(v1);
		assertEquals(2, undirectedGraph.numVertices());
		assertEquals(0, undirectedGraph.numEdges());

		// Directed Graph
		v1 = directedGraph.insertVertex("Raleigh");
		v2 = directedGraph.insertVertex("Asheville");
		e1 = directedGraph.insertEdge(v1, v2, 15);

		assertEquals(2, directedGraph.numVertices());
		assertEquals(1, directedGraph.numEdges());
		directedGraph.removeVertex(v2);
		assertEquals(1, directedGraph.numVertices());
		assertEquals(0, directedGraph.numEdges());
	}

	/**
	 * Test the output of the removeEdge(e) behavior
	 */
	@Test
	public void testRemoveEdge() {
		// Undirected Graph
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);

		assertEquals(2, undirectedGraph.numVertices());
		assertEquals(1, undirectedGraph.numEdges());
		undirectedGraph.removeEdge(e1);
		assertEquals(2, undirectedGraph.numVertices());
		assertEquals(0, undirectedGraph.numEdges());

		// Directed Graph
		v1 = directedGraph.insertVertex("Raleigh");
		v2 = directedGraph.insertVertex("Asheville");
		Edge<Integer> e2 = directedGraph.insertEdge(v1, v2, 10);

		assertEquals(2, directedGraph.numVertices());
		assertEquals(1, directedGraph.numEdges());
		directedGraph.removeEdge(e2);
		assertEquals(2, directedGraph.numVertices());
		assertEquals(0, directedGraph.numEdges());
	}
}
