import api.EdgeData;

public class edgeToJson {
    private int src;
    private double w;
    private int dest;

    public edgeToJson(EdgeData edge) {
        this.src = edge.getSrc();
        this.w = edge.getWeight();
        this.dest = edge.getDest();
    }

    public int getSrc(){
        return this.src;
    }

    public double w(){
        return this.w;
    }

    public int getDest(){
        return this.dest;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public void setW(double w) {
        this.w = w;
    }

    public void setDest(int dest) {
        this.dest = dest;
    }

    public String toString(){
        return this.src + "," + this.w + "," + this.dest;
    }
}
