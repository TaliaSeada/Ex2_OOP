public class Main {
    public static void main(String[] args) {
        GraphAlgorithms graph = new GraphAlgorithms();
        graph.load("C:\\100000Nodes.json");

        long start = System.currentTimeMillis();
        System.out.println(graph.center().getKey());
        long end = System.currentTimeMillis();
        System.out.println("time = " + (end - start));

//        System.out.println(Ex2.getGrapgAlgo(args[0]).getGraph());
//        System.out.println(Ex2.getGrapg(args[0]));
//        Ex2.runGUI(args[0]);
    }
}
