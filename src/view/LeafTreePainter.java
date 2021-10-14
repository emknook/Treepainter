package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import model.Tree;

public class LeafTreePainter extends TreePainter {

	@Override
	void paintTree(Tree t, Graphics g, PaintingPanel p) {
		Graphics2D g2 = (Graphics2D) g;
		int width = 0;
		int height = 0;
		int diameter = 0;
		Color leavesColor = new Color(1, 196, 6);
		Color color = new Color(198, 100, 9);
		double factor;
		if (t.getRelY() != 100) {
			factor = (((100 - t.getRelY()) / 6) * -0.09 + 1);
		} else {
			factor = 1;
		}
		switch (t.getSize()) {
		case XXL:
			width = (int) (XXLWidth * factor);
			height = (int) (XXLHeight * factor);
			diameter = (int) (XXLDiameter * factor);
			leavesColor = new Color(0, 71, 1);
			g2.setStroke(new BasicStroke((int) (XXLStroke * factor)));
			break;
		case XL:
			width = (int) (XLWidth * factor);
			height = (int) (XLHeight * factor);
			diameter = (int) (XLDiameter * factor);
			leavesColor = new Color(1, 91, 3);
			g2.setStroke(new BasicStroke((int) (XLStroke * factor)));
			break;
		case L:
			width = (int) (LWidth * factor);
			height = (int) (LHeight * factor);
			diameter = (int) (LDiameter * factor);
			leavesColor = new Color(1, 130, 4);
			g2.setStroke(new BasicStroke((int) (LStroke * factor)));
			break;
		case M:
			width = (int) (MWidth * factor);
			height = (int) (MHeight * factor);
			diameter = (int) (MDiameter * factor);
			leavesColor = new Color(1, 158, 5);
			g2.setStroke(new BasicStroke((int) (MStroke * factor)));
			break;
		case S:
			width = (int) (SWidth * factor);
			height = (int) (SHeight * factor);
			diameter = (int) (SDiameter * factor);
			leavesColor = new Color(1, 196, 6);
			g2.setStroke(new BasicStroke((int) (SStroke * factor)));
			break;
		}
		int x = (int) (t.getRelX() / 100 * p.getWidth());
		int y = (int) (t.getRelY() / 100 * p.getHeight()) - height;
		g.setColor(Color.DARK_GRAY);
		g.fillOval(x-width/2, y+height-(height/12/2), width*2, height/12);
		g.setColor(color);
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
		g.setColor(leavesColor);
		g.fillOval(x - diameter / 2 + width / 2, y - diameter / 2, diameter, diameter);
		g.setColor(Color.BLACK);
		g.drawOval(x - diameter / 2 + width / 2, y - diameter / 2, diameter, diameter);
	}

}
