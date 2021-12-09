import api.NodeData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        GraphAlgorithms graph = new GraphAlgorithms();
//        graph.load("C:\\10000Nodes.json");
        graph.load("data/G1.json");
       graph.getGraph().removeNode(3);
//        List<NodeData> check = new ArrayList<>();
//        check.add(graph.getGraph().getNode(0));
//        check.add(graph.getGraph().getNode(3));
//        check.add(graph.getGraph().getNode(8));
//        check.add(graph.getGraph().getNode(12));

        //System.out.println(graph.isConnected());
        long start = System.currentTimeMillis();
        try
        {
            System.out.println(graph.center().getKey());
        }
        catch (NullPointerException e)
        {
            System.out.println("graph not connected");
        }
        long finish = System.currentTimeMillis();
        System.out.println("time = " + (finish-start));
//        for(int i =0; i < path.size();i++){
//            System.out.println(path.get(i).getKey());
//        }





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
