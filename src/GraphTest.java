import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @Test
    void testConstructor() {
        GraphAlgorithms GA = new GraphAlgorithms();
        GA.load("data/G1.json");
        Graph graph = (Graph)GA.getGraph();
        assertTrue(graph.getNodes().size() == 17);
        assertTrue(graph.getAllEdges().size() == 36);
    }

    @Test
    void testRemoveEdge(){
        GraphAlgorithms GA = new GraphAlgorithms();
        GA.load("data/G1.json");
        Graph graph = (Graph)GA.getGraph();
        EdgeData edge = graph.removeEdge(0,16);
        assertEquals(edge.getSrc(),0);
        assertEquals(edge.getDest(),16);
    }

    @Test
    void testRemoveNode(){
        GraphAlgorithms GA = new GraphAlgorithms();
        GA.load("data/G1.json");
        Graph graph = (Graph)GA.getGraph();
        Node node = (Node)graph.removeNode(0);
        assertEquals(graph.getNodes().size(),16);
        assertEquals(graph.getAllEdges().size(),32);
    }
}