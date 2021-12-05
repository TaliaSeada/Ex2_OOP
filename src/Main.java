import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        GraphAlgorithms graph = new GraphAlgorithms();
        graph.load("data/G1.json");
        System.out.println(graph.bfs(0));
        ArrayList<HashMap> result = graph.Dijkstra(0);
        System.out.println(result.get(0).toString());
    }
}
