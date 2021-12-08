# Second Assignment - OOP Course
##Table Of Content:
####- Intro
####- Implementation
- Class Node
- Class Edge
- Class Location
## Intro:
This project is about Weighted Directional Graphs. <br>
More info - https://github.com/benmoshe/OOP_2021/tree/main/Assignments/Ex2/src/api <br>
####The main Task:
The main task of this project is to implement these four algorithms: <br>
#####1. shortestPath: 
In this algorithm we need to find the shortest path between two given nodes (return weight of path or list of nodes in this path).
#####2. center:
In this algorithm we need to find the center node of a graph. 
#####3. isConnected:
In this algorithm we need to find if there is a path between every two nodes in the graph.
#####4. tsp:
Traveling Sales Man problem.

##Implementation:

First we implemented the Node, Edge and the Location classes.
Afterwards we implemented the Graph and GraphAlgorithms classes.
###Class Location:
This class represents a geographic location <x,y,z>, (aka Point3D data).
#####The parameters of the class:
- double x
- double y
- double z

#####The functions that we received with the given interface:
1. x() - Returns x value of the location.
2. y() - Returns y value of the location.
3. z() - Returns z value of the location.
4. distance(GeoLocation g) - Calculates the distance between this location to a given location.

#####The functions that we added to this class (not included in the interface):
1. Location(double x, double y, double z) - Constructor.
2. toString() - Returns string with x,y,z values.


###Class Node:
This class represents the set of operations applicable on a
node (vertex) in a (directional) weighted graph.

#####The parameters of the class:
- int key : Integer that represents a node's ID number
- GeoLocation loc : GeoLocation (Object) that represents a node's location.
- String info : String that represents the data about a node.
- ArrayList<Integer> EdgesToNode : ArrayList of integers that holds the edges which goes to a node.
- ArrayList<Integer> EdgesFromNode : ArrayList of integers that holds the edges which goes from a node.
- int tag : Integer that represents the color associated to a node.

#####The functions that we received with the given interface:
1. getKey() - Returns the ID of this node.
2. getLocation() - Returns the location of this node.
3. setLocation(GeoLocation p) - Setting new location to this node.
4. getInfo() - Returns the remark (meta data) associated with this node.
5. setInfo(String s) - Setting new remark (meta data) that will be associated with this node.
6. getTag() - Returns the tag (Temporal data) of this node.
We defined tag to be colors: 0 - for Undiscovered nodes "white", 1- for discovered but not finished "grey", 2- for finished nodes "black".
7. setTag(int t)- Setting new tag to this node.

#####The functions that we added to this class (not included in the interface):
1. Node(int key, GeoLocation location) - Constructor that receives ID number and location.

2. Node(int key,double x, double y, double z) - Constructor that receives ID number and location's x,y,z values.

3. Node(LinkedTreeMap<?,?> node) - Constructor that receives Linked Tree Map from a Json File.

4. Node(Node other) - Copy constructor.

5. ArrayList<Integer> getToNode() - Returns the list of edges that goes to this node.

6. ArrayList<Integer> getFromNode() - Returns the list of edges that goes from this node.

7. addEdge(Edge edge) - Adding an edge to one of the lists of nodes according to the edge's src and dest nodes.

8. removeEdge(int node,String type) - Removing an edge from one of the lists according to the edge's src and dest nodes.

###Class Edge:
This class represents the set of operations applicable on a
directional edge(src,dest) in a (directional) weighted graph.

#####The parameters of the class:
- int src : source node of the edge
- int dest : destination node of the edge
- double w : weight of the edge
- String info : information associated with this edge

#####The functions that we received with the given interface:
- getSrc() - The ID of the source node of this edge.
- getDest() - The ID of the destination node of this edge
- getWeight() - The weight of this edge (positive value).
- getInfo() - Returns the remark (meta data) associated with this edge.
- setInfo(String s) - Allows changing the remark (meta data) associated with this edge.

#####The functions that we added to this class (not included in the interface):
- Edge(int src, int dest, double weight) - Constructor that receives the source and destination nodes and weight.
- Edge(LinkedTreeMap<?,?> edge) - Constructor that receives Linked Tree Map from a Json File.
- Edge(Edge other) - Copy constructor.
- compareTo(Edge o) - compare this edge to another edge.

###Class edgeToJson:
This class is used to load an edge to Json file.
