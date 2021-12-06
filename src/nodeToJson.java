public class nodeToJson {
    String pos;
    int id;

    public nodeToJson(NodeData node) {
        this.pos = node.getLocation().toString();
        this.id = node.getKey();
    }
}
