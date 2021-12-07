import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Test_Node {

    //tests for Node class
    @Test
    public void TestGetKey(){
        Node lior = new Node(0, 1.0,2.0,0.0);
        Assert.assertEquals(0, lior.getKey());

        Node talia = new Node(100, 30.0,30.5,0.0);
        Assert.assertEquals(100,talia.getKey());

        Node Dana = new Node(18, 50.0,30.8,0.0);
        Assert.assertEquals(18,Dana.getKey());
    }

    @Test
    public void TestGetLocation(){
        Node lior = new Node(0, 1.0,2.0,0.0);
        Assert.assertEquals(1.0,lior.getLocation().x(),0);
        Assert.assertEquals(2.0,lior.getLocation().y(),0);
        Assert.assertEquals(0.0,lior.getLocation().z(),0);

        Node talia = new Node(100, 30.0,30.5,0.0);
        Assert.assertEquals(30.0,talia.getLocation().x(),0);
        Assert.assertEquals(30.5,talia.getLocation().y(),0);
        Assert.assertEquals(0.0,talia.getLocation().z(),0);

        Node Dana = new Node(18, 50.0,30.8,0.0);
        Assert.assertEquals(50.0,Dana.getLocation().x(),0);
        Assert.assertEquals(30.8,Dana.getLocation().y(),0);
        Assert.assertEquals(0.0,Dana.getLocation().z(),0);
    }

    @Test
    public void TestSetLocation(){
        Node lior = new Node(0, 1.0,2.0,0.0);
        GeoLocation talia = new Location(30.0,30.5,0.0);
        lior.setLocation(talia);
        Assert.assertEquals(30, lior.getLocation().x(),0);
        Assert.assertEquals(30.5, lior.getLocation().y(),0);
        Assert.assertEquals(0.0, lior.getLocation().z(),0);
    }

    @Test
    public void TestGetInfo(){
        Node lior = new Node(0, 1.0,2.0,0.0);
        Assert.assertEquals("Location of node: x = 1.0 y = 2.0 z = 0.0",lior.getInfo());

        Node talia = new Node(100, 30.0,30.5,0.0);
        Assert.assertEquals("Location of node: x = 30.0 y = 30.5 z = 0.0",talia.getInfo());

        Node Dana = new Node(18, 50.0,30.8,0.0);
        Assert.assertEquals("Location of node: x = 50.0 y = 30.8 z = 0.0",Dana.getInfo());
    }

    @Test
    public void TestSetInfo(){
        Node lior = new Node(0, 1.0,2.0,0.0);
        String Dana = "Location of node: x = 50.0 y = 30.8 z = 0.0";
        lior.setInfo(Dana);
        Assert.assertEquals("Location of node: x = 50.0 y = 30.8 z = 0.0",lior.getInfo());
    }

    @Test
    public void TestGetFromNode(){
        Node lior = new Node(0, 1.0,2.0,0.0);
        Node Dana = new Node(18, 50.0,30.8,0.0);
        Edge Braitman = new Edge(lior.getKey(),Dana.getKey(),5);
        lior.addEdge(Braitman);
        ArrayList check = new ArrayList<Integer>(10);
        check.add(18);

        Assert.assertEquals(check,lior.getFromNode());
    }

    @Test
    public void TestGetToNode(){
        Node lior = new Node(0, 1.0,2.0,0.0);
        Node Dana = new Node(18, 50.0,30.8,0.0);
        Edge Breitman = new Edge(Dana.getKey(),lior.getKey(),5);
        lior.addEdge(Breitman);
        ArrayList check = new ArrayList<Integer>(10);
        check.add(18);

        Assert.assertEquals(check,lior.getToNode());
    }

    @Test
    public void TestAddEdge(){
        Node talia = new Node(100, 30.0,30.5,0.0);
        Node Dana = new Node(80, 50.0,30.8,0.0);
        Edge Seada = new Edge(Dana.getKey(),talia.getKey(),5);
        talia.addEdge(Seada);
        ArrayList check = new ArrayList<Integer>(10);
        check.add(80);

        Assert.assertEquals(check,talia.getToNode());
    }

    @Test
    public void TestRemoveEdge(){
        Node talia = new Node(100, 30.0,30.5,0.0);
        Node Dana = new Node(80, 50.0,30.8,0.0);

        Edge Seada = new Edge(Dana.getKey(),talia.getKey(),5);
        talia.addEdge(Seada);

        Node lior = new Node(0, 1.0,2.0,0.0);
        Edge Breitman = new Edge(lior.getKey(),talia.getKey(),5);
        talia.addEdge(Breitman);

        ArrayList check = new ArrayList<Integer>(10);
        check.add(0);

        talia.removeEdge(Dana.getKey(), "src");

        Assert.assertEquals(check,talia.getToNode());
    }
}
