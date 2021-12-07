import api.NodeData;

public class nodeToJson {
    private String pos;
    private int id;

    public nodeToJson(NodeData node) {
        this.pos = node.getLocation().toString();
        this.id = node.getKey();
    }

    public String getPos(){
        return this.pos;
    }

    public int getId(){
        return this.id;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public void setId(int id){
        this.id = id;
    }
    public String toString(){
        return this.id + "," + this.pos;
    }
}
