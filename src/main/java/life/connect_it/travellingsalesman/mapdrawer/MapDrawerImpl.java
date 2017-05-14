package life.connect_it.travellingsalesman.mapdrawer;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class MapDrawerImpl implements MapDrawer {

    private Frame mainFrame;
    private Label headerLabel;
    private Label statusLabel;
    private Panel controlPanel;

    public MapDrawerImpl() {
        prepareGUI();
    }

    @Override
    public void drawMap(List<double[]> coordinates, List<Integer> order) {
        List<double[]> way = new ArrayList<>();
        order.forEach(index -> way.add(coordinates.get(index)));
        controlPanel.add(new MyCanvas(way));
        mainFrame.setVisible(true);
    }

    private void prepareGUI() {
        mainFrame = new Frame("Java AWT Examples");
        mainFrame.setSize(1200, 1200);
        mainFrame.setLayout(new GridLayout(3, 1));
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        headerLabel = new Label();
        headerLabel.setAlignment(Label.CENTER);
        statusLabel = new Label();
        statusLabel.setAlignment(Label.CENTER);
        statusLabel.setSize(350, 100);

        controlPanel = new Panel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }


    class MyCanvas extends Canvas {
        private final List<double[]> way;

        public MyCanvas(List<double[]> way) {
            setBackground(Color.GRAY);
            setSize(300, 300);
            this.way = way;
        }

        public void paint(Graphics g) {
            Graphics2D g2;
            g2 = (Graphics2D) g;
            int factor = 100;
            for (int i = 0; i < way.size() - 1; i++) {
                double[] start = way.get(i);
                double[] end = way.get(i + 1);
                g2.drawLine(Double.valueOf(factor * start[0]).intValue(),
                    Double.valueOf(factor * start[1]).intValue(),
                    Double.valueOf(factor * end[0]).intValue(),
                    Double.valueOf(factor * end[1]).intValue());
            }
            g2.drawLine(Double.valueOf(factor * way.get(way.size() - 1)[0]).intValue(),
                Double.valueOf(factor * way.get(way.size() - 1)[1]).intValue(),
                Double.valueOf(factor * way.get(0)[0]).intValue(),
                Double.valueOf(factor * way.get(0)[1]).intValue());
        }
    }
}

