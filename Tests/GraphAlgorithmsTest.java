import api.NodeData;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Node;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphAlgorithmsTest {

    //need to test large graphs too
    //empty graph

    @Test
    void test_isConnected(){
        //all the given graphs are connected
        GraphAlgorithms GA = new GraphAlgorithms();
        GA.load("data/G1.json");
        Assert.assertTrue(GA.isConnected());

        GA.load("data/G2.json");
        Assert.assertTrue(GA.isConnected());

        GA.load("data/G3.json");
        Assert.assertTrue(GA.isConnected());

        GA.load("data/G4.json");
        Assert.assertTrue(GA.isConnected());
    }

    @Test
    void test_bfs(){
        GraphAlgorithms GA = new GraphAlgorithms();
        GA.load("data/G1.json");
        Graph graph = (Graph)GA.getGraph();

        Assert.assertEquals(7, GA.bfs(0,graph));
        Assert.assertEquals(7, GA.bfs(1,graph));
        Assert.assertEquals(7, GA.bfs(2,graph));
        Assert.assertEquals(8, GA.bfs(3,graph));
        Assert.assertEquals(8, GA.bfs(4,graph));
        Assert.assertEquals(8, GA.bfs(5,graph));
        Assert.assertEquals(7, GA.bfs(6,graph));
        Assert.assertEquals(7, GA.bfs(7,graph));
        Assert.assertEquals(7, GA.bfs(8,graph));
        Assert.assertEquals(7, GA.bfs(9,graph));
        Assert.assertEquals(7, GA.bfs(10,graph));
        Assert.assertEquals(7, GA.bfs(11,graph));
        Assert.assertEquals(8, GA.bfs(12,graph));
        Assert.assertEquals(8, GA.bfs(13,graph));
        Assert.assertEquals(7, GA.bfs(14,graph));
        Assert.assertEquals(7, GA.bfs(15,graph));
        Assert.assertEquals(7, GA.bfs(16,graph));
    }

    @Test
    void test_createOppositeGraph(){
        GraphAlgorithms GA = new GraphAlgorithms();
        GA.load("data/G1.json");
        Graph graph1 = (Graph)GA.getGraph();
        Assert.assertTrue(graph1 != GA.createOppositeGraph());

        GA.load("data/G2.json");
        Graph graph2 = (Graph)GA.getGraph();
        Assert.assertTrue(graph2 != GA.createOppositeGraph());

        GA.load("data/G3.json");
        Graph graph3 = (Graph)GA.getGraph();
        Assert.assertTrue(graph3 != GA.createOppositeGraph());

        GA.load("data/G4.json");
        Graph graph4 = (Graph)GA.getGraph();
        Assert.assertTrue(graph4 != GA.createOppositeGraph());
    }

    @Test
    void test_shortestPathDist(){
        GraphAlgorithms GA = new GraphAlgorithms();
        GA.load("data/myGraph.json");
        double pathDist = GA.shortestPathDist(0,7);
        assertEquals(57,pathDist);


        pathDist = GA.shortestPathDist(8,3);
        assertEquals(11,pathDist);

        pathDist = GA.shortestPathDist(3,8);
        assertEquals(41,pathDist);
    }

    @Test
    void test_tsp(){

    }

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