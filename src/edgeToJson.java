public class edgeToJson {
    int src;
    double w;
    int dest;

    public edgeToJson(EdgeData edge) {
        this.src = edge.getSrc();
        this.w = edge.getWeight();
        this.dest = edge.getDest();
    }
}
