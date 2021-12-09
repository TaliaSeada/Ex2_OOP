public class Main {
    public static void main(String[] args) {
        GraphAlgorithms graph = new GraphAlgorithms();
//        graph.load("C:\\10000Nodes.json");
       graph.load("data/G4.json");
       graph.getGraph().removeNode(362);
        graph.getGraph().removeNode(56);
        graph.getGraph().removeNode(320);
        graph.getGraph().removeNode(264);
        graph.getGraph().removeNode(407);
        graph.getGraph().removeNode(288);
        graph.getGraph().removeNode(325);
        graph.getGraph().removeNode(645);
        graph.getGraph().removeNode(55);
        graph.getGraph().removeNode(203);









        System.out.println(graph.center().getKey());
        //System.out.println(args[0]);
    }
}
