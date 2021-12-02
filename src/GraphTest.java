import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @Test
    void testConstructor() {
        GraphAlgorithms GA = new GraphAlgorithms();
        GA.load("data/G1.json");
        Graph graph = (Graph)GA.getGraph();
        assertTrue(graph.getNodes().size() == 17);

    }
}