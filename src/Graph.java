import com.google.gson.*;

import java.util.ArrayList;
import java.util.Iterator;

public class Graph implements DirectedWeightedGraph{
    private ArrayList<Edge> edges;
    private ArrayList<Node> nodes;
    private String name;

    public Graph(ArrayList<Edge> edges, ArrayList<Node> nodes,String name){
        this.edges.addAll(edges);
        this.nodes.addAll(nodes);
        this.name = name;
    }

    public Graph(Graph other){
        this.edges = new ArrayList<Edge>(other.getEdges());
        this.nodes = new ArrayList<Node>(other.getNodes());
        this.name = other.getName();
    }



    public ArrayList<Edge> getEdges(){
        return this.edges;
    }

    public ArrayList<Node> getNodes(){
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
