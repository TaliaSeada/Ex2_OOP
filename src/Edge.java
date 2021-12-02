import com.google.gson.internal.LinkedTreeMap;

public class Edge implements EdgeData, Comparable<Edge>{
    private int src;
    private int dest;
    private double weight;
    private String info;
//    private int tag;

    public Edge(int src, int dest, double weight){
        this.dest = dest;
        this.src = src;
        this.weight = weight;
        this.info = "source of edge: " + src +" destination of edge: " + dest + " weight of edge "+ weight;
    }

    public Edge(LinkedTreeMap<?,?> edge){
        String dest = edge.get("dest").toString();
        String src =  edge.get("src").toString();
        String w = edge.get("w").toString();
        this.dest = (int)Double.parseDouble(dest);
        this.src = (int)Double.parseDouble(src);
        this.weight = Double.parseDouble(w);
        this.info = "source of edge: " + src +" destination of edge: " + dest + " weight of edge "+ this.weight;

    }

    public Edge(Edge other){
        this.dest = other.getDest();
        this.src = other.getSrc();
        this.weight = other.getWeight();
        this.info = other.getInfo();
    }


    @Override
    public int getSrc() {
        return this.src;
    }

    @Override
    public int getDest() {
        return this.dest;
    }

    @Override
    public double getWeight() {
        return this.weight;
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

    @Override
    public int compareTo(Edge o) {
        if(this.weight > o.getWeight())
        {
            return 1;
        }
        else if(this.weight < o.getWeight())
        {
            return -1;
        }
        return 0;
    }
}
