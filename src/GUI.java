//Usually you will require both swing and awt packages
// even if you are working with just swings.
import javax.swing.*;
class GUI {
    public static void main(String args[]) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            String path = "data/G1.json";
            public void run() {
                createMainWindow(path);
            }
        });


    }

    public static void createMainWindow(String path) {
        JFrame frame = new JFrame("Graph gui");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        myBoxLayout layout = new myBoxLayout();
        layout.createMainWindow(frame.getContentPane(), path);

        //frame.pack();
        frame.setVisible(true);
    }
}