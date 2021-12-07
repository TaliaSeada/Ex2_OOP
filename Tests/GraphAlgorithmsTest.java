import api.NodeData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphAlgorithmsTest {

    @Test
    void testLoad() {
        GraphAlgorithms GA = new GraphAlgorithms();
        assertTrue(GA.load("data/G1.json"));
        assertNotNull(GA.getGraph());
    }

    @Test
    void testSave(){
        GraphAlgorithms GA = new GraphAlgorithms();
        GA.load("data/G1.json");
        assertTrue(GA.save("out.json"));
        assertFalse(GA.save("out"));
    }

    @Test
    void testCenter(){
        GraphAlgorithms GA = new GraphAlgorithms();
        GA.load("data/G1.json");
        NodeData node = GA.center();
        assertEquals(node.getKey(),8);

        GA.load("data/G2.json");
        node = GA.center();
        assertEquals(node.getKey(),0);

        GA.load("data/G3.json");
        node = GA.center();
        assertEquals(node.getKey(),40);
    }

    @Test
    void testShortestPath(){
        GraphAlgorithms GA = new GraphAlgorithms();
        GA.load("data/G1.json");
        List<NodeData> path = GA.shortestPath(0,8);
        String Path = "";
        String actual = "0-1-2-6-7-8";
        extractPath(path, Path, actual);

        path = GA.shortestPath(8,10);
        Path = "";
        actual = "8-9-10";
        extractPath(path, Path, actual);


    }

    @Test
    private void extractPath(List<NodeData> path, String path2, String actual) {
        for(int i =0; i < path.size();i++)
        {
            if(path2.equals("")){
                path2 += path.get(i).getKey();
            }
            else
            {
                path2 += "-" + path.get(i).getKey();
            }
        }
        assertEquals(actual, path2);
    }
}