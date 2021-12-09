import javax.swing.*;
class GUI {
    /**
     * this class activate the GUI of this project
     */
    public static void activateGUI(String path){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
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
        frame.setLocationRelativeTo(null);

        //frame.pack();
        frame.setVisible(true);
    }
}