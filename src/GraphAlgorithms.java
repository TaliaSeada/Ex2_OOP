import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.*;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.stream.JsonToken;

public class GraphAlgorithms implements DirectedWeightedGraphAlgorithms{
    private Graph graph;


    @Override
    public void init(DirectedWeightedGraph g) {
        this.graph = (Graph) g;

    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return null;
    }

    @Override
    public DirectedWeightedGraph copy() {
        return null;
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        return 0;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        return null;
    }

    @Override
    public NodeData center() {
        return null;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        return null;
    }

    @Override
    public boolean save(String file) {
        return false;
    }

    @Override
    public boolean load(String file) {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(file));
            List<?> edges = null;
            List<?> nodes = null;
            Map<?,?> map = gson.fromJson(reader, Map.class);
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                if(entry.getKey().equals("Edges")) {
                    edges = (List<?>) entry.getValue();
                }
                else {
                    nodes = (List<?>) entry.getValue();
                }
                System.out.println(entry.getKey());
            }
            assert edges != null;
            ArrayList<Node> nodeArrayList = new ArrayList<>();
            ArrayList<Edge> edgeArrayList = new ArrayList<>();
            for (Object edge : edges) {
                edgeArrayList.add(new Edge((LinkedTreeMap<?, ?>) edge));
           }
            assert nodes != null;
            for (Object node : nodes) {
                nodeArrayList.add(new Node((LinkedTreeMap<?, ?>) node));
            }
            Graph graph = new Graph(edgeArrayList,nodeArrayList,file.split(".")[0]);
            this.init(graph);

            reader.close();
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
