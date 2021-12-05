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

    public ArrayList<Integer> Dijkstra(int sourceNode) {
        ArrayList<Integer> distances = new ArrayList<>();
        ArrayList<Boolean> visited = new ArrayList<>();
        for(int i =0; i < this.graph.getNodes().size();i++) {

        }

        return distances;
    }


    public int bfs(int nodeKey) {
        /*
            for bfs algorithm, we will change the tags of the graphs
            0 for Undiscovered nodes "white"
            1 for discovered but not finished "grey"
            2 for finished nodes "black"
         */
        ArrayList<Integer> distances =new ArrayList<>();
        ArrayList<EdgeData> lastEdge = new ArrayList<>();
        //lastEdge.get(i) represents the last edge in the path from node i to the node we do bfs on
        for(int i = 0; i < this.graph.getNodes().size(); i++) {
            distances.add(Integer.MAX_VALUE); //infinite
            lastEdge.add(null);
        }
        // mark the node as visit (now)
        this.graph.getNode(nodeKey).setTag(1);
        distances.set(nodeKey,0);
        LinkedList<Integer> queue = new LinkedList<>();

        queue.add(nodeKey);

        while(!queue.isEmpty()) {
            Node currNode = (Node)this.graph.getNodes().get(queue.poll());
            for(Integer key:currNode.getEdgeTo()){
                if(this.graph.getNodes().get(key).getTag() !=1 && this.graph.getNodes().get(key).getTag() !=2) {
                    this.graph.getNodes().get(key).setTag(1);
                    distances.set(key, distances.get(currNode.getKey()) + 1);
                    lastEdge.set(key,this.graph.getEdge(currNode.getKey(),key));
                    queue.add(key);
                }
            }
            this.graph.getNode(currNode.getKey()).setTag(2);
        }
        int maxDistance = Integer.MIN_VALUE;
        for(Integer distance:distances) {
            if(distance>maxDistance){
                maxDistance = distance;
            }
        }
        return maxDistance;
    }
    
    public Graph createOppositeGraph() {
        
        ArrayList<Edge> edges = new ArrayList<>();
        ArrayList<Node> nodes = new ArrayList<>();
        
        Iterator<EdgeData> edgeIter = this.graph.edgeIter();
        while(edgeIter.hasNext())
        {
            Edge currEdge = (Edge)edgeIter.next();
            Edge newEdge = new Edge(currEdge.getDest(),currEdge.getSrc(),currEdge.getWeight());
            edges.add(newEdge);
        }
        Iterator<NodeData> nodeIter = this.graph.nodeIter();
        while(nodeIter.hasNext())
        {
            NodeData node = nodeIter.next();
            Node newNode = new Node(node.getKey(), node.getLocation());
            nodes.add(newNode);
        }

        Graph opposite = new Graph(edges,nodes,this.graph.getName());

        return opposite;
    }

}
class pairs {
    private int index;
    private int pathLen;

    public pairs(int index, int pathLen){
        this.index = index;
        this.pathLen = pathLen;
    }

    public int getIndex(){
        return this.index;
    }

    public int getPathLen(){
        return this.pathLen;
    }
}
