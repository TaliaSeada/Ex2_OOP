
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Graph implements DirectedWeightedGraph{
    private HashMap<Integer,NodeData> nodes;
    private HashMap<Integer,HashMap<String,EdgeData>> nodeEdges;
    private ArrayList<EdgeData> allEdges;
    private String name;

    public Graph(ArrayList<Edge> edges, ArrayList<Node> nodes,String name){
        this.nodeEdges = new HashMap<>();
        this.nodes = new HashMap<>();
        for(NodeData node:nodes)
        {
            this.nodes.put(node.getKey(),node);
        }
        for(Integer key: this.nodes.keySet())
        {
            HashMap<String,EdgeData> edgesFrom = new HashMap<>();
            for(EdgeData edge: edges)
            {
                if(edge.getSrc() == key)
                {
                    edgesFrom.put(key +"-"+edge.getDest(),edge);
                }
            }
            this.nodeEdges.put(key,edgesFrom);
        }
        for(Integer key:this.nodeEdges.keySet())
        {
            for(String edgeKey:this.nodeEdges.get(key).keySet())
            {
                Node srcNode = (Node)this.nodes.get(key);
                Node destNode = (Node)this.nodes.get(Integer.parseInt(edgeKey.split("-")[1]));
                srcNode.getFromNode().add(destNode.getKey());
                destNode.getToNode().add(key);
            }
        }
        this.allEdges = new ArrayList<>(edges);
        this.name = name;
    }

    public Graph(Graph other){
        this.nodes = new HashMap<>();
        this.nodeEdges = new HashMap<>();
        for(Integer key:other.getNodeEdges().keySet())
        {
            HashMap<String,EdgeData> edgesTo = new HashMap<>();
            for(String edgeKey:other.getNodeEdges().get(key).keySet())
            {
                edgesTo.put(edgeKey,other.getNodeEdges().get(key).get(edgeKey));
            }
            this.nodeEdges.put(key,edgesTo);
        }
        for(Integer key:other.getNodes().keySet())
        {
            this.nodes.put(key,other.getNodes().get(key));
        }
        this.name = other.getName();
        this.allEdges = new ArrayList<>(other.allEdges);
    }

    public ArrayList<EdgeData> getAllEdges(){
        return this.allEdges;
    }


    public HashMap<Integer, HashMap<String, EdgeData>> getNodeEdges() {
        return nodeEdges;
    }

    public HashMap<Integer,NodeData> getNodes(){
        return this.nodes;
    }
    public String getName(){
        return this.name;
    }

    @Override
    public NodeData getNode(int key) {
        return this.nodes.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        String key = src +"-"+dest;
        return this.nodeEdges.get(src).get(key);
    }

    @Override
    public void addNode(NodeData n) {
        this.nodes.put(n.getKey(),n);
    }

    @Override
    public void connect(int src, int dest, double w) {
        Edge edgeData = new Edge(src,dest,w);
        String key = src + "-" + dest;
        this.nodeEdges.get(src).put(key,edgeData);
        Node source = (Node)this.nodes.get(src);
        Node destination = (Node)this.nodes.get(src);
        source.addEdge(edgeData);
        destination.addEdge(edgeData);
    }

    @Override
    public Iterator<NodeData> nodeIter() throws RuntimeException{
        ArrayList<NodeData> thisNodes = new ArrayList<>();
        for(Integer i:this.nodes.keySet())
        {
            thisNodes.add(this.nodes.get(i));
        }
        return thisNodes.iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter() throws RuntimeException {
        return allEdges.iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) throws RuntimeException{
        HashMap<String, EdgeData> edges = this.nodeEdges.get(node_id);
        return edges.values().iterator();
    }

    @Override
    public NodeData removeNode(int key) {
        Node node = (Node)this.nodes.get(key);
        HashMap<Integer, HashMap<String,EdgeData>> edgesToRemove;
        ArrayList<Integer> nodesConnectedTo = new ArrayList<>();
        nodesConnectedTo.addAll(node.getToNode());
        for(int i = 0; i < nodesConnectedTo.size();i++)
        {
            removeEdge(nodesConnectedTo.get(i),key);
        }
        nodesConnectedTo.clear();
        nodesConnectedTo.addAll(node.getFromNode());
        for(int i = 0; i < nodesConnectedTo.size();i++)
        {
            removeEdge(key,nodesConnectedTo.get(i));
        }
        this.nodes.remove(key);
        this.nodeEdges.remove(key);
        return node;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        EdgeData edge = this.nodeEdges.get(src).get(src+"-"+dest);
        Node nodeSrc = (Node)this.nodes.get(src);
        Node nodeDest = (Node)this.nodes.get(dest);
        nodeSrc.removeEdge(dest, "dest");
        nodeDest.removeEdge(src, "src");
        this.nodeEdges.get(src).remove(src+"-"+dest);
        this.allEdges.remove(edge);
        return edge;
    }

    @Override
    public int nodeSize() {
        return this.nodes.size();
    }

    @Override
    public int edgeSize() {
        return this.nodeEdges.size();
    }

    @Override
    public int getMC() {
        return 0;
    }
}
