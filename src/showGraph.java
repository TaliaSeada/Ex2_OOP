import api.DirectedWeightedGraph;
import api.EdgeData;
import api.GeoLocation;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.security.cert.PolicyNode;
import java.util.*;
import java.util.List;
/**
 * this class builds part of the GUI for this project
 */
public class showGraph extends JPanel {
    private static GraphAlgorithms GA = new GraphAlgorithms();
    private List<GeoLocation> scores;
    private int padding = 20;
    private int labelPadding = 12;
    private static int pointWidth = 7;
    private Color pointColor = new Color(255,0,0);
    private Color lineColor = new Color(0,0,0);
    private Color indexColor = new Color(0,0,255);
    public ArrayList<EdgeData> paintEdges = new ArrayList<>();
    private NodeData center;
    public HashMap<Integer,GeoLocation> locations = new HashMap<>();
    public HashMap<Integer,Point> points = new HashMap<>();

    // constructor
    public showGraph(List<GeoLocation> scores,ArrayList<EdgeData> edgesToPaint, NodeData center) {
        this.paintEdges = edgesToPaint;
        this.scores = scores;
        this.center = center;
        Iterator<NodeData> iter = GA.getGraph().nodeIter();
        while(iter.hasNext())
        {
            NodeData n = iter.next();
            locations.put(n.getKey(),n.getLocation());
        }
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
        for (Integer key:locations.keySet()) {
            int x1 = (int) ((min_x - locations.get(key).x()) * xScale + padding);
            int y1 = (int) ((min_y - locations.get(key).y()) * yScale + padding);
            points.put(key,new Point(x1,y1));
        }

        Stroke oldStroke = g2.getStroke();
        g2.setColor(lineColor);

        for (Integer i: points.keySet()) {
            Iterator<EdgeData> edges = GA.getGraph().edgeIter(i);
            while(edges.hasNext()) {
                EdgeData edge = edges.next();
                int x1 = points.get(i).x;
                int y1 = points.get(i).y;
                int x2 = points.get(edge.getDest()).x;
                int y2 = points.get(edge.getDest()).y;

                drawArrowLine(g2,x1,y1,x2,y2,7,4);
            }
        }

        g2.setColor(Color.CYAN);

        if(paintEdges != null)
        {
            for(EdgeData edge: this.paintEdges) {
                int x1 = points.get(edge.getSrc()).x;
                int y1 = points.get(edge.getSrc()).y;
                int x2 = points.get(edge.getDest()).x;
                int y2 = points.get(edge.getDest()).y;

                drawArrowLine(g2,x1,y1,x2,y2,7,4);
            }
        }


        g2.setStroke(oldStroke);
        g2.setColor(pointColor);

        for (Integer i:this.points.keySet()) {
            g2.setColor(pointColor);
            int x = points.get(i).x - pointWidth / 2;
            int y = points.get(i).y - pointWidth / 2;
            int ovalW = pointWidth;
            int ovalH = pointWidth;
            g2.fillOval(x, y, ovalW, ovalH);

            g2.setColor(indexColor);
            g2.setFont(new Font("Serif", Font.PLAIN, 14));
            g2.drawString(i+"", x-15, y+15);
            if(this.center != null && this.center.getKey() == i) {
                g2.setColor(Color.BLACK);
                g2.drawString("center", x + 10, y - 10);
            }
        }
    }

    public static void createAndShowGui(DirectedWeightedGraph g, ArrayList<EdgeData> toPaint, NodeData center) {
        GA.init(g);
        List<GeoLocation> scores = new ArrayList<>();
        Iterator<NodeData> iter = g.nodeIter();
        while(iter.hasNext()){
            NodeData n = iter.next();
            scores.add(n.getLocation());
        }

        /* Main panel */
        showGraph mainPanel = new showGraph(scores,toPaint,center);
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