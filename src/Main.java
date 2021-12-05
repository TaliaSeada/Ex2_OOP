import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        GraphAlgorithms graph = new GraphAlgorithms();
        graph.load("data/G1.json");
        System.out.println(graph.bfs(0));
        ArrayList<HashMap> result = graph.Dijkstra(0);
        HashMap<Integer, Node> LastNodes = result.get(1);
        System.out.println(result.get(0).toString());
        for(Integer key:LastNodes.keySet()) {
            if(LastNodes.get(key) == null) {
                System.out.println("source");
            }
            else {
                System.out.println(key + " last Edge from: " +LastNodes.get(key).getKey());

            }
        }
        for(int i =0; i < graph.shortestPath(0,8).size();i++)
        {
            System.out.println(graph.shortestPath(0,8).get(i).getKey());
        }
    }
}
