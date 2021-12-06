import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class myBoxLayout{
    public final boolean loaded = false;
    public static void createMainWindow(Container pane) {
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        JButton fileActions = new JButton("press to do actions from files");
        fileActions.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(fileActions);
        pane.add(Box.createRigidArea(new Dimension(5, 20)));
        fileActions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateFileWindow();
            }
        });


        JButton graphActions = new JButton("press to see actions on graph");
        graphActions.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(graphActions);
        pane.add(Box.createRigidArea(new Dimension(5, 20)));

        JButton algoActions = new JButton("press to see algorithms to run on graph");
        algoActions.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(algoActions);
    }

    public static void CreateFileWindow(){
        JFrame frame = new JFrame("file actions window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        String Path = "";
        GroupLayout layout = new GroupLayout(frame.getContentPane());
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JTextField path = new JTextField("Enter path of file (instead of this)");

        JButton load = new JButton("load from file");



        JButton save = new JButton("Save to file");

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addComponent(save)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(path)
                                .addComponent(load))
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
