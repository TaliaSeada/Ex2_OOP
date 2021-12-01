import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

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
       return this.graph;
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
            List<?> edges = null; // ? - we don't know the type yet
            List<?> nodes = null;
            Map<?,?> map = gson.fromJson(reader, Map.class);
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                if(entry.getKey().equals("Edges")) {
                    edges = (List<?>) entry.getValue();
                }
                else {
                    nodes = (List<?>) entry.getValue();
                }

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
            Graph graph = new Graph(edgeArrayList,nodeArrayList,file.split("\\.")[0]);
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


    public int bfs(NodeData node)
    {
        /*
            for bfs algorithm, we will change the tags of the graphs
            0 for Undiscovered nodes "white"
            1 for discovered but not finished "grey"
            2 for finished nodes "black"
         */
        ArrayList<Integer> distances =new ArrayList<>();
        ArrayList<EdgeData> lastEdge = new ArrayList<>(this.graph.getNodes().size());
        //lastEdge.get(i) represents the last edge in the path from node i to the node we do bfs on
        for(int i = 0; i < this.graph.getNodes().size(); i++) {
            distances.add(Integer.MAX_VALUE); //infinite
        }
        // mark the node as visit (now)
        this.graph.getNode(node.getKey()).setTag(1);
        lastEdge.set(node.getKey(),null);
        distances.set(node.getKey(),0);
        LinkedList<Node> queue = new LinkedList<>();

        queue.add((Node)node);

        while(!queue.isEmpty()) {
            Node currNode = queue.poll();
            for(Integer key:currNode.getEdgeTo()){
                if(this.graph.getNode(key).getTag() == 0) {
                    this.graph.getNode(key).setTag(1);
                    distances.set(key, distances.get(currNode.getKey()) + 1);
                    lastEdge.set(key,this.graph.getEdge(currNode.getKey(),key));
                    queue.add((Node) this.graph.getNode(key));
                }
            }
            this.graph.getNode(currNode.getKey()).setTag(2);
        }
        int maxDistance = Integer.MIN_VALUE;
        for(Integer distance:distances)
        {
            if(distance>maxDistance){
                maxDistance = distance;
            }
        }
        return maxDistance;
    }
}
