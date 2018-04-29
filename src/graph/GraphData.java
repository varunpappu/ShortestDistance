package graph;

import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

import javax.swing.*;

public class GraphData extends JPanel {
	ArrayList<Integer> counter = new ArrayList<Integer>();
	final int PAD = 20;

	public GraphData(ArrayList<Integer> counter) {

		this.counter = counter;
		System.out.println(counter.toString());
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int w = getWidth();
		int h = getHeight();
		// Draw ordinate.
		g2.draw(new Line2D.Double(PAD, PAD, PAD, h - PAD));
		// Draw abcissa.
		g2.draw(new Line2D.Double(PAD, h - PAD, w - PAD, h - PAD));
		double xInc = (double) (w - 2 * PAD) / (counter.size() - 1);
		double scale = (double) (h - 2 * PAD) / getMax();
		// Mark data points.
		g2.setPaint(Color.red);
		for (int i = 0; i < counter.size(); i++) {
			double x = PAD + i * xInc;
			double y = h - PAD - scale * counter.get(i);
			g2.fill(new Ellipse2D.Double(x - 2, y - 2, 4, 4));
		}
	}

	private int getMax() {
		int max = -Integer.MAX_VALUE;
		for (int i = 0; i < counter.size(); i++) {
			if (counter.get(i) > max)
				max = counter.get(i);
		}
		return max;
	}

}