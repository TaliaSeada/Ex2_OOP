import org.junit.Assert;
import org.junit.Test;

public class Test_Edge {

    @Test
    public void TestGetSrc(){
        Node talia = new Node(100, 30.0,30.5,0.0);
        Node Dana = new Node(80, 50.0,30.8,0.0);

        Edge Seada = new Edge(Dana.getKey(),talia.getKey(),5);

        Assert.assertEquals(80,Seada.getSrc());
    }

    @Test
    public void TestGetDest(){
        Node talia = new Node(100, 30.0,30.5,0.0);
        Node Dana = new Node(80, 50.0,30.8,0.0);

        Edge Seada = new Edge(Dana.getKey(),talia.getKey(),5);

        Assert.assertEquals(100,Seada.getDest());
    }

    @Test
    public void TestGetWeight(){
        Node talia = new Node(100, 30.0,30.5,0.0);
        Node Dana = new Node(80, 50.0,30.8,0.0);

        Edge Seada = new Edge(Dana.getKey(),talia.getKey(),5);

        Assert.assertEquals(5,Seada.getWeight(),0);
    }

    @Test
    public void TestGetInfo(){
        Node talia = new Node(100, 30.0,30.5,0.0);
        Node Dana = new Node(80, 50.0,30.8,0.0);

        Edge Seada = new Edge(Dana.getKey(),talia.getKey(),5);

        String check = "source of edge: " + 80 +" destination of edge: " + 100 + " weight of edge "+ 5.0;
        Assert.assertEquals(check,Seada.getInfo());
    }

    @Test
    public void TestSetInfo(){
        Node talia = new Node(100, 30.0,30.5,0.0);
        Node Dana = new Node(80, 50.0,30.8,0.0);

        Edge Seada = new Edge(Dana.getKey(),talia.getKey(),5);

        String check = "source of edge: " + 5 +" destination of edge: " + 10 + " weight of edge "+ 3.0;

        Edge check_edge = new Edge(5,10,3.0);
        Seada.setInfo(check);
        Assert.assertEquals(check_edge.getInfo() , Seada.getInfo());
    }
}
