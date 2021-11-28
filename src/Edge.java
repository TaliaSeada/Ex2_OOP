public class Edge implements EdgeData{
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
        this.info = info;

    }

    @Override
    public int getTag() {
        return 0;
    }

    @Override
    public void setTag(int t) {

    }
}
