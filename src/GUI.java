//Usually you will require both swing and awt packages
// even if you are working with just swings.
import javax.swing.*;
import java.awt.*;
class GUI {
    public static void main(String args[]) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createMainWindow();
            }
        });


    }

    public static void createMainWindow() {
        JFrame frame = new JFrame("Graph gui");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        myBoxLayout.createMainWindow(frame.getContentPane());

        //frame.pack();
        frame.setVisible(true);
    }
}