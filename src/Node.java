public class Node implements NodeData{
    private int key;
    private Location loc;
    private String info;
    private int tag;
//    private double weight;

    public Node(int key,int x, int y, int z){
        this.loc = new Location(x,y,z);
        this.key = key;
        this.info = "Location of node: x = " + x + " y = " + y + " z = "+ z;
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
