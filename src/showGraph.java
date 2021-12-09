import api.DirectedWeightedGraph;
import api.EdgeData;
import api.GeoLocation;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class showGraph extends JPanel {
    private static GraphAlgorithms GA = new GraphAlgorithms();
    private List<GeoLocation> scores;
    private int padding = 20;
    private int labelPadding = 12;
    private static int pointWidth = 7;
    private Color pointColor = new Color(255,0,0);
    private Color lineColor = new Color(0,0,0);
    private Color indexColor = new Color(0,0,255);



    // constructor
    public showGraph(List<GeoLocation> scores) {
        this.scores = scores;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double min_x = Double.MAX_VALUE, min_y = Double.MAX_VALUE;
        double max_x = Double.MIN_VALUE, max_y = Double.MIN_VALUE;
        int t = 0;
        while(t < scores.size()){
            if(scores.get(t).x() < min_x){
                min_x = scores.get(t).x();
            }
            if(scores.get(t).x() > max_x){
                max_x = scores.get(t).x();
            }
            if(scores.get(t).y() < min_y){
                min_y = scores.get(t).y();
            }
            if(scores.get(t).y() > max_y){
                max_y = scores.get(t).y();
            }
            t++;
        }

        double xScale = ((double) getWidth() - (3 * padding) - labelPadding) / (min_x - max_x);
        double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (min_y - max_y);

        List<Point> graphPoints = new ArrayList<>();
        for (int i = 0; i < scores.size(); i++) {
            int x1 = (int) ((min_x - scores.get(i).x()) * xScale + padding);
            int y1 = (int) ((min_y - scores.get(i).y()) * yScale + padding);
            graphPoints.add(new Point(x1, y1));
        }

        Stroke oldStroke = g2.getStroke();
        g2.setColor(lineColor);

        for (int i = 0; i < graphPoints.size() - 1; i++) {
            Iterator<EdgeData> edges = GA.getGraph().edgeIter(i);
            while(edges.hasNext()) {
                EdgeData edge = edges.next();
                int x1 = graphPoints.get(i).x;
                int y1 = graphPoints.get(i).y;
                int x2 = graphPoints.get(edge.getDest()).x;
                int y2 = graphPoints.get(edge.getDest()).y;

                drawArrowLine(g2,x1,y1,x2,y2,7,4);
            }
        }

        g2.setStroke(oldStroke);
        g2.setColor(pointColor);

        for (int i = 0; i < graphPoints.size(); i++) {
            g2.setColor(pointColor);
            int x = graphPoints.get(i).x - pointWidth / 2;
            int y = graphPoints.get(i).y - pointWidth / 2;
            int ovalW = pointWidth;
            int ovalH = pointWidth;
            g2.fillOval(x, y, ovalW, ovalH);

            g2.setColor(indexColor);
            g2.drawString(i+"", x-15, y+15);
        }
    }

    public static void createAndShowGui(DirectedWeightedGraph g) {
        GA.init(g);
//        GA.load("data/G1.json");
//        g = GA.getGraph();
        List<GeoLocation> scores = new ArrayList<>();
        Iterator<NodeData> iter = g.nodeIter();
        while(iter.hasNext()){
            NodeData n = iter.next();
            scores.add(n.getLocation());
        }

        /* Main panel */
        showGraph mainPanel = new showGraph(scores);
        mainPanel.setPreferredSize(new Dimension(800, 600));
        /* creating the frame */
        JFrame frame = new JFrame("Graph");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Draw an arrow line between two points.
     * @param g the graphics component.
     * @param x1 x-position of first point.
     * @param y1 y-position of first point.
     * @param x2 x-position of second point.
     * @param y2 y-position of second point.
     * @param d  the width of the arrow.
     * @param h  the height of the arrow.
     */
    private void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2, int d, int h) {
        int dx = x2 - x1, dy = y2 - y1;
        double D = Math.sqrt(dx*dx + dy*dy);
        double xm = D - d, xn = xm, ym = h, yn = -h, x;
        double sin = dy / D, cos = dx / D;

        x = xm*cos - ym*sin + x1;
        ym = xm*sin + ym*cos + y1;
        xm = x;

        x = xn*cos - yn*sin + x1;
        yn = xn*sin + yn*cos + y1;
        xn = x;

        int[] xpoints = {x2, (int) xm, (int) xn};
        int[] ypoints = {y2, (int) ym, (int) yn};

        g.drawLine(x1, y1, x2, y2);
        g.fillPolygon(xpoints, ypoints, 3);
    }


//    /*the main method runs createAndShowGui*/
//    public static void main(String[] args) {
//
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                createAndShowGui(GA.getGraph());
//            }
//        });}
}