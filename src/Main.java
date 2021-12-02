public class Main {
    public static void main(String[] args) {
        GraphAlgorithms graph = new GraphAlgorithms();
        graph.load("data/G1.json");
        System.out.println(graph.bfs(0));
        Graph opposite = graph.createOppositeGraph();
        System.out.println("check");
    }
}
