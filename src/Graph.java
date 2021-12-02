
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Graph implements DirectedWeightedGraph{
    private HashMap<Integer,NodeData> nodes;
    private HashMap<String,EdgeData> edges;
    private String name;

    public Graph(ArrayList<Edge> edges, ArrayList<Node> nodes,String name){
        this.edges = new HashMap<>();
        this.nodes = new HashMap<>();
        for(EdgeData edge:edges)
        {
            String key = edge.getSrc()+ "-" +edge.getDest();
            this.edges.put(key,edge);
        }
        for(NodeData node:nodes)
        {
            this.nodes.put(node.getKey(),node);
        }
        for(String key:this.edges.keySet())
        {
            EdgeData edge = this.edges.get(key);
            Node src = (Node)this.nodes.get(edge.getSrc());
            Node dest = (Node)this.nodes.get(edge.getDest());
            src.addEdge((Edge) edge);
            dest.addEdge((Edge) edge);
        }
        this.name = name;
    }

    public Graph(Graph other){
        this.edges = new HashMap<String,EdgeData>(other.getEdges());
        for(Integer key:other.getNodes().keySet())
        {
            this.nodes.put(key,other.getNodes().get(key));
        }
        this.name = other.getName();
    }



    public HashMap<String, EdgeData> getEdges(){
        return this.edges;
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
        String key = src+"-" +dest;
        return this.edges.get(key);
    }

    @Override
    public void addNode(NodeData n) {
        this.nodes.put(n.getKey(),n);
    }

    @Override
    public void connect(int src, int dest, double w) {
        Edge edgeData = new Edge(src,dest,w);
        String key = "" + src + dest;
        this.edges.put(key,edgeData);
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
        ArrayList<EdgeData> thisEdge = new ArrayList<>();
        for(String key:this.edges.keySet())
        {
            thisEdge.add(this.edges.get(key));
        }
        return thisEdge.iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) throws RuntimeException{
        ArrayList<EdgeData> nodeFromEdges = new ArrayList<>();
        for (String Key:this.edges.keySet()) {
            String[] nodes = Key.split("-");
            if (Integer.parseInt(nodes[0]) == node_id) {
                nodeFromEdges.add(this.edges.get(Key));
            }
        }
        return nodeFromEdges.iterator();
    }

    @Override
    public NodeData removeNode(int key) {
        Node node = (Node)this.nodes.remove(key);
        for(Integer other:node.getToNode())
        {
            removeEdge(other,key);
        }
        for(Integer other:node.getFromNode())
        {
            removeEdge(key,other);
        }
        return node;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        EdgeData edge = this.edges.remove(src+"-"+dest);
        Node nodeSrc = (Node)this.nodes.get(src);
        Node nodeDest = (Node)this.nodes.get(dest);
        nodeSrc.removeEdge(dest, "dest");
        nodeDest.removeEdge(src, "src");

        return edge;
    }

    @Override
    public int nodeSize() {
        return this.nodes.size();
    }

    @Override
    public int edgeSize() {
        return this.edges.size();
    }

    @Override
    public int getMC() {
        return 0;
    }
}
