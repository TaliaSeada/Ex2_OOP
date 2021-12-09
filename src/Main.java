public class Main {
    public static void main(String[] args) {
        GraphAlgorithms graph = new GraphAlgorithms();
//        graph.load("C:\\10000Nodes.json");
       graph.load("data/G3.json");
       graph.getGraph().removeNode(40);
        System.out.println(graph.dijkstra(1).get(0));
        //System.out.println(args[0]);
    }
}
