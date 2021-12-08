# Second Assignment - OOP Course
##Table Of Content:
####- Intro

####- Implementation
- Class Node
- Class Edge
- Class Location
- Class nodeToJson
- Class edgeToJson
- Class Graph
- Class GraphAlgorithms



## Intro:
This project is about Weighted Directional Graphs. <br>
More info - https://github.com/benmoshe/OOP_2021/tree/main/Assignments/Ex2/src/api <br>

####The main Task:
The main task of this project is to ...
We achieve it by implementing these four algorithms: <br>

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
- int key : Represents a node's ID number
- GeoLocation loc : Represents a node's location.
- String info : Represents the data about a node.
- ArrayList<Integer> EdgesToNode : holds the edges which goes to a node.
- ArrayList<Integer> EdgesFromNode : holds the edges which goes from a node.
- int tag : Represents the color associated to a node.

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
5. getToNode() - Returns the list of edges that goes to this node.
6. getFromNode() - Returns the list of edges that goes from this node.
7. addEdge(Edge edge) - Adding an edge to one of the lists of nodes according to the edge's src and dest nodes.
8. removeEdge(int node,String type) - Removing an edge from one of the lists according to the edge's src and dest nodes.


###Class nodeToJson:
This class is used to load a node to Json file.


###Class Edge:
This class represents the set of operations applicable on a
directional edge(src,dest) in a (directional) weighted graph.

#####The parameters of the class:
- int src : source node of the edge
- int dest : destination node of the edge
- double w : weight of the edge
- String info : information associated with this edge

#####The functions that we received with the given interface:
1. getSrc() - The ID of the source node of this edge.
2. getDest() - The ID of the destination node of this edge
3. getWeight() - The weight of this edge (positive value).
4. getInfo() - Returns the remark (meta data) associated with this edge.
5. setInfo(String s) - Allows changing the remark (meta data) associated with this edge.

#####The functions that we added to this class (not included in the interface):
1. Edge(int src, int dest, double weight) - Constructor that receives the source and destination nodes and weight.
2. Edge(LinkedTreeMap<?,?> edge) - Constructor that receives Linked Tree Map from a Json File.
3. Edge(Edge other) - Copy constructor.
4. compareTo(Edge o) - compare this edge to another edge.


###Class edgeToJson:
This class is used to load an edge to Json file.


###Class Graph:
This class represents a Directional Weighted Graph

#####The parameters of the class:
- HashMap<Integer,NodeData> nodes : holds all the nodes of this graph
- HashMap<Integer,HashMap<String,EdgeData>> nodeEdges : holds the edges that goes from a node
- ArrayList<EdgeData> allEdges : holds the edges of this graph
- final String name : name of this graph
- int MC =0 : Mode Count

#####The functions that we received with the given interface:
1. getNode(int key) - returns the node_data by the node_id.
2. getEdge(int src, int dest) - returns the data of the edge (src,dest).
3. addNode(NodeData n) - adds a new node to the graph with the given node_data.
4. connect(int src, int dest, double w) - Connects an edge with weight w between node src to node dest. this method run in O(1) time.
5. Iterator<NodeData> nodeIter() - This method returns an Iterator for the collection representing all the nodes in the graph.
6. Iterator<EdgeData> edgeIter() - This method returns an Iterator for all the edges in this graph.
7. Iterator<EdgeData> edgeIter(int node_id) - This method returns an Iterator for edges getting out of the given node (all the edges starting (source) at the given node).
8. removeNode(int key) - Deletes the node (with the given ID) from the graph - and removes all edges which starts or ends at this node. This method run in O(k), V.degree=k, as all the edges removed.
9. removeEdge(int src, int dest) - Deletes the edge from the graph, this method run in O(1) time.
10. nodeSize() - Returns the number of vertices (nodes) in the graph.
11. int edgeSize() - Returns the number of edges (assume directional graph).
12. getMC() - Returns the Mode Count.

#####The functions that we added to this class (not included in the interface):
1. Graph() - Default constructor
2. Graph(ArrayList<Edge> edges, ArrayList<Node> nodes,String name) - constructor that receives the edges, nodes and the name of a graph.
3. Graph(Graph other) - copy constructor.
4. getAllEdges() - Returns all the edges of the graph.
5. getNodeEdges() - Returns all the edges associated with a node.
6. getNodes() - Returns all the nodes of the graph.
7. getName() - Returns the name of the graph.


###Class GraphAlgorithms:
This class represents a Directed (positive) Weighted Graph Theory Algorithms.

#####The parameter of the class:
- Graph graph

#####The functions that we received with the given interface:
1. init(DirectedWeightedGraph g) - Inits the graph on which this set of algorithms operates on.
2. getGraph() - Returns the underlying graph of which this class works.
3. copy() - Computes a deep copy of this weighted graph.
4. isConnected() - Returns true if and only if (iff) there is a valid path from each node to each other node.  
   In this method we used bfs algorithm:
bfs from a node, reverse edges, again bfs from the same node. If we got an integer that is smaller than infinity in both, it means the graph is strongly connected.
5. shortestPathDist(int src, int dest) - Computes the length of the shortest path between src to dest.
   In this method we used Dijkstra algorithm to find the shortest path distance.
6. shortestPath(int src, int dest) - Computes the shortest path between src to dest - as an ordered List of nodes.
   In this method we used Dijkstra algorithm to find the shortest path.
7. center() - Finds the api.NodeData which minimizes the max distance to all the other nodes.
   In this method we iterate over the nodes of the graph and run the Dijkstra algorithm on each one of them.
   then, from each hashMap of distances of every node we got, we need to take the longest path.
   then take the minimum longest path of all the nodes.
8. tsp(List<NodeData> cities) - Computes a list of consecutive nodes which go over all the nodes in cities.
   the sum of the weights of all the consecutive (pairs) of nodes (directed) is the "cost" of the solution -
   the lower the better.
   in this method we iterate over the given list and run the Dijkstra algorithm on the first node.
   After running once on a node, take the shortest path to a node (it's inside the list).
   Then, run again, but now on the node we took from the last iteration.
   The function stops when we passed all the nodes.
9. save(String file) - Saves this weighted (directed) graph to the given file name - in JSON format.
   Here we use the classes "nodeToJson" and "edgeToJson" to save the information into a Json file.
10. load(String file) - This method loads a graph to this graph algorithm.
    if the file was successfully loaded - the underlying graph of this class will be changed (to the loaded one), in case the
    graph was not loaded the original graph should remain "as is".

#####The functions that we added to this class (not included in the interface):
1. Dijkstra(int sourceNode) - This method finds the shortest path between two nodes with using Dijkstra Algorithm.
2. bfs(int nodeKey,Graph graph) - This method runs over all the nodes in the graph using the BFS algorithm, and checks if these nodes have edges connected to them.
   for bfs algorithm, we will change the tags of the graphs:
   - 0 for undiscovered nodes "white".
   - 1 for discovered but not finished "grey".
   - 2 for finished nodes "black".
3. createOppositeGraph() - This method creates an opposite graph of this graph.


