package group18;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

@SuppressWarnings("serial")
public class DrawGraph extends JPanel {
    private static final int MAX_SCORE = 90000; // max cases per day
    private static final int PREF_W = 1200; // width of the jframe
    private static final int PREF_H = 650;
    private static final int BORDER_GAP = 40;
    private static final Color GRAPH_COLOR = Color.green;
    private static final Color GRAPH_POINT_COLOR = new Color(150, 50, 50, 180);
    private static final Stroke GRAPH_STROKE = new BasicStroke(3f);
    private static final int GRAPH_POINT_WIDTH = 10;
    private static final int Y_HATCH_CNT = 10;
    private List<Integer> scores;
    private List<Integer> scores1;

    public DrawGraph(List<Integer> scores,List<Integer> scores1) {
        this.scores = scores;
        this.scores1 =scores1 ;
    }

    public DrawGraph() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        double xScale = ((double) getWidth() - 2 * BORDER_GAP) / (scores.size() - 1);
        double xScale1 = ((double) getWidth() - 2 * BORDER_GAP) / (scores1.size() - 1);
        double yScale = ((double) getHeight() - 2 * BORDER_GAP) / (MAX_SCORE - 1);


        List<Point> graphPoints = new ArrayList<>();
        List<Point> graphPointsDeath = new ArrayList<>();

        for (int i = 0; i < scores.size(); i++) {
            int x1 = (int) (i * xScale + BORDER_GAP);

            int y1 = (int) ((MAX_SCORE - scores.get(i)) * yScale + BORDER_GAP);

            graphPoints.add(new Point(x1, y1));
        }

        // deaths

        for (int i = 0; i < scores1.size(); i++) {
            int x1 = (int) (i * xScale1 + BORDER_GAP);

            int y1 = (int) ((MAX_SCORE - scores1.get(i)) * yScale + BORDER_GAP);

            graphPointsDeath.add(new Point(x1, y1));
        }


        // create x and y axes
        g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);
        g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, getWidth() - BORDER_GAP, getHeight() - BORDER_GAP);

        // create hatch marks for y axis.
        for (int i = 0; i < Y_HATCH_CNT; i++) {
            int x1 = GRAPH_POINT_WIDTH + BORDER_GAP;
            int y0 = getHeight() - (((i + 1) * (getHeight() - BORDER_GAP * 2)) / Y_HATCH_CNT + BORDER_GAP);
            g2.drawLine(BORDER_GAP, y0, x1, y0);
        }

        String[] cases = {"0","8000", "16000", "24000", "32000", "40000", "48000", "56000", "64000", "72000", "80000"};
        for (int i = 0; i < scores.size() - 1; i++) {
            for (int j = 0; j < 11; j++) {
                int y0 = getHeight() - (((i + 1) * (getHeight() - BORDER_GAP * 2)) / (scores.size() - 1)  + BORDER_GAP);
                int num = 0;
                for(int k = 1; k <= j; k++)
                {
                    num = num - 55; // adding -55 to the numbers to change the position
                }
                g2.drawString(cases[j], BORDER_GAP - 35, y0 + num);
            }
            i = i + 8000;
        }

//        // create hatch marks for x axis.
//        for (
//                int i = 0; i < scores.size() + 12; i++) {
//            int x0 = (i + 1) * (getWidth() - BORDER_GAP * 2) / (scores.size() - 1) + BORDER_GAP;
//            int y0 = getHeight() - BORDER_GAP;
//            int y1 = y0 - GRAPH_POINT_WIDTH;
//            g2.drawLine(x0, y0, x0, y1);
//
//        }
        // Print the x axis values
        String[] months = {"Feb", "March", "April", "May", "Jun", "July", "Aug", "sep", "oct", "nov", "dec","jan","feb","march","April"};
        for (
                int i = 0; i < scores.size() - 1; ) {
            for (int j = 0; j < 15; j++) {
                int x0 = (i + 1) * (getWidth() - BORDER_GAP * 2) / (scores.size() - 1) + BORDER_GAP;
                int y0 = getHeight() - BORDER_GAP;
                g2.drawString(months[j], x0, y0 + 20);
                i = i + 30;
            }
        }

        Stroke oldStroke = g2.getStroke();
        g2.setColor(GRAPH_COLOR);
        g2.setStroke(GRAPH_STROKE);

        // this plots the cases graph in green color ( graph_color ) -- Ratnesh
        for (
                int i = 0; i < graphPoints.size() - 1; i++) {
            int two_weeks =FrameTest.sizeofrealcases + 13;
            int four_weeks =FrameTest.sizeofrealcases + 27;
            int six_weeks =FrameTest.sizeofrealcases + 40;

            if (i == FrameTest.sizeofrealcases) {
                g2.setColor(new Color(51, 102, 0));

            }

            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);

            if (i == two_weeks) {
                g2.drawString("2W" ,x2,y2-60);
            }
            if (i == four_weeks) {
                g2.drawString("4W" ,x2,y2-60);
            }
            if (i == six_weeks) {
                g2.drawString("6W" ,x2,y2-60);
            }
        }

        g2.setColor(Color.yellow); // deaths graph color   -- Ratnesh
        // this plots the whole death graph   -- Ratnesh


        for (
                int i = 0; i < graphPointsDeath.size() - 1; i++) {
            if (i == FrameTest.sizeofrealdeaths) {
                g2.setColor(new Color(255, 153, 0));

            }
            int x1 = graphPointsDeath.get(i).x;
            int y1 = graphPointsDeath.get(i).y;
            int x2 = graphPointsDeath.get(i + 1).x;
            int y2 = graphPointsDeath.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);

        }

        g2.setStroke(oldStroke);

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    static void createAndShowGui(ArrayList<Integer> scores,ArrayList<Integer> scores1) {
        /*List<Integer> scores = new ArrayList<Integer>();*/

        DrawGraph mainPanel = new DrawGraph(scores,scores1);

        JFrame frame = new JFrame("DrawGraph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();

        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setResizable(false);
    }

}