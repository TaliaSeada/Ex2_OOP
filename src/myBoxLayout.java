import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class myBoxLayout{
    public static boolean loaded = false;
    private static GraphAlgorithms GA = new GraphAlgorithms();

    public static void createMainWindow(Container pane) {
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        JButton fileActions = new JButton("Files");
        fileActions.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(fileActions);
        pane.add(Box.createRigidArea(new Dimension(5, 20)));
        fileActions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateFileWindow();
            }
        });


        JButton graphActions = new JButton("Graph");
        graphActions.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(graphActions);
        pane.add(Box.createRigidArea(new Dimension(5, 20)));

        graphActions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!loaded){
                    JOptionPane.showMessageDialog(pane, "You did not load any file!");
                }
            }
        });

        JButton algoActions = new JButton("Algorithms");
        algoActions.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(algoActions);
        algoActions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!loaded){
                    JOptionPane.showMessageDialog(pane, "You did not load any file!");
                }
            }
        });
    }

    public static void CreateFileWindow(){
        JFrame frame = new JFrame("file actions window");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(500, 500);

        GroupLayout layout = new GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JTextField path = new JTextField("Enter path of file");

        JButton load = new JButton("load from file");
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Path = path.getText();
                if(!GA.load(Path)) {
                    path.setText("invalid path!");
                }
                else {
                    loaded = true;
                    frame.dispose();
                }
            }
        });



        JButton save = new JButton("Save to file");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!loaded) {
                    JOptionPane.showMessageDialog(frame, "You did not load any file!");
                }
                else {
                    String Path = path.getText();
                    GA.save(Path);
                }
            }
        });

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(load)
                                .addComponent(path))
                        .addComponent(save)


        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(save)
                                .addComponent(load))
                        .addComponent(path)
        );

        frame.setVisible(true);


    }


}
