import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class myBoxLayout{
    public static void createMainWindow(Container pane) {
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        JButton fileActions = new JButton("press to do actions from files");
        fileActions.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(fileActions);
        pane.add(Box.createRigidArea(new Dimension(5, 20)));
        fileActions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateFileWindow(pane);
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

    public static void CreateFileWindow(Container pane){
        String Path = "";
        pane.setLayout(new GroupLayout(pane));
        JButton load = new JButton("load from file");
    }


}
