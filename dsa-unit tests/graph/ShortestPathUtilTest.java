package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for ShortestPathUtil Checks the expected outputs of Dijksra's
 * algorithm and the shortest path tree construction method
 *
 * @author Dr. King
 * @author Jack Farrell
 *
 */
public class ShortestPathUtilTest {

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
     * Test the output of Dijkstra's algorithm.
     */
    @Test
    public void testDijkstra() {
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

        Map<Vertex<String>, Integer> result = ShortestPathUtil.dijkstra(graph, vA);

        assertNotNull(result.get(vA));
        assertNotNull(result.get(vB));
        assertNotNull(result.get(vC));
        assertNotNull(result.get(vD));
        assertNotNull(result.get(vE));

        assertEquals(Integer.valueOf(0), result.get(vA));   
        assertEquals(Integer.valueOf(5), result.get(vB));   
        assertEquals(Integer.valueOf(3), result.get(vC));  
        assertEquals(Integer.valueOf(5), result.get(vD));  
        assertEquals(Integer.valueOf(9), result.get(vE));  
    }

    /**
     * Test the output of the shortest path tree construction method.
     */
    @Test
    public void testShortestPathTree() {
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

        Map<Vertex<String>, Integer> costs = ShortestPathUtil.dijkstra(graph, vA);

        Map<Vertex<String>, Edge<Road>> result = ShortestPathUtil.shortestPathTree(graph, vA, costs);

        assertNotNull(result.get(vB));
        assertNotNull(result.get(vC));
        assertNotNull(result.get(vD));
        assertNotNull(result.get(vE));

        assertEquals(roadAB, result.get(vB).getElement());   
        assertEquals(roadAC, result.get(vC).getElement());  
        assertEquals(roadCD, result.get(vD).getElement());   
        assertEquals(roadDE, result.get(vE).getElement());   


        assertEquals(4, result.size());
    }
}