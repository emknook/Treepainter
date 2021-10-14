package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.Duck;

public class DuckPainter {

	private BufferedImage duckImage;
	private int x = 0;
	private int y = 0;
	private final int w = 183;
	private final int h = 150;

	public DuckPainter() {
		try {
			duckImage = ImageIO.read(DuckPainter.class.getResource("/pics/duckshapes.png"));
		} catch (IOException e) {
			System.err.println("Image not found");
		}
	}

	public void paintDuck(Duck duck, Graphics g, PaintingPanel p) {
		if (duck != null) {
			switch (duck.getShape()) {
			case Up:
				x = w * 2;
				y = 0;
				break;
			case HalfUp:
				x = w;
				y = 0;
				break;
			case StartUp:
				x = 0;
				y = 0;
				break;
			case Mid:
				x = w * 2;
				y = h;
				break;
			case StartDown:
				x = w;
				y = h;
				break;
			case Down:
				x = 0;
				y = h;
				break;
			default:
				break;
			}
			g.drawImage(duckImage.getSubimage(x, y, w, h), (int) (duck.getRelX() / 100 * p.getWidth()) - w / 2,
					(int) (duck.getRelY() / 100 * p.getHeight()), null);
		}
	}

}
