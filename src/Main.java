import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        GraphAlgorithms graph = new GraphAlgorithms();
        Graph myBuild = new Graph();
        NodeData node = new Node(0,1,2,0);
        myBuild.addNode(node);
        node = new Node(1,7,15,0);
        myBuild.addNode(node);
        node = new Node(2,4,1,0);
        myBuild.addNode(node);
        node = new Node(3,19,10,0);
        myBuild.addNode(node);
        node = new Node(4,15,32,0);
        myBuild.addNode(node);
        node = new Node(5,3,3,0);
        myBuild.addNode(node);
        node = new Node(6,2,0,0);
        myBuild.addNode(node);
        node = new Node(7,10,20,0);
        myBuild.addNode(node);
        node = new Node(8,15,17,0);
        myBuild.addNode(node);
        node = new Node(9,10,2,0);
        myBuild.addNode(node);


        myBuild.connect(0,6,3);
        myBuild.connect(0,4,44);
        myBuild.connect(4,7,17);
        myBuild.connect(4,8,15);
        myBuild.connect(8,1,10);
        myBuild.connect(8,3,11);
        myBuild.connect(1,9,16);
        myBuild.connect(9,5,15);
        myBuild.connect(9,2,7);
        myBuild.connect(5,6,4);
        myBuild.connect(6,2,3);
        myBuild.connect(2,1,17);
        myBuild.connect(1,3,17);
        myBuild.connect(9,3,17);
        myBuild.connect(9,7,18);
        myBuild.connect(7,4,17);
        myBuild.connect(7,8,8);
        myBuild.connect(8,9,20);
        myBuild.connect(1,0,19);
        myBuild.connect(3,4,26);
        myBuild.connect(3,0,25);
        myBuild.connect(3,5,26);





        graph.init(myBuild);
        graph.save("data/myGraph.json");


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
