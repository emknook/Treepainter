package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import model.Tree;

public class PineTreePainter extends TreePainter {

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
			leavesColor = new Color(61, 94, 84);
			g2.setStroke(new BasicStroke((int) (XXLStroke * factor)));
			break;
		case XL:
			width = (int) (XLWidth * factor);
			height = (int) (XLHeight * factor);
			diameter = (int) (XLDiameter * factor);
			leavesColor = new Color(66, 109, 82);
			g2.setStroke(new BasicStroke((int) (XLStroke * factor)));
			break;
		case L:
			width = (int) (LWidth * factor);
			height = (int) (LHeight * factor);
			diameter = (int) (LDiameter * factor);
			leavesColor = new Color(71, 124, 83);
			g2.setStroke(new BasicStroke((int) (LStroke * factor)));
			break;
		case M:
			width = (int) (MWidth * factor);
			height = (int) (MHeight * factor);
			diameter = (int) (MDiameter * factor);
			leavesColor = new Color(68, 132, 72);
			g2.setStroke(new BasicStroke((int) (MStroke * factor)));
			break;
		case S:
			width = (int) (SWidth * factor);
			height = (int) (SHeight * factor);
			diameter = (int) (SDiameter * factor);
			leavesColor = new Color(73, 142, 69);
			g2.setStroke(new BasicStroke((int) (SStroke * factor)));
			break;
		}
		int x = (int) (t.getRelX() / 100 * p.getWidth());
		int y = (int) (t.getRelY() / 100 * p.getHeight()) - height;
		g.setColor(Color.DARK_GRAY);
		g.fillOval(x - width / 2, y + height - (height / 12 / 2), width * 2, height / 12);
		g.setColor(color);
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
		g.setColor(leavesColor);
		y = y + diameter / 3;
		g.fillPolygon(
				new int[] { (int) (x + width / 2 + diameter / 2 * 0.65), x + width / 2,
						(int) (x + width / 2 - diameter / 2 * 0.65) },
				new int[] { (int) (y + diameter * 0.23), (int) (y - diameter * 0.6), (int) (y + diameter * 0.23) }, 3);
		g.fillArc(x - diameter / 2 + width / 2, y - diameter / 2, diameter, (int) (diameter * 0.8), -125, 70);
		g.setColor(Color.BLACK);
		g.drawArc(x - diameter / 2 + width / 2, y - diameter / 2, diameter, (int) (diameter * 0.8), -125, 70);
		g.drawLine((int) (x + width / 2 + diameter / 2 * 0.62), (int) (y + diameter * 0.22), x + width / 2,
				(int) (y - diameter * 0.6));
		g.drawLine((int) (x + width / 2 - diameter / 2 * 0.62), (int) (y + diameter * 0.22), x + width / 2,
				(int) (y - diameter * 0.6));
	}

}
