import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphAlgorithmsTest {

    @Test
    void load() {
        GraphAlgorithms GA = new GraphAlgorithms();
        assertTrue(GA.load("data/G1.json"));
        assertNotNull(GA.getGraph());


    }
}