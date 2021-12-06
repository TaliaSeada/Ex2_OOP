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
        ArrayList<HashMap> result = Dijkstra(src);
        HashMap<Integer,Double> dists = result.get(0);
        return dists.get(dest);
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        List<NodeData> path = new ArrayList<>();
        HashMap<Integer,Node> lastPath = Dijkstra(src).get(1);
        int firstInPath = lastPath.get(dest).getKey();
        path.add(this.graph.getNode(dest));
        path.add(lastPath.get(dest));
        while(firstInPath!=src){
            path.add(lastPath.get(firstInPath));
            firstInPath = lastPath.get(firstInPath).getKey();
        }
        List<NodeData> pathReversed = new ArrayList<>();
        for(int i = (path.size()) ; i >0 ; i--)
        {
            pathReversed.add(path.get(i-1));
        }
        return pathReversed;
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
            return false;
        }
    }

    public ArrayList<HashMap> Dijkstra(int sourceNode) {
        ArrayList<HashMap> res = new ArrayList<>();

        // hashMap of the nodes and their path length from the node
        HashMap<Integer, Double> dist = new HashMap<Integer, Double>();
        // hashMap of the path from some node to the current node
        HashMap<Integer, Node> prev = new HashMap<Integer, Node>();

        ArrayList<Integer> q = new ArrayList<Integer>();
        for (Integer v : this.graph.getNodes().keySet()){
            dist.put(v, Double.MAX_VALUE);
            prev.put(v, null);
            q.add(v);
        }

        dist.put(sourceNode, 0.0);
        ArrayList<Integer> visited = new ArrayList<>();
        while(q.size() != 0){
            int min = getMinPath(dist,visited);
            Node node = (Node) this.graph.getNode(min);
            q.remove((Integer)min);
            visited.add(min);
            for(Integer key : node.getEdgeFrom()){
                if(dist.get(key) == Double.MAX_VALUE){
                    dist.put(key, dist.get(min) + (this.graph.getEdge(min, key).getWeight()));
                    prev.put(key, node);
                }
                else {
                    double tmp = dist.get(min) + (this.graph.getEdge(min, key).getWeight());

                    if (dist.get(key) > tmp) {
                        dist.put(key, tmp);
                        prev.put(key, node);
                    }
                }
            }

        }
        res.add(dist);
        res.add(prev);
        return res;

    }
    private int getMinPath(HashMap<Integer, Double> dist,ArrayList<Integer> visited) {
        double min = Double.MAX_VALUE;
        int res = 0;
        for(Integer key : dist.keySet()){
            if(dist.get(key) < min && !visited.contains(key)){
                min = dist.get(key);
                res = key;
            }
        }
        return res;
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
        while(edgeIter.hasNext()) {
            Edge currEdge = (Edge)edgeIter.next();
            Edge newEdge = new Edge(currEdge.getDest(),currEdge.getSrc(),currEdge.getWeight());
            edges.add(newEdge);
        }
        Iterator<NodeData> nodeIter = this.graph.nodeIter();
        while(nodeIter.hasNext()) {
            NodeData node = nodeIter.next();
            Node newNode = new Node(node.getKey(), node.getLocation());
            nodes.add(newNode);
        }

        Graph opposite = new Graph(edges,nodes,this.graph.getName());

        return opposite;
    }

}