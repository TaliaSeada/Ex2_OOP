# Second Assignment - OOP Course
## Table Of Content:
### Credits
### Intro
### Implementation
- Class Node
- Class Edge
- Class Location
- Class nodeToJson
- Class edgeToJson
- Class Graph
- Class GraphAlgorithms
- Class GUI
- Class myBoxLayout
- Class showGraph
- Class minHeap
- Class pair
### Results
### How to interact with the project
### Tests


## Credits:
This Projects was made by: Talia Seada (ID:), Lior Breitman (ID:), Dana Zorohov (ID: 207817529)

## Intro:
This project is about Weighted Directional Graphs. <br>
More info - https://github.com/benmoshe/OOP_2021/tree/main/Assignments/Ex2/src/api <br>

### The main Task:
The main task of this project is to ...
We achieve it by implementing these four algorithms: <br>

#### 1. shortestPath: 
In this algorithm we need to find the shortest path between two given nodes (return weight of path or list of nodes in this path).

#### 2. center:
In this algorithm we need to find the center node of a graph. 

#### 3. isConnected:
In this algorithm we need to find if there is a path between every two nodes in the graph.

#### 4. tsp:
Traveling Sales Man problem.


## Implementation:

First we implemented the Node, Edge and the Location classes.
Afterwards we implemented the Graph and GraphAlgorithms classes.


### Class Location:
This class represents a geographic location <x,y,z>, (aka Point3D data).

#### The parameters of the class:
- double x
- double y
- double z

#### The functions that we received with the given interface:
1. __x()__ - Returns x value of the location.
2. __y()__ - Returns y value of the location.
3. __z()__ - Returns z value of the location.
4. __distance(GeoLocation g)__ - Calculates the distance between this location to a given location.

#### The functions that we added to this class (not included in the interface):
1. __Location(double x, double y, double z)__ - Constructor.
2. __toString()__ - Returns string with x,y,z values.


### Class Node:
This class represents the set of operations applicable on a
node (vertex) in a (directional) weighted graph.

#### The parameters of the class:
- __int key__ : Represents a node's ID number
- __GeoLocation loc__ : Represents a node's location.
- __String info__ : Represents the data about a node.
- __ArrayList<Integer> EdgesToNode__ : holds the edges which goes to a node.
- __ArrayList<Integer> EdgesFromNode__ : holds the edges which goes from a node.
- __int tag__ : Represents the color associated to a node.

#### The functions that we received with the given interface:
1. __getKey()__ - Returns the ID of this node.
2. __getLocation()__ - Returns the location of this node.
3. __setLocation(GeoLocation p)__ - Setting new location to this node.
4. __getInfo()__ - Returns the remark (meta data) associated with this node.
5. __setInfo(String s)__ - Setting new remark (meta data) that will be associated with this node.
6. __getTag()__ - Returns the tag (Temporal data) of this node.
We defined tag to be colors: 0 - for Undiscovered nodes "white", 1- for discovered but not finished "grey", 2- for finished nodes "black".
7. __setTag(int t)__- Setting new tag to this node.

#### The functions that we added to this class (not included in the interface):
1. __Node(int key, GeoLocation location)__ - Constructor that receives ID number and location.
2. __Node(int key,double x, double y, double z)__ - Constructor that receives ID number and location's x,y,z values.
3. __Node(LinkedTreeMap<?,?> node)__ - Constructor that receives Linked Tree Map from a Json File.
4. __Node(Node other)__ - Copy constructor.
5. __getToNode()__ - Returns the list of edges that goes to this node.
6. __getFromNode()__ - Returns the list of edges that goes from this node.
7. __addEdge(Edge edge)__ - Adding an edge to one of the lists of nodes according to the edge's src and dest nodes.
8. __removeEdge(int node,String type)__ - Removing an edge from one of the lists according to the edge's src and dest nodes.


### Class nodeToJson:
This class is used to load a node to Json file.


### Class Edge:
This class represents the set of operations applicable on a
directional edge(src,dest) in a (directional) weighted graph.

#### The parameters of the class:
- __int src__ : source node of the edge
- __int dest__ : destination node of the edge
- __double w__ : weight of the edge
- __String info__ : information associated with this edge

#### The functions that we received with the given interface:
1. __getSrc()__ - The ID of the source node of this edge.
2. __getDest()__ - The ID of the destination node of this edge
3. __getWeight()__ - The weight of this edge (positive value).
4. __getInfo()__ - Returns the remark (meta data) associated with this edge.
5. __setInfo(String s)__ - Allows changing the remark (meta data) associated with this edge.

#### The functions that we added to this class (not included in the interface):
1. __Edge(int src, int dest, double weight)__ - Constructor that receives the source and destination nodes and weight.
2. __Edge(LinkedTreeMap<?,?> edge)__ - Constructor that receives Linked Tree Map from a Json File.
3. __Edge(Edge other)__ - Copy constructor.
4. __compareTo(Edge o)__ - compare this edge to another edge.


### Class edgeToJson:
This class is used to load an edge to Json file.


### Class Graph:
This class represents a Directional Weighted Graph

#### The parameters of the class:
- __HashMap<Integer,NodeData> nodes__ : holds all the nodes of this graph
- __HashMap<Integer,HashMap<String,EdgeData>> nodeEdges__ : holds the edges that goes from a node
- __ArrayList<EdgeData> allEdges__ : holds the edges of this graph
- __final String name__ : name of this graph
- __int MC =0__ : Mode Count

#### The functions that we received with the given interface:
1. __getNode(int key)__ - returns the node_data by the node_id.
2. __getEdge(int src, int dest)__ - returns the data of the edge (src,dest).
3. __addNode(NodeData n)__ - adds a new node to the graph with the given node_data.
4. __connect(int src, int dest, double w)__ - Connects an edge with weight w between node src to node dest. this method run in O(1) time.
5. __Iterator<NodeData> nodeIter()__ - This method returns an Iterator for the collection representing all the nodes in the graph.
6. __Iterator<EdgeData> edgeIter()__ - This method returns an Iterator for all the edges in this graph.
7. __Iterator<EdgeData> edgeIter(int node_id)__ - This method returns an Iterator for edges getting out of the given node (all the edges starting (source) at the given node).
8. __removeNode(int key)__ - Deletes the node (with the given ID) from the graph - and removes all edges which starts or ends at this node. This method run in O(k), V.degree=k, as all the edges removed.
9. __removeEdge(int src, int dest)__ - Deletes the edge from the graph, this method run in O(1) time.
10. __nodeSize()__ - Returns the number of vertices (nodes) in the graph.
11. __int edgeSize()__ - Returns the number of edges (assume directional graph).
12. __getMC()__ - Returns the Mode Count.

#### The functions that we added to this class (not included in the interface):
1. __Graph()__ - Default constructor
2. __Graph(ArrayList<Edge> edges, ArrayList<Node> nodes,String name)__ - constructor that receives the edges, nodes and the name of a graph.
3. __Graph(Graph other)__ - copy constructor.
4. __getAllEdges()__ - Returns all the edges of the graph.
5. __getNodeEdges()__ - Returns all the edges associated with a node.
6. __getNodes()__ - Returns all the nodes of the graph.
7. __getName()__ - Returns the name of the graph.


### Class GraphAlgorithms:
This class represents a Directed (positive) Weighted Graph Theory Algorithms.

#### The parameter of the class:
- Graph graph

#### The functions that we received with the given interface:
1. __init(DirectedWeightedGraph g)__ - Inits the graph on which this set of algorithms operates on.
2. __getGraph()__ - Returns the underlying graph of which this class works.
3. __copy()__ - Computes a deep copy of this weighted graph.
4. __isConnected()__ - Returns true if and only if (iff) there is a valid path from each node to each other node.  
   In this method we used bfs algorithm:
bfs from a node, reverse edges, again bfs from the same node. If we got an integer that is smaller than infinity in both, it means the graph is strongly connected.
5. __shortestPathDist(int src, int dest)__ - Computes the length of the shortest path between src to dest.
   In this method we used Dijkstra algorithm to find the shortest path distance.
6. __shortestPath(int src, int dest)__ - Computes the shortest path between src to dest - as an ordered List of nodes.
   In this method we used Dijkstra algorithm to find the shortest path.
7. __center()__ - Finds the api.NodeData which minimizes the max distance to all the other nodes.
   In this method we iterate over the nodes of the graph and run the Dijkstra algorithm on each one of them.
   then, from each hashMap of distances of every node we got, we need to take the longest path.
   then take the minimum longest path of all the nodes.
8. __tsp(List<NodeData> cities)__ - Computes a list of consecutive nodes which go over all the nodes in cities.
   the sum of the weights of all the consecutive (pairs) of nodes (directed) is the "cost" of the solution -
   the lower the better.
   in this method we iterate over the given list and run the Dijkstra algorithm on the first node.
   After running once on a node, take the shortest path to a node (it's inside the list).
   Then, run again, but now on the node we took from the last iteration.
   The function stops when we passed all the nodes.
9. __save(String file)__ - Saves this weighted (directed) graph to the given file name - in JSON format.
   Here we use the classes "nodeToJson" and "edgeToJson" to save the information into a Json file.
10. __load(String file)__ - This method loads a graph to this graph algorithm.
    if the file was successfully loaded - the underlying graph of this class will be changed (to the loaded one), in case the
    graph was not loaded the original graph should remain "as is".

#### The functions that we added to this class (not included in the interface):
1. __Dijkstra(int sourceNode)__ - This method finds the shortest path between two nodes with using Dijkstra Algorithm.
2. __bfs(int nodeKey,Graph graph)__ - This method runs over all the nodes in the graph using the BFS algorithm, and checks if these nodes have edges connected to them.
   for bfs algorithm, we will change the tags of the graphs:
   - 0 for undiscovered nodes "white".
   - 1 for discovered but not finished "grey".
   - 2 for finished nodes "black".
3. __createOppositeGraph()__ - This method creates an opposite graph of this graph.


### Class GUI:
This class is used to draw a graph.

#### The function in this class:
__createMainWindow(String path)__ - In this function we create the main window.


### Class myBoxLayout:
In this class we create the appearance of the main window.

#### The parameter of the class:
- GraphAlgorithms GA = new GraphAlgorithms()

#### The functions in this class:
1. __createMainWindow(Container pane, String path)__ - In this method we create the main window.
2. __CreateAlgoWindow()__ - In this method we create the algorithms window.
3. __CreateFileWindow()__ - In this method we create the file window.
4. __CreateGraphWindow()__ - In this method we create the graph window.
5. __actionPerformed(ActionEvent e)__ - In this method we receive the action that occurs (what button was pushed) and by the event we make the action to happen.


### Class showGraph:
In this class we display a graph in the graph's window.
//to do

### Class minHeap:
//to do

### Class pair:
//to do

## How to interact with the project:
### Download:
In order to download the project 
//to do

## Run:
In order to run the project 
//to do

## GUI:
In order to use GUI
//to do

## Tests:
//to do

