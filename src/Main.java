import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        GraphAlgorithms graph = new GraphAlgorithms();
        graph.load("data/G1.json");
        List<NodeData> check = new ArrayList<>();
        check.add(graph.getGraph().getNode(0));
        check.add(graph.getGraph().getNode(3));
        check.add(graph.getGraph().getNode(8));
        check.add(graph.getGraph().getNode(12));

        //System.out.println(graph.isConnected());
        long start = System.currentTimeMillis();
        List<NodeData> path = graph.tsp(check);
        long finish = System.currentTimeMillis();
        System.out.println("time = " + (finish-start));
        for(int i =0; i < path.size();i++){
            System.out.println(path.get(i).getKey());
        }
        System.out.println("---------");



        path = graph.shortestPath(0,3);
        for(int i =0; i < path.size();i++){
            System.out.println(path.get(i).getKey());
        }
        System.out.println("---------");
        path = graph.shortestPath(3,8);
        for(int i =0; i < path.size();i++){
            System.out.println(path.get(i).getKey());
        }
        System.out.println("---------");
        path = graph.shortestPath(8,12);
        for(int i =0; i < path.size();i++){
            System.out.println(path.get(i).getKey());
        }
        System.out.println("---------");



//        graph.save("data/myGraph.json");


        //graph.load("data/G1.json");
        //graph.save("output.json");
        //long startTime = System.currentTimeMillis();
//        System.out.println(graph.center().getKey());
        //long finishTime = System.currentTimeMillis();
//        System.out.println((finishTime - startTime));

//        boolean con = graph.isConnected();
//        System.out.println(con);

    }
}
