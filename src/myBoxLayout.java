import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class myBoxLayout extends JFrame implements ActionListener {
    private GraphAlgorithms GA = new GraphAlgorithms();
    // Buttons
    JButton fileActions = new JButton("file");
    JButton graphActions = new JButton("Graph");
    JButton algoActions = new JButton("algorithms");
    JButton save = new JButton("save to file");
    JButton show = new JButton("show button");
    JButton AddNode = new JButton("Add node");
    JButton RemoveNode = new JButton("remove Node");
    JButton Connect2Nodes = new JButton("Connect between 2 nodes");
    JButton removeEdges = new JButton("Disconnect 2 nodes");
    // texts
    JTextField filePath = new JTextField("enter path to file");
    // frames
    JFrame fileFrame = new JFrame("file actions window");
    JFrame graphFrame = new JFrame("graph window");
    JFrame algoFrame = new JFrame("Algorithm");
    Container cont;

    //layouts
    GroupLayout fileLayout = new GroupLayout(fileFrame.getContentPane());
    BoxLayout graphLayout = new BoxLayout(graphFrame.getContentPane(),BoxLayout.Y_AXIS);

    //dialogs
    JDialog notImplemented = new JDialog(graphFrame,"not implemented yet");


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

    }

    public void CreateFileWindow() {
        fileFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        fileFrame.setSize(500, 500);

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
        if (e.getSource() == this.fileActions){
            fileFrame.setVisible(true);
        }
        if(e.getSource() == this.save){
            String Path = filePath.getText();
            String[] split = Path.split("\\.");
            if (split.length == 0 || !split[split.length - 1].equals("json")) {
                filePath.setText("invalid path");
            }
            else {
                GA.save(Path);
                JOptionPane.showMessageDialog(null, "Saved!");
                filePath.setText("enter path file");
                fileFrame.setVisible(false);
            }
        }
        if(e.getSource() == this.graphActions){
            graphFrame.setVisible(true);
        }
        if(e.getSource() == this.show){

        }
        if(e.getSource() == this.removeEdges){

        }
        if(e.getSource() == this.RemoveNode){

        }
        if(e.getSource() == this.Connect2Nodes){

        }
        if(e.getSource() == this.AddNode){

        }
        if(e.getSource() == this.algoActions){
            CreateAlgoWindow();
        }
    }
}
