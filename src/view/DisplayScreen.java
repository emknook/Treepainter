package view;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;


public class DisplayScreen {

	private int height;
	private int width;

	public DisplayScreen() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		width = gd.getDisplayMode().getWidth();
		height = gd.getDisplayMode().getHeight();
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public Point getCenteredPoint(PaintingFrame p) {
		Point returnPoint = new Point(width / 2 - p.getWidth() / 2,
				height / 2 - p.getHeight() / 2 - p.getInsets().top);
		return returnPoint;
	}

}
