package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for GraphTraversalUtil
 * Checks the expected outputs of depth first search
 * and breadth first search
 *
 * @author Dr. King
 * @author Jack Farrell
 *
 */
public class GraphTraversalUtilTest {

    /**
     * Test the output of depth first search on a graph
     */ 
    @Test
    public void testDepthFirstSearch() {
        Graph<String, Integer> graph = new AdjacencyListGraph<>(false);

        Vertex<String> vA = graph.insertVertex("A");
        Vertex<String> vB = graph.insertVertex("B");
        Vertex<String> vC = graph.insertVertex("C");
        Vertex<String> vD = graph.insertVertex("D");
        Vertex<String> vE = graph.insertVertex("E");

        Edge<Integer> eAB = graph.insertEdge(vA, vB, 1);
        Edge<Integer> eAC = graph.insertEdge(vA, vC, 2);
        Edge<Integer> eBD = graph.insertEdge(vB, vD, 3);
        Edge<Integer> eCE = graph.insertEdge(vC, vE, 4);
        Edge<Integer> eDE = graph.insertEdge(vD, vE, 5);

        Map<Vertex<String>, Edge<Integer>> dfsForest = GraphTraversalUtil.depthFirstSearch(graph, vA);

        assertEquals(eAB, dfsForest.get(vB));
        assertEquals(eBD, dfsForest.get(vD));
        assertEquals(eDE, dfsForest.get(vE));
        assertEquals(eCE, dfsForest.get(vC));
    }

    /**
     * Test the output of the breadth first search
     */
    @Test
    public void testBreadthFirstSearch() {
        Graph<String, Integer> graph = new AdjacencyListGraph<>(false);

        Vertex<String> vA = graph.insertVertex("A");
        Vertex<String> vB = graph.insertVertex("B");
        Vertex<String> vC = graph.insertVertex("C");
        Vertex<String> vD = graph.insertVertex("D");
        Vertex<String> vE = graph.insertVertex("E");

        Edge<Integer> eAB = graph.insertEdge(vA, vB, 1);
        Edge<Integer> eAC = graph.insertEdge(vA, vC, 2);
        Edge<Integer> eBD = graph.insertEdge(vB, vD, 3);
        Edge<Integer> eCE = graph.insertEdge(vC, vE, 4);
        Edge<Integer> eDE = graph.insertEdge(vD, vE, 5);

        Map<Vertex<String>, Edge<Integer>> bfsForest = GraphTraversalUtil.breadthFirstSearch(graph, vA);

        assertEquals(eAB, bfsForest.get(vB));
        assertEquals(eAC, bfsForest.get(vC));
        assertEquals(eBD, bfsForest.get(vD));
        assertEquals(eCE, bfsForest.get(vE));
    }
}