package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;
import edu.ncsu.csc316.dsa.set.HashSet;
import edu.ncsu.csc316.dsa.set.Set;

/**
 * Test class for MinimumSpanningTreeUtil Checks the expected outputs of
 * Prim-Jarnik's algorithm and Kruskal's algorithm
 *
 * @author Dr. King
 * @author Jack Farrell
 *
 */
public class MinimumSpanningTreeUtilTest {

    /**
     * Inner class used for testing that implements the Weighted interface.
     */
    public class Road implements Weighted {
        /**
         * Road name
         */
        private String name;
        /**
         * Road length
         */
        private int length;

        /**
         * Constructs a Road with a given name and length.
         *
         * @param n name of the road
         * @param l length of the road
         */
        public Road(String n, int l) {
            this.name = n;
            this.length = l;
        }

        /**
         * Getter method for the road's name.
         *
         * @return the name of the road
         */
        public String getName() {
            return name;
        }

        /**
         * Getter method for the road's length.
         *
         * @return the length of the road
         */
        public int getLength() {
            return length;
        }

        /**
         * Getter method for the road's weight (length).
         *
         * @return the weight of the road
         */
        @Override
        public int getWeight() {
            return length;
        }
    }

    /**
     * Private field that holds a graph.
     */
    private Graph<String, Road> graph;

    /**
     * Initializes the graph before each test.
     */
    @Before
    public void setUp() {
        graph = new EdgeListGraph<>();
    }
    
    /**
     * Test the output of Prim-Jarnik's algorithm.
     */
    @Test
    public void testPrimJarnik() {
        Vertex<String> vA = graph.insertVertex("A");
        Vertex<String> vB = graph.insertVertex("B");
        Vertex<String> vC = graph.insertVertex("C");
        Vertex<String> vD = graph.insertVertex("D");
        Vertex<String> vE = graph.insertVertex("E");

        Road roadAB = new Road("Road1", 5);
        Road roadAC = new Road("Road2", 3);
        Road roadBC = new Road("Road3", 7);
        Road roadCD = new Road("Road4", 2);
        Road roadDE = new Road("Road5", 4);

        graph.insertEdge(vA, vB, roadAB);
        graph.insertEdge(vA, vC, roadAC);
        graph.insertEdge(vB, vC, roadBC);
        graph.insertEdge(vC, vD, roadCD);
        graph.insertEdge(vD, vE, roadDE);

		PositionalList<Edge<Road>> result = MinimumSpanningTreeUtil.primJarnik(graph);
		assertEquals(4, result.size());
		assertNotNull(result.first());
		assertNotNull(result.last());
    }

    /**
     * Test the output of Kruskal's algorithm.
     */
    @Test
    public void testKruskal() {
        Vertex<String> vA = graph.insertVertex("A");
        Vertex<String> vB = graph.insertVertex("B");
        Vertex<String> vC = graph.insertVertex("C");
        Vertex<String> vD = graph.insertVertex("D");
        Vertex<String> vE = graph.insertVertex("E");

        Road roadAB = new Road("Road1", 5);
        Road roadAC = new Road("Road2", 3);
        Road roadBC = new Road("Road3", 7);
        Road roadCD = new Road("Road4", 2);
        Road roadDE = new Road("Road5", 4);

        graph.insertEdge(vA, vB, roadAB);
        graph.insertEdge(vA, vC, roadAC);
        graph.insertEdge(vB, vC, roadBC);
        graph.insertEdge(vC, vD, roadCD);
        graph.insertEdge(vD, vE, roadDE);

        PositionalList<Edge<Road>> mstEdges = MinimumSpanningTreeUtil.kruskal(graph);

        assertEquals(4, mstEdges.size());

        Set<Edge<Road>> mstEdgeSet = new HashSet<>();
        for (Edge<Road> edge : mstEdges) {
            mstEdgeSet.add(edge);
        }

        assertTrue(mstEdgeSet.contains(graph.getEdge(vA, vC)) || mstEdgeSet.contains(graph.getEdge(vC, vA))); // Edge A-C
        assertTrue(mstEdgeSet.contains(graph.getEdge(vC, vD)) || mstEdgeSet.contains(graph.getEdge(vD, vC))); // Edge C-D
        assertTrue(mstEdgeSet.contains(graph.getEdge(vD, vE)) || mstEdgeSet.contains(graph.getEdge(vE, vD))); // Edge D-E
        assertTrue(mstEdgeSet.contains(graph.getEdge(vA, vB)) || mstEdgeSet.contains(graph.getEdge(vB, vA))); // Edge A-B

        int totalWeight = 0;
        for (Edge<Road> edge : mstEdges) {
            totalWeight += edge.getElement().getWeight();
        }
        assertEquals(14, totalWeight);
    }
}