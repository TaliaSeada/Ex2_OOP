import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        GraphAlgorithms graph = new GraphAlgorithms();
        graph.load("data/G1.json");


        //System.out.println(graph.isConnected());
        long start = System.currentTimeMillis();
        System.out.println(graph.isConnected());
        System.out.println(graph.isConnected());
        long finish = System.currentTimeMillis();
        System.out.println("time = " + (finish-start));
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
