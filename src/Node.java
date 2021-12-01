import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;

public class Node implements NodeData{
    private int key;
    private GeoLocation loc;
    private String info;
    private ArrayList<Integer> EdgeTo;
    private ArrayList<Integer> EdgeFrom;
    private int tag; // 0 - white, 1 - gray, 2 - black
//    private double weight;

    public Node(int key,double x, double y, double z){
        this.EdgeFrom = new ArrayList<>();
        this.EdgeTo = new ArrayList<>();
        this.loc = new Location(x,y,z);
        this.key = key;
        this.info = "Location of node: x = " + x + " y = " + y + " z = "+ z;
    }

    public Node(LinkedTreeMap<?,?> node) {
        this.EdgeFrom = new ArrayList<>();
        this.EdgeTo = new ArrayList<>();
        String pos =node.get("pos").toString();
        String id = node.get("id").toString();
        System.out.println(pos);
        System.out.println(id);
        String[] posValues = pos.split(",");
        this.key = Integer.parseInt(id);
        Location loc = new Location(Double.parseDouble(posValues[0]),Double.parseDouble(posValues[1]),Double.parseDouble(posValues[2]));
        this.loc = loc;
        this.info = "Location of node #"+ this.key +" : x = " + loc.x() + " y = " + loc.y() + " z = "+ loc.z();
        this.tag = 0;
    }

    public Node(Node other){
        this.key = other.getKey();
        this.loc = new Location(other.getLocation().x(),other.getLocation().y(),other.getLocation().z());
        this.info = other.getInfo();
        this.EdgeFrom = new ArrayList<Integer>(other.getFromNode());
        this.EdgeTo = new ArrayList<Integer>(other.getToNode());
        this.tag = other.getTag();
    }

    public ArrayList<Integer> getToNode(){
        return this.EdgeTo;
    }
    public ArrayList<Integer> getFromNode(){
        return this.EdgeFrom;
    }
    public void addEdge(Edge edge) {
        if(edge.getSrc() == this.key)
        {
            EdgeFrom.add(edge.getDest());
        }
        else if(edge.getDest() == this.key)
        {
            EdgeTo.add(edge.getSrc());
        }
    }


    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public GeoLocation getLocation() {
        return this.loc;
    }

    @Override
    public void setLocation(GeoLocation p) {
        this.loc = (Location) p;
    }

    @Override
    public double getWeight() {
        return 0;
    }

    @Override
    public void setWeight(double w) {
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {
        this.info = s;
    }

    @Override
    public int getTag() {
        return 0;
    }

    @Override
    public void setTag(int t) {

    }
}
