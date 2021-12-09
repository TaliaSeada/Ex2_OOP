import api.EdgeData;
import api.GeoLocation;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class myBoxLayout extends JFrame implements ActionListener {
    private GraphAlgorithms GA = new GraphAlgorithms();
    List<NodeData> cities = new ArrayList<>();

    // Buttons
    JButton fileActions = new JButton("file");
    JButton graphActions = new JButton("Graph");
    JButton algoActions = new JButton("algorithms");

    JButton save = new JButton("save to file");
    JButton show = new JButton("show graph");
    JButton AddNode = new JButton("Add node");
    JButton RemoveNode = new JButton("remove Node");
    JButton Connect2Nodes = new JButton("Connect between 2 nodes");
    JButton removeEdges = new JButton("Disconnect 2 nodes");

    JButton isConnected = new JButton("Check if the graph is connected");
    JButton shortestPathDist = new JButton("Get the shortest path distance between two nodes");
    JButton shortestPath = new JButton("Show the shortest path on the graph");
    JButton center = new JButton("Show the center node on the graph");
    JButton tsp_path = new JButton("insert nodes for TSP");
    JButton drawTSP = new JButton("Draw TSP path");

    // texts
    JTextField filePath = new JTextField("enter path to file");

    // frames
    JFrame fileFrame = new JFrame("file actions window");
    JFrame graphFrame = new JFrame("graph window");
    JFrame algoFrame = new JFrame("Algorithm");
    Container cont;

    //layouts
    GroupLayout fileLayout = new GroupLayout(fileFrame.getContentPane());
    BoxLayout graphLayout = new BoxLayout(graphFrame.getContentPane(), BoxLayout.Y_AXIS);
    BoxLayout algoLayout = new BoxLayout(algoFrame.getContentPane(), BoxLayout.Y_AXIS);


    public void createMainWindow(Container pane, String path) {
        cont = pane;
        GA.load(path);
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));


        fileActions.setAlignmentX(Component.CENTER_ALIGNMENT);
        fileActions.addActionListener(this);
        pane.add(fileActions);
        pane.add(Box.createRigidArea(new Dimension(5, 20)));


        graphActions.setAlignmentX(Component.CENTER_ALIGNMENT);
        graphActions.addActionListener(this);
        pane.add(graphActions);
        pane.add(Box.createRigidArea(new Dimension(5, 20)));


        algoActions.setAlignmentX(Component.CENTER_ALIGNMENT);
        algoActions.addActionListener(this);
        pane.add(algoActions);
        pane.add((Box.createRigidArea(new Dimension(5, 20))));
        CreateAlgoWindow();
        CreateFileWindow();
        CreateGraphWindow();
        fileFrame.setVisible(false);
        algoFrame.setVisible(false);
        graphFrame.setVisible(false);
    }

    public void CreateAlgoWindow() {
        algoFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        algoFrame.setSize(500, 500);
        algoFrame.setLocationRelativeTo(null);

        algoFrame.getContentPane().setLayout(algoLayout);

        isConnected.setAlignmentX(Component.CENTER_ALIGNMENT);
        isConnected.addActionListener(this);
        algoFrame.getContentPane().add(isConnected);

        shortestPathDist.setAlignmentX(Component.CENTER_ALIGNMENT);
        shortestPathDist.addActionListener(this);
        algoFrame.getContentPane().add(shortestPathDist);

        shortestPath.setAlignmentX(Component.CENTER_ALIGNMENT);
        shortestPath.addActionListener(this);
        algoFrame.getContentPane().add(shortestPath);


        center.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.addActionListener(this);
        algoFrame.getContentPane().add(center);


        tsp_path.setAlignmentX(Component.CENTER_ALIGNMENT);
        tsp_path.addActionListener(this);
        algoFrame.getContentPane().add(tsp_path);

        drawTSP.setAlignmentX(Component.CENTER_ALIGNMENT);
        drawTSP.addActionListener(this);
        algoFrame.getContentPane().add(drawTSP);

        algoFrame.setVisible(true);
    }

    public void CreateFileWindow() {
        fileFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        fileFrame.setSize(500, 500);
        fileFrame.setLocationRelativeTo(null);

        fileFrame.getContentPane().setLayout(fileLayout);
        fileLayout.setAutoCreateGaps(true);
        fileLayout.setAutoCreateContainerGaps(true);

        save.addActionListener(this);

        fileLayout.setHorizontalGroup(
                fileLayout.createSequentialGroup()
                        .addGroup(fileLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(filePath))
                        .addComponent(save)


        );
        fileLayout.setVerticalGroup(
                fileLayout.createSequentialGroup()
                        .addGroup(fileLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(save))
                        .addComponent(filePath)
        );

        fileFrame.setVisible(true);


    }

    public void CreateGraphWindow() {
        graphFrame.setDefaultCloseOperation(HIDE_ON_CLOSE);
        graphFrame.setSize(500, 500);
        graphFrame.setLocationRelativeTo(null);

        graphFrame.getContentPane().setLayout(graphLayout);

        show.setAlignmentX(Component.CENTER_ALIGNMENT);
        show.addActionListener(this);
        graphFrame.getContentPane().add(show);

        AddNode.setAlignmentX(Component.CENTER_ALIGNMENT);
        AddNode.addActionListener(this);
        graphFrame.getContentPane().add(AddNode);

        RemoveNode.setAlignmentX(Component.CENTER_ALIGNMENT);
        RemoveNode.addActionListener(this);
        graphFrame.getContentPane().add(RemoveNode);


        Connect2Nodes.setAlignmentX(Component.CENTER_ALIGNMENT);
        Connect2Nodes.addActionListener(this);
        graphFrame.getContentPane().add(Connect2Nodes);


        removeEdges.setAlignmentX(Component.CENTER_ALIGNMENT);
        removeEdges.addActionListener(this);
        graphFrame.getContentPane().add(removeEdges);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.fileActions) {
            fileFrame.setVisible(true);
        }
        if (e.getSource() == this.save) {
            String Path = filePath.getText();
            String[] split = Path.split("\\.");
            if (split.length == 0 || !split[split.length - 1].equals("json")) {
                filePath.setText("invalid path");
            } else {
                GA.save(Path);
                JOptionPane.showMessageDialog(null, "Saved!");
                filePath.setText("enter path file");
                fileFrame.setVisible(false);
            }
        }
        if (e.getSource() == this.graphActions) {
            graphFrame.setVisible(true);
        }
        if (e.getSource() == this.show) {
            showGraph.createAndShowGui(this.GA.getGraph(), new ArrayList<>(), null);
        }
        if (e.getSource() == this.removeEdges) {
            String src = openSrc();
            String dest = openDest();
            this.GA.getGraph().removeEdge(Integer.parseInt(src), Integer.parseInt(dest));
        }
        if (e.getSource() == this.RemoveNode) {
            String node = openNode();
            this.GA.getGraph().removeNode(Integer.parseInt(node));
        }
        if (e.getSource() == this.Connect2Nodes) {
            String node1 = openNode1();
            String node2 = openNode2();
            String w = openW();
            this.GA.getGraph().connect(Integer.parseInt(node1), Integer.parseInt(node2), Double.parseDouble(w));
        }
        if (e.getSource() == this.AddNode) {
            String node = openNode();
            String x = openLocX();
            String y = openLocY();
            GeoLocation loc = new Location(Double.parseDouble(x), Double.parseDouble(y), 0.0);
            NodeData n = new Node(Integer.parseInt(node), loc);
            this.GA.getGraph().addNode(n);
        }
        if (e.getSource() == this.algoActions) {
            algoFrame.setVisible(true);
        }
        if (e.getSource() == this.isConnected) {
            Boolean ans = this.GA.isConnected();
            String answer = ans ? "YES" : "NO";
            JOptionPane.showMessageDialog(null, answer);
        }
        if (e.getSource() == this.shortestPathDist) {
            String src = openSrc();
            String dest = openDest();
            double ans = this.GA.shortestPathDist(Integer.parseInt(src), Integer.parseInt(dest));
            JOptionPane.showMessageDialog(null, ans);
        }
        if (e.getSource() == this.shortestPath) {
            String src = openSrc();
            String dest = openDest();
            List<NodeData> path = this.GA.shortestPath(Integer.parseInt(src), Integer.parseInt(dest));
            showGraph.createAndShowGui(this.GA.getGraph(), getEdgesOfPath(path), null);
        }
        if (e.getSource() == this.center) {
            showGraph.createAndShowGui(this.GA.getGraph(), null, this.GA.center());
        }

        if (e.getSource() == this.tsp_path) {
            Iterator<NodeData> iter = GA.getGraph().nodeIter();
            ArrayList<Integer> option = new ArrayList<>();

            while (iter.hasNext()) {
                option.add(iter.next().getKey());
            }
            Object[] options = option.toArray();
            int n = JOptionPane.showOptionDialog(graphFrame,
                    "choose Nodes",
                    "A Silly Question",
                    JOptionPane.CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    null);
            NodeData no = GA.getGraph().getNode(n);
            if (!cities.contains(no)){
                cities.add(no);
            }
        }
        if (e.getSource() == this.drawTSP) {
            if(cities.size() == 1){
                JOptionPane.showMessageDialog(null, "Insert More Than One Node");
            }
            else if(cities.size() != 0) {
                List<NodeData> path = this.GA.tsp(cities);
                showGraph.createAndShowGui(this.GA.getGraph(), getEdgesOfPath(path), null);
                cities = new ArrayList<>();
            }
            else{
                JOptionPane.showMessageDialog(null, "Insert Nodes!");
            }
        }

    }

    public String openSrc() {
        String s = (String) JOptionPane.showInputDialog(
                graphFrame,
                "insert source node:\n",
                "Source",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "0");
        return s;
    }

    public String openDest() {
        String s = (String) JOptionPane.showInputDialog(
                graphFrame,
                "insert destination node:\n",
                "Destination",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "0");
        return s;
    }

    public String openNode() {
        String s = (String) JOptionPane.showInputDialog(
                graphFrame,
                "insert node:\n",
                "Node",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "0");
        return s;
    }

    public String openNode1() {
        String s = (String) JOptionPane.showInputDialog(
                graphFrame,
                "insert first node:\n",
                "Source",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "0");
        return s;
    }

    public String openNode2() {
        String s = (String) JOptionPane.showInputDialog(
                graphFrame,
                "insert second node:\n",
                "Destination",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "0");
        return s;
    }

    public String openW() {
        String s = (String) JOptionPane.showInputDialog(
                graphFrame,
                "insert weight of edge:\n",
                "Weight",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "0.0");
        return s;
    }

    public String openLocX() {
        String s = (String) JOptionPane.showInputDialog(
                graphFrame,
                "insert x-axis location of node:\n",
                "X",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "0.0");
        return s;
    }

    public String openLocY() {
        String s = (String) JOptionPane.showInputDialog(
                graphFrame,
                "insert y-axis location of node:\n",
                "Y",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "0.0");
        return s;
    }


    public ArrayList<EdgeData> getEdgesOfPath(List<NodeData> path) {
        ArrayList<EdgeData> pathEdges = new ArrayList<>();
        for (int i = 0; i < path.size() - 1; i++) {
            EdgeData edge = this.GA.getGraph().getEdge(path.get(i).getKey(), path.get(i + 1).getKey());
            pathEdges.add(edge);
        }

        return pathEdges;
    }
}
