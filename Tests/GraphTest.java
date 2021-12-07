import org.junit.jupiter.api.Test;

import java.util.Iterator;

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
        edge = graph.removeEdge(0,5);
        assertEquals(edge,null);
    }

    @Test
    void testRemoveNode(){
        GraphAlgorithms GA = new GraphAlgorithms();
        GA.load("data/G1.json");
        Graph graph = (Graph)GA.getGraph();
        Node node = (Node)graph.removeNode(0);
        assertEquals(graph.getNodes().size(),16);
        assertEquals(graph.getAllEdges().size(),32);
        graph.removeNode(2);
        assertEquals(graph.getNodes().size(),15);
        assertEquals(graph.getAllEdges().size(),26);
    }

    @Test
    void testAddNode()
    {
        GraphAlgorithms GA = new GraphAlgorithms();
        GA.load("data/G1.json");
        Graph graph = (Graph)GA.getGraph();
        Node node = (Node)graph.removeNode(0);
        assertEquals(graph.getNodes().size(),16);
        graph.addNode(node);
        assertEquals(graph.getNodes().size(),17);
    }

    @Test
    void testConnect()
    {
        GraphAlgorithms GA = new GraphAlgorithms();
        GA.load("data/G1.json");
        Graph graph = (Graph)GA.getGraph();
        assertEquals(graph.getAllEdges().size(),36);
        graph.connect(0,10,5);
        assertEquals(graph.getAllEdges().size(),37);
    }

    @Test
    void testNodeIter() {
        GraphAlgorithms GA = new GraphAlgorithms();
        GA.load("data/G1.json");
        Graph graph = (Graph)GA.getGraph();
        Iterator<NodeData> iter = graph.nodeIter();
        int counter = 0;
        while(iter.hasNext()) {
            counter++;
            iter.next();
        }
        assertEquals(counter,17);

        Iterator<NodeData> iter2 = graph.nodeIter();
        graph.removeNode(10);
        assertThrows(RuntimeException.class, () -> iter2.next());
    }

    @Test
    void testEdgeIter(){
        GraphAlgorithms GA = new GraphAlgorithms();
        GA.load("data/G1.json");
        Graph graph = (Graph)GA.getGraph();
        Iterator<EdgeData> iter = graph.edgeIter();
        int counter = 0;
        while(iter.hasNext())
        {
            counter++;
            iter.next();
        }
        assertEquals(counter,36);
        Iterator<EdgeData> iter2 = graph.edgeIter();
        graph.removeEdge(0,1);
        assertThrows(RuntimeException.class, () -> iter2.next());

        Iterator<EdgeData> iterNode = graph.edgeIter(2);
        counter = 0;
        while(iterNode.hasNext())
        {
            counter++;
            iterNode.next();
        }

        assertEquals(counter,3);
        Iterator<EdgeData> iterNode2 = graph.edgeIter(2);
        graph.connect(2,7,8);
        assertThrows(RuntimeException.class, () -> iterNode2.next());
    }
}