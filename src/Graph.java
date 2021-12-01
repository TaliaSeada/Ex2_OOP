
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Graph implements DirectedWeightedGraph{
    private HashMap<Integer,NodeData> nodes;
    private  ArrayList<Edge> edges;
    private String name;

    public Graph(ArrayList<Edge> edges, ArrayList<Node> nodes,String name){
        this.edges = new ArrayList<>();
        this.nodes = new HashMap<>();
        this.edges.addAll(edges);
        for(NodeData node:nodes)
        {
            this.nodes.put(node.getKey(),node);
        }
        this.name = name;
    }

    public Graph(Graph other){
        this.edges = new ArrayList<Edge>(other.getEdges());
        for(Integer key:other.getNodes().keySet())
        {
            this.nodes.put(key,other.getNodes().get(key));
        }
        this.name = other.getName();
    }



    public ArrayList<Edge> getEdges(){
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
        return null;
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        for(Edge edge:this.edges){
            if(edge.getDest() == dest && edge.getSrc() == dest){
                return edge;
            }
        }
        return null;
    }

    @Override
    public void addNode(NodeData n) {

    }

    @Override
    public void connect(int src, int dest, double w) {

    }

    @Override
    public Iterator<NodeData> nodeIter() {
        return null;
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        return null;
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        return null;
    }

    @Override
    public NodeData removeNode(int key) {
        return null;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        return null;
    }

    @Override
    public int nodeSize() {
        return 0;
    }

    @Override
    public int edgeSize() {
        return 0;
    }

    @Override
    public int getMC() {
        return 0;
    }
}
